var This=this;
var nextPage=1;
var dataString="";
var dianji=0;
var userId;
//初始化页面
show();

function select(){
	var titleString= $('#title').val();
	nextPage=1;
	$('#posterPackage').html("");
	dataString='&title='+titleString;
	show();
}

function selectSort(type){
	nextPage=1;
	$('#posterPackage').html("");
	if(type==1){
		dataString='&selectType=1';
	}else if(type==2){
		dataString='&selectType=2';
	}else if(type==3){
		dataString='&selectType=3';
	}
	show();
}

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
		url : '/mts/system/circle/list/json?web='+"&cityId="+getQueryString("cityId")+"&appuserId="+userId+data,
		type : "post",
		dataType : "json",
		success : function(result){
			if(result.status=="error"){
				window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
				return;
			}
			if(result.data!=undefined){
				
				for (var int = 0; int < result.data.length; int++){
					
					if(result.data[int].createTime){
						
						result.data[int].createTime=getDateDiff(result.data[int].createTime);
						
					}
					
					if(result.data[int].image!=undefined){
						var images=result.data[int].image.split(";");
						result.data[int].images=images;
						
					}
					console.log(result.data[int]);
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


$.ajax({
	url : '/mts/system/lunbopic/list/json?web=&position=6&cityId='+getQueryString("cityId"),
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
				$('#lunbo_list_tmpl').tmpl(result.data[int]).appendTo($('#lunbo'));
			}
			
			TouchSlide({
				slideCell:"#bann",
				titCell:".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
				mainCell:".bd ul", 
				effect:"left",
				autoPlay:true,//自动播放
				autoPage:true //自动分页
			});
			
			
		}
		
	},
	error:function(XMLHttpRequest, textStatus, errorThrown){
		console.log(XMLHttpRequest) ;
		console.log(textStatus) ;
	}
});


function xinzeng(){
	window.location.href='/mts/appWeb/circle/circleSave.jsp?cityId='+getQueryString("cityId");
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

function showCheck(obj){
	$(obj).siblings('.more_ul').toggle();
	$(obj).siblings('.arr_up_down').toggle();
	dianji=1;
	$("#black-box").toggle()
	console.log("dsfs");
	console.log($("#black-box"))
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