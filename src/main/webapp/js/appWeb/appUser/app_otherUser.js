var This=this;
var nextPage=1;
var dataString="";
var changeType=1;
var userId;
var appUserId="";
var isChange=0;
var isChangess=0;
//初始化页面
var data='&pageIndex='+nextPage;
show();

//加载页面方法
function show(){
	$.ajax({
		url : '/mts/system/appuser/look/json?web=',
		type : "post",
		dataType : "json",
		success : function(result){
			if(result.status=="error"){
				
				return;
			}
			if(result.data!=undefined){
				appUserId=result.data.id;
			}
			isChange=0;
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			console.log(XMLHttpRequest) ;
			console.log(textStatus) ;
		}
	});
	isChangess=1;
	
	if(isChange==0){
		isChange=1;
	data='&pageIndex='+nextPage;
	//加载页面方法
	$.ajax({
	url : '/mts/system/appuser/look/json?web=&id='+getQueryString("id"),
	type : "post",
	dataType : "json",
	success : function(result){
		if(result.status=="error"){
			window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
			return;
		}
		
		$('#appuser_detail_tmpl').tmpl(result.data).appendTo($('#detail'));
		
		
		$.ajax({
			url : '/mts/system/appuser/publish/json?web=&id='+getQueryString("id"),
			type : "post",
			dataType : "json",
			success : function(result){
				if(result.status=="error"){
					window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
					return;
				}
				if(result.data!=undefined){
					$('#cityCircleCount').html(result.data.cityCircleCount);
					$('#activityCount').html(result.data.activityCount);
					$('#mediaCount').html(result.data.mediaCount);
					$('#posterCount').html(result.data.posterCount);
				}
				isChange=0;
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				console.log(XMLHttpRequest) ;
				console.log(textStatus) ;
			}
		});
		
		
		if(result.data.userMedals!=undefined){
			for (var int = 0; int < result.data.userMedals.length; int++) {
				$('#appuser_xunzhang_tmpl').tmpl(result.data.userMedals[int]).appendTo($('#xzmb'));
				
			}
		}
				userId=result.data.id;
				if(appUserId!=undefined){
					$.ajax({
						url : '/mts/system/circle/list/json?web='+"&userId="+userId+data+"&appuserId="+appUserId,
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
										var imagess=new Array();
										for (var int2 = 0; int2 < images.length; int2++) {
											if(images[int2]!=""){
												imagess[int2]=images[int2];
											}
										}
										result.data[int].images=imagess;
										
									}
									
									if(result.data[int].createTime){
										result.data[int].m=result.data[int].createTime.substring(5,7);
										result.data[int].d=result.data[int].createTime.substring(8,10);
										result.data[int].t=result.data[int].createTime.substring(11,16);
										
									}
									
									$('#circle_list_tmpl').tmpl(result.data[int]).appendTo($('#list'));
									if(int==result.data.length-1){
										isChangess=0;
									}
								}
							}
							
						},
						
						error:function(XMLHttpRequest, textStatus, errorThrown){
							
							console.log(XMLHttpRequest);
							
							console.log(textStatus);
							
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




function change(type,isClear,li){
	console.log(isChangess);
	if(isChangess==0){
		isChangess=1;
	if(isClear==2){
		
		changeType=type;
		
		$("#list").html("");
		$("#posterPackage").html("");
		
		
		switch (isClear) {
		case 2:
			nextPage = 1 ;
			//初始化状态
			var change=$('.change');
			for (var int = 0; int < change.length; int++) {
				$(change[int]).removeAttr("class");
				$(change[int]).attr("class","change dis_f ali_ct flex_col jus_ct");
				
				switch (int) {
				case 0:
					$($($($(change[int]).children())[0])).attr("src",$("#imgUrl").html()+"h1.png");
					break;
				case 1:
					$($($($(change[int]).children())[0])).attr("src",$("#imgUrl").html()+"h2.png");
					break;
				case 2:
					$($($($(change[int]).children())[0])).attr("src",$("#imgUrl").html()+"h3.png");
					break;
				case 3:
					$($($($(change[int]).children())[0])).attr("src",$("#imgUrl").html()+"h4.png");
					break;

				}
				
			}
			
			switch (type) {
			case 1:
				$($($(li).children())[0]).attr("src",$("#imgUrl").html()+"h1h.png");
				break;
			case 2:
				$($($(li).children())[0]).attr("src",$("#imgUrl").html()+"h2h.png");
				break;
			case 3:
				$($($(li).children())[0]).attr("src",$("#imgUrl").html()+"h3h.png");
				break;
			case 4:
				$($($(li).children())[0]).attr("src",$("#imgUrl").html()+"h4h.png");
				break;

			}
			
			//初始化状态
			var lir=$('.lir');
			for (var int = 0; int < lir.length; int++) {
				$(lir[int]).removeAttr("class");
				$(lir[int]).attr("class","lir");
			}
			$(li).attr("class","change dis_f ali_ct flex_col jus_ct zy_cur ");
			$($($(li).children())[1]).attr("class","lir clr_r");
			
			break;
		}
		
	}
	
	
	switch (type) {
	case 1:
		
		$.ajax({
			url : '/mts/system/circle/list/json?web='+"&userId="+userId+'&pageIndex='+nextPage+"&appuserId="+appUserId,
			type : "post",
			dataType : "json",
			async: false,
			success : function(result){
				
				if(result.status=="error"){
					window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
					return;
				}
				
				if(result.data!=undefined){
					for (var int = 0; int < result.data.length; int++){
						
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
						
						if(result.data[int].createTime){
							result.data[int].m=result.data[int].createTime.substring(5,7);
							result.data[int].d=result.data[int].createTime.substring(8,10);
							result.data[int].t=result.data[int].createTime.substring(11,16);
							
						}
						
						$('#circle_list_tmpl').tmpl(result.data[int]).appendTo($('#list'));
						if(int==result.data.length-1){
							isChangess=0;
						}
					}
				}
				
			},
			
			error:function(XMLHttpRequest, textStatus, errorThrown){
				
				console.log(XMLHttpRequest);
				
				console.log(textStatus);
				
			}
		});
		
		break;
	case 2:
		$.ajax({
			url : '/mts/system/posterpackage/list/json?web=&type=2&personType=2&userId='+userId+'&pageIndex='+nextPage,
			type : "post",
			dataType : "json",
			async: false,
			success : function(result){
				
				if(result.status=="error"){
					window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
					return;
				}
				if(result.data!=undefined){
					for (var int = 0; int < result.data.length; int++) {
						
						result.data[int].image=result.data[int].image.split(";")[0];
						
						$('#posterPackage_list_tmpl').tmpl(result.data[int]).appendTo($('#posterPackage'));
						if(int==result.data.length-1){
							isChangess=0;
						}
					}
				}
				
			},
			
			error:function(XMLHttpRequest, textStatus, errorThrown){
				
				console.log(XMLHttpRequest);
				
				console.log(textStatus);
				
			}
		});
		
		break;
	case 3:
		$.ajax({
			url : '/mts/system/mediapackage/list/json?web=&status=3&personType=2&userId='+userId+'&pageIndex='+nextPage,
			type : "post",
			dataType : "json",
			async: false,
			success : function(result){
				if(result.status=="error"){
					window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
					return;
				}
				if(result.data!=undefined){
					for (var int = 0; int < result.data.length; int++) {
						
						$('#media_list_tmpl').tmpl(result.data[int]).appendTo($('#list'));
						
						if(int==result.data.length-1){
							isChangess=0;
						}
						}
				}
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				console.log(XMLHttpRequest) ;
				console.log(textStatus) ;
			}
		});
		break;
	case 4:
		$.ajax({
			url : '/mts/system/activity/appList/json?web='+'&appuserId='+appUserId+'&userId='+userId+'&pageIndex='+nextPage,
			type : "post",
			async: false,
			dataType : "json",
			success : function(result){
				
				if(result.status=="error"){
					
					window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
					
					return;
					
				}
				
				if(result.data!=undefined){
					
					for (var int = 0; int < result.data.length; int++){
						
						if(result.data[int].endTime){
							
							result.data[int].endTime=result.data[int].endTime.substring(0,10);
							
						}
						
						if(result.data[int].image!=undefined){
							var images=result.data[int].image.split(";");
							
							result.data[int].image=images[0];
							
						}
						
						
						
						$('#activity_list_tmpl').tmpl(result.data[int]).appendTo($('#list'));
						if(int==result.data.length-1){
							isChangess=0;
						}
					}
					
					
				}
				
			},
			
			error:function(XMLHttpRequest, textStatus, errorThrown){
				
				console.log(XMLHttpRequest);
				
				console.log(textStatus);
				
			}
		});
		break;
	}
	isChangess=0;
	}
	
}

function zan(id){
	//加载页面方法
	$.ajax({
		url : '/mts/system/oper/update/json?web=&type=7'+"&itemId="+id+"&userId="+appUserId,
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
function locationHrefa(id){
	if(dianji==1){
		dianji=0;
		return;
	}
	window.location.href='/mts/appWeb/circle/circleDetail.jsp?id='+id;
}
function locationHref(id){
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
		change(changeType,1);
	}
}

function getQueryString(aaa) { 
	var reg = new RegExp("(^|&)" + aaa + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
} 
