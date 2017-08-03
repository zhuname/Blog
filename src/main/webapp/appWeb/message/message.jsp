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
	src="<%=basePath%>/js/appWeb/message/app_message.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0"
	name="viewport">
<title>我的消息</title>




</head>

<body>

	<div class="wraper" style="background: #f9f9fb;height:100%;" id="messageList">
		<div class="dis_f ali_ct jus_bt pad_20 bg_f borderbot1">
			<img src="<%=basePath%>/js/appWeb/images/back.png"  onclick="javascript:window.history.back();"  class="dis_b" style="width:1rem;" />
			<p class="f_30 clr_3">通知消息</p>
			<a ><img src="<%=basePath%>/js/appWeb/images/more.png" class="dis_b more_ul_toggle" style="width:1rem;visibility: hidden;" /></a>
	
		</div>


	</div>
	
	
	<script id="message_list_tmpl" type="text/x-jquery-tmpl">
		<div class="pad_30 bg_f borderbot1">
			<div class=" dis_f ali_ct jus_bt">
				<div class="f_26 clr_3 dis_f ali_ct">
					{{if pushType }}
					{{if pushType == 2 || pushType == 3|| pushType == 8|| pushType == 9|| pushType == 15|| pushType == 16|| pushType == 17|| pushType == 19|| pushType == 10|| pushType == 25|| pushType ==26|| pushType ==28|| pushType ==29|| pushType ==36|| pushType ==37|| pushType ==42|| pushType ==43 }}
				
						<img src="<%=basePath%>/js/appWeb/images/msg_red.png" class="dis_b" style="width:1rem;margin-right:0.3rem;" />
					{{else pushType == 4||pushType ==5||pushType ==6||pushType ==18||pushType ==20||pushType ==11||pushType ==13||pushType ==14}}
						<img src="<%=basePath%>/js/appWeb/images/msg_card.png" class="dis_b" style="width:1rem;margin-right:0.3rem;" />
					{{else pushType == 21||pushType ==22||pushType ==23||pushType ==24||pushType ==27||pushType ==31||pushType ==32}}
						<img src="<%=basePath%>/js/appWeb/images/msg_activity.png" class="dis_b" style="width:1rem;margin-right:0.3rem;" />
					{{else pushType == 33||pushType ==34}}
						<img src="<%=basePath%>/js/appWeb/images/msg_city_circle.png" class="dis_b" style="width:1rem;margin-right:0.3rem;" />
					{{else pushType == 1||pushType ==38||pushType ==39||pushType ==40||pushType ==7||pushType ==13||pushType ==35||pushType ==41}}
						<img src="<%=basePath%>/js/appWeb/images/msg_sys.png" class="dis_b" style="width:1rem;margin-right:0.3rem;" />
					{{else}}
						<img src="<%=basePath%>/js/appWeb/images/msg_sys.png" class="dis_b" style="width:1rem;margin-right:0.3rem;" />
					{{/if}}
					{{/if}}
					{{= name}}
				</div>
				<div class="f_22 clr_3">{{= createTime}}</div>
			</div>
			<div class="mt_20 f_24 clr_he bg_f">
				{{= content}}
			</div>
		</div>
	</script>
	
</body>


</html>
