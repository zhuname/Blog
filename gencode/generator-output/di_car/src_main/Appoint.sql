
INSERT INTO t_menu values('t_appoint_list','预约表管理', 'system_manager', null,'/appoint/list','1','是',null,null);
INSERT INTO t_menu values('t_appoint_update','修改预约表', 't_appoint_list', null,'/appoint/update','0','是',null,null);
INSERT INTO t_menu values('t_appoint_look','查看预约表', 't_appoint_list', null,'/appoint/look','0','是',null,null);
INSERT INTO t_menu values('t_appoint_export','导出预约表', 't_appoint_list', null,'/appoint/list/export','0','是',null,null);
INSERT INTO t_menu values('t_appoint_deletemore','批量删除预约表', 't_appoint_list', null,'/appoint/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_appoint_delete','删除预约表', 't_appoint_list', null,'/appoint/delete','0','是',null,null);
INSERT INTO t_menu values('t_appoint_upload','导入预约表', 't_appoint_list', null,'/appoint/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_appoint_list_admin', 'admin', 't_appoint_list');
INSERT INTO `t_role_menu` VALUES ('t_appoint_update_admin', 'admin', 't_appoint_update');
INSERT INTO `t_role_menu` VALUES ('t_appoint_look_admin', 'admin', 't_appoint_look');
INSERT INTO `t_role_menu` VALUES ('t_appoint_export_admin', 'admin', 't_appoint_export');
INSERT INTO `t_role_menu` VALUES ('t_appoint_deletemore_admin', 'admin', 't_appoint_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_appoint_delete_admin', 'admin', 't_appoint_delete');
INSERT INTO `t_role_menu` VALUES ('t_appoint_upload_admin', 'admin', 't_appoint_upload');
