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
	src="<%=basePath%>/js/appWeb/appUser/app_findPass.js"></script>

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

	<style>
		.title_top{
			background-color: #f95d47;
			color: #fff;
		}

		.bjt_0{
			background-image: url("<%=basePath%>/js/appWeb/images/nicheng.png");
			background-size:0.8rem;
			background-repeat: no-repeat;
			background-position: left;
			padding-left: 1rem;
		}

		.bjt_1{
			background-image: url("<%=basePath%>/js/appWeb/images/suo.png");
			background-size:0.8rem;
			background-repeat: no-repeat;
			background-position: left;
			padding-left: 1rem;

		}

		.bjt_2{
			background-image: url("<%=basePath%>/js/appWeb/images/zhanghao.png");
			background-size:0.8rem;
			background-repeat: no-repeat;
			background-position: left;
			padding-left: 1rem;
		}


		.yanzhengma{
			padding: 0.75rem;
			position: absolute;
			right: 0rem;
			text-align: center;
			width: 4rem;
			color: #0084d4;
			border-left: 0.05rem solid #eeeeee;
		}

	</style>
	
	<script type="text/javascript">
		var show=1;
		function showPass() {
			if(show==1){
				$("#password").attr("type","text");
				show=2;
			}else{
				$("#password").attr("type","password");
				show=1;
			}
			
		}
		</script>


	<div class="wraper overh ">
		<div class="title_top dis_f ali_ct jus_bt pad_20 bg_f borderbot1">
			<a href="#"><img src="<%=basePath%>/js/appWeb/images/back5.png" class="dis_b" style="width:0.4rem;" /></a>
			<p class="f_30 ">找回密码</p>
			<a ><img src="<%=basePath%>/js/appWeb/images/more.png" class="dis_b" style="width:1rem;visibility: hidden;" /></a>
	
		</div>


		<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt baba ">
			<input id="phonenum" class="bjt_2 ipt3 f_28 clr_9" type="text" placeholder="请输入你的手机号" />
		</div>
		<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt baba" style="position: relative">
			<input id="IdenCode" class="ipt3 f_28 clr_9" type="text" placeholder="请输入你的验证码" />
			<div class="yanzhengma f_24" id="on" onclick="settime()">发送验证码</div>
		</div>
		<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt baba " style="position: relative">
			<img onclick="showPass();" src="<%=basePath%>/js/appWeb/images/yanjing.png" style="width: 0.8rem; position: absolute; right: 1rem">
			<input id="passWord" class="bjt_1 ipt3 f_28 clr_9" type="text" placeholder="请输入你的新密码" />
		</div>

		<div class="pad_30">
			<input type="button" onclick="checkSignOn();" class="f_26 clr_9 dis_b waiting_check_a" style="background: #e3e3e6;border:0;" value="确认" />
		</div>

	</div>
</body>

</html>