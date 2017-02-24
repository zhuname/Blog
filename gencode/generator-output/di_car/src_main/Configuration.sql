
INSERT INTO t_menu values('t_configuration_list','配置表管理', 'system_manager', null,'/configuration/list','1','是',null,null);
INSERT INTO t_menu values('t_configuration_update','修改配置表', 't_configuration_list', null,'/configuration/update','0','是',null,null);
INSERT INTO t_menu values('t_configuration_look','查看配置表', 't_configuration_list', null,'/configuration/look','0','是',null,null);
INSERT INTO t_menu values('t_configuration_export','导出配置表', 't_configuration_list', null,'/configuration/list/export','0','是',null,null);
INSERT INTO t_menu values('t_configuration_deletemore','批量删除配置表', 't_configuration_list', null,'/configuration/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_configuration_delete','删除配置表', 't_configuration_list', null,'/configuration/delete','0','是',null,null);
INSERT INTO t_menu values('t_configuration_upload','导入配置表', 't_configuration_list', null,'/configuration/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_configuration_list_admin', 'admin', 't_configuration_list');
INSERT INTO `t_role_menu` VALUES ('t_configuration_update_admin', 'admin', 't_configuration_update');
INSERT INTO `t_role_menu` VALUES ('t_configuration_look_admin', 'admin', 't_configuration_look');
INSERT INTO `t_role_menu` VALUES ('t_configuration_export_admin', 'admin', 't_configuration_export');
INSERT INTO `t_role_menu` VALUES ('t_configuration_deletemore_admin', 'admin', 't_configuration_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_configuration_delete_admin', 'admin', 't_configuration_delete');
INSERT INTO `t_role_menu` VALUES ('t_configuration_upload_admin', 'admin', 't_configuration_upload');
