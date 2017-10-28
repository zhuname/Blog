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
	src="<%=basePath%>/js/appWeb/appUser/app_myMedal.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0"
	name="viewport">
<title>首页</title>

</head>

<body>

	<div class="wraper ">
		<div class="center_bg" id="detail">
		</div>


	<div class="bg_f padl_20" id="list">
		

		
	</div>
	
	<script id="appuser_detail_tmpl" type="text/x-jquery-tmpl">
	<div class="center_bg">
			<div class="pad_30 dis_f jus_bt ali_ct">
			<img src="<%=basePath%>/js/appWeb/images/back4.png"  onclick="javascript:window.history.back();"  class="dis_b" style="width:0.5rem;" />
			</div>

			<div class="pad_30 dis_f ali_end jus_bt">
				<div class="dis_f ali_ct">
					<img src="{{if header}}{{= header}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}" class="dis_b"  onclick="window.location.href='/mts/appWeb/appuser/appuserUpdate.jsp';" style="width:1.5rem;height:1.5rem;border:2px solid #FFF;border-radius: 0.8rem;margin-right:0.5rem;" />
					<div class="clr_f">
						<span class="f_28">{{= name}}</span>
						{{if sex}}
							{{if sex == '男'}}
							<img src="<%=basePath%>/js/appWeb/images/male.png" class="ver_mid" style="width:0.4rem;" />
							{{else sex == '女'}}
							<img src="<%=basePath%>/js/appWeb/images/female2.png" class="ver_mid" style="width:0.4rem;" />
							{{/if}}
						{{/if}}
						<span class="f_28" id="xzmb"></span>
						
						<br />
						<p class="f_20 clr_f">{{if sign}}{{= sign}}{{else}}美天给生活一点惊喜{{/if}}</p>
					</div>
				</div>

				<div class="opa8" onclick="window.location.href='/mts/appWeb/appuser/fansList.jsp';">
					<img src="<%=basePath%>/js/appWeb/images/fans2.png" class="ver_mid" style="width:0.55rem;" />
					<span class="ver_mid f_22 clr_f">{{= fansNum}}</span>
				</div>
			</div>


			<div style="background: url({{if header}}{{= header}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}) no-repeat;width:16rem;height:7rem; position: absolute; top: 0;z-index: -1"></div>


		</div>
		</script>
		
		<script id="appuser_list_tmpl" type="text/x-jquery-tmpl">
		<div class="f_20 clr_3  padt_30 borderbot1 dis_f ali_ct jus_bt">
			<div class="dis_f ali_ct"  style="width:80%;">
				<img src="{{= image}}" class="dis_b" style="width:0.6rem;margin-right:0.5rem;" />
				<div class="f_28 clr_he">{{= name}}<p class="f_20">{{= descr}}</p></div>
			</div>
			<div class="f_24 clr_r" style="border:1px solid #f9634e;border-radius: 0.1rem;padding:0.1rem; ">{{if applyStatus}}{{if applyStatus==1}}申请认证{{/if}}{{if applyStatus == 2}}申请成功{{/if}}{{if applyStatus == 3}}申请失败{{/if}}{{else}}申请中{{/if}}</div>
		</div>
		</script>
		
		<!-- 这个是勋章模版 -->
	<script id="appuser_xunzhang_tmpl" type="text/x-jquery-tmpl">
		<img src="{{= medal.image}}" class="ver_mid" style="width:0.5rem;" />
	</script>
		
	</div>

</body>


</html>
