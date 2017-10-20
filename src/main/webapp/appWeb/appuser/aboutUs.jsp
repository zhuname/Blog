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
	src="<%=basePath%>/js/appWeb/appUser/app_aboutUs.js"></script>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0"
	name="viewport">
    <title>关于我们</title>
    <style>
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

        body, div, p {
            padding: 0;
            margin: 0;
            box-sizing: border-box;
        }

        body {
            padding-top: 104px;
            background: #efeff4;
        }

        .top {
            height: 2rem;
            width: 100%;
            background: #fff;
            position: fixed;
            top: 0;
            left: 0;
            border-bottom: 3px solid #bababa;
            text-align: center;
            line-height: 2rem;
        }

        .top span {
            font-size: 0.8rem;

        }

        .top i {
            display: inline-block;
            position: absolute;
            left: 1.2rem;
            top: 50%;
            width: .8rem;
            height: .9rem;
            transform: translateY(-50%);
            background: url('<%=basePath%>/js/appWeb/images/back.png') center no-repeat;
        }

        h1 {
            height: 3.5rem;
            margin-top: 1.2rem;
            width: 100%;
            background: url("./100.png") center no-repeat;
            background-size: 3rem;
        }

        p {
            font-size: .6rem;
            text-align: center;
            color: #868686;
            margin-bottom: 1rem;
        }

        .show-txt {
            width: 100%;
            height: 7rem;
            background: #fff;
            color: #333333;
            font-size: .6rem;
            line-height: .9rem;
            padding: .8rem .6rem;
            text-indent: 1.2rem;
            overflow-x: hidden;
            overflow-y: auto;
        }

        .serer-tell {
            height: 2rem;
            line-height: 2rem;
            background: #fff;
            border-top: 1px solid #bababa;
            color: #333333;
            padding-left: .6rem;
        }

        .serer-tell span {
            font-size: .6rem;
        }

        h5 {
            font-size: .6rem;
            color: #959595;
            margin-bottom: 1rem;
            text-align: center;
            left: 0;
            position: fixed;
            bottom: 0;
            width: 100%;
            height: 1rem;
            background: url("./100.png") center no-repeat;
        }
    </style>
</head>
<body>
<div class="top">
    <span>关于我们</span>
    <i></i>
</div>
<h1 id="apkImg"></h1>
<p>美天赏V1.1</p>
<div class="show-txt">
    <span><pre id="appIntroduce"></pre></span>
</div>
<div class="serer-tell">
    <span>客服电话:</span>
    <span id="kefuphone">400-400-400</span>
</div>
<h5 id="aboutImage"></h5>
</body>
</html>