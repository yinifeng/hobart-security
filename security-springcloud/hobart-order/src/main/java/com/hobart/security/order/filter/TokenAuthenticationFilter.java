package com.hobart.security.order.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hobart.security.order.model.dto.UserDTO;
import com.hobart.security.order.util.EncryptUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        Map<String,Object> headerMap = new HashMap<>();
        while (headerNames.hasMoreElements()){
            String key = headerNames.nextElement();
            headerMap.put(key,httpServletRequest.getHeader(key));
        }
        System.out.println("请求头信息======================>"+JSON.toJSONString(headerMap));

        //解析出头中的token
        String jsonToken = httpServletRequest.getHeader("json-token");
        if (jsonToken != null){
            //解析token
            String token = EncryptUtils.decodeUTF8StringBase64(jsonToken);
            System.out.println("网关请求TOKEN======================>"+token);
            JSONObject tokenObject = JSON.parseObject(token);
            
            //获取用户身份信息
            String principal = tokenObject.getString("principal");
            //UserDTO userDTO = new UserDTO();
            //userDTO.setUserName(principal);
            //这个principal在UserDetailsService设置的是对象json信息
            UserDTO userDTO = JSON.parseObject(principal, UserDTO.class);
            
            //获取用户权限信息
            JSONArray jsonArray = tokenObject.getJSONArray("authorities");
            String[] authorities = jsonArray.toArray(new String[jsonArray.size()]);
            //将用户身份信息和权限信息填充到token对象中
            UsernamePasswordAuthenticationToken authenticationToken 
                    = new UsernamePasswordAuthenticationToken(userDTO,null, AuthorityUtils.createAuthorityList(authorities));
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
            //将authenticationToken放到安全上下文中
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
