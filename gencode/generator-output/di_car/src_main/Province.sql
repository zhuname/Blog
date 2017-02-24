
INSERT INTO t_menu values('t_province_list','省级表管理', 'system_manager', null,'/province/list','1','是',null,null);
INSERT INTO t_menu values('t_province_update','修改省级表', 't_province_list', null,'/province/update','0','是',null,null);
INSERT INTO t_menu values('t_province_look','查看省级表', 't_province_list', null,'/province/look','0','是',null,null);
INSERT INTO t_menu values('t_province_export','导出省级表', 't_province_list', null,'/province/list/export','0','是',null,null);
INSERT INTO t_menu values('t_province_deletemore','批量删除省级表', 't_province_list', null,'/province/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_province_delete','删除省级表', 't_province_list', null,'/province/delete','0','是',null,null);
INSERT INTO t_menu values('t_province_upload','导入省级表', 't_province_list', null,'/province/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_province_list_admin', 'admin', 't_province_list');
INSERT INTO `t_role_menu` VALUES ('t_province_update_admin', 'admin', 't_province_update');
INSERT INTO `t_role_menu` VALUES ('t_province_look_admin', 'admin', 't_province_look');
INSERT INTO `t_role_menu` VALUES ('t_province_export_admin', 'admin', 't_province_export');
INSERT INTO `t_role_menu` VALUES ('t_province_deletemore_admin', 'admin', 't_province_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_province_delete_admin', 'admin', 't_province_delete');
INSERT INTO `t_role_menu` VALUES ('t_province_upload_admin', 'admin', 't_province_upload');
