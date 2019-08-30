package com.cloudboot.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaCluster1Application {

	public static void main(String[] args) {
		SpringApplication.run(EurekaCluster1Application.class, args);
	}

}
