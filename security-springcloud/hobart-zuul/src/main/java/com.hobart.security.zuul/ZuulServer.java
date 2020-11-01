package com.hobart.security.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


/**
 * 
 * 网关：
 *  1、网关解析JWT令牌，校验权限，解析明文token传到微服务网
 *  
 *  2、网关只做转发
 *  
 *  目前我们这边采用第一种方案
 *  
 *  
 *  网关就是一个资源服务
 * 
 * 
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ZuulServer {
    public static void main(String[] args) {
        SpringApplication.run(ZuulServer.class,args);
    }
}
