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

<script type="text/javascript"
	src="<%=basePath%>/js/appWeb/weixinjs/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/js/appWeb/weixinjs/TouchSlide.1.1.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/js/appWeb/weixinjs/global_phone.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/js/appWeb/weixinjs/jquery.tmpl.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/js/appWeb/css/css.css" />
<script type="text/javascript"
	src="<%=basePath%>/js/appWeb/appUser/app_myActivity.js"></script>
	
<script src="<%=basePath%>/js/appWeb/weixinjs/swiper.min.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/appWeb/css/swiper.min.css" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0" name="viewport">
<title>首页</title>
</head>

<body>

	<div class="wraper">

			<div class="dis_f ali_ct jus_bt pad_20 bg_f borderbot1">
				<a  onclick="javascript:window.history.back();"><img src="<%=basePath%>/js/appWeb/images/back.png" class="dis_b" style="width:1rem;" /></a>
				<p class="f_30 clr_3">我的参与</p>
				<a ><img src="<%=basePath%>/js/appWeb/images/more.png" class="dis_b more_ul_toggle" style="width:1rem;visibility: hidden;" /></a>
		
			</div>

			<div id="activity"></div>
		
		<script id="activity_list_tmpl" type="text/x-jquery-tmpl">
				<div class="mt_10 pos_rela"  onclick="window.location.href='/mts/appWeb/activity/activityDetail.jsp?id={{= id}}'";>
				{{if type==1}}
				<img src="{{= image}}" class="ver_mid" style="width:16rem;height:8rem;" />
				{{else}}
				<img src="<%=basePath%>/js/appWeb/images/play.png" class="dis_b play_img" style="width:2rem;" />
				<img src="{{= mediaUrl}}" class="ver_mid" style="width:16rem;height:8rem;" />
				{{/if}}
				{{if status==4}}
				<img src="<%=basePath%>/js/appWeb/images/over.png" class="dis_b end_img" style="width:3rem;" />
				{{/if}}
				<div class="f_28 clr_f tc_title padl_30 dis_f ali_ct">
					<img src="<%=basePath%>/js/appWeb/images/bar.png" class="dis_b" style="width:0.1rem;margin-right:0.5rem;" />
					<p>{{= content}}</p>
				</div>
				<div class="bg_f pad_20 dis_f ali_ct jus_bt">
					<div style="width:9.5rem;" class="dis_f ali_ct jus_bt">
						<div><img src="<%=basePath%>/js/appWeb/images/eye4.png" class="ver_mid" style="height:0.4rem;" />
						<span class="f_20 clr_6 ver_mid">{{= viewedCount}}</span></div>
						<div><img src="<%=basePath%>/js/appWeb/images/user2.png" class="ver_mid" style="height:0.45rem;" />
						<span class="f_20 clr_6 ver_mid">{{= joinCount}}人参与</span></div>
						<div><img src="<%=basePath%>/js/appWeb/images/cup.png" class="ver_mid" style="height:0.55rem;" />
						<span class="f_20 clr_6 ver_mid">{{= winPrizePerson}}人中奖</span></div>
					</div>
					
					<div>
						<img src="<%=basePath%>/js/appWeb/images/lou.png" class="ver_mid" style="height:0.4rem;" />
						<span class="f_20 clr_6 ver_mid">{{= endTime}}止</span>
					</div>
				</div>
			</div>
		</script>


	</div>
	
</body>

</html>