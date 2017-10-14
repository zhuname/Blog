var This=this;
var lqNum,currentLqNum;


//获取用户信息
$.ajax({
	url : '/mts/system/appuser/look/json?web=',
	type : "post",
	dataType : "json",
	success : function(result){
		
		if(result.status=="error"){
			window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
			return;
		}
		
		if(result.data.cityId==undefined){
			window.location.href="/mts/appWeb/appuser/myCity.jsp?id="+result.data.id;
			return;
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
		
	},
	error:function(XMLHttpRequest, textStatus, errorThrown){
		console.log(XMLHttpRequest) ;
		console.log(textStatus) ;
	}
});




function countTime() {  
	
	if(currentLqNum==0){
		
		//获取当前时间  
		var date = new Date();  
		var now = date.getTime();  
		
		var x = date; // 取得的TextBox中的时间
		var time = new Date(x);
		
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
		var lqHtml="还剩余<span class=\"f_30\">"+currentLqNum+"</span>次领取机会";
		$('#lqjh').html(lqHtml);
	}
	
	setTimeout(countTime,1000);  
}


function getQueryString(aaa) { 
	var reg = new RegExp("(^|&)" + aaa + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
} 