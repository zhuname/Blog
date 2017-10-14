var This=this;
var nextPage=1;
var dataString="";
var userId=null;
var changeType=1;	
	//初始化页面
change(changeType);

function change(type,change){
	var data='&pageIndex='+nextPage+dataString;
	changeType = type;
	
	if(change==0){
		nextPage=1;
		$("#list").html("");
	}
	
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
				url : '/mts/system/collect/statics/json?web=&userId='+userId,
				type : "post",
				dataType : "json",
				success : function(result){
					if(result.status=="error"){
						window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
						return;
					}
					$("#posterNum").html(result.data.posterCount);
					$("#mediaNum").html(result.data.mediaCount);
					$("#cardNum").html(result.data.cardCount);
					$("#huodongNum").html(result.data.activityCount);
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					console.log(XMLHttpRequest) ;
					console.log(textStatus) ;
				}
			});
			
			//加载数据
			switch (type) {
			case 1:
				$.ajax({
					url : '/mts/system/collect/list/json?web=&type=1&userId='+userId+data,
					type : "post",
					dataType : "json",
					success : function(result){
						
						if(result.status=="error"){
							window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
							return;
						}
						if(result.data!=undefined){
							for (var int = 0; int < result.data.length; int++) {
								$('#posterPackage_list_tmpl').tmpl($(result.data[int].posterPackage)[0]).appendTo($('#list'));
							}
						}
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
						console.log(XMLHttpRequest) ;
						console.log(textStatus) ;
					}
					
				});
				
				
				$("#posterShow").attr("class","f_20 clr_r dis_f jus_ct flex_col ali_ct favor_cur");
				$($("#posterShow").children()[0]).attr("src",$("#url").html()+"sc1h.png");
				$("#mediaShow").attr("class","f_20 clr_3 dis_f jus_ct flex_col ali_ct");
				$($("#mediaShow").children()[0]).attr("src",$("#url").html()+"sc2.png");
				$("#cardShow").attr("class","f_20 clr_3 dis_f jus_ct flex_col ali_ct");
				$($("#cardShow").children()[0]).attr("src",$("#url").html()+"sc3.png");
				$("#huodongShow").attr("class","f_20 clr_3 dis_f jus_ct flex_col ali_ct");
				$($("#huodongShow").children()[0]).attr("src",$("#url").html()+"sc4.png");
				
				break;
			case 2:
				
				$.ajax({
					url : '/mts/system/collect/list/json?web=&type=2&userId='+userId+data,
					type : "post",
					dataType : "json",
					success : function(result){
						if(result.status=="error"){
							window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
							return;
						}
						if(result.data!=undefined){
							for (var int = 0; int < result.data.length; int++) {
								
								$('#media_list_tmpl').tmpl($(result.data[int].mediaPackage)[0]).appendTo($('#list'));
								
							}
						}
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
						console.log(XMLHttpRequest) ;
						console.log(textStatus) ;
					}
					
				});
				
				$("#mediaShow").attr("class","f_20 clr_r dis_f jus_ct flex_col ali_ct favor_cur");
				$("#posterShow").attr("class","f_20 clr_3 dis_f jus_ct flex_col ali_ct");
				$("#cardShow").attr("class","f_20 clr_3 dis_f jus_ct flex_col ali_ct");
				$("#huodongShow").attr("class","f_20 clr_3 dis_f jus_ct flex_col ali_ct");
				
				$($("#posterShow").children()[0]).attr("src",$("#url").html()+"sc1.png");
				$($("#mediaShow").children()[0]).attr("src",$("#url").html()+"sc2h.png");
				$($("#cardShow").children()[0]).attr("src",$("#url").html()+"sc3.png");
				$($("#huodongShow").children()[0]).attr("src",$("#url").html()+"sc4.png");
				break;
			case 3:
			
				$.ajax({
					url : '/mts/system/collect/list/json?web=&type=3&userId='+userId+data,
					type : "post",
					dataType : "json",
					success : function(result){
						if(result.status=="error"){
							window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
							return;
						}
						if(result.data!=undefined){
							for (var int = 0; int < result.data.length; int++) {
								
								if($(result.data[int].card)[0].endTime){
									
									$(result.data[int].card)[0].endTime=$(result.data[int].card)[0].endTime.substring(0,10);
									
								}
								
								
								$('#card_list_tmpl').tmpl($(result.data[int].card)[0]).appendTo($('#list'));
							}
						}
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
						console.log(XMLHttpRequest) ;
						console.log(textStatus) ;
					}
					
				});
				
				
				
				$("#cardShow").attr("class","f_20 clr_r dis_f jus_ct flex_col ali_ct favor_cur");
				$("#mediaShow").attr("class","f_20 clr_3 dis_f jus_ct flex_col ali_ct");
				$("#posterShow").attr("class","f_20 clr_3 dis_f jus_ct flex_col ali_ct");
				$("#huodongShow").attr("class","f_20 clr_3 dis_f jus_ct flex_col ali_ct");
				$($("#posterShow").children()[0]).attr("src",$("#url").html()+"sc1.png");
				$($("#mediaShow").children()[0]).attr("src",$("#url").html()+"sc2.png");
				$($("#cardShow").children()[0]).attr("src",$("#url").html()+"sc3h.png");
				$($("#huodongShow").children()[0]).attr("src",$("#url").html()+"sc4.png");
				break;
			case 4:
			
				$.ajax({
					url : '/mts/system/collect/list/json?web=&type=4&userId='+userId+data,
					type : "post",
					dataType : "json",
					success : function(result){
						if(result.status=="error"){
							window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
							return;
						}
						if(result.data!=undefined){
							for (var int = 0; int < result.data.length; int++) {
								$('#activity_list_tmpl').tmpl($(result.data[int].activity)[0]).appendTo($('#list'));
							}
						}
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
						console.log(XMLHttpRequest) ;
						console.log(textStatus) ;
					}
					
				});
				
				
				$("#huodongShow").attr("class","f_20 clr_r dis_f jus_ct flex_col ali_ct favor_cur");
				$("#mediaShow").attr("class","f_20 clr_3 dis_f jus_ct flex_col ali_ct");
				$("#cardShow").attr("class","f_20 clr_3 dis_f jus_ct flex_col ali_ct");
				$("#posterShow").attr("class","f_20 clr_3 dis_f jus_ct flex_col ali_ct");
				$($("#posterShow").children()[0]).attr("src",$("#url").html()+"sc1.png");
				$($("#mediaShow").children()[0]).attr("src",$("#url").html()+"sc2.png");
				$($("#cardShow").children()[0]).attr("src",$("#url").html()+"sc3.png");
				$($("#huodongShow").children()[0]).attr("src",$("#url").html()+"sc4h.png");
				break;
			}
			
		
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			console.log(XMLHttpRequest);
			console.log(textStatus);
		}
		});
	
	
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
