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
			
			if(getCookie("cardCityId")!=null){
				var cityIdsStr=getCookie("cardCityId").split(',');;
				for (var cityIdsStrInt = 0; cityIdsStrInt < cityIdsStr.length; cityIdsStrInt++) {
					
					$('#'+cityIdsStr[cityIdsStrInt]+'').attr('check','1');
					$('#'+cityIdsStr[cityIdsStrInt]+'').attr('src',$('#yes').attr('value'));
					
					//保存数据
					cityNames=getCookie("cardCityName");
					cityIds=getCookie("cardCityId");
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
	setCookie("cardCityId",cityIds);
	setCookie("cardCityName",cityNames);
	
	var cityIdsStr=getCookie("cardCityId").split(',');
	var cityValue="[";
	for (var cityIdsStrInt = 0; cityIdsStrInt < cityIdsStr.length; cityIdsStrInt++) {
		
		if(cityIdsStr[cityIdsStrInt]!=""){
		
			if(cityIdsStrInt==cityIdsStr.length-1){
				//保存数据
				cityValue=cityValue+"{\"cityId\":"+cityIdsStr[cityIdsStrInt]+",\"type\":1}";
			}else{
				cityValue=cityValue+"{\"cityId\":"+cityIdsStr[cityIdsStrInt]+",\"type\":1},";
			}
		
		}
	}
	
	cityValue=cityValue+"]";
	setCookie("cardCityValue",cityValue);
	window.location.href="/mts/appWeb/card/cardSave.jsp";
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
