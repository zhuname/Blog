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
	src="<%=basePath%>/js/appWeb/posterPackage/app_posterPackageDetail.js"></script>
	
	
	
	
<script src="<%=basePath%>/js/appWeb/weixinjs/swiper.min.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/appWeb/css/swiper.min.css" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0"
	name="viewport">
<title>红包详情</title>
<style type="text/css">

</style>
</head>

<body>
			<div id="show-img-box" class=" show-img-box pwd" style="padding:0">
				<div class="pwd-box">
					<span style="height:2rem;display:block;font-size:.5rem;">预定成功，请去我的预定界面查看兑换码进行兑换</span>
					<p onclick="javascript:window.location.href='/mts/appWeb/appuser/myAppoint.jsp';">确定</p>
				</div>
			</div>
			<div class="pay-type dis_f ali_end">
				<ul>
					<li>
						<img src="<%=basePath%>/js/appWeb/images/close.png" onclick="javascript:$('.pay-type').css('top','100%')">
						<p>请选择支付方式</p>
					</li>
					<li>
						<img src="<%=basePath%>/js/appWeb/images/icon-weixin.png">
						<p>微信支付</p>
					</li>
					<li>
						<img src="<%=basePath%>/js/appWeb/images/icon-zfb.png">
						<p>支付宝</p>
					</li>

					<li>
						<img src="<%=basePath%>/js/appWeb/images/icon-money.png">
						<p>余额支付:￥<span>33.12</span></p>
					</li>
				</ul>
			</div>
	<div class="wraper overh " style="position: relative;">
<<<<<<< HEAD
		<div class="dis_f ali_ct jus_bt pad_20 bg_f borderbot1 pos_rela" id="headers">
			<a onclick="javascript:window.history.back();" ><img src="<%=basePath%>/js/appWeb/images/back.png" class="dis_b" style="width:1rem;" /></a>
=======
		<div class="dis_f ali_ct jus_bt pad_20 bg_f borderbot1 pos_rela">
			<a onclick="javascript:hrefIndexShare();" ><img src="<%=basePath%>/js/appWeb/images/back.png" class="dis_b" style="width:1rem;" /></a>
