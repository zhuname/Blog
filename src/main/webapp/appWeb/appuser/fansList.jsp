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
	src="<%=basePath%>/js/appWeb/appUser/app_fansList.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0"
	name="viewport">
<title>首页</title>

</head>

<body>

	<div class="wraper" style="background: #f9f9fb;height:100%;" id="detail">
		<div class="dis_f ali_ct jus_bt pad_20 bg_f borderbot1">
			<a onclick="javascript:window.history.back();"><img src="<%=basePath%>/js/appWeb/images/back.png" class="dis_b" style="width:1rem;" /></a>
			<p class="f_30 clr_3">粉丝列表</p>
			<a ><img src="<%=basePath%>/js/appWeb/images/gantan.png" class="dis_b" style="width:0.9rem;visibility: hidden;" /></a>
	
		</div>

		<div class="padt_20 bordertop1 borderbot1 disf ali_ct jus_ct dis_f bg_f">
			<img src="<%=basePath%>/js/appWeb/images/member.png" class="dis_b" style="width:0.7rem;" />
			&nbsp;
			<span class="f_24 clr_he" id="fansNum">0人 关注了我</span>
		</div>
	
		<script id="fans_list_tmpl" type="text/x-jquery-tmpl">
		<div class="pad_30 bg_f dis_f ali_ct jus_bt borderbot1">
			<div class="dis_f ali_ct">
				<img onclick="window.location.href='/mts/appWeb/appuser/otherUser.jsp?id={{if appUser}}{{= appUser.id}}{{/if}}'" src="{{if appUser}}{{if appUser.header}}{{= appUser.header}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}" class="dis_b" style="width:1.7rem;border-radius: 0.9rem;margin-right:0.5rem;" />
				<div class="f_28 clr_he">
					<span class="ver_mid clr_3">{{if appUser.name}}{{= appUser.name}}{{/if}}</span>
					{{if appUser.sex}}
							{{if appUser.sex == '男'}}
							<img src="<%=basePath%>/js/appWeb/images/male.png" class="ver_mid" style="width:0.4rem;" />
							{{else appUser.sex == '女'}}
							<img src="<%=basePath%>/js/appWeb/images/female2.png" class="ver_mid" style="width:0.4rem;" />
							{{/if}}
						{{/if}}
					{{if appUser.userMedals}}
					{{each appUser.userMedals}}
							<img src="{{= $value.medal.image}}" class="ver_mid" style="width:0.5rem;" />
   					 {{/each}}
					{{/if}}
					<p class="mt_10 f_22">
						<span class="ver_mid">{{if appUser}}{{if appUser.sign}}{{= appUser.sign}}{{else}}美天给生活一点惊喜{{/if}}{{else}}美天给生活一点惊喜{{/if}}</span>
					</p>
				</div>
			</div>
			<div class="f_22 clr_r" onclick="attr({{= appUser.id}});" style="border:1px solid #f9634e;padding:0.1rem 0.5rem;border-radius: 0.2rem;white-space:nowrap;">{{if isAttr}}
																													{{if isAttr == 1 }}
																														已关注
																													{{else}}
																														关注
																													{{/if}}
																													{{else}}
																													关注
																												  {{/if}}</div>
			</div>
		</script>
		
		
		

	</div>
	
	

</body>


</html>
