var This=this;

function save(){
	if(getCookie("mediaPackageCategoryId")!=null
			&&$('#title').val()!=null&&$('#descr').val()!=null&&$('#appointExplain').val()!=null&&$('#command').val()!=null
			&&$('#title').val()!=""&&$('#descr').val()!=""&&$('#appointExplain').val()!=""&&$('#command').val()!=""){
		setCookie("mediaPackageTitle",$('#title').val());
		setCookie("mediaPackageAppointExplain",$('#appointExplain').val());
		setCookie("mediaPackageDescr",$('#descr').val());
		setCookie("mediaPackageCommand",$('#command').val());
		window.location.href="/mts/appWeb/mediaPackage/mediaPackagePay.jsp";
	}else {
		alert("请完善资料!");
	}
}

function init(){

	if(getCookie("mediaPackageTitle")!=null){
		$('#title').val(getCookie("mediaPackageTitle"));
	}
	
	if(getCookie("mediaPackageDescr")!=null){
		$('#descr').val(getCookie("mediaPackageDescr"));
	}
	
	if(getCookie("mediaPackageAppointExplain")!=null){
		$('#appointExplain').val(getCookie("mediaPackageAppointExplain"));
	}
	
	if(getCookie("mediaPackageCommand")!=null){
		$('#command').val(getCookie("mediaPackageCommand"));
	}
	
	if(getCookie("mediaPackageCategoryId")!=null){
		$('#categoryId').val(getCookie("mediaPackageCategoryId"));
		$('#categoryId').html(getCookie("mediaPackageCategoryName"));
		$('#categoryId').parent().attr("class","dis_f ali_ct");
	}
	if(getCookie("mediaPackageCityId")!=null){
		$('#cityIds').parent().attr("class","dis_f ali_ct");
		$('#cityIds').html(getCookie("mediaPackageCityName"));
		$('#cityIds').val(getCookie("mediaPackageCityId"));
	}
	
	if(getCookie("mediaPackageCardId")!=null){
		$('#cardId').parent().parent().attr("class","pad_2030 f_24 bg_f dis_f ali_ct jus_bt borderbot1 kq_open");
		$('#cardId').html(getCookie("mediaPackageCardName"));
		$('#cardId').val(getCookie("mediaPackageCardId"));
	}
	
	if(getCookie("mediaPackageIsRelevance")!=null){
		$('#cardImg').attr('src', './js/appWeb/images/kai.png');
		$('#cardShow').show();
	}
	
	if(getCookie("mediaPackageIsAppoint")!=null){
		$('#yuyueImg').attr('src', './js/appWeb/images/kai.png');
		$('#yuyueShow').show();
	}
	
	if(getCookie("mediaPackageEncrypt")!=null){
		$('#encryptImg').attr('src', './js/appWeb/images/kai.png');
		$('#encryptShow').show();
	}
	
}
function onCard(){
	if($('#cardImg').attr('src').indexOf('guan')>-1){
		$('#cardImg').attr('src', './js/appWeb/images/kai.png');
		$('#cardShow').show();
		setCookie("mediaPackageIsRelevance", 1);
	}else{
		$('#cardImg').attr('src', './js/appWeb/images/guan.png');
		$('#cardShow').hide();
		setCookie("mediaPackageIsRelevance", 0);
	}
}

function onYuyue(){
	if($('#yuyueImg').attr('src').indexOf('guan')>-1){
		$('#yuyueImg').attr('src', './js/appWeb/images/kai.png');
		$('#yuyueShow').show();
		setCookie("mediaPackageIsAppoint", 1);
	}else{
		$('#yuyueImg').attr('src', './js/appWeb/images/guan.png');
		$('#yuyueShow').hide();
		setCookie("mediaPackageIsAppoint", 0);
	}
}

function onEncrypt(){
	if($('#encryptImg').attr('src').indexOf('guan')>-1){
		$('#encryptImg').attr('src', './js/appWeb/images/kai.png');
		$('#encryptShow').show();
		setCookie("mediaPackageEncrypt", 1);
	}else{
		$('#encryptImg').attr('src', './js/appWeb/images/guan.png');
		$('#encryptShow').hide();
		setCookie("mediaPackageEncrypt", 0);
	}


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
        	$('#mediaUrl').html('<video src="'+data+'" controls="controls">您的浏览器不支持 video 标签。</video>');      	
        },
        error : function(data, status, e) {
       		console.log(data);
            alert('上传出错');
        }
    })

    return false;

});


















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