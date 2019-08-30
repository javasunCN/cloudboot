package com.cloudboot.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CloudbootEurekaClient2Application {

	public static void main(String[] args) {
		SpringApplication.run(CloudbootEurekaClient2Application.class, args);
	}

}
