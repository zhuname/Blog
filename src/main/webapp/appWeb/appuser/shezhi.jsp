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
	src="<%=basePath%>/js/appWeb/appUser/app_appUserShezhi.js"></script>
	
<script src="<%=basePath%>/js/appWeb/weixinjs/swiper.min.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/appWeb/css/swiper.min.css" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0" name="viewport">
<title>首页</title>
<style>
body{background: #f0f2f5;}
</style>
</head>

<body>

	<div class="wraper overh ">
		<div class="dis_f ali_ct jus_bt pad_20 bg_f borderbot1">
			<a onclick="javascript:window.history.back();"><img src="<%=basePath%>/js/appWeb/images/back.png" class="dis_b" style="width:1rem;" /></a>
			<p class="f_30 clr_3">设置</p>
			<a><img src="<%=basePath%>/js/appWeb/images/more.png" class="dis_b" style="width:1rem;visibility: hidden;" /></a>
		</div>
		
		<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt baba mt_20">
			<div class="dis_f ali_ct"><img src="<%=basePath%>/js/appWeb/images/g1.png" class="dis_b" style="height:0.8rem;margin-right: 0.3rem;" />修改密码</div>
			<img src="<%=basePath%>/js/appWeb/images/arr_he2.png" class="dis_b" style="height:0.6rem;" />
		</div>

		<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt baba mt_20">
		<div class="dis_f ali_ct"><img src="<%=basePath%>/js/appWeb/images/g3.png" class="dis_b" style="height:0.8rem;margin-right: 0.3rem;" />关于我们</div>
			
			<img src="<%=basePath%>/js/appWeb/images/arr_he2.png" class="dis_b" style="height:0.6rem;" />
		</div>

		<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt baba">
		<div class="dis_f ali_ct"><img src="<%=basePath%>/js/appWeb/images/g4.png" class="dis_b" style="height:0.8rem;margin-right: 0.3rem;" />意见反馈</div>
			
			<img src="<%=basePath%>/js/appWeb/images/arr_he2.png" class="dis_b" style="height:0.6rem;" />
		</div>

				<div class="pad_30">
			<input onclick="logOff();" type="button" class="f_26 clr_f dis_b waiting_check_a" style="background: #f95d47;border:0;" value="退出登录" />
		</div>

		<script>
			$('.switcher_toggle').click(function(){
				if($(this).attr('src').indexOf('kai')>-1){
					$(this).attr('src', '<%=basePath%>/js/appWeb/images/guan.png');
				}else{
					$(this).attr('src', '<%=basePath%>/js/appWeb/images/kai.png');
				}
			});
		</script>


	</div>
</body>

</html>