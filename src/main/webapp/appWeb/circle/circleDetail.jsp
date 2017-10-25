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
	src="<%=basePath%>/js/appWeb/circle/app_circleDetail.js"></script>
	
<script src="<%=basePath%>/js/appWeb/weixinjs/swiper.min.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/appWeb/css/swiper.min.css" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0"
	name="viewport">
<title>首页</title>


</head>

<body  class="body">

<style>
		.srk_box{
			height: 100%;
			width: 100%;
			background-color: rgba(0,0,0,0.5);
			position: fixed;
			top: 0rem;

		}
		
		.body{
			height: 1px;
			width: 1px;
		}

		.bottom_srk{
			position: fixed;
			bottom: 0rem;
			width: 100%;
			background-color: #f2f2f2;
			box-sizing: border-box;
		}

		.wraper{
			position:absolute;
			z-index: -2;
			width: 100%;
		}


		.inpt2{
			border: 2px solid #e1e1e1;
			border-radius: 0.1rem;
			width: 11rem;
			float: left;
			height: 2.0rem;
			line-height: 2.0rem;
			padding-left: 0.5rem;
			display: block;
			z-index: 1;
		}

		.fs{
			background-color: #0a7cd4;
			float: right;
			height: 2.0rem;
			line-height: 2.0rem;
			width: 3rem;
			border-radius: 0.2rem;
			color: #ffffff;
			text-align: center;
		}

	</style>

	<div class="wraper ">
	<div class="dis_f ali_ct jus_bt pad_20 bg_f borderbot1">
			<a  onclick="javascript:window.history.back();" ><img src="<%=basePath%>/js/appWeb/images/back.png" class="dis_b" style="width:1rem;" /></a>
			<p class="f_30 clr_3">城市圈详情</p>
			<a ><img src="<%=basePath%>/js/appWeb/images/more.png" class="dis_b more_ul_toggle" style="width:1rem;visibility: hidden;" /></a>
		</div>
		
		
		<div id="detail"></div>
		
		<!-- <div class="pad_20"></div>
		<div class="pad_20"></div> -->
		
		
		<script id="foot_tmpl" type="text/x-jquery-tmpl">
		<div class="fixed_dashang">
			<div class="dis_f ali_ct jus_bt bg_f bordertop1" style="padding:0 1.5rem;" >
				<div class="pad_20"  onclick="$('#showDashang').show();" >
					<img src="<%=basePath%>/js/appWeb/images/ds.png" class="ver_mid" style="width:0.8rem;" />
					<span class="f_26 clr_6 ver_mid">打赏</span>
				</div>
				<div class="pad_20" onclick="showInput({{= id}});">
					<img src="<%=basePath%>/js/appWeb/images/xx.png" class="ver_mid" style="height:0.7rem;" />
					<span class="f_26 clr_6 ver_mid">评论</span>
				</div>
				<div class="pad_20" id="dianzanshow">
					{{if isOper==1}}
					<img src="<%=basePath%>/js/appWeb/images/praise2.png" class="ver_mid" style="height:0.75rem;" />
					<span class="f_26 clr_r ver_mid">点赞</span>
					{{else}}
					<img src="<%=basePath%>/js/appWeb/images/praise3.png" onclick="dianzan();" class="ver_mid" style="height:0.75rem;" />
					<span class="f_26 clr_6 ver_mid"  onclick="dianzan();" >点赞</span>
					{{/if}}
				</div>
			</div>
		</div>
		</script>
		
		
		<script id="footNo_tmpl" type="text/x-jquery-tmpl">
		<div class="fixed_dashang">
			<div class="dis_f ali_ct jus_bt bg_f bordertop1" style="padding:0 1.5rem;" >
				<div class="pad_20" onclick="window.location.href='/mts/appWeb/appuser/appuserLogin.jsp'" >
					<img src="<%=basePath%>/js/appWeb/images/ds.png" class="ver_mid" style="width:0.8rem;" />
					<span class="f_26 clr_6 ver_mid">打赏</span>
				</div>
				<div class="pad_20" onclick="window.location.href='/mts/appWeb/appuser/appuserLogin.jsp'">
					<img src="<%=basePath%>/js/appWeb/images/xx.png" class="ver_mid" style="height:0.7rem;" />
					<span class="f_26 clr_6 ver_mid">评论</span>
				</div>
				<div class="pad_20">
					<img src="<%=basePath%>/js/appWeb/images/praise3.png" onclick="window.location.href='/mts/appWeb/appuser/appuserLogin.jsp'" class="ver_mid" style="height:0.75rem;" />
					<span class="f_26 clr_6 ver_mid"  onclick="dianzan();" >点赞</span>
				</div>
			</div>
		</div>
		</script>
		
		
		<script id="zan_image_tmpl" type="text/x-jquery-tmpl">
			<img src="<%=basePath%>/js/appWeb/images/praise2.png" class="ver_mid" style="height:0.75rem;" />
			<span class="f_26 clr_r ver_mid">点赞</span>
		</script>
		
		<div id="content"></div>
		</br></br></br>

		<script id="detail_tmpl" type="text/x-jquery-tmpl">
				<div class="pad_30 bg_f borderbot1">
			<div class="dis_f ali_top jus_bt pos_rela">
				<div class="dis_f ali_ct" >
					<img onclick="window.location.href='/mts/appWeb/appuser/otherUser.jsp?id={{if appUser}}{{= appUser.id}}{{/if}}'" src="{{if appUser}}{{if appUser.header}}{{= appUser.header}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}" class="dis_b" style="width:1.75rem;border-radius: 0.9rem;margin-right:0.5rem;" />
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
				<div class="whte"  onclick="showColl(this)"></div>
				<a href="javascript:;" onclick="showColl(this);" class="more_ul_toggle"><img src="<%=basePath%>/js/appWeb/images/more.png" class="dis_b" style="width:1rem;" /></a>
				<img src="<%=basePath%>/js/appWeb/images/arr_up.png" style="width:0.55rem;top:1rem;right:0.3rem;" class="dis_b arr_up_down dis_n" />
				<ul class="more_ul pad_20 dis_n" style="top:1.25rem;right:0;z-index=15">
					<li onclick="attr();"><img src="<%=basePath%>/js/appWeb/images/a2.png" class="ver_mid" style="width:0.75rem;" /> <span class="f_26 clr_f ver_mid"  id="attr">已关注</span></li>
					<li onclick="collect();"><img src="<%=basePath%>/js/appWeb/images/a3.png" class="ver_mid" style="width:0.75rem;" /> <span class="f_26 clr_f ver_mid" id="collect">收藏</span></li>
					<li onclick="report();"><img src="<%=basePath%>/js/appWeb/images/a4.png" class="ver_mid" style="width:0.75rem;" /> <span class="f_26 clr_f ver_mid" id="report">举报</span></li>
				</ul>
			</div>
			<div class="f_28 clr_3 mt_20">{{= content}}</div>

			<div class="dis_f ali_ct jus_bt flex_w quan_img mt_20">
				<div id="images"></div>
			</div>
		</div>

	
			<div class="dis_f ali_ct jus_bt bg_f borderbot1" style="padding:0 1.5rem;" >
				<div class="pad_20" id="ds" onclick="change(1);" style="border-bottom:1px solid #f95d47;">
					<img id="dsImg" src="<%=basePath%>/js/appWeb/images/dsh.png" class="ver_mid" style="width:0.8rem;" />
					<span id="dsSpan" class="f_20 clr_r ver_mid">{{= cityCirclePerson}}</span>
				</div>
				<div class="pad_20" id="xx" onclick="change(8);" >
					<img id="xxImg" src="<%=basePath%>/js/appWeb/images/xx.png" class="ver_mid" style="height:0.7rem;" />
					<span id="xxSpan" class="f_20 clr_6 ver_mid">{{= commentCount}}</span>
				</div>
				<div class="pad_20" id="dz" onclick="change(7);" >
					<img id="dzImg" src="<%=basePath%>/js/appWeb/images/zan2.png" class="ver_mid" style="height:0.75rem;" />
					<span id="dzSpan" class="f_20 clr_6 ver_mid">{{= topCount}}</span>
				</div>
			</div>
		</script>
		
		<div id="srcUrl" style="display: none;"><%=basePath%></div>
		
		<script id="detail_image_tmpl" type="text/x-jquery-tmpl">
			<img src="{{= image}}" class="ver_mid" style="width:4.75rem;height:4.75rem;" />
		</script>
		
		
		<script id="shang_list_tmpl" type="text/x-jquery-tmpl">
			<div class="pad_30 bg_f borderbot1" >
				<div class="dis_f ali_ct jus_bt">
					<div class="dis_f ali_ct" >
						<img onclick="window.location.href='/mts/appWeb/appuser/otherUser.jsp?id={{if appUser}}{{= appUser.id}}{{/if}}'" src="{{if appUser}}{{if appUser.header}}{{= appUser.header}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}" class="dis_b" style="width:1.75rem;border-radius: 0.9rem;margin-right:0.5rem;" />
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
							<div class="f_22 clr_9 mt_10">{{= createTime}}</div>
						</div>
					</div>
					<div class="f_24 clr_r">打赏 <span class="f_32">￥{{= money}}</span></div>
				</div>
				<div class="f_28 clr_3 mt_20">{{= content}}</div>
			</div>
		</script>
		
		
		<script id="content_tmpl" type="text/x-jquery-tmpl">
			<div class="pad_30 bg_f borderbot1" onclick="toUser({{if appUser}}{{= appUser.id}}{{/if}},'{{if appUser}}{{= appUser.name}}{{/if}}');">
			<div class="dis_f ali_ct jus_bt">
				<div class="dis_f ali_ct" >
					<img onclick="window.location.href='/mts/appWeb/appuser/otherUser.jsp?id={{if appUser}}{{= appUser.id}}{{/if}}'" src="{{if appUser}}{{if appUser.header}}{{= appUser.header}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}" class="dis_b" style="width:1.75rem;border-radius: 0.9rem;margin-right:0.5rem;" />
					<div>
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
							<div class="f_22 clr_9 mt_10">{{= createTime}}</div>
						</div>
					</div>
				</div>
			</div>
			<div class="f_28 clr_3 mt_20">{{if toUserName}}回复  <font color="#003D79">{{= toUserName}}</font>：{{/if}}{{= content}}</div>
			</div>
		</script>
		
		
		<script id="top_tmpl" type="text/x-jquery-tmpl">
		<div class="pad_30 bg_f bordertop1">
			<div class="dis_f ali_ct jus_bt pos_rela" >
				<div class="dis_f ali_ct" >
					<img onclick="window.location.href='/mts/appWeb/appuser/otherUser.jsp?id={{if appUser}}{{= appUser.id}}{{/if}}'" src="{{if appUser}}{{if appUser.header}}{{= appUser.header}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}" class="dis_b" style="width:1.75rem;border-radius: 0.9rem;margin-right:0.5rem;" />
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
						<div class="f_22 clr_9 mt_10">{{if sign}}{{= sign}}{{else}}美天给生活一点惊喜{{/if}}</div>
					</div>
				</div>

				<div class="f_18 clr_9">{{= createTime}}</div>
			</div>
		</div>
		</script>
		


	<div class="kq_mask" style="display: none;" id="showDashang">
		<!-- <div class="dashang_bg pos_rela" style="padding-top:5.8rem;">
			<a href="javascript:;" class="dis_b close_kq_mask"></a>
			<input class="ipt3 f_26 clr_6 ipt_dashang" id="money" onkeyup="javascript:$('#balance').html($('#money').val());" type="text" placeholder="￥请填写打赏金额" />
			<input class="ipt3 f_26 clr_6 ipt_msg" id="content" type="text" placeholder="留下你的打赏留言吧" />
			<div class="f_20 clr_3 dashang_ye" >使用账户余额付款 ¥<span id="balance"">0</span> <a href="#" class="clr_b">更换</a></div>
		</div> -->
		
		<div class="dashang_bg pos_rela" style="padding-top:5.8rem;">

			<a href="javascript:;" class="dis_b close_kq_mask"></a>
			<a onclick="shaizi();"><img src="<%=basePath%>/js/appWeb/images/shaizi.png" class="dis_b" style="position: absolute;top:7.1rem;right:1.5rem;width:0.7rem;" /></a>
			
			<input class="ipt3 f_26 clr_6 ipt_dashang" style="width: 58%" id="money" onkeyup="javascript:$('#balance').html($('#money').val());" type="text" placeholder="￥请填写打赏金额" />
			<input class="ipt3 f_26 clr_6 ipt_msg" id="contentVal" type="text" placeholder="留下你的打赏留言吧" />

			<a href="javascript:dashang()"><img src="<%=basePath%>/js/appWeb/images/shang.png" class="shang_img" /></a>
			<div class="f_20 clr_3 dashang_ye" >使用账户余额付款 ¥<span id="balance"">0</span> <a href="#" class="clr_b">更换</a></div>
		</div>
	</div>

	<script>
		$('.close_kq_mask').click(function(){
			$(this).parents('.kq_mask').hide();
		});
	</script>
	
	<div id="srk_box" style="display:none;" onclick="hideInput();"  class="srk_box">
	</div>
	<div  id="inputBtn"  style="display:none;">
			<div class="pad_20 bottom_srk">
				<input id="content" class="inpt2 f_28 clr_9" type="text" placeholder="回复：说点儿什么吧...">
				<div class="fs" onclick="fasong();">发送</div>
				<div style="clear: both"></div>
			</div>
		</div>
	

	<script type="text/javascript">
	$('.more_ul_toggle').click(function(){
		$(this).siblings('.more_ul').toggle();
		$(this).siblings('.arr_up_down').toggle();
	});
	
	function showColl(show){
		$(show).siblings('.more_ul').toggle();
		$(show).siblings('.arr_up_down').toggle();
		$(".whte").toggle();
	};
	
	</script>
		
	</div>
</body>

</html>