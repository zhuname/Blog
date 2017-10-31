var This=this;
var lqNum,currentLqNum;

var code=getQueryString("code");
var openid=null;
var unionid = null ;
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
    		window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx8653ea068146c48c&redirect_uri=http://app.mtianw.com/mts/appWeb/appuser/chongzhi.jsp&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
    	}
    } else {
    }  
}); 
	
function chongzhi(){
	
	if(payType==1){
		wx();
	}else{
		zfb();
	}

}


function wx(){
	if($("#money").val()!=""){
		$.ajax({
			url : '/mts/system/wx/getDingdan/json?web=1&payType=R&openid1='+openid+'&total_fee1='+$("#money").val(),
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
	}else{
		alert("请填写充值金额");
	}
}



function zfb(){
	if($("#money").val()!=""){
		
		
		
		
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
				
					
				window.location.href="/mts/system/zfb/getDingdan/json?name=每天赏支付&money="+$("#money").val()+"&detail=每天赏充值余额&code="+"R"+result.data.id+"_"+new Date().getTime();
					
					
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				console.log(XMLHttpRequest) ;
				console.log(textStatus) ;
			}
		});

		
		
	}else{
		alert("请填写充值金额");
	}
}

if (typeof(WeixinJSBridge) == "undefined"){  
if( document.addEventListener ){  
    document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);  
}else if (document.attachEvent){  
    document.attachEvent('WeixinJSBridgeReady', onBridgeReady);  
    document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);  
}  
}else{  
onBridgeReady();  
}  

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