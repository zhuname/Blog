var This=this;
var cityIds="";
var cityNames="";
$.ajax({
	url : '/mts/system/city/list/json?web=&open=1',
	type : "post",
	dataType : "json",
	success : function(result){
		
		if(result.status=="error"){
			window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
			return;
		}
		
		if(result.data!=undefined){
			//获取消息记录
			
			for (var int = 0; int < result.data.length; int++) {
				
				$('#city_list_tmpl').tmpl(result.data[int]).appendTo($('#'+result.data[int].capital+''));
				$('#'+result.data[int].capital+'').prev().show();
			}
			
			if(getCookie("mediaPackageCityId")!=null){
				var cityIdsStr=getCookie("mediaPackageCityId").split(',');;
				for (var cityIdsStrInt = 0; cityIdsStrInt < cityIdsStr.length; int2++) {
					$('#'+cityIdsStr[cityIdsStrInt]+'').attr('check','1');
					$('#'+cityIdsStr[cityIdsStrInt]+'').attr('src',$('#yes').attr('value'));
					
					//保存数据
					cityNames=getCookie("mediaPackageCityName");
					cityIds=getCookie("mediaPackageCityId");
				}
			}
			
		}
		
	},
	error:function(XMLHttpRequest, textStatus, errorThrown){
		console.log(XMLHttpRequest) ;
		console.log(textStatus) ;
	}
});

function check(id,name){
	if($('#'+id+'').attr('check')==0){
		$('#'+id+'').attr('check','1');
		$('#'+id+'').attr('src',$('#yes').attr('value'));
		cityIds=cityIds+id+",";
		cityNames=cityNames+name+",";
	}else if($('#'+id+'').attr('check')==1){
		$('#'+id+'').attr('check','0');
		$('#'+id+'').attr('src',$('#no').attr('value'));
		cityIds=cityIds.replace(id+",","");
		cityNames=cityNames.replace(name+",","");
	}
}

function checkSucc(){
	setCookie("mediaPackageCityId",cityIds);
	setCookie("mediaPackageCityName",cityNames);
	window.location.href="/mts/appWeb/mediaPackage/mediaPackageSave.jsp";
}

window.onscroll=function(){
	var a = document.documentElement.scrollTop==0? document.body.clientHeight : document.documentElement.clientHeight;
	var b = document.documentElement.scrollTop==0? document.body.scrollTop : document.documentElement.scrollTop;
	var c = document.documentElement.scrollTop==0? document.body.scrollHeight : document.documentElement.scrollHeight;
	if(a+b==c){
		nextPage=nextPage+1;
		show();
	}
}

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