>>>>>>> 055b0d9de6133c487fb661906cdb486cadaedb0a
			<p class="f_30 clr_3">海报详情</p>
						<div class="whte"></div>
			<a><img src="<%=basePath%>/js/appWeb/images/more.png" class="dis_b more_ul_toggle" style="width:1rem;" /></a>
			
			<img src="<%=basePath%>/js/appWeb/images/images/arr_up.png" style="width:0.55rem" class="dis_b arr_up_down dis_n" />
			<ul class="more_ul pad_20 dis_n">
				<li onclick="attr();"><img src="<%=basePath%>/js/appWeb/images/a2.png" class="ver_mid" style="width:0.75rem;" /> <span class="f_26 clr_f ver_mid"  id="attr">关注</span></li>
				<li onclick="collect();"><img src="<%=basePath%>/js/appWeb/images/a3.png" class="ver_mid" style="width:0.75rem;" /> <span class="f_26 clr_f ver_mid" id="collect">收藏</span></li>
				<li onclick="report(this);"><img src="<%=basePath%>/js/appWeb/images/a4.png" class="ver_mid" style="width:0.75rem;" /> <span class="f_26 clr_f ver_mid" id="report">举报</span></li>
			</ul>

			<script type="text/javascript">
				$('.more_ul_toggle').click(function(){
					$('.more_ul').toggle();
					$('.arr_up_down').toggle();
					$(".whte").show();
				});
				$(".whte").click(function(){
				$(this).hide();
					$('.more_ul').toggle();
					$('.arr_up_down').toggle();
				})
			</script>
		</div>
					<div class="public_App" style="top:2rem;">
			<img src="<%=basePath%>/js/appWeb/images/close_hb.png" id="close_app">
			<img src="<%=basePath%>/js/appWeb/images/App_icon.png">
			<p>领福利，发活动，用APP免费参加</p>
			<span onclick="appLink()">立即打开</span>
		</div>
		<script type="text/javascript">
			$("#close_app").click(function(){
				$(".public_App").remove()
			})
		</script>
		
		
		<div id="detail">
		
		
		
		</div>
		<div id="content">
		
		</div>


		<div class="pad_30"></div>
		<div class="pad_30"></div>
		
		<div style="display:none" id="showZan"><%=basePath%>/js/appWeb/images/zan2.png</div>
		<div style="display:none" id="showYiZan"><%=basePath%>/js/appWeb/images/zan.png</div>

		<div class="fixed_comment dis_f ali_ct jus_bt" style="z-index:110;transfrom:translate(0,0,0);padding: 0.2rem .5rem;">
			<img id="zanImg" src="<%=basePath%>/js/appWeb/images/zan2.png" class="ver_mid"  onclick="oper(1);"  style="height:1rem;" />
			<input onclick="isDenglu();" placeholder="说点什么吧......" class="ipt1 f_26 clr_9" id="comment" type="text" />
			<input class="btn1 f_26 clr_f" type="button" onclick="oper(2);"  value="发送" />
		</div>
	</div>
	
	<script id="detail_tmpl" type="text/x-jquery-tmpl">
		<div class="bg_f dis_f ali_ct pad_30" >
			<img onclick="window.location.href='/mts/appWeb/appuser/otherUser.jsp?id={{if appUser}}{{= appUser.id}}{{/if}}'" src="{{if appUser}}{{if appUser.header}}{{= appUser.header}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}" class="dis_b" style="width:2rem;height:2rem;border-radius: 1rem;margin-right:0.5rem;" />
			<div>
				
					<span class="ver_mid f_28 clr_3">{{if appUser}}{{= appUser.name}}{{/if}} </span>
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
					<div class="f_18 clr_6 mt_10">{{if appUser}}{{if appUser.sign}}{{= appUser.sign}}{{else}}美天给生活一点惊喜{{/if}}{{else}}美天给生活一点惊喜{{/if}}</div>
				
			</div>
		</div>

		<div class="bg_f padl_20">
			<div class="bannD pos_rela" id="bann">
				<div class="hd">
					<ul></ul>
				</div>
				<div class="bd">
					<ul id="lunbo">
					</ul>
				</div>
			</div>

			<div class="f_40 clr_3 mt_20">
				<img src="<%=basePath%>/js/appWeb/images/bar.png" class="ver_mid" style="width:0.1rem;" />
				<span class="ver_mid">{{= title}}</span>
			</div>

			<div class="f_28 clr_6 mt_20 clr_he" style="line-height:1.2rem;text-indent: 1.4rem;">
				<pre  style="white-space: pre-wrap;line-height:1rem;">{{= descr}}</pre>
			</div>

			<div class="dis_f ali_ct jus_rt pad_30">
				<div class="" style="margin-right:2rem;display:inline-block;">
					<img src="<%=basePath%>/js/appWeb/images/eye2.png" class="dis_b" style="height:0.4rem;display:inline-block;vertical-align: middle" />
					<div class="clr_6" style="display:inline-block;vertical-align: middle">&nbsp; {{= lookNum}}</div>
				</div>
				<div class="" style="margin-right:2rem;display:inline-block;">
					<img src="<%=basePath%>/js/appWeb/images/msg3.png" class="dis_b" style="height:0.6rem;display:inline-block;vertical-align: middle" />
					<div class="clr_6" style="display:inline-block;vertical-align: middle">&nbsp; {{= commentCount}}</div>
				</div>
				<div class="dis_f ali_ct" style="display:inline-block;">
						{{if isTop==1 }}
						<img src="<%=basePath%>/js/appWeb/images/praise2.png" class="dis_b" style="height:0.6rem;display:inline-block;vertical-align: middle " />
						{{else}}
						<img src="<%=basePath%>/js/appWeb/images/praise3.png" class="dis_b" style="height:0.6rem;display:inline-block;vertical-align: middle " />
						{{/if}}
					<div class="clr_6" style="display:inline-block;vertical-align: middle">&nbsp; {{= topCount}}</div>
				</div>
			</div>
		</div>

		{{if isRelevance}}

		<div class="bg_f dis_f ali_ct jus_bt xjq_wrap" >
			<div class="dis_f ali_ct">
				<div class="xjq_box f_20 clr_f dis_f ali_ct jus_ct flex_col"  onclick="window.location.href='/mts/appWeb/card/cardDetail.jsp?id={{= cardId}}';">
					<img src="<%=basePath%>/js/appWeb/images/xjq.png" class="ver_mid" style="width:0.9rem;" />
					<p>{{= cardCategoryName}}</p>
				</div>
			
				<div class="f_24 clr_6 xjq_lq_box dis_f ali_ct jus_ct" >已有{{= cardLqNum}}次领取</div>
			</div>
			<div class="dis_f ali_ct jus_bt"  onclick="window.location.href='/mts/appWeb/card/cardUserList.jsp?id={{= cardId}}';">
				<div class="ceng_img" id="lingquan" >
				</div>
				<img src="<%=basePath%>/js/appWeb/images/right.png" onclick="window.location.href='/mts/appWeb/card/cardUserList.jsp?id={{= cardId}}';" class="dis_b" style="width:0.4rem;margin:0 0.5rem;" />
			</div>
		</div>
		{{/if}}
		{{if isAppoint}}
		{{if isLook==3}}
		<div class="bg_f dis_f ali_ct jus_bt xjq_wrap">
			<div class="dis_f ali_ct">
				<div class="xjq_box f_20 clr_f dis_f ali_ct jus_ct flex_col" style="background: #eb5744;" onclick="javascript:window.location.href='/mts/appWeb/appuser/appuserLogin.jsp';">
					<img src="<%=basePath%>/js/appWeb/images/yyyl.png" class="ver_mid" style="width:0.9rem;" />
					<p >预订有礼</p>
				</div>

				<div class="f_24 clr_6 xjq_lq_box dis_f ali_ct jus_ct">已有{{= appointCount}}次预订</div>
			</div>

			<div class="dis_f ali_ct jus_bt"  onclick="window.location.href='/mts/appWeb/appoint/appointUserList.jsp?itemId={{= id}}&type=1';">
				<div class="ceng_img" id="yuyue">
				</div>
				<img src="<%=basePath%>/js/appWeb/images/right.png" class="dis_b" style="width:0.4rem;margin:0 0.5rem;" />
			</div>
		</div>
		{{else}}

