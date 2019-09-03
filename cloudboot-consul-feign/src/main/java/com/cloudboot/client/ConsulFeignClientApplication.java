package com.cloudboot.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@RestController
public class ConsulFeignClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsulFeignClientApplication.class, args);
	}


	@Autowired
	private DiscoveryClient discoveryClient;

	@RequestMapping("/")
	public String home() {
		List<ServiceInstance> list = discoveryClient.getInstances("cloudboot-consul-server");
		if (list != null && list.size() > 0 ) {
			return list.get(0).getUri().toString();
		}
		return "无";
	}

}
