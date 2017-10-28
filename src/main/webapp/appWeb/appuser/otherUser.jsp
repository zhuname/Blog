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
	src="<%=basePath%>/js/appWeb/appUser/app_otherUser.js"></script>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0"
	name="viewport">
<title>首页</title>
<style>
body{background: #f0f2f5;}
#mains video{
width:90%;
height:auto;
}
</style>
</head>

<body>

	<div class="wraper">
		<%-- <div class="center_bg">
			<div class="pad_30 dis_f jus_bt ali_ct">
			<img src="<%=basePath%>/js/appWeb/images/back4.png" class="dis_b" style="width:0.5rem;" />
			</div>

			<div class="pad_30 dis_f ali_end jus_bt mt_40">
				<div class="dis_f ali_ct">
					<img src="<%=basePath%>/js/appWeb/images/face2.png" class="dis_b" style="width:1.5rem;border:2px solid #FFF;border-radius: 0.8rem;margin-right:0.5rem;" />
					<div class="clr_f">
						<span class="f_28">用户名称</span>
						<img src="<%=basePath%>/js/appWeb/images/female2.png" class="ver_mid" style="width:0.4rem;" />
						<img src="<%=basePath%>/js/appWeb/images/badge2.png" class="ver_mid" style="width:0.5rem;" />
						<br />
						<p class="f_20 clr_f">美天给生活一点惊喜</p>
					</div>
				</div>

				<div class="opa8 flex_col dis_f ali_ct jus_ct">
					<div>
					<img src="<%=basePath%>/js/appWeb/images/fans2.png" class="ver_mid" style="width:0.55rem;" />
					<span class="ver_mid f_22 clr_f">22</span>
					</div>
					<div class="f_20 f_light clr_f" style="border:1px solid #FFF;padding:0 0.1rem;border-radius: 0.1rem;">已关注</div>
				</div>
			</div>
		</div> --%>
		
		<div class="wraper overh" id="detail"></div>

	<script id="appuser_detail_tmpl" type="text/x-jquery-tmpl">
	<div class="center_bg">
			<div class="pad_30 dis_f jus_rt ali_ct" id="msg" >
			</div>

			<div class="pad_30 dis_f ali_end jus_bt">
				<div class="dis_f ali_ct">
					<img src="{{if header}}{{= header}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}" class="dis_b" style="width:1.5rem;height:1.5rem;border:2px solid #FFF;border-radius: 0.8rem;margin-right:0.5rem;" />
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

				<div class="opa8">
					<img src="<%=basePath%>/js/appWeb/images/fans2.png" class="ver_mid" style="width:0.55rem;" />
					<span class="ver_mid f_22 clr_f">{{= fansNum}}</span>
				<div class="f_20 f_light clr_f" style="border:1px solid #FFF;padding:0 0.1rem;border-radius: 0.1rem;">已关注</div>
				</div>
			</div>

			<div class="f_20 clr_f dis_f ali_ct jus_rt padl_30" >
				
				<span id="lqSpan">
				</span>
				&nbsp;
				<sapn id="lqjh">
				
				</span>
			</div>

			<div style="background: url({{if header}}{{= header}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}) no-repeat;width:16rem;height:7rem; position: absolute; top: 0;z-index: -1"></div>


		</div>



	</script>
	
	<div id="imgUrl" style="display: none;">
	
	<%=basePath%>/js/appWeb/images/
	</div>
	
		<!-- 这个是勋章模版 -->
	<script id="appuser_xunzhang_tmpl" type="text/x-jquery-tmpl">
		<img src="{{= medal.image}}" class="ver_mid" style="width:0.5rem;" />
	</script>

		<ul  class="zy_nav_ul f_24 clr_3 dis_f ali_ct jus_bt padl_20 bg_f borderbot1" >
			<li onclick="change(1,2,this)" class="change dis_f ali_ct flex_col jus_ct zy_cur ">
				<img src="<%=basePath%>/js/appWeb/images/h1h.png" class="ver_mid" style="width:0.8rem;" />
				<p class='lir clr_r' id="cityCircleCount">0</p>
			</li>

			<li onclick="change(2,2,this)" class="change dis_f ali_ct flex_col jus_ct ">
				<img src="<%=basePath%>/js/appWeb/images/h2.png" class="ver_mid" style="width:0.8rem;" />
				<p class='lir'  id="posterCount">0</p>
			</li>

			<li onclick="change(3,2,this)" class="change dis_f ali_ct flex_col jus_ct ">
				<img src="<%=basePath%>/js/appWeb/images/h3.png" class="ver_mid" style="width:0.8rem;" />
				<p class='lir' id="mediaCount">0</p>
			</li>

			<li onclick="change(4,2,this)" class="change dis_f ali_ct flex_col jus_ct ">
				<img src="<%=basePath%>/js/appWeb/images/h4.png" class="ver_mid" style="width:0.8rem;" />
				<p class='lir' id="activityCount">0</p>
			</li>
		</ul>


		<div class="wraper overh" id="list"></div>

		<ul class="mask_hb_ul dis_f ali_ct jus_bt flex_w"  id="posterPackage" style="padding:0 .4rem;">

		</ul>
		
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
							<img src="{{if $value.appUser.header}}{{= $value.appUser.header}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}" class="ver_mid" style="width:1rem;height:1rem;border-radius: 0.5rem;margin-right:0.2rem;" />
   					 {{/each}}
					{{/if}}
						<div class="clr_3 f_30">…</div>
						<img src="<%=basePath%>/js/appWeb/images/right.png" class="dis_b" style="width:0.4rem;margin:0 0.5rem;" />
					</div>
				</div>
			</div>
			</script>

		<script id="circle_list_tmpl" type="text/x-jquery-tmpl">
				<div class="dis_f jus_bt pad_2030 bg_f"  onclick="locationHref({{= id}});" style="padding-bottom:0;">
			<div class="f_38 clr_6">
				{{= d}}<p class="f_22">{{= m}}月</p>
			</div>

			<div class="mt_10" style="width:90%;">
				<div class="f_22 clr_6">{{= t}} 发布动态</div>
				<div class="clr_3 f_28 mt_10" >{{= content}}</div>
				<div class="dis_f ali_ct  flex_w quan_img mt_20" id="mains">
						{{if type==2}}
				{{if mediaUrl}}
				<video src="{{= mediaUrl}}" style="width:100%;height:30%;" poster="{{= mediaImage}}" controls="controls">
					您的浏览器不支持 video 标签。
				</video>
				{{/if}}
				{{else}}
