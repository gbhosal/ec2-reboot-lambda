package com.searshc.hs.ec2rebootlambda.function;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.searshc.hs.ec2rebootlambda.vo.HelloWorldRequest;
import com.searshc.hs.ec2rebootlambda.vo.HelloWorldResponse;

@Component("HelloWorldFunction")
public class HelloWorldFunction implements Function<HelloWorldRequest, HelloWorldResponse> {
	@Value("${default.user.name}")
	private String defaultName;
	
	@Override
	public HelloWorldResponse apply(HelloWorldRequest helloWorldRequest) {
		String userName = helloWorldRequest.getUserName();
		if (StringUtils.isEmpty(userName)) {
			userName = this.defaultName;
		}
		return new HelloWorldResponse("Hello World !!! - [" + userName + "]");
	}
}