<div class="bg_f dis_f ali_ct jus_bt xjq_wrap">
			<div class="dis_f ali_ct">
				<div class="xjq_box f_20 clr_f dis_f ali_ct jus_ct flex_col" style="background: #eb5744;" onclick="reserver()">
					<img src="<%=basePath%>/js/appWeb/images/yyyl.png" class="ver_mid" style="width:0.9rem;" />
					<p >预订有礼</p>
				</div>

				<div class="f_24 clr_6 xjq_lq_box dis_f ali_ct jus_ct">已有{{= appointCount}}人预订</div>
			</div>

			<div class="dis_f ali_ct jus_bt"  onclick="window.location.href='/mts/appWeb/appoint/appointUserList.jsp?itemId={{= id}}&type=1';">
				<div class="ceng_img" id="yuyue">
				</div>
				<img src="<%=basePath%>/js/appWeb/images/right.png" class="dis_b" style="width:0.4rem;margin:0 0.5rem;" />
			</div>
		</div>

		{{/if}}
		{{/if}}

		<div class="bg_f dis_f ali_ct pad_30 jus_bt" style="border-top:1px solid #f2f4f7;">
			<div id="lingqu"  onclick="window.location.href='/mts/appWeb/posterPackage/posterPackageUsersList.jsp?itemId={{= id}}&type=1';">
			</div>
			<div class="clr_3 f_30 dis_f" onclick="window.location.href='/mts/appWeb/posterPackage/posterPackageUsersList.jsp?itemId={{= id}}&type=1';" style="line-height:1.5rem;-webkit-align-items:center;">
