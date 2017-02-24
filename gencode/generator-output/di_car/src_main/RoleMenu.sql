
INSERT INTO t_menu values('t_role_menu_list','角色菜单中间表管理', 'system_manager', null,'/rolemenu/list','1','是',null,null);
INSERT INTO t_menu values('t_role_menu_update','修改角色菜单中间表', 't_role_menu_list', null,'/rolemenu/update','0','是',null,null);
INSERT INTO t_menu values('t_role_menu_look','查看角色菜单中间表', 't_role_menu_list', null,'/rolemenu/look','0','是',null,null);
INSERT INTO t_menu values('t_role_menu_export','导出角色菜单中间表', 't_role_menu_list', null,'/rolemenu/list/export','0','是',null,null);
INSERT INTO t_menu values('t_role_menu_deletemore','批量删除角色菜单中间表', 't_role_menu_list', null,'/rolemenu/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_role_menu_delete','删除角色菜单中间表', 't_role_menu_list', null,'/rolemenu/delete','0','是',null,null);
INSERT INTO t_menu values('t_role_menu_upload','导入角色菜单中间表', 't_role_menu_list', null,'/rolemenu/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_role_menu_list_admin', 'admin', 't_role_menu_list');
INSERT INTO `t_role_menu` VALUES ('t_role_menu_update_admin', 'admin', 't_role_menu_update');
INSERT INTO `t_role_menu` VALUES ('t_role_menu_look_admin', 'admin', 't_role_menu_look');
INSERT INTO `t_role_menu` VALUES ('t_role_menu_export_admin', 'admin', 't_role_menu_export');
INSERT INTO `t_role_menu` VALUES ('t_role_menu_deletemore_admin', 'admin', 't_role_menu_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_role_menu_delete_admin', 'admin', 't_role_menu_delete');
INSERT INTO `t_role_menu` VALUES ('t_role_menu_upload_admin', 'admin', 't_role_menu_upload');
