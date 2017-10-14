
//发送验证码时添加cookie
function addCookie(name,value,expiresHours){ 
    var cookieString=name+"="+escape(value); 
    //判断是否设置过期时间,0代表关闭浏览器时失效
    if(expiresHours>0){ 
        var date=new Date(); 
        date.setTime(date.getTime()+expiresHours*1000); 
        cookieString=cookieString+";expires=" + date.toUTCString(); 
    } 
        document.cookie=cookieString; 
} 
//修改cookie的值
function editCookie(name,value,expiresHours){ 
    var cookieString=name+"="+escape(value); 
    if(expiresHours>0){ 
      var date=new Date(); 
      date.setTime(date.getTime()+expiresHours*1000); //单位是毫秒
      cookieString=cookieString+";expires=" + date.toGMTString(); 
    } 
      document.cookie=cookieString; 
} 
//根据名字获取cookie的值
function getCookieValue(name){ 
      var strCookie=document.cookie; 
      var arrCookie=strCookie.split("; "); 
      for(var i=0;i<arrCookie.length;i++){ 
        var arr=arrCookie[i].split("="); 
        if(arr[0]==name){
          return unescape(arr[1]);
          break;
        }else{
             return ""; 
             break;
         } 
      } 
       
}
//
//
$(function(){
    $("#on").click(function (){
        sendCode($("#on"));
    });
    v = getCookieValue("secondsremained");//获取cookie值
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
        addCookie("secondsremained",60,60);//添加cookie记录,有效时间60s
        settime();//开始倒计时
    }
}
////将手机利用ajax提交到后台的发短信接口
//function doPostBack(url,backFunc,queryParam) {
//  $.ajax({
//      async : false,
//      cache : false,
//      type : 'POST',
//      url : url,// 请求的action路径
//      data:queryParam,
//      error : function() {// 请求失败处理函数
//      },
//      success : backFunc
//  });
//}
//function backFunc1(data){
//  var d = $.parseJSON(data);
//  if(!d.success){
//      alert(d.msg);
//  }else{//返回验证码
//      alert("模拟验证码:"+d.msg);
//      $("#code").val(d.msg);
//  }
//}
//开始倒计时
var countdown;
function settime() { 
    countdown=getCookieValue("secondsremained");
    if (countdown == 0) { 
        $('#on').removeAttr("disabled");    
        $('#on').html("发送验证码");
        return;
    } else { 
        $('#on').prop("disabled", true);
        $('#on').html("重新发送(" + countdown + ")"); 
        countdown--;
        editCookie("secondsremained",countdown,countdown+1);
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
			url : '/mts/system/sms/content/json?type=1&web=&phone='+$('#phonenum').val(),
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
			url : '/mts/system/appuser/update/json?web=&phone='+$("#phonenum").val()+'&name='+$("#user").val()+'&password='+$("#passWord").val()+'&content='+$("#IdenCode").val(),
			type : "post",
			dataType : "json",
			success : function(result){
				debugger;
				if(result.status=="error"){
					alert(result.message);;
					return;
				}
				if(result.data!=undefined){
					console.log(result.data);
					alert(result.message);;
					return;
				}
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