<span class="dis_b" style="margin-top:-0.5rem;">…</span>

			<img onclick="window.location.href='/mts/appWeb/posterPackage/posterPackageUsersList.jsp?itemId={{= id}}&type=1';" src="<%=basePath%>/js/appWeb/images/right.png" class="dis_b" style="width:0.4rem;margin:0 0.5rem;" />
</div>
		</div>

		<div class="bg_f dis_f ali_ct jus_ct">
			<p style="margin-right:1.5rem;">
				<img src="<%=basePath%>/js/appWeb/images/hongbao2.png" class="ver_mid" style="height:0.7rem;" />
				<span class="clr_r f_30 ver_mid">{{= sumMoney}}元</span>
			</p>
			<p class="f_26 clr_3">
				剩余<span class="clr_r f_30">{{= balance}}元</span>
			</p>
		</div>

		<div class="pad_30 bg_f">
			{{if isLook==1}}
			<a class="f_26 clr_f dis_b waiting_check_a" style="background: #c8c8cc;">已抢</a>
			{{else}}
			{{if status}}	
			{{if status==3}}
			<a class="f_26 clr_f dis_b waiting_check_a" id="ljll"  onclick="lingqu();" style="background: #f95d47;display:none;">立即领取</a>
			<a class="f_26 clr_f dis_b waiting_check_a" id="lqjs"  style="background: #c8c8cc;">倒计时<span id="miao">10</span>秒抢红包</a>
			{{else status==4}}
			<a class="f_26 clr_f dis_b waiting_check_a" style="background: #c8c8cc;">已抢完</a>
			{{/if}}	
			{{/if}}

			{{else isLook==3}}
			<a class="f_26 clr_f dis_b waiting_check_a" id="ljll"  onclick="javascript:window.location.href='/mts/appWeb/appuser/appuserLogin.jsp';" style="background: #f95d47;display:none;">立即领取</a>
			{{/if}}
		</div>

		<div class="mt_20 bg_f borderbot1">
			<ul class="dis_f ali_ct jus_bt" style="width:10rem;margin:0 auto;">
				<li class="pad_20 bord_norm" onclick="change(2);">
					<img id="contentChe" src="<%=basePath%>/js/appWeb/images/msg2.png" class="ver_mid" style="height:0.75rem;" />
					<img id="contentNo" src="<%=basePath%>/js/appWeb/images/msg3.png" class="ver_mid" style="height:0.75rem;" />
					<span id="contentNum" class="clr_r f_26 ver_mid">{{= commentCount}}</span>
				</li>
				
				<li class="pad_20 bord_norm" onclick="change(1);">
					<img id="topChe" src="<%=basePath%>/js/appWeb/images/zan3.png" class="ver_mid" style="height:0.75rem;" />
					<img id="topNo" src="<%=basePath%>/js/appWeb/images/praise3.png" class="ver_mid" style="height:0.75rem;" />
					<span  id="topNum"  class="clr_3 f_26 ver_mid">{{= topCount}}</span>
				</li>
			</ul>
		</div>
		<div class="kq_mask" id="payTmpl" style="display: none;z-index:200;">
        <div class="yuyue_bg pos_rela" style="padding-top:5.8rem;">
            <a href="javascript:;" class="dis_b close_kq_mask" onclick="removeRes()"></a>
            <input class="ipt3 f_26 clr_6 ipt_dashang" style="top:6.5rem;outline:none;" onkeyup="changeBalance();" id="money" type="number" placeholder="￥ 请输入金额"/>
            <input class="ipt3 f_26 clr_6 ipt_msg" style="top:8.3rem;left:2.6rem;" id="phone" type="text" placeholder="请填写预订电话"/>
            <div class="f_20 clr_6" style="margin-top:3rem;line-height: 0.8rem;padding:1rem 1rem 0 1rem;">
               {{= appointExplain}}
            </div>
            <div class="f_20 clr_3 al_ct pad_20" >使用账户余额付款 ¥<span id="userBalance">0.00</span> <a href="javascript:;" class="clr_b" onclick="javascript:$('.pay-type').css('top',0)" style="padding:0 .2rem;">更换</a></div>

            <input type="button" onclick="yuyue();"; class="f_26 clr_f dis_b waiting_check_a" style="background: #f95d47;border:0;width:9.5rem;height:1.7rem;line-height: 1.7rem;" value="立即预订"/>
        </div>


    	</div>

	</script>
	<script>

		$(".pay-type").click(function(){
			$(this).css("top","100%")
		})
		$(".pay-type ul").click(function(){
			event.stopPropagation();    	
		})
	</script>
		 
	
	
	<script id="yuyue_tmpl" type="text/x-jquery-tmpl">
		<img onclick="window.location.href='/mts/appWeb/appuser/otherUser.jsp?id={{if appUser}}{{= appUser.id}}{{/if}}'" src="{{if appUser}}{{if appUser.header}}{{= appUser.header}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}" class="ver_mid" style="width:1.25rem;height:1.25rem;border-radius: 0.7rem;" />
	</script>
	
	<script id="lingquan_tmpl" type="text/x-jquery-tmpl">
		<img onclick="window.location.href='/mts/appWeb/appuser/otherUser.jsp?id={{= id}}'" src="{{if header}}{{= header}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}" class="ver_mid" style="width:1.25rem;height:1.25rem;border-radius: 0.7rem;" />
	</script>
	<script id="lingqu_tmpl" type="text/x-jquery-tmpl">
		<img onclick="window.location.href='/mts/appWeb/appuser/otherUser.jsp?id={{if appUser}}{{= appUser.id}}{{/if}}'" src="{{if appUser}}{{if appUser.header}}{{= appUser.header}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}" class="ver_mid" style="width:1.5rem;height:1.5rem;border-radius: 0.8rem;" />
	</script>
	<script id="content_tmpl" type="text/x-jquery-tmpl">
		<div class="pad_30 bg_f borderbot1" onclick="toUser({{if appUser}}{{= appUser.id}}{{/if}},'{{if appUser}}{{= appUser.name}}{{/if}}');">
			<div class="dis_f ali_ct" >
				<img onclick="window.location.href='/mts/appWeb/appuser/otherUser.jsp?id={{if appUser}}{{= appUser.id}}{{/if}}'" src="{{if appUser}}{{if appUser.header}}{{= appUser.header}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}" class="dis_b" style="width:1.75rem;height:1.75rem;border-radius: 0.9rem;margin-right:0.5rem;" />
				<div>
					<span class="ver_mid f_28 clr_3">{{if appUser}}{{= appUser.name}}{{/if}}</span>
					{{if appUser}}
					{{if appUser.sex}}
							{{if appUser.sex == '男'}}
							<img src="<%=basePath%>/js/appWeb/images/male.png" class="ver_mid" style="width:0.4rem;" />
							{{else appUser.sex == '女'}}
							<img src="<%=basePath%>/js/appWeb/images/female2.png" class="ver_mid" style="width:0.4rem;" />
							{{/if}}
						{{/if}}
					{{/if}}
					<img src="<%=basePath%>/js/appWeb/images/badge.png" class="ver_mid" style="width:0.5rem;" />
					<div class="f_22 clr_9">{{= createTime}}</div>
				</div>
			</div>
			<div class="f_28 clr_3 mt_20">{{if toUserName}}回复  <font color="#003D79">{{= toUserName}}</font>：{{/if}}{{= content}}</div>
		</div>
	</script>
	
	<script id="top_tmpl" type="text/x-jquery-tmpl">
		<div class="pad_30 bg_f borderbot1">
			<div class="dis_f ali_ct" >
				<img onclick="window.location.href='/mts/appWeb/appuser/otherUser.jsp?id={{if appUser}}{{= appUser.id}}{{/if}}'" src="{{if appUser}}{{if appUser.header}}{{= appUser.header}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}" class="dis_b" style="width:1.75rem;height:1.75rem;border-radius: 0.9rem;margin-right:0.5rem;" />
				<div>
					<span class="ver_mid f_28 clr_3">{{if appUser}}{{= appUser.name}}{{/if}}</span>
						{{if appUser.sex}}
							{{if appUser.sex == '男'}}
							<img src="<%=basePath%>/js/appWeb/images/male.png" class="ver_mid" style="width:0.4rem;" />
							{{else appUser.sex == '女'}}
							<img src="<%=basePath%>/js/appWeb/images/female2.png" class="ver_mid" style="width:0.4rem;" />
							{{/if}}
						{{/if}}
					<img src="<%=basePath%>/js/appWeb/images/badge.png" class="ver_mid" style="width:0.5rem;" />
					<div class="f_22 clr_9">{{= createTime}}</div>
				</div>
			</div>
			<div class="f_28 clr_3 mt_20">{{if appUser}}{{if appUser.sign}}{{= appUser.sign}}{{else}}美天给生活一点惊喜{{/if}}{{else}}美天给生活一点惊喜{{/if}}</div>
		</div>
	</script>
	<script id="lunbo_list_tmpl" type="text/x-jquery-tmpl">
			<li><img src="{{= image}}" class="dis_b" style="width:15rem;" /></li>
	</script>
	
	<div class="bonus_mask" onclick="$('.bonus_mask').hide()" style="display:none;">
			<div class="honus_bg pos_rela" onclick="javascript:event.stopPropagation()">
				<div style="position: absolute; height: 2rem;width:7rem; bottom: 0.5rem; left: 3rem"  onclick="lingquList();"></div>
			<img src="<%=basePath%>/js/appWeb/images/close_hb123.png" class="ver_mid close_hb" style="width:2rem;height:2rem;"  onclick="javascript:$('.bonus_mask').hide();"/>
				<div style="font-size:2rem;color:#fbd39c;padding-top:5.5rem;" class="al_ct" ><span id="moneyShow"></span><span class="f_24">元</span></div>

				<div class="al_ct" style="padding-top:4.5rem;">
					<img class="ver_mid" id="header" style="width:3rem;border-radius: 1.5rem;" />
					<p class="f_28 mt_30" style="color:#e08877;"  id="name"></p>
					
				</div>
			</div>
		</div>

		<div class="command_mask" style="width:16rem;display: none;">
			<div class="command pad_20">
				<input class="ipt4 f_28 clr_9" placeholder="请输入加密口令方能进行领取哦" type="text" />
				<input class="btn4 f_30 clr_f mt_30 qd_click" value="确定"  onclick="lingqu();" type="button" />
			</div>
		</div>
		
		
		
			<!-- 提示窗 -->
