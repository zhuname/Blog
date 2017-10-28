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
	src="<%=basePath%>/js/appWeb/appUser/app_myCircle.js"></script>
	
<script src="<%=basePath%>/js/appWeb/weixinjs/swiper.min.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/appWeb/css/swiper.min.css" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0" name="viewport">
<title>首页</title>
</head>

<body>

	<div class="wraper" style="background: #f9f9fb;height:100%;">
		<div class="dis_f ali_ct jus_bt pad_20 bg_f borderbot1">
			<a  onclick="javascript:window.history.back();"><img src="<%=basePath%>/js/appWeb/images/back.png" class="dis_b" style="width:1rem;" /></a>
			<p class="f_30 clr_3">我的动态</p>
			<a ><img src="<%=basePath%>/js/appWeb/images/more.png" class="dis_b more_ul_toggle" style="width:1rem;visibility: hidden;" /></a>
	
		</div>
		
		
		<div id="circle"></div>
		
		<script id="circle_list_tmpl" type="text/x-jquery-tmpl">
				<div class="dis_f jus_bt pad_2030 bg_f"  onclick="locationHref({{= id}});" style="padding-bottom:0;">
			<div class="f_38 clr_6">
				{{= d}}<p class="f_22">{{= m}}月</p>
			</div>

			<div class="mt_10" style="width:90%;">
				<div class="f_22 clr_6">{{= t}} 发布动态</div>
				<div class="clr_3 f_28 mt_10" >{{= content}}</div>
				<div class="dis_f ali_ct  flex_w quan_img mt_20">
					{{if images}}
						{{each images}}
								<img src="{{= $value}}" class="ver_mid" style="width:4rem;height:4rem;" />
   						 {{/each}}
						{{/if}}
				</div>

				<div class="dis_f jus_rt mt_20 hrs" style="position:relative;">
					<div class="dis_f ali_ct jus_bt padt_20" style="width:9rem;">
						{{if sumMoney}}
						<div>
							<img src="<%=basePath%>/js/appWeb/images/ds.png" class="ver_mid" style="width:0.8rem;" />
							<span class="f_20 clr_6 ver_mid">打赏<span class="f_24 clr_r">￥{{= sumMoney}}</span></span>
						</div>
						{{else}}	
							<div style="width:2rem;"></div>
						{{/if}}
						<div>
							<img src="<%=basePath%>/js/appWeb/images/xx.png" class="ver_mid" style="height:0.7rem;" />
							<span class="f_20 clr_6 ver_mid">{{= commentCount}}</span>
						</div>
						<div>
							{{if topCount==1}}
								<img src="<%=basePath%>/js/appWeb/images/zan.png" class="ver_mid" style="height:0.75rem;" />
								<span class="f_20 clr_r ver_mid ">{{= topCount}}</span>
							{{else}}
								<div id="zan{{= id}}" style="display:none;">
								<img src="<%=basePath%>/js/appWeb/images/zan.png" class="ver_mid" style="height:0.75rem;" />
								<span class="f_20 clr_r ver_mid " id="topCount{{= id}}">{{= topCount}}</span>
								</div>
								<div id="zanShow{{= id}}">
								<img onclick="zan({{= id}});" src="<%=basePath%>/js/appWeb/images/zan2.png" class="ver_mid" style="height:0.75rem;" />
								<span class="f_20 clr_6 ver_mid ">{{= topCount}}</span>
								</div>
							{{/if}}
						</div>
					</div>
				</div>
			</div>
		</div>
		</script>
		

	</div>
</body>

</html>