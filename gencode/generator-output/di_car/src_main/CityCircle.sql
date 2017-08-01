
INSERT INTO t_menu values('t_city_circle_list','城事圈打赏表管理', 'system_manager', null,'/citycircle/list','1','是',null,null);
INSERT INTO t_menu values('t_city_circle_update','修改城事圈打赏表', 't_city_circle_list', null,'/citycircle/update','0','是',null,null);
INSERT INTO t_menu values('t_city_circle_look','查看城事圈打赏表', 't_city_circle_list', null,'/citycircle/look','0','是',null,null);
INSERT INTO t_menu values('t_city_circle_export','导出城事圈打赏表', 't_city_circle_list', null,'/citycircle/list/export','0','是',null,null);
INSERT INTO t_menu values('t_city_circle_deletemore','批量删除城事圈打赏表', 't_city_circle_list', null,'/citycircle/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_city_circle_delete','删除城事圈打赏表', 't_city_circle_list', null,'/citycircle/delete','0','是',null,null);
INSERT INTO t_menu values('t_city_circle_upload','导入城事圈打赏表', 't_city_circle_list', null,'/citycircle/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_city_circle_list_admin', 'admin', 't_city_circle_list');
INSERT INTO `t_role_menu` VALUES ('t_city_circle_update_admin', 'admin', 't_city_circle_update');
INSERT INTO `t_role_menu` VALUES ('t_city_circle_look_admin', 'admin', 't_city_circle_look');
INSERT INTO `t_role_menu` VALUES ('t_city_circle_export_admin', 'admin', 't_city_circle_export');
INSERT INTO `t_role_menu` VALUES ('t_city_circle_deletemore_admin', 'admin', 't_city_circle_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_city_circle_delete_admin', 'admin', 't_city_circle_delete');
INSERT INTO `t_role_menu` VALUES ('t_city_circle_upload_admin', 'admin', 't_city_circle_upload');
