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
	src="<%=basePath%>/js/appWeb/activity/app_activityDetail.js"></script>
<script src="<%=basePath%>/js/appWeb/weixinjs/swiper.min.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/appWeb/css/swiper.min.css" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0"
	name="viewport">
<title>首页</title>
</head>

				<script type="text/javascript">
					$('.filter_toggle').click(function(){
						$('.xx_daohang').toggle();
						$('.xx_pic').toggle();
						
					});
					function showColl(show){
						$(show).siblings('.more_ul').toggle();
						$(show).siblings('.arr_up_down').toggle();
						$(".whte").toggle();
					};
				</script>

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

		<div class="tupian_bg" no-repeat;" style="position:absolute;top:0;left:0;width:14.5rem;">
			<div class="pad_30  dis_f ali_ct jus_bt pos_rela" >
				<a onclick="javascript:window.history.back();" ><img src="<%=basePath%>/js/appWeb/images/back3.png" class="dis_b" style="width:1rem;" /></a>
				<div class="whte" onclick="showColl(this)"></div>
				<a href="javascript:;"  onclick="showColl(this);" ><img src="<%=basePath%>/js/appWeb/images/more2.png" class="dis_b filter_toggle" style="width:1rem;margin-left: 130%;" /></a>
				<img src="<%=basePath%>/js/appWeb/images/arr_up.png" style="width:0.55rem;top:1.7rem;" class="dis_b arr_up_down dis_n xx_pic"/>
				<ul class="more_ul pad_20 dis_n xx_daohang" style="top:1.9rem;z-index=15" >
					<li><img src="<%=basePath%>/js/appWeb/images/a1.png" class="ver_mid" style="width:0.75rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid">分享</span></li>
					<li id="guanzhu" onclick="attr();"><img src="<%=basePath%>/js/appWeb/images/a2.png" class="ver_mid" style="width:0.75rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid"  id="attr">已关注</span></li>
					<li id="pingbi" onclick="collect();"><img src="<%=basePath%>/js/appWeb/images/f3.png" class="ver_mid" style="height:0.75rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid"  id="collect">屏蔽</span></li>
					<li onclick="report();"><img src="<%=basePath%>/js/appWeb/images/a4.png" class="ver_mid" style="width:0.75rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid" id="report">举报</span></li>
				</ul>
				
			</div>
		</div>







