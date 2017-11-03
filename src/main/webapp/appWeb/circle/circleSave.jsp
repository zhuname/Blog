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
	src="<%=basePath%>/js/appWeb/circle/app_circleSave.js"></script>
	
<script src="<%=basePath%>/js/appWeb/weixinjs/swiper.min.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/appWeb/css/swiper.min.css" />
<script src="<%=basePath%>/js/jquery/ajaxfileupload.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0"
	name="viewport">
<title id="showTitle1">发布城市圈</title>

<style>
body{background: #f0f2f5;}
</style>
</head>

<body>

	<div class="wraper overh">
		<div class="dis_f ali_ct jus_bt pad_20 bg_f borderbot1">
			<a  onclick="javascript:hrefIndexShare();" ><img src="<%=basePath%>/js/appWeb/images/back.png" class="dis_b" style="width:1rem;" /></a>
			<p class="f_30 clr_3" id="showTitle">发布城市圈</p>
			<a ><img src="<%=basePath%>/js/appWeb/images/fabu2.png" onclick="xinzeng();" class="dis_b" style="width:2.2rem;" /></a>
	
		</div>


	<%-- <div class="bg_f dis_f ali_ct jus_bt" style="padding:0.75rem 2.5rem;">
		<div class="dis_f ali_ct f_24 clr_3 baba">
			<img src="<%=basePath%>/js/appWeb/images/image.png" class="dis_b" style="width:1rem;" /> 
			<div style="margin:0 0.5rem;">图片</div>
			<img src="<%=basePath%>/js/appWeb/images/check_yes.png" onclick="change(1);" class="dis_b check_img" style="width:0.65rem;height:0.65rem;" /> 
		</div>

		<div class="dis_f ali_ct f_24 clr_3 baba">
			<img src="<%=basePath%>/js/appWeb/images/video2.png" class="dis_b" style="width:1rem;" /> 
			<div style="margin:0 0.5rem;">视频</div>
			<img src="<%=basePath%>/js/appWeb/images/check_no.png" onclick="change(2);" class="dis_b check_img" style="width:0.65rem;" /> 
		</div>

		<script>
			$('.check_img').click(function(){
				if($(this).attr('src').indexOf('no')>-1){
					$(this).attr('src', '<%=basePath%>/js/appWeb/images/check_yes.png');
					$(this).parents('.baba').siblings('.baba').find(".check_img").attr('src', '<%=basePath%>/js/appWeb/images/check_no.png');
				}else{
					$(this).attr('src', '<%=basePath%>/js/appWeb/images/check_no.png');
				}
			});
		</script>
	</div> --%>

	<div class="bg_f pad_20 bordertop1">
		<textarea placeholder="说点什么吧..." id="content" class="f_20 clr_9" style="width:15rem;height:4rem;border:0;"></textarea>
	</div>

	<div class="bg_f pad_30 mt_20" id="shipinShow" style="display:none;">
			<img src="<%=basePath%>/js/appWeb/images/xzsp.png" onclick="headOnc();" class="dis_b" style="width:14.5rem;" />
			<input type="text" id="mediaUrl" style="display:none">
	</div>
	
	<div class="bg_f pad_30 mt_20" id="tupianShow">
	
			<div id="imageAdd"></div>

			<div style="float: left;  width:30%; position: relative; margin-left: 0.4rem; margin-top: 0.4rem;">
				<img src="<%=basePath%>/js/appWeb/images/xztp.png"  onclick="headOnc();"  class="dis_b" style="width:4.2rem; position: relative; top: 0%; left: 0%" />
			</div>
		<div style="clear: both"></div>
	</div>

	</div>
	
	<script id="image_tmpl" type="text/x-jquery-tmpl">
			<div style="float: left;  width:30%; position: relative; margin-left: 0.4rem; margin-top: 0.4rem;">
				<img src="{{= imag}}" name="images" class="dis_b" style="width:4.2rem; position: relative; top: 0%; left: 0%" />
				<img src="<%=basePath%>/js/appWeb/images/close2.png" onclick="removeImg(this);" style="position: absolute; top: 0.1rem; right: 0.1rem; width: 0.8rem; opacity: 0.6">
			</div>
	</script>
	
	<input type="file" id="filed" name="filed" style="display:none">
	
</body>

</html>