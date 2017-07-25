<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>测试页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
        <meta http-equiv="Cache-Control" content="no-transform" />
        <meta http-equiv="Cache-Control" content="no-siteapp" />
        <meta  name="keywords" content="定客"/>
        <meta  name="description"  content="定客"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"/>
        <meta http-equiv="Cache-Control" content="no-cache, must-revalidate"/>
        <meta http-equiv="Pragma" content="no-cache"/>
        <meta name="format-detection" content="telephone=no"/>
        <meta name="apple-mobile-web-app-capable" content="yes"/>
        <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
        
        <meta   name="viewport" content="width=device-width,target-densitydpi=high-dpi,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"  />   
        
         <script src="<%=basePath%>/js/coms/ajaxfileupload/jquery.js"></script>
        <script src="<%=basePath%>/js/jquery/jquery.form.js"></script>
		<script src="<%=basePath%>/js/jquery/ajaxfileupload.js"></script>
        <script type="text/javascript" src="<%=basePath%>/js/appWeb/appUser/app_appUser.js"></script>

  </head>
  
  <body>
    微信登陆测试页面 <br>
  </body>
</html>
