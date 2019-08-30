package com.cloudboot.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ConsulProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsulProductApplication.class, args);
	}


	/** 获取服务信息 */
	@Autowired
	private DiscoveryClient discoveryClient;

	@RequestMapping("/")
	public String get() {
		List<ServiceInstance> list = discoveryClient.getInstances("cloudboot-consul-product");
		if (list != null && list.size() > 0 ) {
			return list.get(0).getInstanceId();
		}
		return "无";
	}

	@RequestMapping("/getInfo")
	public String getInfo() {

		return "订单服务";
	}

}
