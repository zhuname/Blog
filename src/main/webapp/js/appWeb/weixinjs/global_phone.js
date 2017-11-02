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

function GetDateDiff(diffTime) {
    //将xxxx-xx-xx的时间格式，转换为 xxxx/xx/xx的格式   
	    	startTime = diffTime.replace(/\-/g, "/");  
	    	return startTime;
};  

$(function(){
    pushHistory();
});
function pushHistory(){
    window.addEventListener("popstate", function(e){
        alert("回退！");

        window.location.reload();
    }, false); 
    var state = {
        title:"",
        url: "#"
    }; 
    window.history.pushState(state, "", "#"); 
};

function hrefIndexShare() {
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
	}
    
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

