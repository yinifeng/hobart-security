package com.hobart.security.config;

import com.hobart.security.service.impl.SpringUserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    
    //定义用户信息服务 （查询用户信息）
    
    //数据库查询
/*    @Bean
    public UserDetailsService userDetailsService(){
        //基于内存方式，数据库
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("zhangsan").password("$2a$10$mbO7Uro/vT3qi.7OiJr/CuTBNso3PyB8h.reu7HtDHFGXEW44bQUW").authorities("p1").build());
        manager.createUser(User.withUsername("lisi").password("$2a$10$vLXf5NB0.1lyblr0WcaWRuIIJYowr/NREsA2.jzc6SKNNfPMa5SDC").authorities("p2").build());
        return manager;
    }*/
    

    //数据库查询
    @Bean
    public UserDetailsService userDetailsService(){
        return new SpringUserDetailsServiceImpl();
    } 
    
    //密码编码器
    @Bean
    public PasswordEncoder passwordEncoder(){
        //return NoOpPasswordEncoder.getInstance();//明文字符串比较
        return new BCryptPasswordEncoder();//加密算法
    }
    
    //安全拦截机制
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/r/r1").hasAuthority("p1")
                .antMatchers("/r/r2").hasAuthority("p2")
                .antMatchers("/r/**").authenticated()//所有/r/**的请求必须认证通过
                .anyRequest().permitAll()//除了/r/**其他请求可以访问
                //.and().formLogin().successForwardUrl("/login-success")//允许表单登录 //自定义登录成功的页面地址 这个是spring提供的登录页面
                .and().formLogin().loginPage("/login-view").loginProcessingUrl("/login").successForwardUrl("/login-success")//自定义跳转登录页面
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login-view?logout")//退出定义
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);//token 就不需要创建session
    }

}
