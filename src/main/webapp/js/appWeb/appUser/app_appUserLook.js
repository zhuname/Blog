var This=this;
var lqNum,currentLqNum;





var dataLunbo="";
/*if(getQueryString("cityId")!=undefined){
	dataLunbo='&cityIds='+getQueryString("cityId");
}*/

if(getCookie("htmlCityId")==undefined||getCookie("htmlCityId")==null||getCookie("htmlCityId")==""){
	debugger;
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
				//获取消息记录
				for (var int = 0; int < result.data.length; int++) {
					$('#lunbo_list_tmpl').tmpl({'image':result.data[int].image}).appendTo($('#lunbo'));
					
				}
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