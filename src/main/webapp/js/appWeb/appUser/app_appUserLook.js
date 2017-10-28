var This=this;
var lqNum,currentLqNum;





var dataLunbo="";
/*if(getQueryString("cityId")!=undefined){
	dataLunbo='&cityIds='+getQueryString("cityId");
}*/

if(getCookie("htmlCityId")==undefined||getCookie("htmlCityId")==null||getCookie("htmlCityId")==""){
	setCookie("htmlCityId",410100);
	dataLunbo='&cityIds='+getCookie("htmlCityId");
}else{
	dataLunbo='&cityIds='+getCookie("htmlCityId");
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
				
				
				//获取消息记录
				for (var int = 0; int < result.data.length; int++) {
					$("#lunbo").append("<div class=\"swiper-slide\" onclick=\"window.location.href='"+url+"' \" style=\"height:auto;\"><img src=\"{{= image}}\" class=\"dis_b\" style=\"width:14.25rem;height:6.25rem;" +
							"margin:.5rem auto 0 auto;\" /></div>").find(".swiper-slide:last img").attr("src",result.data[int].image)
					
				}
				var swiper = new Swiper('.swiper-container', {
					pagination: '.swiper-pagination',
			        paginationClickable: true,
		    		observer:true,//修改swiper自己或子元素时，自动初始化swiper
		    		observeParents:true,//修改swiper的父元素时，自动初始化swiper
		    		autoplay : 2000,
		    		loop : true,
		   		 });
				
			}
			
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			console.log(XMLHttpRequest) ;
			console.log(textStatus) ;
		}
	});
}








//获取用户信息
$.ajax({
	url : '/mts/system/appuser/look/json?web=',
	type : "post",
	dataType : "json",
	success : function(result){
		
		if(result.status=="error"){
			$('#weidenglu').tmpl(null).appendTo($('#detail'));
			return;
		}
		
		if(result.data.cityId==undefined){
			window.location.href="/mts/appWeb/appuser/myCity.jsp?id="+result.data.id;
			return;
		}
		
		if(getQueryString("isCity")!=undefined){
			setCookie("htmlCityId", result.data.cityId);
		}
		
		if(result.data!=undefined){
			$('#appuser_detail_tmpl').tmpl(result.data).appendTo($('#detail'));
			
			
			if(result.data.userMedals!=undefined){
				
				
				for (var int = 0; int < result.data.userMedals.length; int++) {
					$('#appuser_xunzhang_tmpl').tmpl(result.data.userMedals[int]).appendTo($('#xzmb'));
					
				}
				
				
			}
			
			$.ajax({
				url : '/mts/system/message/staticsunread/json?web=&userId='+result.data.id,
				type : "post",
				dataType : "json",
				success : function(result){

					if(result.status=="error"){
						window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
						return;
					}
					if(result.data!=undefined){
						if(result.data>0){
							$('#msg').html("");
							$('#message_weidu_tmpl').tmpl(null).appendTo($('#msg'));
						}else{
							$('#msg').html("");
							$('#message_yidu_tmpl').tmpl(null).appendTo($('#msg'));
						}
					}
					
					
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					console.log(XMLHttpRequest) ;
					console.log(textStatus) ;
				}
			});
			
			$.ajax({
				url : '/mts/system/appuser/statics/json?web=&id='+result.data.id,
				type : "post",
				dataType : "json",
				success : function(result){

					if(result.status=="error"){
						window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
						return;
					}
					$('#appuser_statics_tmpl').tmpl(result.data).appendTo($('#detail'));
					
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					console.log(XMLHttpRequest) ;
					console.log(textStatus) ;
				}
			});
			
			if(result.data.currentLqNum!=undefined){
				
				currentLqNum=result.data.currentLqNum;
				
			}
			if(result.data.lqNum!=undefined){
				
				lqNum=result.data.lqNum;
				
			}
			
			countTime();
			
		}else{
			$('#appuser_noLoginDetail_tmpl').tmpl(null).appendTo($('#detail'));
			$('#appuser_statics_tmpl').tmpl(null).appendTo($('#detail'));
		}
//		点击关闭
		$("#add").click(function(even){
			$("#add").hide();
			 event.stopPropagation(); 
		})
		
	},
	error:function(XMLHttpRequest, textStatus, errorThrown){
		console.log(XMLHttpRequest) ;
		console.log(textStatus) ;
	}
});





function getTime() {  
	
			console.log(result.data);
			return result.data;
		
}


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
		var date = new Date(Date.parse(result.data.replace(/-/g, "/")));;  
		var now = date.getTime();  
		
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
		
		var lqHtml="<span class=\"f_30\">"+m+":"+s+"</span>后   获得<span class=\"f_30\">"+lqNum+"</span>次领取机会";
		
		$('#lqSpan').html($('#appuser_wenPic_tmpl').html());
		
		$('#lqjh').html(lqHtml);
	}else{
		var lqHtml="您有<span class=\"f_30\">"+currentLqNum+"</span>次领取机会";
		$('#lqjh').html(lqHtml);
	}
	
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			console.log(XMLHttpRequest) ;
			console.log(textStatus) ;
		}
	});
	setTimeout(countTime,1000);  
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


$.ajax({
	url : '/mts/system/syssysparam/list/json?web=',
	type : "post",
	dataType : "json",
	success : function(result){
		
		if(result.status=="error"){
			window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
			return;
		}
		if(result.data!=undefined){
			//获取消息记录
			$('#packageRule').html(result.data.packageRule);
			
		}
		
	},
	error:function(XMLHttpRequest, textStatus, errorThrown){
		console.log(XMLHttpRequest) ;
		console.log(textStatus) ;
	}
});

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