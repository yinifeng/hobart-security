package com.hobart.security.entity;

import javax.persistence.*;

import com.hobart.security.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description  
 * @Author  hobart
 * @Date 2020-12-13 
 */

@Setter
@Getter
@ToString
@Entity
@Table ( name ="t_hobart_sys_user_role" )
public class SysUserRole extends BaseEntity {

	private static final long serialVersionUID =  3118983172342125419L;

	/**
	 * 用户id
	 */
	@ManyToOne(targetEntity = SysUser.class,fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private SysUser sysUser;

	/**
	 * 角色id
	 */
	@ManyToOne(targetEntity = SysRole.class,fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private SysRole sysRole;

}
