
INSERT INTO t_menu values('t_l_media_package_list','小视频红包管理', 'system_manager', null,'/lmediapackage/list','1','是',null,null);
INSERT INTO t_menu values('t_l_media_package_update','修改小视频红包', 't_l_media_package_list', null,'/lmediapackage/update','0','是',null,null);
INSERT INTO t_menu values('t_l_media_package_look','查看小视频红包', 't_l_media_package_list', null,'/lmediapackage/look','0','是',null,null);
INSERT INTO t_menu values('t_l_media_package_export','导出小视频红包', 't_l_media_package_list', null,'/lmediapackage/list/export','0','是',null,null);
INSERT INTO t_menu values('t_l_media_package_deletemore','批量删除小视频红包', 't_l_media_package_list', null,'/lmediapackage/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_l_media_package_delete','删除小视频红包', 't_l_media_package_list', null,'/lmediapackage/delete','0','是',null,null);
INSERT INTO t_menu values('t_l_media_package_upload','导入小视频红包', 't_l_media_package_list', null,'/lmediapackage/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_l_media_package_list_admin', 'admin', 't_l_media_package_list');
INSERT INTO `t_role_menu` VALUES ('t_l_media_package_update_admin', 'admin', 't_l_media_package_update');
INSERT INTO `t_role_menu` VALUES ('t_l_media_package_look_admin', 'admin', 't_l_media_package_look');
INSERT INTO `t_role_menu` VALUES ('t_l_media_package_export_admin', 'admin', 't_l_media_package_export');
INSERT INTO `t_role_menu` VALUES ('t_l_media_package_deletemore_admin', 'admin', 't_l_media_package_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_l_media_package_delete_admin', 'admin', 't_l_media_package_delete');
INSERT INTO `t_role_menu` VALUES ('t_l_media_package_upload_admin', 'admin', 't_l_media_package_upload');
