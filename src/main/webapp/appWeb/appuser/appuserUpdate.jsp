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
	src="<%=basePath%>/js/appWeb/appUser/app_appUserUpdate.js"></script>
	
	<script src="<%=basePath%>/js/jquery/ajaxfileupload.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0"
	name="viewport">
<title>首页</title>

<SCRIPT type="text/javascript">
	
	function headOnc(){
		jQuery("#filed").click();
	}

$(document).on("change", "#filed", function() {
    //...

	  $.ajaxFileUpload({
            url : '/mts/adminFileUpload',
            secureuri : false,
            fileElementId : 'filed',
            dataType : 'text',
            data : {},
            success : function(data, status) {
            	console.log(data);
            	jQuery("#header").attr('src',data);
            	
            	updateHeader(data);            	
            	
            },
            error : function(data, status, e) {
           		console.log(data);
                alert('上传出错');
            }
        })

        return false;


	});
		
</SCRIPT>


</head>

<body>

	<div class="wraper" style="background: #f9f9fb;height:100%;" id="detail">

	</div>

		<!-- 这个是未读消息 -->
	<script id="appUser_update_tmpl" type="text/x-jquery-tmpl">


	<div class="dis_f ali_ct jus_bt pad_20 bg_f borderbot1">
			<a onclick="window.location.href='/mts/appWeb/appuser/appuserLook.jsp';"><img src="<%=basePath%>/js/appWeb/images/back.png" class="dis_b" style="width:1rem;" /></a>
			<p class="f_30 clr_3">个人资料</p>
			<a ><img src="<%=basePath%>/js/appWeb/images/more.png" class="dis_b more_ul_toggle" style="width:1rem;visibility: hidden;" /></a>
		</div>

		<div class="dis_f ali_ct jus_bt bg_f pad_2030 borderbot1" onclick="headOnc();" >
			<img src="<%=basePath%>/js/appWeb/images/thumbs.png" class="dis_b" style="width:1rem;" />
			<div class="dis_f ali_ct">
			<img src="{{= header}}" class="dis_b" style="width:1.5rem;border-radius: 0.8rem;" />
			<img src="<%=basePath%>/js/appWeb/images/arr_he.png" class="dis_b" style="height:0.4rem;margin-left:0.5rem;" />
			</div>
		</div>
			 <input type="file" id="filed" name="filed" style="display:none">

		<div class="dis_f ali_ct jus_bt bg_f pad_2030 borderbot1 mt_20 bordertop1 " onclick="javascript:$('.nameShow').show();">
			<img src="<%=basePath%>/js/appWeb/images/wdnc.png" class="dis_b" style="width:1rem;" />
			<div class="dis_f ali_ct f_28 clr_he">
				<p style="white-space:nowrap">{{= name}}</p>
				<img src="<%=basePath%>/js/appWeb/images/arr_he.png" class="dis_b" style="height:0.4rem;margin-left:0.5rem;" />
			</div>
		</div>

		<div class="dis_f ali_ct jus_bt bg_f pad_2030 borderbot1"  onclick="javascript:$('.sexShow').show();">
			<img src="<%=basePath%>/js/appWeb/images/xb.png" class="dis_b" style="width:1rem;" />
			<div class="dis_f ali_ct f_28 clr_he">
				<p style="white-space:nowrap">{{= sex}}</p>
				<img src="<%=basePath%>/js/appWeb/images/arr_he.png" class="dis_b" style="height:0.4rem;margin-left:0.5rem;" />
			</div>
		</div>

		<div class="dis_f ali_ct jus_bt bg_f pad_2030 borderbot1"  onclick="javascript:$('.signShow').show();">
			<img src="<%=basePath%>/js/appWeb/images/kyyhbjj.png" class="dis_b" style="width:1rem;" />
			<div class="dis_f ali_ct f_28 clr_he">
				<p style="white-space:nowrap">{{= signVal}}</p>
				<img src="<%=basePath%>/js/appWeb/images/arr_he.png" class="dis_b" style="height:0.4rem;margin-left:0.5rem;" />
			</div>
		</div>

		<div class="dis_f ali_ct jus_bt bg_f pad_2030 borderbot1 mt_20 bordertop1" onclick="window.location.href='/mts/appWeb/appuser/myCity.jsp?id={{= id}}';" >
			<img src="<%=basePath%>/js/appWeb/images/dz.png" class="dis_b" style="width:1rem;" />
			<div class="dis_f ali_ct f_28 clr_he">
				<p style="white-space:nowrap">{{= cityName}}</p>
				<img src="<%=basePath%>/js/appWeb/images/arr_he.png" class="dis_b" style="height:0.4rem;margin-left:0.5rem;" />
			</div>
		</div>

		<div class="dis_f ali_ct jus_bt bg_f pad_2030 borderbot1 mt_20 bordertop1">
			<img src="<%=basePath%>/js/appWeb/images/sj.png" class="dis_b" style="width:1rem;" />
			<div class="dis_f ali_ct f_28 clr_he">
				<p style="white-space:nowrap">{{= phone}}</p>
				<img src="<%=basePath%>/js/appWeb/images/arr_he.png" class="dis_b" style="height:0.4rem;margin-left:0.5rem;" />
			</div>
		</div>

		<div class="dis_f ali_ct jus_bt bg_f pad_2030 borderbot1 mt_20 bordertop1">
			<img src="<%=basePath%>/js/appWeb/images/wx.png" class="dis_b" style="width:1rem;" />
			<div class="dis_f ali_ct f_28 clr_he">
				<p style="white-space:nowrap">未绑定</p>
				<img src="<%=basePath%>/js/appWeb/images/arr_he.png" class="dis_b" style="height:0.4rem;margin-left:0.5rem;" />
			</div>
		</div>

		<div class="dis_f ali_ct jus_bt bg_f pad_2030 borderbot1">
			<img src="<%=basePath%>/js/appWeb/images/qq.png" class="dis_b" style="width:1rem;" />
			<div class="dis_f ali_ct f_28 clr_he">
				<p style="white-space:nowrap">未绑定</p>
				<img src="<%=basePath%>/js/appWeb/images/arr_he.png" class="dis_b" style="height:0.4rem;margin-left:0.5rem;" />
			</div>
		</div>

	<div class="command_mask nameShow" style="width:16rem;display: none;">
			<div class="command pad_20">
				<input class="ipt4 f_28 clr_9" placeholder="{{= name}}" value="{{= name}}" type="text" id="name"/>
				<input class="btn4 f_30 clr_f mt_30 qd_click"  onclick="javascript:updateName($('#name').val());" value="确定" type="button" />
			</div>
	</div>

	<div class="command_mask signShow" style="width:16rem;display: none;">
			<div class="command pad_20">
				<input class="ipt4 f_28 clr_9" placeholder="{{= sign}}" value="{{= sign}}" type="text" id="sign"/>
				<input class="btn4 f_30 clr_f mt_30 qd_click"  onclick="javascript:updateSign($('#sign').val());" value="确定" type="button" />
			</div>
	</div>


	<div class="command_mask sexShow" style="width:16rem;display: none;">
				<div class="dis_f ali_ct jus_ct" style="height:100%;">
					<img src="<%=basePath%>/js/appWeb/images/male3.png"  onclick="javascript:updateSex('男');"  class="dis_b qd_click" style="height:4rem;margin-right:2rem;" />
					<img src="<%=basePath%>/js/appWeb/images/female3.png"  onclick="javascript:updateSex('女');"  class="dis_b qd_click" style="height:4rem;" />
				</div>
		</div>
	</script>
	
	
	
	
</body>


</html>
