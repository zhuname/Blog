var This=this;
var nextPage=1;
var dataString;
//初始化页面
show();

function select(){
	console.log(1111);
	var titleString= $('#title').val();
	nextPage=1;
	$('#posterPackage').html("");
	dataString='&title='+title;
	show();
}

function selectSort(){
	console.log(1111);
	var titleString= $('#title').val();
	nextPage=1;
	$('#posterPackage').html("");
	dataString='&title='+title;
	show();
}

//加载页面方法
function show(){
	var data='&pageIndex='+nextPage+dataString;
	
	$.ajax({
		url : '/mts/system/posterpackage/list/json?web=&type=1'+data,
		type : "post",
		dataType : "json",
		success : function(result){
			if(result.status=="error"){
				window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
				return;
			}
			if(result.data!=undefined){
				for (var int = 0; int < result.data.length; int++) {
					
					$('#posterPackage_list_tmpl').tmpl(result.data[int]).appendTo($('#posterPackage'));
					
				}
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			console.log(XMLHttpRequest) ;
			console.log(textStatus) ;
		}
	});
}


$.ajax({
	url : '/mts/system/lunbopic/list/json?web=&position=1&cityId='+getQueryString("cityId"),
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
				$('#lunbo_list_tmpl').tmpl(result.data[int]).appendTo($('#lunbo'));
			}
			
			TouchSlide({
				slideCell:"#bann",
				titCell:".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
				mainCell:".bd ul", 
				effect:"left",
				autoPlay:true,//自动播放
				autoPage:true //自动分页
			});
			
			
		}
		
	},
	error:function(XMLHttpRequest, textStatus, errorThrown){
		console.log(XMLHttpRequest) ;
		console.log(textStatus) ;
	}
});


$.ajax({
	url : '/mts/system/category/list/json?web=&type=1',
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
				
				$('#category_list_tmpl').tmpl(result.data[int]).appendTo($('#category'));
				
				 var swiper = new Swiper('.swiper-container', {
			            slidesPerView : result.data.length
			        });
			}
			
			
		}
		
	},
	error:function(XMLHttpRequest, textStatus, errorThrown){
		console.log(XMLHttpRequest) ;
		console.log(textStatus) ;
	}
});


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
