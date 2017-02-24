
INSERT INTO t_menu values('t_sms_list','验证码表管理', 'system_manager', null,'/sms/list','1','是',null,null);
INSERT INTO t_menu values('t_sms_update','修改验证码表', 't_sms_list', null,'/sms/update','0','是',null,null);
INSERT INTO t_menu values('t_sms_look','查看验证码表', 't_sms_list', null,'/sms/look','0','是',null,null);
INSERT INTO t_menu values('t_sms_export','导出验证码表', 't_sms_list', null,'/sms/list/export','0','是',null,null);
INSERT INTO t_menu values('t_sms_deletemore','批量删除验证码表', 't_sms_list', null,'/sms/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_sms_delete','删除验证码表', 't_sms_list', null,'/sms/delete','0','是',null,null);
INSERT INTO t_menu values('t_sms_upload','导入验证码表', 't_sms_list', null,'/sms/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_sms_list_admin', 'admin', 't_sms_list');
INSERT INTO `t_role_menu` VALUES ('t_sms_update_admin', 'admin', 't_sms_update');
INSERT INTO `t_role_menu` VALUES ('t_sms_look_admin', 'admin', 't_sms_look');
INSERT INTO `t_role_menu` VALUES ('t_sms_export_admin', 'admin', 't_sms_export');
INSERT INTO `t_role_menu` VALUES ('t_sms_deletemore_admin', 'admin', 't_sms_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_sms_delete_admin', 'admin', 't_sms_delete');
INSERT INTO `t_role_menu` VALUES ('t_sms_upload_admin', 'admin', 't_sms_upload');
