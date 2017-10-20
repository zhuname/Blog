var This=this;
var month="";
var isMonth=0;
var nextPage=1;
var chargePercent;
var balance;
var minimumCharge;

var userId;
var withdrawType;
var money;
var branchBank;
var ownerName;
var ownerPhone;
var cardNum;
var bankName;

//初始化页面
show();

//加载页面方法
function show(){
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
					
					$('#money').attr("placeholder","可提现金额为￥"+result.data.balance);
					balance=result.data.balance;
				}
				
				//获取收支记录
				if(result.data.id!=undefined){
					
					userId=result.data.id;
					
					if(result.data.withdrawType!=undefined){
						
						withdrawType=result.data.withdrawType;
						branchBank=result.data.branchBank;
						ownerName=result.data.ownerName;
						ownerPhone=result.data.ownerPhone;
						cardNum=result.data.cardNum;
						bankName=result.data.bankName;
						
						switch (result.data.withdrawType) {
						case 1:
							$("#showType").html("<div>"+result.data.bankName+"    "+result.data.cardNum+"<br />"+result.data.ownerName+"   "+result.data.cardNum+"</div><div class='clr_he'>编辑</div>");
							break;
						case 2:
							$("#showType").html("<div>支付宝 <br/>"+result.data.cardNum+"    "+result.data.cardNum+"   </div><div class='clr_he'>编辑</div>");
							break;
						case 3:
							$("#showType").html("<div>微信 <br/>"+result.data.wxName+"<br/>"+result.data.wxAccount+"    "+result.data.wxPhone+"</div><div class='clr_he'>编辑</div>");
							break;
						}
						
					}
					
					
					//获取用户信息
					$.ajax({
						url : '/mts/system/syssysparam/list/json?web=',
						type : "post",
						dataType : "json",
						success : function(result){
							
							if(result.status=="error"){
								window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
								return;
							}
							
							if(result.data!=undefined){
								
								chargePercent=result.data.chargePercent;
								minimumCharge=result.data.minimumCharge;
								
							}
							
						},
						error:function(XMLHttpRequest, textStatus, errorThrown){
							console.log(XMLHttpRequest) ;
							console.log(textStatus) ;
						}
					});
					
					
				}
				
			}
			
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			console.log(XMLHttpRequest) ;
			console.log(textStatus) ;
		}
	});
}


function changeMoney(){
	
	$("#shouxu").html("￥"+$("#money").val()*chargePercent);
	var dao= $("#money").val()-($("#money").val()*chargePercent);
	$("#daozhangMon").html("￥"+dao);
	console.log($("#money").val());
	console.log($("#money").val()*chargePercent);
	console.log($("#money").val()-($("#money").val()*chargePercent));
	
}

function tixian(){
	
	if($("#money").val()<minimumCharge){
		alert("提现金额最低为"+minimumCharge+"元");
	}
	var data='userId='+userId+"&withdrawType="+withdrawType+"&money="+$("#money").val();
	var url="/mts/system/withdraw/update/json?web=&";
	switch (withdrawType) {
	case 1:
			data=data+'&bankName='+bankName+'&branchBank='+branchBank+'&ownerName='+ownerName+'&cardNum='+cardNum;
		break;
	case 2:
			data=data+'&cardNum='+ownerName+'&ownerName='+cardNum;
		break;
	case 3:
			data=data+'&ownerName='+wxName+'&cardNum='+wxAccount+'&ownerPhone='+wxPhone;
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
			
			window.location.reload();
			
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


function getQueryString(aaa) { 
	var reg = new RegExp("(^|&)" + aaa + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
} 