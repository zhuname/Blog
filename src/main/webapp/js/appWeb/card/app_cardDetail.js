var This=this;

$.ajax({
	url : '/mts/system/card/look/json?web=&id='+getQueryString("id"),
	type : "post",
	dataType : "json",
	success : function(result){
		
		if(result.status=="error"){
			window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
			return;
		}
		
		if(result.data!=undefined){
			//获取消息记录
			
			if(result.data.endTime!=undefined){
				//获取消息记录
				
				result.data.endTime=result.data.endTime.substring(0,7);
				
			}
			
			$('#detail_tmpl').tmpl(result.data).appendTo($('#detail'));
			
			
			
			//领取卡券列表
			$.ajax({
				url : '/mts/system/moneydetail/listuser/json?web=&itemId='+result.data.id,
				type : "post",
				dataType : "json",
				success : function(result){
					if(result.status=="error"){
						window.location.href="/mts/appWeb/appuser/appuserLogin.jsp";
						return;
					}
					if(result.data!=undefined){
						for (var int = 0; int < result.data.length; int++) {
							if(int<6){
								
								$('#lingquan_tmpl').tmpl(result.data[int]).appendTo($('#lingquan'));
								
							}
							
						}
						$('#lingquanFoot_tmpl').tmpl(null).appendTo($('#lingquan'));
					}
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					console.log(XMLHttpRequest) ;
					console.log(textStatus) ;
				}
			});
			
			
			
			
			
			
			
			
		}
		
	},
	error:function(XMLHttpRequest, textStatus, errorThrown){
		console.log(XMLHttpRequest) ;
		console.log(textStatus) ;
	}
});


function getQueryString(aaa) { 
	var reg = new RegExp("(^|&)" + aaa + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
} 
