<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0" name="viewport">

<title>首页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
        <meta http-equiv="Cache-Control" content="no-transform" />
        <meta http-equiv="Cache-Control" content="no-siteapp" />
        <meta  name="keywords" content="定客"/>
        <meta  name="description"  content="定客"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no"/>
        <meta http-equiv="Cache-Control" content="no-cache, must-revalidate"/>
        <meta http-equiv="Pragma" content="no-cache"/>
        <meta name="format-detection" content="telephone=no"/>
        <meta name="apple-mobile-web-app-capable" content="yes"/>
        <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
        
        <meta  name="viewport" content="width=device-width,target-densitydpi=high-dpi,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"  />   
        
        
		<script type="text/javascript" src="<%=basePath%>/js/appWeb/weixinjs/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/appWeb/weixinjs/TouchSlide.1.1.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/appWeb/weixinjs/global_phone.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/appWeb/weixinjs/jquery.tmpl.min.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/appWeb/css/css.css" />
		<script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" data-appid="1105975411" data-redirecturi="http://app.mtianw.com/mts/appWeb/appuser/appuserLogin.jsp" charset="utf-8" data-callback="true"></script>
        <script type="text/javascript" src="<%=basePath%>/js/appWeb/appUser/app_appUserLogin.js"></script>

		<script type="text/javascript">
		function qqLogin() {
			QC.Login.showPopup({
				   appId:"1105975411",  
				   redirectURI:"http://app.mtianw.com/mts/appWeb/appuser/appuserLogin.jsp"  
			});   
		}
		</script>
		
		 <script type="text/javascript">
		//从页面收集OpenAPI必要的参数。get_user_info不需要输入参数，因此paras中没有参数
	
		var headimgurl="";
		var checkSex="";
		var nickname="";
		
		var paras = {};
		//用JS SDK调用OpenAPI
		QC.api("get_user_info", paras)
			//指定接口访问成功的接收函数，s为成功返回Response对象
			.success(function(s){
				//成功回调，通过s.data获取OpenAPI的返回数据
				console.log(s.data);
				headimgurl=s.data.figureurl_qq_2;
				nickname=s.data.nickname;
				checkSex=s.data.gender;
			})
			



 			if(QC.Login.check()){//如果已登录  
	   			 QC.Login.getMe(function(openId, accessToken){  
	   				console.log("openId为："+openId);
	   				console.log(headimgurl);
					console.log(nickname);
					console.log(checkSex);
	   			/* 	$.ajax({
	    	    		url : "/mts/system/appuser/loginS/json?web=1&qqNum="+openId+"&header="+headimgurl+"&sex="+checkSex+"&name="+nickname,
	    	    		type : "get",
	    	    		success : function(result) {
	    	    			window.location.href="/mts/appWeb/appuser/appuserLook.jsp"; 
	    	    		},
	    	    		error:function(XMLHttpRequest, textStatus, errorThrown){
	    	    			console.log(XMLHttpRequest) ;
	    	    			console.log(textStatus) ;
	    	    		}
	    	    	}); 
	   				 */
	   			});  
	   			//这里可以调用自己的保存接口  
	    		//...
				}  
		</script>
		
		
		<script type="text/javascript">
		var show=1;
		function showPass() {
			if(show==1){
				$("#password").attr("type","text");
				show=2;
			}else{
				$("#password").attr("type","password");
				show=1;
			}
			
		}
		</script>

<style>
html,body{height:100%;}
</style>
</head>

<body>

	<div class="wraper overh" style="height:100%;">
		<div class="login_bg">
			<div class="login_bg_mid" style="padding-top:6.1rem;">
				
				<div class="dis_f ali_ct padt_20 ">
					<img src="<%=basePath%>/js/appWeb/images/mobile.png" class="dis_b" style="height:0.9rem;margin-right:0.5rem;" />
					<input class="ipt3 f_24 clr_9" id="phone" placeholder="手机号" type="text" />
				</div>
				
				<div class="dis_f ali_ct jus_bt ">
					<div class="dis_f ali_ct padt_20 ">
						<img src="<%=basePath%>/js/appWeb/images/password.png" class="dis_b" style="height:0.9rem;margin-right:0.5rem;" />
						<input class="ipt3 f_24 clr_9" id="password" placeholder="密码"  type="password" />
					</div>

					<img onclick="showPass();" src="<%=basePath%>/js/appWeb/images/eye3.png" class="dis_b" style="width:0.9rem;" />
				</div>

				<div class="dis_f ali_ct jus_bt padt_20" style="border:0;">
					<a  onclick="window.location.href='/mts/appWeb/appuser/zhuce.jsp';"  class="clr_9 f_24">注册</a>
					<a  onclick="window.location.href='/mts/appWeb/appuser/findPass.jsp';"  class="clr_9 f_24">忘记密码</a>
				</div>

				<input class="btn3 f_28 clr_r bg_f mt_20"  onclick="login();" type="button" value="登录" />
			</div>

			<div class="dis_f ali_ct jus_bt" style="width:11rem;margin:5rem auto 0 auto;">
				<img src="<%=basePath%>/js/appWeb/images/login_line.png" class="dis_b" style="width:3.5rem;" />
				<div class="f_24 clr_f f_light">快捷登录</div>
				<img src="<%=basePath%>/js/appWeb/images/login_line.png" class="dis_b" style="width:3.5rem;" />
			</div>

			<div class="dis_f ali_ct jus_bt" style="width:8.5rem;margin:1rem auto 0 auto;">
				<img id="wxShow" onclick="wxCheck();" src="<%=basePath%>/js/appWeb/images/d3.png" class="dis_b" style="width:2rem;" />
				<img id="qqShow" onclick="qqLogin()" src="<%=basePath%>/js/appWeb/images/d1.png" class="dis_b" style="width:2rem;" />
			</div>


		</div>
	</div>
</body>

</html>