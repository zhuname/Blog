var This=this;
var withdrawType=1;
var bankId;
var bankName="";
var id;

$(document).ready(function(){ 
	
	
	
	changeType(withdrawType);
	
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
				
				//获取收支记录
				if(result.data.id!=undefined){
					
					//获取用户信息
					
					id=result.data.id;
					
					
					if(result.data.withdrawType!=undefined){
						
						changeType(result.data.withdrawType);
						
						switch (result.data.withdrawType) {
						case 1:
							$("#branchBank").val(result.data.branchBank);
							$("#ownerName").val(result.data.ownerName);
							$("#ownerPhone").val(result.data.ownerPhone);
							$("#cardNum").val(result.data.cardNum);
							$("#bankName").html(result.data.bankName);
							bankName=result.data.cardNum;
							break;
						case 2:
							$("#ownerNameA").val(result.data.ownerName);
							$("#cardNumA").val(result.data.cardNum);
							break;
						case 3:
							$("#wxName").val(result.data.wxName);
							$("#wxAccount").val(result.data.wxAccount);
							$("#wxPhone").val(result.data.wxPhone);
							break;
						}
						
					}
					
				}
				
			}
			
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			console.log(XMLHttpRequest) ;
			console.log(textStatus) ;
		}
	});
	
	
	
	
	$.ajax({
		url : '/mts/system/bank/list/json?web=&',
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
					
				}
				
			}
			
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			console.log(XMLHttpRequest) ;
			console.log(textStatus) ;
		}
	});
	
}); 


function check(id,name){
	$("#bankName").html(name);
	bankId=id;
	bankName=name;
	changeType(1);
}


function changeType(type){
	withdrawType=type;
	switch (type) {
	case 1:
		$("#showSave").show();
		$("#showBank").show();
		$("#showAli").hide();
		$("#showWx").hide();
		$('#showCheck').hide();
		$('#changeName').html("银行卡");
		$("#showBankl").hide();
		break;
	case 2:
		$("#showSave").show();
		$("#showAli").show();
		$("#showBank").hide();
		$("#showWx").hide();
		$('#showCheck').hide();
		$('#changeName').html("支付宝");
		$("#showBankl").hide();
		break;
	case 3:
		$("#showSave").show();
		$("#showWx").show();
		$("#showBank").hide();
		$('#showCheck').hide();
		$("#showAli").hide();
		$('#changeName').html("微信");
		$("#showBankl").hide();
		break;
	case 4:
		$("#showBankl").show();
		$("#showSave").hide();
		$("#showWx").hide();
		$("#showBank").hide();
		$('#showCheck').hide();
		$("#showAli").hide();
		$('#changeName').html("微信");
		break;
	}
}

function xinzeng(){
	var data='id='+id+"&withdrawType="+withdrawType;
	var url="/mts/system/appuser/update/json?web=&";
	switch (withdrawType) {
	case 1:
		if(bankName!=""&&$("#branchBank").val()!=""&&$("#ownerName").val()!=""&&$("#ownerPhone").val()!=""&&$("#cardNum").val()!=""){
			data=data+'&bankName='+bankName+'&branchBank='+$("#branchBank").val()+'&ownerName='+$("#ownerName").val()+'&ownerPhone='+$("#ownerPhone").val()+'&cardNum='+$("#cardNum").val();
		}else{
			alert("请完善信息！");
		}
		break;
	case 2:
		if($("#ownerNameA").val()!=""&&$("#cardNumA").val()!=""){
			data=data+'&cardNum='+$("#ownerNameA").val()+'&ownerName='+$("#cardNumA").val();
		}else{
			alert("请完善信息！");
		}
		break;
	case 3:
		if($("#wxName").val()!=""&&$("#wxAccount").val()!=""&&$("#wxPhone").val()!=""){
			data=data+'&wxName='+$("#wxName").val()+'&wxAccount='+$("#wxAccount").val()+'&wxPhone='+$("#wxPhone").val();
		}else{
			alert("请完善信息！");
		}
		break;
	}
	
	url=url+data;
	
	$.ajax({
		url : url,
		
		type : "post",
		
		dataType : "json",
		
		success : function(result){
			
			if(result.status=="error"){
				alert(result.message);
				return;
			}
			
			javascript:window.history.back();
			
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			console.log(XMLHttpRequest) ;
			console.log(textStatus) ;
		}
	});
	
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

function getQueryString(aaa){ 
	var reg = new RegExp("(^|&)" + aaa + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
} 