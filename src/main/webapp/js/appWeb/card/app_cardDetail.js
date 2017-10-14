var This=this;
var convertMoney;
var num;
var limitNumber;
var userId;

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
		url : '/mts/system/card/look/json?web=&id='+getQueryString("id"),
		type : "post",
		dataType : "json",
		success : function(result){
			
			if(result.status=="error"){
				window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
				return;
			}
			
			if(result.data!=undefined){
				//获取消息记录
				
				if(result.data.endTime!=undefined){
					//获取消息记录
					
					result.data.endTime=result.data.endTime.substring(0,7);
					
				}
				
				if(result.data.convertMoney==undefined){
					result.data.convertMoney="免费";
				}else{
					result.data.convertMoney = parseFloat(result.data.convertMoney).toFixed(2);
				}
				
				$('#detail_tmpl').tmpl(result.data).appendTo($('#detail'));
				
				convertMoney=result.data.convertMoney;
				num=result.data.num;
				limitNumber=result.data.num;
				
				$('#lingqu_tmpl').tmpl({'balance':user.balance,'convertMoney':result.data.convertMoney}).appendTo($('#detail'));
				
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
		if(parseInt($('#num').val())==parseInt(num)||parseInt($('#num').val())==parseInt(limitNumber)){
			return;
		}
		$('#num').val(parseInt($('#num').val())+1);
		$('#balance').html(parseInt($('#num').val())*parseInt(convertMoney));
		$('#convertMoney').html(parseInt($('#num').val())*parseInt(convertMoney));
	}
}

function pay(){
	
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
				window.location.href="/mts/appWeb/card/cardUserList.jsp?id="+getQueryString("id");
			}
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
