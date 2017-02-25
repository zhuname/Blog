
INSERT INTO t_menu values('t_user_role_list','用户角色中间表管理', 'system_manager', null,'/userrole/list','1','是',null,null);
INSERT INTO t_menu values('t_user_role_update','修改用户角色中间表', 't_user_role_list', null,'/userrole/update','0','是',null,null);
INSERT INTO t_menu values('t_user_role_look','查看用户角色中间表', 't_user_role_list', null,'/userrole/look','0','是',null,null);
INSERT INTO t_menu values('t_user_role_export','导出用户角色中间表', 't_user_role_list', null,'/userrole/list/export','0','是',null,null);
INSERT INTO t_menu values('t_user_role_deletemore','批量删除用户角色中间表', 't_user_role_list', null,'/userrole/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_user_role_delete','删除用户角色中间表', 't_user_role_list', null,'/userrole/delete','0','是',null,null);
INSERT INTO t_menu values('t_user_role_upload','导入用户角色中间表', 't_user_role_list', null,'/userrole/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_user_role_list_admin', 'admin', 't_user_role_list');
INSERT INTO `t_role_menu` VALUES ('t_user_role_update_admin', 'admin', 't_user_role_update');
INSERT INTO `t_role_menu` VALUES ('t_user_role_look_admin', 'admin', 't_user_role_look');
INSERT INTO `t_role_menu` VALUES ('t_user_role_export_admin', 'admin', 't_user_role_export');
INSERT INTO `t_role_menu` VALUES ('t_user_role_deletemore_admin', 'admin', 't_user_role_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_user_role_delete_admin', 'admin', 't_user_role_delete');
INSERT INTO `t_role_menu` VALUES ('t_user_role_upload_admin', 'admin', 't_user_role_upload');
