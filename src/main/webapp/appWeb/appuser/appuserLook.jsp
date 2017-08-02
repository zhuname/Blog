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
	src="<%=basePath%>/js/appWeb/appUser/app_appUserLook.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0"
	name="viewport">
<title>首页</title>

</head>

<body>

	<div class="wraper overh" id="detail"></div>

	<script id="appuser_detail_tmpl" type="text/x-jquery-tmpl">
	<div class="center_bg" >
			<div class="pad_30 dis_f jus_rt ali_ct" id="msg">
				<img src="<%=basePath%>/js/appWeb/images/msg4.png" class="dis_b" style="width:0.8rem;" />
			</div>

			<div class="pad_30 dis_f ali_end jus_bt">
				<div class="dis_f ali_ct">
					<img src="{{= header}}" class="dis_b" style="width:1.5rem;border:2px solid #FFF;border-radius: 0.8rem;margin-right:0.5rem;" />
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
						<p class="f_20 clr_f">{{= sign}}</p>
					</div>
				</div>

				<div class="opa8">
					<img src="<%=basePath%>/js/appWeb/images/fans2.png" class="ver_mid" style="width:0.55rem;" />
					<span class="ver_mid f_22 clr_f">{{= fansNum}}</span>

				</div>
			</div>

			<div class="f_20 clr_f dis_f ali_ct jus_rt padl_30" >
				
				<span id="lqSpan">
				</span>
				&nbsp;
				<sapn id="lqjh">
				
				</span>
			</div>

			<div style="background: url({{= header}}) no-repeat;width:16rem;height:7rem; position: absolute; top: 0;z-index: -1"></div>


		</div>



	</script>
	
	
	<script id="appuser_noLoginDetail_tmpl" type="text/x-jquery-tmpl">
		<div class="center_bg" style="background-color:#f95d47">
			<div class="q1" onclick="window.location.href='/mts/appWeb/appuser/appuserLogin.jsp';">
				登录
			</div>
		</div>
	</script>

	<script id="appuser_statics_tmpl" type="text/x-jquery-tmpl">

<div class="bg_f padl_20">
		<div class="f_20 clr_3  padt_30 borderbot1 dis_f ali_ct jus_bt" onclick="window.location.href='/mts/appWeb/moneyDetail/appMoneyDetail.jsp?userId={{= id}}';">
			<div class="dis_f ali_ct">
				<img src="<%=basePath%>/js/appWeb/images/wdqb.png" class="dis_b" style="width:0.8rem;margin-right:0.5rem;" />
				<p>我的钱包</p>
			</div>
			<div class="f_18 clr_he dis_f ali_ct">
				<p>
				{{if balance}}
				{{= balance}}
				{{else}}
				0
				{{/if}}
				</p>
				<img src="<%=basePath%>/js/appWeb/images/arr_he.png" class="dis_b" style="height:0.4rem;margin-left:0.5rem;" />
			</div>
		</div>
		<div class="f_20 clr_3  padt_30 borderbot1 dis_f ali_ct jus_bt">
			<div class="dis_f ali_ct">
				<img src="<%=basePath%>/js/appWeb/images/wdfb.png" class="dis_b" style="width:0.8rem;margin-right:0.5rem;" />
				<p>我的发布</p>
			</div>
			<div class="f_18 clr_he dis_f ali_ct">
				<p>
				{{if totalMedia}}
				{{= totalMedia+totalPoster+totalCard+totalActivity}}
				{{else}}
				0
				{{/if}}
				</p>
				<img src="<%=basePath%>/js/appWeb/images/arr_he.png" class="dis_b" style="height:0.4rem;margin-left:0.5rem;" />
			</div>
		</div>
		<div class="f_20 clr_3  padt_30 borderbot1 dis_f ali_ct jus_bt">
			<div class="dis_f ali_ct">
				<img src="<%=basePath%>/js/appWeb/images/wddt.png" class="dis_b" style="width:0.8rem;margin-right:0.5rem;" />
				<p>我的动态</p>
			</div>
			<div class="f_18 clr_he dis_f ali_ct">
				<p>
				{{if totalCircle}}
				{{= totalCircle}}
				{{else}}
				0
				{{/if}}
				</p>
				<img src="<%=basePath%>/js/appWeb/images/arr_he.png" class="dis_b" style="height:0.4rem;margin-left:0.5rem;" />
			</div>
		</div>
	</div>

