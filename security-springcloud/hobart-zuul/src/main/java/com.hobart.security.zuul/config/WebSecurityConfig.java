package com.hobart.security.zuul.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * 网关是一个资源服务，也要配置安全访问控制
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    

    //安全拦截机制
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/**").permitAll();//所有请求放行，去每个资源服务去校验权限
    }

}
