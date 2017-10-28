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
		url : '/mts/system/circle/list/json?web='+"&userId="+userId+data+"&appuserId="+userId,
		type : "post",
		dataType : "json",
		success : function(result){
			
			if(result.status=="error"){
				window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
				return;
			}
			
			if(result.data!=undefined){
				for (var int = 0; int < result.data.length; int++){
					
					if(result.data[int].image!=undefined){
						var images=result.data[int].image.split(";");
						result.data[int].images=images;
						
					}
					
					if(result.data[int].createTime){
						result.data[int].m=result.data[int].createTime.substring(5,7);
						result.data[int].d=result.data[int].createTime.substring(8,10);
						result.data[int].t=result.data[int].createTime.substring(11,16);
						
					}
					
					$('#circle_list_tmpl').tmpl(result.data[int]).appendTo($('#circle'));
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

function zan(id){
	//加载页面方法
	$.ajax({
		url : '/mts/system/oper/update/json?web=&type=7'+"&itemId="+id+"&userId="+userId,
		type : "post",
		dataType : "json",
		success : function(result){
			if(result.status=="error"){
				window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
				return;
			}
			$('#zan'+id).show();
			$('#zanShow'+id).hide();
			$('#topCount'+id).html(parseInt($('#topCount'+id).html())+1);
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			console.log(XMLHttpRequest) ;
			console.log(textStatus) ;
		}
	});
}


function locationHref(id){
	if(dianji==1){
		dianji=0;
		return;
	}
	window.location.href='/mts/appWeb/circle/circleDetail.jsp?id='+id;
}

function getDateDiff(dateTimeStamp){
	dateTimeStamp=GetDateDiff(dateTimeStamp);
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