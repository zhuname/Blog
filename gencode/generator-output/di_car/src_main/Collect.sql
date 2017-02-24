
INSERT INTO t_menu values('t_collect_list','收藏表管理', 'system_manager', null,'/collect/list','1','是',null,null);
INSERT INTO t_menu values('t_collect_update','修改收藏表', 't_collect_list', null,'/collect/update','0','是',null,null);
INSERT INTO t_menu values('t_collect_look','查看收藏表', 't_collect_list', null,'/collect/look','0','是',null,null);
INSERT INTO t_menu values('t_collect_export','导出收藏表', 't_collect_list', null,'/collect/list/export','0','是',null,null);
INSERT INTO t_menu values('t_collect_deletemore','批量删除收藏表', 't_collect_list', null,'/collect/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_collect_delete','删除收藏表', 't_collect_list', null,'/collect/delete','0','是',null,null);
INSERT INTO t_menu values('t_collect_upload','导入收藏表', 't_collect_list', null,'/collect/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_collect_list_admin', 'admin', 't_collect_list');
INSERT INTO `t_role_menu` VALUES ('t_collect_update_admin', 'admin', 't_collect_update');
INSERT INTO `t_role_menu` VALUES ('t_collect_look_admin', 'admin', 't_collect_look');
INSERT INTO `t_role_menu` VALUES ('t_collect_export_admin', 'admin', 't_collect_export');
INSERT INTO `t_role_menu` VALUES ('t_collect_deletemore_admin', 'admin', 't_collect_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_collect_delete_admin', 'admin', 't_collect_delete');
INSERT INTO `t_role_menu` VALUES ('t_collect_upload_admin', 'admin', 't_collect_upload');
