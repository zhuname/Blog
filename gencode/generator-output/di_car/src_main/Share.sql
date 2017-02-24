
INSERT INTO t_menu values('t_share_list','分享表管理', 'system_manager', null,'/share/list','1','是',null,null);
INSERT INTO t_menu values('t_share_update','修改分享表', 't_share_list', null,'/share/update','0','是',null,null);
INSERT INTO t_menu values('t_share_look','查看分享表', 't_share_list', null,'/share/look','0','是',null,null);
INSERT INTO t_menu values('t_share_export','导出分享表', 't_share_list', null,'/share/list/export','0','是',null,null);
INSERT INTO t_menu values('t_share_deletemore','批量删除分享表', 't_share_list', null,'/share/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_share_delete','删除分享表', 't_share_list', null,'/share/delete','0','是',null,null);
INSERT INTO t_menu values('t_share_upload','导入分享表', 't_share_list', null,'/share/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_share_list_admin', 'admin', 't_share_list');
INSERT INTO `t_role_menu` VALUES ('t_share_update_admin', 'admin', 't_share_update');
INSERT INTO `t_role_menu` VALUES ('t_share_look_admin', 'admin', 't_share_look');
INSERT INTO `t_role_menu` VALUES ('t_share_export_admin', 'admin', 't_share_export');
INSERT INTO `t_role_menu` VALUES ('t_share_deletemore_admin', 'admin', 't_share_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_share_delete_admin', 'admin', 't_share_delete');
INSERT INTO `t_role_menu` VALUES ('t_share_upload_admin', 'admin', 't_share_upload');
