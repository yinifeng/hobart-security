package com.hobart.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;


@RestController
public class LoginController {
    
    @RequestMapping(value = "/login-success",produces = {"text/plain;charset=utf-8"/*, MediaType.TEXT_PLAIN_VALUE*/})
    //@PostMapping(value = "/login-success")
    public String loginSuccess(){
        System.out.println(getUserName()+"登录成功处理.....");
        return getUserName()+"登录成功";
    }

    @RequestMapping(value = "/r/r1",produces = "text/plain;charset=utf-8")
    public String r1(HttpSession session){
        getSessionInfo(session);
        return getUserName()+"访问资源r1";
    }

    @RequestMapping(value = "/r/r2",produces = "text/plain;charset=utf-8")
    public String r2(HttpSession session){
        getSessionInfo(session);
        return getUserName()+"访问资源r2";
    }

    private void getSessionInfo(HttpSession session) {
        Enumeration<String> attributeNames = session.getAttributeNames();
        while(attributeNames.hasMoreElements()){
            String element = attributeNames.nextElement();
            Object attribute = session.getAttribute(element);
            System.out.println(element+"<====>"+attribute);
            SecurityContextImpl context = (SecurityContextImpl) attribute;
            
        }
    }
    
    private String getUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal == null){
            return "匿名";
        }
        if (principal instanceof  UserDetails){
            UserDetails userDetails = (UserDetails) principal;
            return userDetails.getUsername();
        }else{
            return principal.toString();
        }
    }
}
