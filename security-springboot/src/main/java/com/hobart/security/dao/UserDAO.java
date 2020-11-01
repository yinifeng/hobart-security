package com.hobart.security.dao;

import com.hobart.security.domain.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
}
