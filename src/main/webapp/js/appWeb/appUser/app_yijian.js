var This=this;
var id;


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
			
			id=result.data.id;
		}
		
	},
	error:function(XMLHttpRequest, textStatus, errorThrown){
		console.log(XMLHttpRequest) ;
		console.log(textStatus) ;
	}
});






function send(){
	
	
	if($("#phone").val()==""||$("#content").val()==""){
		
		alert("请完善信息");
		
		return;
	}
	//获取用户信息
	$.ajax({
		url : '/mts/system/feedback/update/json?web=&userId='+id+'&content='+$("#content").val()+'&phone='+$("#phone").val(),
		type : "post",
		dataType : "json",
		success : function(result){
			
			if(result.status=="error"){
				alert(result.message);
				return;
			}
				
			window.location.href="/mts/appWeb/appuser/shezhi.jsp";
			
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