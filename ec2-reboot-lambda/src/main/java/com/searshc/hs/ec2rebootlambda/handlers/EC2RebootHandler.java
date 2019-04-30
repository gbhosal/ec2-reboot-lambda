package com.searshc.hs.ec2rebootlambda.handlers;

import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;

public class EC2RebootHandler extends SpringBootRequestHandler<ScheduledEvent, String> {
	
}
