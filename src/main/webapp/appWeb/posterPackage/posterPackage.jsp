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
	src="<%=basePath%>/js/appWeb/posterPackage/app_posterPackage.js"></script>
	
<script src="<%=basePath%>/js/appWeb/weixinjs/swiper.min.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/appWeb/css/swiper.min.css" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0"
	name="viewport">
<title>红包列表</title>


</head>

<body>

	<div class="wraper">

		<div class="haibao_bg pos_rela">
		
			<div class="bann pos_rela" id="bann">
				<div class="hd">
					<ul></ul>
				</div>
				<div class="bd">
					<ul id="lunbo">
					</ul>
				</div>
			</div>
			<script type="text/javascript">
		</script>
		
			<div class="pad_30  dis_f ali_ct jus_bt " style="position:absolute;top:0;left:0;width:14.5rem;">
				<a  onclick="javascript:window.history.back();" ><img src="<%=basePath%>/js/appWeb/images/back2.png" class="dis_b" style="width:1rem;" /></a>
				<div class="search_bg pos_rela">
					<input class="ipt2 f_22 clr_he" id="title" type="text" placeholder="昵称/主题" />
					<div onclick="select();" class="btn2"  ></div>
				</div>
				<a href="javascript:;"><img src="<%=basePath%>/js/appWeb/images/filter.png" class="dis_b filter_toggle" style="width:1rem;" /></a>
				<img src="<%=basePath%>/js/appWeb/images/arr_up.png" style="width:0.55rem;top:1.7rem;" class="dis_b arr_up_down dis_n"/>
				<ul class="more_ul pad_20 dis_n" style="top:1.9rem;" >
					<li><img src="<%=basePath%>/js/appWeb/images/package_new.png" class="ver_mid" style="width:0.6rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid">最新发布</span></li>
					<li><img src="<%=basePath%>/js/appWeb/images/package_yuyue.png" class="ver_mid" style="width:0.6rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid">预约最多</span></li>
					<li><img src="<%=basePath%>/js/appWeb/images/package_card.png" class="ver_mid" style="width:0.6rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid">卡券最多</span></li>
					<li><img src="<%=basePath%>/js/appWeb/images/package_money.png" class="ver_mid" style="width:0.6rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid">金额最多</span></li>
				</ul>

				<script type="text/javascript">
					$('.filter_toggle').click(function(){
						$('.more_ul').toggle();
						$('.arr_up_down').toggle();
					});
				</script>
			</div>
		</div>

<div  class="overh" style="height:2.8rem;">
	<div style="overflow-x: auto;padding-bottom:0.5rem;">
		<div class="bg_f pad_2030 dis_f ali_ct jus_bt hbhb_nav" style="width:32rem;" id="category">
			<a href="#"><img src="<%=basePath%>/js/appWeb/images/c1.png" class="dis_b" style="width:1rem;" /><p class="f_20 clr_r">全部</p></a>
		</div>
	</div>
</div>


		<div class="haibao_wrap" style="margin-top:-0.5rem;">
			<ul class="mask_hb_ul dis_f ali_ct jus_bt flex_w"  id="posterPackage">

			</ul>
		</div>
		
		<script id="category_list_tmpl" type="text/x-jquery-tmpl">
			<a href="#"><img src="{{= image}}" class="dis_b" style="width:1rem;" /><p class="f_20 clr_3">{{= name}}</p></a>
		</script>
		
		<script id="lunbo_list_tmpl" type="text/x-jquery-tmpl">
			<li><a href="#"><img src="{{= image}}" class="dis_b" /></a></li>
		</script>

		<script id="posterPackage_list_tmpl" type="text/x-jquery-tmpl">
				<li>
					<img src="{{= image}}" class="dis_b" style="width:7.15rem;height:10rem;" />
					<a  onclick="window.location.href='/mts/appWeb/posterPackage/posterPackageDetail.jsp?id={{= id}}';" class="dis_b mask_hb">
						<div class="pad_20 dis_f ali_ct jus_bt">
							<p class="dis_f ali_ct"><img src="<%=basePath%>/js/appWeb/images/eye_lock.png" class="dis_b" style="width:0.6rem;" />
							&nbsp;<span class="f_18 clr_f">{{= lookNum}}</span></p>
							{{if encrypt}}
							{{if encrypt == 1}}
							<img src="<%=basePath%>/js/appWeb/images/lock.png" class="dis_b" style="height:0.6rem;" />	
							{{/if}}	
							{{/if}}
						</div>

						<div class="f_28 clr_f pad_30 al_ct">{{= title}}</div>

						<div class="f_20 clr_f al_ct" style="margin-top:3rem;">{{if status}}{{if status==4}} 已抢完 {{else}}还有<span class="clr_r"> {{= balance}}元 </span>待抢{{/if}}{{/if}}</div>
					</a>
				</li>
		</script>

		<a href="#"><img src="<%=basePath%>/js/appWeb/images/public.png" class="public_fixed" style="height:2.9rem;" /></a>
	</div>
</body>

</html>