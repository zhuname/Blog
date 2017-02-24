
INSERT INTO t_menu values('t_feedback_list',' 意见反馈表管理', 'system_manager', null,'/feedback/list','1','是',null,null);
INSERT INTO t_menu values('t_feedback_update','修改 意见反馈表', 't_feedback_list', null,'/feedback/update','0','是',null,null);
INSERT INTO t_menu values('t_feedback_look','查看 意见反馈表', 't_feedback_list', null,'/feedback/look','0','是',null,null);
INSERT INTO t_menu values('t_feedback_export','导出 意见反馈表', 't_feedback_list', null,'/feedback/list/export','0','是',null,null);
INSERT INTO t_menu values('t_feedback_deletemore','批量删除 意见反馈表', 't_feedback_list', null,'/feedback/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_feedback_delete','删除 意见反馈表', 't_feedback_list', null,'/feedback/delete','0','是',null,null);
INSERT INTO t_menu values('t_feedback_upload','导入 意见反馈表', 't_feedback_list', null,'/feedback/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_feedback_list_admin', 'admin', 't_feedback_list');
INSERT INTO `t_role_menu` VALUES ('t_feedback_update_admin', 'admin', 't_feedback_update');
INSERT INTO `t_role_menu` VALUES ('t_feedback_look_admin', 'admin', 't_feedback_look');
INSERT INTO `t_role_menu` VALUES ('t_feedback_export_admin', 'admin', 't_feedback_export');
INSERT INTO `t_role_menu` VALUES ('t_feedback_deletemore_admin', 'admin', 't_feedback_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_feedback_delete_admin', 'admin', 't_feedback_delete');
INSERT INTO `t_role_menu` VALUES ('t_feedback_upload_admin', 'admin', 't_feedback_upload');
