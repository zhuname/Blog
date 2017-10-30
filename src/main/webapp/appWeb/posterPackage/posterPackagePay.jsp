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
	src="<%=basePath%>/js/appWeb/posterPackage/app_posterPackagePay.js"></script>
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
			<a onclick="javascript:window.history.back();" ><img src="<%=basePath%>/js/appWeb/images/back.png" class="dis_b" style="width:1rem;" /></a>
			<p class="f_30 clr_3">发布海报红包</p>
			<a ><img src="<%=basePath%>/js/appWeb/images/gantan.png" class="dis_b" style="width:0.9rem;" /></a>
	
		</div>


		<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt baba bordertop1">
			<p class="dis_f ali_ct">
			<img src="<%=basePath%>/js/appWeb/images/3-02posterPack.png" class="dis_b"  style="height:1rem;margin-right: 0.5rem;" />
			金额
			</p>
			<input class="ipt3 al_rt f_28 ph_red" id="money" type="text" placeholder="请填写海报红包金额" onkeyup="getFloatStr();" style="width:9rem;" />
		</div>
		<div class="pad_30 borderbot1 bg_f f_28 clr_3  ali_ct jus_bt baba bordertop1">
			<div style="width: 100%; display: flex;display: -webkit-flex; -webkit-justify-content:space-between;">
				<p class="dis_f ali_ct">
					<img src="<%=basePath%>/js/appWeb/images/3-02posterPackPeo.png" class="dis_b" style="height:1rem;margin-right: 0.5rem;" />
					人数
				</p> 
				<input class="ipt3 al_rt f_28 ph_red" id="lqNum" type="text" placeholder="请填写可领取人数" style="width:9rem;" />
			</div>
			<div style="width: 100%; margin-top: 0.4rem; color: #cc9d9d; font-size: 0.5rem;">
				温馨提示:<此红包为拼手气随机红包><红包金额越多，您的海报越靠前排列>《审核时间Am9:00-Pm21:00》</div>
		</div>
		<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt baba bordertop1">
			<p class="dis_f ali_ct">
				<img src="<%=basePath%>/js/appWeb/images/3-02posterPackMon.png" class="dis_b" style="height:1rem;margin-right: 0.5rem;" />
				总金额
			</p>
			<p style="color: #f95d47; font-size: 1rem;"><span style="font-size: 0.6rem">￥</span><span id="showMoney">0.00</span></p>
		</div>

		<div class="pay_type_mask1 " style="margin-top: 0.5rem">
			<div class="bg_f">
				<div class="pad_2030 borderbot1">支付方式</div>
				<div class="padl_30">
					<div class="dis_f ali_ct padt_30 borderbot1">
						<div class="dis_f ali_ct"><img src="<%=basePath%>/js/appWeb/images/wx.png" class="dis_b" style="width:1rem;margin-right:0.5rem;" />
							<div class="clr_3 f_26">微信支付</div></div>
						
					</div>

					<div class="dis_f ali_ct padt_30 borderbot1">
						<div class="dis_f ali_ct">
						<img src="<%=basePath%>/js/appWeb/images/zfb.png" class="dis_b" style="width:1rem;margin-right:0.5rem;" />
						<div class="clr_3 f_26">支付宝支付</div>
						</div>
					</div>

					<div class="dis_f ali_ct padt_30 borderbot1  jus_bt">
						<div class="dis_f ali_ct">
						<img src="<%=basePath%>/js/appWeb/images/ye.png" class="dis_b" style="width:1rem;margin-right:0.5rem;" />
						<div class="clr_3 f_26">余额支付：￥<span id="balance"></span></div>
						</div>
						<img src="<%=basePath%>/js/appWeb/images/check_yes.png" style="width: 0.8rem">
					</div>

				</div>
			</div>
		</div>

		<script>
			$('.switcher_toggle').click(function(){
				if($(this).attr('src').indexOf('guan')>-1){
					$(this).attr('src', '<%=basePath%>/js/appWeb/images/kai.png');
					$('.'+$(this).attr('myid')).show();
				}else{
					$(this).attr('src', '<%=basePath%>/js/appWeb/images/guan.png');
					$('.'+$(this).attr('myid')).hide();
				}
			});
		</script>

	<div class="pad_30">
			<input type="button" onclick="pay();" class="f_26 clr_f dis_b waiting_check_a" style="background: #f95d47;border:0;" value="下一步" />
		</div>
		
	</div>
</body>

</html>