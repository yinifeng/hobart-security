package com.hobart.security.order.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * 这是一个ResourceServer的配置
 * 
 * 测试访问，必须在Headers上携带token
 * http://localhost:8814/order/r1
 * 
 * Headers上的键值对
 * 
 * Authorization=Bearer access_token(cd7d9a7b-f555-448a-93a3-20a751eea388)
 * 
 * 
 * 
 */
@Configuration
@EnableResourceServer//开启Resource服务
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    
    public static final String RESOURCE_ID = "res1";
    
    @Autowired
    private JwtTokenStore tokenStore;

    /**
     * 每次调用都会远程去校验令牌
     * 
     * 使用JWT令牌改造
     * 
     * @return
     */
/*    @Bean
    public RemoteTokenServices tokenService(){
        RemoteTokenServices service = new RemoteTokenServices();
        service.setCheckTokenEndpointUrl("http://localhost:8421/uaa/oauth/check_token");
        service.setClientId("c1");
        service.setClientSecret("secret");
        return service;
    }*/

    /**
     * 如果资源服务和授权服务在同一个服务 DefaultTokenServices 就行token校验
     * 
     * 如果资源服务和授权服务不在同一个服务 RemoteTokenServices 进行token校验
     * 
     * @param resources
     * @throws Exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID)//资源id，在授权服务已经分配了这个客户端能访问那些资源
                //.tokenServices(tokenService())//远程验证令牌的服务
                .tokenStore(tokenStore)//JWT验证token 这个权限信息已经在token中
                .stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/**").access("#oauth2.hasScope('ROLE_ADMIN')")//授权服务配置的scop就可以在这个地方验证权限
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//基于token的方式session不需要配置了
    }
    
}
