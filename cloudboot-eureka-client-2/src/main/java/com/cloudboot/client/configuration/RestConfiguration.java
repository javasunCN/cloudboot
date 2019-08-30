package com.cloudboot.client.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

/**
 * @program: cloudboot
 * @description: RestTemplate 配置
 *  SpringBoot采用两种调用方式
 *      1、REST: 默认继承ribbon,将RestTemplate注册到Spring环境中,如果使用域名调用时，要开启Ribbon(@LoadBalanced)
 *          @LoadBalanced:启用负载均衡
 *      2、Fegin
 * @author: zhangzhihong@joyschool.cn
 * @create: 2019-08-30 11:01
 **/
@Configuration
public class RestConfiguration {

    /**
     * @LoadBalanced 以别名方式访问
     * @param factory
     * @return
     */
    @Bean
    @ConditionalOnMissingBean({RestOperations.class, RestTemplate.class})
    @LoadBalanced  //(如果是名称的远程要开启，不然要关闭)
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

    @Bean
    @ConditionalOnMissingBean({ClientHttpRequestFactory.class})
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(15000);
        factory.setConnectTimeout(15000);
        return factory;
    }

}
