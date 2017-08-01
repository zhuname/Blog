var This=this;


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
    	 //alert('判断：非微信端登录');
    }  

    
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


function getQueryString(aaa) { 
	var reg = new RegExp("(^|&)" + aaa + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
} 