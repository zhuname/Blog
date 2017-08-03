var This=this;
var nextPage=1;
var userId;
//初始化页面
show();

//加载页面方法
function show(){
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
				
				if(result.data.fansNum!=undefined){
					$('#fansNum').html(result.data.fansNum+"人关注了我");
				}
				
				userId=result.data.id;
			//获取用户信息
			$.ajax({
				url : '/mts/system/attention/fensiList/json?itemId='+result.data.id+'&pageIndex='+nextPage,
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
							
							console.log(result.data[int]);
							
							$('#fans_list_tmpl').tmpl(result.data[int]).appendTo($('#detail'));
							
						}
						
					}
					
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					console.log(XMLHttpRequest) ;
					console.log(textStatus) ;
				}
			});
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			console.log(XMLHttpRequest) ;
			console.log(textStatus) ;
		}
	});
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


function attr(itemId) { 
	//获取用户信息
	$.ajax({
		url : '/mts/system/attention/update/json?itemId='+itemId+'&userId='+userId,
		type : "post",
		dataType : "json",
		success : function(result){
			
			if(result.status=="error"){
				window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
				return;
			}
			
			if(result.data!=undefined){
				location.reload() ;
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