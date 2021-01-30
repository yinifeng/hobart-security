package com.hobart.security.repository;

import com.hobart.security.entity.SysRole;
import com.hobart.security.entity.SysRoleMenu;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.Collection;
import java.util.List;

public interface SysRoleMenuRepository extends JpaRepositoryImplementation<SysRoleMenu,Long> {
    
    List<SysRoleMenu> queryBysysRoleIn(Collection<SysRole> sysRoles);
}
