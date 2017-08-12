var This=this;
var nextPage=1;
var lqNum,currentLqNum;
//初始化页面
show();
//加载页面方法
function show(){
	$.ajax({
		url : '/mts/system/appuser/look/json?web=',
		type : "post",
		dataType : "json",
		success : function(result){
			if(result.status=="error"){
				window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
				return;
			}
			var user=result;
			if(result.data!=undefined){
		//获取用户信息
			$.ajax({
				url : '/mts/system/appuser/indexStatics/json?web=&id='+result.data.id+'&cityId='+result.data.cityId,
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
							$('#detail_tmpl').tmpl(result.data).appendTo($('#detail'));
						
							$('#name').html(user.data.name);
							$('#header').attr("src",user.data.header);
							if(user.data.currentLqNum!=undefined){
								
								currentLqNum=user.data.currentLqNum;
								
							}
							
							if(user.data.lqNum!=undefined){
								
								lqNum=user.data.lqNum;
								
							}
							
							$.ajax({
								url : '/mts/system/city/detail/json?web=&id='+user.data.cityId,
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
										weather+=result.data.weather+result.data.templowdu+"°c/"+result.data.templowdu+"°c";
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
		
		$('#sj').html(m+":"+s);
		$('#sjwz').html("后获得"+lqNum+"次机会");
		
	}else{
		var lqHtml="还剩余"+currentLqNum+"次领取机会";
		$('#sj').html("");
		$('#sjwz').html(lqHtml);
	}
	
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

function getQueryString(aaa) { 
	var reg = new RegExp("(^|&)" + aaa + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
} 