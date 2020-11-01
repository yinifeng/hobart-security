package com.hobart.springmvc.interceptor;

import com.hobart.springmvc.domain.dto.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@Component
public class SimpleAuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        UserDTO userDTO = (UserDTO)request.getSession().getAttribute(UserDTO.SESSION_USER_KEY);
        if (userDTO == null){
            writerResponse(response,"请先登录");
        }
        
        if (userDTO.getAuthentication().contains("p1") && request.getRequestURI().contains("/r/r1")){
            return true;
        }

        if (userDTO.getAuthentication().contains("p2") && request.getRequestURI().contains("/r/r2")){
            return true;
        }
        writerResponse(response,"权限不足，拒绝访问");
        return false;
    }

    private void writerResponse(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("text/plain;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(msg);
        writer.close();
        response.setContentLength(msg.getBytes(StandardCharsets.UTF_8).length);
        //response.resetBuffer();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
