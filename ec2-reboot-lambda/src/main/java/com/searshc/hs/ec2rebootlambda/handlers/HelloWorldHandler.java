package com.searshc.hs.ec2rebootlambda.handlers;

import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

import com.searshc.hs.ec2rebootlambda.vo.HelloWorldRequest;
import com.searshc.hs.ec2rebootlambda.vo.HelloWorldResponse;

public class HelloWorldHandler extends SpringBootRequestHandler<HelloWorldRequest, HelloWorldResponse> {
	
}
