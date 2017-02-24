
INSERT INTO t_menu values('t_media_package_list','视频红包表管理', 'system_manager', null,'/mediapackage/list','1','是',null,null);
INSERT INTO t_menu values('t_media_package_update','修改视频红包表', 't_media_package_list', null,'/mediapackage/update','0','是',null,null);
INSERT INTO t_menu values('t_media_package_look','查看视频红包表', 't_media_package_list', null,'/mediapackage/look','0','是',null,null);
INSERT INTO t_menu values('t_media_package_export','导出视频红包表', 't_media_package_list', null,'/mediapackage/list/export','0','是',null,null);
INSERT INTO t_menu values('t_media_package_deletemore','批量删除视频红包表', 't_media_package_list', null,'/mediapackage/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_media_package_delete','删除视频红包表', 't_media_package_list', null,'/mediapackage/delete','0','是',null,null);
INSERT INTO t_menu values('t_media_package_upload','导入视频红包表', 't_media_package_list', null,'/mediapackage/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_media_package_list_admin', 'admin', 't_media_package_list');
INSERT INTO `t_role_menu` VALUES ('t_media_package_update_admin', 'admin', 't_media_package_update');
INSERT INTO `t_role_menu` VALUES ('t_media_package_look_admin', 'admin', 't_media_package_look');
INSERT INTO `t_role_menu` VALUES ('t_media_package_export_admin', 'admin', 't_media_package_export');
INSERT INTO `t_role_menu` VALUES ('t_media_package_deletemore_admin', 'admin', 't_media_package_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_media_package_delete_admin', 'admin', 't_media_package_delete');
INSERT INTO `t_role_menu` VALUES ('t_media_package_upload_admin', 'admin', 't_media_package_upload');
