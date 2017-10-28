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
	src="<%=basePath%>/js/appWeb/mediaPackage/app_mediaPackage.js"></script>
	
<script src="<%=basePath%>/js/appWeb/weixinjs/swiper.min.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/appWeb/css/swiper.min.css" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0"
	name="viewport">
<title>首页</title>
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
					<li onclick="selectSort(1);"><img src="<%=basePath%>/js/appWeb/images/package_new.png"  class="ver_mid" style="width:0.6rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid">最新发布</span></li>
					<li onclick="selectSort(2);"><img src="<%=basePath%>/js/appWeb/images/package_yuyue.png"  class="ver_mid" style="width:0.6rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid">预订最多</span></li>
					<li onclick="selectSort(3);"><img src="<%=basePath%>/js/appWeb/images/package_card.png"   class="ver_mid" style="width:0.6rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid">卡券最多</span></li>
					<li onclick="selectSort(4);"><img src="<%=basePath%>/js/appWeb/images/package_money.png"  class="ver_mid" style="width:0.6rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid">金额最多</span></li>
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
		<div class="bg_f pad_2030 dis_f ali_ct jus_bt hbhb_nav" style="width:22rem;" id="category">
			<a  onclick="javascript:selectCategory();" class="pos_rela active-f clr_r"><img src="<%=basePath%>/js/appWeb/images/c1.png" class="dis_b" style="width:1rem;" /><p class="f_20 clr_r">全部</p></a>
		</div>
	</div>
</div>

			<div id="mediaPackage" >

			</div>

			<script id="category_list_tmpl" type="text/x-jquery-tmpl">
				<a id="ca{{= id}}" onclick="javascript:selectCategory({{= id}});"><img src="{{= image}}" class="dis_b" style="width:1rem;" /><p class="f_20">{{= name}}</p></a>
			</script>
			
			<script id="lunbo_list_tmpl" type="text/x-jquery-tmpl">
				<li><a href="{{if type==1}}{{= url}}{{else type==2}}/mts/appWeb/posterPackage/posterPackageDetail.jsp?id={{= itemId}}{{else type==3}}/mts/appWeb/mediaPackage/mediaPackageDetail.jsp?id={{= itemId}}{{else type==4}}/mts/appWeb/card/cardDetail.jsp?id={{= itemId}}{{else type==5}}/mts/appWeb/activity/activityDetail.jsp?id={{= itemId}}{{else type==6}}/mts/appWeb/circle/circleDetail.jsp?id={{= itemId}}{{/if}}"><img src="{{= image}}" class="dis_b" /></a></li>
			</script>
			
			<script id="media_list_tmpl" type="text/x-jquery-tmpl">
				<div class="mt_10 pos_rela ">
				<img src="{{= mediaImage}}" class="ver_mid" style="width:16rem;height:8rem;"  onclick="window.location.href='/mts/appWeb/mediaPackage/mediaPackageDetail.jsp?id={{= id}}';"  />
				<div class="f_28 clr_f tc_title padl_30 dis_f ali_ct" onclick="window.location.href='/mts/appWeb/mediaPackage/mediaPackageDetail.jsp?id={{= id}}';">
					<img src="<%=basePath%>/js/appWeb/images/bar.png" class="dis_b" style="width:0.1rem;margin-right:0.5rem;" />
					<p >{{= title}}</p>
				</div>
				{{if encrypt==1}}
				<div class="f_28 clr_f tc_lock padl_30 dis_f ali_ct" onclick="window.location.href='/mts/appWeb/mediaPackage/mediaPackageDetail.jsp?id={{= id}}';">
					<img src="<%=basePath%>/js/appWeb/images/lock.png" class="dis_b" style="height:0.6rem;" />
				</div>
				{{/if}}
				<div class="dis_f ali_ct jus_ct flex_col play_video mt_120" onclick="window.location.href='/mts/appWeb/mediaPackage/mediaPackageDetail.jsp?id={{= id}}';">
					<a onclick="window.location.href='/mts/appWeb/mediaPackage/mediaPackageDetail.jsp?id={{= id}}';" ><img src="<%=basePath%>/js/appWeb/images/play.png" class="dis_b" style="width:2rem;" /></a>
					<div class="f_24 clr_f mt_40">还有<span class="clr_ora">{{= balance}}元</span>待抢</div>
				</div>
				<div class="bg_f pad_20 dis_f ali_ct jus_bt">
					<div style="width:6rem;" class="dis_f ali_ct jus_bt">
						<div><img src="<%=basePath%>/js/appWeb/images/play.png" class="ver_mid" style="height:0.6rem;" />
						<span class="f_20 clr_6 ver_mid">{{= scanNum}}</span></div>
						<div><img src="<%=basePath%>/js/appWeb/images/pl.png" class="ver_mid" style="height:0.6rem;" />
						<span class="f_20 clr_6 ver_mid">{{= commentCount}}</span></div>
						<div><img src="<%=basePath%>/js/appWeb/images/zan2.png" class="ver_mid" style="height:0.7rem;" />
						<span class="f_20 clr_6 ver_mid">{{= topCount}}</span></div>
					</div>

					<div class="bg_f dis_f ali_ct jus_bt">
						{{if moneyDetails}}
					{{each moneyDetails}}
							<img src="{{if $value.appUser.header}}{{= $value.appUser.header}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}" class="ver_mid" style="width:1rem;border-radius: 0.5rem;margin-right:0.2rem;" />
   					 {{/each}}
					{{/if}}
						<div class="clr_3 f_30">…</div>
						<img src="<%=basePath%>/js/appWeb/images/right.png" class="dis_b" style="width:0.4rem;margin:0 0.5rem;" />
					</div>
				</div>
			</div>
			</script>

			<a  onclick="window.location.href='/mts/shareApp/down.html';" ><img src="<%=basePath%>/js/appWeb/images/public.png" class="public_fixed" style="height:2.9rem;" /></a>
	</div>
</body>

</html>