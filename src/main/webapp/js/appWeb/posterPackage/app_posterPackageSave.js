var This=this;

function save(){
	if(getCookie("posterPackageCardId")!=null&&getCookie("posterPackageCategoryId")!=null&&getCookie("posterPackageCityId")
			&&$('#title').val()!=null&&$('#descr').val()!=null&&$('#appointExplain').val()!=null&&$('#command').val()!=null
			&&$('#title').val()!=""&&$('#descr').val()!=""&&$('#appointExplain').val()!=""&&$('#command').val()!=""){
		setCookie("posterPackageTitle",$('#title').val());
		setCookie("posterPackageAppointExplain",$('#appointExplain').val());
		setCookie("posterPackageDescr",$('#descr').val());
		setCookie("posterPackageCommand",$('#command').val());
		window.location.href="/mts/appWeb/posterPackage/posterPackagePay.jsp";
	}else {
		alert("请完善资料!");
	}
}

function init(){

	if(getCookie("posterPackageTitle")!=null){
		$('#title').val(getCookie("posterPackageTitle"));
	}
	
	if(getCookie("posterPackageDescr")!=null){
		$('#descr').val(getCookie("posterPackageDescr"));
	}
	
	if(getCookie("posterPackageAppointExplain")!=null){
		$('#appointExplain').val(getCookie("posterPackageAppointExplain"));
	}
	
	if(getCookie("posterPackageCommand")!=null){
		$('#command').val(getCookie("posterPackageCommand"));
	}
	
	if(getCookie("posterPackageCategoryId")!=null){
		$('#categoryId').val(getCookie("posterPackageCategoryId"));
		$('#categoryId').html(getCookie("posterPackageCategoryName"));
		$('#categoryId').parent().attr("class","dis_f ali_ct");
	}
	if(getCookie("posterPackageCityId")!=null){
		$('#cityIds').parent().attr("class","dis_f ali_ct");
		$('#cityIds').html(getCookie("posterPackageCityName"));
		$('#cityIds').val(getCookie("posterPackageCityId"));
	}
	
	if(getCookie("posterPackageCardId")!=null){
		$('#cardId').parent().parent().attr("class","pad_2030 f_24 bg_f dis_f ali_ct jus_bt borderbot1 kq_open");
		$('#cardId').html(getCookie("posterPackageCardName"));
		$('#cardId').val(getCookie("posterPackageCardId"));
	}
	
	if(getCookie("posterPackageIsRelevance")!=null){
		$('#cardImg').attr('src', './js/appWeb/images/kai.png');
		$('#cardShow').show();
	}
	
	if(getCookie("posterPackageIsAppoint")!=null){
		$('#yuyueImg').attr('src', './js/appWeb/images/kai.png');
		$('#yuyueShow').show();
	}
	
	if(getCookie("posterPackageEncrypt")!=null){
		$('#encryptImg').attr('src', './js/appWeb/images/kai.png');
		$('#encryptShow').show();
	}
	
}
function onCard(){
	if($('#cardImg').attr('src').indexOf('guan')>-1){
		$('#cardImg').attr('src', './js/appWeb/images/kai.png');
		$('#cardShow').show();
		setCookie("posterPackageIsRelevance", 1);
	}else{
		$('#cardImg').attr('src', './js/appWeb/images/guan.png');
		$('#cardShow').hide();
		setCookie("posterPackageIsRelevance", 0);
	}
}

function onYuyue(){
	if($('#yuyueImg').attr('src').indexOf('guan')>-1){
		$('#yuyueImg').attr('src', './js/appWeb/images/kai.png');
		$('#yuyueShow').show();
		setCookie("posterPackageIsAppoint", 1);
	}else{
		$('#yuyueImg').attr('src', './js/appWeb/images/guan.png');
		$('#yuyueShow').hide();
		setCookie("posterPackageIsAppoint", 0);
	}
}

function onEncrypt(){
	if($('#encryptImg').attr('src').indexOf('guan')>-1){
		$('#encryptImg').attr('src', './js/appWeb/images/kai.png');
		$('#encryptShow').show();
		setCookie("posterPackageEncrypt", 1);
	}else{
		$('#encryptImg').attr('src', './js/appWeb/images/guan.png');
		$('#encryptShow').hide();
		setCookie("posterPackageEncrypt", 0);
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
        	$(".header")[0].attr('src',data);
        	$(".header")[0].Attr("class","dis_b");
        	$('#images_update_tmpl').tmpl(null).appendTo($('#image'));      	
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