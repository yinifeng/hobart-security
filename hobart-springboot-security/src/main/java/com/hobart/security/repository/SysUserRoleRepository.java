package com.hobart.security.repository;

import com.hobart.security.entity.SysUser;
import com.hobart.security.entity.SysUserRole;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.List;

public interface SysUserRoleRepository extends JpaRepositoryImplementation<SysUserRole,Long> {
    
    List<SysUserRole> queryBySysUser(SysUser sysUser);
}
