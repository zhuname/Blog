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
	src="<%=basePath%>/js/appWeb/moneyDetail/app_tixian.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0"
	name="viewport">
<title>首页</title>
<style>
body{background: #f0f2f5;}
</style>
</head>

<body>

	<div class="wraper">
		<div class="dis_f ali_ct jus_bt pad_20 bg_f borderbot1">
			<a onclick="javascript:window.history.back();"><img src="<%=basePath%>/js/appWeb/images/back.png" class="dis_b" style="width:1rem;" /></a>
			<p class="f_30 clr_3">提现</p>
			<a ><img src="<%=basePath%>/js/appWeb/images/more.png" class="dis_b" style="width:1rem;visibility: hidden;" /></a>
	
		</div>

		<div id="showType" class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt" onclick="window.location.href='/mts/appWeb/moneyDetail/tixiansave.jsp';">
				<div>您现在未添加账户，现在添加</div>
				<img src="<%=basePath%>/js/appWeb/images/arr_he2.png" class="dis_b" style="height:0.6rem;" />
		</div>


		<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt baba mt_20">
			<div class="dis_f ali_ct">
				<img src="<%=basePath%>/js/appWeb/images/txje.png" class="dis_b" style="width:0.6rem;margin-right:0.5rem;" />
				提现金额
			</div>
			<input onkeyup="changeMoney();" class="clr_he" type="text" id="money" placeholder="可提现金额为￥0.0" style="border:0;text-align:right;width:10rem;">
			
		</div>
		<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt baba">
			<div class="dis_f ali_ct">
				<img src="<%=basePath%>/js/appWeb/images/sxf.png" class="dis_b" style="width:0.6rem;margin-right:0.5rem;" />
				手续费
			</div>
			<div class="clr_he" id="shouxu">￥0.0</div>
		</div>
		<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt baba">
			<div class="dis_f ali_ct">
				<img src="<%=basePath%>/js/appWeb/images/dzje.png" class="dis_b" style="width:0.6rem;margin-right:0.5rem;" />
				到账金额
			</div>
			<div class="clr_he" id="daozhangMon">￥0.0</div>
		</div>

				<div class="pad_30">
			<input type="button" onclick="tixian();" class="f_26 clr_f dis_b waiting_check_a" style="background: #f95d47;border:0;" value="立即提现" />
		</div>


	</div>
</body>

</html>