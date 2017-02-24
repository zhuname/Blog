
INSERT INTO t_menu values('t_menu_list','菜单管理', 'system_manager', null,'/menu/list','1','是',null,null);
INSERT INTO t_menu values('t_menu_update','修改菜单', 't_menu_list', null,'/menu/update','0','是',null,null);
INSERT INTO t_menu values('t_menu_look','查看菜单', 't_menu_list', null,'/menu/look','0','是',null,null);
INSERT INTO t_menu values('t_menu_export','导出菜单', 't_menu_list', null,'/menu/list/export','0','是',null,null);
INSERT INTO t_menu values('t_menu_deletemore','批量删除菜单', 't_menu_list', null,'/menu/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_menu_delete','删除菜单', 't_menu_list', null,'/menu/delete','0','是',null,null);
INSERT INTO t_menu values('t_menu_upload','导入菜单', 't_menu_list', null,'/menu/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_menu_list_admin', 'admin', 't_menu_list');
INSERT INTO `t_role_menu` VALUES ('t_menu_update_admin', 'admin', 't_menu_update');
INSERT INTO `t_role_menu` VALUES ('t_menu_look_admin', 'admin', 't_menu_look');
INSERT INTO `t_role_menu` VALUES ('t_menu_export_admin', 'admin', 't_menu_export');
INSERT INTO `t_role_menu` VALUES ('t_menu_deletemore_admin', 'admin', 't_menu_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_menu_delete_admin', 'admin', 't_menu_delete');
INSERT INTO `t_role_menu` VALUES ('t_menu_upload_admin', 'admin', 't_menu_upload');
