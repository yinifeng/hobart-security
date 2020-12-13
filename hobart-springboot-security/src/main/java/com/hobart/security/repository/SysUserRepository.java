package com.hobart.security.repository;

import com.hobart.security.entity.SysUser;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface SysUserRepository extends JpaRepositoryImplementation<SysUser,Long> {
}
