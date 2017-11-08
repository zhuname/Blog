var This=this;
var convertMoney;
var num;
var limitNumber;
var userId;
var nextPage=1;
var id;
var itemUserId;
var userData="";
var balance;
var payType;
var code=getQueryString("code");
var openid=null;
var unionid = null ;

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
    		window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx8653ea068146c48c&redirect_uri=http://app.mtianw.com/mts/appWeb/circle/circleDetail.jsp?id="+getQueryString("id")+"&response_type=code&scope=snsapi_base&state="+isShare+"#wechat_redirect";
    	}
    	$('#zfbShow').remove();
    } else {
    	$('#wxShow').remove();
    }  
}); 
$.ajax({
	url : '/mts/system/appuser/look/json?web=',
	type : "post",
	dataType : "json",
	success : function(result){
		
		if(result.status=="error"){
			userId="";
		}else{
			userId=result.data.id;
			userData="&appuserId="+userId;
			$("#payBalance").html(result.data.balance);
		}
		var user= result.data;
	$.ajax({
		url : '/mts/system/circle/look/json?web=&id='+getQueryString("id")+userData,
		type : "post",
		dataType : "json",
		success : function(result){
			
			if(result.status=="error"){
				
				window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
				return;
			}
			
			if(result.data!=undefined){
				//获取消息记录
				
				
				if(result.data.createTime){
					
					result.data.createTime=getDateDiff(result.data.createTime);
					
				}
				
				
				$('#detail_tmpl').tmpl(result.data).appendTo($('#detail'));
				
				
				if(result.data.image){
					
					result.data.image=result.data.image.split(";");
					
				}
				
				if(result.data.image!=undefined){
					for (var int = 0; int < result.data.image.length; int++) {
						if(result.data.image[int]!=""){
							$('#detail_image_tmpl').tmpl({'image':result.data.image[int]}).appendTo($('#images'));
						}
					}
				}
				
				id=result.data.id;
				
				//右上角
				itemUserId=result.data.userId;
				initColl();
				
				if(userId!=undefined){
					$('#foot_tmpl').tmpl(result.data).appendTo($('#detail'));
				}else{
					$('#footNo_tmpl').tmpl(null).appendTo($('#detail'));
				}
				
				
				change(1);
				
				
			}
			
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			console.log(XMLHttpRequest) ;
			console.log(textStatus) ;
		}
	});
	},
error:function(XMLHttpRequest, textStatus, errorThrown){
console.log(XMLHttpRequest) ;
console.log(textStatus) ;
}
});
var isChange=0;
function change(changeType){
	$('#content').html("");
	nextPage = 1 ;
	type=changeType;
	if(isChange==0){
		show(type);
	}
}


