<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Object data=session.getAttribute("data");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<meta http-equiv="Cache-Control" content="no-transform" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta name="format-detection" content="telephone=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">  
<meta name="app-mobile-web-app-capable" content="yes">  
<meta name="viewport"
	content="width=device-width,target-densitydpi=high-dpi,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<script type="text/javascript" src="<%=basePath%>/js/appWeb/weixinjs/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/appWeb/activity/map.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/appWeb/weixinjs/jquery.tmpl.min.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html,#allmap {width: 100%;height: 100%; margin:0;font-family:"微软雅黑";}
		#l-map{height:300px;width:100%;}
		#r-result{width:100%;}
	</style>
	
	 <style>
        div,ul,li,p,span,b,em,label,img,body{
            padding:0;
            margin:0;
            border:0;
            list-style: none;
            font-style: normal;
            box-sizing: border-box;
        }
        .box{
            width: 100%;
            height: 6.2rem;
            background: #ddd;
        }
        .mapTxt{
            max-width: 7.5rem;
            min-width: 3.2rem;
        }
        .mapTxt ul li{
            height: 1.37rem;
            padding:.18rem 0 0 .2rem;
            border-bottom: 1px solid #e0e0e0;
        }
        .mapTxt ul li img{
            width: .50rem;
            height: .50rem;
            margin-top:.24rem;
            float: left;
        }
        .mapTxt .txtR{
            float: left;
            margin-left: .21rem;
        }
        .txtR p:nth-of-type(1){
            line-height: .3rem;
            font-size: .3rem;
            color: #444444;
        }
        .txtR p:nth-of-type(2){
            margin-top:.39rem;
            line-height: .24rem;
            font-size: .25rem;
            color: #444444;
        }
    </style>
	
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=NgMZRcGV4cz2tc9tvQD7M5VkgB7gUT3g"></script>
	<title>根据多关键字本地搜索</title>
</head>
<body>
	<div id="allmap"></div>
</body>
</html>

<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("allmap");
	map.centerAndZoom(new BMap.Point(getQueryString("log"),getQueryString("lat")),16);
	map.enableScrollWheelZoom(true);
	
	// 用经纬度设置地图中心点

	if(getQueryString("log") != "" && getQueryString("lat") != ""){
		map.clearOverlays(); 
		var new_point = new BMap.Point(getQueryString("log"),getQueryString("lat"));
		var marker = new BMap.Marker(new_point);  // 创建标注
		map.addOverlay(marker);              // 将标注添加到地图中
		map.panTo(new_point);      
	}
		
	function getQueryString(aaa) { 
		var reg = new RegExp("(^|&)" + aaa + "=([^&]*)(&|$)", "i"); 
		var r = window.location.search.substr(1).match(reg); 
		if (r != null) return unescape(r[2]); return null; 
	} 
</script>