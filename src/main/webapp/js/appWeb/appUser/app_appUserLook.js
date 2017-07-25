var This=this;


	debugger;
    var ua = navigator.userAgent.toLowerCase();  
    if(ua.match(/MicroMessenger/i)=="micromessenger") {  
    	var openid=null;
    	This.openid=openid;
    	var code=getQueryString("code");
    	if(code!=null){
    		$.ajax({
    			url : '../admin/data/read_openId.action?code='+code,
    			type : "get",
    			success : function(result){
    				result = JSON.parse(result);
    				data = JSON.parse(result.data);
    				data = JSON.parse(data);
    				openid=data.openid;
    			},
    			error:function(XMLHttpRequest, textStatus, errorThrown){
    				console.log(XMLHttpRequest) ;
    				console.log(textStatus) ;
    			}
    		});
    	}else{
    		$.ajax({
    			url : '/mts/system/appuser/openWx/json',
    			type : "get",
    			success : function(result){
    				window.location.href=result;
    			},
    			error:function(XMLHttpRequest, textStatus, errorThrown){
    				console.log(XMLHttpRequest) ;
    				console.log(textStatus) ;
    			}
    		});
    	}
    } else {  
    	$.ajax({
			url : '/mts/system/appuser/openWx/json',
			type : "get",
			success : function(result){
				window.location.href=result;
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				console.log(XMLHttpRequest) ;
				console.log(textStatus) ;
			}
		});
    	 //alert('判断：非微信端登录');
    }  



function getQueryString(aaa) { 
	var reg = new RegExp("(^|&)" + aaa + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
} 