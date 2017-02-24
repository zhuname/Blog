
INSERT INTO t_menu values('t_role_list','角色管理', 'system_manager', null,'/role/list','1','是',null,null);
INSERT INTO t_menu values('t_role_update','修改角色', 't_role_list', null,'/role/update','0','是',null,null);
INSERT INTO t_menu values('t_role_look','查看角色', 't_role_list', null,'/role/look','0','是',null,null);
INSERT INTO t_menu values('t_role_export','导出角色', 't_role_list', null,'/role/list/export','0','是',null,null);
INSERT INTO t_menu values('t_role_deletemore','批量删除角色', 't_role_list', null,'/role/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_role_delete','删除角色', 't_role_list', null,'/role/delete','0','是',null,null);
INSERT INTO t_menu values('t_role_upload','导入角色', 't_role_list', null,'/role/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_role_list_admin', 'admin', 't_role_list');
INSERT INTO `t_role_menu` VALUES ('t_role_update_admin', 'admin', 't_role_update');
INSERT INTO `t_role_menu` VALUES ('t_role_look_admin', 'admin', 't_role_look');
INSERT INTO `t_role_menu` VALUES ('t_role_export_admin', 'admin', 't_role_export');
INSERT INTO `t_role_menu` VALUES ('t_role_deletemore_admin', 'admin', 't_role_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_role_delete_admin', 'admin', 't_role_delete');
INSERT INTO `t_role_menu` VALUES ('t_role_upload_admin', 'admin', 't_role_upload');
