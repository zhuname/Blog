var This=this;
var nextPage=1;
var dataString;
//初始化页面

if(getQueryString('itemId')!=undefined&&getQueryString('itemId')!=null){
	$.ajax({
		url : '/mts/system/moneydetail/statics/json?type='+getQueryString("type")+'&web=&itemId='+getQueryString("itemId"),
		type : "post",
		dataType : "json",
		success : function(result){
			if(result.status=="error"){
				window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
				return;
			}
			if(result.data!=undefined){
				$('#user_top_tmpl').tmpl(result.data).appendTo($('#detail'));
				show();
			}
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				console.log(XMLHttpRequest) ;
				console.log(textStatus) ;
			}
		});
}

//加载页面方法
function show(){
	if(getQueryString('itemId')!=undefined&&getQueryString('itemId')!=null){
				$.ajax({
					url : '/mts/system/moneydetail/list/json?type='+getQueryString("type")+'&web=&itemId='+getQueryString("itemId")+"&pageIndex="+nextPage,
					type : "post",
					dataType : "json",
					success : function(result){
						if(result.status=="error"){
							window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
							return;
						}
						if(result.data!=undefined){
							for (var int = 0; int < result.data.length; int++) {
								
								$('#user_list_tmpl').tmpl(result.data[int]).appendTo($('#detail'));
								
							}
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
