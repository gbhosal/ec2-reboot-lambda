package com.searshc.hs.ec2rebootlambda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EC2RebootLambdaApplication {
	public EC2RebootLambdaApplication() {
	}

	public static void main(String[] args) {
		SpringApplication.run(EC2RebootLambdaApplication.class, args);
	}
}