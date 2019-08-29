package com.cloudboot.server.api;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: cloudboot
 * @description: 服务提供者
 * @author: zhangzhihong@joyschool.cn
 * @create: 2019-08-29 16:48
 **/
@RestController
public class ClientApiController {

    @Autowired
    @Lazy
    private EurekaClient eurekaClient;
    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/getService")
    public String getService() {
        return "接口提供服务 cloudboot-eureka-client-1";
    }

    @GetMapping("/greeting")
    public String greeting() {
        return String.format("Hello from '%s'!", eurekaClient.getApplication(appName).getName());
    }
}
