
INSERT INTO t_menu values('t_shield_list','屏蔽表管理', 'system_manager', null,'/shield/list','1','是',null,null);
INSERT INTO t_menu values('t_shield_update','修改屏蔽表', 't_shield_list', null,'/shield/update','0','是',null,null);
INSERT INTO t_menu values('t_shield_look','查看屏蔽表', 't_shield_list', null,'/shield/look','0','是',null,null);
INSERT INTO t_menu values('t_shield_export','导出屏蔽表', 't_shield_list', null,'/shield/list/export','0','是',null,null);
INSERT INTO t_menu values('t_shield_deletemore','批量删除屏蔽表', 't_shield_list', null,'/shield/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_shield_delete','删除屏蔽表', 't_shield_list', null,'/shield/delete','0','是',null,null);
INSERT INTO t_menu values('t_shield_upload','导入屏蔽表', 't_shield_list', null,'/shield/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_shield_list_admin', 'admin', 't_shield_list');
INSERT INTO `t_role_menu` VALUES ('t_shield_update_admin', 'admin', 't_shield_update');
INSERT INTO `t_role_menu` VALUES ('t_shield_look_admin', 'admin', 't_shield_look');
INSERT INTO `t_role_menu` VALUES ('t_shield_export_admin', 'admin', 't_shield_export');
INSERT INTO `t_role_menu` VALUES ('t_shield_deletemore_admin', 'admin', 't_shield_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_shield_delete_admin', 'admin', 't_shield_delete');
INSERT INTO `t_role_menu` VALUES ('t_shield_upload_admin', 'admin', 't_shield_upload');
