var This=this;
var nextPage=1;
var dataString="";
var dianji=0;
var userId="";
var userData="";
//初始化页面
show();

function showGengduo(id,obj){
	event.stopPropagation()
	if($(obj).attr('show')==1){
		$(obj).attr("show","0");
		javascript:$('#contents'+id).show();
		$(obj).html('收起');
	}else{
		$(obj).attr("show","1");
		javascript:$('#contents'+id).hide();
		$(obj).html('查看全部');
	}
	
}

function select(){
	var titleString= $('#title').val();
	nextPage=1;
	$('#circle').html("");
	dataString='&selectTitle='+titleString;
	show();
}

function selectSort(type){
	nextPage=1;
	$('#circle').html("");
	if(type==1){
		dataString='&sort=1';
	}else if(type==2){
		if(userId==""){
			window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
			return;
		}
		dataString='&sort=2';
	}else if(type==3){
		if(userId==""){
			window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
			return;
		}
		dataString='&sort=3';
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
			userId="";
		}else{
			userId=result.data.id;
			userData="&appuserId="+userId;
		}
	
	$.ajax({
		url : '/mts/system/circle/list/json?web='+"&cityId="+getQueryString("cityId")+userData+data,
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
						var imagess=new Array();
						for (var int2 = 0; int2 < images.length; int2++) {
							if(images[int2]!=""){
								imagess[int2]=images[int2];
							}
						}
						
						result.data[int].images=imagess;
					}
					
					if(result.data[int].content.length>100){
						result.data[int].quanbu=1;
						
						var con1="";
						var con2="";
						
						for (var int3 = 0; int3 < result.data[int].content.length; int3++) {
							if(int3<100){
								con1+=result.data[int].content[int3];
							}else{
								con2+=result.data[int].content[int3];
							}
						}
						result.data[int].content=con1;
						result.data[int].contents=con2;
						
					}
					
					if(result.data[int].isShield==1){
						result.data[int].isShields="取消屏蔽";
					}else{
						result.data[int].isShields="屏蔽";
					}
					
					$('#circle_list_tmpl').tmpl(result.data[int]).appendTo($('#circle'));
					javascript:$('#contents'+result.data[int].id).hide();
					
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


function attr(itemUserId){
	if(userId==""){
		window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
		return;
	}
	//加载页面方法
	$.ajax({
	url : '/mts/system/attention/update/json?web=&userId='+userId+'&itemId='+itemUserId,
	type : "post",
	dataType : "json",
	success : function(result){
		if(result.status=="error"){
			
			window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
			return;
		}
		 window.location.reload();
	},
	error:function(XMLHttpRequest, textStatus, errorThrown){
			console.log(XMLHttpRequest) ;
			console.log(textStatus) ;
		}
	});
}

function pingbi(itemUserId){
	if(userId==""){
		window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
		return;
	}
	//加载页面方法
	$.ajax({
	url : '/mts/system/shield/update/json?web=&userId='+userId+'&itemId='+itemUserId,
	type : "post",
	dataType : "json",
	success : function(result){
		if(result.status=="error"){
			
			window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
			return;
		}
		 window.location.reload();
	},
	error:function(XMLHttpRequest, textStatus, errorThrown){
			console.log(XMLHttpRequest) ;
			console.log(textStatus) ;
		}
	});
}


$.ajax({
	url : '/mts/system/lunbopic/list/json?web=&position=6&cityIds='+getQueryString("cityId"),
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
	if(userId==""){
		window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
		return;
	}
	window.location.href='/mts/appWeb/circle/circleSave.jsp?cityId='+getQueryString("cityId");
}


function zan(id){
	if(userId==""){
		window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
		return;
	}
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
}
function dou(){
	  event.stopPropagation();
}


var jubaoItemId="";
var reportedUserId="";

function report(obj,reportedUserIdV,jubaoItemIdV){
	if(userId==""){
		window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
		return;
	}
	$(".alert-box").css("top","0");
	$(obj).parents('.more_ul').toggle();
	$(obj).parents('.more_ul').siblings('.arr_up_down').toggle();
	
	reportedUserId=reportedUserIdV;
	jubaoItemId=jubaoItemIdV;
}

function jubao(){
	if(userId==""){
		window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
		return;
	}
	//加载页面方法
	$.ajax({
	url : '/mts/system/report/update/json?web=&type=2&operUserId='+userId+'&itemId='+jubaoItemId+'&reportedUserId='+reportedUserId+'&content='+$("#content").val(),
	type : "post",
	dataType : "json",
	success : function(result){
		if(result.status=="error"){
			
			window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
			return;
		}
		 window.location.reload();
	},
	error:function(XMLHttpRequest, textStatus, errorThrown){
			console.log(XMLHttpRequest) ;
			console.log(textStatus) ;
		}
	});
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
function showImg(obj){
	event.stopPropagation()
	console.log($(obj).index())
swiper.activeIndex=$(obj).index()
	$(".show-img-box .swiper-wrapper").empty()
	$(".show-img-box").toggle()
	$(obj).parent().children().each(function(){
		$(".show-img-box .swiper-wrapper").append("<div class=\"swiper-slide\"><img></div>").find("img:last").attr("src",this.src);
	})
}