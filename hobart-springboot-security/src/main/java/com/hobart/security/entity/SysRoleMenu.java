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
@Table ( name ="t_hobart_sys_role_menu" )
public class SysRoleMenu extends BaseEntity {

	private static final long serialVersionUID =  4880820432938835538L;

	/**
	 * 角色id
	 */
	@ManyToOne(targetEntity = SysRole.class,fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private SysRole sysRole;

	/**
	 * 菜单id
	 */
	@ManyToOne(targetEntity = SysMenu.class,fetch = FetchType.EAGER)
	@JoinColumn(name = "menu_id")
	private SysMenu sysMenu;

}
