
INSERT INTO t_menu values('t_city_list','城市表管理', 'system_manager', null,'/city/list','1','是',null,null);
INSERT INTO t_menu values('t_city_update','修改城市表', 't_city_list', null,'/city/update','0','是',null,null);
INSERT INTO t_menu values('t_city_look','查看城市表', 't_city_list', null,'/city/look','0','是',null,null);
INSERT INTO t_menu values('t_city_export','导出城市表', 't_city_list', null,'/city/list/export','0','是',null,null);
INSERT INTO t_menu values('t_city_deletemore','批量删除城市表', 't_city_list', null,'/city/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_city_delete','删除城市表', 't_city_list', null,'/city/delete','0','是',null,null);
INSERT INTO t_menu values('t_city_upload','导入城市表', 't_city_list', null,'/city/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_city_list_admin', 'admin', 't_city_list');
INSERT INTO `t_role_menu` VALUES ('t_city_update_admin', 'admin', 't_city_update');
INSERT INTO `t_role_menu` VALUES ('t_city_look_admin', 'admin', 't_city_look');
INSERT INTO `t_role_menu` VALUES ('t_city_export_admin', 'admin', 't_city_export');
INSERT INTO `t_role_menu` VALUES ('t_city_deletemore_admin', 'admin', 't_city_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_city_delete_admin', 'admin', 't_city_delete');
INSERT INTO `t_role_menu` VALUES ('t_city_upload_admin', 'admin', 't_city_upload');
