var This=this;
var convertMoney;
var num;
var limitNumber;
var userId;
var id;
var itemUserId;
var payType=3;
var lqNum=0;

function changePayType(type){
	payType=type;
	javascript:$('.pay-type').css('top','100%');
	switch (type) {
	case 1:
		$("#payString").html("支付宝");
		break;
	case 2:
		$("#payString").html("微信");
		break;
	case 3:
		$("#payString").html("账户余额");
		break;
	default:
		break;
	}
}

$().ready(function(){
	
    var ua = navigator.userAgent.toLowerCase();  
    if(ua.match(/MicroMessenger/i)=="micromessenger") {  
    	
    	if(code!=null&&code!=undefined&&code!="undefined"){
    		$.ajax({
    			url : '/mts/system/appuser/openId/json?web=1&code='+code,
    			type : "get",
    			success : function(result) {
    				data = JSON.parse(result.data);
    				data = JSON.parse(data);
    				openid = data.openid;
    			},
    			error:function(XMLHttpRequest, textStatus, errorThrown){
    				console.log(XMLHttpRequest) ;
    				console.log(textStatus) ;
    			}
    		});
    	}else{
    		var isShare="";
    		if(getQueryString("isShare")!=undefined){
    			isShare="isShare";
    		}

    		window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx8653ea068146c48c&redirect_uri=http://app.mtianw.com/mts/appWeb/card/cardDetail.jsp?id="+getQueryString("id")+"&response_type=code&scope=snsapi_base&state="+isShare+"#wechat_redirect";
    	}
    	$('#zfbShow').remove();
    } else {
    	if(document.referrer+""!="http://app.mtianw.com/mts/appWeb/appuser/appuserLogin.jsp"){
			setCookie("cardUrl",document.referrer);
		}
    	$('#wxShow').remove();
    }  
}); 

function hrefCardShare() {
	window.location.href=getCookie("cardUrl");
}

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
			$("#payBalance").html(result.data.balance);
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
				
				initColl();
				
				if(result.data.title!=undefined){
					$("#title").html(result.data.title);
				}
				
				$('#detail_tmpl').tmpl(result.data).appendTo($('#detail'));
				
				convertMoney=result.data.convertMoney;
				num=result.data.num;
				limitNumber=result.data.limitNumber;
				
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
		$('#balance').html($('#num').val()*convertMoney);
		$('#convertMoney').html($('#num').val()*convertMoney);
	}else if(type ==2){
		if(parseInt($('#num').val())>=parseInt(num-lqNum)||parseInt($('#num').val())>=parseInt(limitNumber-lqNum)){
			return;
		}
		$('#num').val(parseInt($('#num').val())+1);
		$('#balance').html($('#num').val()*convertMoney);
		$('#convertMoney').html($('#num').val()*convertMoney);
	}
}



function pay(){
	
	if(itemUserId==userId){
		alert("不能领取自己的卡券哦~");
		return;
	}
	
	
	if(userId!=null&&userId!=""&&id!=null&&id!=""){
			
			
			if(payType==3){
				$.ajax({
					url : '/mts/system/card/payCard/json?web=&cardId='+getQueryString("id")+'&userId='+userId+'&num='+$('#num').val(),
					type : "post",
					dataType : "json",
					success : function(result){
						if(result.status=="error"){
							alert(result.message);
							return;
						}
						if(result.data!=undefined){
							
							if(result.data.convertMoney==undefined){
								location.reload();
								return;
							}
							
							$.ajax({
								url : '/mts/system/appuser/pay/json?web=&type=3&userId='+userId+'&code='+result.data.userCards[0].code,
								type : "post",
								dataType : "json",
								success : function(result){
									if(result.status=="error"){
										window.location.href="/mts/appWeb/card/cardUserList.jsp?id="+getQueryString("id");
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
			}else if(payType==1){
				$.ajax({
					url : '/mts/system/card/payCard/json?web=&cardId='+getQueryString("id")+'&userId='+userId+'&num='+$('#num').val(),
					type : "post",
					dataType : "json",
					success : function(result){
						if(result.status=="error"){
							alert(result.message);
							return;
						}
						if(result.data!=undefined){
							
							if(result.data.convertMoney==undefined){
								location.reload();
								return;
							}
							
							window.location.href="/mts/system/zfb/getDingdan/json?name=购买卡券&money="+($('#num').val()*result.data.convertMoney)+"&detail=购买卡券&code="+"C"+result.data.code+"_"+new Date().getTime();
							
						}
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
						console.log(XMLHttpRequest) ;
						console.log(textStatus) ;
					}
				});
				
			}if(payType==2){
				
				$.ajax({
					url : '/mts/system/card/payCard/json?web=&cardId='+getQueryString("id")+'&userId='+userId+'&num='+$('#num').val(),
					type : "post",
					dataType : "json",
					success : function(result){
						if(result.status=="error"){
							alert(result.message);
							return;
						}
						if(result.data!=undefined){
							
							if(result.data.convertMoney==undefined){
								location.reload();
								return;
							}
							
							
							
							$.ajax({
								url : '/mts/system/wx/getDingdan/json?web=1&code='+result.data.code+'&payType=D&openid1='+openid+'&total_fee1='+($('#num').val()*result.data.convertMoney)*100,
								type : "get",
								success : function(result) {
									console.log(result);
									
									var out_trade_no=result.data.out_trade_no;
									
									WeixinJSBridge.invoke(  
									        'getBrandWCPayRequest', {  
									            "appId" : result.data.appId,     //公众号名称，由商户传入   
									            "timeStamp" :  result.data.timeStamp, //时间戳，自1970年以来的秒数 (java需要处理成10位才行，又一坑)
									            "nonceStr" :  result.data.nonceStr, //随机串  
									            "package" :  result.data.package1, //拼装好的预支付标示  
									            "signType" : "MD5",//微信签名方式  
									            "paySign" :  result.data.paySign //微信签名  
									        },  
									        function(res){  
									             //使用以下方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。  
									            if(res.err_msg == "get_brand_wcpay_request:ok" ) {       
									                 alert("支付成功");      
									                 
									                 $.ajax({
									             		url : '/mts/system/wx/htmlRetrun/json?web=1&out_trade_no='+out_trade_no,
									             		type : "get",
									             		success : function(result){
									             			data = JSON.parse(result.data);
									             			data = JSON.parse(data);
									             			openid = data.openid;
									             		},
									             		error:function(XMLHttpRequest, textStatus, errorThrown){
									             			console.log(XMLHttpRequest) ;
									             			console.log(textStatus) ;
									             		}
									             	});
									                 ;
									                 
									                 
									            }else{  
									                 alert("支付失败");  
									            }  
									        }  
									    );
									
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
			
			
	}
			
	
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









var jubaoItemId="";
var reportedUserId="";

function report(obj){
	
	if(userId==""){
		window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
		return;
	}
	
	$(".alert-box").css("top","0");
	$(obj).parents('.more_ul').toggle();
	$(obj).parents('.more_ul').siblings('.arr_up_down').toggle();
	
	reportedUserId=itemUserId;
	jubaoItemId=id;
}

function jubao(){
	//加载页面方法
	$.ajax({
	url : '/mts/system/report/update/json?web=&type=5&operUserId='+userId+'&itemId='+jubaoItemId+'&reportedUserId='+reportedUserId+'&content='+$("#content").val(),
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
