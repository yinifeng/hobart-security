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
@Table(name = "t_hobart_sys_menu")
public class SysMenu extends BaseEntity {

    private static final long serialVersionUID = 4860313771526816422L;

    /**
     * 父菜单id
     */
    @Column(name = "menu_pid")
    private Long menuPid;

    /**
     * 所有父节点id,用逗号隔开
     */
    @Column(name = "menu_pids")
    private String menuPids;

    /**
     * 0-不是叶子节点,1-是叶子节点(最末尾节点)
     */
    @Column(name = "is_leaf")
    private Integer isLeaf;

    /**
     * 菜单名称
     */
    @Column(name = "menu_name")
    private String menuName;

    /**
     * 跳转url
     */
    @Column(name = "menu_url")
    private String menuUrl;

    /**
     * 菜单图标
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 菜单图标颜色
     */
    @Column(name = "icon_color")
    private String iconColor;

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

    /**
     * 0-启用,1-禁用
     */
    @Column(name = "status")
    private Integer status;

}
