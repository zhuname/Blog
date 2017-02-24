
INSERT INTO t_menu values('t_red_city_list','红包城市管理', 'system_manager', null,'/redcity/list','1','是',null,null);
INSERT INTO t_menu values('t_red_city_update','修改红包城市', 't_red_city_list', null,'/redcity/update','0','是',null,null);
INSERT INTO t_menu values('t_red_city_look','查看红包城市', 't_red_city_list', null,'/redcity/look','0','是',null,null);
INSERT INTO t_menu values('t_red_city_export','导出红包城市', 't_red_city_list', null,'/redcity/list/export','0','是',null,null);
INSERT INTO t_menu values('t_red_city_deletemore','批量删除红包城市', 't_red_city_list', null,'/redcity/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_red_city_delete','删除红包城市', 't_red_city_list', null,'/redcity/delete','0','是',null,null);
INSERT INTO t_menu values('t_red_city_upload','导入红包城市', 't_red_city_list', null,'/redcity/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_red_city_list_admin', 'admin', 't_red_city_list');
INSERT INTO `t_role_menu` VALUES ('t_red_city_update_admin', 'admin', 't_red_city_update');
INSERT INTO `t_role_menu` VALUES ('t_red_city_look_admin', 'admin', 't_red_city_look');
INSERT INTO `t_role_menu` VALUES ('t_red_city_export_admin', 'admin', 't_red_city_export');
INSERT INTO `t_role_menu` VALUES ('t_red_city_deletemore_admin', 'admin', 't_red_city_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_red_city_delete_admin', 'admin', 't_red_city_delete');
INSERT INTO `t_role_menu` VALUES ('t_red_city_upload_admin', 'admin', 't_red_city_upload');
