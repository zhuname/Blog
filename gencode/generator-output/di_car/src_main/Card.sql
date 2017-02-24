
INSERT INTO t_menu values('t_card_list','卡券表管理', 'system_manager', null,'/card/list','1','是',null,null);
INSERT INTO t_menu values('t_card_update','修改卡券表', 't_card_list', null,'/card/update','0','是',null,null);
INSERT INTO t_menu values('t_card_look','查看卡券表', 't_card_list', null,'/card/look','0','是',null,null);
INSERT INTO t_menu values('t_card_export','导出卡券表', 't_card_list', null,'/card/list/export','0','是',null,null);
INSERT INTO t_menu values('t_card_deletemore','批量删除卡券表', 't_card_list', null,'/card/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_card_delete','删除卡券表', 't_card_list', null,'/card/delete','0','是',null,null);
INSERT INTO t_menu values('t_card_upload','导入卡券表', 't_card_list', null,'/card/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_card_list_admin', 'admin', 't_card_list');
INSERT INTO `t_role_menu` VALUES ('t_card_update_admin', 'admin', 't_card_update');
INSERT INTO `t_role_menu` VALUES ('t_card_look_admin', 'admin', 't_card_look');
INSERT INTO `t_role_menu` VALUES ('t_card_export_admin', 'admin', 't_card_export');
INSERT INTO `t_role_menu` VALUES ('t_card_deletemore_admin', 'admin', 't_card_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_card_delete_admin', 'admin', 't_card_delete');
INSERT INTO `t_role_menu` VALUES ('t_card_upload_admin', 'admin', 't_card_upload');
