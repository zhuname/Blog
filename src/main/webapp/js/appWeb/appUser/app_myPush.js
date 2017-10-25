var This=this;
var nextPage=1;
var dataString="";
var statusType=1;
var changeType=1;
var userId;
var isChange=0;
//初始化页面
show();

//加载页面方法
function show(){
	if(isChange==0){
		isChange=1;
	var data='&pageIndex='+nextPage;
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
						url : '/mts/system/posterpackage/list/json?web=&type=3&userId='+userId+"&status="+statusType+data,
						type : "post",
						dataType : "json",
						success : function(result){
							if(result.status=="error"){
								window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
								return;
							}
							if(result.data!=undefined){
								for (var int = 0; int < result.data.length; int++) {
									
									result.data[int].image=result.data[int].image.split(";")[0];
									
									$('#posterPackage_list_tmpl').tmpl(result.data[int]).appendTo($('#posterPackage'));
									
								}
							}
							isChange=0;
						},
						error:function(XMLHttpRequest, textStatus, errorThrown){
							console.log(XMLHttpRequest) ;
							console.log(textStatus) ;
						}
					});
					},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				console.log(XMLHttpRequest) ;
				console.log(textStatus) ;
			}
		});
	}
}




function change(type,isClear,li){
	if(isChange==0){
	isChange=1;
	switch (isClear) {
	case 1:
		$("#posterPackage").html("");
		$("#mediaPackage").html("");
		$("#card").html("");
		$("#activity").html("");
		
		
		nextPage = 1 ;
		break;
	case 2:
		$("#posterPackage").html("");
		$("#mediaPackage").html("");
		$("#card").html("");
		$("#activity").html("");
		
		statusType=1;
		nextPage = 1 ;
		
		//初始化状态
		var statuss=$('.status');
		for (var int = 0; int < statuss.length; int++) {
			$(statuss[int]).removeAttr("class");
			$(statuss[int]).attr("class","shenhe_norm padl_20 status");
		}
		$("#initStatus").attr("class","shenhe_cur padl_20 status");
		
		var changeTypes=$('.changeType');
		for (var int = 0; int < changeTypes.length; int++) {
			$(changeTypes[int]).removeAttr("class");
			$(changeTypes[int]).attr("class","clr_r changeType");
			$($(changeTypes[int]).parent()).attr("class"," ");
		}
		$(li).attr("class","clr_f changeType");
		$($(li).parent()).attr("class","fb_cur");
		break;
	}
	
	switch (type) {
	case 1:
		$.ajax({
			url : '/mts/system/posterpackage/list/json?web=&type=3&userId='+userId+"&status="+statusType+'&pageIndex='+nextPage,
			type : "post",
			dataType : "json",
			success : function(result){
				if(result.status=="error"){
					window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
					return;
				}
				if(result.data!=undefined){
					for (var int = 0; int < result.data.length; int++) {
						result.data[int].image=result.data[int].image.split(";")[0];
						
						if(statusType==2){
							$('#posterPackage_list2_tmpl').tmpl(result.data[int]).appendTo($('#posterPackage'));
						}else if(statusType==4){
							$('#posterPackage_list4_tmpl').tmpl(result.data[int]).appendTo($('#posterPackage'));
						}else{
							$('#posterPackage_list_tmpl').tmpl(result.data[int]).appendTo($('#posterPackage'));
						}
					}
				}
				isChange=0;
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				console.log(XMLHttpRequest) ;
				console.log(textStatus) ;
			}
		});
		changeType=type;
		break;
	case 2:
		$.ajax({
			url : '/mts/system/mediapackage/list/json?web=&personType=3&userId='+userId+"&status="+statusType+'&pageIndex='+nextPage,
			type : "post",
			dataType : "json",
			success : function(result){
				if(result.status=="error"){
					window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
					return;
				}
				if(result.data!=undefined){
					for (var int = 0; int < result.data.length; int++) {
						if(statusType==2){
							$('#media_list2_tmpl').tmpl(result.data[int]).appendTo($('#mediaPackage'));
						}else if(statusType==4){
							$('#media_list4_tmpl').tmpl(result.data[int]).appendTo($('#mediaPackage'));
						}else{
							$('#media_list_tmpl').tmpl(result.data[int]).appendTo($('#mediaPackage'));
						}
						
					}
				}
				isChange=0;
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				console.log(XMLHttpRequest) ;
				console.log(textStatus) ;
			}
		});
		
		
		
		changeType=type;
		break;
		
		
	case 3:
		
		var cardStatus;
		switch (statusType) {
		case 1:
			cardStatus = 1 ;
			break;
		case 2:
			cardStatus = 3 ;
			break;
		case 3:
			cardStatus = 2 ;
			break;
		case 4:
			cardStatus = 4 ;
			break;
		}
		
		$.ajax({
			url : '/mts/system/card/list/json?web=&userId='+userId+"&status="+cardStatus+'&pageIndex='+nextPage,
			type : "post",
			dataType : "json",
			success : function(result){
				if(result.status=="error"){
					window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
					return;
				}
				
				if(result.data!=undefined){
					for (var int = 0; int < result.data.length; int++) {
						if(result.data[int].endTime!=undefined){
							//获取消息记录
							
							result.data[int].endTime=result.data[int].endTime.substring(0,10);
							
						}
						if(cardStatus==3){
							$('#card_list3_tmpl').tmpl(result.data[int]).appendTo($('#card'));
						}else if(cardStatus==4){
							$('#card_list4_tmpl').tmpl(result.data[int]).appendTo($('#card'));
						}else{
							$('#card_list_tmpl').tmpl(result.data[int]).appendTo($('#card'));
						}
						
					}
				}
				isChange=0;
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				console.log(XMLHttpRequest) ;
				console.log(textStatus) ;
			}
		});
		
		
		
		changeType=type;
		break;
		
		case 4:
		
		
		$.ajax({
			url : '/mts/system/activity/appList/json?web=&userId='+userId+"&status="+statusType+'&pageIndex='+nextPage,
			type : "post",
			dataType : "json",
			success : function(result){
				if(result.status=="error"){
					window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
					return;
				}
				if(result.data!=undefined){
					for (var int = 0; int < result.data.length; int++) {
						result.data[int].image=result.data[int].image.split(";")[0];
						if(result.data[int].endTime){
							
							result.data[int].endTime=result.data[int].endTime.substring(0,10);
							
						}
						
						if(statusType==2){
							$('#activity_list2_tmpl').tmpl(result.data[int]).appendTo($('#activity'));
						}else if(statusType==4){
							$('#activity_list4_tmpl').tmpl(result.data[int]).appendTo($('#activity'));
						}else{
							$('#activity_list_tmpl').tmpl(result.data[int]).appendTo($('#activity'));
						}
						
					}
					
				}
				isChange=0;
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				console.log(XMLHttpRequest) ;
				console.log(textStatus) ;
			}
		});
		
		
		
		changeType=type;
		break;
		
	default:
		break;
	}
	}
	
}

function changeStatus(status,li){
	if(isChange==0){
		var statuss=$('.status');
		for (var int = 0; int < statuss.length; int++) {
			$(statuss[int]).removeAttr("class");
			$(statuss[int]).attr("class","shenhe_norm padl_20 status");
		}
		$(li).attr("class","shenhe_cur padl_20 status");
		statusType = status;
		nextPage = 1 ;
		change(changeType,1);
	}
}

window.onscroll=function(){
	var a = document.documentElement.scrollTop==0? document.body.clientHeight : document.documentElement.clientHeight;
	var b = document.documentElement.scrollTop==0? document.body.scrollTop : document.documentElement.scrollTop;
	var c = document.documentElement.scrollTop==0? document.body.scrollHeight : document.documentElement.scrollHeight;
	if(a+b==c){
		nextPage=nextPage+1;
		change(changeType);
	}
}

function getQueryString(aaa) { 
	var reg = new RegExp("(^|&)" + aaa + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
} 
