/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : security

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2019-11-25 00:37:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `client_id` varchar(100) NOT NULL,
  `client_secret` varchar(64) NOT NULL,
  `resource_ids` varchar(255) NOT NULL,
  `scope` varchar(50) NOT NULL,
  `authorized_grant_types` varchar(100) NOT NULL,
  `web_server_redirect_uri` varchar(255) NOT NULL,
  `authorities` varchar(255) DEFAULT NULL,
  `access_token_validity` int(11) NOT NULL,
  `refresh_token_validity` int(11) NOT NULL,
  `additional_information` longtext,
  `archived` tinyint(4) NOT NULL,
  `trusted` tinyint(4) NOT NULL,
  `autoapprove` varchar(50) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('1', 'c1', '$2a$10$ihEqysI4uQwOkt9omJD76O/EViGNQlmOX271UoTcyLndKW2A6mpT6', 'res1', 'ROLE_ADMIN,ROLE_USER,ROLE_API', 'authorization_code,password,client_credentials,implicit,refresh_token', 'http://www.baidu.com', '', '7200', '259200', null, '0', '0', 'false', '2019-11-24 17:47:12');
INSERT INTO `oauth_client_details` VALUES ('2', 'c2', 'ss', 'res2', 'ROLE_API', 'authorization_code,password,client_credentials,implicit,refresh_token', 'http://www.baidu.com', null, '31536000', '2592000', null, '0', '0', 'false', '2019-11-24 17:49:48');

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(100) NOT NULL,
  `authentication` blob,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of oauth_code
-- ----------------------------

-- ----------------------------
-- Table structure for t_hobart_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_hobart_permission`;
CREATE TABLE `t_hobart_permission` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `code` varchar(30) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `url` varchar(100) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_hobart_permission
-- ----------------------------
INSERT INTO `t_hobart_permission` VALUES ('1', 'p1', '测试资源1', '/r/r1', '2019-11-24 16:55:08', '2019-11-24 16:55:11');
INSERT INTO `t_hobart_permission` VALUES ('2', 'p2', '测试资源2', '/r/r2', '2019-11-24 16:55:31', '2019-11-24 16:55:33');

-- ----------------------------
-- Table structure for t_hobart_role
-- ----------------------------
DROP TABLE IF EXISTS `t_hobart_role`;
CREATE TABLE `t_hobart_role` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `status` tinyint(4) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_hobart_role
-- ----------------------------
INSERT INTO `t_hobart_role` VALUES ('1', '管理员', null, '1', '2019-11-24 16:47:28', '2019-11-24 16:47:31');

-- ----------------------------
-- Table structure for t_hobart_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_hobart_role_permission`;
CREATE TABLE `t_hobart_role_permission` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(10) NOT NULL,
  `permission_id` bigint(10) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_hobart_role_permission
-- ----------------------------
INSERT INTO `t_hobart_role_permission` VALUES ('1', '1', '1', '2019-11-24 16:56:02', '2019-11-24 16:56:05');
INSERT INTO `t_hobart_role_permission` VALUES ('2', '1', '2', '2019-11-24 16:56:17', '2019-11-24 16:56:20');

-- ----------------------------
-- Table structure for t_hobart_user
-- ----------------------------
DROP TABLE IF EXISTS `t_hobart_user`;
CREATE TABLE `t_hobart_user` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `password` varchar(64) NOT NULL,
  `full_name` varchar(50) NOT NULL,
  `age` int(3) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_hobart_user
-- ----------------------------
INSERT INTO `t_hobart_user` VALUES ('1', 'zhangsan', '$2a$10$zSxoge/BvC/aOGe50fW2IOdJHDoRn8QYVzhF99q6xHdGMsLI5WTB6', '张三', '21', '2019-11-22 20:54:27', '2019-11-22 20:54:30');
INSERT INTO `t_hobart_user` VALUES ('2', 'lisi', '$2a$10$YOAFO/HGRP9oLwwb1iKPsODL1cS12WmJOBItsKFongXI2f.3P1Gdu', '李四', '22', '2019-11-22 20:54:45', '2019-11-22 20:54:48');

-- ----------------------------
-- Table structure for t_hobart_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_hobart_user_role`;
CREATE TABLE `t_hobart_user_role` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(10) NOT NULL,
  `role_id` bigint(10) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_hobart_user_role
-- ----------------------------
INSERT INTO `t_hobart_user_role` VALUES ('1', '1', '1', '2019-11-24 16:56:40', '2019-11-24 16:56:43');
