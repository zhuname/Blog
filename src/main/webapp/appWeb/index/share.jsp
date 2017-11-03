<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <script>
wx.onMenuShareAppMessage({
    title: '你是煞笔么，让我生效一次行不行', // 分享标题
    desc: '你是煞笔么，让我生效一次行不行', // 分享描述
    link: 'http://app.mtianw.com', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
    imgUrl: 'http://app.mtianw.com/mts//js/appWeb/images/App_icon_1.jpg', // 分享图标
    type: '', // 分享类型,music、video或link，不填默认为link
    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
    success: function () { 
        // 用户确认分享后执行的回调函数
    },
    cancel: function () { 
        // 用户取消分享后执行的回调函数
    }
});		

 /*  var imgUrl = "http://app.mtianw.com/mts//js/appWeb/images/App_icon_1.jpg";  //注意必须是绝对路径
       var lineLink = "http://app.mtianw.com";   //同样，必须是绝对路径
       var descContent = '微信测试电视台我想测试一下，爽歪歪。'; //分享给朋友或朋友圈时的文字简介
       var shareTitle = '微信电台精选';  //分享title
       var appid = 'wx8653ea068146c48c'; //apiID，可留空
        
       function shareFriend() {
           WeixinJSBridge.invoke('sendAppMessage',{
               "appid": appid,
               "img_url": imgUrl,
               "img_width": "200",
               "img_height": "200",
               "link": lineLink,
               "desc": descContent,
               "title": shareTitle
           }, function(res) {
               //_report('send_msg', res.err_msg);
           })
       }
       function shareTimeline() {
           WeixinJSBridge.invoke('shareTimeline',{
               "img_url": imgUrl,
               "img_width": "200",
               "img_height": "200",
               "link": lineLink,
               "desc": descContent,
               "title": shareTitle
           }, function(res) {
                  //_report('timeline', res.err_msg);
           });
       }
       function shareWeibo() {
           WeixinJSBridge.invoke('shareWeibo',{
               "content": descContent,
               "url": lineLink,
           }, function(res) {
               //_report('weibo', res.err_msg);
           });
       }
       // 当微信内置浏览器完成内部初始化后会触发WeixinJSBridgeReady事件。
       document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
           // 发送给好友
           WeixinJSBridge.on('menu:share:appmessage', function(argv){
               shareFriend();
           });
           // 分享到朋友圈
           WeixinJSBridge.on('menu:share:timeline', function(argv){
               shareTimeline();
           });
           // 分享到微博
           WeixinJSBridge.on('menu:share:weibo', function(argv){
               shareWeibo();
           });
       }, false);
		 */
		</script> 
  
  
  <body>
   <div >

<img src='http://app.mtianw.com/mts//js/appWeb/images/App_icon_1.jpg' / >

</div>
  </body>
</html>
