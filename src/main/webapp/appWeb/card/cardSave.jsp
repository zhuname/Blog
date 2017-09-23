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
	src="<%=basePath%>/js/appWeb/card/app_cardSave.js"></script>
<script src="<%=basePath%>/js/appWeb/weixinjs/swiper.min.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/appWeb/css/swiper.min.css" />

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
			<a onclick="javascript:window.history.back();" ><img src="<%=basePath%>/js/appWeb/images/back.png" class="dis_b" style="width:1rem;" /></a>
			<p class="f_30 clr_3">发布卡券</p>
			<a ><img src="<%=basePath%>/js/appWeb/images/gantan.png" class="dis_b" style="width:0.9rem;" /></a>
		</div>
		
	<div class="bg_f pad_30">
		<a href="#" ><img src="<%=basePath%>/js/appWeb/images/kq_up.png" class="dis_b" style="width:14.5rem;" /></a>
	</div>

<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt baba bordertop1">
			<p class="dis_f ali_ct">
			<img src="<%=basePath%>/js/appWeb/images/zt.png" class="dis_b" style="height:1rem;margin-right: 0.5rem;" />
			标题
			</p>
			<input class="ipt3 al_rt f_28 ph_red" type="text" placeholder="请填写卡券主题，20字以内" style="width:9rem;" />
		</div>


<div class="pad_2030 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt">
	<textarea placeholder="请填写卡券简介" class="f_24 clr_9 pad_20" style="width:15rem;height:3rem;border:0;background: #f0f2f5;"></textarea>
</div>

<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt">
	<p class="dis_f ali_ct">
		<img src="<%=basePath%>/js/appWeb/images/fl2.png" class="dis_b" style="height:1rem;margin-right: 0.5rem;" />
		分类
	</p>
	<p class="dis_f ali_ct clr_ph_red">
		请选择卡券分类
		<img src="<%=basePath%>/js/appWeb/images/arr_he2.png" class="dis_b" style="height:0.6rem;margin-left: 0.5rem;" />
	</p>
</div>


<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt">
	<p class="dis_f ali_ct">
		<img src="<%=basePath%>/js/appWeb/images/je.png" class="dis_b" style="height:1rem;margin-right: 0.5rem;" />
		金额
	</p>
	<input class="ipt3 al_rt clr_9 f_28 ph_red" type="text" placeholder="不填写金额则用户可以免费领取" style="width:10rem;" />
</div>


<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt">
	<p class="dis_f ali_ct">
		<img src="<%=basePath%>/js/appWeb/images/sl.png" class="dis_b" style="height:1rem;margin-right: 0.5rem;" />
		数量
	</p>
	<input class="ipt3 al_rt clr_9 f_28 ph_red" type="text" placeholder="请填写允许领取总数量" />
</div>

<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt">
	<p class="dis_f ali_ct">
		<img src="<%=basePath%>/js/appWeb/images/xl.png" class="dis_b" style="height:1rem;margin-right: 0.5rem;" />
		限领
	</p>
	<input class="ipt3 al_rt clr_9 f_28 ph_red" type="text" placeholder="请填写每人限领数量" />
</div>

<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt">
	<p class="dis_f ali_ct">
		<img src="<%=basePath%>/js/appWeb/images/rq.png" class="dis_b" style="height:1rem;margin-right: 0.5rem;" />
		日期
	</p>
	<p class="dis_f ali_ct clr_ph_red">
		请选择截止日期
		<img src="<%=basePath%>/js/appWeb/images/arr_he2.png" class="dis_b" style="height:0.6rem;margin-left: 0.5rem;" />
	</p>
</div>
<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt">
	<p class="dis_f ali_ct">
		<img src="<%=basePath%>/js/appWeb/images/cs2.png" class="dis_b" style="height:1rem;margin-right: 0.5rem;" />
		城市
	</p>
	<p class="dis_f ali_ct clr_ph_red">
		选择况换点所在城市
		<img src="<%=basePath%>/js/appWeb/images/arr_he2.png" class="dis_b" style="height:0.6rem;margin-left: 0.5rem;" />
	</p>
</div>

<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt">
	<p class="dis_f ali_ct">
		<img src="<%=basePath%>/js/appWeb/images/dz2.png" class="dis_b" style="height:1rem;margin-right: 0.5rem;" />
		地址
	</p>
	<input class="ipt3 al_rt clr_9 f_28 ph_red" type="text" placeholder="请填写用户使用地址" />
</div>

<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt">
	<p class="dis_f ali_ct">
		<img src="<%=basePath%>/js/appWeb/images/dw.png" class="dis_b" style="height:1rem;margin-right: 0.5rem;" />
		定位
	</p>
	<p class="dis_f ali_ct clr_ph_red">
		点击进行地图定位
	</p>
</div>

<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt">
	<p class="dis_f ali_ct">
		<img src="<%=basePath%>/js/appWeb/images/dh.png" class="dis_b" style="height:1rem;margin-right: 0.5rem;" />
		电话
	</p>

	<input class="ipt3 al_rt clr_9 f_28 ph_red" type="text" placeholder="请填写联系电话" />
</div>

	<div class="pad_30">
			<input type="button" class="f_26 clr_f dis_b waiting_check_a" style="background: #f95d47;border:0;" value="发布卡券" />
		</div>
		
	</div>
</body>

</html>