<script id="lunbo_list_tmpl" type="text/x-jquery-tmpl">
				<li><a><img src="{{= image}}" class="dis_b" /></a></li>
			</script>


		<div id="detail"></div>
		<script id="detail_tmpl" type="text/x-jquery-tmpl">
		<div class="pad_30 bg_f mt_20">
			<div class="dis_f ali_top jus_bt pos_rela" >
				<div class="dis_f ali_ct" >
					<img src="{{if appUser}}{{if appUser.header}}{{= appUser.header}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}" class="dis_b" style="width:1.75rem;border-radius: 0.9rem;margin-right:0.5rem;" />
					<div>
						<span class="ver_mid f_28 clr_3">{{if appUser}}{{= appUser.name}}{{/if}}</span>
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
					<div class="f_22 clr_9">{{if appUser}}{{if appUser.sign}}{{= appUser.sign}}{{else}}美天给生活一点惊喜{{/if}}{{else}}美天给生活一点惊喜{{/if}}</div>
					</div>
				</div>
			</div>
			<div class="f_32 clr_3 mt_20 ">
				<img src="<%=basePath%>/js/appWeb/images/bar.png" class="ver_mid" style="width:0.1rem;" />
				<span class="ver_mid">{{= content}}</span>
			</div>

			<div class="dis_f ali_ct jus_bt padt_20 bordertop1 mt_20">
				<div class="dis_f ali_ct">
					<img src="<%=basePath%>/js/appWeb/images/tel.png" class="ver_mid" style="height:0.7rem;margin-right:0.3rem;" />
					<span class="f_24 clr_3" class="ver_mid" > {{= phone}}</span>
				</div>
				<div class="dis_f ali_ct">
					<img src="<%=basePath%>/js/appWeb/images/lou2.png" class="ver_mid" style="height:0.5rem;margin-right:0.3rem;" />
					<span class="f_24 clr_3" class="ver_mid" > {{= endTime}}止</span>
				</div>
			</div>

			<div class="dis_f ali_ct jus_bt">
				<div class="dis_f ali_ct">
					<img src="<%=basePath%>/js/appWeb/images/addr.png" class="ver_mid" style="height:0.8rem;margin-right:0.3rem;" />
					<span class="f_24 clr_3" class="ver_mid" > {{= address}}</span>
				</div>
			</div>
		</div>



			<div class="f_26 dis_f ali_ct mt_20">
				<div class="clr_f padt_20 dis_f ali_ct jus_ct" id="huodongBtn" onclick="change(1);" style="background: #539df2;width:8rem;">
					<img src="<%=basePath%>/js/appWeb/images/hdjl.png" class="ver_mid" style="height:0.65rem;margin-right:0.3rem;" />
					<span>活动奖励</span>
				</div>

				<div class="clr_f padt_20 dis_f ali_ct jus_ct" id="canyuBtn" onclick="change(2);" style="background: #c8c8cc;width:8rem;">
					<img src="<%=basePath%>/js/appWeb/images/cysm.png" class="ver_mid" style="height:0.65rem;margin-right:0.3rem;" />
					<span>活动介绍</span>
				</div>
			</div>


			<div class="bg_f pad_20 clr_3 f_22 " id="huodongShow" style="line-height: 1rem;">

			</div>

			<div class="bg_f pad_20 clr_3 f_22 " id="canyuShow" style="display:none;line-height: 1rem;width:93%;word-break:   break-all;   word-wrap:break-word;border:0px   solid  #555">
				<pre>{{= joinExplain}}</pre>
			</div>

			<div class="bg_f dis_f ali_ct jus_bt xjq_wrap mt_20">
				<div class="f_22 clr_3 dis_f ali_ct pad_20">
					<img src="<%=basePath%>/js/appWeb/images/zhong.png" class="dis_b" style="width:0.8rem;margin-right:0.3rem;" />
					<div>{{= sumWinPrizePerson}}个中奖名额，已获奖{{= winPrizePerson}}人</div>
				</div>
				<div class="dis_f ali_ct jus_bt">
					<div class="ceng_img" id="zhongjiang" onclick="window.location.href='/mts/appWeb/activity/activityUserList.jsp?type=2&id={{= id}}';" >
					</div>
					<img src="<%=basePath%>/js/appWeb/images/right.png"  class="dis_b" style="width:0.4rem;margin:0 0.5rem;"  onclick="window.location.href='/mts/appWeb/activity/activityUserList.jsp?type=2&id={{= id}}';" />
				</div>
			</div>

			<div class=" bg_f pad_20 bordertop1">
				<div class="dis_f ali_ct jus_bt" style="width:11rem;margin:0 auto;">
					<div style="width:2rem;border-top:1px solid #ddd;"></div>
					<div class="dis_f ali_ct">
						<img src="<%=basePath%>/js/appWeb/images/flag.png" class="dis_b" style="width:0.7rem;margin-right:0.3rem;" />
						<div class="f_22 clr_3">已有{{= joinCount}}人参与活动</div>
					</div>
					<div style="width:2rem;border-top:1px solid #ddd;"></div>
				</div>
			</div>
			
			<div class="bg_f ali_ct padl_20 clr_3 f_30" style="padding-bottom:0.5rem;text-align:right;">
				<a id="canyu"  onclick="window.location.href='/mts/appWeb/activity/activityUserList.jsp?type=1&id={{= id}}';" ></a>
				<span onclick="window.location.href='/mts/appWeb/activity/activityUserList.jsp?type=1&id={{= id}}';">...</span>
				<img src="<%=basePath%>/js/appWeb/images/right.png" class="ver_mid" style="width:0.4rem;margin:0 0.5rem;"  onclick="window.location.href='/mts/appWeb/activity/activityUserList.jsp?type=1&id={{= id}}';" />
			</div>

			{{if isPart}}
				{{if isPart == 1}}
				<a class="dis_b f_28 a_btn2 al_ct mt_20" style=" background: #e3e3e6;color: #a7a7a6;">已参与</a>
				{{else}}
				<a  onclick="canyu();"  class="dis_b f_28 a_btn2 al_ct mt_20" style=" background: #FF7575;color: #FFFFFF;">立即参与</a>
				{{/if}}
			{{else}}
				<a onclick="canyu();" class="dis_b f_28 a_btn2 al_ct mt_20" style=" background: #FF7575;color: #FFFFFF;">立即参与</a>
			{{/if}}

		</script>

		<script id="huodong_tmpl" type="text/x-jquery-tmpl">
			<div class="dis_f ali_ct jus_bt"  style="float:left;">
				<div class="dis_f ali_ct"  style="float:right;">
					<span class="f_24 clr_3" class="ver_mid" > {{= title}}:{{= content}}</span>
				</div>
			</div>
			<span class="f_24 clr_3" class="ver_mid" style="float:right;"> {{= sumCount}}人</span>
			</br>
		</script>
		
		<script id="canyu_tmpl" type="text/x-jquery-tmpl">
			<img src="{{if appUser}}{{if appUser.header}}{{= appUser.header}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}" class="ver_mid" style="width:1.5rem;border-radius: 0.8rem;" />
		</script>
		
		<script id="zhongjiang_tmpl" type="text/x-jquery-tmpl">
			<img src="{{if appUser}}{{if appUser.header}}{{= appUser.header}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}" class="ver_mid" style="width:1.25rem;border-radius: 0.7rem;" />
		</script>

	</div>
</body>

</html>