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
	src="<%=basePath%>/js/appWeb/appUser/app_myCity.js"></script>
	
<script src="<%=basePath%>/js/appWeb/weixinjs/swiper.min.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/appWeb/css/swiper.min.css" />

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

	<style>
		.clearfix{ clear: both}

		.box22{
			background-color: #e9e9e9;
		}

		.wancheng{
			color: #333333;
		}

		.top_title1{
			position: relative;
		}

		.top_title1 .title_bt{
			display: block;
			color: #f84848;
			line-height: 1.2rem;
			padding: 0rem 2rem 0rem 0.5rem;
		}

		.top_title1 .title_content{
			background-color: #ffffff;
			display: block;
			padding: 0rem 2rem 0rem 0.5rem;
			line-height: 2.2rem;
		}

		.title_content .cjdz_1{
			float: left;
		}

		.title_content .cjdz_2{
			float: right;
			margin-top: 0.775rem;
		}


		.left_dvwz{
			position:fixed;
			right: 0.8rem;
			top: 2rem;
		}

		.left_dvwz a{
			display: block;
			color: #2f2f2f;
			line-height: 0.9rem;
			width:2rem;
			text-align:center;
		}

	</style>


	<div class="wraper box22">
		<div class="title_top dis_f ali_ct jus_bt pad_20 bg_f borderbot1">
			<a onclick="javascript:window.history.back();" ><img src="<%=basePath%>/js/appWeb/images/back6.png" class="dis_b" style="width:0.4rem;" /></a>
			<p class="f_30 ">选择城市</p>
			<a class="dis_b f_24 wancheng"  ></a>
		</div>

		<div class=" top_title1">
			<span class="title_bt f_20" style="display: none;"><a name="A">A</a></span>
			<span id="A" ></span>
			<span class="title_bt f_20" style="display: none;"><a name="B">B</a></span>
			<span id="B" ></span>
			<span class="title_bt f_20" style="display: none;"><a name="C">C</a></span>
			<span id="C" ></span>
			<span class="title_bt f_20" style="display: none;"><a name="D">D</a></span>
			<span id="D" ></span>
			<span class="title_bt f_20" style="display: none;"><a name="E">E</a></span>
			<span id="E" ></span>
			<span class="title_bt f_20" style="display: none;"><a name="F">F</a></span>
			<span id="F" ></span>
			<span class="title_bt f_20" style="display: none;"><a name="G">G</a></span>
			<span id="G" ></span>
			<span class="title_bt f_20" style="display: none;"><a name="H">H</a></span>
			<span id="H" ></span>
			<span class="title_bt f_20" style="display: none;"><a name="I">I</a></span>
			<span id="I" ></span>
			<span class="title_bt f_20" style="display: none;"><a name="J">J</a></span>
			<span id="J" ></span>
			<span class="title_bt f_20" style="display: none;"><a name="K">K</a></span>
			<span id="K" ></span>
			<span class="title_bt f_20" style="display: none;"><a name="L">L</a></span>
			<span id="L" ></span>
			<span class="title_bt f_20" style="display: none;"><a name="M">M</a></span>
			<span id="M" ></span>
			<span class="title_bt f_20" style="display: none;"><a name="N">N</a></span>
			<span id="N" ></span>
			<span class="title_bt f_20" style="display: none;"><a name="O">O</a></span>
			<span id="O" ></span>
			<span class="title_bt f_20" style="display: none;"><a name="P">P</a></span>
			<span id="P" ></span>
			<span class="title_bt f_20" style="display: none;"><a name="Q">Q</a></span>
			<span id="Q" ></span>
			<span class="title_bt f_20" style="display: none;"><a name="R">R</a></span>
			<span id="R" ></span>
			<span class="title_bt f_20" style="display: none;"><a name="S">S</a></span>
			<span id="S" ></span>
			<span class="title_bt f_20" style="display: none;"><a name="T">T</a></span>
			<span id="T" ></span>
			<span class="title_bt f_20" style="display: none;"><a name="U">U</a></span>
			<span id="U" ></span>
			<span class="title_bt f_20" style="display: none;"><a name="V">V</a></span>
			<span id="V" ></span>
			<span class="title_bt f_20" style="display: none;"><a name="W">W</a></span>
			<span id="W" ></span>
			<span class="title_bt f_20" style="display: none;"><a name="X">X</a></span>
			<span id="X" ></span>
			<span class="title_bt f_20" style="display: none;"><a name="Y">Y</a></span>
			<span id="Y" ></span>
			<span class="title_bt f_20" style="display: none;"><a name="Z">Z</a></span>
			<span id="Z" ></span>
			<div class="left_dvwz">
				<a href="javascript:void(0)" onclick="document.getElementById('A').scrollIntoView();" class="f_22">A</a>
				<a href="javascript:void(0)" onclick="document.getElementById('B').scrollIntoView();"  class="f_22">B</a>
				<a href="javascript:void(0)" onclick="document.getElementById('C').scrollIntoView();" class="f_22">C</a>
				<a href="javascript:void(0)" onclick="document.getElementById('D').scrollIntoView();" class="f_22">D</a>
				<a href="javascript:void(0)" onclick="document.getElementById('E').scrollIntoView();" class="f_22">E</a>
				<a href="javascript:void(0)" onclick="document.getElementById('F').scrollIntoView();" class="f_22">F</a>
				<a href="javascript:void(0)" onclick="document.getElementById('G').scrollIntoView();" class="f_22">G</a>
				<a href="javascript:void(0)" onclick="document.getElementById('H').scrollIntoView();" class="f_22">H</a>
				<a href="javascript:void(0)" onclick="document.getElementById('I').scrollIntoView();" class="f_22">I</a>
				<a href="javascript:void(0)" onclick="document.getElementById('J').scrollIntoView();" class="f_22">J</a>
				<a href="javascript:void(0)" onclick="document.getElementById('K').scrollIntoView();" class="f_22">K</a>
				<a href="javascript:void(0)" onclick="document.getElementById('L').scrollIntoView();" class="f_22">L</a>
				<a href="javascript:void(0)" onclick="document.getElementById('M').scrollIntoView();" class="f_22">M</a>
				<a href="javascript:void(0)" onclick="document.getElementById('N').scrollIntoView();" class="f_22">N</a>
				<a href="javascript:void(0)" onclick="document.getElementById('O').scrollIntoView();" class="f_22">O</a>
				<a href="javascript:void(0)" onclick="document.getElementById('P').scrollIntoView();" class="f_22">P</a>
				<a href="javascript:void(0)" onclick="document.getElementById('Q').scrollIntoView();" class="f_22">Q</a>
				<a href="javascript:void(0)" onclick="document.getElementById('R').scrollIntoView();" class="f_22">R</a>
				<a href="javascript:void(0)" onclick="document.getElementById('S').scrollIntoView();" class="f_22">S</a>
				<a href="javascript:void(0)" onclick="document.getElementById('T').scrollIntoView();" class="f_22">T</a>
				<a href="javascript:void(0)" onclick="document.getElementById('U').scrollIntoView();" class="f_22">U</a>
				<a href="javascript:void(0)" onclick="document.getElementById('V').scrollIntoView();" class="f_22">V</a>
				<a href="javascript:void(0)" onclick="document.getElementById('W').scrollIntoView();" class="f_22">W</a>
				<a href="javascript:void(0)" onclick="document.getElementById('X').scrollIntoView();" class="f_22">X</a>
				<a href="javascript:void(0)" onclick="document.getElementById('Y').scrollIntoView();" class="f_22">Y</a>
				<a href="javascript:void(0)" onclick="document.getElementById('Z').scrollIntoView();" class="f_22">Z</a>
			</div>
		</div>

		<script id="city_list_tmpl" type="text/x-jquery-tmpl">
			<div class="title_content f_26 borderbot1" onclick="check({{= id}},'{{= name}}');">
				<span class="cjdz_1">{{= name}}</span>
				<div class="clearfix"></div>
			</div>
		</script>
		
		<div id="yes" style="display: none;" value="<%=basePath%>/js/appWeb/images/check_yes.png"></div>
		<div id="no" style="display: none;" value="<%=basePath%>/js/appWeb/images/check_no.png"></div>

	</div>
</body>

</html>