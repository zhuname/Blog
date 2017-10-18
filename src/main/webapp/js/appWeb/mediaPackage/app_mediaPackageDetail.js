var This=this;
var nextPage=1;
var type=1;
var id;
var userId;
var itemUserId;
var packageUserId;
var encrypt;


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
		url : '/mts/system/mediapackage/look/json?web=&id='+getQueryString("id")+"&appUserId="+userId,
		type : "post",
		dataType : "json",
		success : function(result){
			
			if(result.status=="error"){
				window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
				return;
			}
			if(result.data!=undefined){
				if(result.data.appUser.name!=undefined){
					
					$('#name').html(result.data.appUser.name+"的红包");
					
				}
				
				if(result.data.appUser.header!=undefined){
					
					$('#header').attr('src',result.data.appUser.header);
					
				}
				
				if(result.data.encrypt!=undefined){
					
					encrypt=result.data.encrypt;
					
				}
					
					if(result.data.id!=undefined){
						
						packageUserId=result.data.id;
						
					}
				
					if(result.data.image!=undefined){
						
						var images=result.data.image.split(";");
						result.data.images=images;
						
					}
					
					if(result.data.isTop==undefined){
						
						result.data.isTop=0;
						
					}
					
					$('#detail_tmpl').tmpl(result.data).appendTo($('#detail'));
					
					
					id=result.data.id;
					
					
					//右上角
					itemUserId=result.data.userId;
					initColl();
					
					//初始化页面
					change(4);
					
					//获取预约列表
					$.ajax({
						url : '/mts/system/appoint/appointList/json?type=2&web=&itemId='+result.data.id,
						type : "post",
						dataType : "json",
						success : function(result){
							if(result.status=="error"){
								window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
								return;
							}
							if(result.data!=undefined){
									for (var int = 0; int < result.data.length; int++) {
										
										if(int<4){
										
											$('#yuyue_tmpl').tmpl(result.data[int]).appendTo($('#yuyue'));
											
										}
										
									}
							}else{
								$($('#yuyue')).parent().remove();
							}
						},
						error:function(XMLHttpRequest, textStatus, errorThrown){
							console.log(XMLHttpRequest) ;
							console.log(textStatus) ;
						}
					});
					
					
					if(result.data.cardId){
						//领取卡券列表
						$.ajax({
							url : '/mts/system/moneydetail/listuser/json?web=&itemId='+result.data.cardId,
							type : "post",
							dataType : "json",
							success : function(result){
								if(result.status=="error"){
									window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
									return;
								}
								if(result.data!=undefined){
									for (var int = 0; int < result.data.length; int++) {
										if(int<4){
											
											$('#lingquan_tmpl').tmpl(result.data[int]).appendTo($('#lingquan'));
											
										}
										
									}
								}
							},
							error:function(XMLHttpRequest, textStatus, errorThrown){
								console.log(XMLHttpRequest) ;
								console.log(textStatus) ;
							}
						});
					}
					
					
					//领取人列表
					$.ajax({
						url : '/mts/system/moneydetail/list/json?type=2&web=&itemId='+result.data.id,
						type : "post",
						dataType : "json",
						success : function(result){
							if(result.status=="error"){
								window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
								return;
							}
							if(result.data!=undefined){
									for (var int = 0; int < result.data.length; int++) {
										if(int<6){
										
											$('#lingqu_tmpl').tmpl(result.data[int]).appendTo($('#lingqu'));
											
										}
										
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
	},
	error:function(XMLHttpRequest, textStatus, errorThrown){
			console.log(XMLHttpRequest) ;
			console.log(textStatus) ;
		}
	});
	
	
	function change(changeType){
		$('#content').html("");
		nextPage = 1 ;
		type=changeType;
		show(type);
	}
	
	
	function show(type){
		if(type==3){
			$('#topNo').hide();
			$('#topChe').show();
			
			$('#contentNo').show();
			$('#contentChe').hide();
			
			$('#contentNum').removeAttr("class");
			$('#contentNum').attr("class","clr_3 f_26 ver_mid");
			
			$('#topNum').removeAttr("class");
			$('#topNum').attr("class","clr_r f_26 ver_mid");
			
			
		}else if(type==4){
			$('#topNo').show();
			$('#topChe').hide();
			
			$('#contentNo').hide();
			$('#contentChe').show();
			
			$('#contentNum').removeAttr("class");
			$('#contentNum').attr("class","clr_r f_26 ver_mid");
			
			$('#topNum').removeAttr("class");
			$('#topNum').attr("class","clr_3 f_26 ver_mid");
			
		}
		//评论/点赞列表
		$.ajax({
			url : '/mts/system/oper/list/json?type='+type+'&web=&itemId='+id+"&pageIndex="+nextPage,
			type : "post",
			dataType : "json",
			success : function(result){
				if(result.status=="error"){
					window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
					return;
				}
				if(result.data!=undefined){
						for (var int = 0; int < result.data.length; int++) {
							if(result.data[int].createTime){
								
								result.data[int].createTime=getDateDiff(result.data[int].createTime);
								
							}
							if(type==4){
								$('#content_tmpl').tmpl(result.data[int]).appendTo($('#content'));
							}else if(type==3){
								$('#top_tmpl').tmpl(result.data[int]).appendTo($('#content'));
							}
						}
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
		var halfamonth = day * 15;
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
			result="" + parseInt(monthC) + "月前";
		}else if(weekC>=1){
			result="" + parseInt(weekC) + "周前";
		}else if(dayC>=1){
			result=""+ parseInt(dayC) + "天前";
		}else if(hourC>=1){
			result=""+ parseInt(hourC) + "小时前";
		}else if(minC>=1){
			result=""+ parseInt(minC) + "分钟前";
		}else{
			result="刚刚";
		}
		return result;
	}

function yuyue(){
	
	
	
	if($("#money").val()!=null&&$("#money").val()!=""&&$("#phone").val()!=null&&$("#phone").val()!=""){
		
		//加载页面方法
		$.ajax({
		url : '/mts/system/appoint/update/json?web=&type=2&itemId='+getQueryString("id")+"&userId="+userId+"&packageUserId="+packageUserId+"&phone="+$("#phone").val()+"&money="+$("#money").val(),
		type : "post",
		dataType : "json",
		success : function(result){
			if(result.status=="error"){
				window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
				return;
			}
			window.location.href="/mts/appWeb/appuser/myAppoint.jsp";
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
				console.log(XMLHttpRequest) ;
				console.log(textStatus) ;
			}
		});
	
	}
}


function lingqu(){
		if(encrypt!=undefined&&encrypt==1&&$('#command').val()!=undefined&&$('#command').val()==""){
			$('.command_mask').show();
		}else{
			//加载页面方法
			$.ajax({
				url : '/mts/system/mediapackage/snatch/json?web=&id='+getQueryString("id")+"&userId="+userId,
				type : "post",
				dataType : "json",
				success : function(result){
					if(result.status=="error"){
						alert(result.message);
						return;
					}
					$('#moneyN').html(result.data.money);
					$('.bonus_mask').show();
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					console.log(XMLHttpRequest) ;
					console.log(textStatus) ;
				}
			});
			
		}
		
		
}

function lingquList(){
	window.location.href="/mts/appWeb/posterPackage/posterPackageUsersList.jsp?itemId="+id+"&type=1";
}

var toUserIdString="";

function toUser(toUserId,toUserName){
	
	toUserIdString="&toUserId="+toUserId;
	$("#comment").attr("placeholder","回复  "+toUserName+"：");
}
function oper(type){
	if(type==4){
		if($("#comment").val()!=null&&$("#comment").val()!=""){
			
			//加载页面方法
			$.ajax({
				url : '/mts/system/oper/update/json?web=&type=4&content='+$("#comment").val()+"&itemId="+id+"&userId="+userId+toUserIdString,
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
	}else if(type==3){
			
			//加载页面方法
			$.ajax({
				url : '/mts/system/oper/update/json?web=&type=3'+"&itemId="+id+"&userId="+userId,
				type : "post",
				dataType : "json",
				success : function(result){
					if(result.status=="error"){
						alert(result.message);;
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
}
	


function initColl(){
	if(userId!=itemUserId){
	//加载页面方法
	$.ajax({
	url : '/mts/system/attention/atten/json?web=&userId='+userId+'&itemId='+itemUserId,
	type : "post",
	dataType : "json",
	success : function(result){
		
		if(result.status=="error"){
			window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
			return;
		}
		if(result.data==0){
			$("#attr").html("关注");
		}else{
			$("#attr").html("已关注");
		}
	},
	error:function(XMLHttpRequest, textStatus, errorThrown){
			console.log(XMLHttpRequest) ;
			console.log(textStatus) ;
		}
	});
	//加载页面方法
	$.ajax({
	url : '/mts/system/collect/coll/json?web=&type=2&userId='+userId+'&itemId='+id,
	type : "post",
	dataType : "json",
	success : function(result){
		if(result.status=="error"){
			window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
			return;
		}
		if(result.data==0){
			$("#collect").html("收藏");
		}else{
			$("#collect").html("已收藏");
		}
	},
	error:function(XMLHttpRequest, textStatus, errorThrown){
			console.log(XMLHttpRequest) ;
			console.log(textStatus) ;
		}
	});
	}else{
		$($("#attr").parent()).remove();
		$($("#collect").parent()).remove();
	}
}

function collect(){
	//加载页面方法
	$.ajax({
	url : '/mts/system/collect/update/json?web=&type=2&userId='+userId+'&itemId='+id,
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

function attr(){
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
	
window.onscroll=function(){
	var a = document.documentElement.scrollTop==0? document.body.clientHeight : document.documentElement.clientHeight;
	var b = document.documentElement.scrollTop==0? document.body.scrollTop : document.documentElement.scrollTop;
	var c = document.documentElement.scrollTop==0? document.body.scrollHeight : document.documentElement.scrollHeight;
	if(a+b==c){
		nextPage=nextPage+1;
		show(type);
	}
}

function getQueryString(aaa) { 
	var reg = new RegExp("(^|&)" + aaa + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
} 
