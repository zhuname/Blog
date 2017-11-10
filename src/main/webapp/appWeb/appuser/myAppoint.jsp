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
	src="<%=basePath%>/js/appWeb/appUser/app_myAppoint.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0"
	name="viewport">
<title>首页</title>
<style>
html,body{height:100%;}
</style>
</head>

<body>

	<div class="wraper" style="background: #f9f9fb;height:100%;">
		<div class="dis_f ali_ct jus_bt pad_20 bg_f borderbot1 bg_r">
			<a  onclick="javascript:window.history.back();" ><img src="<%=basePath%>/js/appWeb/images/back5.png" class="dis_b" style="height:1rem;" /></a>
			<p class="f_30 clr_f">我的预订</p>
			<a ><img src="<%=basePath%>/js/appWeb/images/more.png" class="dis_b more_ul_toggle" style="width:1rem;visibility: hidden;" /></a>
		</div>

		<div id="appoint"></div>

		<script id="appoint_list_tmpl" type="text/x-jquery-tmpl">
		<div class="pad_3020 ">
			<div class="pad_20 dis_f ali_top bg_f">
<div onclick="window.location.href='/mts/appWeb/appuser/otherUser.jsp?id={{if appUser}}{{= appUser.id}}{{/if}}'"  class="dis_b yy_face_img" style="margin-right:0.5rem;height:2rem;overflow: hidden;">


				<img src="{{if appUser}}{{if appUser.header}}{{= appUser.header}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}"style="width:2rem;"/>
</div>				<div class="f_24 clr_3 dis_f ali_ct jus_bt" style="width:12rem;">
					<div>
						<span class="ver_mid f_24 clr_3">{{if appUser}}{{= appUser.name}}{{/if}}</span>
						{{if appUser.sex}}
							{{if appUser.sex == '男'}}
							<img src="<%=basePath%>/js/appWeb/images/male.png" class="ver_mid" style="width:0.4rem;" />
							{{else appUser.sex == '女'}}
							<img src="<%=basePath%>/js/appWeb/images/female2.png" class="ver_mid" style="width:0.4rem;" />
							{{/if}}
						{{/if}}
						<img src="<%=basePath%>/js/appWeb/images/badge.png" class="ver_mid" style="width:0.5rem;" />
					</div>
					<div class="f_22 clr_9 mt_10">{{= createTime}}</div>
				</div>

			</div>

			<div class="f_30 clr_3 bg_f padl_20" style="padding-bottom:0.5rem;">{{= title}}</div>
			<div class="bg_f"><img src="<%=basePath%>/js/appWeb/images/kq_quan.png" class="ver_mid" style="height:0.483rem;" /></div>
			<div class="dis_f ali_ct jus_bt pad_20 bg_f">
				<div class="f_20 clr_3">兑换码：{{= cardCode}}</div>
				<div class="f_20 clr_9">预订金额：<span class="clr_ora">￥{{= money}}元</span></div>
			</div>
		</div>
		</script>


		<div class="yydh_box bg_f dis_f ali_ct jus_bt">
			<input class="ipt1 f_28 clr_9" id="cardCode" type="text" placeholder="请填写兑换码进行兑换" style="margin-left:0.5rem;">
			<input class="f_28 clr_f bg_r" type="button" onclick="yuyue();" value="预订兑换" style="height:2.5rem;border:0;width:5rem;" />
		</div>

	</div>
</body>

</html>