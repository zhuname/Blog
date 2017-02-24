
INSERT INTO t_menu values('t_lunbo_pic_list','轮播图表管理', 'system_manager', null,'/lunbopic/list','1','是',null,null);
INSERT INTO t_menu values('t_lunbo_pic_update','修改轮播图表', 't_lunbo_pic_list', null,'/lunbopic/update','0','是',null,null);
INSERT INTO t_menu values('t_lunbo_pic_look','查看轮播图表', 't_lunbo_pic_list', null,'/lunbopic/look','0','是',null,null);
INSERT INTO t_menu values('t_lunbo_pic_export','导出轮播图表', 't_lunbo_pic_list', null,'/lunbopic/list/export','0','是',null,null);
INSERT INTO t_menu values('t_lunbo_pic_deletemore','批量删除轮播图表', 't_lunbo_pic_list', null,'/lunbopic/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_lunbo_pic_delete','删除轮播图表', 't_lunbo_pic_list', null,'/lunbopic/delete','0','是',null,null);
INSERT INTO t_menu values('t_lunbo_pic_upload','导入轮播图表', 't_lunbo_pic_list', null,'/lunbopic/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_lunbo_pic_list_admin', 'admin', 't_lunbo_pic_list');
INSERT INTO `t_role_menu` VALUES ('t_lunbo_pic_update_admin', 'admin', 't_lunbo_pic_update');
INSERT INTO `t_role_menu` VALUES ('t_lunbo_pic_look_admin', 'admin', 't_lunbo_pic_look');
INSERT INTO `t_role_menu` VALUES ('t_lunbo_pic_export_admin', 'admin', 't_lunbo_pic_export');
INSERT INTO `t_role_menu` VALUES ('t_lunbo_pic_deletemore_admin', 'admin', 't_lunbo_pic_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_lunbo_pic_delete_admin', 'admin', 't_lunbo_pic_delete');
INSERT INTO `t_role_menu` VALUES ('t_lunbo_pic_upload_admin', 'admin', 't_lunbo_pic_upload');
