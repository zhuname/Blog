
INSERT INTO t_menu values('t_sys_sysparam_list','系统常用变量表管理', 'system_manager', null,'/syssysparam/list','1','是',null,null);
INSERT INTO t_menu values('t_sys_sysparam_update','修改系统常用变量表', 't_sys_sysparam_list', null,'/syssysparam/update','0','是',null,null);
INSERT INTO t_menu values('t_sys_sysparam_look','查看系统常用变量表', 't_sys_sysparam_list', null,'/syssysparam/look','0','是',null,null);
INSERT INTO t_menu values('t_sys_sysparam_export','导出系统常用变量表', 't_sys_sysparam_list', null,'/syssysparam/list/export','0','是',null,null);
INSERT INTO t_menu values('t_sys_sysparam_deletemore','批量删除系统常用变量表', 't_sys_sysparam_list', null,'/syssysparam/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_sys_sysparam_delete','删除系统常用变量表', 't_sys_sysparam_list', null,'/syssysparam/delete','0','是',null,null);
INSERT INTO t_menu values('t_sys_sysparam_upload','导入系统常用变量表', 't_sys_sysparam_list', null,'/syssysparam/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_sys_sysparam_list_admin', 'admin', 't_sys_sysparam_list');
INSERT INTO `t_role_menu` VALUES ('t_sys_sysparam_update_admin', 'admin', 't_sys_sysparam_update');
INSERT INTO `t_role_menu` VALUES ('t_sys_sysparam_look_admin', 'admin', 't_sys_sysparam_look');
INSERT INTO `t_role_menu` VALUES ('t_sys_sysparam_export_admin', 'admin', 't_sys_sysparam_export');
INSERT INTO `t_role_menu` VALUES ('t_sys_sysparam_deletemore_admin', 'admin', 't_sys_sysparam_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_sys_sysparam_delete_admin', 'admin', 't_sys_sysparam_delete');
INSERT INTO `t_role_menu` VALUES ('t_sys_sysparam_upload_admin', 'admin', 't_sys_sysparam_upload');
