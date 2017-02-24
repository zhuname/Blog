
INSERT INTO t_menu values('t_attention_list','关注表管理', 'system_manager', null,'/attention/list','1','是',null,null);
INSERT INTO t_menu values('t_attention_update','修改关注表', 't_attention_list', null,'/attention/update','0','是',null,null);
INSERT INTO t_menu values('t_attention_look','查看关注表', 't_attention_list', null,'/attention/look','0','是',null,null);
INSERT INTO t_menu values('t_attention_export','导出关注表', 't_attention_list', null,'/attention/list/export','0','是',null,null);
INSERT INTO t_menu values('t_attention_deletemore','批量删除关注表', 't_attention_list', null,'/attention/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_attention_delete','删除关注表', 't_attention_list', null,'/attention/delete','0','是',null,null);
INSERT INTO t_menu values('t_attention_upload','导入关注表', 't_attention_list', null,'/attention/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_attention_list_admin', 'admin', 't_attention_list');
INSERT INTO `t_role_menu` VALUES ('t_attention_update_admin', 'admin', 't_attention_update');
INSERT INTO `t_role_menu` VALUES ('t_attention_look_admin', 'admin', 't_attention_look');
INSERT INTO `t_role_menu` VALUES ('t_attention_export_admin', 'admin', 't_attention_export');
INSERT INTO `t_role_menu` VALUES ('t_attention_deletemore_admin', 'admin', 't_attention_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_attention_delete_admin', 'admin', 't_attention_delete');
INSERT INTO `t_role_menu` VALUES ('t_attention_upload_admin', 'admin', 't_attention_upload');
