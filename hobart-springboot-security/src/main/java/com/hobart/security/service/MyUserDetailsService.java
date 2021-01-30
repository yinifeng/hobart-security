package com.hobart.security.service;

import com.hobart.security.domain.MyUserDetails;
import com.hobart.security.entity.*;
import com.hobart.security.repository.SysRoleMenuRepository;
import com.hobart.security.repository.SysUserRepository;
import com.hobart.security.repository.SysUserRoleRepository;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetailsService implements UserDetailsService {
    @Resource
    private SysUserRepository userRepository;
    @Resource
    private SysUserRoleRepository userRoleRepository;
    @Resource
    private SysRoleMenuRepository roleMenuRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据登录名查询用户
        SysUser user = userRepository.findByLoginName(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        MyUserDetails userDetails = new MyUserDetails();
        userDetails.setUsername(user.getLoginName());
        userDetails.setPassword(user.getPassword());
        //启用
        userDetails.setEnabled(user.getStatus() == 0);
        
        List<String> authoritiesa = new ArrayList<>();
        //根据用户查询角色
        List<SysUserRole> sysUserRoles = userRoleRepository.queryBySysUser(user);
        List<SysRole> sysRoles = sysUserRoles.stream().map(SysUserRole::getSysRole).distinct().collect(Collectors.toList());
        
        //ROLE_admin  角色也是一种特殊的权限资源
        sysRoles.stream().map(role -> "ROLE_" + role.getRoleCode()).forEach(authoritiesa::add);
        //根据角色查询权限资源
        List<SysRoleMenu> sysRoleMenus = roleMenuRepository.queryBysysRoleIn(sysRoles);
        sysRoleMenus.stream().map(menu->menu.getSysMenu().getMenuUrl()).distinct().forEach(authoritiesa::add);
        //userDetails.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(",", authoritiesa)));        
        userDetails.setAuthorities(AuthorityUtils.createAuthorityList(authoritiesa.toArray(new String[0])));
        return userDetails;
    }
}
