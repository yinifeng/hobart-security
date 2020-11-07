package com.hobart.security.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 该类类型应为：webMvcConfigurer，所以我们实现其接口
 * 通过覆盖重写其中的方法实现扩展MVC的功能
 */
@Configuration
public class HobartWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //引入thymeleaf
        // 浏览器访问：localhost:8080/index.html或者localhost:8080/，都跳转到 classpath:/templates/index.html
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        // 浏览器访问：localhost:8080/main.html 跳转到 classpath:/templates/dashborad.html
        //registry.addViewController("/main.html").setViewName("dashboard");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        
    }
}
