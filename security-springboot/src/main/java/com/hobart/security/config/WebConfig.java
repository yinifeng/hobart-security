package com.hobart.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    //配置加载Controller
    //类似 springmvc.xml
    
    
    //视图解析器
    //这个可以application.properties 文件配置
/*    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }*/

/*    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }*/

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //重定向spring-security的登录页面
        //registry.addViewController("/").setViewName("redirect:/login"); //重定向spring的提供的登录页面
        registry.addViewController("/").setViewName("redirect:/login-view"); //重定向自定义的登录页面
        registry.addViewController("/login-view").setViewName("login"); //重定向自定义的登录页面
    }
    
}
