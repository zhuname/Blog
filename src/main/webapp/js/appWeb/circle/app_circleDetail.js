var This=this;
var convertMoney;
var num;
var limitNumber;
var userId;
var nextPage=1;
var id;

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
		var user= result.data;
	$.ajax({
		url : '/mts/system/circle/look/json?web=&id='+getQueryString("id")+"&appuserId="+userId,
		type : "post",
		dataType : "json",
		success : function(result){
			
			if(result.status=="error"){
				window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
				return;
			}
			
			if(result.data!=undefined){
				//获取消息记录
				
				
				if(result.data.createTime){
					
					result.data.createTime=getDateDiff(result.data.createTime);
					
				}
				
				
				$('#detail_tmpl').tmpl(result.data).appendTo($('#detail'));
				
				
				if(result.data.image){
					
					result.data.image=result.data.image.split(";");
					
				}
				if(result.data.image!=undefined){
					for (var int = 0; int < result.data.image.length; int++) {
						$('#detail_image_tmpl').tmpl({'image':result.data.image[int]}).appendTo($('#images'));
					}
				}
				
				id=result.data.id;
				
				$('#foot_tmpl').tmpl(result.data).appendTo($('#detail'));
				
				change(1);
				
				
				
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
	
	var url="";
	
	if(type==1){
		$("#dz").removeAttr('style');
		$("#xx").removeAttr('style');
		$("#ds").attr('style',"border-bottom:1px solid #f95d47;");
		
		$("#dsImg").attr("src",$("#srcUrl").html()+"/js/appWeb/images/dsh.png");
		$("#xxImg").attr("src",$("#srcUrl").html()+"/js/appWeb/images/xx.png");
		$("#dzImg").attr("src",$("#srcUrl").html()+"/js/appWeb/images/zan2.png");
		
		$("#dsSpan").attr("class","f_20 clr_r ver_mid");
		$("#xxSpan").attr("class","f_20 clr_6 ver_mid");
		$("#dzSpan").attr("class","f_20 clr_6 ver_mid");
		
		url='/mts/system/citycircle/list/json?web=&itemId='+id+"&pageIndex="+nextPage;
	}else if(type==7){
		$("#ds").removeAttr('style');
		$("#xx").removeAttr('style');
		$("#dz").attr('style',"border-bottom:1px solid #f95d47;");
		
		$("#dsImg").attr("src",$("#srcUrl").html()+"/js/appWeb/images/ds.png");
		$("#xxImg").attr("src",$("#srcUrl").html()+"/js/appWeb/images/xx.png");
		$("#dzImg").attr("src",$("#srcUrl").html()+"/js/appWeb/images/zan3.png");
		
		$("#dsSpan").attr("class","f_20 clr_6 ver_mid");
		$("#xxSpan").attr("class","f_20 clr_6 ver_mid");
		$("#dzSpan").attr("class","f_20 clr_r ver_mid");
		
		url='/mts/system/oper/list/json?type='+type+'&web=&itemId='+id+"&pageIndex="+nextPage;
	}else if(type==8){
		$("#ds").removeAttr('style');
		$("#dz").removeAttr('style');
		$("#xx").attr('style',"border-bottom:1px solid #f95d47;");
		
		$("#dsImg").attr("src",$("#srcUrl").html()+"/js/appWeb/images/ds.png");
		$("#xxImg").attr("src",$("#srcUrl").html()+"/js/appWeb/images/xxh.png");
		$("#dzImg").attr("src",$("#srcUrl").html()+"/js/appWeb/images/zan2.png");
		
		$("#dsSpan").attr("class","f_20 clr_6 ver_mid");
		$("#xxSpan").attr("class","f_20 clr_r ver_mid");
		$("#dzSpan").attr("class","f_20 clr_6 ver_mid");
		
		url='/mts/system/oper/list/json?type='+type+'&web=&itemId='+id+"&pageIndex="+nextPage;
	}
	//评论/点赞列表
	$.ajax({
		url : url,
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
						if(type==8){
							$('#content_tmpl').tmpl(result.data[int]).appendTo($('#content'));
						}else if(type==7){
							$('#top_tmpl').tmpl(result.data[int]).appendTo($('#content'));
						}else if(type==1){
							$('#shang_list_tmpl').tmpl(result.data[int]).appendTo($('#content'));
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



function dianzan(){
	
	if(userId!=null&&userId!=""&&id!=null&&id!=""){
		$('#dianzanshow').html("");
		$('#zan_image_tmpl').tmpl(null).appendTo($('#dianzanshow'));
		//加载页面方法
		$.ajax({
		url : '/mts/system/oper/update/json?web=&type=7&itemId='+getQueryString("id")+"&userId="+userId,
		type : "post",
		dataType : "json",
		success : function(result){
			if(result.status=="error"){
				window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
				return;
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
				console.log(XMLHttpRequest) ;
				console.log(textStatus) ;
			}
		});
	
	}
}

function dashang(){
	
	var content = $('#content').val();
	var money = $('#money').val();
	
	if(userId!=null&&userId!=""&&id!=null&&id!=""&&content!=null&&content!=""&&money!=null&&money!=""){
		//加载页面方法
		$.ajax({
		url : '/mts/system/citycircle/update/json?web=&itemId='+getQueryString("id")+"&userId="+userId+"&content="+content+"&money="+money,
		type : "post",
		dataType : "json",
		success : function(result){
			if(result.status=="error"){
				window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
				return;
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
				console.log(XMLHttpRequest) ;
				console.log(textStatus) ;
			}
		});
	
	}else{
		alert("请完善打赏信息");
	}
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
