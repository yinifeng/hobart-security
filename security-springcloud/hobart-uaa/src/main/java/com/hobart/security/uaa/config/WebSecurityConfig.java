package com.hobart.security.uaa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
//开启注解资源权限验证
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    
    //认证管理器
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
    
    
    //密码编码器
    @Bean
    public PasswordEncoder passwordEncoder(){
        //return NoOpPasswordEncoder.getInstance();//明文字符串比较
        return new BCryptPasswordEncoder();//加密算法
    }
    
    //安全拦截机制
/*    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                //.antMatchers("/r/r1").hasAuthority("p1")
                //.antMatchers("/r/r2").hasAuthority("p2")
                .antMatchers("/r/**").authenticated()//所有/r/**的请求必须认证通过
                .anyRequest().permitAll()//除了/r/**其他请求可以访问
                //.and().formLogin().successForwardUrl("/login-success")//允许表单登录 //自定义登录成功的页面地址 这个是spring提供的登录页面
                .and().formLogin().loginPage("/login-view").loginProcessingUrl("/login").successForwardUrl("/login-success")//自定义跳转登录页面
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login-view?logout")//退出定义
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);//token 就不需要创建session
    }*/

    //安全拦截机制
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/r/r1").hasAnyAuthority("p1")
                .antMatchers("/login*").permitAll()
                .anyRequest().authenticated()
                .and().formLogin();
    }

}