function initColl(){
	if(userId!=undefined&&userId!=null&&userId!=""){
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
	//加载页面方法
	$.ajax({
	url : '/mts/system/collect/update/json?web=&type=4&userId='+userId+'&itemId='+id,
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


function shaizi(){
	var random=Math.round(Math.random()*9+1)+"."+Math.round(Math.random()*9)+Math.round(Math.random()*9);
	$("#money").val(random);
	$("#balance").html(random);
}

function attr(){
	//加载页面方法
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



function show(type){
	isChange=1;
	var url="";
	
	if(type==1){
		$("#dz").removeAttr('style');
		$("#xx").removeAttr('style');
		$("#ds").attr('style',"border-bottom:1px solid #f95d47;");
		
		$("#dsImg").attr("src",$("#srcUrl").html()+"/js/appWeb/images/dsh.png");
		$("#xxImg").attr("src",$("#srcUrl").html()+"/js/appWeb/images/xx.png");
		$("#dzImg").attr("src",$("#srcUrl").html()+"/js/appWeb/images/zan2.png");
		
		$("#dsSpan").attr("class","f_20 clr_r ver_mid");
		$("#xxSpan").attr("class","f_20 clr_6 ver_mid");
		$("#dzSpan").attr("class","f_20 clr_6 ver_mid");
		
		url='/mts/system/citycircle/list/json?web=&itemId='+id+"&pageIndex="+nextPage;
	}else if(type==7){
		$("#ds").removeAttr('style');
		$("#xx").removeAttr('style');
		$("#dz").attr('style',"border-bottom:1px solid #f95d47;");
		
		$("#dsImg").attr("src",$("#srcUrl").html()+"/js/appWeb/images/ds.png");
		$("#xxImg").attr("src",$("#srcUrl").html()+"/js/appWeb/images/xx.png");
		$("#dzImg").attr("src",$("#srcUrl").html()+"/js/appWeb/images/zan3.png");
		
		$("#dsSpan").attr("class","f_20 clr_6 ver_mid");
		$("#xxSpan").attr("class","f_20 clr_6 ver_mid");
		$("#dzSpan").attr("class","f_20 clr_r ver_mid");
		
		url='/mts/system/oper/list/json?type='+type+'&web=&itemId='+id+"&pageIndex="+nextPage;
	}else if(type==8){
		$("#ds").removeAttr('style');
		$("#dz").removeAttr('style');
		$("#xx").attr('style',"border-bottom:1px solid #f95d47;");
		
		$("#dsImg").attr("src",$("#srcUrl").html()+"/js/appWeb/images/ds.png");
		$("#xxImg").attr("src",$("#srcUrl").html()+"/js/appWeb/images/xxh.png");
		$("#dzImg").attr("src",$("#srcUrl").html()+"/js/appWeb/images/zan2.png");
		
		$("#dsSpan").attr("class","f_20 clr_6 ver_mid");
		$("#xxSpan").attr("class","f_20 clr_r ver_mid");
		$("#dzSpan").attr("class","f_20 clr_6 ver_mid");
		
		url='/mts/system/oper/list/json?type='+type+'&web=&itemId='+id+"&pageIndex="+nextPage;
	}
	//评论/点赞列表
	$.ajax({
		url : url,
		type : "post",
		dataType : "json",
		success : function(result){
			if(result.status=="error"){
				
				window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
				return;
			}
			if(result.data!=undefined){
					for (var int = 0; int < result.data.length; int++) {
						if(result.data[int].createTime){
							
							result.data[int].createTime=getDateDiff(result.data[int].createTime);
							
						}
						if(type==8){
							$('#content_tmpl').tmpl(result.data[int]).appendTo($('#content'));
						}else if(type==7){
							$('#top_tmpl').tmpl(result.data[int]).appendTo($('#content'));
						}else if(type==1){
							$('#shang_list_tmpl').tmpl(result.data[int]).appendTo($('#content'));
						}
						
					}
					isChange=0;
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			console.log(XMLHttpRequest) ;
			console.log(textStatus) ;
		}
	});
}


function dashangShow(){
	if(userId==""){
		window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
		return;
	}
	$('#showDashang').show().css("position","absolute");
	$("body").scrollTop(0);
	$(".wraper").css("height","100%")
}


function dianzan(){
	if(userId==""){
		window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
		return;
	}
	
	if(userId!=null&&userId!=""&&id!=null&&id!=""){
		$('#dianzanshow').html("");
		$('#zan_image_tmpl').tmpl(null).appendTo($('#dianzanshow'));
		//加载页面方法
		$.ajax({
		url : '/mts/system/oper/update/json?web=&type=7&itemId='+getQueryString("id")+"&userId="+userId,
		type : "post",
		dataType : "json",
		success : function(result){
			if(result.status=="error"){
				
				window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
				return;
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
				console.log(XMLHttpRequest) ;
				console.log(textStatus) ;
			}
		});
	
	}
}

function showInput(id){
	
	if(userId==""){
		window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
		return;
	}
	
	$("#inputBtn").show();
	$("#srk_box").show();
	itemId = id ;
	
}


var toUserIdString="";

function toUser(toUserId,toUserName){
	
	toUserIdString="&toUserId="+toUserId;
	$("#comment").attr("placeholder","回复  "+toUserName+"：");
}


function fasong(){
	console.log($("#content").val());
	//加载页面方法
	$.ajax({
		url : '/mts/system/oper/update/json?web=&type=8'+"&itemId="+itemId+"&userId="+userId+"&content="+$("#contentValue1").val()+toUserIdString,
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

function hideInput(){
	
	$("#inputBtn").hide();
	$("#srk_box").hide();
	$("#content").val("");
	
}


function dashang(){
	
	var content = $('#contentVal').val();
	
	var money = $('#money').val();
	
	if(userId!=null&&userId!=""&&id!=null&&id!=""&&content!=null&&content!=""&&money!=null&&money!=""){
		//加载页面方法
		$.ajax({
		url : '/mts/system/citycircle/update/json?web=&itemId='+id+"&userId="+userId+"&content="+content+"&money="+money,
		type : "post",
		dataType : "json",
		success : function(result){
			if(result.status=="error"){
				
				window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
				return;
			}
			
			
			
			
			
			
			
			if(payType==3){
				if(result.data!=undefined){
					//加载页面方法
					$.ajax({
					url : '/mts/system/appuser/pay/json?web=&itemId='+id+'&code='+result.data.code+"&userId="+userId+"&type="+5+"&money="+money,
					type : "post",
					dataType : "json",
					success : function(result){
						if(result.status=="error"){
							alert(result.message);
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
			}else if(payType==1){
				window.location.href="/mts/system/zfb/getDingdan/json?name=每天赏打赏&money="+money+"&detail=每天赏打赏&code="+"D"+result.data.code+"_"+new Date().getTime();
			}if(payType==2){
				$.ajax({
					url : '/mts/system/wx/getDingdan/json?web=1&code='+result.data.code+'&payType=D&openid1='+openid+'&total_fee1='+money*100,
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
	
	}else{
		alert("请完善打赏信息");
	}
	
}

window.onscroll=function(){
	var a = document.documentElement.scrollTop==0? document.body.clientHeight : document.documentElement.clientHeight;
	var b = document.documentElement.scrollTop==0? document.body.scrollTop : document.documentElement.scrollTop;
	var c = document.documentElement.scrollTop==0? document.body.scrollHeight : document.documentElement.scrollHeight;
	if(a+b==c){
		nextPage=nextPage+1;
		show(type);
	}
}



var jubaoItemId="";
var reportedUserId="";

function report(obj,reportedUserIdV,jubaoItemIdV){
	if(userId==""){
		window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
		return;
	}
	$(".alert-box").css("top","0");
	$(obj).parents('.more_ul').toggle();
	$(obj).parents('.more_ul').siblings('.arr_up_down').toggle();
	
	reportedUserId=reportedUserIdV;
	jubaoItemId=jubaoItemIdV;
}

function jubao(){
	//加载页面方法
	$.ajax({
	url : '/mts/system/report/update/json?web=&type=2&operUserId='+userId+'&itemId='+jubaoItemId+'&reportedUserId='+reportedUserId+'&content='+$("#content1").val(),
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




function getDateDiff(dateTimeStamp){
	
	dateTimeStamp=GetDateDiff(dateTimeStamp);
	var stringTime = dateTimeStamp;
	var timestamp2 = Date.parse(new Date(stringTime));
	dateTimeStamp = timestamp2 ;
	
	
	var minute = 1000 * 60;
	var hour = minute * 60;
	var day = hour * 24;
	var halfamonth = day * 15;
	var month = day * 30;
	var now = new Date().getTime();
	var diffValue = now - dateTimeStamp;
	if(diffValue < 0){return;}
	var monthC =diffValue/month;
	var weekC =diffValue/(7*day);
	var dayC =diffValue/day;
	var hourC =diffValue/hour;
	var minC =diffValue/minute;
	if(monthC>=1){
		result="" + parseInt(monthC) + "月前";
	}else if(weekC>=1){
		result="" + parseInt(weekC) + "周前";
	}else if(dayC>=1){
		result=""+ parseInt(dayC) + "天前";
	}else if(hourC>=1){
		result=""+ parseInt(hourC) + "小时前";
	}else if(minC>=1){
		result=""+ parseInt(minC) + "分钟前";
	}else{
		result="刚刚";
	}
	return result;
}

function getQueryString(aaa) { 
	var reg = new RegExp("(^|&)" + aaa + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
} 
function showImg(obj){
	 event.stopPropagation(); 
	 swiper.activeIndex=$(obj).index()
	$(".show-img-box .swiper-wrapper").empty()
	$(".show-img-box").toggle()
	$(obj).parents("#images").children().each(function(){
		$(".show-img-box .swiper-wrapper").append("<div class=\"swiper-slide\"><img></div>").find("img:last").attr("src",this.src);
		
	})
}