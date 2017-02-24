
INSERT INTO t_menu values('t_money_detail_list','金额表动记录表管理', 'system_manager', null,'/moneydetail/list','1','是',null,null);
INSERT INTO t_menu values('t_money_detail_update','修改金额表动记录表', 't_money_detail_list', null,'/moneydetail/update','0','是',null,null);
INSERT INTO t_menu values('t_money_detail_look','查看金额表动记录表', 't_money_detail_list', null,'/moneydetail/look','0','是',null,null);
INSERT INTO t_menu values('t_money_detail_export','导出金额表动记录表', 't_money_detail_list', null,'/moneydetail/list/export','0','是',null,null);
INSERT INTO t_menu values('t_money_detail_deletemore','批量删除金额表动记录表', 't_money_detail_list', null,'/moneydetail/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_money_detail_delete','删除金额表动记录表', 't_money_detail_list', null,'/moneydetail/delete','0','是',null,null);
INSERT INTO t_menu values('t_money_detail_upload','导入金额表动记录表', 't_money_detail_list', null,'/moneydetail/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_money_detail_list_admin', 'admin', 't_money_detail_list');
INSERT INTO `t_role_menu` VALUES ('t_money_detail_update_admin', 'admin', 't_money_detail_update');
INSERT INTO `t_role_menu` VALUES ('t_money_detail_look_admin', 'admin', 't_money_detail_look');
INSERT INTO `t_role_menu` VALUES ('t_money_detail_export_admin', 'admin', 't_money_detail_export');
INSERT INTO `t_role_menu` VALUES ('t_money_detail_deletemore_admin', 'admin', 't_money_detail_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_money_detail_delete_admin', 'admin', 't_money_detail_delete');
INSERT INTO `t_role_menu` VALUES ('t_money_detail_upload_admin', 'admin', 't_money_detail_upload');
