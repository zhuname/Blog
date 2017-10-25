var This=this;
var convertMoney;
var num;
var limitNumber;
var userId;
var id;
var itemUserId;
var lqNum=0;

$.ajax({
	url : '/mts/system/appuser/look/json?web=',
	type : "post",
	dataType : "json",
	success : function(result){
		var user= result.data;
		if(result.status=="error"){
			userId="";
		}else{
			userId=result.data.id;
			userData="&appUserId="+userId;
			balance=result.data.balance;
		}
	$.ajax({
		url : '/mts/system/card/look/json?web=&id='+getQueryString("id")+'&appUserId='+userId,
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
				itemUserId=result.data.userId;
				lqNum=result.data.lqNum;
				if(result.data.endTime!=undefined){
					//获取消息记录
					
					result.data.endTime=result.data.endTime.substring(0,10);
					
				}
				
				if(result.data.convertMoney==undefined){
					result.data.convertMoney="免费";
				}else{
					result.data.convertMoney = parseFloat(result.data.convertMoney).toFixed(2);
				}
				
				if(result.data.num==0){
					result.data.status=5;
				}
				
				if(result.data.status==2&&userId==""){
					result.data.status=6;
				}
				
				$('#detail_tmpl').tmpl(result.data).appendTo($('#detail'));
				
				convertMoney=result.data.convertMoney;
				num=result.data.num;
				limitNumber=result.data.num;
				
				if(user==undefined){
					$('#lingqu_tmpl').tmpl({'balance':0,'convertMoney':result.data.convertMoney}).appendTo($('#detail'));
				}else{
					$('#lingqu_tmpl').tmpl({'balance':user.balance,'convertMoney':result.data.convertMoney}).appendTo($('#detail'));
				}
				
				if(getQueryString("userCardId")!=undefined&&getQueryString("userCardId")!=""&&getQueryString("userCardId")!=null){
					//领取卡券列表
					$.ajax({
						url : '/mts/system/usercard/look/json?web=&id='+getQueryString("userCardId"),
						type : "post",
						dataType : "json",
						success : function(result){
							console.log(result);
							if(result.status=="error"){
								window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
								return;
							}
							if(result.data!=undefined){
								
								$("#cardCode").html(result.data.cardCode);
								
								
							}
						},
						error:function(XMLHttpRequest, textStatus, errorThrown){
							console.log(XMLHttpRequest) ;
							console.log(textStatus) ;
						}
					});
				}
				
				//领取卡券列表
				$.ajax({
					url : '/mts/system/moneydetail/listuser/json?web=&itemId='+result.data.id,
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
									
									$('#lingquan_tmpl').tmpl(result.data[int]).appendTo($('#lingquan'));
									
								}
								
							}
							$('#lingquanFoot_tmpl').tmpl(null).appendTo($('#lingquan'));
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
	if(result.data!=undefined){
		for (var int = 0; int < result.data.length; int++) {
			if(int<6){
				
				$('#lingquan_tmpl').tmpl(result.data[int]).appendTo($('#lingquan'));
				
			}
			
		}
		$('#lingquanFoot_tmpl').tmpl(null).appendTo($('#lingquan'));
	}
	},
error:function(XMLHttpRequest, textStatus, errorThrown){
console.log(XMLHttpRequest) ;
console.log(textStatus) ;
}
});

function change(type){
	if(type==1){
		if(parseInt($('#num').val())==parseInt(1)){
			return;
		}
		$('#num').val(parseInt($('#num').val())-1);
		$('#balance').html(parseInt($('#num').val())*parseInt(convertMoney));
		$('#convertMoney').html(parseInt($('#num').val())*parseInt(convertMoney));
	}else if(type ==2){
		if(parseInt($('#num').val())==parseInt(num-lqNum)||parseInt($('#num').val())==parseInt(limitNumber-lqNum)){
			return;
		}
		$('#num').val(parseInt($('#num').val())+1);
		$('#balance').html(parseInt($('#num').val())*parseInt(convertMoney));
		$('#convertMoney').html(parseInt($('#num').val())*parseInt(convertMoney));
	}
}

function pay(){
	
	if(itemUserId==userId){
		alert("不能领取自己的卡券哦~");
		return;
	}
	
	$.ajax({
		url : '/mts/system/card/payCard/json?web=&cardId='+getQueryString("id")+'&userId='+userId+'&num='+$('#num').val(),
		type : "post",
		dataType : "json",
		success : function(result){
			if(result.status=="error"){
				window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
				return;
			}
			if(result.data!=undefined){
				$.ajax({
					url : '/mts/system/appuser/pay/json?web=&type=3&userId='+userId+'&code='+result.data.userCards[0].code,
					type : "post",
					dataType : "json",
					success : function(result){
						if(result.status=="error"){
							window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
							return;
						}
						if(result.data!=undefined){
							window.location.href="/mts/appWeb/card/cardUserList.jsp?id="+getQueryString("id");
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


function phone(date){
	$("#tell").click();
}

function initColl(){
	if(userId!=undefined&&userId!=null){
	
	if(userId!=itemUserId){
	//加载页面方法
	$.ajax({
	url : '/mts/system/attention/atten/json?web=&userId='+userId+'&itemId='+itemUserId,
	type : "post",
	dataType : "json",
	success : function(result){
		
		if(result.status=="error"){
			
			window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
			return;
		}
		if(result.data==0){
			$("#attr").html("关注");
		}else{
			$("#attr").html("已关注");
		}
	},
	error:function(XMLHttpRequest, textStatus, errorThrown){
			console.log(XMLHttpRequest) ;
			console.log(textStatus) ;
		}
	});
	//加载页面方法
	$.ajax({
	url : '/mts/system/collect/coll/json?web=&type=3&userId='+userId+'&itemId='+id,
	type : "post",
	dataType : "json",
	success : function(result){
		if(result.status=="error"){
			
			window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
			return;
		}
		if(result.data==0){
			$("#collect").html("收藏");
		}else{
			$("#collect").html("已收藏");
		}
	},
	error:function(XMLHttpRequest, textStatus, errorThrown){
			console.log(XMLHttpRequest) ;
			console.log(textStatus) ;
		}
	});
	}else{
		$($("#attr").parent()).remove();
		$($("#collect").parent()).remove();
	}
	
	}
}

function collect(){
	
	if(userId==""){
		window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
		return;
	}
	//加载页面方法
	$.ajax({
	url : '/mts/system/collect/update/json?web=&type=3&userId='+userId+'&itemId='+id,
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

function attr(){
	//加载页面方法
	if(userId==""){
		window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
		return;
	}
	$.ajax({
	url : '/mts/system/attention/update/json?web=&userId='+userId+'&itemId='+itemUserId,
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



















function getQueryString(aaa) { 
	var reg = new RegExp("(^|&)" + aaa + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
} 
