package com.hobart.security.uaa.dao;

import com.hobart.security.uaa.model.dto.PermissionDTO;
import com.hobart.security.uaa.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public UserDTO getUserByUserName(String userName){
        String sql = "select t.id as id,t.user_name as userName,t.`password` as password,t.full_name as fullName," +
                "t.age as age,t.create_time as createTime,t.update_time as updateTime " +
                "from t_hobart_user t where t.user_name = ?";
        //UserDTO userDTO = jdbcTemplate.queryForObject(sql, new Object[]{userName}, UserDTO.class);
        //List<UserDTO> userDTOList = jdbcTemplate.query(sql, new Object[]{userName}, new BeanPropertyRowMapper<>(UserDTO.class));
        //if (userDTOList.isEmpty()){
        //    return null;
        //}
        //return userDTOList.iterator().next();
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(UserDTO.class), userName);
    }
    
    public List<PermissionDTO> findPermissionList(Long userId){
        String sql = "SELECT\n" +
                "\ta.id,\n" +
                "\ta.`code`,\n" +
                "\ta.description,\n" +
                "\ta.url,\n" +
                "\ta.create_time as createTime,\n" +
                "\ta.update_time as updateTime\n" +
                "FROM\n" +
                "\tt_hobart_permission a,\n" +
                "\tt_hobart_role_permission b,\n" +
                "\tt_hobart_user_role c,\n" +
                "\tt_hobart_user d\n" +
                "WHERE\n" +
                "\ta.id = b.permission_id\n" +
                "AND b.role_id = c.role_id\n" +
                "AND c.user_id = d.id\n" +
                "AND d.id = ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(PermissionDTO.class));
    }
}
