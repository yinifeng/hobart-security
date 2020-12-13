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
 * @Author hobart
 * @Date 2020-12-13
 */

@Setter
@Getter
@ToString
@Entity
@Table(name = "t_hobart_sys_org")
public class SysOrg extends BaseEntity {

    private static final long serialVersionUID = 5858526289862603671L;

    /**
     * 父组织id
     */
    @Column(name = "org_pid")
    private Long orgPid;

    /**
     * 所有父节点id,用逗号隔开
     */
    @Column(name = "org_pids")
    private String orgPids;

    /**
     * 0-不是叶子节点,1-是叶子节点(最末尾节点)
     */
    @Column(name = "is_leaf")
    private Integer isLeaf;

    /**
     * 组织名字
     */
    @Column(name = "org_name")
    private String orgName;

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
     * 排序
     */
    @Column(name = "sort_id")
    private Integer sortId;

    /**
     * 层级
     */
    @Column(name = "level")
    private Integer level;

}
