
INSERT INTO t_menu values('t_oper_list','点赞、评论表管理', 'system_manager', null,'/oper/list','1','是',null,null);
INSERT INTO t_menu values('t_oper_update','修改点赞、评论表', 't_oper_list', null,'/oper/update','0','是',null,null);
INSERT INTO t_menu values('t_oper_look','查看点赞、评论表', 't_oper_list', null,'/oper/look','0','是',null,null);
INSERT INTO t_menu values('t_oper_export','导出点赞、评论表', 't_oper_list', null,'/oper/list/export','0','是',null,null);
INSERT INTO t_menu values('t_oper_deletemore','批量删除点赞、评论表', 't_oper_list', null,'/oper/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_oper_delete','删除点赞、评论表', 't_oper_list', null,'/oper/delete','0','是',null,null);
INSERT INTO t_menu values('t_oper_upload','导入点赞、评论表', 't_oper_list', null,'/oper/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_oper_list_admin', 'admin', 't_oper_list');
INSERT INTO `t_role_menu` VALUES ('t_oper_update_admin', 'admin', 't_oper_update');
INSERT INTO `t_role_menu` VALUES ('t_oper_look_admin', 'admin', 't_oper_look');
INSERT INTO `t_role_menu` VALUES ('t_oper_export_admin', 'admin', 't_oper_export');
INSERT INTO `t_role_menu` VALUES ('t_oper_deletemore_admin', 'admin', 't_oper_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_oper_delete_admin', 'admin', 't_oper_delete');
INSERT INTO `t_role_menu` VALUES ('t_oper_upload_admin', 'admin', 't_oper_upload');
