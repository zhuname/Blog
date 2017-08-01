
INSERT INTO t_menu values('t_circle_list','城事圈表管理', 'system_manager', null,'/circle/list','1','是',null,null);
INSERT INTO t_menu values('t_circle_update','修改城事圈表', 't_circle_list', null,'/circle/update','0','是',null,null);
INSERT INTO t_menu values('t_circle_look','查看城事圈表', 't_circle_list', null,'/circle/look','0','是',null,null);
INSERT INTO t_menu values('t_circle_export','导出城事圈表', 't_circle_list', null,'/circle/list/export','0','是',null,null);
INSERT INTO t_menu values('t_circle_deletemore','批量删除城事圈表', 't_circle_list', null,'/circle/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_circle_delete','删除城事圈表', 't_circle_list', null,'/circle/delete','0','是',null,null);
INSERT INTO t_menu values('t_circle_upload','导入城事圈表', 't_circle_list', null,'/circle/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_circle_list_admin', 'admin', 't_circle_list');
INSERT INTO `t_role_menu` VALUES ('t_circle_update_admin', 'admin', 't_circle_update');
INSERT INTO `t_role_menu` VALUES ('t_circle_look_admin', 'admin', 't_circle_look');
INSERT INTO `t_role_menu` VALUES ('t_circle_export_admin', 'admin', 't_circle_export');
INSERT INTO `t_role_menu` VALUES ('t_circle_deletemore_admin', 'admin', 't_circle_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_circle_delete_admin', 'admin', 't_circle_delete');
INSERT INTO `t_role_menu` VALUES ('t_circle_upload_admin', 'admin', 't_circle_upload');
