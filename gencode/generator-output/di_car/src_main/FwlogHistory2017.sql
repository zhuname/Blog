
INSERT INTO t_menu values('t_fwlog_history_2017_list','访问日志管理', 'system_manager', null,'/fwloghistory2017/list','1','是',null,null);
INSERT INTO t_menu values('t_fwlog_history_2017_update','修改访问日志', 't_fwlog_history_2017_list', null,'/fwloghistory2017/update','0','是',null,null);
INSERT INTO t_menu values('t_fwlog_history_2017_look','查看访问日志', 't_fwlog_history_2017_list', null,'/fwloghistory2017/look','0','是',null,null);
INSERT INTO t_menu values('t_fwlog_history_2017_export','导出访问日志', 't_fwlog_history_2017_list', null,'/fwloghistory2017/list/export','0','是',null,null);
INSERT INTO t_menu values('t_fwlog_history_2017_deletemore','批量删除访问日志', 't_fwlog_history_2017_list', null,'/fwloghistory2017/delete/more','0','是',null,null);
INSERT INTO t_menu values('t_fwlog_history_2017_delete','删除访问日志', 't_fwlog_history_2017_list', null,'/fwloghistory2017/delete','0','是',null,null);
INSERT INTO t_menu values('t_fwlog_history_2017_upload','导入访问日志', 't_fwlog_history_2017_list', null,'/fwloghistory2017/upload','0','是',null,null);
INSERT INTO `t_role_menu` VALUES ('t_fwlog_history_2017_list_admin', 'admin', 't_fwlog_history_2017_list');
INSERT INTO `t_role_menu` VALUES ('t_fwlog_history_2017_update_admin', 'admin', 't_fwlog_history_2017_update');
INSERT INTO `t_role_menu` VALUES ('t_fwlog_history_2017_look_admin', 'admin', 't_fwlog_history_2017_look');
INSERT INTO `t_role_menu` VALUES ('t_fwlog_history_2017_export_admin', 'admin', 't_fwlog_history_2017_export');
INSERT INTO `t_role_menu` VALUES ('t_fwlog_history_2017_deletemore_admin', 'admin', 't_fwlog_history_2017_deletemore');
INSERT INTO `t_role_menu` VALUES ('t_fwlog_history_2017_delete_admin', 'admin', 't_fwlog_history_2017_delete');
INSERT INTO `t_role_menu` VALUES ('t_fwlog_history_2017_upload_admin', 'admin', 't_fwlog_history_2017_upload');
