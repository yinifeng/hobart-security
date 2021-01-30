package com.hobart.security.configure;

import com.hobart.security.auth.MySessionInformationExpiredStrategy;
import com.hobart.security.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import sun.net.www.content.image.gif;
import sun.net.www.content.image.png;

@Configuration
/**
 * @PreAuthorize
 * @PostAuthorize
 * @PreFilter
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class HobartWebSecurityConfigure extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin() //开启form登录认证
                .loginPage("/login.html")//登录处理页面
                .loginProcessingUrl("/login")//登录请求路劲
                .usernameParameter("username")//登录表单用户名
                .passwordParameter("password")//登录表单密码
                .defaultSuccessUrl("/")//登录成功 跳转url
        .and().authorizeRequests() //授权处理
                .antMatchers("/login.html","/login","/favicon.ico").permitAll() //排除授权 路劲表达式
                //按角色权限
                //.antMatchers("/","/biz1","biz2").hasAnyAuthority("ROLE_common","ROLE_admin") //拥有admin user角色访问 等价于hasAnyRole("admin","user")
                //.antMatchers("/syslog","/sysuser").hasAnyRole("admin") //拥有admin角色访问 等价于hasAnyAuthority("ROLE_admin")
                //按权限路劲
                //内存配置
                //.antMatchers("/syslog").hasAuthority("sys:log")
                //.antMatchers("/sysuser").hasAuthority("sys:user")
                //数据库配置
                //.antMatchers("/syslog").hasAuthority("/syslog")
                //.antMatchers("/sysuser").hasAuthority("/sysuser")
                //.anyRequest().authenticated()//其他所有请求路劲授权处理
                //支持spel表达式查找bean处理
                .anyRequest().access("@rbacService.hasPermission(request,authentication)")
        .and().sessionManagement() //session管理
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .invalidSessionUrl("/login.html") //session失效后跳转页面
                .sessionFixation().migrateSession() //每次登录后 复制session改变sessionid
                //限制一个账号不能同时登录
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false) //一个用户超过最大登录数了将被踢下线
                .expiredSessionStrategy(new MySessionInformationExpiredStrategy());//session被迫下线执行策略
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //内存配置登录用户的密码和权限
                //auth.inMemoryAuthentication()
                //.withUser("admin").password(passwordEncoder().encode("123456"))
                //按权限
                //.authorities("sys:log","sys:user")
                //开启这个角色，上面权限路劲访问不了
                //.roles("admin")
                //.and().withUser("zhangsan").password(passwordEncoder().encode("123456"))
                //.roles("common") //按角色
                //.and().passwordEncoder(passwordEncoder());
        //UserDetailsService 配置根据业务获取登录用户信息
        auth.userDetailsService(myUserDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //将项目中的静态资源开发出来，不需要权限访问
        //这样配置不要经过filter验证，上面permitAll()会经过filter校验
        web.ignoring()
                .antMatchers("/static/**","/asserts/**","/webjars/**",
                        "/resources/**", "/public/**","/css/**","/fonts/**","/img/**","/js/**"
                        //druid配置不生效
                        /*,"*.js","*.gif","*.jpg","*.png","*.css","*.ico","/druid/**"*/);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public MyUserDetailsService myUserDetailsService(){
        return new MyUserDetailsService();
    }
}
