var This=this;
var nextPage=1;
var dataString;
var cardId="";
var cardName="";
//初始化页面
show();

//加载页面方法
function show(){
	//加载页面方法
	$.ajax({
	url : '/mts/system/appuser/look/json?web=',
	type : "post",
	dataType : "json",
	success : function(result){
		
		if(result.status=="error"){
			window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
			return;
		}
		
		$.ajax({
			url : '/mts/system/card/list/json?status=2&web=&userId='+result.data.id+"&nextPage="+nextPage,
			type : "post",
			dataType : "json",
			success : function(result){
				if(result.status=="error"){
					window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
					return;
				}
				if(result.data!=undefined){
					for (var int = 0; int < result.data.length; int++) {
						if(result.data.endTime!=undefined){
							//获取消息记录
							
							result.data.endTime=result.data.endTime.substring(0,7);
							
						}
						$('#card_list_tmpl').tmpl(result.data[int]).appendTo($('#list'));
						
					}
					
					var imagess=document.getElementsByName("image");
					if(imagess.length>0){
						$(imagess[0]).click();
					}
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
}


function check(id,name){
	if($('#'+id+'').attr('src').indexOf('no')>-1){
		var images=document.getElementsByName("image");
		for (var int = 0; int < images.length; int++) {
			console.log(images[int]);
			$(images[int]).attr('src', './js/appWeb/images/no.png');
		}
		$('#'+id+'').attr('src', './js/appWeb/images/yes.png');
		$('#'+id+'').parents('.baba').siblings('.baba').find('.switcher_click').attr('src', './js/appWeb/images/no.png');
		cardId=id;
		cardName=name;
	}else{
		$('#'+id+'').attr('src', './js/appWeb/images/no.png');
		cardId="";
		cardName="";
	}
}

function checkSuccess(){
	setCookie("posterPackageCardId",cardId);
	setCookie("posterPackageCardName", cardName);
	window.location.href="/mts/appWeb/posterPackage/posterPackageSave.jsp";
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
