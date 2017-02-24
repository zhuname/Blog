
INSERT INTO t_menu values('t_bank_list','银行表管理', 'system_manager', null,'/bank/list','1','是',null,null);
INSERT INTO t_menu values('t_bank_update','修改银行表', 't_bank_list', null,'/bank/update','0','是',null,null);
INSERT INTO t_menu values('t_bank_look','查看银行表', 't_bank_list', null,'/bank/look','0','是',null,null);
INSERT INTO t_menu values('t_bank_export','导出银行表', 't_bank_list', null,'/bank/list/export','0','是',null,null);
INSERT INTO t_menu values('t_bank_deletemore','批量删除银行表', 't_bank_list', null,'/bank/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_bank_delete','删除银行表', 't_bank_list', null,'/bank/delete','0','是',null,null);
INSERT INTO t_menu values('t_bank_upload','导入银行表', 't_bank_list', null,'/bank/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_bank_list_admin', 'admin', 't_bank_list');
INSERT INTO `t_role_menu` VALUES ('t_bank_update_admin', 'admin', 't_bank_update');
INSERT INTO `t_role_menu` VALUES ('t_bank_look_admin', 'admin', 't_bank_look');
INSERT INTO `t_role_menu` VALUES ('t_bank_export_admin', 'admin', 't_bank_export');
INSERT INTO `t_role_menu` VALUES ('t_bank_deletemore_admin', 'admin', 't_bank_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_bank_delete_admin', 'admin', 't_bank_delete');
INSERT INTO `t_role_menu` VALUES ('t_bank_upload_admin', 'admin', 't_bank_upload');
