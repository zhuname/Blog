
INSERT INTO t_menu values('t_app_user_list','用户表管理', 'system_manager', null,'/appuser/list','1','是',null,null);
INSERT INTO t_menu values('t_app_user_update','修改用户表', 't_app_user_list', null,'/appuser/update','0','是',null,null);
INSERT INTO t_menu values('t_app_user_look','查看用户表', 't_app_user_list', null,'/appuser/look','0','是',null,null);
INSERT INTO t_menu values('t_app_user_export','导出用户表', 't_app_user_list', null,'/appuser/list/export','0','是',null,null);
INSERT INTO t_menu values('t_app_user_deletemore','批量删除用户表', 't_app_user_list', null,'/appuser/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_app_user_delete','删除用户表', 't_app_user_list', null,'/appuser/delete','0','是',null,null);
INSERT INTO t_menu values('t_app_user_upload','导入用户表', 't_app_user_list', null,'/appuser/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_app_user_list_admin', 'admin', 't_app_user_list');
INSERT INTO `t_role_menu` VALUES ('t_app_user_update_admin', 'admin', 't_app_user_update');
INSERT INTO `t_role_menu` VALUES ('t_app_user_look_admin', 'admin', 't_app_user_look');
INSERT INTO `t_role_menu` VALUES ('t_app_user_export_admin', 'admin', 't_app_user_export');
INSERT INTO `t_role_menu` VALUES ('t_app_user_deletemore_admin', 'admin', 't_app_user_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_app_user_delete_admin', 'admin', 't_app_user_delete');
INSERT INTO `t_role_menu` VALUES ('t_app_user_upload_admin', 'admin', 't_app_user_upload');
