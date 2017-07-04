
INSERT INTO t_menu values('t_awards_list','同城活动奖项表管理', 'system_manager', null,'/awards/list','1','是',null,null);
INSERT INTO t_menu values('t_awards_update','修改同城活动奖项表', 't_awards_list', null,'/awards/update','0','是',null,null);
INSERT INTO t_menu values('t_awards_look','查看同城活动奖项表', 't_awards_list', null,'/awards/look','0','是',null,null);
INSERT INTO t_menu values('t_awards_export','导出同城活动奖项表', 't_awards_list', null,'/awards/list/export','0','是',null,null);
INSERT INTO t_menu values('t_awards_deletemore','批量删除同城活动奖项表', 't_awards_list', null,'/awards/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_awards_delete','删除同城活动奖项表', 't_awards_list', null,'/awards/delete','0','是',null,null);
INSERT INTO t_menu values('t_awards_upload','导入同城活动奖项表', 't_awards_list', null,'/awards/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_awards_list_admin', 'admin', 't_awards_list');
INSERT INTO `t_role_menu` VALUES ('t_awards_update_admin', 'admin', 't_awards_update');
INSERT INTO `t_role_menu` VALUES ('t_awards_look_admin', 'admin', 't_awards_look');
INSERT INTO `t_role_menu` VALUES ('t_awards_export_admin', 'admin', 't_awards_export');
INSERT INTO `t_role_menu` VALUES ('t_awards_deletemore_admin', 'admin', 't_awards_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_awards_delete_admin', 'admin', 't_awards_delete');
INSERT INTO `t_role_menu` VALUES ('t_awards_upload_admin', 'admin', 't_awards_upload');
