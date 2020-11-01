package com.hobart.security.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackages = "com.hobart.security", 
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,
                value = Controller.class)})
public class ApplicationConfig {
    
    //在此配置除了Controller的其他Bean，比如：数据库连接池，事务管理器，业务Bean
    //类似 applicationContext.xml
}
