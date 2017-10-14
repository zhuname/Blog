var This=this;
var lqNum,currentLqNum;

var code=getQueryString("code");
var openid=null;
var unionid = null ;
$().ready(function(){
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
	//window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx8653ea068146c48c&redirect_uri=http://app.mtianw.com/mts/appWeb/appuser/chongzhi.jsp&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
}
})


function chongzhi(){
	if($("#money").val()!=""){
		$.ajax({
			url : '/mts/system/wx/getDingdan/json?web=1&openid1='+111+'&total_fee1=1',
			type : "get",
			success : function(result) {
				console.log(result);
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