
INSERT INTO t_menu values('t_user_medal_list','我的勋章表管理', 'system_manager', null,'/usermedal/list','1','是',null,null);
INSERT INTO t_menu values('t_user_medal_update','修改我的勋章表', 't_user_medal_list', null,'/usermedal/update','0','是',null,null);
INSERT INTO t_menu values('t_user_medal_look','查看我的勋章表', 't_user_medal_list', null,'/usermedal/look','0','是',null,null);
INSERT INTO t_menu values('t_user_medal_export','导出我的勋章表', 't_user_medal_list', null,'/usermedal/list/export','0','是',null,null);
INSERT INTO t_menu values('t_user_medal_deletemore','批量删除我的勋章表', 't_user_medal_list', null,'/usermedal/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_user_medal_delete','删除我的勋章表', 't_user_medal_list', null,'/usermedal/delete','0','是',null,null);
INSERT INTO t_menu values('t_user_medal_upload','导入我的勋章表', 't_user_medal_list', null,'/usermedal/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_user_medal_list_admin', 'admin', 't_user_medal_list');
INSERT INTO `t_role_menu` VALUES ('t_user_medal_update_admin', 'admin', 't_user_medal_update');
INSERT INTO `t_role_menu` VALUES ('t_user_medal_look_admin', 'admin', 't_user_medal_look');
INSERT INTO `t_role_menu` VALUES ('t_user_medal_export_admin', 'admin', 't_user_medal_export');
INSERT INTO `t_role_menu` VALUES ('t_user_medal_deletemore_admin', 'admin', 't_user_medal_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_user_medal_delete_admin', 'admin', 't_user_medal_delete');
INSERT INTO `t_role_menu` VALUES ('t_user_medal_upload_admin', 'admin', 't_user_medal_upload');
