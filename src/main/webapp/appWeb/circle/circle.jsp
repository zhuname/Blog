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
	src="<%=basePath%>/js/appWeb/circle/app_circle.js"></script>
	
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
				<img src="<%=basePath%>/js/appWeb/images/arr_up.png" style="width:0.55rem;top:1.7rem;" class="dis_b arr_up_down dis_n xx_pic"/>
				<ul class="more_ul pad_20 dis_n xx_daohang" style="top:1.9rem;" >
					<li><img src="<%=basePath%>/js/appWeb/images/package_new.png"  onclick="selectSort(1);"  class="ver_mid" style="width:0.6rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid">最新发布</span></li>
					<li><img src="<%=basePath%>/js/appWeb/images/package_yuyue.png"  onclick="selectSort(2);"  class="ver_mid" style="width:0.6rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid">预约最多</span></li>
					<li><img src="<%=basePath%>/js/appWeb/images/package_card.png" onclick="selectSort(3);"  class="ver_mid" style="width:0.6rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid">卡券最多</span></li>
					<li><img src="<%=basePath%>/js/appWeb/images/package_money.png" onclick="selectSort(4);"  class="ver_mid" style="width:0.6rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid">金额最多</span></li>
				</ul>

				<script type="text/javascript">
					$('.filter_toggle').click(function(){
						$('.xx_pic').toggle();
						$('.xx_daohang').toggle();
					});
				</script>
			</div>
		</div>

		<div id="circle"></div>
		
		
		<script id="circle_list_tmpl" type="text/x-jquery-tmpl">
				<div class="pad_30 bg_f borderbot1 mt_20"  onclick="locationHref({{= id}});" >
			<div class="dis_f ali_top jus_bt pos_rela" >
				<div class="dis_f ali_ct" >
					<img src="{{if appUser}}{{= appUser.header}}{{/if}}" class="dis_b" style="width:1.75rem;border-radius: 0.9rem;margin-right:0.5rem;" />
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
						<div class="f_22 clr_9">{{= createTime}}</div>
					</div>
				</div>

				<a  onclick="showCheck(this);" class="more_ul_toggle"><img src="<%=basePath%>/js/appWeb/images/more.png" class="dis_b" style="width:1rem;" /></a>
				<img src="<%=basePath%>/js/appWeb/images/arr_up.png" style="width:0.55rem;top:1rem;right:0.3rem;" class="dis_b arr_up_down dis_n" />
				<ul class="more_ul pad_20 dis_n" style="top:1.25rem;right:0;">
					<li><img src="<%=basePath%>/js/appWeb/images/a1.png" class="ver_mid" style="width:0.75rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid">分享</span></li>
					<li><img src="<%=basePath%>/js/appWeb/images/a2.png" class="ver_mid" style="width:0.75rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid">已关注</span></li>
					<li><img src="<%=basePath%>/js/appWeb/images/f3.png" class="ver_mid" style="height:0.75rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid">屏蔽</span></li>
					<li><img src="<%=basePath%>/js/appWeb/images/a4.png" class="ver_mid" style="width:0.75rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid">举报</span></li>
				</ul>
			</div>
			<div class="f_28 clr_3 mt_20">{{= content}}</div>

			<div class="dis_f ali_ct jus_bt flex_w quan_img mt_20">
				{{if images}}
						{{each images}}
							<img src=" {{= $value}}" class="ver_mid" style="width:4.75rem;height:4.75rem;" />
   						 {{/each}}
						{{/if}}
			</div>
		</div>

		<div class="bg_f dis_f jus_rt padl_30">
			<div class="dis_f ali_ct jus_bt padt_20" style="width:5rem;">
				<div>
					<img src="<%=basePath%>/js/appWeb/images/xx.png" class="ver_mid" style="height:0.7rem;" />
					<span class="f_20 clr_6 ver_mid">{{= commentCount}}</span>
				</div>
				<div>
					{{if topCount==1}}
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
			</script>

		
	
		<script id="lunbo_list_tmpl" type="text/x-jquery-tmpl">
				<li><a href="#"><img src="{{= image}}" class="dis_b" /></a></li>
		</script>

<script type="text/javascript">

</script>

			<a onclick="xinzeng();" ><img src="<%=basePath%>/js/appWeb/images/public.png" class="public_fixed" style="height:2.9rem;" /></a>
	</div>
</body>

</html>