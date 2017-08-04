var This=this;
var userId;

//获取用户信息
$.ajax({
	url : '/mts/system/appuser/look/json?web=',
	type : "post",
	dataType : "json",
	success : function(result){

		if(result.status=="error"){
			window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
			return;
		}
		
		if(result.data!=undefined){
			
			if(result.data.sign!=undefined){
				if(result.data.sign.length>15){
					result.data.signVal=result.data.sign.substring(0,15)+"...";
				}else{
					result.data.signVal=result.data.sign;
				}
			}
			userId = result.data.id;
			$('#appUser_update_tmpl').tmpl(result.data).appendTo($('#detail'));
			
		}
		
	},
	error:function(XMLHttpRequest, textStatus, errorThrown){
		console.log(XMLHttpRequest) ;
		console.log(textStatus) ;
	}
});

    
    function updateName(name){
    	
    	$.ajax({
			url : '/mts/system/appuser/update/json?name='+name+'&id='+userId,
			type : "post",
			dataType : "json",
			success : function(result){

				if(result.status=="error"){
					alert(result.message);
					return;
				}
				
				location.reload() ;
				
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				console.log(XMLHttpRequest) ;
				console.log(textStatus) ;
			}
		});
    	
    	
    	
    }
    
    function updateSex(sex){
    	
    	$.ajax({
			url : '/mts/system/appuser/update/json?sex='+sex+'&id='+userId,
			type : "post",
			dataType : "json",
			success : function(result){

				if(result.status=="error"){
					alert(result.message);
					return;
				}
				
				location.reload() ;
				
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				console.log(XMLHttpRequest) ;
				console.log(textStatus) ;
			}
		});
    	
    }

    function updateSign(sign){
    	
    	$.ajax({
			url : '/mts/system/appuser/update/json?sign='+sign+'&id='+userId,
			type : "post",
			dataType : "json",
			success : function(result){

				if(result.status=="error"){
					alert(result.message);
					return;
				}
				
				location.reload() ;
				
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				console.log(XMLHttpRequest) ;
				console.log(textStatus) ;
			}
		});
    	
    }
    
    function updateHeader(header){
    	
    	$.ajax({
			url : '/mts/system/appuser/update/json?header='+header+'&id='+userId,
			type : "post",
			dataType : "json",
			success : function(result){

				if(result.status=="error"){
					alert(result.message);
					return;
				}
				
				location.reload() ;
				
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