<div class="alert-box dis_f jus_ct ali_end" style="position:fixed;width:100%;height:100%;background:rgba(0,0,0,.6);left:0;top:100%;z-index:1000;transition:all .5s;">
<div class="Report dis_f" style="width:100%;height:2rem;background:#fff;padding:.4rem;box-sizing:border-box;">
	<input type="text" id="content1" style="flex:1;border:.06rem solid #ddd;border-radius:.2rem;padding-left:.2rem;" placeholder="请填写举报内容">
	<span onclick="jubao();" style="width:3rem;height:100%;display:inline-block;text-align:center;line-height:1.2rem;margin-left:.4rem;background:#00a0e2;color:#fff;">发送</span>
</div>
</div>


	<script type="text/javascript">
					$('.filter_toggle').click(function(){
						$('.xx_pic').toggle();
						$('.xx_daohang').toggle();
						$('#black-box').toggle();
						console.log($('#black-box'))
					});
					$('#black-box').click(function(){
						$(this).toggle();
						$('.more_ul_toggle').siblings("ul").hide()
						$('.more_ul_toggle').siblings("img").hide()
					});
					$(".alert-box").click(function(){
						$(this).css("top","100%");
						$("#black-box").hide();
						$('.more_ul_toggle').siblings("ul").hide()
						$('.more_ul_toggle').siblings("img").hide()
					})
					$(".Report").click(function(){
						event.stopPropagation();
					})
$(window).scroll(function(){
	if($("body").scrollTop()>=$("#headers").outerHeight()){
		$(".public_App").css("top",0)
	}else{
		$(".public_App").css("top","2rem")
	}
})
				</script>
		
	
</body>

</html>