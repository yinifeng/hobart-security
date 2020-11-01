package com.hobart.security.uaa.dao;

import com.hobart.security.dao.UserDAO;
import com.hobart.security.domain.dto.UserDTO;
import com.hobart.security.uaa.AbstractApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDAOTest extends AbstractApplicationTest {
    
    @Autowired
    private UserDAO userDAO;
    
    @Test
    public void testGetUserByUserName(){
        UserDTO user = userDAO.getUserByUserName("zhangsan");

        System.out.println(user);
    }
}
