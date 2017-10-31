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
	src="<%=basePath%>/js/appWeb/appUser/app_appUserChongzhi.js"></script>

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

	<div class="wraper overh ">
		<div class="dis_f ali_ct jus_bt pad_20 bg_f borderbot1">
			<a href="#"><img src="<%=basePath%>/js/appWeb/images/back.png" class="dis_b" style="width:1rem;" /></a>
			<p class="f_30 clr_3">充值</p>
			<a ><img src="<%=basePath%>/js/appWeb/images/more.png" class="dis_b" style="width:1rem;visibility: hidden;" /></a>
		</div>

		<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt">
			<div class="dis_f ali_ct">
				<img src="<%=basePath%>/js/appWeb/images/cz.png" class="dis_b" style="width:0.7rem;margin-right:0.5rem;" />
				充值金额
			</div>
			<input class="ipt3 f_28 clr_he al_rt" id="money" placeholder="请填写您充值的金额" type="number" />
		</div>
		
		<div class="bg_f pad_30 f_28 clr_3 mt_20 borderbot1">支付方式</div>
<div>
		<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt baba" id="wxShow">
			<div class="dis_f ali_ct">
				<img src="<%=basePath%>/js/appWeb/images/wx3.png" class="dis_b" style="width:1rem;margin-right:0.5rem;" />
				微信
			</div>
			<img src="<%=basePath%>/js/appWeb/images/check_yes.png" payType="1" class="dis_b check_toggle check" style="width:0.7rem;" />
			
		</div>
		<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt baba" id="zfbShow">
			<div class="dis_f ali_ct">
				<img src="<%=basePath%>/js/appWeb/images/zfb3.png" class="dis_b" style="width:1rem;margin-right:0.5rem;" />
				支付宝
			</div>
			<img src="<%=basePath%>/js/appWeb/images/check_no.png" class="dis_b check_toggle"  payType="2" style="width:0.7rem;" />
			
		</div>
</div>
		<script>
			var payType=1;
			$('.check_toggle').click(function(){
				if($(this).attr('src').indexOf('no')>-1){
					$(this).attr('src', '<%=basePath%>/js/appWeb/images/check_yes.png');
					$(this).attr('class', 'dis_b check_toggle check');
					payType=$(this).attr('payType');

					$(this).parents('.baba').siblings('.baba').find('.check_toggle').attr('src', '<%=basePath%>/js/appWeb/images/check_no.png');
					$(this).parents('.baba').siblings('.baba').find('.check_toggle').attr('class', 'dis_b check_toggle');
				}
			});
		</script>


		<div class="pad_30">
			<input onclick="chongzhi();" type="button" class="f_26 clr_f dis_b waiting_check_a" style="background: #f95d47;border:0;" value="立即充值" />
		</div>

	</div>
</body>

</html>