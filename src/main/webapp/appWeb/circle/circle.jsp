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
<title>城市圈</title>
</head>

<body>
		<div id="black-box" style="position:fixed;left:0;top:0;width:100%;height:100%;z-index:100;display:none;"></div>
		<div class="show-img-box">
	    <div class="swiper-container">
        <div class="swiper-wrapper">
        </div>
    </div>
</div>
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
<!-- 提示窗 -->
<div class="alert-box dis_f jus_ct ali_end" style="position:fixed;width:100%;height:100%;background:rgba(0,0,0,.6);left:0;top:100%;z-index:1000;transition:all .5s;">
<div class="Report dis_f" style="width:100%;height:2rem;background:#fff;padding:.4rem;box-sizing:border-box;">
	<input type="text" id="content" style="flex:1;border:.06rem solid #ddd;border-radius:.2rem;padding-left:.2rem;" placeholder="请填写举报内容">
	<span onclick="jubao();" style="width:3rem;height:100%;display:inline-block;text-align:center;line-height:1.2rem;margin-left:.4rem;background:#00a0e2;color:#fff;">发送</span>
</div>
</div>
			<div class="pad_30  dis_f ali_ct jus_bt " style="position:absolute;top:0;left:0;width:14.5rem;">
				<a  onclick="javascript:window.location.href='/mts/appWeb/index/index.jsp';" ><img src="<%=basePath%>/js/appWeb/images/back2.png" class="dis_b" style="width:1rem;" /></a>
				<div class="whte"></div>
				<div class="search_bg pos_rela">
					<input class="ipt2 f_22 clr_he" id="title" type="text" placeholder="昵称/主题" />
					<div onclick="select();" class="btn2"  ></div>
				</div>
				
				<a href="javascript:;"><img src="<%=basePath%>/js/appWeb/images/filter.png" class="dis_b filter_toggle" style="width:1rem;" /></a>
				<img src="<%=basePath%>/js/appWeb/images/arr_up.png" style="width:0.55rem;top:1.7rem;" class="dis_b arr_up_down dis_n xx_pic"/>
				
				<ul class="more_ul pad_20 dis_n xx_daohang" style="top:1.9rem;" >
					<li onclick="selectSort(1);"><img src="<%=basePath%>/js/appWeb/images/package_new.png"    class="ver_mid" style="width:0.6rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid">最新的</span></li>
					<li onclick="selectSort(2);"><img src="<%=basePath%>/js/appWeb/images/package_yuyue.png"    class="ver_mid" style="width:0.6rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid">关注的</span></li>
					<li onclick="selectSort(3);"><img src="<%=basePath%>/js/appWeb/images/package_card.png"   class="ver_mid" style="width:0.6rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid">屏蔽的</span></li>
				</ul>
				

			</div>
		</div>
		<div id="circle"></div>
		
		
		<script id="circle_list_tmpl" type="text/x-jquery-tmpl">
				<div class="pad_30 bg_f borderbot1 mt_20"    onclick="locationHref({{= id}});">
			<div class="dis_f ali_top jus_bt pos_rela" >
				<div class="dis_f ali_ct" >
					<img onclick="window.location.href='/mts/appWeb/appuser/otherUser.jsp?id={{if appUser}}{{= appUser.id}}{{/if}}'" src="{{if appUser}}{{if appUser.header}}{{= appUser.header}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}" class="dis_b" style="width:1.75rem;height:1.75rem;border-radius: 0.9rem;margin-right:0.5rem;" />
						<div>
						<span class="ver_mid f_28 clr_3">{{if appUser}}{{= appUser.name}}{{/if}}</span>
						{{if appUser}}{{if appUser.sex}}
						{{if appUser.sex == '男'}}
							<img src="<%=basePath%>/js/appWeb/images/male.png" class="ver_mid" style="width:0.4rem;" />
						{{else appUser.sex == '女'}}
							<img src="<%=basePath%>/js/appWeb/images/female2.png" class="ver_mid" style="width:0.4rem;" />
						{{/if}}
						{{/if}}{{/if}}
						{{if appUser}}{{if appUser.userMedals}}
						{{each appUser.userMedals}}
							<img src="{{= $value.medal.image}}" class="ver_mid" style="width:0.5rem;" />
   						{{/each}}
						{{/if}}{{/if}}
						<div class="f_22 clr_9">{{= createTime}}</div>
					</div>
				</div>

				<a  onclick="showCheck(this);" class="more_ul_toggle"><img src="<%=basePath%>/js/appWeb/images/more.png" class="dis_b" style="width:1rem;" /></a>
				<img src="<%=basePath%>/js/appWeb/images/arr_up.png" style="width:0.55rem;top:1rem;right:0.3rem;" class="dis_b arr_up_down dis_n" />
				<ul class="more_ul pad_20 dis_n" style="top:1.25rem;right:0;" onclick="dou()">
					<li onclick="attr({{if appUser}}{{= appUser.id}}{{/if}});"><img src="<%=basePath%>/js/appWeb/images/a2.png" class="ver_mid" style="width:0.75rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid">{{if isAttr}}已关注{{else}}关注{{/if}}</span></li>
					<li onclick="pingbi({{if appUser}}{{= appUser.id}}{{/if}});"><img src="<%=basePath%>/js/appWeb/images/f3.png" class="ver_mid" style="height:0.75rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid">屏蔽</span></li>
					<li onclick="report(this,{{if appUser}}{{= appUser.id}}{{/if}},{{= id}})"><img src="<%=basePath%>/js/appWeb/images/a4.png" class="ver_mid" style="width:0.75rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid">举报</span></li>
				</ul>
			</div>
			<pre style="white-space: pre-wrap;word-wrap: break-word;"><div class="f_28 clr_3 mt_20" style="display:inline;">{{= content}}<div class="f_28 clr_3 mt_20" style="display:none;display:inline;" id="contents{{= id}}">{{= contents}}</div> </div></pre>
			
			{{if quanbu}}
			<div class="f_28 clr_3 mt_20" id="gengduo" show="1" style="font-weight:400;color:blue;padding-left:.2rem;" onclick="showGengduo({{= id}},this);">查看更多</div>
			{{/if}}
			<div class="dis_f ali_ct flex_w quan_img mt_20">
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

		</div>

		<div class="bg_f dis_f jus_rt padl_30">
			<div class="dis_f ali_ct jus_bt padt_20" style="width:5rem;">
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
		</script>
		<!--  --><script id="lunbo_list_tmpl" type="text/x-jquery-tmpl">
			<li><a href="{{if type==1}}{{= url}}{{else type==2}}/mts/appWeb/posterPackage/posterPackageDetail.jsp?id={{= itemId}}{{else type==3}}/mts/appWeb/mediaPackage/mediaPackageDetail.jsp?id={{= itemId}}{{else type==4}}/mts/appWeb/card/cardDetail.jsp?id={{= itemId}}{{else type==5}}/mts/appWeb/activity/activityDetail.jsp?id={{= itemId}}{{else type==6}}/mts/appWeb/circle/circleDetail.jsp?id={{= itemId}}{{/if}}"><img src="{{= image}}" class="dis_b" /></a></li>
		</script>
						<script type="text/javascript">
					$('.filter_toggle').click(function(){
						$('.xx_pic').toggle();
						$('.xx_daohang').toggle();
						$('.whte').toggle();
					});
					$('.whte').click(function(){
						$('.xx_pic').toggle();
						$('.xx_daohang').toggle();
						$('.whte').toggle();
					});
					$(".alert-box").click(function(){
						$(this).css("top","100%");
						$("#black-box").hide();
						$('.xx_pic').hide()
						$('.more_ul_toggle').siblings("img").hide()
					})
					$(".Report").click(function(){
						event.stopPropagation();
					})
					    $(".show-img-box").click(function(){
    	$(this).toggle();
    });
				    var swiper = new Swiper('.swiper-container', {
				        pagination: '.swiper-pagination',
				        paginationClickable: true,
				        observer:true,
				        observeParents:true,
				        spaceBetween : 10
				    });
				</script>

<script type="text/javascript">

</script>
			<a onclick="xinzeng();" ><img src="<%=basePath%>/js/appWeb/images/public.png" class="public_fixed" style="height:2.9rem;" /></a>
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