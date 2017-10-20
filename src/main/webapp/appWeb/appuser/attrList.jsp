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
	src="<%=basePath%>/js/appWeb/appUser/app_attrList.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0"
	name="viewport">
<title>首页</title>

</head>

<body>

	<div class="wraper" style="background: #f9f9fb;height:100%;">
		<div class="dis_f ali_ct jus_bt pad_20 bg_f borderbot1">
			<a onclick="javascript:window.history.back();"><img src="<%=basePath%>/js/appWeb/images/back.png" class="dis_b" style="width:1rem;" /></a>
			<p class="f_30 clr_3">我的关注</p>
			<a ><img src="<%=basePath%>/js/appWeb/images/more.png" class="dis_b more_ul_toggle" style="width:1rem;visibility: hidden;" /></a>
		</div>

		<div id="detail">
			<div class="dis_f ali_ct jus_ct flex_col" style="height:85%;">
				<img src="<%=basePath%>/js/appWeb/images/wdgz2.png" class="dis_b" style="width:5.25rem;" />
				<div class="f_28 clr_r mt_40">暂无数据</div>
			</div>
		</div>

	</div>
	
	<script id="attr_list_tmpl" type="text/x-jquery-tmpl">
		<div class="pad_30 bg_f dis_f ali_ct jus_bt borderbot1">
			<div class="dis_f ali_ct">
				<img src="{{if header}}{{= header}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}" class="dis_b" style="width:1.7rem;border-radius: 0.9rem;margin-right:0.5rem;" />
				<div class="f_28 clr_he">
					<span class="ver_mid clr_3">{{= name}}</span>
					{{if sex}}
							{{if sex == '男'}}
							<img src="<%=basePath%>/js/appWeb/images/male.png" class="ver_mid" style="width:0.4rem;" />
							{{else sex == '女'}}
							<img src="<%=basePath%>/js/appWeb/images/female2.png" class="ver_mid" style="width:0.4rem;" />
							{{/if}}
						{{/if}}
					{{if userMedals}}
					{{each userMedals}}
							<img src="{{= $value.medal.image}}" class="ver_mid" style="width:0.5rem;" />
   					 {{/each}}
					{{/if}}
					<p class="mt_10 f_22">
						<span class="ver_mid">{{if sign}}{{= sign}}{{else}}美天给生活一点惊喜{{/if}}</span>
					</p>
				</div>
			</div>
			<div class="f_22 clr_r"  onclick="attr({{= itemId}});"  style="border:1px solid #f9634e;padding:0.1rem 0.5rem;border-radius: 0.2rem;white-space:nowrap;">取消关注</div>
		</div>
		</script>
	
	<!-- 这个是勋章模版 -->
	<script id="appuser_xunzhang_tmpl" type="text/x-jquery-tmpl">
		<img src="{{= medal.image}}" class="ver_mid" style="width:0.5rem;" />
	</script>
	
</body>


</html>
