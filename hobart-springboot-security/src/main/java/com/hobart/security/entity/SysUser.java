package com.hobart.security.entity;

import javax.persistence.*;
import java.io.Serializable;

import com.hobart.security.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Description
 * @Author hobart
 * @Date 2020-12-13
 */

@Setter
@Getter
@ToString
@Entity
@Table(name = "t_hobart_sys_user")
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = 1447382189153247724L;

    /**
     * 登录账户名
     */
    @Column(name = "login_name")
    private String loginName;

    /**
     * 用户名称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 登录密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 所属组织id
     */
    @ManyToOne(targetEntity = SysOrg.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "org_id")
    private SysOrg sysOrg;

    /**
     * 联系地址
     */
    @Column(name = "address")
    private String address;

    /**
     * 联系电话
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 联系邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 0-启用,1-禁用
     */
    @Column(name = "status")
    private Integer status;

}
