var This=this;
var nextPage=1;
var lqNum,currentLqNum;



var dataLunbo="";
if(getCookie("htmlCityId")!=undefined){
	dataLunbo='&cityIds='+getCookie("htmlCityId");
}


var userId;
function canyu(){
	if(userId==""){
		window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
		return;
	}
	window.location.href='/mts/appWeb/circle/circleSave.jsp?';
}

$.ajax({
	url : '/mts/system/lunbopic/list/json?web=&position=7'+dataLunbo,
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
				var url="";
				if(result.data[int].type!=undefined){
					if(result.data[int].type==1){
						url=result.data[int].url;
					}else if(result.data[int].type==2){
						url="/mts/appWeb/posterPackage/posterPackageDetail.jsp?id="+result.data[int].itemId;
					}else if(result.data[int].type==3){
						url="/mts/appWeb/mediaPackage/mediaPackageDetail.jsp?id="+result.data[int].itemId;
					}else if(result.data[int].type==4){
						url="/mts/appWeb/card/cardDetail.jsp?id="+result.data[int].itemId;
					}else if(result.data[int].type==5){
						url="/mts/appWeb/activity/activityDetail.jsp?id="+result.data[int].itemId;
					}else if(result.data[int].type==6){
						url="/mts/appWeb/circle/circleDetail.jsp?id="+result.data[int].itemId;
					}
					
				}
				
				
				$("#lunbo").append("<div class=\"swiper-slide\" onclick=\"window.location.href='"+url+"' \" style=\"height:auto;\"><img src=\"{{= image}}\" class=\"dis_b\" style=\"width:14.25rem;height:6.25rem;" +
				"margin:.5rem auto 0 auto;\" /></div>").find(".swiper-slide:last img").attr("src",result.data[int].image);
		
	}
	var swiper = new Swiper('.swiper-container', {
		pagination: '.swiper-pagination',
        paginationClickable: true,
		observer:true,//修改swiper自己或子元素时，自动初始化swiper
		observeParents:true,//修改swiper的父元素时，自动初始化swiper
		autoplay : 2000,
		loop : true,
		 });
				
setTimeout(function(){
	TouchSlide({
		slideCell:"#bann",
		titCell:".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
		mainCell:".bd ul", 
		effect:"left",
		autoPlay:true,//自动播放
		autoPage:true //自动分页
	});
},5000)
			
		}
		
	},
	error:function(XMLHttpRequest, textStatus, errorThrown){
		console.log(XMLHttpRequest) ;
		console.log(textStatus) ;
	}
});


//初始化页面
show();

