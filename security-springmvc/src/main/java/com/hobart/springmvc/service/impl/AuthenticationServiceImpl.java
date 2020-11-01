package com.hobart.springmvc.service.impl;

import com.hobart.springmvc.domain.dto.UserDTO;
import com.hobart.springmvc.domain.req.AuthenticationRequest;
import com.hobart.springmvc.service.AuthenticationService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    
    private static final Map<String,UserDTO> USER_MAP = new HashMap<>();
    
    static {
        Set<String> authentication1 = new HashSet<>();
        authentication1.add("p1");//p1角色对应资源权限 /r/r1
        Set<String> authentication2 = new HashSet<>();
        authentication2.add("p2");//p1角色对应资源权限 /r/r2
        USER_MAP.put("zhangsan",new UserDTO("1010","zhangsan","123","张三","111111",authentication1));
        USER_MAP.put("lisi",new UserDTO("1011","lisi","456","李四","222222",authentication2));
    }
    
    
    @Override
    public UserDTO authentication(AuthenticationRequest authenticationRequest) {
        if (authenticationRequest == null || StringUtils.isEmpty(authenticationRequest.getUsername())
                || StringUtils.isEmpty(authenticationRequest.getPassword())){
            throw new RuntimeException("账号或密码为空");
        }

        //根据账号查询数据库
        UserDTO userDTO = getUserDTO(authenticationRequest.getUsername());
        if (userDTO == null){
            throw new RuntimeException("用户不存在");
        }
        if (!authenticationRequest.getPassword().equals(userDTO.getPassword())){
            throw new RuntimeException("密码错误");
        }
        return userDTO;
    }

    private UserDTO getUserDTO(String userName) {
        return USER_MAP.get(userName);
    }
}
