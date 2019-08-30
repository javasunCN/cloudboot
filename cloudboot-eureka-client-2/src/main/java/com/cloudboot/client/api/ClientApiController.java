package com.cloudboot.client.api;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @program: cloudboot
 * @description: 服务提供者
 * @author: zhangzhihong@joyschool.cn
 * @create: 2019-08-29 16:48
 **/
@RestController
public class ClientApiController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    @Lazy
    private EurekaClient eurekaClient;
    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/getService")
    public String getService() {
        return "接口提供服务 cloudboot-eureka-client-2";
    }

    @GetMapping("/greeting")
    public String greeting() {
        return String.format("Hello from '%s'!", eurekaClient.getApplication(appName).getName());
    }


    /**
     * 问题1：以别名方式访问必须依赖rebbion相关的配置，添加注解
     *    ## "http://cloudboot-eureka-client1/getService"
     *  java.net.UnknownHostException:cloudboot-eureka-client1
     *
     * @return
     */
    @GetMapping("/client/1/getService")
    public String getClinet1Service() {
        // 1、服务别名调用 2、直接调用
        String info = restTemplate.getForObject("http://cloudboot-eureka-client1/getService", String.class);
        //String info = restTemplate.getForObject("http://localhost:8200/getService", String.class);
        return String.format("调用Client1服务 '%s!", info);
    }
}
