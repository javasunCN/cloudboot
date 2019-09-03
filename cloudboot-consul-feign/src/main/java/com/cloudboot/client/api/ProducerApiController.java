package com.cloudboot.client.api;

import com.cloudboot.client.feign.ProducerApiFeign;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: cloudboot
 * @description:
 * @author: zhangzhihong@joyschool.cn
 * @create: 2019-08-30 17:49
 **/
@RestController
public class ProducerApiController {

    @Resource
    ProducerApiFeign producerApiFeign;

    @RequestMapping("/get")
    public String getClinet1Service() {
        // 1、服务别名调用 2、直接调用
        String info = producerApiFeign.getInfo();
        return info;
    }

    @RequestMapping("/getProducerInstances")
    public String getProducerInstances() {
        // 1、服务别名调用 2、直接调用
        String info = producerApiFeign.getProducerInstances();
        return info;
    }

}
