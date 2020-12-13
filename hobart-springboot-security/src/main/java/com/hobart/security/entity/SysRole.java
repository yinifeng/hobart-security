package com.hobart.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
@Table ( name ="t_hobart_sys_role" )
public class SysRole extends BaseEntity {

	private static final long serialVersionUID =  8762885963969736084L;

	/**
	 * 角色名称
	 */
   	@Column(name = "role_name" )
	private String roleName;

	/**
	 * 角色描述
	 */
   	@Column(name = "role_desc" )
	private String roleDesc;

	/**
	 * 角色英文代码，admin
	 */
   	@Column(name = "role_code" )
	private String roleCode;

	/**
	 * 排序
	 */
   	@Column(name = "sort_id" )
	private Integer sortId;

	/**
	 * 0-启用,1-禁用
	 */
   	@Column(name = "status" )
	private Integer status;

}
