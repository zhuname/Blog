var This=this;
var userId;
var id;
var type;
var cityId;

$(document).ready(function(){ 
	init();
}); 

function init(){
	if(getCookie("activityCityId")!=null){
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


function addAwards(){
	$('#award_tmpl').tmpl(null).appendTo($('#awards'));
}

function closeAward(award){
	$($($($(award).parent()).parent()).parent()).remove();
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
        	$('#images_update_tmpl').tmpl(null).appendTo($('#image'));   
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