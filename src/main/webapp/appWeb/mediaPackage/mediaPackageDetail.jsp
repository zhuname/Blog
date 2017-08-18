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
	src="<%=basePath%>/js/appWeb/mediaPackage/app_mediaPackageDetail.js"></script>
	
	
	
	
<script src="<%=basePath%>/js/appWeb/weixinjs/swiper.min.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/appWeb/css/swiper.min.css" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0"
	name="viewport">
<title>视频详情</title>
</head>

<body>

	<div class="wraper overh ">
		<div class="dis_f ali_ct jus_bt pad_20 bg_f borderbot1 pos_rela">
			<a onclick="javascript:window.history.back();" ><img src="<%=basePath%>/js/appWeb/images/back.png" class="dis_b" style="width:1rem;" /></a>
			<a><img src="<%=basePath%>/js/appWeb/images/more.png" class="dis_b more_ul_toggle" style="width:1rem;" /></a>
			
			<img src="images/arr_up.png" style="width:0.55rem" class="dis_b arr_up_down dis_n" />
			<ul class="more_ul pad_20 dis_n">
				<li><img src="<%=basePath%>/js/appWeb/images/a1.png" class="ver_mid" style="width:0.75rem;" /> <span class="f_26 clr_f ver_mid">分享</span></li>
				<li><img src="<%=basePath%>/js/appWeb/images/a2.png" class="ver_mid" style="width:0.75rem;" /> <span class="f_26 clr_f ver_mid">已关注</span></li>
				<li><img src="<%=basePath%>/js/appWeb/images/a3.png" class="ver_mid" style="width:0.75rem;" /> <span class="f_26 clr_f ver_mid">收藏</span></li>
				<li><img src="<%=basePath%>/js/appWeb/images/a4.png" class="ver_mid" style="width:0.75rem;" /> <span class="f_26 clr_f ver_mid">举报</span></li>
			</ul>

			<script type="text/javascript">
				$('.more_ul_toggle').click(function(){
					$('.more_ul').toggle();
					$('.arr_up_down').toggle();
				});
			</script>
		</div>

		
		
		<div id="detail">
		
		
		
		</div>
		<div id="content">
		
		
		
		</div>


		<div class="pad_30"></div>
		<div class="pad_30"></div>

		<div class="fixed_comment dis_f ali_ct jus_bt pad_20">
			<img src="<%=basePath%>/js/appWeb/images/zan.png" class="ver_mid"  onclick="oper(3);"  style="height:1rem;" />
			<input placeholder="说点什么吧......" class="ipt1 f_26 clr_9" id="comment" type="text" />
			<input class="btn1 f_26 clr_f" type="button" onclick="oper(4);"  value="发送" />
		</div>
	</div>
	
	<script id="detail_tmpl" type="text/x-jquery-tmpl">
		<div class="bg_f dis_f ali_ct pad_30" >
			<img src="{{if appUser}}{{= appUser.header}}{{/if}}" class="dis_b" style="width:2rem;border-radius: 1rem;margin-right:0.5rem;" />
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
					<div class="f_18 clr_6 mt_10">{{if appUser}}{{= appUser.sign}}{{/if}}</div>
				
			</div>
		</div>

		<div class="bg_f padl_20">
			
			<video src="{{= mediaUrl}}" style="width:100%;height:30%;" controls="controls">
					您的浏览器不支持 video 标签。
			</video>

			<div class="f_40 clr_3 mt_20">
				<img src="<%=basePath%>/js/appWeb/images/bar.png" class="ver_mid" style="width:0.1rem;" />
				<span class="ver_mid">{{= title}}</span>
			</div>

			<div class="f_28 clr_6 mt_20 clr_he" style="line-height:1.2rem;text-indent: 1.4rem;">
				{{= descr}}
			</div>

			<div class="dis_f ali_ct jus_rt pad_30">
				<div class="dis_f ali_ct" style="margin-right:2rem;">
					<img src="<%=basePath%>/js/appWeb/images/eye2.png" class="dis_b" style="height:0.4rem;" />
					<div class="clr_6">&nbsp; {{= lookNum}}</div>
				</div>
				<div class="dis_f ali_ct" style="margin-right:2rem;">
					<img src="<%=basePath%>/js/appWeb/images/msg3.png" class="dis_b" style="height:0.6rem;" />
					<div class="clr_6">&nbsp; {{= commentCount}}</div>
				</div>
				<div class="dis_f ali_ct">
						{{if isTop==1 }}
						<img src="<%=basePath%>/js/appWeb/images/praise2.png" class="dis_b" style="height:0.6rem;" />
						{{else}}
						<img src="<%=basePath%>/js/appWeb/images/praise3.png" class="dis_b" style="height:0.6rem;" />
						{{/if}}
					<div class="clr_6">&nbsp; {{= topCount}}</div>
				</div>
			</div>
		</div>
		{{if isAppoint}}

		<div class="bg_f dis_f ali_ct jus_bt xjq_wrap" >
			<div class="dis_f ali_ct">
				<div class="xjq_box f_20 clr_f dis_f ali_ct jus_ct flex_col"  onclick="window.location.href='/mts/appWeb/card/cardDetail.jsp?id={{= cardId}}';">
					<img src="<%=basePath%>/js/appWeb/images/xjq.png" class="ver_mid" style="width:0.9rem;" />
					<p >礼物券</p>
				</div>
			
				<div class="f_24 clr_6 xjq_lq_box dis_f ali_ct jus_ct" >已有{{= cardLqNum}}人领取</div>
			</div>
			<div class="dis_f ali_ct jus_bt"  onclick="window.location.href='/mts/appWeb/card/cardUserList.jsp?id={{= cardId}}';">
				<div class="ceng_img" id="lingquan" >
				</div>
				<img src="<%=basePath%>/js/appWeb/images/right.png" class="dis_b" style="width:0.4rem;margin:0 0.5rem;" />
			</div>
		</div>
		{{/if}}
		{{if isRelevance}}
		<div class="bg_f dis_f ali_ct jus_bt xjq_wrap">
			<div class="dis_f ali_ct">
				<div class="xjq_box f_20 clr_f dis_f ali_ct jus_ct flex_col" style="background: #eb5744;" onclick="javascript:$('.kq_mask').show();">
					<img src="<%=basePath%>/js/appWeb/images/yyyl.png" class="ver_mid" style="width:0.9rem;" />
					<p >预约有礼</p>
				</div>

				<div class="f_24 clr_6 xjq_lq_box dis_f ali_ct jus_ct">已有{{= appointCount}}人领取</div>
			</div>

			<div class="dis_f ali_ct jus_bt"  onclick="window.location.href='/mts/appWeb/appoint/appointUserList.jsp?itemId={{= id}}&type=1';">
				<div class="ceng_img" id="yuyue">
				</div>
				<img src="<%=basePath%>/js/appWeb/images/right.png" class="dis_b" style="width:0.4rem;margin:0 0.5rem;" />
			</div>
		</div>
		{{/if}}

		<div class="bg_f dis_f ali_ct pad_30 jus_bt" style="border-top:1px solid #f2f4f7;">
			<div id="lingqu"  onclick="window.location.href='/mts/appWeb/posterPackage/posterPackageUsersList.jsp?itemId={{= id}}&type=1';">
			</div>
			<div class="clr_3 f_30">…</div>

			<img src="<%=basePath%>/js/appWeb/images/right.png" class="dis_b" style="width:0.4rem;margin:0 0.5rem;" />
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
			<a class="f_26 clr_f dis_b waiting_check_a" style="background: #f95d47;">已抢</a>
			{{else}}
			{{if status}}	
			{{if status==3}}
			<a class="f_26 clr_f dis_b waiting_check_a"  onclick="lingqu();" style="background: #f95d47;">立即领取</a>
			{{else status==4}}
			<a class="f_26 clr_f dis_b waiting_check_a" style="background: #f95d47;">已抢完</a>
			{{/if}}	
			{{/if}}
			{{/if}}
		</div>

		<div class="mt_20 bg_f borderbot1">
			<ul class="dis_f ali_ct jus_bt" style="width:10rem;margin:0 auto;">
				<li class="pad_20 bord_norm" onclick="change(4);">
					<img id="contentChe" src="<%=basePath%>/js/appWeb/images/msg2.png" class="ver_mid" style="height:0.75rem;" />
					<img id="contentNo" src="<%=basePath%>/js/appWeb/images/msg3.png" class="ver_mid" style="height:0.75rem;" />
					<span id="contentNum" class="clr_r f_26 ver_mid">{{= commentCount}}</span>
				</li>
				
				<li class="pad_20 bord_norm" onclick="change(3);">
					<img id="topChe" src="<%=basePath%>/js/appWeb/images/zan3.png" class="ver_mid" style="height:0.75rem;" />
					<img id="topNo" src="<%=basePath%>/js/appWeb/images/praise3.png" class="ver_mid" style="height:0.75rem;" />
					<span  id="topNum"  class="clr_3 f_26 ver_mid">{{= topCount}}</span>
				</li>
			</ul>
		</div>



		<div class="kq_mask" id="payTmpl" style="display: none;">
        <div class="yuyue_bg pos_rela" style="padding-top:5.8rem;">
            <a href="javascript:;" class="dis_b close_kq_mask" onclick="javascript:$('.kq_mask').hide();"></a>

            <input class="ipt3 f_26 clr_6 ipt_dashang" style="top:6.5rem;" id="money" type="number" placeholder="￥请填写打赏金额"/>
            <input class="ipt3 f_26 clr_6 ipt_msg" style="top:8.3rem;left:2.6rem;" id="phone" type="text" placeholder="请填写预约电话"/>
            <div class="f_20 clr_6" style="margin-top:3rem;line-height: 0.8rem;padding:1rem 1rem 0 1rem;">
                ● 处为商家填写的预约支付说写的预约支付
                约支付说预约支付说写的<br/>
                ● 处为商家填写的预约支付说明的预约支预
                家填写的预约支付说明的预约支预约支付
                说明的预约支预约支
            </div>
            <div class="f_20 clr_3 al_ct pad_20">使用账户余额付款 ¥6.00 <a href="javascript:;" class="clr_b">更换</a></div>

            <input type="button" onclick="yuyue();"; class="f_26 clr_f dis_b waiting_check_a" style="background: #f95d47;border:0;width:9.5rem;height:1.7rem;line-height: 1.7rem;" value="立即预约"/>
        </div>


    	</div>

	</script>
	
	
		 
	
	
	<script id="yuyue_tmpl" type="text/x-jquery-tmpl">
		<img src="{{if appUser}}{{= appUser.header}}{{/if}}" class="ver_mid" style="width:1.25rem;border-radius: 0.7rem;" />
	</script>
	
	<script id="lingquan_tmpl" type="text/x-jquery-tmpl">
		<img src="{{if header}}{{= header}}{{/if}}" class="ver_mid" style="width:1.25rem;border-radius: 0.7rem;" />
	</script>
	<script id="lingqu_tmpl" type="text/x-jquery-tmpl">
		<img src="{{if appUser}}{{= appUser.header}}{{/if}}" class="ver_mid" style="width:1.5rem;border-radius: 0.8rem;" />
	</script>
	<script id="content_tmpl" type="text/x-jquery-tmpl">
		<div class="pad_30 bg_f borderbot1">
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
					<img src="<%=basePath%>/js/appWeb/images/badge.png" class="ver_mid" style="width:0.5rem;" />
					<div class="f_22 clr_9">{{= createTime}}</div>
				</div>
			</div>
			<div class="f_28 clr_3 mt_20">{{= content}}</div>
		</div>
	</script>
	
	<script id="top_tmpl" type="text/x-jquery-tmpl">
		<div class="pad_30 bg_f borderbot1">
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
					<img src="<%=basePath%>/js/appWeb/images/badge.png" class="ver_mid" style="width:0.5rem;" />
					<div class="f_22 clr_9">{{= createTime}}</div>
				</div>
			</div>
			<div class="f_28 clr_3 mt_20">{{if appUser}}{{= appUser.sign}}{{/if}}</div>
		</div>
	</script>
	<script id="lunbo_list_tmpl" type="text/x-jquery-tmpl">
			<li><img src="{{= image}}" class="dis_b" style="width:15rem;" /></li>
	</script>
	
	<div class="bonus_mask" style="display: none;">

			<div class="honus_bg pos_rela">
				<div style="position: absolute; height: 2rem;width:7rem; bottom: 0.5rem; left: 3rem"  onclick="lingquList();"></div>
			<img src="<%=basePath%>/js/appWeb/images/close_hb.png" class="ver_mid close_hb" style="width:1rem;height:1rem;"  onclick="javascript:$('.bonus_mask').hide();"/>
				<div style="font-size:2rem;color:#fbd39c;padding-top:5.5rem;" class="al_ct" ><span id="money"></span><span class="f_24">元</span></div>

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
	
</body>

</html>