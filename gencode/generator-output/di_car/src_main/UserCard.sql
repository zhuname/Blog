
INSERT INTO t_menu values('t_user_card_list','卡券兑换表管理', 'system_manager', null,'/usercard/list','1','是',null,null);
INSERT INTO t_menu values('t_user_card_update','修改卡券兑换表', 't_user_card_list', null,'/usercard/update','0','是',null,null);
INSERT INTO t_menu values('t_user_card_look','查看卡券兑换表', 't_user_card_list', null,'/usercard/look','0','是',null,null);
INSERT INTO t_menu values('t_user_card_export','导出卡券兑换表', 't_user_card_list', null,'/usercard/list/export','0','是',null,null);
INSERT INTO t_menu values('t_user_card_deletemore','批量删除卡券兑换表', 't_user_card_list', null,'/usercard/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_user_card_delete','删除卡券兑换表', 't_user_card_list', null,'/usercard/delete','0','是',null,null);
INSERT INTO t_menu values('t_user_card_upload','导入卡券兑换表', 't_user_card_list', null,'/usercard/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_user_card_list_admin', 'admin', 't_user_card_list');
INSERT INTO `t_role_menu` VALUES ('t_user_card_update_admin', 'admin', 't_user_card_update');
INSERT INTO `t_role_menu` VALUES ('t_user_card_look_admin', 'admin', 't_user_card_look');
INSERT INTO `t_role_menu` VALUES ('t_user_card_export_admin', 'admin', 't_user_card_export');
INSERT INTO `t_role_menu` VALUES ('t_user_card_deletemore_admin', 'admin', 't_user_card_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_user_card_delete_admin', 'admin', 't_user_card_delete');
INSERT INTO `t_role_menu` VALUES ('t_user_card_upload_admin', 'admin', 't_user_card_upload');
