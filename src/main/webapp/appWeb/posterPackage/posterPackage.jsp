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
<title>海报红包</title>


	<script>
		
			/* 		//分享回调
			dataForShareCallback=function(type){_$(_api3._shareCount,"info_id="+_info._id36+"&info_type=party&share_url="+dataForShare.url+"&share_type="+type+"&share_location=detail_party");};
			
			 var dataForShare={
            weixin_icon:"http://img.sys.hudongba.com/images/share/other_qq_3.png",
            weixin_tl_icon:"http://img.sys.hudongba.com/images/share/other_weixin_tl_3.png",
            weixin_url:"http://www.hdb.com/",
            qq_icon:"http://img.sys.hudongba.com/images/share/other_qq_3.png",
            weibo_icon:"http://img.sys.hudongba.com/images/share/other_weibo_3.png",
            url:"http://www.hdb.com/",
            title:"互动吧，免费发布、传播活动，最大化享受微信传播红利。",
            description:"互动吧，免费提供活动发布、管理、传播、收款等全环节服务，通过互动吧，您可以轻松在微信朋友圈、聊天群里组织各类活动。",
            sms:"互动吧，免费提供活动发布、管理、传播、收款等全环节服务，通过互动吧，您可以轻松在微信朋友圈、聊天群里组织各类活动。http://hudong.ba",
            appId:"wx2eb9d27e0e24ec11",
            share_twitter_image:"http://quick.hudongba.com/upload/_oss/userposterimageimg/201710/24/31508833689387_posterimage3.jpg@!wap-detail-post-image",//推特分享图片forApp
            callback:function(type){_$(_api3._shareCount,"info_id=0&info_type=other");}
        };
        (function(){
                dataForShare.weixin_icon="http://img.sys.hudongba.com/images/share/other_qq_3.png";
                dataForShare.weixin_icon_android="http://img.sys.hudongba.com/images/share/other_qq_3.png";
                dataForShare.weixin_tl_icon="http://img.sys.hudongba.com/images/share/other_weixin_tl_3.png";
                dataForShare.weixin_url= convertUrlByHdbParam(location.href);
                dataForShare.qq_icon="http://img.sys.hudongba.com/images/share/other_qq_3.png";
                dataForShare.weibo_icon="http://img.sys.hudongba.com/images/share/other_weixin_msg_3.png";
                dataForShare.url = convertUrlByHdbParam(location.href);
                dataForShare.title="【11.12樊登北京千人活动】2017领导力高峰论坛暨一书一课产品发布会";
                dataForShare.description="11月12日下午13:00—18:00，在北京外国语大学，大咖云集领导力高峰论坛暨一书一课产品发布会，樊登老师将会...";
                dataForShare.share_big_img = "";
            dataForShare.description=dataForShare.description || dataForShare.title;
            dataForShare.sms="快来看看这个活动：【11.12樊登北京千人活动】2017领导力高峰论坛暨一书一课产品发布会 http://www.hdb.com/party/7s222.html";
            dataForShare.callback=function(type){_$("/post/api:7","info_id="+_info._id36+"&info_type="+_info._type+"&share_url="+dataForShare.weixin_url+"&share_type="+type+"&share_location=wxdetail")};
        })(); */
		
		
		
		</script>
		


</head>

<body>


<div style ='margin:0 auto;width:0px;height:0px;overflow:hidden;'>
<img src="<%=basePath%>/js/appWeb/images/App_icon.jpg">
</div>




	<div class="wraper">
