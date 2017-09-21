var This=this;
var nextPage=1;
var dataString="";
var dianji=0;
var userId;
//初始化页面
show();

//加载页面方法
function show(){
	var data='&pageIndex='+nextPage+dataString;
	
	//加载页面方法
	$.ajax({
	url : '/mts/system/appuser/look/json?web=',
	type : "post",
	dataType : "json",
	success : function(result){
		
		if(result.status=="error"){
			window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
			return;
		}
		userId=result.data.id;
	
	$.ajax({
		url : '/mts/system/usercard/list/json?web='+"&userId="+userId+data,
		type : "post",
		dataType : "json",
		success : function(result){
			
			if(result.status=="error"){
				
				window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
				
				return;
				
			}
			
			if(result.data!=undefined){
				
				for (var int = 0; int < result.data.length; int++){
					
					if(result.data[int].expTime){
						
						result.data[int].expTime=result.data[int].expTime.substring(0,10);
						
					}
					
					$('#card_list_tmpl').tmpl(result.data[int]).appendTo($('#card'));
					
				}
				
			}
			
		},
		
		error:function(XMLHttpRequest, textStatus, errorThrown){
			
			console.log(XMLHttpRequest);
			
			console.log(textStatus);
			
		}
	});
	},
	error:function(XMLHttpRequest, textStatus, errorThrown){
		console.log(XMLHttpRequest);
		console.log(textStatus);
	}
});
}

	function duihuan(){
		$.ajax({
			url : '/mts/system/card/changeCardjson/json?web=&userId='+userId+'&cardCode='+$("#cardCode").val(),
			type : "post",
			dataType : "json",
			success : function(result){
				
				if(result.status=="error"){
					alert(result.message);
					return;
				}
				
				if(result.data!=undefined){
					window.location.href="/mts/appWeb/appuser/myCard.jsp";
					return;
				}
				
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				console.log(XMLHttpRequest) ;
				console.log(textStatus) ;
			}
		});
	
	}

function getDateDiff(dateTimeStamp){
	
	var stringTime = dateTimeStamp;
	var timestamp2 = Date.parse(new Date(stringTime));
	dateTimeStamp = timestamp2 ;
	
	var minute = 1000 * 60;
	var hour = minute * 60;
	var day = hour * 24;
	var month = day * 30;
	var now = new Date().getTime();
	var diffValue = now - dateTimeStamp;
	if(diffValue < 0){return;}
	var monthC =diffValue/month;
	var weekC =diffValue/(7*day);
	var dayC =diffValue/day;
	var hourC =diffValue/hour;
	var minC =diffValue/minute;
	
	if(monthC>=1){
		result="" + parseInt( monthC ) + "月前";
	} else if ( weekC >= 1 ) {
		result="" + parseInt( weekC ) + "周前";
	} else if ( dayC >= 1 ) {
		result=""+ parseInt ( dayC ) + "天前";
	} else if ( hourC >= 1 ) {
		result=""+ parseInt ( hourC ) + "小时前";
	} else if ( minC >= 1 ) {
		result=""+ parseInt ( minC ) + "分钟前";
	} else {
		result="刚刚";
	}
	
	return result;
	
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