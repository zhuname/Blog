<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>测试分享</title>
    <meta name="keywords" content="【11.12樊登北京千人活动】2017领导力高峰论坛暨一书一课产品发布会,【11.12樊登北京千人活动】2017领导力高峰论坛暨一书一课产品发布会门票,【11.12樊登北京千人活动】2017领导力高峰论坛暨一书一课产品发布会报名" />
    <meta name="description" content="【11.12樊登北京千人活动】2017领导力高峰论坛暨一书一课产品发布会参会，首选【互动吧http://www.hdb.com/】。【11.12樊登北京千人活动】2017领导力高峰论坛暨一书一课产品发布会将于11-12 13:00至11-12 18:00在海淀区西三环北路19号北京外国语大学（西校区）国际大厦举办,互动吧为您提供【11.12樊登北京千人活动】2017领导力高峰论坛暨一书一课产品发布会门票、价格、活动时间、活动地点、活动报名等全方位服务，权威、全面、方便、快捷。了解更多【11.12樊登北京千人活动】2017领导力高峰论坛暨一书一课产品发布会相关活动信息，直接上互动吧，随时随地购票！服务热线400-082-1222。" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <link rel="apple-touch-icon" href="http://app.mtianw.com:80/mts//js/appWeb/images/App_icon.png" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

 <script>
		
				//分享回调
			dataForShareCallback=function(type){_$(_api3._shareCount,"info_id="+_info._id36+"&info_type=party&share_url="+dataForShare.url+"&share_type="+type+"&share_location=detail_party");};
			
			 var dataForShare={
            weixin_icon:"http://app.mtianw.com:80/mts//js/appWeb/images/App_icon.png",
            weixin_tl_icon:"http://app.mtianw.com:80/mts//js/appWeb/images/App_icon.png",
            weixin_url:"http://app.mtianw.com",
            qq_icon:"http://app.mtianw.com:80/mts//js/appWeb/images/App_icon.png",
            weibo_icon:"http://app.mtianw.com:80/mts//js/appWeb/images/App_icon.png",
            url:"http://app.mtianw.com",
            title:"互动吧，免费发布、传播活动，最大化享受微信传播红利。",
            description:"互动吧，免费提供活动发布、管理、传播、收款等全环节服务，通过互动吧，您可以轻松在微信朋友圈、聊天群里组织各类活动。",
            sms:"互动吧，免费提供活动发布、管理、传播、收款等全环节服务，通过互动吧，您可以轻松在微信朋友圈、聊天群里组织各类活动。http://hudong.ba",
            appId:"",
            share_twitter_image:"http://app.mtianw.com",//推特分享图片forApp
            callback:function(type){_$(_api3._shareCount,"info_id=0&info_type=other");}
        };
        (function(){
                dataForShare.weixin_icon="http://app.mtianw.com:80/mts//js/appWeb/images/App_icon.png";
                dataForShare.weixin_icon_android="http://app.mtianw.com:80/mts//js/appWeb/images/App_icon.png";
                dataForShare.weixin_tl_icon="http://app.mtianw.com:80/mts//js/appWeb/images/App_icon.png";
                dataForShare.weixin_url= convertUrlByHdbParam(location.href);
                dataForShare.qq_icon="http://img.sys.hudongba.com/images/share/other_qq_3.png";
                dataForShare.weibo_icon="http://app.mtianw.com:80/mts//js/appWeb/images/App_icon.png";
                dataForShare.url = convertUrlByHdbParam(location.href);
                dataForShare.title="【11.12樊登北京千人活动】2017领导力高峰论坛暨一书一课产品发布会";
                dataForShare.description="11月12日下午13:00—18:00，在北京外国语大学，大咖云集领导力高峰论坛暨一书一课产品发布会，樊登老师将会...";
                dataForShare.share_big_img = "";
            dataForShare.description=dataForShare.description || dataForShare.title;
            dataForShare.sms="快来看看这个活动：【11.12樊登北京千人活动】2017领导力高峰论坛暨一书一课产品发布会 http://www.hdb.com/party/7s222.html";
            dataForShare.callback=function(type){_$("/post/api:7","info_id="+_info._id36+"&info_type="+_info._type+"&share_url="+dataForShare.weixin_url+"&share_type="+type+"&share_location=wxdetail")};
        })(); 
		
		
		
		</script> 



  </head>
  
  <body>
  
  测试微信
  
  
    <img src="http://app.mtianw.com:80/mts//js/appWeb/images/App_icon.png"/>
  </body>
</html>