<div class="whte"></div>
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
			<div class=" show-img-box pwd" style="padding:0">
				<div class="pwd-box">
					<input id="command" type="text" placeholder="请输入加密口令方能领取哦">
					<p style="" onclick="jinru();">确定</p>
				</div>
			</div>
			<script type="text/javascript">
		</script>
		
			<div class="pad_30  dis_f ali_ct jus_bt " style="position:absolute;top:0;left:0;width:14.5rem;">
				<a  onclick="javascript:window.location.href='/mts/appWeb/index/index.jsp';" ><img src="<%=basePath%>/js/appWeb/images/back2.png" class="dis_b" style="width:1rem;" /></a>
				<div class="search_bg pos_rela">
					<input class="ipt2 f_22 clr_he" id="title" type="text" placeholder="昵称/主题" />
					<div onclick="select();" class="btn2"  ></div>
				</div>
				<a href="javascript:;"><img src="<%=basePath%>/js/appWeb/images/filter.png" class="dis_b filter_toggle" style="width:1rem;" /></a>
				<img src="<%=basePath%>/js/appWeb/images/arr_up.png" style="width:0.55rem;top:1.7rem;" id="img-top" class="dis_b arr_up_down dis_n"/>
				<ul class="more_ul pad_20 dis_n" style="top:1.9rem;" id="img-list">
					<li onclick="selectSort(1);"><img src="<%=basePath%>/js/appWeb/images/package_new.png" class="ver_mid" style="width:0.6rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid">最新发布</span></li>
					<li onclick="selectSort(2);"><img src="<%=basePath%>/js/appWeb/images/package_yuyue.png"  class="ver_mid" style="width:0.6rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid">预订最多</span></li>
					<li onclick="selectSort(3);"><img src="<%=basePath%>/js/appWeb/images/package_card.png"  class="ver_mid" style="width:0.6rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid">卡券最多</span></li>
					<li onclick="selectSort(4);"><img src="<%=basePath%>/js/appWeb/images/package_money.png"  class="ver_mid" style="width:0.6rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid">金额最多</span></li>
				</ul>

				<script type="text/javascript">
					$('.filter_toggle').click(function(){
						$("#img-top").toggle();
						$("#img-list").toggle();
						$(".whte").toggle();
					});
					$(".whte").click(function(){
						$("#img-top").toggle();
						$("#img-list").toggle();
						$(".whte").toggle();
					})
					$(".pwd").click(function(){
						$(this).toggle();
					})
					$(".pwd p").click(function(){
						$(".pwd").toggle();
						
					})
					$(".pwd .pwd-box").click(function(){
						event.stopPropagation();    
					})
				</script>
			</div>
		</div>

<div  class="overh" style="height:2.8rem;">
	<div style="overflow-x: auto;padding-bottom:0.5rem;">
		<div class="bg_f pad_2030 dis_f ali_ct jus_bt hbhb_nav" style="width:22rem;" id="category">
			<a id="caAll" onclick="javascript:selectCategory();" class="pos_rela active-f clr_r"><img src="<%=basePath%>/js/appWeb/images/c1.png" class="dis_b" style="width:1rem;" /><p class="f_20">全部</p></a>
		</div>
	</div>
</div>


		<div class="haibao_wrap" style="margin-top:-0.5rem;">
			<ul class="mask_hb_ul dis_f ali_ct jus_bt flex_w"  id="posterPackage">

			</ul>
		</div>
		
		<script id="category_list_tmpl" type="text/x-jquery-tmpl">
			<a id="ca{{= id}}" onclick="javascript:selectCategory({{= id}});"><img src="{{= image}}" class="dis_b" style="width:1rem;" /><p class="f_20 ">{{= name}}</p></a>
		</script>

		<script id="lunbo_list_tmpl" type="text/x-jquery-tmpl">
			<li><a {{if type}}href="{{if type==1}}{{= url}}{{else type==2}}/mts/appWeb/posterPackage/posterPackageDetail.jsp?id={{= itemId}}{{else type==3}}/mts/appWeb/mediaPackage/mediaPackageDetail.jsp?id={{= itemId}}{{else type==4}}/mts/appWeb/card/cardDetail.jsp?id={{= itemId}}{{else type==5}}/mts/appWeb/activity/activityDetail.jsp?id={{= itemId}}{{else type==6}}/mts/appWeb/circle/circleDetail.jsp?id={{= itemId}}{{/if}}{{/if}}"><img src="{{= image}}" class="dis_b" /></a></li>
		</script>

		<script id="posterPackage_list_tmpl" type="text/x-jquery-tmpl">
				<li>
					<img src="{{= image}}" class="dis_b" style="width:7.15rem;height:10rem;" />
					<a  onclick="changeHref(this,{{= id}},'{{if commend}}{{= command}}{{/if}}');" class="dis_b mask_hb">
						<div class="pad_20 dis_f ali_ct jus_bt">
							<p class="dis_f ali_ct"><img src="<%=basePath%>/js/appWeb/images/eye_lock.png" class="dis_b" style="width:0.6rem;" />
							&nbsp;<span class="f_18 clr_f">{{= lookNum}}</span></p>
							{{if encrypt}}
							{{if encrypt == 1}}
							<img src="<%=basePath%>/js/appWeb/images/lock.png" class="dis_b" style="height:0.6rem;" />	
							{{/if}}	
							{{/if}}
						</div>

						<div class="f_28 clr_f pad_30 al_ct" style="height:5rem;">{{= title}}</div>

						<div class="f_20 clr_f al_ct">{{if status}}{{if status==4}} 已抢完 {{else}}还有<span class="clr_r"> {{= balance}}元 </span>待抢{{/if}}{{/if}}</div>
					</a>
				</li>
		</script>
		
		

		<a  onclick="window.location.href='/mts/shareApp/down.html';" ><img src="<%=basePath%>/js/appWeb/images/public.png" class="public_fixed" style="height:2.9rem;" /></a>
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
			
			$(document).ready(function(){ 
			
			}); 
			
			
		</script>
		
		
	
		
	</div>
</body>

</html>