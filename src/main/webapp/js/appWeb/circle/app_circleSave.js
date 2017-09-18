var This=this;
var userId;
var nextPage=1;
var id;
var chageType;

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
		var user= result.data;
		
		change(1);
		
		
	},
error:function(XMLHttpRequest, textStatus, errorThrown){
console.log(XMLHttpRequest) ;
console.log(textStatus) ;
}
});

function xinzeng(){
	var data='userId='+userId+'&cityId='+getQueryString('cityId')+'&type='+chageType+'&content='+$('#content').html();
	var url="";
	if($('#content').html()==""||$('#content').html()==null){
		alert('请完善信息');
		return;
	}
	
	if(chageType==1){
		data+="&osType=html&image=";
		var images = document.getElementsByName("images");
		
		for (var int = 0; int < images.length; int++) {
			data+=$(images[int]).attr("src")+";";
		}
	}else if (chageType==2) {
		data+="&osType=html&mediaUrl="+$("#mediaUrl").val();
	}
	
	if(getQueryString('activityId')!=undefined){
		url='/mts/system/circle/update/json?';
	}else{
		url='/mts/system/joinactivity/update/json?';
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

function removeImg(image){
	
	$(image).parent().remove();
	
}

function change(type){
	
	chageType = type ;
	
	if(type==1){
		$("#tupianShow").show();
		$("#shipinShow").hide();
	}else if (type==2){
		$("#shipinShow").show();
		$("#tupianShow").hide();
	}
	
}


function headOnc(){
	jQuery("#filed").click();
}

$(document).on("change", "#filed", function() {
//...

  $.ajaxFileUpload({
        url : '/mts/adminFileUpload' ,
        secureuri : false,
        fileElementId : 'filed' ,
        dataType : 'text' ,
        data : {} ,
        success : function(data, status) {
        	console.log(data);
        	if(chageType==2){
        		$("#shipinShow").html("<video src=\""+data+"\"  style=\"width:100%;\"  controls=\"controls\">您的浏览器不支持 video 标签。</video>");
        		$("#mediaUrl").val(data);
        	}else if(chageType==1){
        		$('#image_tmpl').tmpl({'imag':data}).appendTo($('#imageAdd'));
        	}
        },
        error : function(data, status, e) {
       		console.log(data);
            alert('上传出错');
        }
    });

    return false;

});


function getQueryString(aaa) {
	var reg = new RegExp("(^|&)" + aaa + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
}