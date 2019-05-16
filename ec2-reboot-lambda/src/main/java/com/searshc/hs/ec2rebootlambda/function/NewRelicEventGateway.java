package com.searshc.hs.ec2rebootlambda.function;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.model.RebootInstancesRequest;
import com.searshc.hs.ec2rebootlambda.vo.NewRelicAlertRequest;

@Component("NewRelicEventGateway")
public class NewRelicEventGateway implements Function<Message<NewRelicAlertRequest>, Message<String>> {
	private static Logger LOGGER = LoggerFactory.getLogger(NewRelicEventGateway.class);
	
	@Override
	public Message<String> apply(Message<NewRelicAlertRequest> request) {
		LOGGER.info("Request Body = {}", request.getPayload());
		request.getHeaders().forEach((key, value) -> LOGGER.info("Headers :: Key = {}, Value = {}", key, value));
		 
		AmazonEC2 amazonEC2 = AmazonEC2ClientBuilder.defaultClient();
		
		NewRelicAlertRequest newRelicAlertRequest = request.getPayload();
		if ("CLOSED".equalsIgnoreCase(newRelicAlertRequest.getCurrentState())) {
			return new GenericMessage<String>("SUCCESS");
		}
		
		if ("Critical-memory-alert".equalsIgnoreCase(newRelicAlertRequest.getConditionName())) {
			List<String> privateDns = newRelicAlertRequest
					.getTargets()
					.stream()
					.filter(e -> "INFRASTRUCTURE".equalsIgnoreCase(e
							.getProduct())
							&& "Host".equalsIgnoreCase(e.getType()))
					.map(e -> e.getName()).collect(Collectors.toList());
			DescribeInstancesRequest describeInstancesRequest = new DescribeInstancesRequest();
			List<Filter> filters = new ArrayList<>();
			Filter filter = new Filter()
							.withName("private-dns-name")
							.withValues(privateDns);
			filters.add(filter);
			describeInstancesRequest.setFilters(filters);
			LOGGER.info("DescribeInstancesRequest = {}", describeInstancesRequest);
			DescribeInstancesResult describeInstancesResult = amazonEC2.describeInstances(describeInstancesRequest);
			LOGGER.info("DescribeInstancesResult = {}", describeInstancesResult);
			
			List<String> instanceIds = null;
			if (!CollectionUtils.isEmpty(describeInstancesResult
					.getReservations())) {
				instanceIds = describeInstancesResult.getReservations().stream()
				.map(e -> e.getInstances()).flatMap(Collection::stream)
				.map(e -> e.getInstanceId()).collect(Collectors.toList());
			}
			
			if (CollectionUtils.isEmpty(instanceIds)) {
				LOGGER.info("No matching EC2 Instance was found");
				return new GenericMessage<String>("FAILURE");
			}
			
			RebootInstancesRequest rebootInstancesRequest = new RebootInstancesRequest();
			rebootInstancesRequest.setInstanceIds(instanceIds);
			
			LOGGER.info("RebootInstancesRequest = {}", rebootInstancesRequest);
			amazonEC2.rebootInstances(rebootInstancesRequest);
			LOGGER.info("Reboot will continue in Async flow");
			return new GenericMessage<String>("SUCCESS");
		}
		return new GenericMessage<String>("SUCCESS");
	}
}