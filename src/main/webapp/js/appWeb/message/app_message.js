var This=this;
var nextPage=1;
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
		//获取用户信息
			$.ajax({
				url : '/mts/system/message/list/json?web=&userId='+result.data.id+'&pageIndex='+nextPage,
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
							
							//处理日期，
							if(result.data[int].createTime!=undefined){
								var date=result.data[int].createTime.substring(0,16);
								result.data[int].createTime=date;
							}
							
							$('#message_list_tmpl').tmpl(result.data[int]).appendTo($('#messageList'));
							
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

function getQueryString(aaa) { 
	var reg = new RegExp("(^|&)" + aaa + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
} 