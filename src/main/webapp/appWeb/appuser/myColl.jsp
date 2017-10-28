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
	src="<%=basePath%>/js/appWeb/appUser/app_myColl.js"></script>
	
<script src="<%=basePath%>/js/appWeb/weixinjs/swiper.min.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/appWeb/css/swiper.min.css" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0" name="viewport">
<title>首页</title>
<style>
html,body{height:100%;}
</style>
</head>

<body>

	<div class="wraper" style="background: #f9f9fb;height:100%;">
		<div class="dis_f ali_ct jus_bt pad_20 bg_f borderbot1 bg_r">
			<a  onclick="javascript:window.history.back();" ><img src="<%=basePath%>/js/appWeb/images/back5.png" class="dis_b" style="height:1rem;" /></a>
			<p class="f_30 clr_f">我的收藏</p>
			<a ><img src="<%=basePath%>/js/appWeb/images/more.png" class="dis_b more_ul_toggle" style="width:1rem;visibility: hidden;" /></a>
	
		</div>

		<ul class="favor_ul dis_f ali_ct  jus_bt padl_30">
			<li id="posterShow" class="f_20 clr_r dis_f jus_ct flex_col ali_ct favor_cur" onclick="change(1,0);">
				<img src="<%=basePath%>/js/appWeb/images/sc1h.png" class="dis_b" style="width:0.8rem;" />
				<span id="posterNum">0</span>
			</li>
			<li id="mediaShow" class="f_20 clr_3 dis_f jus_ct flex_col ali_ct"  onclick="change(2,0);">
				<img src="<%=basePath%>/js/appWeb/images/sc2.png" class="dis_b" style="width:0.8rem;" />
				<span id="mediaNum">0</span>
			</li>
			<li id="cardShow" class="f_20 clr_3 dis_f jus_ct flex_col ali_ct"  onclick="change(3,0);">
				<img src="<%=basePath%>/js/appWeb/images/sc3.png" class="dis_b" style="width:0.8rem;" />
				<span id="cardNum">0</span>
			</li>
			<li id="huodongShow" class="f_20 clr_3 dis_f jus_ct flex_col ali_ct"  onclick="change(4,0);">
				<img src="<%=basePath%>/js/appWeb/images/sc4.png" class="dis_b" style="width:0.8rem;" />
				<span id="huodongNum">0</span>
			</li>
		</ul>

	<div class="haibao_wrap" style="margin-top:-0.5rem;overflow:hidden;box-sizing:border-box;">
			<ul class="mask_hb_ul dis_f ali_ct jus_bt flex_w" id="list">
			
				</ul>
				</div>

	</div>
	
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
		
		
		<script id="media_list_tmpl" type="text/x-jquery-tmpl">
				<div class="mt_10 pos_rela ">
				<img src="{{= mediaImage}}" class="ver_mid" style="width:100%;height:8rem;"  onclick="window.location.href='/mts/appWeb/mediaPackage/mediaPackageDetail.jsp?id={{= id}}';"  />
				<div class="f_28 clr_f tc_title padl_30 dis_f ali_ct" onclick="window.location.href='/mts/appWeb/mediaPackage/mediaPackageDetail.jsp?id={{= id}}';">
					<img src="<%=basePath%>/js/appWeb/images/bar.png" class="dis_b" style="width:0.1rem;margin-right:0.5rem;" />
					<p >{{= title}}</p>
				</div>

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

					<div class="bg_f dis_f ali_ct jus_rt">
						{{if moneyDetails}}
					{{each moneyDetails}}
							<img src="{{= $value.appUser.header}}" class="ver_mid" style="width:1rem;height:1rem;border-radius: 0.5rem;margin-right:0.2rem;" />
   					 {{/each}}
					{{/if}}
						<div class="clr_3 f_30">…</div>
						<img src="<%=basePath%>/js/appWeb/images/right.png" class="dis_b" style="width:0.4rem;margin:0 0.5rem;" />
					</div>
				</div>
			</div>
			</script>
			
			<script id="activity_list_tmpl" type="text/x-jquery-tmpl">
				<div class="mt_10 pos_rela"  onclick="window.location.href='/mts/appWeb/activity/activityDetail.jsp?id={{= id}}'";>
				{{if type==1}}
				<img src="{{= image}}" class="ver_mid" style="width:100%;height:8rem;" />
				{{else}}
				<img src="<%=basePath%>/js/appWeb/images/play.png" class="dis_b play_img" style="width:2rem;" />
				<img src="{{= mediaUrl}}" class="ver_mid" style="width:16rem;height:8rem;" />
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
						<span class="f_20 clr_6 ver_mid">{{= createTime}}止</span>
					</div>
				</div>
			</div>
		</script>
		
		
		<script id="card_list_tmpl" type="text/x-jquery-tmpl">
				<div class="pad_3020 pos_rela" onclick="window.location.href='/mts/appWeb/card/cardDetail.jsp?id={{= id}}'" style="width:100%;box-sizing: border-box;">
			<div class="pad_20 dis_f ali_top bg_f" style="width:100%; box-sizing: border-box;">
				<img src="{{if appUser}}{{if appUser.header}}{{= appUser.header}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}" class="dis_b yy_face_img" style="margin-right:0.5rem;"/>
				<div class="f_24 clr_3 dis_f ali_ct jus_bt" style="width:12rem;">
					<div>
						<span class="ver_mid f_24 clr_3">{{if appUser}}{{= appUser.name}}{{/if}}</span>
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
					</div>
					<div class="dis_f ali_ct">
						<div class="clr_he" style="margin-right:0.2rem;">
							<img src="<%=basePath%>/js/appWeb/images/kq2.png" style="width:0.6rem;" class="ver_mid"/>
							<span class="ver_mid">{{= convertNum}}</span>
						</div>
					</div>
				</div>

			</div>

			<div class="f_30 clr_3 bg_f padl_20" style="padding-bottom:0.5rem;">{{= title}}</div>
<div class="bg_f"><img src="<%=basePath%>/js/appWeb/images/kq_quan.png" class="ver_mid" style="height:0.483rem;width:100%;" /></div>
			<div class="dis_f ali_ct jus_bt pad_20 bg_f" >
					<div class="f_18 clr_he dis_f ali_ct">
						<img src="<%=basePath%>/js/appWeb/images/jzsj.png" class="ver_mid" style="width:0.4rem;margin-right: 0.2rem;" />
						{{= endTime}}止
					</div>
					{{if convertMoney}}
						<div class="f_20 clr_r">{{= convertMoney}}元</div>
					{{else}}
						<div class="f_20 clr_r">免费</div>
					{{/if}}
				</div>
		</div>
		</script>
	
		<div id="url" style="display: none;"><%=basePath%>/js/appWeb/images/</div>
	
</body>

</html>