package com.searshc.hs.ec2rebootlambda;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.context.FunctionalSpringApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;

@SpringBootApplication
public class EC2RebootLambdaApplication implements ApplicationContextInitializer<GenericApplicationContext> {
	public EC2RebootLambdaApplication() {
	}

	public static void main(String[] args) {
		FunctionalSpringApplication.run(EC2RebootLambdaApplication.class, args);
	}

	@Override
	public void initialize(GenericApplicationContext applicationContext) {
	}
}