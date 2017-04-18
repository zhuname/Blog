//省市县选择插件，
//by：wml

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
				if(cityIdVal!=""&&undefined!=cityIdVal){
				
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
        	
        	if(obj1==undefined){
        		jQuery("#city").html(pro);
        	}
<<<<<<< HEAD
        	
        	for (var int = 0; int < obj1.length; int++) {
=======
>>>>>>> f72b260615a15adda6088f4762671c33b58db4fc
        	
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



//多选城市
function checkCityIds(itemId,tableId){
	var cityId=jQuery("#city").val();
	
	var cityName=$("#city").find("option:selected").text();
	
	var provinceName=$("#province").find("option:selected").text();
	
	if(cityId==null||undefined==cityId||""==cityId){
		return ;
	}
	
	//循环看有没有重复的cityId
	var cityIdsVal=$('#'+itemId+'').val();
	
	var cityIdVal=cityIdsVal.split(",");
	
	for (var int = 0; int < cityIdVal.length; int++) {
		
		if(cityIdVal[int]==cityId){
			return;
		}
		
	}
	
	$('#'+itemId+'').val($('#'+itemId+'').val()+cityId+",");
	
	
	$('#'+tableId+'').append("<tr id="+cityId+"><td>"+provinceName+""+"</td><td>"+cityName+""+"</td><td class=\"deleteCityId\">×</td></tr>");
	
}

$(document).on("click", ".deleteCityId", function() {
	
	
	var id = $(this).parent().attr("id");
	
	var itemId=$(this).parent().parent().attr("class");
	
	var itemValue=$('#'+itemId+'').val();
	
	itemValue=itemValue.replace(id+",","");
	
	$('#'+itemId+'').val(itemValue);
	
	$(this).parent().remove();
	
	
});

function initCitys(tableId,itemId){
	
	var cityIdsVal=$('#'+itemId+'').val();
	
	var cityIdVal=cityIdsVal.split(",");
	
	for (var int = 0; int < cityIdVal.length; int++) {
		
				$.ajax({
					url : '/mts/system/city/look/json?id='+cityIdVal[int],
					secureuri : false,
					dataType : 'json',
					data : {},
					success : function(data, status) {
						
						//这浏览器会报错，但是，并不影响使用！ 	     ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
						$('#'+tableId+'').append("<tr id="+data.data.id+"><td>"+data.data.fatherName+""+"</td><td>"+data.data.name+""+"</td><td class=\"deleteCityId\">×</td></tr>");
						
					},
					error : function(data, status, e) {
						console.log(data);
					}
				})
		
	}
	
}

