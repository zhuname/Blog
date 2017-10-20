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
	src="<%=basePath%>/js/appWeb/moneyDetail/app_tixianSave.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0"
	name="viewport">
<title>首页</title>
<style>
body{background: #f0f2f5;}

 		::-webkit-scrollbar {
            width: 0px;
            height: 1px;
        }

        ::-webkit-scrollbar-thumb {
            border-radius: 5px;
            -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
            background: rgba(0, 0, 0, 0.2);
        }

        span, p, b, em, h1, h2, h3, h4, h5, h6 {
            font-style: normal;
            font-weight: normal;
            font-size: .4rem;
        }

        body, div, p, ul, li, ol {
            padding: 0;
            margin: 0;
            box-sizing: border-box;
            list-style: none;
        }

        .alert {
            width: 100%;
            height: 100%;
            position: fixed;
            top: 0;
            left: 0;
            background: rgba(0, 0, 0, .6);
        }

        .alert ul {
            width: 14.5rem;
            position: absolute;
            bottom: 0;
            left: 50%;
            transform: translateX(-50%);
        }

        .alert ul > li, .alert ul > li ol {
            -webkit-border-radius: .2rem;
            border-radius: .2rem;
            overflow: hidden;
        }

        .alert ul > li:last-of-type {
            margin-top: .5rem;
            margin-bottom: 1rem;
            width: 100%;
            height: 1.8rem;
            line-height: 1.8rem;
            text-align: center;
            border: 1px solid #ddd;
            border-top: none;
            background: #fff;
            font-size: .7rem;
        }

        .alert ol li {
            width: 100%;
            height: 1.8rem;
            line-height: 1.8rem;
            text-align: center;
            border-top:  1px solid #ddd;
            background: #fff;
            font-size: .7rem;
        }

        .alert ul li ol {
            border-radius: .2rem;
        }

</style>
</head>

<body>
	<div class="wraper">
	
	<div id="showSave">
	
		<div class="dis_f ali_ct jus_bt pad_20 bg_f borderbot1">
			<a onclick="window.location.href='/mts/appWeb/moneyDetail/tixian.jsp';"><img src="<%=basePath%>/js/appWeb/images/back.png" class="dis_b" style="width:1rem;" /></a>
			<p class="f_30 clr_3">提现帐户管理</p>
			<a ><img src="<%=basePath%>/js/appWeb/images/more.png" class="dis_b" style="width:1rem;visibility: hidden;" /></a>
		</div>

		<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt" onclick="javascript:$('#showCheck').show();">
				<div id="changeName">请选择种类</div>
				<img src="<%=basePath%>/js/appWeb/images/arr_he2.png" class="dis_b" style="height:0.6rem;" />
		</div>
		<div id="showBank">
			<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt" onclick="javascript:changeType(4);">
					<div id="bankName">请选择银行</div>
					<img src="<%=basePath%>/js/appWeb/images/arr_he2.png" class="dis_b" style="height:0.6rem;" />
			</div>
			<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt baba">
					<img src="<%=basePath%>/js/appWeb/images/khh.png" class="dis_b" style="width:1rem;" />
				<input class="clr_he" type="text" id="branchBank" placeholder="请填写开户行" style="border:0;text-align:right;width:10rem;">
			</div>
			<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt baba">
					<img src="<%=basePath%>/js/appWeb/images/hzxm.png" class="dis_b" style="width:1rem;" />
				<input class="clr_he" type="text" id="ownerName" placeholder="请填写户主姓名" style="border:0;text-align:right;width:10rem;">
			</div>
			<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt baba">
					<img src="<%=basePath%>/js/appWeb/images/hzsjh.png" class="dis_b" style="width:1rem;" />
				<input class="clr_he" type="text" id="ownerPhone" placeholder="请填写户主手机号" style="border:0;text-align:right;width:10rem;">
			</div>
			<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt baba">
					<img src="<%=basePath%>/js/appWeb/images/ndkh.png" class="dis_b" style="width:1rem;" />
				<input class="clr_he" type="text" id="cardNum" placeholder="请填写您的卡号" style="border:0;text-align:right;width:10rem;">
			</div>
		</div>

		<div id="showAli">
			<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt baba">
					<img src="<%=basePath%>/js/appWeb/images/zfbzh.png" class="dis_b" style="width:1rem;" />
				<input class="clr_he" type="text" id="cardNumA" placeholder="请填写支付宝帐号" style="border:0;text-align:right;width:10rem;">
			</div>
			<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt baba">
					<img src="<%=basePath%>/js/appWeb/images/hzxm.png" class="dis_b" style="width:1rem;" />
				<input class="clr_he" type="text" id="ownerNameA" placeholder="请填写真实姓名" style="border:0;text-align:right;width:10rem;">
			</div>
		</div>
		
		<div id="showWx">
			<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt baba">
					<img src="<%=basePath%>/js/appWeb/images/wx4.png" class="dis_b" style="width:1rem;" />
				<input class="clr_he" type="text" id="wxAccount" placeholder="请填写微信帐号" style="border:0;text-align:right;width:10rem;">
			</div>
			<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt baba">
					<img src="<%=basePath%>/js/appWeb/images/hzxm.png" class="dis_b" style="width:1rem;" />
				<input class="clr_he" type="text" id="wxName" placeholder="请填写微信昵称" style="border:0;text-align:right;width:10rem;">
			</div>
			<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt baba">
					<img src="<%=basePath%>/js/appWeb/images/hzsjh.png" class="dis_b" style="width:1rem;" />
				<input class="clr_he" type="text" id="wxPhone" placeholder="请填写微信绑定手机号" style="border:0;text-align:right;width:10rem;">
			</div>
		</div>

		<div class="pad_30">
			<input type="button" onclick="xinzeng();" class="f_26 clr_f dis_b waiting_check_a" style="background: #f95d47;border:0;" value="完成添加" />
		</div>

		<div id="showCheck" class="alert" style="display:none;">
		    <ul>
		        <li>
		            <ol>
		                <li onclick="changeType(1);">银行卡</li>
		                <li onclick="changeType(2);">支付宝</li>
		                <li onclick="changeType(3);">微信</li>
		            </ol>
		        </li>
		        <li onclick="javascript:$('#showCheck').hide();">取消</li>
		    </ul>
		</div>
	</div>
		
		<div id="showBankl">
			<div class="dis_f ali_ct jus_bt pad_20 bg_f borderbot1">
				<a   onclick="javascript:changeType(1);" ><img src="<%=basePath%>/js/appWeb/images/back.png" class="dis_b" style="width:1rem;" /></a>
				<p class="f_30 clr_3">银行</p>
				<a ><img src="<%=basePath%>/js/appWeb/images/more.png" class="dis_b more_ul_toggle" style="width:1rem;visibility: hidden;" /></a>
			</div>
			<div class="bg_f" style="height:100%;" id="category">
	
			</div>
			<script id="category_list_tmpl" type="text/x-jquery-tmpl">
			<div class="f_26 clr_3 dis_f al_ct pad_20 borderbot1"  onclick="check('{{= id}}','{{= name}}');">
			{{= name}}
			</div>
		</script>
		</div>
	</div>
</body>

</html>