
INSERT INTO t_menu values('t_apply_medal_list','申请勋章表\r\n管理', 'system_manager', null,'/applymedal/list','1','是',null,null);
INSERT INTO t_menu values('t_apply_medal_update','修改申请勋章表\r\n', 't_apply_medal_list', null,'/applymedal/update','0','是',null,null);
INSERT INTO t_menu values('t_apply_medal_look','查看申请勋章表\r\n', 't_apply_medal_list', null,'/applymedal/look','0','是',null,null);
INSERT INTO t_menu values('t_apply_medal_export','导出申请勋章表\r\n', 't_apply_medal_list', null,'/applymedal/list/export','0','是',null,null);
INSERT INTO t_menu values('t_apply_medal_deletemore','批量删除申请勋章表\r\n', 't_apply_medal_list', null,'/applymedal/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_apply_medal_delete','删除申请勋章表\r\n', 't_apply_medal_list', null,'/applymedal/delete','0','是',null,null);
INSERT INTO t_menu values('t_apply_medal_upload','导入申请勋章表\r\n', 't_apply_medal_list', null,'/applymedal/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_apply_medal_list_admin', 'admin', 't_apply_medal_list');
INSERT INTO `t_role_menu` VALUES ('t_apply_medal_update_admin', 'admin', 't_apply_medal_update');
INSERT INTO `t_role_menu` VALUES ('t_apply_medal_look_admin', 'admin', 't_apply_medal_look');
INSERT INTO `t_role_menu` VALUES ('t_apply_medal_export_admin', 'admin', 't_apply_medal_export');
INSERT INTO `t_role_menu` VALUES ('t_apply_medal_deletemore_admin', 'admin', 't_apply_medal_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_apply_medal_delete_admin', 'admin', 't_apply_medal_delete');
INSERT INTO `t_role_menu` VALUES ('t_apply_medal_upload_admin', 'admin', 't_apply_medal_upload');
