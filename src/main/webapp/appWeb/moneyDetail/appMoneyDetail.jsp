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
	src="<%=basePath%>/js/appWeb/moneyDetail/app_moneyDetail.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0"
	name="viewport">
<title>我的钱包</title>




</head>

<body>

	<div class="wraper overh ">
		

		<div class="bg_r pad_30 dis_f ali_ct jus_ct flex_col">

			<div class="f_30 clr_f pos_rela w_100 al_ct">我的钱包
				<img src="<%=basePath%>/js/appWeb/images/back4.png" onclick="javascript:window.history.back();" class="dis_b back4" style="height:0.8rem;" />
			</div>
			<div class="f_26 clr_f mt_30 w_100">账户余额:</div>

			<div class="f_26 clr_f mt_30">￥<span style="font-size:1.5rem;" id="balance">0.0</span></div>

			<div class="mt_40">
				<a  onclick="window.location.href='/mts/appWeb/appuser/chongzhi.jsp';"  class="f_26 clr_r a_btn3" style="margin-right:1rem;">充值</a>
				<!-- <a  onclick="window.location.href='/mts/appWeb/moneyDetail/tixian.jsp';"  class="f_26 clr_f a_btn4" >提现</a> -->
				<a   onclick="window.location.href='http://app.mtianw.com/mts/shareApp/down.html';"   class="f_26 clr_f a_btn4" >提现</a>
				
				
			</div>

		</div>

		<div class="f_28 clr_3 dis_f ali_ct bg_f pad_30">
			<img src="<%=basePath%>/js/appWeb/images/szjl.png" class="dis_b" style="height:0.8rem;margin-right:0.5rem;" />
			收支记录
		</div>

		<div id="moneyDetailList">
		
		</div>

		<!-- <div class="dis_f pad_30 bg_f jus_bt borderbot1">
			<div class="f_26 clr_he dis_f jus_bt flex_col">
				3号<p class="f_30">16:46</p>
			</div>
			<div class="f_28 clr_3 dis_f flex_col jus_bt" style="width:6rem;">
				充值<p class="f_24 clr_he">充值失败 <a href="#" class="clr_r">查看原因</a></p>
			</div>
			<div class="f_28 clr_3 dis_f jus_rt flex_col ali_end">
				<span class="f_40 clr_r"><span class="f_24">+</span>0.5</span>
				<p class="f_22 clr_he">剩余金额：￥0.5</p>
			</div>
		</div> -->


	</div>
	
	<script id="moneyDetail_monthList_tmpl" type="text/x-jquery-tmpl">
		<div class="f_20 clr_he  pad_2030" style="background: #efefef;">
			{{= year}}年{{= month}}月
		</div>
	</script>
	<script id="moneyDetail_list_tmpl" type="text/x-jquery-tmpl">
		<div class="dis_f pad_30 bg_f jus_bt borderbot1">
			<div class="f_26 clr_he dis_f jus_bt flex_col">
				{{= day}}号<p class="f_30">{{= time}}</p>
			</div>

			<div class="f_28 clr_3 dis_f flex_col jus_bt" style="width:6rem;">

				{{if type}}

					{{if type==1}}
					海报红包
					{{else type==2}}
					视频红包
					{{else type==3}}
					购买卡券
					{{else type==4}}
					充值
					{{else type==5}}
					发布视频
					{{else type==6}}
					发布海报 
					{{else type==7}}
					提现成功
					{{else type==8}}
					发布卡券人收益
					{{else type==9}}
					提现失败
					{{else type==10}}
					提现申请中
					{{else type==11}}
					图片红包审核失败
					{{else type==12}}
					视频红包审核失败
					{{else type==13}}
					预约支付
					{{else type==14}}
					被打赏
					{{else type==15}}
					发布红包人收益
					{{else type==16}}
					被打赏
					{{else type==17}}
					后台余额变动
					{{/if}}
				{{/if}}<p class="f_24 clr_he">{{= content}}</p>
			</div>

			<div class="f_28 clr_3 dis_f jus_rt flex_col ali_end">
				<span class="f_40 clr_r">{{= money}}</span>
				<p class="f_22 clr_he">剩余金额：￥{{= balance}}</p>
			</div>
		</div>
	</script>
	
	
</body>


</html>
