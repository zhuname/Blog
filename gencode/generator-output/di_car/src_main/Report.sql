
INSERT INTO t_menu values('t_report_list','举报表管理', 'system_manager', null,'/report/list','1','是',null,null);
INSERT INTO t_menu values('t_report_update','修改举报表', 't_report_list', null,'/report/update','0','是',null,null);
INSERT INTO t_menu values('t_report_look','查看举报表', 't_report_list', null,'/report/look','0','是',null,null);
INSERT INTO t_menu values('t_report_export','导出举报表', 't_report_list', null,'/report/list/export','0','是',null,null);
INSERT INTO t_menu values('t_report_deletemore','批量删除举报表', 't_report_list', null,'/report/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_report_delete','删除举报表', 't_report_list', null,'/report/delete','0','是',null,null);
INSERT INTO t_menu values('t_report_upload','导入举报表', 't_report_list', null,'/report/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_report_list_admin', 'admin', 't_report_list');
INSERT INTO `t_role_menu` VALUES ('t_report_update_admin', 'admin', 't_report_update');
INSERT INTO `t_role_menu` VALUES ('t_report_look_admin', 'admin', 't_report_look');
INSERT INTO `t_role_menu` VALUES ('t_report_export_admin', 'admin', 't_report_export');
INSERT INTO `t_role_menu` VALUES ('t_report_deletemore_admin', 'admin', 't_report_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_report_delete_admin', 'admin', 't_report_delete');
INSERT INTO `t_role_menu` VALUES ('t_report_upload_admin', 'admin', 't_report_upload');
