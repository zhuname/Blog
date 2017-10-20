var This=this;
var nextPage=1;
var dataString="";
//初始化页面
show();

function selectCategory(categoryId){
	nextPage=1;
	$('#mediaPackage').html("");
	dataString='&categoryId='+categoryId;
	show();
}

function select(){
	var titleString= $('#title').val();
	nextPage=1;
	$('#mediaPackage').html("");
	dataString='&title='+titleString;
	show();
}

function selectSort(type){
	nextPage=1;
	$('#mediaPackage').html("");
	if(type==1){
		dataString='&selectType=1';
	}else if(type==2){
		dataString='&selectType=2';
	}else if(type==3){
		dataString='&selectType=3';
	}
	show();
}

//加载页面方法
function show(){
	var data='&pageIndex='+nextPage+dataString;
	
	$.ajax({
		url : '/mts/system/mediapackage/list/json?web=&status=3&personType=1'+data,
		type : "post",
		dataType : "json",
		success : function(result){
			if(result.status=="error"){
				window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
				return;
			}
			if(result.data!=undefined){
				for (var int = 0; int < result.data.length; int++) {
					
					$('#media_list_tmpl').tmpl(result.data[int]).appendTo($('#mediaPackage'));
					
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
	url : '/mts/system/lunbopic/list/json?web=&position=2&cityIds='+getQueryString("cityId"),
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
				console.log(result.data[int]);
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
	url : '/mts/system/category/list/json?web=&type=2',
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
