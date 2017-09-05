var This=this;
var userId;

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
				$("#balance").html(result.data.balance);
			}
			userId=result.data.id;
		}
	},
	error:function(XMLHttpRequest, textStatus, errorThrown){
		console.log(XMLHttpRequest) ;
		console.log(textStatus) ;
	}
});


function pay(){
	if(getCookie("mediaPackageCategoryId")!=null
			&&getCookie("mediaPackageTitle")!=null&&getCookie("mediaPackageDescr")!=null
			&&getCookie("mediaPackageTitle")!=""&&getCookie("mediaPackageDescr")!=""
			&&$("#money").val()!=""&&$("#lqNum").val()!=""	){
		var data="";
		if(getCookie("mediaPackageCityId")!=null){
			data+="&cityIds="+getCookie("mediaPackageCityId");
		}
		if(getCookie("mediaPackageAppointExplain")!=null){
			data+="&appointExplain="+getCookie("mediaPackageAppointExplain");
		}
		if(getCookie("mediaPackageCommand")!=null){
			data+="&command="+getCookie("mediaPackageCommand");
		}
		if(getCookie("mediaPackageIsRelevance")!=null){
			data+="&isRelevance="+getCookie("mediaPackageIsRelevance");
		}
		if(getCookie("mediaPackageIsAppoint")!=null){
			data+="&isAppoint="+getCookie("mediaPackageIsAppoint");
		}
		if(getCookie("mediaPackageEncrypt")!=null){
			data+="&encrypt="+getCookie("mediaPackageEncrypt");
		}
			
		//获取用户信息
		$.ajax({
			url : '/mts/system/mediapackage/update/json?web=&sumMoney='+$("#money").val()+"&getNum="+$("#money").val()+"&userId="+userId+"&mediaUrl="+getCookie("mediaPackageImage")+"&categoryId="+getCookie("mediaPackageCategoryId")+data,
			type : "post",
			dataType : "json",
			success : function(result){
				if(result.status=="error"){
					window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
					return;
				}
				if(result.data!=undefined){
					if(result.data.balance!=undefined){
						window.location.href="/mts/appWeb/mediapackage/mymediapackage.jsp";
					}
				}
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				console.log(XMLHttpRequest) ;
				console.log(textStatus) ;
			}
		});
	}else {
		alert("请完善资料!");
	}
}


function getFloatStr(num){  
	num=$("#money").val();
    num += '';
    num = num.replace(/[^0-9|\.]/g, ''); //清除字符串中的非数字非.字符  
    if(/^0+/) //清除字符串开头的0  
        num = num.replace(/^0+/, '');  
    if(!/\./.test(num)) //为整数字符串在末尾添加.00  
        num += '.00';  
    if(/^\./.test(num)) //字符以.开头时,在开头添加0  
        num = '0' + num;  
    num += '00';        //在字符串末尾补零  
    num = num.match(/\d+\.\d{2}/)[0];  
    $("#showMoney").html(num);
};

function getQueryString(aaa) { 
	var reg = new RegExp("(^|&)" + aaa + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
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