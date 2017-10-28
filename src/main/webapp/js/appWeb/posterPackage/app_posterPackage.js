var This=this;
var nextPage=1;
var dataString="";
var userId;
//初始化页面
show();
function selectCategory(categoryId){
	nextPage=1;
	$('#posterPackage').html("");
	
	var active=$(".active-f");
	for (var int = 0; int < active.length; int++) {
		$(active[int]).attr("class","");
	}
	if(categoryId==null){
		$("#caAll").attr("class","pos_rela active-f clr_r");
	}else{
		$("#ca"+categoryId).attr("class","pos_rela active-f clr_r");
	}
	
	dataString='&categoryId='+categoryId;
	show();
}

function select(){
	var titleString= $('#title').val();
	nextPage=1;
	$('#posterPackage').html("");
	dataString='&title='+titleString;
	show();
}

function selectSort(type){
	nextPage=1;
	$('#posterPackage').html("");
	$('.more_ul').toggle();
	$('.arr_up_down').toggle();
	$(".whte").toggle();
	if(type==1){
		dataString='&selectType=1';
	}else if(type==2){
		dataString='&selectType=2';
	}else if(type==3){
		dataString='&selectType=3';
	}else if(type==4){
		dataString='';
	}
	show();
}

//加载页面方法
function show(){
	
	
	$.ajax({
		url : '/mts/system/appuser/look/json?web=',
		type : "post",
		dataType : "json",
		success : function(result){
			
			
			if(result.status=="error"){
				userId="";
			}else{
				userId=result.data.id;
				userData="&appUserId="+userId;
			}
	
	var data='&pageIndex='+nextPage+dataString+'&cityId='+getQueryString("cityId");
	
	if(userId!=""){
		data=data+"&appUserId="+userId;
	}
	
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
					
					result.data[int].image=result.data[int].image.split(";")[0];
					
					$('#posterPackage_list_tmpl').tmpl(result.data[int]).appendTo($('#posterPackage'));
					
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

$.ajax({
	url : '/mts/system/lunbopic/list/json?web=&position=1&cityIds='+getQueryString("cityId"),
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

var commanda;
var jinruId;
function changeHref(obj,id,command){
	
	if($(obj).find(".jus_bt").children().is("img")){
		$(".pwd").toggle();
	}else{
		window.location.href='/mts/appWeb/posterPackage/posterPackageDetail.jsp?id='+id;
	}
	commanda=command;
	jinruId=id;
}

function jinru(){
	
	alert(11);
	
	if(commanda!=$("#command").val()){
		alert(22);
		alert("请输入正确口令");
		return;
	}
	alert(33);
	window.location.href='/mts/appWeb/posterPackage/posterPackageDetail.jsp?id='+jinruId;
}
