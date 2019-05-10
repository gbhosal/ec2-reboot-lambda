package com.searshc.hs.ec2rebootlambda.function;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import com.searshc.hs.ec2rebootlambda.vo.NewRelicAlertRequest;

@Component("NewRelicEventGateway")
public class NewRelicEventGateway implements Function<Message<NewRelicAlertRequest>, Message<String>> {
	private static Logger LOGGER = LoggerFactory.getLogger(NewRelicEventGateway.class);
	
	@Override
	public Message<String> apply(Message<NewRelicAlertRequest> request) {
		LOGGER.info("Request Body = {}", request.getPayload());
		 request.getHeaders().forEach((key, value) -> LOGGER.info("Headers :: Key = {}, Value = {}", key, value));
		return new GenericMessage<String>("");
	}
}