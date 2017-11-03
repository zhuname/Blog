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
	src="<%=basePath%>/js/appWeb/activity/app_activity.js"></script>
<script src="<%=basePath%>/js/appWeb/weixinjs/swiper.min.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/appWeb/css/swiper.min.css" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0"
	name="viewport">
<title>同城活动</title>
</head>

<body>

	<div class="wraper">

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
		<div class="whte"></div>
			<div class="pad_30  dis_f ali_ct jus_bt " style="position:absolute;top:0;left:0;width:14.5rem;">
				<a  onclick="javascript:hrefIndexShare();" ><img src="<%=basePath%>/js/appWeb/images/back2.png" class="dis_b" style="width:1rem;" /></a>
				<div class="search_bg pos_rela">
					<input class="ipt2 f_22 clr_he" id="title" type="text" placeholder="昵称/主题" />
					<div onclick="select();" class="btn2"  ></div>
				</div>
				<a href="javascript:;"><img src="<%=basePath%>/js/appWeb/images/filter.png" class="dis_b filter_toggle" style="width:1rem;" /></a>
				<img src="<%=basePath%>/js/appWeb/images/arr_up.png" style="width:0.55rem;top:1.7rem;" class="dis_b arr_up_down dis_n xx_pic"/>
				<ul class="more_ul pad_20 dis_n xx_daohang" style="top:1.9rem;" >
					<li onclick="selectSort(1);" ><img src="<%=basePath%>/js/appWeb/images/e1.png"   class="ver_mid" style="width:0.6rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid">最新发布</span></li>
					<li onclick="selectSort(2);"><img src="<%=basePath%>/js/appWeb/images/e2.png"    class="ver_mid" style="width:0.6rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid">参与人数</span></li>
					<li  onclick="selectSort(3);"><img src="<%=basePath%>/js/appWeb/images/e3.png"   class="ver_mid" style="width:0.6rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid">截止时间</span></li>
				</ul>

				<script type="text/javascript">
					$('.filter_toggle').click(function(){
						$('.xx_pic').toggle();
						$('.xx_daohang').toggle();
						$(".whte").toggle()
					});
					$(".whte").click(function(){
						$('.xx_pic').toggle();
						$('.xx_daohang').toggle();
						$(".whte").toggle()
					})
					
				</script>
			</div>
		</div>

		<script id="lunbo_list_tmpl" type="text/x-jquery-tmpl">
				<li><a href="{{if type==1}}{{= url}}{{else type==2}}/mts/appWeb/posterPackage/posterPackageDetail.jsp?id={{= itemId}}{{else type==3}}/mts/appWeb/mediaPackage/mediaPackageDetail.jsp?id={{= itemId}}{{else type==4}}/mts/appWeb/card/cardDetail.jsp?id={{= itemId}}{{else type==5}}/mts/appWeb/activity/activityDetail.jsp?id={{= itemId}}{{else type==6}}/mts/appWeb/circle/circleDetail.jsp?id={{= itemId}}{{/if}}"><img src="{{= image}}" class="dis_b" /></a></li>
		</script>
		
		<div id="activity"></div>
		
		<script id="activity_list_tmpl" type="text/x-jquery-tmpl">
				<div class="mt_10 pos_rela"  onclick="window.location.href='/mts/appWeb/activity/activityDetail.jsp?id={{= id}}'";>
				{{if type==1}}
				<img src="{{= image}}" class="ver_mid" style="width:16rem;height:8rem;" />
				{{else}}
				<img src="<%=basePath%>/js/appWeb/images/play.png" class="dis_b play_img" style="width:2rem;" />
				<img src="{{= mediaImage}}" class="ver_mid" style="width:16rem;height:8rem;" />
				{{/if}}
				{{if status==4}}
				<img src="<%=basePath%>/js/appWeb/images/over.png" class="dis_b end_img" style="width:3rem;" />
				{{/if}}
				<div class="f_28 clr_f tc_title padl_30 dis_f ali_ct">
					<img src="<%=basePath%>/js/appWeb/images/bar.png" class="dis_b" style="width:0.1rem;margin-right:0.5rem;" />
					<p>{{= content}}</p>
				</div>
				<div class="bg_f pad_20 dis_f ali_ct jus_bt">
					<div style="width:9.5rem;" class="dis_f ali_ct jus_bt">
						<div><img src="<%=basePath%>/js/appWeb/images/eye4.png" class="ver_mid" style="height:0.4rem;" />
						<span class="f_20 clr_6 ver_mid">{{= viewedCount}}</span></div>
						<div><img src="<%=basePath%>/js/appWeb/images/user2.png" class="ver_mid" style="height:0.45rem;" />
						<span class="f_20 clr_6 ver_mid">{{= joinCount}}人参与</span></div>
						<div><img src="<%=basePath%>/js/appWeb/images/cup.png" class="ver_mid" style="height:0.55rem;" />
						<span class="f_20 clr_6 ver_mid">{{= winPrizePerson}}人中奖</span></div>
					</div>

					<div>
						<img src="<%=basePath%>/js/appWeb/images/lou.png" class="ver_mid" style="height:0.4rem;" />
						<span class="f_20 clr_6 ver_mid">{{= endTime}}止</span>
					</div>
				</div>
			</div>
		</script>
		


			<a   onclick="window.location.href='/mts/shareApp/down.html';" ><img src="<%=basePath%>/js/appWeb/images/public.png" class="public_fixed" style="height:2.9rem;" /></a>
					<div class="public_App" style="bottom:0;">
			<img src="<%=basePath%>/js/appWeb/images/close_hb.png" id="close_app">
			<img src="<%=basePath%>/js/appWeb/images/App_icon.jpg">
			
			<p>领福利，发活动，用APP免费参加</p>
			<span onclick="appLink()">立即打开</span>
		</div>
		<script type="text/javascript">
			$("#close_app").click(function(){
				$(".public_App").remove()
			})
		</script>
	</div>
</body>

</html>