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
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">  
<meta name="app-mobile-web-app-capable" content="yes">  
<meta name="viewport"
	content="width=device-width,target-densitydpi=high-dpi,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<script type="text/javascript" src="<%=basePath%>/js/appWeb/weixinjs/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/appWeb/activity/map.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/appWeb/weixinjs/jquery.tmpl.min.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html,#allmap {width: 100%;height: 100%; margin:0;font-family:"微软雅黑";}
		#l-map{height:300px;width:100%;}
		#r-result{width:100%;}
	</style>
	
	 <style>
        div,ul,li,p,span,b,em,label,img,body{
            padding:0;
            margin:0;
            border:0;
            list-style: none;
            font-style: normal;
            box-sizing: border-box;
        }
        .box{
            width: 100%;
            height: 6.2rem;
            background: #ddd;
        }
        .mapTxt{
            max-width: 7.5rem;
            min-width: 3.2rem;
        }
        .mapTxt ul li{
            height: 1.37rem;
            padding:.18rem 0 0 .2rem;
            border-bottom: 1px solid #e0e0e0;
        }
        .mapTxt ul li img{
            width: .50rem;
            height: .50rem;
            margin-top:.24rem;
            float: left;
        }
        .mapTxt .txtR{
            float: left;
            margin-left: .21rem;
        }
        .txtR p:nth-of-type(1){
            line-height: .3rem;
            font-size: .3rem;
            color: #444444;
        }
        .txtR p:nth-of-type(2){
            margin-top:.39rem;
            line-height: .24rem;
            font-size: .25rem;
            color: #444444;
        }
    </style>
	
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=NgMZRcGV4cz2tc9tvQD7M5VkgB7gUT3g"></script>
	<title>根据多关键字本地搜索</title>
</head>
<body>
	<div id="l-map"></div>
	<div class="mapTxt">
    <ul id="details">
    </ul>
</div>
</body>
</html>

<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("l-map");
	var point = new BMap.Point(116.404, 39.915);
	map.centerAndZoom(point,18);

	var geolocation = new BMap.Geolocation();
	geolocation.getCurrentPosition(function(r){
		if(this.getStatus() == BMAP_STATUS_SUCCESS){
			var mk = new BMap.Marker(r.point);
			map.addOverlay(mk);
			map.panTo(r.point);
			
			 initMap( r.point.lat,r.point.lng );
			
			/* debugger;
			map.centerAndZoom(new BMap.Point(r.point.lng,r.point.lat), 18);
			var myKeys = ["酒店", "加油站", "路"];
			var local = new BMap.LocalSearch(map, {
				renderOptions:{map: map, panel:"r-result"},
				pageCapacity:5
			});
			local.searchInBounds(myKeys, map.getBounds()); */
		}
		else {
			alert('failed'+this.getStatus());
		}        
	},{enableHighAccuracy: true})
	//关于状态码
	//BMAP_STATUS_SUCCESS	检索成功。对应数值“0”。
	//BMAP_STATUS_CITY_LIST	城市列表。对应数值“1”。
	//BMAP_STATUS_UNKNOWN_LOCATION	位置结果未知。对应数值“2”。
	//BMAP_STATUS_UNKNOWN_ROUTE	导航结果未知。对应数值“3”。
	//BMAP_STATUS_INVALID_KEY	非法密钥。对应数值“4”。
	//BMAP_STATUS_INVALID_REQUEST	非法请求。对应数值“5”。
	//BMAP_STATUS_PERMISSION_DENIED	没有权限。对应数值“6”。(自 1.1 新增)
	//BMAP_STATUS_SERVICE_UNAVAILABLE	服务不可用。对应数值“7”。(自 1.1 新增)
	//BMAP_STATUS_TIMEOUT	超时。对应数值“8”。(自 1.1 新增)
	
	
	map.addEventListener("click",function(e){
		//alert(e.point.lng + "," + e.point.lat);
		
		var point = new BMap.Point(e.point.lng, e.point.lat);
		var marker = new BMap.Marker(point);  // 创建标注
		
		//删除地图标注
		map.clearOverlays();
		//新添加一个标注地址
		marker.setPosition(point);
		//添加标注
        map.addOverlay(marker);               // 将标注添加到地图中
        initMap(e.point.lat,e.point.lng);
		
	});
	
	
	function initMap(lat,lng){
		
		$.ajax({
			//url : '/mts/system/activity/getPoi/json?web=&lat='+r.point.lat+'&lon='+r.point.lng,
			url:"http://api.map.baidu.com/geocoder/v2/?ak=NgMZRcGV4cz2tc9tvQD7M5VkgB7gUT3g&callback=renderReverse&location="+ lat + "," + lng + "&output=json&pois=1",	
			type : "post",
			dataType : "jsonp",
			success : function(result){
				if(result.status=="error"){
					window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
					return;
				}
				
				if(result.result!=undefined){
					
					$("#details").html("");
					
					$("#detail_first_tmpl").tmpl(result.result).appendTo($("#details"));
					
					for (var int = 0; int < result.result.pois.length; int++) {
						$("#detail_list_tmpl").tmpl(result.result.pois[int]).appendTo($("#details"));
					}
					
				}
				
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				console.log(XMLHttpRequest) ;
				console.log(textStatus) ;
			}
		});
		
	}
	
	
	function setCookie(name,value) 
	{
	    var Days = 30; 
	    var exp = new Date(); 
	    exp.setTime(exp.getTime() + Days*24*60*60*1000); 
	    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString(); 
	}  
	
	function changeLat(lat,lng){
		setCookie("cardLongitude",lng);
		setCookie("cardLatitude",lat);
		window.location.href="/mts/appWeb/card/cardSave.jsp";
	}
	function setCookie(name,value)
	{
	    var Days = 30;
	    var exp = new Date();
	    exp.setTime(exp.getTime() + Days*24*60*60*1000);
	    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
	}
</script>
	<script id="detail_list_tmpl" type="text/x-jquery-tmpl">
		<li onclick="changeLat({{= point.y}},{{= point.x}});">
            <img src="<%=basePath%>/js/appWeb/images/xiao.png" alt="">
            <div class="txtR">
				<p><em>{{= name}}</em></p>
				<p>{{= addr}}</p>
            </div>
        </li>
	</script>
	
	<script id="detail_first_tmpl" type="text/x-jquery-tmpl">
		<li onclick="changeLat({{= location.lat}},{{= location.lng}});">
            <img src="<%=basePath%>/js/appWeb/images/da.png" alt="">
            <div class="txtR">
                <p><em>{{= business}}</em></p>
                <p>{{= formatted_address}}</p>
            </div>
        </li>
	</script>

