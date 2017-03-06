
INSERT INTO t_menu values('t_password_list','Password管理', 'system_manager', null,'/password/list','1','是',null,null);
INSERT INTO t_menu values('t_password_update','修改Password', 't_password_list', null,'/password/update','0','是',null,null);
INSERT INTO t_menu values('t_password_look','查看Password', 't_password_list', null,'/password/look','0','是',null,null);
INSERT INTO t_menu values('t_password_export','导出Password', 't_password_list', null,'/password/list/export','0','是',null,null);
INSERT INTO t_menu values('t_password_deletemore','批量删除Password', 't_password_list', null,'/password/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_password_delete','删除Password', 't_password_list', null,'/password/delete','0','是',null,null);
INSERT INTO t_menu values('t_password_upload','导入Password', 't_password_list', null,'/password/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_password_list_admin', 'admin', 't_password_list');
INSERT INTO `t_role_menu` VALUES ('t_password_update_admin', 'admin', 't_password_update');
INSERT INTO `t_role_menu` VALUES ('t_password_look_admin', 'admin', 't_password_look');
INSERT INTO `t_role_menu` VALUES ('t_password_export_admin', 'admin', 't_password_export');
INSERT INTO `t_role_menu` VALUES ('t_password_deletemore_admin', 'admin', 't_password_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_password_delete_admin', 'admin', 't_password_delete');
INSERT INTO `t_role_menu` VALUES ('t_password_upload_admin', 'admin', 't_password_upload');
