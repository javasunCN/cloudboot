package com.cloudboot.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @EnableEurekaClient:将当前服务注册到Eureka上
 */
@SpringBootApplication
@EnableEurekaClient
public class CloudbootEurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudbootEurekaClientApplication.class, args);
	}

}