<div class="show-img"  style="overflow:hidden">
				{{if images}}
					{{each images}}
						<img src="{{= $value}}" class="ver_mid" style="width:4.6rem;height:auto;margin:.1rem;float:left" onclick="showImg(this)"/>
   					{{/each}}
				{{/if}}
</div>
				{{/if}}

				</div>

				<div class="dis_f jus_rt mt_20 hrs pos_rela">
					<div class="dis_f ali_ct jus_bt padt_20" style="width:9rem;">
						{{if sumMoney}}
						<div>
							<img src="<%=basePath%>/js/appWeb/images/ds.png" class="ver_mid" style="width:0.8rem;" />
							<span class="f_20 clr_6 ver_mid">打赏<span class="f_24 clr_r">￥{{= sumMoney}}</span></span>
						</div>
						{{else}}	
							<div style="width:2rem;"></div>
						{{/if}}
						<div>
							<img src="<%=basePath%>/js/appWeb/images/xx.png" class="ver_mid" style="height:0.7rem;" />
							<span class="f_20 clr_6 ver_mid">{{= commentCount}}</span>
						</div>
						<div>
							{{if isOper}}
								<img src="<%=basePath%>/js/appWeb/images/zan.png" class="ver_mid" style="height:0.75rem;" />
								<span class="f_20 clr_r ver_mid ">{{= topCount}}</span>
							{{else}}
								<div id="zan{{= id}}" style="display:none;">
								<img src="<%=basePath%>/js/appWeb/images/zan.png" class="ver_mid" style="height:0.75rem;" />
								<span class="f_20 clr_r ver_mid " id="topCount{{= id}}">{{= topCount}}</span>
								</div>
								<div id="zanShow{{= id}}">
								<img onclick="zan({{= id}});" src="<%=basePath%>/js/appWeb/images/zan2.png" class="ver_mid" style="height:0.75rem;" />
								<span class="f_20 clr_6 ver_mid ">{{= topCount}}</span>
								</div>
							{{/if}}
						</div>
					</div>
				</div>
			</div>
		</div>
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

		
	</div>
</body>

</html>