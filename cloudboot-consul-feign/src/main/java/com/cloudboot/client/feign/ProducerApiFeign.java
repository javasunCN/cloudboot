package com.cloudboot.client.feign;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 接口形式书写
 */
@FeignClient(name = "cloudboot-consul-product")
public interface ProducerApiFeign {

    @RequestMapping("/getInfo")
    public String getInfo();

    @RequestMapping("/")
    public String getProducerInstances();
}
