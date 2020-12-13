package com.hobart.security;

import com.hobart.security.entity.SysUser;
import com.hobart.security.repository.SysUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
public class RepositoryTest {
    @Autowired
    private SysUserRepository userRepository;
    
    @Test
    @Transactional
    public void testListUser(){
        List<SysUser> userList = userRepository.findAll();
        userList.forEach(System.out::println);
    }
}