<div class="bg_f padl_20 mt_20">
		<div class="f_20 clr_3  padt_30 borderbot1 dis_f ali_ct jus_bt ">
			<div class="dis_f ali_ct">
				<img src="<%=basePath%>/js/appWeb/images/wdyy.png" class="dis_b" style="width:0.8rem;margin-right:0.5rem;" />
				<p>我的预约</p>
			</div>
			<div class="f_18 clr_he dis_f ali_ct">
				<p>
				{{if totalAppoint}}
				{{= totalAppoint}}
				{{else}}
				0
				{{/if}}
				</p>
				<img src="<%=basePath%>/js/appWeb/images/arr_he.png" class="dis_b" style="height:0.4rem;margin-left:0.5rem;" />
			</div>
		</div>
		<div class="f_20 clr_3  padt_30 borderbot1 dis_f ali_ct jus_bt">
			<div class="dis_f ali_ct">
				<img src="<%=basePath%>/js/appWeb/images/wdkq.png" class="dis_b" style="width:0.8rem;margin-right:0.5rem;" />
				<p>我的卡券</p>
			</div>
			<div class="f_18 clr_he dis_f ali_ct">
				<p>
				{{if totalUserCard}}
				{{= totalUserCard}}
				{{else}}
				0
				{{/if}}
				</p>
				<img src="<%=basePath%>/js/appWeb/images/arr_he.png" class="dis_b" style="height:0.4rem;margin-left:0.5rem;" />
			</div>
		</div>
		<div class="f_20 clr_3  padt_30 borderbot1 dis_f ali_ct jus_bt">
			<div class="dis_f ali_ct">
				<img src="<%=basePath%>/js/appWeb/images/wdcy.png" class="dis_b" style="width:0.8rem;margin-right:0.5rem;" />
				<p>我的参与</p>
			</div>
			<div class="f_18 clr_he dis_f ali_ct">
				<p>
				{{if totalJoin}}
				{{= totalJoin}}
				{{else}}
				0
				{{/if}}
				</p>
				<img src="<%=basePath%>/js/appWeb/images/arr_he.png" class="dis_b" style="height:0.4rem;margin-left:0.5rem;" />
			</div>
		</div>


	</div>


	<div class="bg_f padl_20 mt_20">
		<div class="f_20 clr_3  padt_30 borderbot1 dis_f ali_ct jus_bt ">
			<div class="dis_f ali_ct">
				<img src="<%=basePath%>/js/appWeb/images/wdsc.png" class="dis_b" style="width:0.8rem;margin-right:0.5rem;" />
				<p>我的收藏</p>
			</div>
			<div class="f_18 clr_he dis_f ali_ct">
				<p>
				{{if totalCollect}}
				{{= totalCollect}}
				{{else}}
				0
				{{/if}}
				</p>
				<img src="<%=basePath%>/js/appWeb/images/arr_he.png" class="dis_b" style="height:0.4rem;margin-left:0.5rem;" />
			</div>
		</div>
		<div class="f_20 clr_3  padt_30 borderbot1 dis_f ali_ct jus_bt">
			<div class="dis_f ali_ct">
				<img src="<%=basePath%>/js/appWeb/images/wdgz.png" class="dis_b" style="width:0.8rem;margin-right:0.5rem;" />
				<p>我的关注</p>
			</div>
			<div class="f_18 clr_he dis_f ali_ct">
				<p>
				{{if totalAttention}}
				{{= totalAttention}}
				{{else}}
				0
				{{/if}}
				</p>
				<img src="<%=basePath%>/js/appWeb/images/arr_he.png" class="dis_b" style="height:0.4rem;margin-left:0.5rem;" />
			</div>
		</div>
		<div class="f_20 clr_3  padt_30 borderbot1 dis_f ali_ct jus_bt">
			<div class="dis_f ali_ct">
				<img src="<%=basePath%>/js/appWeb/images/wdxz.png" class="dis_b" style="width:0.8rem;margin-right:0.5rem;" />
				<p>我的勋章</p>
			</div>
			<div class="f_18 clr_he dis_f ali_ct">
				<p>
				{{if totalMedal}}
				{{= totalMedal}}
				{{else}}
				0
				{{/if}}
				</p>
				<img src="<%=basePath%>/js/appWeb/images/arr_he.png" class="dis_b" style="height:0.4rem;margin-left:0.5rem;" />
			</div>
		</div>


	</div>



	<div class="bg_f padl_20 mt_20">
		<div class="f_20 clr_3  padt_30 borderbot1 dis_f ali_ct jus_bt ">
			<div class="dis_f ali_ct">
				<img src="<%=basePath%>/js/appWeb/images/sz.png" class="dis_b" style="width:0.8rem;margin-right:0.5rem;" />
				<p>设置</p>
			</div>
			<div class="f_18 clr_he dis_f ali_ct">
				<img src="<%=basePath%>/js/appWeb/images/arr_he.png" class="dis_b" style="height:0.4rem;margin-left:0.5rem;" />
			</div>
		</div>
	</div>


		<div class="pad_30"></div>
		<div class="pad_30"></div>

		<div class="bg_f fixed_nav">
			<ul class="nav_ul dis_f ali_ct jus_bt" style="width:12rem;margin:0 auto;">
				<li><a href="#" class="dis_f ali_ct flex_col clr_6 jus_ct">
				<img src="<%=basePath%>/js/appWeb/images/b1_no.png" class="dis_b" style="width:1rem;" /><p class="f_18 ">美天赏</p></a></li>
				<li><a href="#"><img src="<%=basePath%>/js/appWeb/images/fabu.png" class="dis_b" style="width:2.25rem;margin-top:-0.2rem;" /></a></li>
				<li><a href="#" class="dis_f ali_ct flex_col clr_r jus_ct">
				<img src="<%=basePath%>/js/appWeb/images/b2h.png" class="dis_b" style="width:1rem;" /><p class="f_18 ">我的</p></a></li>
			</ul>
		</div>
	
	</script>
	
	<!-- 这个是领取机会的问号 -->
	<script id="appuser_wenPic_tmpl" type="text/x-jquery-tmpl">
		<img src="<%=basePath%>/js/appWeb/images/wenhao.png" class="ver_mid" style="width:0.55rem;" />
	</script>
	
	
	<!-- 这个是勋章模版 -->
	<script id="appuser_xunzhang_tmpl" type="text/x-jquery-tmpl">
		<img src="{{= medal.image}}" class="ver_mid" style="width:0.5rem;" />
	</script>
	
	<!-- 这个是已读消息 -->
	<script id="message_yidu_tmpl" type="text/x-jquery-tmpl">
		<img src="<%=basePath%>/js/appWeb/images/msg4.png" class="dis_b" style="width:0.8rem;" />
	</script>
	<!-- 这个是未读消息 -->
	<script id="message_weidu_tmpl" type="text/x-jquery-tmpl">
		<img src="<%=basePath%>/js/appWeb/images/weiduxx.png" class="dis_b" style="width:0.8rem;" />
	</script>
	
	

</body>


</html>
