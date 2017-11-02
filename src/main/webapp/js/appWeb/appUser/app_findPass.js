
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

//
//
$(function(){
    $("#on").click(function (){
        sendCode($("#on"));
    });
    v = getCookie("sendt");//获取cookie值
    if(v>0){
        settime($("#on"));//开始倒计时
    }
})
//发送验证码
function sendCode(){
    var phonenum = $("#phonenum").val();
    var result = isPhoneNum();
    if(result){
    	sendMessage();
        //doPostBack('/mts/system/sms/content/json',backFunc1,{"phone":phonenum,"web":"","type":1});
    	setCookie("sendt",60);//添加cookie记录,有效时间60s
        console.log(getCookie("sendt"));
        settime();//开始倒计时
    }
}
//开始倒计时
var countdown;
function settime() { 
    countdown=getCookie("sendt");
    if (countdown == 0) { 
        $('#on').removeAttr("disabled");    
        $('#on').html("发送验证码");
        return;
    } else { 
        $('#on').prop("disabled", true);
        $('#on').html("重新发送(" + countdown + ")"); 
        countdown--;
        setCookie("sendt",countdown);
    } 
    setTimeout(function() { settime() },1000) //每1000毫秒执行一次
}




//校验手机号是否合法
function isPhoneNum(){
    var phonenum = $("#phonenum").val();
    var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
    if(!myreg.test(phonenum)){ 
        alert('请输入有效的手机号码！'); 
        return false; 
    }else{
        return true;
    }
}

//发送短信接口
function sendMessage(){
$.ajax({
			url : '/mts/system/sms/content/json?type=4&web=&phone='+$('#phonenum').val(),
			type : "post",
			dataType : "json",
			success : function(result){
				if(result.status=="error"){
					alert(result.message);;
					return;
				}
				if(result.data!=undefined){
					console.log(result.data);
				}
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				alert(result.message);
				console.log(XMLHttpRequest) ;
				console.log(textStatus) ;
			}
		});
}
//注册接口
function checkSignOn(){
	$.ajax({
			url : '/mts/system/appuser/forget/json?web=&phone='+$("#phonenum").val()+'&password='+$("#passWord").val()+'&content='+$("#IdenCode").val(),
			type : "post",
			dataType : "json",
			success : function(result){
				if(result.status=="error"){
					alert(result.message);;
					return;
				}
				window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				console.log(XMLHttpRequest) ;
				console.log(textStatus) ;
			}
		});
}
//注册
function signOn(){
	var user = $("#user").val();
	var passWord = $("#passWord").val();
	var phoneNum = $("#phonenum").val();
	var IdenCode = $("#IdenCode").val();
	if(user!=""&&passWord!= ""&&phoneNum!=""&&IdenCode!=""){
		var register = document.getElementById("register");
		register.style.backgroundColor = "#0084D4";
		//checkSignOn();
		$('#register').removeAttr("disabled");    
    }else{
    	var register = document.getElementById("register");
		register.style.backgroundColor = "#e3e3e6";
		$('#register').prop("disabled", true);
    }
    	
    
}


