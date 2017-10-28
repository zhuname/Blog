var This=this;
var userId;
var auserId;
var id;
var type;
var nextPage=1;
var cityId;
var itemId;
var dataString="";
var userData="";
var status;

function select(){
	var titleString= $('#title').val();
	nextPage=1;
	$('#userList').html("");
	dataString='&name='+titleString;
	joinList(getQueryString("type"));
}

function selectSort(type){
	nextPage=1;
	$('#userList').html("");
	if(type==1){
		dataString='&sort=1';
	}else if(type==2){
		dataString='&sort=2';
	}else if(type==3){
		dataString='&sort=3';
	}
	joinList(getQueryString("type"));
}

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
				
				id=result.data.id;
				
				auserId=result.data.userId;
				
				status=result.data.status;
				
				joinList(getQueryString("type"));
				
				$('#canyu_tmpl').tmpl(result.data).appendTo($('#foot'));
				
				if(result.data.awardss!=undefined){
					for (var int = 0; int < result.data.awardss.length; int++) {
						$('#jiang_tmpl').tmpl(result.data.awardss[int]).appendTo($('#jiang'));
					}
				}
			}
			
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			console.log(XMLHttpRequest) ;
			console.log(textStatus) ;
		}
	});
	},
error:function(XMLHttpRequest, textStatus, errorThrown){
console.log(XMLHttpRequest);
console.log(textStatus);
}
});

function canyu(){
	window.location.href='/mts/appWeb/circle/circleSave.jsp?activityId='+id;
}

function joinList(joinOrAward){
	var data='&pageIndex='+nextPage;
	$.ajax({
		url : '/mts/system/joinactivity/list/json?web=&appuserId='+userId+'&activityId='+getQueryString("id")+"&joinOrAward="+joinOrAward+data+userData+userId+dataString,
		type : "post",
		dataType : "json",
		success : function(result){
			
			if(result.status=="error"){
				window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
				return;
			}
			
			if(result.data!=undefined){
				
				
				for (var int = 0; int < result.data.length; int++) {
					
					if(result.data[int].image!=undefined){
						
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
					
					}
					
					if(result.data[int].opers!=undefined){
						
						for (var int2 = 0; int2 < result.data[int].opers.length; int2++) {
							
							result.data[int].opers[int2].createTime=getDateDiff(result.data[int].opers[int2].createTime);
							
						}
					
					}
					
					if(result.data[int].createTime!=undefined){
						
						result.data[int].createTime = getDateDiff(result.data[int].createTime);
						
					}
					
					
					$('#user_list_tmpl').tmpl(result.data[int]).appendTo($('#userList'));
					
				}
				
				showPinglun();
				
				var bj=$(".bj");
				for (var int = 0; int < bj.length; int++) {
					if(auserId!=userId){
						$(bj[int]).html("");
						$(bj[int]).attr("style","width:3.5rem;");
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

//隐藏三个以上的评论
function showPinglun(){
	
	var operList = $(".pinglun");
	
	for (var int = 0; int < operList.length; int++) {
		
		var childrens=$(operList[int]).children();
		var isShow=0;
		
			
			for (var int2 = 0; int2 < childrens.length; int2++) {
				if(int2!=childrens.length-1){
				
				if(int2>2){
					$(childrens[int2]).attr("style","display:none;");
					$(childrens[int2]).attr("isAll","1");
					isShow=1;
				}
				
				}else{
					
					if(isShow==1){
						
						$(childrens[int2]).removeAttr("style");
					}
				}
			}
		
		
		
	}
	
}

function attr(itemUserId){
	if(userId==""){
		window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
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


	
	
	function hideInput(){
		
		$("#inputBtn").hide();
		$("#srk_box").hide();
		$("#content").val("");
		
	}
	
	function oper(id){
		
		if(status==4){
			alert("活动已结束");
			return;
		}
		
		//加载页面方法
		$.ajax({
			url : '/mts/system/oper/update/json?web=&type=6'+"&itemId="+id+"&userId="+userId,
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
	
	function showInput(id){
		if(status==4){
			alert("活动已结束");
			return;
		}
		
		if(userId==""){
			window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
			return;
		}
		
		
		$("#inputBtn").show();
		$("#srk_box").show();
		itemId = id ;
		
	}

	var toUserIdString="";

	function toUser(id,toUserId,toUserName){
		if(status==4){
			alert("活动已结束");
			return;
		}
		if(userId==""){
			window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
		}
		
		showInput(id);
		
		toUserIdString="&toUserId="+toUserId;
		$("#content").attr("placeholder","回复  "+toUserName+"：");
	}
	
	function fasong(){
		console.log($("#content").val());
		//加载页面方法
		$.ajax({
			url : '/mts/system/oper/update/json?web=&type=5'+"&itemId="+itemId+"&userId="+userId+"&content="+$("#content").val()+toUserIdString,
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

function all(id){
	if($("#"+id).attr("all")==0){
		var opers  =  $("#"+id).prevAll();
		
		for (var int = 0; int < opers.length; int++) {
			
			if($(opers[int]).attr("isAll")=="1"){
				
				$(opers[int]).attr("style","");
				
			}
			
		}
		$("#"+id).attr("all","1");
		$("#"+id).html("收回");
	}else {
		var opers  = $("#"+id).prevAll();
		
		for (var int = 0; int < opers.length; int++) {
			
			if($(opers[int]).attr("isAll")=="1"){
				
				$(opers[int]).attr("style","display:none;");
				
			}
			
		}
		$("#"+id).attr("all","0");
		$("#"+id).html("展开");
	}
	
	
}
var joinUserIds;
function alertPrice(joinUserId){
	$(".alert-box").show();
	joinUserIds=joinUserId;
}

function banjiang(awardId){
	//加载页面方法
	$.ajax({
	url : '/mts/system/giveaward/update/json?web=&userId='+userId+'&joinUserId='+joinUserIds+'&awardId='+awardId,
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
		joinList(getQueryString("type"));
	}
}






var jubaoItemId="";
var reportedUserId="";

function report(obj,reportedUserIdV,jubaoItemIdV){
	
	$(".alert-box").css("top","0");
	$(obj).parents('.more_ul').toggle();
	$(obj).parents('.more_ul').siblings('.arr_up_down').toggle();
	$(".whte").toggle()
	reportedUserId=reportedUserIdV;
	jubaoItemId=jubaoItemIdV;
}

function jubao(){
	//加载页面方法
	$.ajax({
	url : '/mts/system/report/update/json?web=&type=2&operUserId='+userId+'&itemId='+jubaoItemId+'&reportedUserId='+reportedUserId+'&content='+$("#contents").val(),
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
function showImg(obj){
	swiper.activeIndex=$(obj).index()
		$(".show-img-box .swiper-wrapper").empty()
		$(".show-img-box").toggle()
		$(obj).parent().children().each(function(){
			$(".show-img-box .swiper-wrapper").append("<div class=\"swiper-slide\"><img></div>").find("img:last").attr("src",this.src);
		})

}
function alertMneu(obj){
	$(obj).siblings('.more_ul').toggle();
	$(obj).siblings('.arr_up_down').toggle();
	$(".whte").toggle();
}