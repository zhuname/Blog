var This=this;
var nextPage=1;
var type=1;
var id;


//加载页面方法

	$.ajax({
		url : '/mts/system/posterpackage/look/json?web=&id='+getQueryString("id"),
		type : "post",
		dataType : "json",
		success : function(result){
			if(result.status=="error"){
				window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
				return;
			}
			if(result.data!=undefined){
				
					if(result.data.image!=undefined){
						
						var images=result.data.image.split(";");
						result.data.images=images;
						
					}
					
					if(result.data.isTop==undefined){
						
						result.data.isTop=0;
						
					}
					
					
					$('#detail_tmpl').tmpl(result.data).appendTo($('#detail'));
					
					//初始化页面
					change(2);
					
					id=result.data.id;
					//获取预约列表
					$.ajax({
						url : '/mts/system/appoint/appointList/json?type=1&web=&itemId='+result.data.id,
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
						url : '/mts/system/moneydetail/list/json?type=1&web=&itemId='+result.data.id,
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
	
	
	function change(changeType){
		$('#content').html("");
		nextPage = 1 ;
		type=changeType;
		show(type);
	}
	
	
	function show(type){
		if(type==1){
			$('#topNo').hide();
			$('#topChe').show();
			
			$('#contentNo').show();
			$('#contentChe').hide();
			
			$('#contentNum').removeAttr("class");
			$('#contentNum').attr("class","clr_3 f_26 ver_mid");
			
			$('#topNum').removeAttr("class");
			$('#topNum').attr("class","clr_r f_26 ver_mid");
			
			
		}else if(type==2){
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
			url : '/mts/system/oper/list/json?type='+type+'&web=&itemId='+id,
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
							if(type==2){
								$('#content_tmpl').tmpl(result.data[int]).appendTo($('#content'));
							}else if(type==1){
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
