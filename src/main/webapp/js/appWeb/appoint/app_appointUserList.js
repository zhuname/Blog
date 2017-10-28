var This=this;
var nextPage=1;
//初始化页面
show();

//加载页面方法
function show(){
		//获取用户信息
	if(getQueryString('itemId')!=undefined&&getQueryString('itemId')!=null&&getQueryString('type')!=undefined&&getQueryString('type')!=null){
		$.ajax({
			url : '/mts/system/appoint/appointStatics/json?web=&type='+getQueryString('type')+'&itemId='+getQueryString('itemId'),
			type : "post",
			dataType : "json",
			success : function(result){
				
				if(result.status=="error"){
					window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
					return;
				}
				
				if(result.data!=undefined){
					//获取消息记录
					if(nextPage==1){
						$('#appoint_top_tmpl').tmpl(result.data).appendTo($('#detail'));
					}
					$.ajax({
						url : '/mts/system/appoint/appointList/json?web=&type='+getQueryString('type')+'&itemId='+getQueryString('itemId')+"&pageIndex="+nextPage,
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
									
									result.data[int].phone=result.data[int].phone.substr(0, 3) + '****' + result.data[int].phone.substr(7);
									
									$('#appoint_list_tmpl').tmpl(result.data[int]).appendTo($('#detail'));
									
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