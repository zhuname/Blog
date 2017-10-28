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
	src="<%=basePath%>/js/appWeb/weixinjs/swiper.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/js/appWeb/weixinjs/TouchSlide.1.1.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/js/appWeb/weixinjs/global_phone.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/js/appWeb/weixinjs/jquery.tmpl.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/js/appWeb/css/swiper.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/js/appWeb/css/css.css" />
<script type="text/javascript"
	src="<%=basePath%>/js/appWeb/index/app_index.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0"
	name="viewport">
<title>首页</title>
<style>
.bannD  .bd ,.bannD .tempWrap{ width:100%;}
/* .bannD  .bd li{ height:33px; line-height:33px;   }
.bannD  .bd li a{ color:#666;  } */
</style>
</head>

<body>

	<div class="wraper overh">
		
		
		<div id="detail">
		
		
		</div>
		
		<!-- <div id="footDetail"></div> -->
		
		
<div class="pad_30"></div>
	<div class="pad_30"></div>
		<div class="bg_f fixed_nav">
			<ul class="nav_ul dis_f ali_ct jus_bt" style="width:12rem;margin:0 auto;">
				<li><a onclick="window.location.href='/mts/appWeb/index/index.jsp';"  class="dis_f ali_ct flex_col clr_r jus_ct">
				<img src="<%=basePath%>/js/appWeb/images/b1.png" class="dis_b" style="width:1rem;" /><p class="f_18 ">美天赏</p></a></li>
				<li><a  onclick="addShow();"><img src="<%=basePath%>/js/appWeb/images/fabu.png" class="dis_b" style="width:2.25rem;margin-top:-0.2rem;" /></a></li>
				<li><a  onclick="window.location.href='/mts/appWeb/appuser/appuserLook.jsp';" class="dis_f ali_ct flex_col clr_6 jus_ct">
				<img src="<%=basePath%>/js/appWeb/images/b2.png" class="dis_b" style="width:1rem;" /><p class="f_18 ">我的</p></a></li>
			</ul>
		</div>
	</div>
	
	<!-- 这个是未读消息 -->
	<script id="footDetail_tmpl" type="text/x-jquery-tmpl">
		<div class="padl_10">
				<div class="dis_f ali_ct jus_bt bg_f yh_bar padl_10">
					<div>
						<img src="<%=basePath%>/js/appWeb/images/user.png" class="ver_mid" style="width:0.7rem;" />
						<span class="f_16 clr_3 ver_mid">用户总数{{= sumPerson}}人</span>
					</div>

					<div>
						<img src="<%=basePath%>/js/appWeb/images/hongbao.png" class="ver_mid" style="width:0.7rem;" />
						<span class="f_16 clr_3 ver_mid">红包总金额{{= sumMoney}}元</span>
					</div>

					<div>
						<img src="<%=basePath%>/js/appWeb/images/rmb2.png" class="ver_mid" style="width:0.7rem;" />
						<span class="f_16 clr_3 ver_mid">已被领取{{= snachMoney}}元</span>
					</div>
				</div>
		</div>
	</script>
	
	
	<!-- 这个是未读消息 -->
	<script id="detail_tmpl" type="text/x-jquery-tmpl">
		<div class="bg_r">
			<div class="dis_f ali_ct jus_bt pad_30">
				<div class="dis_f ali_ct" onclick="window.location.href='/mts/appWeb/index/myCity.jsp';">
					<img src="<%=basePath%>/js/appWeb/images/location.png" class="dis_b" style="width:0.55rem;" />
					<div class="f_20 clr_f">&nbsp; <a id="city"></a> <span class="f_16" id="weather">多云转晴 23℃/28℃</span></div>
				</div>


				<img id="noLdShow" src="<%=basePath%>/js/appWeb/images/bell.png" onclick="window.location.href='/mts/appWeb/message/message.jsp';" class="dis_b" style="height:0.9rem;" /> 

				<div id="ldShow" style="position:relative"><img src="<%=basePath%>/js/appWeb/images/bell.png"  onclick="window.location.href='/mts/appWeb/message/message.jsp';"  style="height:0.9rem;" /><span style="position:absolute;font-size: 0.2rem;color:#fff;top:-0.2rem;right:-.2rem;text-align: center;line-height:.6rem;width:.6rem;height:.6rem;border-radius:50%;background:red;" id="xxNum">10<span></div>  




			</div>
			<div class="dis_f ali_ct flex_col jus_ct">
				<img id="header" src="<%=basePath%>/js/appWeb/images/default_header.png" class="dis_b" style="width:3rem;border-radius: 1.5rem;border:0.05rem solid #fca498;" />
				<div class="f_24 clr_f mt_10" id="name"></div>
			</div>
			

			<div>
				<div class="dis_f ali_ct jus_bt" style="width:12rem;margin:0 auto;">
					<div class="dis_f ali_ct flex_col" >
						<p class="f_60 clr_f f_light"><span class="f_20">￥</span>{{= sumMoney}}</p>
						<p class="f_18 clr_f">总收入</p>
					</div>
					<div style="border-right:1px solid #fb705d;height:2rem;"></div>
					<div class="dis_f ali_ct flex_col">
						<p class="f_60 clr_f f_light"><span class="f_20">￥</span>{{= todayMoney}}</p>
						<p class="f_18 clr_f">今日收入</p>
					</div>
				</div>

			</div>

			<div class="f_16 clr_f dis_f ali_ct jus_bt" style="width:12rem;margin:0.75rem auto 0 auto;">
				<p><img src="<%=basePath%>/js/appWeb/images/eye.png" class="ver_mid " style="height:0.4rem;" /> <span class="ver_mid">{{= scanNum}}</span></p>
				<p><img src="<%=basePath%>/js/appWeb/images/praise.png" class="ver_mid " style="height:0.5rem;" /> <span class="ver_mid">{{= topNum}}</span></p>
				<p><img src="<%=basePath%>/js/appWeb/images/msg.png" class="ver_mid " style="width:0.55rem;" /> <span class="ver_mid">{{= commentNum}}</span></p>
				<p><img src="<%=basePath%>/js/appWeb/images/fans.png" class="ver_mid " style="width:0.6rem;" /> <span class="ver_mid">{{= attenNum}}</span></p>
			</div>
			

				<img src="<%=basePath%>/js/appWeb/images/lang.png" class="dis_b " style="width:16rem;" />

		</div>
	<div class="pos_rela">
		<div class="clr_r jihui_btn dis_f ali_ct jus_ct">
			<img src="<%=basePath%>/js/appWeb/images/rmb.png" class="ver_mid " style="width:0.75rem;" />&nbsp;
			<span class="f_38 ver_mid" id="sj">09:20</span>&nbsp;
			<span class="f_26 ver_mid" id="sjwz">后获得1次机会</span>
		</div>
	</div>

	<div class="pad_20 mt_20">
		<ul class="haibao_ul dis_f ali_ct jus_bt flex_w">
			<li class="bg_f" >
			<a  onclick="window.location.href='/mts/appWeb/posterPackage/posterPackage.jsp?cityId={{= cityId}}';"  class=" dis_f ali_ct flex_col jus_ct">
				{{if posterCount}}
				{{if posterCount>0}}
				<div class="haibao_tips">{{= posterCount}}</div>
				{{/if}}
				{{/if}}
				<img src="<%=basePath%>/js/appWeb/images/haibao.png" class="dis_b" />
				<p class="f_26 clr_3 mt_10">海报红包</p>
				<p class="f_20 clr_r">还有<span class="f_28">{{= posterMoney}}元</span>待领</p>
			</a>
			</li>
			<li class="bg_f">
			<a onclick="window.location.href='/mts/appWeb/mediaPackage/mediaPackage.jsp?cityId={{= cityId}}';" class=" dis_f ali_ct flex_col jus_ct" >
			{{if mediaCount}}
			{{if mediaCount>0}}
			<div class="haibao_tips">{{= mediaCount}}</div>
			{{/if}}
			{{/if}}
				<img src="<%=basePath%>/js/appWeb/images/shipin.png" class="dis_b" />
				<p class="f_26 clr_3 mt_10">视频红包</p>
				<p class="f_20 clr_r">还有<span class="f_28">{{= mediaMoney}}元</span>待领</p>
				</a>
			</li>
			<li class="bg_f">
			<a  onclick="window.location.href='/mts/appWeb/activity/activity.jsp?cityId={{= cityId}}';"  class=" dis_f ali_ct flex_col jus_ct">
			{{if activityCount}}
			{{if activityCount>0}}
			<div class="haibao_tips">{{= activityCount}}</div>
			{{/if}}
			{{/if}}
				<img src="<%=basePath%>/js/appWeb/images/huodong.png" class="dis_b" />
				<p class="f_26 clr_3 mt_10">同城活动</p>
				<p class="f_20 clr_r">已有<span class="f_28">{{= joinCount}}</span>人参与</p>
				</a>
			</li>
			<li class="bg_f">
			<a  onclick="window.location.href='/mts/appWeb/circle/circle.jsp?cityId={{= cityId}}';"  class=" dis_f ali_ct flex_col jus_ct">
			{{if circleCount}}
			{{if circleCount>0}}
			<div class="haibao_tips">{{= circleCount}}</div>
			{{/if}}
			{{/if}}
				<img src="<%=basePath%>/js/appWeb/images/xinxi.png" class="dis_b" />
				<p class="f_26 clr_3 mt_10">城市圈</p>
				<p class="f_20 clr_r">趣闻趣事<span class="f_28">{{= cityCircleCount}}</span>条</p>
				</a>
			</li>
		</ul>
	</div>
	</script>
	
	<!-- 这个是未读消息 -->
	<script id="nouser_detail_tmpl" type="text/x-jquery-tmpl">
		<div class="bg_r">
			<div class="dis_f ali_ct jus_bt pad_30">
				<div class="dis_f ali_ct" onclick="window.location.href='/mts/appWeb/index/myCity.jsp';">
					<img src="<%=basePath%>/js/appWeb/images/location.png" class="dis_b" style="width:0.55rem;" />
					<div class="f_20 clr_f">&nbsp; <a id="city"></a> <span class="f_16" id="weather"></span></div>
				</div>
				<img src="<%=basePath%>/js/appWeb/images/bell.png" class="dis_b" style="height:0.9rem;" /> 
			</div>

			<div class="dis_f ali_ct jus_ct flex_col" style="height:8.05rem;">
				<a onclick="window.location.href='/mts/appWeb/appuser/appuserLogin.jsp';" class="login_btn_a dis_b al_ct f_26 clr_f">登录/注册</a>
			</div>

				<img src="<%=basePath%>/js/appWeb/images/lang.png" class="dis_b " style="width:16rem;" />

		</div>
	<div class="pos_rela">
		<div class="clr_r jihui_btn dis_f ali_ct jus_ct">
			<img src="<%=basePath%>/js/appWeb/images/rmb.png" class="ver_mid " style="width:0.75rem;" />&nbsp;
			<span class="f_26 ver_mid">看广告领红包</span>
		</div>
	</div>

	<div class="pad_20 mt_20">
		<ul class="haibao_ul dis_f ali_ct jus_bt flex_w">
			<li class="bg_f" >
			<a  onclick="window.location.href='/mts/appWeb/posterPackage/posterPackage.jsp?cityId={{= cityId}}';"  class=" dis_f ali_ct flex_col jus_ct">
				{{if posterCount}}
				{{if posterCount>0}}
				<div class="haibao_tips">{{= posterCount}}</div>
				{{/if}}
				{{/if}}
				<img src="<%=basePath%>/js/appWeb/images/haibao.png" class="dis_b" />
				<p class="f_26 clr_3 mt_10">海报红包</p>
				<p class="f_20 clr_r">还有<span class="f_28">{{= posterMoney}}元</span>待领</p>
			</a>
			</li>
			<li class="bg_f">
			<a onclick="window.location.href='/mts/appWeb/mediaPackage/mediaPackage.jsp?cityId={{= cityId}}';" class=" dis_f ali_ct flex_col jus_ct" >
			{{if mediaCount}}
			{{if mediaCount>0}}
			<div class="haibao_tips">{{= mediaCount}}</div>
			{{/if}}
			{{/if}}
				<img src="<%=basePath%>/js/appWeb/images/shipin.png" class="dis_b" />
				<p class="f_26 clr_3 mt_10">视频红包</p>
				<p class="f_20 clr_r">还有<span class="f_28">{{= mediaMoney}}元</span>待领</p>
				</a>
			</li>
			<li class="bg_f">
			<a  onclick="window.location.href='/mts/appWeb/activity/activity.jsp?cityId={{= cityId}}';"  class=" dis_f ali_ct flex_col jus_ct">
			{{if activityCount}}
			{{if activityCount>0}}
			<div class="haibao_tips">{{= activityCount}}</div>
			{{/if}}
			{{/if}}
				<img src="<%=basePath%>/js/appWeb/images/huodong.png" class="dis_b" />
				<p class="f_26 clr_3 mt_10">同城活动</p>
				<p class="f_20 clr_r">已有<span class="f_28">{{= joinCount}}</span>人参与</p>
				</a>
			</li>
			<li class="bg_f">
			<a  onclick="window.location.href='/mts/appWeb/circle/circle.jsp?cityId={{= cityId}}';"  class=" dis_f ali_ct flex_col jus_ct">
			{{if circleCount}}
			{{if circleCount>0}}
			<div class="haibao_tips">{{= circleCount}}</div>
			{{/if}}
			{{/if}}
				<img src="<%=basePath%>/js/appWeb/images/xinxi.png" class="dis_b" />
				<p class="f_26 clr_3 mt_10">城市圈</p>
				<p class="f_20 clr_r">趣闻趣事<span class="f_28">{{= cityCircleCount}}</span>条</p>
				</a>
			</li>
		</ul>
	</div>
	</script>
	
	<div id="add" class="fabu_mask" style="display:none;">
				<%-- <div><img id="lunbo" src="<%=basePath%>/js/appWeb/images/tpic.jpg" class="dis_b" style="width:14.25rem;height:6.25rem;margin:.5rem auto 0 auto;" /></div> --%>
				    <div class="swiper-container bannD pos_rela" id="alert-banner">
				        <div class="swiper-wrapper" id="lunbo" style="height:auto;">
				        
				        </div>
				        <!-- Add Pagination -->
				        <div class="swiper-pagination"></div>
				    </div>


				<div class="dis_f ali_ct jus_bt" style="width:9rem;margin:0 auto;">
					<div class="dis_f ali_ct jus_ct flex_col">
						<img src="<%=basePath%>/js/appWeb/images/t1.png" onclick="window.location.href='/mts/appWeb/circle/circleSave.jsp';"  class="dis_b" style="width:2.4rem;height:2.4rem;margin:3.5rem auto 0 auto;" />
						<div class="f_28 clr_f mt_10">城市圈</div>
					</div>

					<div class="dis_f ali_ct jus_ct flex_col">
						<img src="<%=basePath%>/js/appWeb/images/t2.png"   onclick="window.location.href='/mts/shareApp/down.html';"  class="dis_b" style="width:2.4rem;height:2.4rem;margin:3.5rem auto 0 auto;" />
						<div class="f_28 clr_f mt_10">同城活动</div>
					</div>
				</div>

				<div class="dis_f ali_ct jus_bt" style="width:13.5rem;margin:0 auto;">
					<div class="dis_f ali_ct jus_ct flex_col">
						<img src="<%=basePath%>/js/appWeb/images/t3.png"   onclick="window.location.href='/mts/shareApp/down.html';"  class="dis_b" style="width:2.4rem;height:2.4rem;margin:2.5rem auto 0 auto;" />
						<div class="f_28 clr_f mt_10">海报</div>
					</div>

					<div class="dis_f ali_ct jus_ct flex_col">
						<img src="<%=basePath%>/js/appWeb/images/t4.png"   onclick="window.location.href='/mts/shareApp/down.html';"  class="dis_b" style="width:2.4rem;height:2.4rem;margin:2.5rem auto 0 auto;" />
						<div class="f_28 clr_f mt_10">视频</div>
					</div>

					<div class="dis_f ali_ct jus_ct flex_col">
						<img src="<%=basePath%>/js/appWeb/images/t5.png"  onclick="window.location.href='/mts/shareApp/down.html';" class="dis_b" style="width:2.4rem;height:2.4rem;margin:2.5rem auto 0 auto;" />
						<div class="f_28 clr_f mt_10">卡券</div>
					</div>
				</div>

				<div><img  onclick="addHide();" src="<%=basePath%>/js/appWeb/images/tclose.png" class="dis_b fabu_click" style="width:1.7rem;height:1.7rem;margin:2rem auto 0 auto;" /></div>
		</div>

		<script type="text/javascript">
			function addShow(){
				$("#add").show();
			};
			function addHide(){
				$("#add").hide();
			};
		</script>
	
	
</body>

</html>


