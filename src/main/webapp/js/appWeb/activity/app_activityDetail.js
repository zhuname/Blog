var This=this;
var userId;
var id;
var type;
var cityId;
var itemUserId;
var userData="";

$.ajax({
	url : '/mts/system/appuser/look/json?web=',
	type : "post",
	dataType : "json",
	success : function(result){
		
		
		if(result.status=="error"){
			userId="";
		}else{
			userId=result.data.id;
			userData="&appUserId="+userId;
		}
	
		//获取同城活动详情
	$.ajax({
		url : '/mts/system/activity/look/json?web=&id='+getQueryString("id")+userData,
		type : "post",
		dataType : "json",
		success : function(result){
			
			if(result.status=="error"){
				window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
				return;
			}
			
			if(result.data!=undefined){
				//获取消息记录
				
				if(result.data.endTime){
					
					result.data.endTime=result.data.endTime.substring(0,10);
					
				}
				
				id=result.data.id;
				
				//右上角
				itemUserId=result.data.userId;
				initColl();
				
				var image=null;
				
				
				$('#detail_tmpl').tmpl(result.data).appendTo($('#detail'));
				
				if(result.data.type==1){
					
					$("#bann").show();
					
					
					if(result.data.image!=undefined){
						
					var lunbo=result.data.image.split(";");
					
					for (var int = 0; int < lunbo.length; int++) {
						
						if(lunbo[int]!=""){
							$('#lunbo_list_tmpl').tmpl({'image':lunbo[int]}).appendTo($('#lunbo'));
							
						}
						
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
				}else{
					$("#videos").show();
					$("#videos").attr("src",result.data.mediaUrl);
					$("#videos").attr("poster",result.data.mediaImage);
				}
				
				
				
				if(userId==itemUserId){
					$("#guanzhu").remove();
					$("#pingbi").remove();
				}
				
				for (var int2 = 0; int2 < result.data.awardss.length; int2++) {
					$('#huodong_tmpl').tmpl(result.data.awardss[int2]).appendTo($('#huodongShow'));
				}
				
				change(1);
				
				//获取同城活动参与人
				
				join(1,8);
				join(2,5);
				
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

function canyu(){
	if(userId==""){
		window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
		return;
	}
	window.location.href='/mts/appWeb/circle/circleSave.jsp?';
}

function change(changeType){
	if(changeType==1){
		$('#huodongShow').show();
		$('#canyuShow').hide();
		$('#canyuBtn').attr("style","background: #c8c8cc;width:8rem;");
		$('#huodongBtn').attr("style","background: #539df2;width:8rem;");
	}else if(changeType==2){
		$('#huodongShow').hide();
		$('#canyuShow').show();
		$('#huodongBtn').attr("style","background: #c8c8cc;width:8rem;");
		$('#canyuBtn').attr("style","background: #539df2;width:8rem;");
	}
}

function initColl(){
	if(userId!=undefined&&userId!=null){
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
		url : '/mts/system/collect/coll/json?web=&type=4&userId='+userId+'&itemId='+id,
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
}

function collect(){
	if(userId==""){
		window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
		return;
	}
	//加载页面方法
	$.ajax({
	url : '/mts/system/collect/update/json?web=&type=4&userId='+userId+'&itemId='+id,
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


function join(joinOrAward,pageSize){
	$.ajax({
		url : '/mts/system/joinactivity/list/json?web=&activityId='+getQueryString("id")+"&sort=1&pageSize="+pageSize+"&joinOrAward="+joinOrAward,
		type : "post",
		dataType : "json",
		success : function(result){
			if(result.status=="error"){
				window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
				return;
			}
			
			if(result.data!=undefined){
				
				if(joinOrAward==1){
					//获取消息记录
					for (var int = 0; int < result.data.length; int++) {
						$('#canyu_tmpl').tmpl(result.data[int]).appendTo($('#canyu'));
					}
				}else if(joinOrAward==2){
					//获取消息记录
					for (var int = 0; int < result.data.length; int++) {
							$('#zhongjiang_tmpl').tmpl(result.data[int]).appendTo($('#zhongjiang'));
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


var jubaoItemId="";
var reportedUserId="";

function report(obj){
	if(userId==""){
		window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
		return;
	}
	$(".alert-box").css("top","0");
	$(obj).parents('.more_ul').toggle();
	$(obj).parents('.more_ul').siblings('.arr_up_down').toggle();
	
	reportedUserId=itemUserId;
	jubaoItemId=id;
}

function jubao(){
	debugger;
	//加载页面方法
	$.ajax({
	url : '/mts/system/report/update/json?web=&type=1&operUserId='+userId+'&itemId='+jubaoItemId+'&reportedUserId='+reportedUserId+'&content='+$("#content").val(),
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

function getQueryString(aaa) { 
	var reg = new RegExp("(^|&)" + aaa + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
} 
