package com.hobart.security.uaa.init;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SpringSecurityApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
    
    public SpringSecurityApplicationInitializer() {
        //如果Outh2配置一下
        //super(WebSecurityConfig.class);
    }
}
