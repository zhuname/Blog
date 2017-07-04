
INSERT INTO t_menu values('t_give_award_list','颁奖表管理', 'system_manager', null,'/giveaward/list','1','是',null,null);
INSERT INTO t_menu values('t_give_award_update','修改颁奖表', 't_give_award_list', null,'/giveaward/update','0','是',null,null);
INSERT INTO t_menu values('t_give_award_look','查看颁奖表', 't_give_award_list', null,'/giveaward/look','0','是',null,null);
INSERT INTO t_menu values('t_give_award_export','导出颁奖表', 't_give_award_list', null,'/giveaward/list/export','0','是',null,null);
INSERT INTO t_menu values('t_give_award_deletemore','批量删除颁奖表', 't_give_award_list', null,'/giveaward/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_give_award_delete','删除颁奖表', 't_give_award_list', null,'/giveaward/delete','0','是',null,null);
INSERT INTO t_menu values('t_give_award_upload','导入颁奖表', 't_give_award_list', null,'/giveaward/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_give_award_list_admin', 'admin', 't_give_award_list');
INSERT INTO `t_role_menu` VALUES ('t_give_award_update_admin', 'admin', 't_give_award_update');
INSERT INTO `t_role_menu` VALUES ('t_give_award_look_admin', 'admin', 't_give_award_look');
INSERT INTO `t_role_menu` VALUES ('t_give_award_export_admin', 'admin', 't_give_award_export');
INSERT INTO `t_role_menu` VALUES ('t_give_award_deletemore_admin', 'admin', 't_give_award_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_give_award_delete_admin', 'admin', 't_give_award_delete');
INSERT INTO `t_role_menu` VALUES ('t_give_award_upload_admin', 'admin', 't_give_award_upload');
