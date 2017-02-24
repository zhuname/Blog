
INSERT INTO t_menu values('t_medal_list','勋章表管理', 'system_manager', null,'/medal/list','1','是',null,null);
INSERT INTO t_menu values('t_medal_update','修改勋章表', 't_medal_list', null,'/medal/update','0','是',null,null);
INSERT INTO t_menu values('t_medal_look','查看勋章表', 't_medal_list', null,'/medal/look','0','是',null,null);
INSERT INTO t_menu values('t_medal_export','导出勋章表', 't_medal_list', null,'/medal/list/export','0','是',null,null);
INSERT INTO t_menu values('t_medal_deletemore','批量删除勋章表', 't_medal_list', null,'/medal/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_medal_delete','删除勋章表', 't_medal_list', null,'/medal/delete','0','是',null,null);
INSERT INTO t_menu values('t_medal_upload','导入勋章表', 't_medal_list', null,'/medal/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_medal_list_admin', 'admin', 't_medal_list');
INSERT INTO `t_role_menu` VALUES ('t_medal_update_admin', 'admin', 't_medal_update');
INSERT INTO `t_role_menu` VALUES ('t_medal_look_admin', 'admin', 't_medal_look');
INSERT INTO `t_role_menu` VALUES ('t_medal_export_admin', 'admin', 't_medal_export');
INSERT INTO `t_role_menu` VALUES ('t_medal_deletemore_admin', 'admin', 't_medal_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_medal_delete_admin', 'admin', 't_medal_delete');
INSERT INTO `t_role_menu` VALUES ('t_medal_upload_admin', 'admin', 't_medal_upload');
