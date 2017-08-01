
INSERT INTO t_menu values('t_join_activity_list','同城活动参与表管理', 'system_manager', null,'/joinactivity/list','1','是',null,null);
INSERT INTO t_menu values('t_join_activity_update','修改同城活动参与表', 't_join_activity_list', null,'/joinactivity/update','0','是',null,null);
INSERT INTO t_menu values('t_join_activity_look','查看同城活动参与表', 't_join_activity_list', null,'/joinactivity/look','0','是',null,null);
INSERT INTO t_menu values('t_join_activity_export','导出同城活动参与表', 't_join_activity_list', null,'/joinactivity/list/export','0','是',null,null);
INSERT INTO t_menu values('t_join_activity_deletemore','批量删除同城活动参与表', 't_join_activity_list', null,'/joinactivity/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_join_activity_delete','删除同城活动参与表', 't_join_activity_list', null,'/joinactivity/delete','0','是',null,null);
INSERT INTO t_menu values('t_join_activity_upload','导入同城活动参与表', 't_join_activity_list', null,'/joinactivity/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_join_activity_list_admin', 'admin', 't_join_activity_list');
INSERT INTO `t_role_menu` VALUES ('t_join_activity_update_admin', 'admin', 't_join_activity_update');
INSERT INTO `t_role_menu` VALUES ('t_join_activity_look_admin', 'admin', 't_join_activity_look');
INSERT INTO `t_role_menu` VALUES ('t_join_activity_export_admin', 'admin', 't_join_activity_export');
INSERT INTO `t_role_menu` VALUES ('t_join_activity_deletemore_admin', 'admin', 't_join_activity_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_join_activity_delete_admin', 'admin', 't_join_activity_delete');
INSERT INTO `t_role_menu` VALUES ('t_join_activity_upload_admin', 'admin', 't_join_activity_upload');
