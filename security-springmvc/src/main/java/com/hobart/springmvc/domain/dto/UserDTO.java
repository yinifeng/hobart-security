package com.hobart.springmvc.domain.dto;

import java.util.Set;

public class UserDTO {
    public static final String SESSION_USER_KEY = "_user";
    
    private String id;
    
    private String username;

    private String password;
    
    private String fullname;
    
    private String mobile;
    
    private Set<String> authentication;

    public UserDTO() {
    }

    public UserDTO(String id, String username, String password, String fullname, String mobile, Set<String> authentication) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.mobile = mobile;
        this.authentication = authentication;
    }

    public UserDTO(String id, String username, String password, String fullname, String mobile) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.mobile = mobile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Set<String> getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Set<String> authentication) {
        this.authentication = authentication;
    }
}
