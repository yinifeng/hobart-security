/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

# Create Database
# ------------------------------------------------------------
CREATE DATABASE IF NOT EXISTS hobart_security DEFAULT CHARACTER SET = utf8mb4;

Use hobart_security;

# Dump of table t_hobart_sys_org
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_hobart_sys_org`;

CREATE TABLE `t_hobart_sys_org` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `org_pid` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '父组织id',
  `org_pids` varchar(500) NOT NULL DEFAULT '' COMMENT '所有父节点id,用逗号隔开',
  `is_leaf` tinyint(4) NOT NULL DEFAULT '1' COMMENT '0-不是叶子节点,1-是叶子节点(最末尾节点)',
  `org_name` varchar(64) NOT NULL DEFAULT '' COMMENT '组织名字',
  `address` varchar(64) DEFAULT '' COMMENT '联系地址',
  `phone` varchar(20) DEFAULT '' COMMENT '联系电话',
  `email` varchar(64) DEFAULT '' COMMENT '联系邮箱',
  `sort_id` tinyint(4) DEFAULT '1' COMMENT '排序',
  `level` tinyint(4) NOT NULL DEFAULT '1' COMMENT '层级',
  `is_deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '1: 逻辑删除, 0: 正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_org_pid` (`org_pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='组织树表';

# Dump of table t_hobart_sys_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_hobart_sys_user`;

CREATE TABLE `t_hobart_sys_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `login_name` varchar(64) NOT NULL DEFAULT '' COMMENT '登录账户名',
  `user_name` varchar(64) NOT NULL DEFAULT '' COMMENT '用户名称',
  `password` varchar(64) NOT NULL DEFAULT '' COMMENT '登录密码',
  `org_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '所属组织id',
  `address` varchar(64) DEFAULT '' COMMENT '联系地址',
  `phone` varchar(20) DEFAULT '' COMMENT '联系电话',
  `email` varchar(64) DEFAULT '' COMMENT '联系邮箱',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0-启用,1-禁用',
  `is_deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '1: 逻辑删除, 0: 正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_login_name` (`login_name`),
  KEY `idx_org_id` (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

# Dump of table t_hobart_sys_menu
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_hobart_sys_menu`;

CREATE TABLE `t_hobart_sys_menu` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_pid` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '父菜单id',
  `menu_pids` varchar(500) NOT NULL DEFAULT '' COMMENT '所有父节点id,用逗号隔开',
  `is_leaf` tinyint(4) NOT NULL DEFAULT '1' COMMENT '0-不是叶子节点,1-是叶子节点(最末尾节点)',
  `menu_name` varchar(64) NOT NULL DEFAULT '' COMMENT '菜单名称',
  `menu_url` varchar(64) DEFAULT '' COMMENT '跳转url',
  `icon` varchar(45) DEFAULT '' COMMENT '菜单图标',
  `icon_color` varchar(16) DEFAULT '' COMMENT '菜单图标颜色',
  `sort_id` tinyint(4) DEFAULT '1' COMMENT '排序',
  `level` tinyint(4) NOT NULL DEFAULT '1' COMMENT '层级',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0-启用,1-禁用',
  `is_deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '1: 逻辑删除, 0: 正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_menu_pid` (`menu_pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单树表';


# Dump of table t_hobart_sys_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_hobart_sys_role`;

CREATE TABLE `t_hobart_sys_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(32) NOT NULL DEFAULT '' COMMENT '角色名称',
  `role_desc` varchar(128) DEFAULT '' COMMENT '角色描述',
  `role_code` varchar(24) NOT NULL DEFAULT '' COMMENT '角色英文代码，admin',
  `sort_id` tinyint(4) DEFAULT '1' COMMENT '排序',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0-启用,1-禁用',
  `is_deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '1: 逻辑删除, 0: 正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_role_code` (`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';


# Dump of table t_hobart_sys_role_menu
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_hobart_sys_role_menu`;

CREATE TABLE `t_hobart_sys_role_menu` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '角色id',
  `menu_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '菜单id',
  `is_deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '1: 逻辑删除, 0: 正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_menu_id` (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色菜单关系表';


# Dump of table t_hobart_sys_user_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_hobart_sys_user_role`;

CREATE TABLE `t_hobart_sys_user_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
  `role_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '角色id',
  `is_deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '1: 逻辑删除, 0: 正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关系表';

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;