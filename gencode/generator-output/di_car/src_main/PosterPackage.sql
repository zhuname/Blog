
INSERT INTO t_menu values('t_poster_package_list','海报红包表管理', 'system_manager', null,'/posterpackage/list','1','是',null,null);
INSERT INTO t_menu values('t_poster_package_update','修改海报红包表', 't_poster_package_list', null,'/posterpackage/update','0','是',null,null);
INSERT INTO t_menu values('t_poster_package_look','查看海报红包表', 't_poster_package_list', null,'/posterpackage/look','0','是',null,null);
INSERT INTO t_menu values('t_poster_package_export','导出海报红包表', 't_poster_package_list', null,'/posterpackage/list/export','0','是',null,null);
INSERT INTO t_menu values('t_poster_package_deletemore','批量删除海报红包表', 't_poster_package_list', null,'/posterpackage/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_poster_package_delete','删除海报红包表', 't_poster_package_list', null,'/posterpackage/delete','0','是',null,null);
INSERT INTO t_menu values('t_poster_package_upload','导入海报红包表', 't_poster_package_list', null,'/posterpackage/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_poster_package_list_admin', 'admin', 't_poster_package_list');
INSERT INTO `t_role_menu` VALUES ('t_poster_package_update_admin', 'admin', 't_poster_package_update');
INSERT INTO `t_role_menu` VALUES ('t_poster_package_look_admin', 'admin', 't_poster_package_look');
INSERT INTO `t_role_menu` VALUES ('t_poster_package_export_admin', 'admin', 't_poster_package_export');
INSERT INTO `t_role_menu` VALUES ('t_poster_package_deletemore_admin', 'admin', 't_poster_package_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_poster_package_delete_admin', 'admin', 't_poster_package_delete');
INSERT INTO `t_role_menu` VALUES ('t_poster_package_upload_admin', 'admin', 't_poster_package_upload');