//加载页面方法
function show(){
	
	if(getCookie("htmlCityId")==undefined||getCookie("htmlCityId")==null||getCookie("htmlCityId")==""){
		
		setCookie("htmlCityId",110100);
		
	}
	
	$.ajax({
		url : '/mts/system/appuser/look/json?web=',
		type : "post",
		dataType : "json",
		success : function(result){
			var user=result;
			
			if(result.status=="error"){
				userId="";
			}
			
			setCookie("htmlCityId", id);
			
			if(result.data!=undefined){
		//获取用户信息
			$.ajax({
				url : '/mts/system/appuser/indexStatics/json?web=&id='+result.data.id+'&cityId='+getCookie("htmlCityId"),
				type : "post",
				dataType : "json",
				success : function(result){
					
					if(result.status=="error"){
						window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
						return;
					}
					if(result.data!=undefined){
						//获取消息记录
							result.data.cityId = user.data.cityId;
							
							
							result.data.todayMoney = parseFloat(result.data.todayMoney).toFixed(2);
							result.data.sumMoney = parseFloat(result.data.sumMoney).toFixed(2);
							result.data.posterMoney = parseFloat(result.data.posterMoney).toFixed(2);
							result.data.mediaMoney = parseFloat(result.data.mediaMoney).toFixed(2);
							
							result.data.cityId=getCookie("htmlCityId");
							
							$('#detail_tmpl').tmpl(result.data).appendTo($('#detail'));
							
							$("#noLdShow").show();
							$("#ldShow").hide();
							
							
							if(user.data!=undefined){
								$.ajax({
									url : '/mts/system/message/staticsunread/json?web=&userId='+user.data.id,
									type : "post",
									dataType : "json",
									success : function(result){
										if(result.status=="error"){
											window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
											return;
										}
										if(result.data!=undefined){
											if(result.data>0){
												
												$("#noLdShow").hide();
												$("#ldShow").show();
												$("#xxNum").html(result.data);
												
											}
										}
									},
									error:function(XMLHttpRequest, textStatus, errorThrown){
										console.log(XMLHttpRequest) ;
										console.log(textStatus) ;
									}
								});
							}
						
							$('#name').html(user.data.name);
							
							
							if(user.data.header!=undefined&&user.data.header!=""){
								$('#header').attr("src",user.data.header);
							}
							
							
							
							if(user.data.currentLqNum!=undefined){
								
								currentLqNum=user.data.currentLqNum;
								
							}
							
							if(user.data.lqNum!=undefined){
								
								lqNum=user.data.lqNum;
								
							}
							$.ajax({
								url : '/mts/system/city/detail/json?web=&id='+getCookie("htmlCityId"),
								type : "post",
								dataType : "json",
								success : function(result){
									if(result.status=="error"){
										window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
										return;
									}
									if(result.data!=undefined){
										//获取用户信息
										$('#city').html(result.data.name);
										var weather="";
										weather+=result.data.weather+result.data.templow+"℃/"+result.data.temphigh+"℃";
										$('#weather').html(weather);
										$('#city').html(result.data.name);
									}
								},
								error:function(XMLHttpRequest, textStatus, errorThrown){
									console.log(XMLHttpRequest) ;
									console.log(textStatus) ;
								}
							});
							countTime();
					}
					
					$("#add").click(function(even){
						$("#add").hide();
						 event.stopPropagation(); 
					});
					
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					console.log(XMLHttpRequest) ;
					console.log(textStatus) ;
				}
			});
		
			}else{

				
				
				//获取用户信息
				$.ajax({
					url : '/mts/system/appuser/indexStatics/json?web=&id='+0+'&cityId='+getCookie("htmlCityId"),
					type : "post",
					dataType : "json",
					success : function(result){
						
						console.log(result);
						if(result.status=="error"){
							window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
							return;
						}
						if(result.data!=undefined){
							//获取消息记录
								
								
								result.data.todayMoney = parseFloat(result.data.todayMoney).toFixed(2);
								result.data.sumMoney = parseFloat(result.data.sumMoney).toFixed(2);
								result.data.posterMoney = parseFloat(result.data.posterMoney).toFixed(2);
								result.data.mediaMoney = parseFloat(result.data.mediaMoney).toFixed(2);
								
								result.data.cityId=getCookie("htmlCityId");
								
								$('#nouser_detail_tmpl').tmpl(result.data).appendTo($('#detail'));
								
								if(getCookie("htmlCityId")!=null&&getCookie("htmlCityId")!=undefined){
									$.ajax({
										url : '/mts/system/city/detail/json?web=&id='+getCookie("htmlCityId"),
										type : "post",
										dataType : "json",
										success : function(result){
											if(result.status=="error"){
												window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
												return;
											}
											if(result.data!=undefined){
												//获取用户信息
												$('#city').html(result.data.name);
												var weather="";
												weather+=result.data.weather+result.data.templow+"℃/"+result.data.temphigh+"℃";
												$('#weather').html(weather);
												$('#city').html(result.data.name);
											}
										},
										error:function(XMLHttpRequest, textStatus, errorThrown){
											console.log(XMLHttpRequest) ;
											console.log(textStatus) ;
										}
									});
								}
							
								countTime();
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
}


$.ajax({
	url : '/mts/system/appuser/plateStatics/json?web=',
	type : "post",
	dataType : "json",
	success : function(result){
		if(result.status=="error"){
			window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
			return;
		}
		if(result.data!=undefined){
			//获取用户信息
			$('#footDetail_tmpl').tmpl(result.data).appendTo($('#footDetail'));
		}
	},
	error:function(XMLHttpRequest, textStatus, errorThrown){
		console.log(XMLHttpRequest) ;
		console.log(textStatus) ;
	}
});


function countTime() { 
	
	//获取用户信息
	$.ajax({
		url : '/mts/system/appuser/getTime/json?web=',
		type : "post",
		dataType : "json",
		success : function(result){
			
			if(result.status=="error"){
				return ;
			}
	
	if(currentLqNum==0){
		
		//获取当前时间  
		var date = new Date(Date.parse(result.data.replace(/-/g, "/")));;  
		var now = date.getTime();  
		
		var x = date; // 取得的TextBox中的时间
		var time = date;
		
		time.setHours(time.getHours()+1, 0, 0, 0);
		
		var end = time.getTime();
		//时间差  
		var leftTime = end-now;  
		//定义变量 d,h,m,s保存倒计时的时间  
		var m,s;
		if (leftTime>=0) {  
			m = Math.floor(leftTime/1000/60%60);  
			s = Math.floor(leftTime/1000%60);                     
		}  
		//递归每秒调用countTime方法，显示动态时间效果  
		
		if(m==0&&s==0){
			window.location.reload();
		}
		if(s<10){
			s="0"+s;
		}
		if(m<10){
			m="0"+m;
		}
	
		$('#sj').html(m+":"+s);
		$('#sjwz').html("后获得"+lqNum+"次领取机会");
		
	}else{
		var lqHtml="还剩余"+currentLqNum+"次领取机会";
		$('#sj').html("");
		$('#sjwz').html(lqHtml);
	}
	
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			console.log(XMLHttpRequest) ;
			console.log(textStatus) ;
		}
	});
	
	setTimeout(countTime,1000);  
}



window.onscroll=function(){
	var a = document.documentElement.scrollTop==0? document.body.clientHeight : document.documentElement.clientHeight;
	var b = document.documentElement.scrollTop==0? document.body.scrollTop : document.documentElement.scrollTop;
	var c = document.documentElement.scrollTop==0? document.body.scrollHeight : document.documentElement.scrollHeight;
	if(a+b==c){
		nextPage=nextPage+1;
	}
}

function setCookie(name,value) 
{
    var Days = 30; 
    var exp = new Date(); 
    exp.setTime(exp.getTime() + Days*24*60*60*1000); 
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString(); 
} 

function getCookie(name) 
{ 
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
 
    if(arr=document.cookie.match(reg))
 
        return unescape(arr[2]); 
    else 
        return null; 
} 

function delCookie(name) 
{ 
    var exp = new Date(); 
    exp.setTime(exp.getTime() - 1); 
    var cval=getCookie(name); 
    if(cval!=null) 
        document.cookie= name + "="+cval+";expires="+exp.toGMTString(); 
}

function getQueryString(aaa) { 
	var reg = new RegExp("(^|&)" + aaa + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
} 