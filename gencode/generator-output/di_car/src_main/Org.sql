
INSERT INTO t_menu values('t_org_list','部门管理', 'system_manager', null,'/org/list','1','是',null,null);
INSERT INTO t_menu values('t_org_update','修改部门', 't_org_list', null,'/org/update','0','是',null,null);
INSERT INTO t_menu values('t_org_look','查看部门', 't_org_list', null,'/org/look','0','是',null,null);
INSERT INTO t_menu values('t_org_export','导出部门', 't_org_list', null,'/org/list/export','0','是',null,null);
INSERT INTO t_menu values('t_org_deletemore','批量删除部门', 't_org_list', null,'/org/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_org_delete','删除部门', 't_org_list', null,'/org/delete','0','是',null,null);
INSERT INTO t_menu values('t_org_upload','导入部门', 't_org_list', null,'/org/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_org_list_admin', 'admin', 't_org_list');
INSERT INTO `t_role_menu` VALUES ('t_org_update_admin', 'admin', 't_org_update');
INSERT INTO `t_role_menu` VALUES ('t_org_look_admin', 'admin', 't_org_look');
INSERT INTO `t_role_menu` VALUES ('t_org_export_admin', 'admin', 't_org_export');
INSERT INTO `t_role_menu` VALUES ('t_org_deletemore_admin', 'admin', 't_org_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_org_delete_admin', 'admin', 't_org_delete');
INSERT INTO `t_role_menu` VALUES ('t_org_upload_admin', 'admin', 't_org_upload');
