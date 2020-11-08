# Sample Data
# ------------------------------------------------------------
INSERT INTO `t_hobart_sys_org` (`org_pid`, `org_pids`, `is_leaf`, `org_name`, `sort_id`, `level`)
VALUES
('0', '', '0', '总部', '1', '1');
INSERT INTO `t_hobart_sys_org` (`org_pid`, `org_pids`, `is_leaf`, `org_name`, `sort_id`, `level`)
VALUES
('1', '1', '0', '研发部', '1', '2');
INSERT INTO `t_hobart_sys_org` (`org_pid`, `org_pids`, `is_leaf`, `org_name`, `sort_id`, `level`)
VALUES
('2', '1,2', '1', '研发一部', '1', '3');
INSERT INTO `t_hobart_sys_org` (`org_pid`, `org_pids`, `is_leaf`, `org_name`, `sort_id`, `level`)
VALUES
('2', '1,2', '1', '研发二部', '2', '3');

INSERT INTO `t_hobart_sys_user` (`login_name`, `user_name`, `password`, `org_id`, `address`, `phone`, `email`)
VALUES
('admin', '系统管理员', '123456', '1', '', '','');
INSERT INTO `t_hobart_sys_user` (`login_name`, `user_name`, `password`, `org_id`, `address`, `phone`, `email`)
VALUES
('zhangsan', '张三', '123456', '4', '深圳宝安', '15000011166','123@qq.com');



INSERT INTO `t_hobart_sys_menu` (`menu_pid`, `menu_pids`, `is_leaf`, `menu_name`, `menu_url`, `sort_id`, `level`)
VALUES
('0', '', '0', '系统管理', '', '1','1');
INSERT INTO `t_hobart_sys_menu` (`menu_pid`, `menu_pids`, `is_leaf`, `menu_name`, `menu_url`, `sort_id`, `level`)
VALUES
('1', '1', '1', '用户管理', '/sys_user', '1','2');
INSERT INTO `t_hobart_sys_menu` (`menu_pid`, `menu_pids`, `is_leaf`, `menu_name`, `menu_url`, `sort_id`, `level`)
VALUES
('1', '1', '1', '日志管理', '/sys_log', '2','2');
INSERT INTO `t_hobart_sys_menu` (`menu_pid`, `menu_pids`, `is_leaf`, `menu_name`, `menu_url`, `sort_id`, `level`)
VALUES
('1', '1', '1', '业务一', '/biz1', '3','2');
INSERT INTO `t_hobart_sys_menu` (`menu_pid`, `menu_pids`, `is_leaf`, `menu_name`, `menu_url`, `sort_id`, `level`)
VALUES
('1', '1', '1', '业务二', '/biz2', '4','2');


INSERT INTO `t_hobart_sys_role` (`role_name`, `role_desc`, `role_code`, `sort_id`)
VALUES
('管理员', '管理员', 'admin', '1');
INSERT INTO `t_hobart_sys_role` (`role_name`, `role_desc`, `role_code`, `sort_id`)
VALUES
('普通用户', '普通用户', 'common', '2');


INSERT INTO `t_hobart_sys_role_menu` (`role_id`, `menu_id`)
VALUES
('1', '2');
INSERT INTO `t_hobart_sys_role_menu` (`role_id`, `menu_id`)
VALUES
('1', '3');
INSERT INTO `t_hobart_sys_role_menu` (`role_id`, `menu_id`)
VALUES
('2', '4');
INSERT INTO `t_hobart_sys_role_menu` (`role_id`, `menu_id`)
VALUES
('2', '5');

INSERT INTO `t_hobart_sys_user_role` (`user_id`, `role_id`)
VALUES
('1', '1');
INSERT INTO `t_hobart_sys_user_role` (`user_id`, `role_id`)
VALUES
('2', '2');