var This=this;
var id;


//获取用户信息
$.ajax({
	url : '/mts/system/syssysparam/list/json?web=',
	type : "post",
	dataType : "json",
	success : function(result){
		
		if(result.status=="error"){
			window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
			return;
		}
		
		if(result.data!=undefined){
			
			$("#appIntroduce").html(result.data.appIntroduce);
			$("#kefuphone").html(result.data.kefuphone);
			$("#apkImg").attr("style", "height: 3.5rem;margin-top: 1.2rem;width: 100%;background: url('"+result.data.apkImg+"') center no-repeat;background-size: 3rem;");
			$("#aboutImage").attr("style", " font-size: .6rem;color: #959595;margin-bottom: 1rem;text-align: center;left: 0;position: fixed;bottom: 0;width: 100%;height: 1rem;background: url('"+result.data.aboutImage+"') center no-repeat;");
			
		}
		
	},
	error:function(XMLHttpRequest, textStatus, errorThrown){
		console.log(XMLHttpRequest) ;
		console.log(textStatus) ;
	}
});



function getQueryString(aaa) { 
	var reg = new RegExp("(^|&)" + aaa + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
} 