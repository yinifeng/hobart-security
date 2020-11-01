package com.hobart.security.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * 令牌配置类
 */
@Configuration
public class TokenConfig {
    
    public static final String SIGNING_KEY = "uaa123";
    
    //JWT存储令牌
    @Bean
    public JwtTokenStore tokenStore(){
        return new JwtTokenStore(accessTokenConverter());
    }
    
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        accessTokenConverter.setSigningKey(SIGNING_KEY);//对称秘钥，资源服务器使用该秘钥来验证
        return accessTokenConverter;
    }


    //基于内存的令牌存储策越
    /*@Bean
    public TokenStore tokenStore(){
        //内存方式，生成普通令牌，相对于jwt是普通令牌
        return new InMemoryTokenStore();
    }*/
}
