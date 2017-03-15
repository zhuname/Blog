

function initCity(localtion,provinceId,cityId){
	
	//初始化
	$('#'+localtion+'').html("<div class=\"layui-form-item\"><div class=\"layui-input-inline\"><select id=\"province\"><option value=\"\">请选择省</option></select></div><div class=\"layui-input-inline\"><select id=\"city\"><option value=\"\">请选择市</option></select></div></div>");
	
		$.ajax({
			url : '/mts/system/city/getAreaAdmin/json?level=1',
			secureuri : false,
			dataType : 'json',
			data : {},
			success : function(data, status) {
				var obj1 = eval(data.data);
				
				var pro="<option value=\"\">请选择省</option>";
				
				var cityIdVal = $('#'+cityId+'').val();
				
				var cityFatherId=null;
				
				//有cityId的
				if(cityIdVal!=""){
				
					$.ajax({
						url : '/mts/system/city/lookAdmin/json?id='+cityIdVal,
						secureuri : false,
						dataType : 'json',
						data : {},
						success : function(data, status) {
							cityFatherId=data.data.fatherId;
							
							
							for (var int = 0; int < obj1.length; int++) {
								
								if(obj1[int].id==cityFatherId){
									pro += "<option value='"+obj1[int].id+"'  selected = \"selected\" >"+obj1[int].name+"</option>";
								}else {
									pro += "<option value='"+obj1[int].id+"' >"+obj1[int].name+"</option>";
								}
								$("#province").html(pro);
								
								
							}
							$.ajax({
								url : '/mts/system/city/getAreaAdmin/json?level=2&fatherId='+cityFatherId,
								secureuri : false,
								dataType : 'json',
								data : {},
								success : function(data, status) {
									var obj2 = eval(data.data);
									
									var proC="<option value=\"\">请选择市</option>";
									
									for (var int = 0; int < obj2.length; int++) {
										
										if(obj2[int].id==cityIdVal){
											proC += "<option value='"+obj2[int].id+"'  selected = \"selected\" >"+obj2[int].name+"</option>";
										}else {
											proC += "<option value='"+obj2[int].id+"'>"+obj2[int].name+"</option>";
										}
										
									}
									
									jQuery("#city").html(proC);
								},
								error : function(data, status, e) {
									console.log(data);
								}
							})
							
						}
					});
					
				}else {
					for (var int = 0; int < obj1.length; int++) {
							pro += "<option value='"+obj1[int].id+"'>"+obj1[int].name+"</option>";
					}
					$("#province").html(pro);
				}
				
			},
			error : function(data, status, e) {
				console.log(data);
			}
		});
		
		
	
	
}



$(document).on("change", "#province", function() {
	
	var fatherId=jQuery("#province").val();

    $.ajax({
        url : '/mts/system/city/getAreaAdmin/json?level=2&fatherId='+fatherId,
        secureuri : false,
        dataType : 'json',
        data : {},
        success : function(data, status) {
        	var obj1 = eval(data.data);
        	
        	var pro="<option value=\"\">请选择市</option>";
        	
        	for (var int = 0; int < obj1.length; int++) {
        	
				pro += "<option value='"+obj1[int].id+"'>"+obj1[int].name+"</option>";
				
			}
        	
        	jQuery("#city").html(pro);
        },
        error : function(data, status, e) {
       		console.log(data);
        }
    })


});



