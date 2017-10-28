var This=this;
var userId;
var id;
var type;
var cityId;


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
		cityId=result.data.cityId;
		},
	error:function(XMLHttpRequest, textStatus, errorThrown){
	console.log(XMLHttpRequest) ;
	console.log(textStatus) ;
	}
});

$(document).ready(function(){ 
	init();
}); 

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
			//获取消息记录
			$('#publishRule').html(result.data.publishRule);
			
		}
		
	},
	error:function(XMLHttpRequest, textStatus, errorThrown){
		console.log(XMLHttpRequest) ;
		console.log(textStatus) ;
	}
});

function init(){
	if(getCookie("activityCityId")!=null){
		getCookie("activityCityValue");
		$('#cityId').val(getCookie("activityCityId"));
		$('#cityName').html(getCookie("activityCityName"));
		$($('#cityId').parent()).attr("class","dis_f ali_ct");
	}
	
	if(getCookie("activityAddress")!=null){
		$('#address').val(getCookie("activityAddress"));
	}
	
	if(getCookie("activityContent")!=null){
		$('#content').val(getCookie("activityContent"));
	}
	
	if(getCookie("activityJoinExplain")!=null){
		$('#joinExplain').val(getCookie("activityJoinExplain"));
	}
	
	if(getCookie("activityPhone")!=null){
		$('#phone').val(getCookie("activityPhone"));
	}
	
	if(getCookie("activityAwards")!=null){
		
		$('#awards').html("");
		
		var activityAwards = JSON.parse(getCookie("activityAwards")); 

		for (var int = 0; int < activityAwards.length; int++) {

			$('#award_init_tmpl').tmpl(activityAwards[int]).appendTo($('#awards'));   

		}

	}


	if(getCookie("activityImages")!=null){
		$('#image').html("");
		var images = getCookie("activityImages").split(";"); 
		for (var int = 0; int < images.length; int++) {
			if(images[int]==""){
				$('#images_update_tmpl').tmpl(null).appendTo($('#image'));
			}else{
				$('#images_update_init_tmpl').tmpl({"image":images[int]}).appendTo($('#image'));
			}
		}
	}
	
	if(getCookie("activityEndTime")!=null){
		
		 $(".datas p:eq(1) span").html(getCookie("activityShowEndTime"));
		 $($(".datas p:eq(1) span").parent()).attr("class","dis_f ali_ct");
		 
	}
	
	if(getCookie("activityLongitude")!=null){
		
		 $("#map").html("已定位");
		 $("#map").attr("class","dis_f ali_ct");
		 
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
		var endTime=getCookie("activityEndTime");
		if(endTime!=undefined){
			endTime=endTime.replace("月", "");
		}
		
		$.ajax({
			url : '/mts/system/activity/update/json?web=&osType=html&type='+1+'&content='+getCookie("activityContent")+'&joinExplain='+getCookie("activityJoinExplain")+'&endTime='+endTime+'&longitude='+getCookie("activityLongitude")+'&latitude='+getCookie("activityLatitude")+'&phone='+getCookie("activityPhone")+'&userId='+userId+'&awards='+getCookie("activityAwards")+'&cityIds='+getCookie("activityCityValue")+'&image='+getCookie("activityImages"),
			type : "post",
			dataType : "json",
			success : function(result){
				if(result.status=="error"){
					window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
					return;
				}
				
				
				
				
				
				if(getCookie("activityCityId")!=null){
					delCookie("activityCityId");
					delCookie("activityCityName");
				}
				
				if(getCookie("activityAddress")!=null){
					delCookie("activityAddress");
				}
				
				if(getCookie("activityContent")!=null){
					delCookie("activityContent");
				}
				
				if(getCookie("activityJoinExplain")!=null){
					delCookie("activityJoinExplain");
				}
				
				if(getCookie("activityPhone")!=null){
					delCookie("activityPhone");
				}
				
				if(getCookie("activityAwards")!=null){
					
					delCookie("activityAwards"); 

				}


				if(getCookie("activityImages")!=null){
					delCookie("activityImages"); 
				}
				
				if(getCookie("activityEndTime")!=null){
					delCookie("activityEndTime")
					delCookie("activityShowEndTime");
					 
				}
				if(getCookie("activityLongitude")!=null){
					 
					 delCookie("activityLongitude");
					 delCookie("activityLatitude");
				}
				
				
				window.location.href="/mts/appWeb/activity/activity.jsp?cityId="+cityId;
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



function changeImages(image){
	if(getCookie("activityImages")!=null){
		setCookie("activityImages",getCookie("activityImages")+image);
	}else {
		setCookie("activityImages",image);
	}
}
function changeAddress(){
	setCookie("activityAddress",$("#address").val());
}
function changeContent(){
	setCookie("activityContent",$("#content").val());
}
function changeJoinExplain(){
	setCookie("activityJoinExplain",$("#joinExplain").val());
}
function changePhone(){
	setCookie("activityPhone",$("#phone").val());
}

function changeAwards(awardsStr){
	setCookie("activityAwards",awardsStr);
}

function awardsSet(){
	var awards=$(".awards");
	var awardsStr="[";
	for (var int = 0; int < awards.length; int++) {
		if(int==awards.length-1){
			awardsStr+=$(awards[int]).attr("value");
		}else{
			awardsStr+=$(awards[int]).attr("value")+",";
		}
	}
	awardsStr+="]";
	changeAwards(awardsStr);
}

function addAwards(){
	$('#award_tmpl').tmpl(null).appendTo($('#awards'));
	awardsSet();
}

function closeAward(award){
	$($($($(award).parent()).parent()).parent()).remove();
	awardsSet();
}

function changeAwardTitle(title){
	var awardStr = $($($($(title).parent()).parent()).parent()).attr("value");
	var award = JSON.parse(awardStr);
	award.title=$(title).val();
	$($($($(title).parent()).parent()).parent()).attr("value",JSON.stringify(award));
	awardsSet();
}

function changeAwardSumCount(sumCount){
	var awardStr = $($($($(sumCount).parent()).parent()).parent()).attr("value");
	var award = JSON.parse(awardStr);
	award.sumCount=$(sumCount).val();
	$($($($(sumCount).parent()).parent()).parent()).attr("value",JSON.stringify(award));
	awardsSet();
}

function changeAwardContent(content){
	var awardStr = $($(content).parent()).attr("value");
	var award = JSON.parse(awardStr);
	award.content=$(content).val();
	$($(content).parent()).attr("value",JSON.stringify(award));
	awardsSet();
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
        	$($(".waitCheck")[0]).attr("class","");
        	changeImages(data+";");
        	
        	var imageNum=0;
        	var images = getCookie("activityImages").split(";"); 
        	
    		for (var int = 0; int < images.length; int++) {
    			if(images[int]==""){
    				imageNum+1;
    			}
    		}
        	
        	if(imageNum!=9){
        		$('#images_update_tmpl').tmpl(null).appendTo($('#image'));   
        	}
        },
        error : function(data, status, e) {
       		console.log(data);
            alert('上传出错');
        }
    })

    return false;

});


function getDateDiff(dateTimeStamp){
	dateTimeStamp=GetDateDiff(dateTimeStamp);
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