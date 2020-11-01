package com.hobart.security.zuul.filter;

import com.alibaba.fastjson.JSON;
import com.hobart.security.zuul.util.EncryptUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AuthFilter extends ZuulFilter {
    @Override
    public String filterType() {
        //前置处理拦截
        return "pre";
    }

    @Override
    public int filterOrder() {
        //数字越小优先级越高
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //是否启用拦截
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        //解析token
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof OAuth2Authentication)){
            //如果不是OAuth2Authentication就放行 null也不是AnonymousAuthenticationToken，就是没验证
            System.out.println("authentication=========>"+authentication);
            return null;
        }
        OAuth2Authentication auth2Authentication=(OAuth2Authentication)authentication;
        Authentication userAuthentication = auth2Authentication.getUserAuthentication();
        //获取当前用户的身份信息
        String principal = userAuthentication.getName();

        //获取用户的权限信息
        List<String> authorities = userAuthentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        OAuth2Request oAuth2Request = auth2Authentication.getOAuth2Request();
        Map<String,Object> jsonToken = new HashMap<>(oAuth2Request.getRequestParameters());
        jsonToken.put("principal",principal);
        jsonToken.put("authorities",authorities);

        //把身份信息和权限信息放到json中，加入到http header中
        ctx.addZuulRequestHeader("json-token", 
                EncryptUtils.encodeUTF8StringBase64(JSON.toJSONString(jsonToken)));
        //转发给微服务
        
        return null;
    }
}
