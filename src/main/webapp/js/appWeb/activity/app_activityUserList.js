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
			userId=undefined;
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
				
				joinList(getQueryString("type"));
				
				$('#canyu_tmpl').tmpl(result.data).appendTo($('#foot'));
				
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
		url : '/mts/system/joinactivity/list/json?web=&activityId='+getQueryString("id")+"&joinOrAward="+joinOrAward+data+userData+userId+dataString,
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
							
							result.data[int].images=images;
							
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

	function oper(id){
			
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
		
		$("#inputBtn").show();
		$("#srk_box").show();
		itemId = id ;
		
	}
	
	
	function hideInput(){
		
		$("#inputBtn").hide();
		$("#srk_box").hide();
		$("#content").val("");
		
	}
	
	
	function fasong(){
		console.log($("#content").val());
		//加载页面方法
		$.ajax({
			url : '/mts/system/oper/update/json?web=&type=5'+"&itemId="+itemId+"&userId="+userId+"&content="+$("#content").val(),
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
function alertPrice(){
	console.log(4654);
	$(".alert-box").show()
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

function getQueryString(aaa) { 
	var reg = new RegExp("(^|&)" + aaa + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
} 
