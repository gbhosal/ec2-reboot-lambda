package com.searshc.hs.ec2rebootlambda.function;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;

@Component("EC2RebootFunction")
public class EC2RebootFunction implements Function<ScheduledEvent, String> {
	private static Logger LOGGER = LoggerFactory.getLogger(EC2RebootFunction.class);
	@Value("${default.user.name}")
	private String defaultName = "Maitree";

	@Override
	public String apply(ScheduledEvent scheduledEvent) {
		LOGGER.info("ScheduledEvent = {}", scheduledEvent);
		return "Success";
	}
}