var This=this;
var userId;
var id;
var type;
var cityId;

$(document).ready(function(){ 
	init();
}); 

function init(){
	
	if(getCookie("cardCityId")!=null){
		getCookie("cardCityValue");
		$('#cityId').val(getCookie("cardCityId"));
		$('#cityName').html(getCookie("cardCityName"));
		$($('#cityId').parent()).attr("class","dis_f ali_ct");
	}
	
	if(getCookie("cardTitle")!=null){
		$('#title').val(getCookie("cardTitle"));
	}
	if(getCookie("cardDescr")!=null){
		$('#descr').val(getCookie("cardDescr"));
	}
	if(getCookie("cardConvertMoney")!=null){
		$('#convertMoney').val(getCookie("cardConvertMoney"));
	}
	if(getCookie("cardConvertNum")!=null){
		$('#convertNum').val(getCookie("cardConvertNum"));
	}
	if(getCookie("cardLimitNumber")!=null){
		$('#limitNumber').val(getCookie("cardLimitNumber"));
	}
	if(getCookie("cardAddress")!=null){
		$('#address').val(getCookie("cardAddress"));
	}
	if(getCookie("cardPhone")!=null){
		$('#phone').val(getCookie("cardPhone"));
	}
	if(getCookie("cardImage")!=null){
		$($(".waitCheck")[0]).attr('src',getCookie("cardImage"));
	}
	
	if(getCookie("cardEndTime")!=null){
		
		 $(".datas p:eq(1) span").html(getCookie("cardShowEndTime"));
		 $($(".datas p:eq(1) span").parent()).attr("class","dis_f ali_ct");
		 
	}
	
	if(getCookie("cardLongitude")!=null){
		
		 $("#map").html("已定位");
		 $("#map").attr("class","dis_f ali_ct");
		 
	}
	
	if(getCookie("cardCategoryId")!=null){
		$('#categoryId').val(getCookie("cardCategoryId"));
		$('#categoryId').html(getCookie("cardCategoryName"));
		$('#categoryId').parent().attr("class","dis_f ali_ct");
	}
	
}


function save(){
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
		userId=result.data.id;
		//获取用户信息
		var endTime=getCookie("cardEndTime");
		if(endTime!=undefined){
			endTime=endTime.replace("月", "");
		}
		
		$.ajax({
			url : '/mts/system/card/update/json?web=&osType=html&userId='+userId+"&catergoryId="+getCookie("cardCategoryId")+"&title="+getCookie("cardTitle")+"&images="+getCookie("cardImage")+"&descr="+getCookie("cardDescr")+"&convertMoney="+getCookie("cardConvertMoney")+"&convertNum="+getCookie("cardConvertNum")+"&address="+getCookie("cardAddress")+"&lat="+getCookie("cardLatitude")+"&lot="+getCookie("cardLongitude")+"&phone="+getCookie("cardPhone")+"&limitNumber="+getCookie("cardLimitNumber"),
			type : "post",
			dataType : "json",
			success : function(result){
				if(result.status=="error"){
					window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
					return;
				}
				if(result.data!=undefined){
					if(result.data.balance!=undefined){
						window.location.href="/mts/appWeb/card.jsp/myCard.jsp.jsp";
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
			console.log(XMLHttpRequest);
			console.log(textStatus);
		}
		});
}


function changeTitle(){
	setCookie("cardTitle",$("#title").val());
}
function changeDescr(){
	setCookie("cardDescr",$("#descr").val());
}
function changeConvertMoney(){
	setCookie("cardConvertMoney",$("#convertMoney").val());
}
function changeConvertNum(){
	setCookie("cardConvertNum",$("#convertNum").val());
}
function changeLimitNumber(){
	setCookie("cardLimitNumber",$("#limitNumber").val());
}
function changeAddress(){
	setCookie("cardAddress",$("#address").val());
}
function changePhone(){
	setCookie("cardPhone",$("#phone").val());
}
function changeImages(image){
	setCookie("cardImage",image);
}



function headOnc(){
	jQuery("#filed").click();
}

$(document).on("change", "#filed", function() {
//...

  $.ajaxFileUpload({
        url : '/mts/adminFileUpload',
        secureuri : false,
        fileElementId : 'filed',
        dataType : 'text',
        data : {},
        success : function(data, status) {
        	$($(".waitCheck")[0]).attr('src',data);
        	changeImages(data);
        },
        error : function(data, status, e) {
       		console.log(data);
            alert('上传出错');
        }
    })

    return false;

});


function getDateDiff(dateTimeStamp){
	
	var stringTime = dateTimeStamp;
	var timestamp2 = Date.parse(new Date(stringTime));
	dateTimeStamp = timestamp2 ;
	
	var minute = 1000 * 60;
	var hour = minute * 60;
	var day = hour * 24;
	var halfamonth = day * 15;
	var month = day * 30;
	var now = new Date().getTime();
	var diffValue = now - dateTimeStamp;
	if(diffValue < 0){return;}
	var monthC =diffValue/month;
	var weekC =diffValue/(7*day);
	var dayC =diffValue/day;
	var hourC =diffValue/hour;
	var minC =diffValue/minute;
	if(monthC>=1){
		result="" + parseInt(monthC) + "月前";
	}else if(weekC>=1){
		result="" + parseInt(weekC) + "周前";
	}else if(dayC>=1){
		result=""+ parseInt(dayC) + "天前";
	}else if(hourC>=1){
		result=""+ parseInt(hourC) + "小时前";
	}else if(minC>=1){
		result=""+ parseInt(minC) + "分钟前";
	}else{
		result="刚刚";
	}
	return result;
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