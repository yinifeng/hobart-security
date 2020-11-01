package com.hobart.springmvc.controller;

import com.hobart.springmvc.domain.dto.UserDTO;
import com.hobart.springmvc.domain.req.AuthenticationRequest;
import com.hobart.springmvc.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {
    @Autowired
    private AuthenticationService authenticationService;
    
    @RequestMapping(value = "/login",produces = {"text/plain;charset=utf-8"/*, MediaType.TEXT_PLAIN_VALUE*/})
    public String login(AuthenticationRequest authenticationRequest, HttpSession session){
        UserDTO userDTO = authenticationService.authentication(authenticationRequest);
        //存入session
        session.setAttribute(UserDTO.SESSION_USER_KEY,userDTO);
        return userDTO.getFullname() + "登录成功";
    }

    @RequestMapping(value = "/logout",produces = "text/plain;charset=utf-8")
    public String logout(HttpSession session){
        session.invalidate();
        return "退出成功";
    }

    @RequestMapping(value = "/r/r1",produces = "text/plain;charset=utf-8")
    public String r1(HttpSession session){
        Object object = session.getAttribute(UserDTO.SESSION_USER_KEY);
        String fullName = "";
        if (object == null){
            fullName = "匿名";
        }else{
            fullName = ((UserDTO) object).getFullname();
        }
        return fullName + "访问资源r1";
    }

    @RequestMapping(value = "/r/r2",produces = "text/plain;charset=utf-8")
    public String r2(HttpSession session){
        Object object = session.getAttribute(UserDTO.SESSION_USER_KEY);
        String fullName = "";
        if (object == null){
            fullName = "匿名";
        }else{
            fullName = ((UserDTO) object).getFullname();
        }
        return fullName + "访问资源r2";
    }
}
