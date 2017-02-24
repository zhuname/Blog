
INSERT INTO t_menu values('t_message_list','消息表管理', 'system_manager', null,'/message/list','1','是',null,null);
INSERT INTO t_menu values('t_message_update','修改消息表', 't_message_list', null,'/message/update','0','是',null,null);
INSERT INTO t_menu values('t_message_look','查看消息表', 't_message_list', null,'/message/look','0','是',null,null);
INSERT INTO t_menu values('t_message_export','导出消息表', 't_message_list', null,'/message/list/export','0','是',null,null);
INSERT INTO t_menu values('t_message_deletemore','批量删除消息表', 't_message_list', null,'/message/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_message_delete','删除消息表', 't_message_list', null,'/message/delete','0','是',null,null);
INSERT INTO t_menu values('t_message_upload','导入消息表', 't_message_list', null,'/message/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_message_list_admin', 'admin', 't_message_list');
INSERT INTO `t_role_menu` VALUES ('t_message_update_admin', 'admin', 't_message_update');
INSERT INTO `t_role_menu` VALUES ('t_message_look_admin', 'admin', 't_message_look');
INSERT INTO `t_role_menu` VALUES ('t_message_export_admin', 'admin', 't_message_export');
INSERT INTO `t_role_menu` VALUES ('t_message_deletemore_admin', 'admin', 't_message_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_message_delete_admin', 'admin', 't_message_delete');
INSERT INTO `t_role_menu` VALUES ('t_message_upload_admin', 'admin', 't_message_upload');
