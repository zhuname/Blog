/**
 * Micheal   javascript 封装
 * 级联菜单的插件，用于多级别
 * names长度可以大于level但是只初始化level数量的下拉列表
 * values长度大于names则只初始化names.length个值，小于names则初始化values.length个值
 *
 */
(function(This){

    This.pathName = window.document.location.pathname ,
	This.path = pathName.substring(0,pathName.substr(1).indexOf('/')+1),

    This.linkage= {

    	names:["provinceId","cityId","areaId"],
		level:3,
		url:"/system/city/getAreaAdmin/json",
        values:["","",""],
        /**
         * 初始化一个联动下拉框
         * @param id 装载联动的容器id(必填)
         * @param level 联动级别,默认为3
         * @param name 每个select的id和name，数组。默认["provinceId","cityId","areaId"]
         * @param values 每个select的value，数组。默认["","",""]
         * @param url ajax请求的url,不需带根,默认省市县接口:"/system/city/getAreaAdmin/json"
         */
        init: function (id, level,names,values,url) {

            if (id == undefined || id == "") {
				alert("请设置id");
				return ;
			}
            $('#' + id + '').empty() ;

        	//将用户自定义的names覆盖默认的
            if (names == undefined || names == null ){
                names = this.names ;
			}
            //将用户自定义的level覆盖默认的
			if (level == undefined || level == null){
            	level = this.level ;
			}

			if (level > names.length){
				alert("请配置正确数量的names") ;
				return ;
			}
			if (values == undefined || values == null){
			    values = this.values ;
            }
            if (url == undefined || url == null){
                url = this.url ;
            }

            //联动下拉列表的模板
            var model = "<span>";
            for (var i=0 ; i<level ; i++){
            	model += "<span ><select id='"+names[i]+"' name='"+names[i]+"' level='"+i+"'style='margin-left: 7px;'class='thisSB' ><option value=\"\">全部</option></select></span>"
			}
			model += "</span>" ;
			$('#' + id + '').html(model);


            //初始化level1的数据
            $.ajax({
                url : This.path + url ,
                secureuri : false,
                dataType : 'json',
                data : {level:1},
                success : function(data, status) {

                	var data = eval(data.data) ;
                    var level1="<option value=\"\">全部</option>";
                    for (var int = 0; int < data.length; int++) {
                        level1 += "<option value='"+data[int].id+"' >"+data[int].name+"</option>";
                    }
                    $('#' + names[0] + '').html(level1);
                    $('#' + names[0] + '').val(values[0]) ;
                },
                error : function(data, status, e) {
                    console.log(data);
                }
            });
            values = eval(values) ;
            var setFlag = false ;  //立个flag，用来如果出现第一个空值，这个level的option还是应该有的，但是其后边的level就可以为空option了
            if (values.length > 0){
                //从位置2开始循环，因为位置1上边已经初始化过了。应该以names昨晚循环点，毕竟是以names为基础
                for (var val = 1 ;val < names.length ; val++){
                    //当上一级是空的时候，以后的级别都应该是空的。此刻就可以退出了
                    if (values[val-1] == '' || values[val-1] == undefined){
                        if(setFlag)
                            break ;
                        setFlag = true ;
                    }
                    //如果初始化值的length大于level，后边的也不用再初始化了
                    if (val+1 > level)
                        break ;
                    $.ajax({
                        url : This.path + url ,
                        secureuri : false,
                        dataType : 'json',
                        async:false,  //同步
                        data : {level:val+1,
                            fatherId:values[val-1]},
                        success : function(data, status) {

                            var data = eval(data.data) ;
                            var option="<option value=\"\">全部</option>";
                            for (var int = 0; int < data.length; int++) {
                                option += "<option value='"+data[int].id+"' >"+data[int].name+"</option>";
                            }
                            $('#' + names[val] + '').html(option);
                            $('#' + names[val] + '').val(values[val]) ;
                        },
                        error : function(data, status, e) {
                            console.log(data);
                        }
                    });
                }
            }

            //各个级别的下拉框的change监听
			$(document).on("change",".thisSB",function () {
				var brothers = $(this).parent().parent().find(".thisSB");
				var level  = $(this).attr('level') ;  //当前select的级别
				var chooseId = $(this).val()

				//先把之后的select都empty掉
				for(var i = 0 ; i< brothers.length ;i++){
					if (i>level){
						$(brothers[i]).empty() ;
						$(brothers[i]).append("<option value=\"\">全部</option>") ;
					}
				}
				//如果当前级别选择是全部，那么就直接return就ok了
				if (chooseId == null || chooseId == '')
				    return ;
				//展示下一级的option
                $.ajax({
                    url : This.path + url ,
                    secureuri : false,
                    dataType : 'json',
                    data : {level:Number(level)+2,  //级别
                        fatherId:chooseId}, //上级id
                    success : function(data, status) {
                        var data = eval(data.data)  ;
                        var option="<option value=\"\">全部</option>";
                        for (var int = 0; int < data.length; int++) {
                            option += "<option value='"+data[int].id+"' >"+data[int].name+"</option>";
                        }
                        $('#' + names[Number(level)+1] + '').html(option);
                    },
                    error : function(data, status, e) {
                        console.log(data);
                    }
                });

            });

        }


    }



})(window);