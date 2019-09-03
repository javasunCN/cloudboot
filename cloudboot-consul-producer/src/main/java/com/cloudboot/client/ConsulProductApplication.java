package com.cloudboot.client;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.kv.model.GetValue;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.jvm.hotspot.utilities.Hashtable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Autowired
	private ConsulClient consulClient;

	@RequestMapping("/")
	public String get() {

		Map<String, String> map = new HashMap<String, String>();
		List<ServiceInstance> list = discoveryClient.getInstances("cloudboot-consul-product");
		map.put("Instances", new Gson().toJson(list));

		Map<String, String> mapKV = getKVValues("config");
		map.putAll(mapKV);
		return new Gson().toJson(map);
	}

	@RequestMapping("/getInfo")
	public String getInfo() {

		return "订单服务";
	}


	/**
	 * 获取应用配置的所有key-value信息
	 *
	 * @param keyPrefix key所在的目录前缀，格式为：config/应用名称/
	 * @return 应用配置的所有key-value信息
	 */

	public Map<String, String> getKVValues(String keyPrefix) {
		Map<String, String> map = new HashMap<>(16);

		try {
			Response<List<GetValue>> response = consulClient.getKVValues(keyPrefix);
			if (response != null) {
				for (GetValue getValue : response.getValue()) {
					int index = getValue.getKey().lastIndexOf("/") + 1;
					String key = getValue.getKey().substring(index);
					String value = getValue.getDecodedValue();
					map.put(key, value);
				}
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
