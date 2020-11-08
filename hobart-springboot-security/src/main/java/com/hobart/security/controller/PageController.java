package com.hobart.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController {

    //登录拦截器处理 不会映射到这里
    @PostMapping("/login")
    public String login(String username,String password){
        //这样的返回 thymeleaf 使徒解析器，会在后面拼接.html
        //在templates 目录找对应的html文件
        System.err.println(username+"<----->"+password);
        return "index";
    }
    
    //日志管理
    @GetMapping("/syslog")
    public String syslog(){
        //这样的返回 thymeleaf 使徒解析器，会在后面拼接.html
        //在templates 目录找对应的html文件
        return "syslog";
    }

    //日志管理
    @GetMapping("/sysuser")
    public String sysuser(){
        //这样的返回 thymeleaf 使徒解析器，会在后面拼接.html
        //在templates 目录找对应的html文件
        return "sysuser";
    }

    //日志管理
    @GetMapping("/biz1")
    public String biz1(){
        //这样的返回 thymeleaf 使徒解析器，会在后面拼接.html
        //在templates 目录找对应的html文件
        return "biz1";
    }

    //日志管理
    @GetMapping("/biz2")
    public String biz2(){
        //这样的返回 thymeleaf 使徒解析器，会在后面拼接.html
        //在templates 目录找对应的html文件
        return "biz2";
    }
}
