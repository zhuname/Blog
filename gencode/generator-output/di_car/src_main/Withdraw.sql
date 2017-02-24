
INSERT INTO t_menu values('t_withdraw_list','提现表管理', 'system_manager', null,'/withdraw/list','1','是',null,null);
INSERT INTO t_menu values('t_withdraw_update','修改提现表', 't_withdraw_list', null,'/withdraw/update','0','是',null,null);
INSERT INTO t_menu values('t_withdraw_look','查看提现表', 't_withdraw_list', null,'/withdraw/look','0','是',null,null);
INSERT INTO t_menu values('t_withdraw_export','导出提现表', 't_withdraw_list', null,'/withdraw/list/export','0','是',null,null);
INSERT INTO t_menu values('t_withdraw_deletemore','批量删除提现表', 't_withdraw_list', null,'/withdraw/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_withdraw_delete','删除提现表', 't_withdraw_list', null,'/withdraw/delete','0','是',null,null);
INSERT INTO t_menu values('t_withdraw_upload','导入提现表', 't_withdraw_list', null,'/withdraw/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_withdraw_list_admin', 'admin', 't_withdraw_list');
INSERT INTO `t_role_menu` VALUES ('t_withdraw_update_admin', 'admin', 't_withdraw_update');
INSERT INTO `t_role_menu` VALUES ('t_withdraw_look_admin', 'admin', 't_withdraw_look');
INSERT INTO `t_role_menu` VALUES ('t_withdraw_export_admin', 'admin', 't_withdraw_export');
INSERT INTO `t_role_menu` VALUES ('t_withdraw_deletemore_admin', 'admin', 't_withdraw_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_withdraw_delete_admin', 'admin', 't_withdraw_delete');
INSERT INTO `t_role_menu` VALUES ('t_withdraw_upload_admin', 'admin', 't_withdraw_upload');
