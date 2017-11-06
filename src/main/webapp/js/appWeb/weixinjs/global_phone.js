!function(x) {
    function w() {
        var a = r.getBoundingClientRect().width;
        a / v > 640 && (a = 640 * v), x.rem = a / 16, r.style.fontSize = x.rem + "px"
    }
    var v, u, t, s = x.document, r = s.documentElement, q = s.querySelector('meta[name="viewport"]'), p = s.querySelector('meta[name="flexible"]');
    if (q) {
        console.warn("将根据已有的meta标签来设置缩放比例");
        var o = q.getAttribute("content").match(/initial\-scale=(["‘]?)([\d\.]+)\1?/);
        o && (u = parseFloat(o[2]), v = parseInt(1 / u))
    } else {
        if (p) {
            var o = p.getAttribute("content").match(/initial\-dpr=(["‘]?)([\d\.]+)\1?/);
            o && (v = parseFloat(o[2]), u = parseFloat((1 / v).toFixed(2)))
        }
    }
    if (!v && !u) {
        var n = (x.navigator.appVersion.match(/android/gi), x.navigator.appVersion.match(/iphone/gi)), v = x.devicePixelRatio;
        v = n ? v >= 3 ? 3 : v >= 2 ? 2 : 1 : 1, u = 1 / v
    }
    if (r.setAttribute("data-dpr", v), !q) {
        if (q = s.createElement("meta"), q.setAttribute("name", "viewport"), q.setAttribute("content", "initial-scale=" + u + ", maximum-scale=" + u + ", minimum-scale=" + u + ", user-scalable=no"), r.firstElementChild) {
            r.firstElementChild.appendChild(q)
        } else {
            var m = s.createElement("div");
            m.appendChild(q), s.write(m.innerHTML)
        }
    }
    x.dpr = v, x.addEventListener("resize", function() {
        clearTimeout(t), t = setTimeout(w, 300)
    }, !1), x.addEventListener("pageshow", function(b) {
        b.persisted && (clearTimeout(t), t = setTimeout(w, 300))
    }, !1), "complete" === s.readyState ? s.body.style.fontSize = 12 * v + "px" : s.addEventListener("DOMContentLoaded", function() {
        s.body.style.fontSize = 12 * v + "px"
    }, !1), w()
}(window);

document.write(" <script language=\"javascript\" src=\"http://res.wx.qq.com/open/js/jweixin-1.2.0.js\" > <\/script>"); 


function GetDateDiff(diffTime) {
    //将xxxx-xx-xx的时间格式，转换为 xxxx/xx/xx的格式   
	    	startTime = diffTime.replace(/\-/g, "/");  
	    	return startTime;
};  






$(function(){
	
	var isHas="2";
	
	console.log($("title").is(".waitCheck"));
	if($("title").is(".waitCheck")){
		isHas="1";
	}else{
		isHas="2";
	}
	
	//重复执行某个方法 
	 //window.setInterval("hello()",1000); 
	
	var utl1 = location.href;
	//var utl1 = location.href.split('#')[0];
	urls=utl1.replace(/\?from=timeline&isappinstalled=0/, "");
	urls=urls.replace(/\?from=groupmessage&isappinstalled=0/, "");
	urls=urls.replace(/\?from=singlemessage&isappinstalled=0/, "");


	if(utl1.indexOf("?from=timeline&isappinstalled=0")>0){
		urls=urls.replace("&code","&cod");
		urls=urls.replace("?code","?cod");
		window.location.href=urls;
		return;
	}
	if(utl1.indexOf("?from=groupmessage&isappinstalled=0")>0){
		urls=urls.replace("&code","&cod");
		urls=urls.replace("?code","?cod");
		window.location.href=urls;
		return;
	}
	if(utl1.indexOf("?from=singlemessage&isappinstalled=0")>0){
		urls=urls.replace("&code","&cod");
		urls=urls.replace("?code","?cod");
		window.location.href=urls;
		return;
	}



	urls=utl1.replace(/&from=timeline&isappinstalled=0/, "");
	urls=urls.replace(/&from=groupmessage&isappinstalled=0/, "");
	urls=urls.replace(/&from=singlemessage&isappinstalled=0/, "");


	if(utl1.indexOf("&from=timeline&isappinstalled=0")>0){
		
		urls=urls.replace("&code","&cod");
		urls=urls.replace("?code","?cod");
		
		window.location.href=urls;
		return;
	}
	if(utl1.indexOf("&from=groupmessage&isappinstalled=0")>0){
		urls=urls.replace("&code","&cod");
		urls=urls.replace("?code","?cod");
		window.location.href=urls;
		return;
	}
	if(utl1.indexOf("&from=singlemessage&isappinstalled=0")>0){
		urls=urls.replace("&code","&cod");
		urls=urls.replace("?code","?cod");
		window.location.href=urls;
		return;
	}

	
	if(isHas=="2"){
		$.ajax({
	    	url : '/mts/system/wxShare/ceshi/json',
	    	   type : "post",
	    	   data:{'shareUrl':urls},
	    	   dataType: 'json',  
	    	    	success : function(result) {
	    	    	
					    	    	
					wx.checkJsApi({
					    jsApiList: ['chooseImage'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
					    success: function(res) {
					        // 以键值对的形式返回，可用的api值true，不可用为false
					        // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
					    },
					    fail:function(res){
					    }
					});
	    	    	
					  wx.config({
					    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
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
	}
	
	
	$(".waitCheck").bind("DOMNodeInserted",function(){
		$.ajax({
	    	url : '/mts/system/wxShare/ceshi/json',
	    	   type : "post",
	    	   data:{'shareUrl':urls},
	    	   dataType: 'json',  
	    	    	success : function(result) {
	    	    	
					    	    	
					wx.checkJsApi({
					    jsApiList: ['chooseImage'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
					    success: function(res) {
					        // 以键值对的形式返回，可用的api值true，不可用为false
					        // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
					    },
					    fail:function(res){
					    }
					});
	    	    	
					  wx.config({
					    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
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
	})	
	

		

	          

	            wx.ready(function(){
	                // wx.hideOptionMenu();
	                wx.onMenuShareTimeline({
	                    title: $(document).attr("title") ,
	                    link: document.location.href,
	                    imgUrl: 'http://app.mtianw.com/mts/js/appWeb/images/App_icon_1.jpg',
	                    success: function () { 
	                        // 用户确认分享后执行的回调函数
	                    },
	                    cancel: function () { 
	                        // 用户取消分享后执行的回调函数
	                    }
	                });
	                wx.onMenuShareAppMessage({
	                      title: $(document).attr("title") ,
	                      desc: '美天赏APP，同城生活互动平台；低投入、精准投放、深度记忆、方便快捷、精准数据。',
	                      link: document.location.href,
	                      imgUrl: 'http://app.mtianw.com/mts//js/appWeb/images/App_icon_1.jpg',
	                      trigger: function (res) {
	                        // 不要尝试在trigger中使用ajax异步请求修改本次分享的内容，因为客户端分享操作是一个同步操作，这时候使用ajax的回包会还没有返回
	                      },
	                      success: function (res) {
	                      },
	                      cancel: function (res) {
	                      },
	                      fail: function (res) {
	                      }
	                    });
	            });
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	var code = getQueryString("code");
	var test = window.location.href;
	if(test+""!="http://app.mtianw.com/mts/appWeb/appuser/appuserLogin.jsp"){
		if(code!=null&&code!=undefined&&code!="undefined"){
			
		}else{
			
			setCookie("backUrl",test);
		}
	}else{
		setCookie("backUrl",document.referrer);
	}
	
	if(getQueryString("cityId")!=undefined&&getQueryString("cityId")!=null){
		setCookie("htmlCityId",getQueryString("cityId"));
	}else{
		if(getCookie("htmlCityId")==undefined||getCookie("htmlCityId")==null||getCookie("htmlCityId")==""){
			
			setCookie("htmlCityId",321300);
			
		}
		
	}
	
});

function hrefIndexShare(type) {
	/*var test = document.referrer;
	if(test+""==""){*/
	switch (type) {
	case 1:
		window.location.href="/mts/appWeb/posterPackage/posterPackage.jsp?cityId="+getCookie("htmlCityId");;
		break;
	case 2:
		window.location.href="/mts/appWeb/mediaPackage/mediaPackage.jsp?cityId="+getCookie("htmlCityId");;
		break;
	case 3:
		window.location.href="/mts/appWeb/activity/activity.jsp?cityId="+getCookie("htmlCityId");;
		break;
	case 4:
		window.location.href="/mts/appWeb/circle/circle.jsp?cityId="+getCookie("htmlCityId");;
		break;
	default:
		if(document.referrer!=""){
			window.location.href=document.referrer;
		}else{
			window.location.href="/mts/appWeb/index/index.jsp";
		}
		break;
	}
	/*	return;
	}
	if(getQueryString("isShare")!=undefined||getQueryString("state")=="isShare"){
		window.location.href="/mts/appWeb/index/index.jsp";
	}else{
		$(document).ready(function(){ 
		    var ua = navigator.userAgent.toLowerCase();  
		    if(ua.match(/MicroMessenger/i)=="micromessenger") {  
		    	window.history.go(-2);
		    } else {
		    	window.history.go(-1);
		    }  
		}); 
	}*/
    
}; 

function getQueryString(aaa) { 
	var reg = new RegExp("(^|&)" + aaa + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
} 
function appLink(){
	window.location.href="/mts/shareApp/down.html"
}
function reserver(){
	$("#payTmpl").css("position","absolute")
	$('.kq_mask').show();
	$("body").scrollTop(0);
	$(".wraper").css("height","100%")
}
function removeRes(){
	$("#payTmpl").css("position","fixed")
	$('.kq_mask').hide();
	$(".wraper").css("height","auto")
}



function setCookie(name,value) 
{
    var Days = 30; 
    var exp = new Date(); 
    exp.setTime(exp.getTime() + Days*24*60*60*1000); 
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString(); 
} 

function getCookie(name) 
{ 
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
 
    if(arr=document.cookie.match(reg))
 
        return unescape(arr[2]); 
    else 
        return null; 
} 

function delCookie(name) 
{ 
    var exp = new Date(); 
    exp.setTime(exp.getTime() - 1); 
    var cval=getCookie(name); 
    if(cval!=null) 
        document.cookie= name + "="+cval+";expires="+exp.toGMTString(); 
}

function getQueryString(aaa) { 
	var reg = new RegExp("(^|&)" + aaa + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
}


