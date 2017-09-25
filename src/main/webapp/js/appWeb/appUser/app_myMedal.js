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
				$('#appuser_detail_tmpl').tmpl(result.data).appendTo($('#detail'));
				
				if(result.data.userMedals!=undefined){
					
					for (var int = 0; int < result.data.userMedals.length; int++) {
						$('#appuser_xunzhang_tmpl').tmpl(result.data.userMedals[int]).appendTo($('#xzmb'));
						
					}
					
				}
				
				
				//获取用户信息
				$.ajax({
					url : '/mts/system/medal/all/json?web=&userId='+result.data.id,
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
								$('#appuser_list_tmpl').tmpl(result.data[int]).appendTo($('#list'));
								
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
	}
}


function getQueryString(aaa) { 
	var reg = new RegExp("(^|&)" + aaa + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
} 