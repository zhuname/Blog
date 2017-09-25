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
	src="<%=basePath%>/js/appWeb/posterPackage/app_posterPackageSave.js"></script>
	<script src="<%=basePath%>/js/jquery/ajaxfileupload.js"></script>
	
	
	
	
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

	<div class="wraper overh">
		<div class="dis_f ali_ct jus_bt pad_20 bg_f borderbot1">
			<a  onclick="javascript:window.history.back();" ><img src="<%=basePath%>/js/appWeb/images/back.png" class="dis_b" style="width:1rem;" /></a>
			<p class="f_30 clr_3">发布海报红包</p>
			<a ><img src="<%=basePath%>/js/appWeb/images/gantan.png" class="dis_b" style="width:0.9rem;" /></a>
		</div>

 	<input type="file" id="filed" name="filed" style="display:none">

	<div class="bg_f pad_30 dis_f ali_ct jus_bt" id="images">
		<a ><img src="<%=basePath%>/js/appWeb/images/upload.png" onclick="headOnc();" class="dis_b waitCheck"  style="width:4.5rem;" /></a>
	</div>

	<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt baba bordertop1">
			<p class="dis_f ali_ct">
			<img src="<%=basePath%>/js/appWeb/images/zt.png" class="dis_b" style="height:1rem;margin-right: 0.5rem;" />
			主题
			</p>
			<input class="ipt3 al_rt f_28 ph_red" id="title" type="text" placeholder="请填写海报主题，20字以内" style="width:9rem;" />
		</div>


<div class="pad_2030 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt">
	<textarea placeholder="请填写海报简介" id="descr" class="f_24 clr_9 pad_20" style="width:15rem;height:3rem;border:0;background: #f0f2f5;"></textarea>
</div>

<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt">
	<p class="dis_f ali_ct">
		<img src="<%=basePath%>/js/appWeb/images/fl.png" class="dis_b" style="height:1rem;margin-right: 0.5rem;" />
		分类
	</p>
	<p class="dis_f ali_ct clr_ph_red"  onclick="window.location.href='/mts/appWeb/posterPackage/posterPackageCategory.jsp';">
		<span id="categoryId">请选择海报分类</span>
		<img src="<%=basePath%>/js/appWeb/images/arr_he2.png" class="dis_b" style="height:0.6rem;margin-left: 0.5rem;" />
	</p>
</div>


<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt">
	<p class="dis_f ali_ct">
		<img src="<%=basePath%>/js/appWeb/images/cs.png" class="dis_b" style="height:1rem;margin-right: 0.5rem;" />
		城市
	</p>
	<p class="dis_f ali_ct clr_ph_red"  onclick="window.location.href='/mts/appWeb/posterPackage/posterPackageCity.jsp';">
		<span id="cityIds">请选择海报投放城市</span>
		<img src="<%=basePath%>/js/appWeb/images/arr_he2.png" class="dis_b" style="height:0.6rem;margin-left: 0.5rem;" />
	</p>
</div>


<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt">
	<p class="dis_f ali_ct">
		<img src="<%=basePath%>/js/appWeb/images/kq.png" class="dis_b" style="height:1rem;margin-right: 0.5rem;" />
		卡券
	</p>

	<img id="cardImg" src="<%=basePath%>/js/appWeb/images/guan.png" class="dis_b switcher_toggle" onclick="onCard();" style="height:1rem;" />

</div>

<div id="cardShow" style="display:none;" class="pad_2030 f_24 clr_he2 bg_f dis_f ali_ct jus_bt borderbot1 kq_open">
	<p class="dis_f ali_ct"><img src="<%=basePath%>/js/appWeb/images/xjq2.png" class="dis_b clr_he2" style="width:0.7rem;margin-right: 0.5rem;" /> 现金券</p>
	<p class="dis_f ali_ct"  onclick="window.location.href='/mts/appWeb/posterPackage/posterPackageCards.jsp';">
		
		<span id="cardId">这是卡券的主题券的主题券的...</span>
		<img src="<%=basePath%>/js/appWeb/images/arr_he2.png" class="dis_b" style="height:0.6rem;margin-left: 0.5rem;" />
	</p>

</div>

<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt">
	<p class="dis_f ali_ct">
		<img src="<%=basePath%>/js/appWeb/images/yy.png" class="dis_b" style="height:1rem;margin-right: 0.5rem;" />
		预约
	</p>

	<img id="yuyueImg" src="<%=basePath%>/js/appWeb/images/guan.png" class="dis_b switcher_toggle" onclick="onYuyue();" style="height:1rem;" />

</div>

<div id="yuyueShow" style="display:none;" class="bg_f pad_2030 clr_he2 borderbot1 yuyue_open f_22">


<input class="ipt3 al_rt f_28 ph_red" type="text" id="appointExplain" placeholder="请填写预定说明或预定优惠信息，客户将在线支付预定款进行预订购买。" style="width:100%;" />
</div>

<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt">
	<p class="dis_f ali_ct">
		<img src="<%=basePath%>/js/appWeb/images/jm.png" class="dis_b" style="height:1rem;margin-right: 0.5rem;" />
		加密
	</p>

	<img id="encryptImg" src="<%=basePath%>/js/appWeb/images/guan.png" class="dis_b switcher_toggle" onclick="onEncrypt();" style="height:1rem;" />

</div>


<div id="encryptShow" style="display:none;"  class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt baba bordertop1">
			<p class="dis_f ali_ct">
			<img src="<%=basePath%>/js/appWeb/images/kl.png" class="dis_b" style="height:1rem;margin-right: 0.5rem;" />
			口令
			</p>
			<input  id="command"  class="ipt3 al_rt f_28 ph_red" type="text" placeholder="请填写海报口令" style="width:9rem;" />
		</div>

		<div class="pad_30">
			<input type="button" onclick="save();" class="f_26 clr_f dis_b waiting_check_a" style="background: #f95d47;border:0;" value="下一步" />
		</div>
	</div>
	
	<script id="images_update_tmpl" type="text/x-jquery-tmpl">
		<a ><img src="<%=basePath%>/js/appWeb/images/upload.png" onclick="headOnc();" class="dis_b waitCheck" style="width:4.5rem;" /></a>
	</script>
	
	<script type="text/javascript">
	
	init();
	
	</script>
	
</body>

</html>