<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>测试分享</title>
    <meta name="keywords" content="只是做个测试" />
    <meta name="description" content="我是来做测试的" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <link rel="apple-touch-icon" href="http://app.mtianw.com/mts//js/appWeb/images/App_icon_1.jpg" />
	 
	<script type="text/javascript"
	src="<%=basePath%>/js/appWeb/weixinjs/jquery-1.9.1.min.js"></script>
	<script type="text/javascript"
	src="<%=basePath%>/js/appWeb/weixinjs/jquery.tmpl.min.js"></script>
	<script type="text/javascript"
	src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	

  </head>
  
  <script>
  
  /* 
  	$.ajax({
    	url : '/mts/system/wxShare/ceshi/json',
    	   type : "get",
    	   dataType: 'json',  
    	    	success : function(result) {
    	    	
    	    	console.log(result);
    	    	
    	    	console.log(result.nonceStr);
				    	    	
				wx.checkJsApi({
				    jsApiList: ['chooseImage'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
				    success: function(res) {
				        // 以键值对的形式返回，可用的api值true，不可用为false
				        // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
				        alert("checkJsApi1");
				    },
				    fail:function(res){
				    	alert("checkJsApi2");
				    }
				});
    	    	
				  wx.config({
				    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				    appId: 'wx8653ea068146c48c', // 必填，公众号的唯一标识
				    timestamp:  result.timestamp, // 必填，生成签名的时间戳
				    nonceStr: result.nonceStr, // 必填，生成签名的随机串
				    signature: result.signature,// 必填，签名，见附录1
				    jsApiList: [onMenuShareTimeline] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
				  });
    	    	
    	    	},
    	  		error:function(XMLHttpRequest, textStatus, errorThrown){
    				console.log(XMLHttpRequest) ;
    				console.log(textStatus) ;
    		}
  	});
  
  

   wx.onMenuShareAppMessage({
    title: '你是煞笔么，让我生效一次行不行', // 分享标题
    desc: '你是煞笔么，让我生效一次行不行', // 分享描述
    link: 'http://app.mtianw.com', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
    imgUrl: 'http://app.mtianw.com/mts//js/appWeb/images/App_icon_1.jpg', // 分享图标
    type: '', // 分享类型,music、video或link，不填默认为link
    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
    success: function () { 
        // 用户确认分享后执行的回调函数
        alert("onMenuShareAppMessage1");
    },
    cancel: function () { 
        // 用户取消分享后执行的回调函数
        alert("onMenuShareAppMessage2");
    }
  });

	wx.error(function(res){
	    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
	    alert(res);
	});

   
   
   
   wx.onMenuShareTimeline({
    title: '你好测试', // 分享标题
    link: 'http://app.mtianw.com', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
    imgUrl: 'http://app.mtianw.com/mts//js/appWeb/images/App_icon_1.jpg', // 分享图标
    success: function () { 
        // 用户确认分享后执行的回调函数
         alert("onMenuShareAppMessage1");
    },
    cancel: function () { 
        // 用户取消分享后执行的回调函数
         alert("onMenuShareAppMessage2");
    }
}); */






























$(function() {


	$.ajax({
    	url : '/mts/system/wxShare/ceshi/json',
    	   type : "get",
    	   dataType: 'json',  
    	    	success : function(result) {
    	    	
    	    	console.log(result);
    	    	
    	    	console.log(result.nonceStr);
				    	    	
				wx.checkJsApi({
				    jsApiList: ['chooseImage'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
				    success: function(res) {
				        // 以键值对的形式返回，可用的api值true，不可用为false
				        // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
				        alert("checkJsApi1");
				    },
				    fail:function(res){
				    	alert("checkJsApi2");
				    }
				});
    	    	
				  wx.config({
				    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				    appId: 'wx8653ea068146c48c', // 必填，公众号的唯一标识
				    timestamp:  result.timestamp, // 必填，生成签名的时间戳
				    nonceStr: result.nonceStr, // 必填，生成签名的随机串
				    signature: result.signature,// 必填，签名，见附录1
				    jsApiList: [ 'checkJsApi','onMenuShareTimeline','onMenuShareAppMessage','onMenuShareQQ','onMenuShareWeibo'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
				  });
    	    	
    	    	},
    	  		error:function(XMLHttpRequest, textStatus, errorThrown){
    				console.log(XMLHttpRequest) ;
    				console.log(textStatus) ;
    		}
  	});

          

            wx.ready(function(){
                // wx.hideOptionMenu();
                wx.onMenuShareTimeline({
                    title: '这是一个测试的标题--程高伟的博客',
                    link: 'http://app.mtianw.com',
                    imgUrl: 'http://app.mtianw.com/mts//js/appWeb/images/App_icon_1.jpg',
                    success: function () { 
                        // 用户确认分享后执行的回调函数
                         alert('分享到朋友圈成功');
                    },
                    cancel: function () { 
                        // 用户取消分享后执行的回调函数
                         alert('你没有分享到朋友圈');
                    }
                });
                wx.onMenuShareAppMessage({
                      title: '这是一个测试的标题--百度',
                      desc: '这个是要分享内容的一些描述--百度一下，你就知道',
                      link: 'http://app.mtianw.com',
                      imgUrl: 'http://app.mtianw.com/mts//js/appWeb/images/App_icon_1.jpg',
                      trigger: function (res) {
                        // 不要尝试在trigger中使用ajax异步请求修改本次分享的内容，因为客户端分享操作是一个同步操作，这时候使用ajax的回包会还没有返回
                      },
                      success: function (res) {
                          alert('分享给朋友成功');
                      },
                      cancel: function (res) {
                        alert('你没有分享给朋友');
                      },
                      fail: function (res) {
                        alert(JSON.stringify(res));
                      }
                    });
            });
        });
</script>

  
  
  <body>
   <div >

<img src='http://app.mtianw.com/mts//js/appWeb/images/App_icon_1.jpg' / >

</div>
  </body>
</html>
