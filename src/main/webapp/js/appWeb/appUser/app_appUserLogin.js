var This=this;

	$(document).ready(function(){ 
    var ua = navigator.userAgent.toLowerCase();  
    if(ua.match(/MicroMessenger/i)=="micromessenger") {  
    	var openid=null;
    	This.openid=openid;
    	var code=getQueryString("code");
    	$("#qqShow").remove();
    } else {
    	 //alert('判断：非微信端登录');
    	$("#wxShow").remove();
    }  
	}); 
    
    function login(){
    	
    	var phone=$("#phone").val();
    	var password=$("#password").val();
    	
    	if(phone==""||phone==null){
    		alert("请输入手机号");
    	}
    	
    	if(password==""||password==null){
    		alert("请输入密码");
    	}
    	
    	$.ajax({
			url : '/mts/system/appuser/login/json?web=&phone='+phone+"&password="+password,
			type : "post",
			dataType : "json",
			success : function(result){

				if(result.status=="error"){
					alert(result.message);
					return;
				}
				
				console.log(result);
				if(result.data!=undefined){
					window.location.href="/mts/appWeb/appuser/appuserLook.jsp"; 
				}
				
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				console.log(XMLHttpRequest) ;
				console.log(textStatus) ;
			}
		});
    	
    	
    	
    }
    
    
    var code=getQueryString("code");
    var openid=null;
    var unionid = null ;
    $().ready(function(){
    if(code!=null&&code!=undefined&&code!="undefined"){
    	$.ajax({
    		url : '/mts/system/appuser/openId/json?web=1&code='+code,
    		type : "get",
    		success : function(result) {
    			data = JSON.parse(result.data);
    			data = JSON.parse(data);
    			$.ajax({
    	    		url : '/mts/system/appuser/getWxUser/json?web=1&openId='+data.openid,
    	    		type : "get",
    	    		success : function(result) {
    	    			data = JSON.parse(result.data);
    	    			var checkSex="";
    	    			if(data.sex==1){
    	    				checkSex="男";
    	    			}else if(data.sex==2){
    	    				checkSex="女";
    	    			}
    	    			$.ajax({
    	    	    		url : '/mts/system/appuser/loginS/json?web=1&wxNum='+data.unionid+'&header='+data.headimgurl+'&sex='+checkSex+'&name='+data.nickname,
    	    	    		type : "get",
    	    	    		success : function(result) {
    	    	    			window.location.href="/mts/appWeb/appuser/appuserLook.jsp"; 
    	    	    		},
    	    	    		error:function(XMLHttpRequest, textStatus, errorThrown){
    	    	    			console.log(XMLHttpRequest) ;
    	    	    			console.log(textStatus) ;
    	    	    		}
    	    	    	});
    	    			
    	    		},
    	    		error:function(XMLHttpRequest, textStatus, errorThrown){
    	    			console.log(XMLHttpRequest) ;
    	    			console.log(textStatus) ;
    	    		}
    	    	});
    			
    		},
    		error:function(XMLHttpRequest, textStatus, errorThrown){
    			console.log(XMLHttpRequest) ;
    			console.log(textStatus) ;
    		}
    	});
    }
    })
    
    function wxCheck() { 
    	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx8653ea068146c48c&redirect_uri=http://app.mtianw.com/mts/appWeb/appuser/appuserLogin.jsp&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
    }

function getQueryString(aaa) { 
	var reg = new RegExp("(^|&)" + aaa + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
} 