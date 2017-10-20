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
	src="<%=basePath%>/js/appWeb/mediaPackage/app_mediaPackageCards.js"></script>
	
<script src="<%=basePath%>/js/appWeb/weixinjs/swiper.min.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/appWeb/css/swiper.min.css" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0"
	name="viewport">
<title>首页</title>
<style>
body{background: #f9f9fb;}
</style>
</head>

<body>

	<div class="wraper overh">
		<div class="dis_f ali_ct jus_bt pad_20 bg_f borderbot1">
			<a onclick="javascript:window.history.back();" ><img src="<%=basePath%>/js/appWeb/images/back.png" class="dis_b" style="width:1rem;" /></a>
			<p class="f_30 clr_3">选择卡券</p>
			<a ><img src="<%=basePath%>/js/appWeb/images/qd.png" onclick="checkSuccess();" class="dis_b" style="width:2.2rem;" /></a>
		</div>
		<div id="list">
		</div>
		<script id="card_list_tmpl" type="text/x-jquery-tmpl">
			<div class="pad_30 dis_f ali_ct jus_bt baba">
			<img id="{{= id}}" src="<%=basePath%>/js/appWeb/images/no.png" style="width:1rem;" onclick="check({{= id}},'{{= title}}')"; name="image" class="dis_b switcher_click" />

			<div  style="width:13rem;">
				<div class="pad_20 dis_f ali_top bg_f">
					<img src="{{if appUser}}{{if appUser.header}}{{= appUser.header}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}" class="dis_b yy_face_img" style="margin-right:0.5rem;"/>
					<div class="f_24 clr_3 dis_f ali_ct jus_bt" style="width:10rem;">
						<div>
							<span class="ver_mid f_24 clr_3">{{if appUser.name}}{{= appUser.name}}{{/if}}</span>
							{{if appUser.sex}}
							{{if appUser.sex == '男'}}
							<img src="<%=basePath%>/js/appWeb/images/male.png" class="ver_mid" style="width:0.4rem;" />
							{{else appUser.sex == '女'}}
							<img src="<%=basePath%>/js/appWeb/images/female2.png" class="ver_mid" style="width:0.4rem;" />
							{{/if}}
							{{/if}}
							{{if appUser.userMedals}}
							{{each appUser.userMedals}}
							<img src="{{= $value.medal.image}}" class="ver_mid" style="width:0.5rem;" />
   							{{/each}}
							{{/if}}
						</div>
						<div class="f_18 clr_he mt_10 dis_f ali_ct">
							<img src="<%=basePath%>/js/appWeb/images/quan.png" class="ver_mid" style="width:0.45rem;margin-right: 0.2rem;" />
							{{= num}}
						</div>
					</div>
				</div>
				<div class="f_26 clr_3 bg_f padl_20" style="padding-bottom:0.5rem;">{{= title}}</div>
<div class="bg_f"><img src="<%=basePath%>/js/appWeb/images/kq_quan2.png" class="ver_mid" style="width:13rem;" /></div>
				<div class="dis_f ali_ct jus_bt pad_20 bg_f">
					<div class="f_18 clr_he dis_f ali_ct">
						<img src="<%=basePath%>/js/appWeb/images/jzsj.png" class="ver_mid" style="width:0.4rem;margin-right: 0.2rem;" />
						2017-02-01止
					</div>
					<div class="f_20 clr_r">{{if convertMoney}}{{= convertMoney}}{{else}}免费{{/if}}</div>
				</div>
			</div>
		</div>
	</script>




	</div>
</body>

</html>