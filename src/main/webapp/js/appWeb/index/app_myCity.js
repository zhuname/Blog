var This=this;
var cityIds="";
var cityNames="";
$.ajax({
	url : '/mts/system/city/list/json?web=&open=1',
	type : "post",
	dataType : "json",
	success : function(result){
		
		if(result.status=="error"){
			window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
			return;
		}
		
		if(result.data!=undefined){
			//获取消息记录
			
			for (var int = 0; int < result.data.length; int++) {
				$('#city_list_tmpl').tmpl(result.data[int]).appendTo($('#list'));
			}
			
			
		}
		
	},
	error:function(XMLHttpRequest, textStatus, errorThrown){
		console.log(XMLHttpRequest) ;
		console.log(textStatus) ;
	}
});

if(getCookie("htmlCityId")==undefined||getCookie("htmlCityId")==null||getCookie("htmlCityId")==""){
	
	setCookie("htmlCityId",321300);
	
	
}

$.ajax({
	url : '/mts/system/city/detail/json?web=&id='+getCookie("htmlCityId"),
	type : "post",
	dataType : "json",
	success : function(result){
		if(result.status=="error"){
			window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
			return;
		}
		if(result.data!=undefined){
			//获取用户信息
			$('#city').html(result.data.name);
		}
	},
	error:function(XMLHttpRequest, textStatus, errorThrown){
		console.log(XMLHttpRequest) ;
		console.log(textStatus) ;
	}
});


function select(){
	
	
	$.ajax({
		url : '/mts/system/city/list/json?web=&open=1&name='+$("#name").val(),
		type : "post",
		dataType : "json",
		success : function(result){
			
			if(result.status=="error"){
				window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
				return;
			}
			
			if(result.data!=undefined){
				//获取消息记录
				$('#list').html("");
				for (var int = 0; int < result.data.length; int++) {
					$('#city_list_tmpl').tmpl(result.data[int]).appendTo($('#list'));
				}
				
				
			}
			
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			console.log(XMLHttpRequest) ;
			console.log(textStatus) ;
		}
	});
			
	
}

function checkIndex(id,name){
	
	setCookie("htmlCityId", id);
	setCookie("htmlCityId1", getCookie("htmlCityId"));
			
	window.location.href="/mts/appWeb/index/index.jsp";
			
	
}

window.onscroll=function(){
	var a = document.documentElement.scrollTop==0? document.body.clientHeight : document.documentElement.clientHeight;
	var b = document.documentElement.scrollTop==0? document.body.scrollTop : document.documentElement.scrollTop;
	var c = document.documentElement.scrollTop==0? document.body.scrollHeight : document.documentElement.scrollHeight;
	if(a+b==c){
		nextPage=nextPage+1;
		show();
	}
}

function getQueryString(aaa) { 
	var reg = new RegExp("(^|&)" + aaa + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
} 

