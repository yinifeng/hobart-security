package com.hobart.security.service.impl;

import com.hobart.security.dao.UserDAO;
import com.hobart.security.domain.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class SpringUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDAO userDAO;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //数据库查询用户信息
        UserDTO userDTO = null;
        try {
            userDTO = userDAO.getUserByUserName(username);
        } catch (Exception e) {
            throw new UsernameNotFoundException("用户不存在");
        }
        if (userDTO == null){
            //如果查不到，返回空，有provider来抛出异常
            throw new UsernameNotFoundException("用户不存在");
        }
        UserDetails userDetails = User.withUsername(userDTO.getUserName()).password(userDTO.getPassword()).authorities("p1").build();
        return userDetails;
    }
}
