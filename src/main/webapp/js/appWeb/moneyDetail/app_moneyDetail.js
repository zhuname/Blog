var This=this;
var month="";
var isMonth=0;
var nextPage=1;


//初始化页面
show();


//加载页面方法
function show(){
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
			
			if(result.data!=undefined){
				
				if(result.data.balance!=undefined){
					
					$('#balance').html(result.data.balance);
					
				}
				
				//获取收支记录
				if(result.data.id!=undefined){
					$.ajax({
						url : '/mts/system/moneydetail/money/json?web=&userId='+result.data.id+'&pageIndex='+nextPage,
						type : "post",
						dataType : "json",
						success : function(result){
							
							if(result.status=="error"){
								window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
								return;
							}
							
							if(result.data!=undefined){
								for (var int = 0; int < result.data.length; int++) {
									
									//处理月份，
									if(result.data[int].createTime!=undefined){
										//获取月份
										var date=result.data[int].createTime.substring(0,7);
										
										var monthList=month.split(";");
										
										//判断是否增加到月份里，判断重复用
										isMonth=0;
										
										for (var int2 = 0; int2 < monthList.length; int2++) {
											
											if(monthList[int2]==date){
												isMonth=1;
											}
											
										}
										
										if(isMonth==0){
											month+=date+";";
											var monthStr=date.split("-")[1];
											var yearStr=date.split("-")[0];
											
											$('#moneyDetail_monthList_tmpl').tmpl({month:monthStr,year:yearStr}).appendTo($('#moneyDetailList'));
										}
										
										
										//处理数据
										var moneyDetail=result.data[int];
										if(moneyDetail.money!=undefined){
											if(moneyDetail.money>0){
												moneyDetail.money="+"+moneyDetail.money;
											}
										}
										
										
										moneyDetail.day=result.data[int].createTime.substring(8,10);
										moneyDetail.time=result.data[int].createTime.split(" ")[1].substring(0,5);
										
										
										$('#moneyDetail_list_tmpl').tmpl(moneyDetail).appendTo($('#moneyDetailList'));
										
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
				
			}
			
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
		show();
	}
}


function getQueryString(aaa) { 
	var reg = new RegExp("(^|&)" + aaa + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
} 