
INSERT INTO t_menu values('t_category_list','分类管理', 'system_manager', null,'/category/list','1','是',null,null);
INSERT INTO t_menu values('t_category_update','修改分类', 't_category_list', null,'/category/update','0','是',null,null);
INSERT INTO t_menu values('t_category_look','查看分类', 't_category_list', null,'/category/look','0','是',null,null);
INSERT INTO t_menu values('t_category_export','导出分类', 't_category_list', null,'/category/list/export','0','是',null,null);
INSERT INTO t_menu values('t_category_deletemore','批量删除分类', 't_category_list', null,'/category/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_category_delete','删除分类', 't_category_list', null,'/category/delete','0','是',null,null);
INSERT INTO t_menu values('t_category_upload','导入分类', 't_category_list', null,'/category/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_category_list_admin', 'admin', 't_category_list');
INSERT INTO `t_role_menu` VALUES ('t_category_update_admin', 'admin', 't_category_update');
INSERT INTO `t_role_menu` VALUES ('t_category_look_admin', 'admin', 't_category_look');
INSERT INTO `t_role_menu` VALUES ('t_category_export_admin', 'admin', 't_category_export');
INSERT INTO `t_role_menu` VALUES ('t_category_deletemore_admin', 'admin', 't_category_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_category_delete_admin', 'admin', 't_category_delete');
INSERT INTO `t_role_menu` VALUES ('t_category_upload_admin', 'admin', 't_category_upload');
