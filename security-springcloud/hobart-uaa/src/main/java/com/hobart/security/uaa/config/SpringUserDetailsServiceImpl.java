package com.hobart.security.uaa.config;

import com.alibaba.fastjson.JSON;
import com.hobart.security.uaa.dao.UserDAO;
import com.hobart.security.uaa.model.dto.PermissionDTO;
import com.hobart.security.uaa.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpringUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDAO userDAO;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //数据库查询用户信息
        UserDTO userDTO = null;
        try {
            userDTO = userDAO.getUserByUserName(username);
            System.out.println(username+"=====================>"+ JSON.toJSONString(userDTO));
        } catch (Exception e) {
            throw new UsernameNotFoundException("用户不存在");
        }
        if (userDTO == null){
            //如果查不到，返回空，有provider来抛出异常
            throw new UsernameNotFoundException("用户不存在");
        }

        List<PermissionDTO> permissionList = userDAO.findPermissionList(userDTO.getId());
        List<String> list = permissionList.stream().map(PermissionDTO::getCode).collect(Collectors.toList());

        /**
         * 这个UserDetailsService的属性不够，我们可以通过继承来扩展
         * 
         * 也可以封装个josn给username
         */
        String userJson = JSON.toJSONString(userDTO);

        UserDetails userDetails = User.withUsername(userJson)
                .password(userDTO.getPassword()).authorities(list.toArray(new String[list.size()])).build();
        return userDetails;
    }
}
