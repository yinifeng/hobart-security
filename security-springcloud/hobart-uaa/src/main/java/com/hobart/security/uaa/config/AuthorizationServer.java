package com.hobart.security.uaa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * 授权服务配置
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {
    
    
    //从TokenConfig中注入令牌策越
    @Autowired
    private TokenStore tokenStore;
    
    //客户端信息从这个方法中configure(ClientDetailsServiceConfigurer clients)配置
    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;

    /**
     * jwt令牌加密转换
     */
    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;


    /**
     * 明文密码加密器
     */
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    /**
     * 基于数据库的ClientDetailsSerivce
     * 
     * 数据库表 oauth_client_details
     */
    @Bean
    public ClientDetailsService clientDetailsService(DataSource dataSource){
        JdbcClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        clientDetailsService.setPasswordEncoder(passwordEncoder);
        return clientDetailsService;
    }




    /**
     * 用来配置客户端详情服务ClientDetailsService
     * 客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里，也可以通过数据来存储查询详情信息
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
        
        //==================内存方式配置ClientDetailsService
        //暂时用内存的方式配置客户端
        /*
        clients.inMemory()//使用内存存储
                .withClient("c1")//client_id 客户端id
                .secret(new BCryptPasswordEncoder().encode("secret"))//客户端秘钥
                .resourceIds("res1")//客户端可以访问的资源列表
                .authorizedGrantTypes("authorization_code","password",
                        "client_credentials","implicit","refresh_token")//该client允许的授权类型,refresh_token刷新令牌
                .scopes("all")//允许的授权范围 授权标识
                .autoApprove(false)//false 跳转到授权页面 true不跳转授权页面直接发令牌
                //验证回调地址
                .redirectUris("http://www.baidu.com");
         */
     
    }

    /**
     * 用来配置令牌（token）的访问端点和令牌服务（token services）
     * AuthorizationServerEndpointsConfigurer通过设定以下属性决定支持的授权类型
     * 1、authenticationManager：认证管理器，当你选择了资源所有者密码授权类型的时候，
     *    请设置这个属性注入一个AuthenticationManager对象
     * 
     * 2、userDetailsService：如果设置这个属性的话，说明你自己有一个UserDetailsService接口的实现，
     *    或者可以把这个设置全局域上面去，当你设置这个之后，那么“refresh_token”即刷新令牌授权类型模式的流程中
     *    就会包含一个检查，用来确保这个账户是否有效，假如说你禁用了这个账户的话
     *    
     * 3、authorizationCodeServices：这个属性是用来设置授权码服务的（即AuthorizationCodeServices实例对象），
     *    主要用于“authorization_code”授权码类型模式
     *    
     * 4、implicitGrantService：这个属性用于设置隐式授权模式，用来管理隐式授权的状态
     * 
     * 5、tokenGranter：当你设置了这个东西（即TokenGranter的接口实现），授权将由你完全掌控，并且忽略掉上面几个属性，
     *    这个属性一般是用作拓展用途的，即标准的4种授权模式已经满足不了你的需求的时候，才会考虑使用这个
     * 
     * 配置授权端点的URL(Endpoint URLS)
     *   AuthorizationServerEndpointsConfigurer这个配置对象有一个叫做pathMapping()的方法用来配置端点URL链接，它有两个参数：
     *   第一个参数：String类型的，这个端点URL的默认连接
     *   第二个参数：String类型的，你要进行替换的URL链接
     *  以上的参数都以"/"字符为开始的字符串，框架的默认URL链接如下列表，可以作为这个pathMapping()方法的第一个参数
     *  /oauth/authorize:授权端点
     *  /oauth/token:令牌端点
     *  /oauth/confirm_access:用户确认授权提交端点
     *  /oauth/error:授权服务错误信息端点
     *  /oauth/check_token:用于资源服务访问的令牌解析端点
     *  /oauth/token_key:提供公有秘钥的端点，如果你使用的是JWT令牌的话
     * 需要注意的是授权端点这个URL应该被Spring Security保护起来只供授权用户访问
     * 
     * 
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //管理令牌
        //令牌访问端点
        endpoints.authenticationManager(authenticationManager)//密码模式
                .authorizationCodeServices(authorizationCodeServices)//授权码模式
                .tokenServices(tokenServices())
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);//允许POST提交访问令牌
        
        
    }

    /**
     * 用来配置令牌端点的安全约束
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //令牌端点安全约束
        security
                .tokenKeyAccess("permitAll()")//完全公开，当使用jwtToken且使用的是非对称加密 /oauth/token_key
                .checkTokenAccess("permitAll()")//完全公开 /oauth/check_token
                .allowFormAuthenticationForClients();//运行表单验证 来获取令牌
    }

    /**
     * 令牌服务配置
     * @return
     */
    @Bean
    public AuthorizationServerTokenServices tokenServices(){
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setClientDetailsService(clientDetailsService);//客户端信息服务
        tokenServices.setSupportRefreshToken(true);//是否刷新令牌
        tokenServices.setTokenStore(tokenStore);//令牌存储策略
        
        //=========jwt start=====//
        //加密令牌增强
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
        tokenServices.setTokenEnhancer(tokenEnhancerChain);
        //=========jwt end=====//
        
        tokenServices.setAccessTokenValiditySeconds(7200);//令牌默认有效期2小时
        tokenServices.setRefreshTokenValiditySeconds(259200);//刷新令牌默认有效期3天
        return tokenServices;
    }
    
    //设置授权码模式的授权码如何存储，暂时采用内存
   /* @Bean
    public AuthorizationCodeServices authorizationCodeServices(){
        return new InMemoryAuthorizationCodeServices();
    }*/
    
   
    //基于数据库存储授权码
    //表 oauth_code
    @Bean
    public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource){
        return new JdbcAuthorizationCodeServices(dataSource);
    }
    
}
