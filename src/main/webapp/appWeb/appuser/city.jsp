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

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0"
	name="viewport">
<title>首页</title>
</head>
<body>


<style>
    .bjt {
        background-image: url("<%=basePath%>/js/appWeb/images/1242.png");
        background-attachment: fixed;
        background-size: cover;
        position: relative;
    }

    .clear_padding_bottom {
        padding-bottom: 0rem;
    }

    .float {
        float: right
    }

    .subox {
        position: relative;
        width: 95%;
        border-bottom: 0.04rem solid #ffffff;
        box-sizing: border-box;
        margin: 0 auto;
        margin-top: 0.8rem;
    }

    .top_1 {
        background: none;
        background-image: url("<%=basePath%>/js/appWeb/images/1sousuo.png");
        border: none;
        height: 1.25rem;
        background-repeat: no-repeat;
        background-size: 0.8rem;
        background-position: left;
        color: #ffffff;
        padding-left: 1.2rem;
        box-sizing: border-box;
        display: block;
        width: 85%;
    }

    .subox span {
        position: absolute;
        right: 0.6rem;
        top: 0rem;
        color: #ffffff;
        margin-top: 0.27rem;
        display: block;
    }

    .dqcs {
        color: #ffffff;
        padding: 0.35rem;
    }

    .dqcs .title_dq {
        opacity: 0.8;
        display: block;
        margin-top: 0.8rem;
    }

    .dqcs .szd {
        border: #ffffff 0.03rem solid;
        border-radius: 0.5rem;
        line-height: 1rem;
        width: 2.8rem;
        text-align: center;
        display: block;
        margin-top: 0.8rem;
    }

    .qtcs {
        width: 95%;
        margin: 0 auto;
        color: #ffffff;
        opacity: 1;
        margin-top: 1.5rem;
    }

    .qtcs span {
        line-height: 2rem;
        border-bottom: 0.04rem solid #ffffff;
        display: block;
    }
	.navBox{
	position:fixed;
	width:100%;
	}
	.addBox{
	margin-top:10%;
	}


    .addBox {
        margin-top: 43%;
        height: 21.56rem;
        overflow-y: scroll;
    }


</style>

<div class="wraper overh bjt">
    <div class="navBox">
        <div class="jus_bt pad_20 pos_rela clear_padding_bottom">
            <a href="#" style="display: block"><img src="<%=basePath%>/js/appWeb/images/1chahao.png" class="dis_b float" style="width:0.7rem;"/></a>
        </div>

        <div class="subox">
            <input class="f_22 clr_he top_1" type="text" placeholder="昵称/主题"/>
            <span class="f_22">搜索</span>
        </div>

        <div class="dqcs">
            <span class="f_22 title_dq">当前城市</span>
            <span class="f_24 szd">包头</span>
        </div>
    </div>

    <div class="qtcs f_22 addBox">
        <span>包头</span>
        <span>包头</span>
        <span>包头</span>
        <span>包头</span>
        <span>包头</span>
        <span>包头</span>
        <span>包头</span>
        <span>包头</span>
        <span>包头</span>
        <span>包头</span>
        <span>包头</span>
        <span>包头</span>
        <span>包头</span>
        <span>包头</span>
        <span>包头</span>
        <span>包头</span>
        <span>包头</span>
        <span>包头</span>
        <span>包头</span>
        <span>包头</span>
        <span>包头</span>
        <span>包头</span>
        <span>包头</span>
        <span>包头</span>

    </div>


</div>
<script>
    $(document).on("pageinit",".addBox",function(){
        $(document).on("scrollstart",function(){
            console.log("!")
        });
    });
    $(".addBox").touchmove()
</script>
</body>

</html>