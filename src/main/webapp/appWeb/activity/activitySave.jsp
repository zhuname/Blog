<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Object data=session.getAttribute("data");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<meta http-equiv="Cache-Control" content="no-transform" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta name="format-detection" content="telephone=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">  
<meta name="app-mobile-web-app-capable" content="yes">  
<meta name="viewport"
	content="width=device-width,target-densitydpi=high-dpi,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />

<script type="text/javascript"
	src="<%=basePath%>/js/appWeb/weixinjs/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/js/appWeb/weixinjs/TouchSlide.1.1.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/js/appWeb/weixinjs/global_phone.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/js/appWeb/weixinjs/jquery.tmpl.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/js/appWeb/css/css.css" />
<script src="<%=basePath%>/js/jquery/ajaxfileupload.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/js/appWeb/activity/app_activitySave.js"></script>
<script src="<%=basePath%>/js/appWeb/weixinjs/swiper.min.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/appWeb/css/swiper.min.css" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0"
	name="viewport">
<title>首页</title>
<style>
body{background: #f0f2f5;}


 div, ul, li, p, span, p {
            padding: 0;
            margin: 0;
            list-style: none;
            border: none;
        }

        .alertBox {
            width: 100%;
            position: fixed;
            height: 100%;

            top: 0;
            left: 0;
            display: none;
        }

        .box {
            width: 10.8rem;
            height: 19.6rem;
            margin: 3rem auto;
            background: white;
        }

        .timeTop {
            width: 100%;
            background: #009788;
            text-align: center;
            height: 7.2rem;
        }

        .timeTop p:first-child {
            font-size: .4rem;
            background: #008c7f;
            line-height: 1.2rem;
            color: white;
        }

        .timeTop p:nth-of-type(2) {
            font-size: .8rem;
            line-height: 1.96rem;
            color: #b2e3ea;
        }

        .timeTop p:nth-of-type(3) {
            font-size: .8rem;
            color: white;
            line-height: normal;
        }

        .timeTop p:nth-of-type(4) {
            font-size: 2rem;
            color: white;
            margin-top: .66rem;
            line-height: 2rem;
        }

        .timeBox {
            width: 100%;
            height: 9.8rem;
            position: relative;
            overflow-y: scroll;
        }

        .timeBox ul {
            position: absolute;
        }

        .timeBox ul li {
            width: 100%;
        }

        .timeBox li p {
            text-align: center;
            line-height: 1.06rem;
            font-weight: bold;
            color: #585858;
        }

        .timeBox li span {
            line-height: 1.2rem;
            text-align: center;
            width: 1.2rem;
            margin: 0 0.17rem;
            display: inline-block;
        }

        .alertBox .week {
            display: flex;
            line-height: 1.06rem;
            color: #585858;
        }

        .alertBox b {
            font-style: normal;
            flex: 1;
            font-size: .1rem;
            text-align: center;
        }

        .foot {
            padding-top: .8rem;
            color: #009788;
        }

        .nos {
            float: right;
            margin-right: 1rem
        }

        .foot .oks {
            float: right;
            margin-right: .6rem;
        }
        .backBox{
            position: absolute;
            z-index: -1;
            width: 100%;
            height: 100%;
            background: rgba(40, 40, 40, 0.6);
        }
</style>
</head>

<body>

	<div class="wraper overh">
		<div class="dis_f ali_ct jus_bt pad_20 bg_f borderbot1">
			<a onclick="javascript:window.history.back();"><img src="<%=basePath%>/js/appWeb/images/back.png" class="dis_b" style="width:1rem;" /></a>
			<p class="f_30 clr_3">发布同城活动</p>
			<a ><img src="<%=basePath%>/js/appWeb/images/gantan.png" class="dis_b" style="width:1rem;" /></a>
	
		</div>


<%-- 	<div class="bg_f dis_f ali_ct jus_bt" style="padding:0.75rem 2.5rem;">
		<div class="dis_f ali_ct f_24 clr_3 baba">
			<img src="<%=basePath%>/js/appWeb/images/image.png" class="dis_b" style="width:1rem;" /> 
			<div style="margin:0 0.5rem;">图片</div>
			<img src="<%=basePath%>/js/appWeb/images/check_yes.png" class="dis_b check_img" style="width:0.65rem;height:0.65rem;" /> 
		</div>

		<div class="dis_f ali_ct f_24 clr_3 baba">
			<img src="<%=basePath%>/js/appWeb/images/video2.png" class="dis_b" style="width:1rem;" /> 
			<div style="margin:0 0.5rem;">视频</div>
			<img src="<%=basePath%>/js/appWeb/images/check_no.png" class="dis_b check_img" style="width:0.65rem;" /> 
		</div>

		<script>
			$('.check_img').click(function(){
				if($(this).attr('src').indexOf('no')>-1){
					$(this).attr('src', '<%=basePath%>/js/appWeb/images/check_yes.png');
					$(this).parents('.baba').siblings('.baba').find(".check_img").attr('src', '<%=basePath%>/js/appWeb/images/check_no.png');
				}else{
					$(this).attr('src', '<%=basePath%>/js/appWeb/images/check_no.png');
				}
			});
		</script>
	</div> --%>

	<div class="bg_f pad_2030 bordertop1" id="image">
			<img onclick="headOnc();" src="<%=basePath%>/js/appWeb/images/xztp.png" class=" waitCheck" style="width:4.75rem;" />
	</div>
	<input type="file" id="filed" name="filed" style="display:none">

	<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt baba bordertop1">
			<p class="dis_f ali_ct">
			<img src="<%=basePath%>/js/appWeb/images/zt.png" class="dis_b" style="height:1rem;margin-right: 0.5rem;" />
			主题
			</p>
			<input class="ipt3 al_rt f_28 ph_red" type="text" onchange="changeContent()" id="content" placeholder="请填写图片主题，20字以内" style="width:9rem;" />
		</div>

	<div class="pad_30 borderbot1 bg_f f_24 clr_3 dis_f ali_ct jus_bt">
		<p class="dis_f ali_ct">
		<img src="<%=basePath%>/js/appWeb/images/cup.png" class="dis_b" style="width:0.75rem;margin-right: 0.5rem;" />
		奖项设置
		</p>
		<a href="javascript:addAwards();" class="f_22 clr_r dis_b" style="border:1px solid #f95d47;padding:0.2rem 0.5rem;border-radius: 0.2rem;">添加一项奖励</a>
	</div>
	
	<div id="awards">
	<div class="pad_30 borderbot1 bg_f awards" value='{"title": "","content":"","sumCount": 1,"osType": "html"}'>
		<div class=" f_24 clr_3 dis_f ali_ct jus_bt">
			<p class="dis_f ali_ct">
			<img onclick="closeAward(this);" src="<%=basePath%>/js/appWeb/images/close3.png" class="dis_b" style="width:0.75rem;margin-right: 0.5rem;" />
			<input onchange="changeAwardTitle(this)"; class="ipt3 f_26 clr_6" placeholder="请输入奖励标题(限8字)" type="text" />
			</p>

			<div class="dis_f ali_ct">
				<img src="<%=basePath%>/js/appWeb/images/reduce.png" onclick="reduce(this);" class="dis_b reduce_click" style="width:0.8rem;" />
				<input class="ipt3 al_ct ipt_num f_22" value="1" type="text" style="width:1.5rem;" />
				<img src="<%=basePath%>/js/appWeb/images/plus.png" onclick="plus(this);" class="dis_b plus_click" style="width:0.8rem;" /> 
			</div>

		</div>
		<input class="ipt3 f_24 clr_6 mt_20 w_100" onchange="changeAwardContent(this)" placeholder="请填写奖励内容" type="text" />

	</div>
	
	</div>


	<div class="pad_30 borderbot1 bg_f f_24 clr_3">
		<div class="borderbot1" style="padding-bottom: 0.5rem;">
			<p class="dis_f ali_ct">
			<img src="<%=basePath%>/js/appWeb/images/cysm2.png" class="dis_b " style="width:0.6rem;margin-right: 0.5rem;" />
			参与说明
			</p>
		</div>
		<textarea class="ipt3 f_24 clr_6 mt_20 w_100" onchange="changeJoinExplain();" id="joinExplain" placeholder="请填写奖励内容" type="text" ></textarea>
	</div>

	<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt datas">
        <p class="dis_f ali_ct">
            <img src="<%=basePath%>/js/appWeb/images/rq.png" class="dis_b" style="height:1rem;margin-right: 0.5rem;"/>
            日期
        </p>
        <p class="dis_f ali_ct clr_ph_red">
            <span>请选择截止日期</span>
            <img src="<%=basePath%>/js/appWeb/images/arr_he2.png" class="dis_b" style="height:0.6rem;margin-left: 0.5rem;"/>
        </p>

    </div>

 <!--弹出时间-->
    <div class="alertBox" id="showDate">
        <div class="backBox"></div>
        <div class="box">
            <div class="timeTop">
                <p></p>
                <p></p>
                <p></p>
                <p></p>
            </div>
            <div class="timeBox">
                <ul>
                </ul>
            </div>
            <div class="foot">
                <div class="oks">确定</div>
                <div class="nos">取消</div>
            </div>
        </div>
    </div>


<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt">
	<p class="dis_f ali_ct">
		<img src="<%=basePath%>/js/appWeb/images/cs2.png" class="dis_b" style="height:1rem;margin-right: 0.5rem;" />
		城市
	</p>
	<p class="dis_f ali_ct clr_ph_red" onclick="window.location.href='/mts/appWeb/activity/activityCity.jsp';">
		
		<a id="cityId"></a>
		<a id="cityName">选择况换点所在城市</a>
		<img src="<%=basePath%>/js/appWeb/images/arr_he2.png" class="dis_b" style="height:0.6rem;margin-left: 0.5rem;" />
	</p>
</div>

<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt">
	<p class="dis_f ali_ct">
		<img src="<%=basePath%>/js/appWeb/images/dz2.png" class="dis_b" style="height:1rem;margin-right: 0.5rem;" />
		地址
	</p>
	<input class="ipt3 al_rt clr_9 f_28 ph_red" type="text" id="address" onchange="changeAddress();" placeholder="请填写用户使用地址" />
</div>

<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt">
	<p class="dis_f ali_ct">
		<img src="<%=basePath%>/js/appWeb/images/dw.png" class="dis_b" style="height:1rem;margin-right: 0.5rem;" />
		定位
	</p>
	<p id="map" class="dis_f ali_ct clr_ph_red"  onclick="window.location.href='/mts/appWeb/activity/baiduMap.jsp';" >
		点击进行地图定位
	</p>
</div>

<div class="pad_30 borderbot1 bg_f f_28 clr_3 dis_f ali_ct jus_bt">
	<p class="dis_f ali_ct">
		<img src="<%=basePath%>/js/appWeb/images/dh.png" class="dis_b" style="height:1rem;margin-right: 0.5rem;" />
		电话
	</p>

	<input class="ipt3 al_rt clr_9 f_28 ph_red" type="text" id="phone" onchange="changePhone();" placeholder="请填写联系电话" />
</div>

		<div class="pad_30">
			<input type="button" onclick="save();" class="f_26 clr_f dis_b waiting_check_a" style="background: #f95d47;border:0;" value="发布活动" />
		</div>


	<script id="award_tmpl" type="text/x-jquery-tmpl">
		<div class="pad_30 borderbot1 bg_f awards" value='{"title": "","content":"","sumCount": 1,"osType": "html"}'>
		<div class=" f_24 clr_3 dis_f ali_ct jus_bt">
			<p class="dis_f ali_ct">
			<img onclick="closeAward(this);" src="<%=basePath%>/js/appWeb/images/close3.png" class="dis_b" style="width:0.75rem;margin-right: 0.5rem;" />
			<input onchange="changeAwardTitle(this)"; class="ipt3 f_26 clr_6" placeholder="请输入奖励标题(限8字)" type="text" />
			</p>

			<div class="dis_f ali_ct">
				<img src="<%=basePath%>/js/appWeb/images/reduce.png" onclick="reduce(this);" class="dis_b reduce_click" style="width:0.8rem;" />
				<input class="ipt3 al_ct ipt_num f_22" value="1" type="text" style="width:1.5rem;" />
				<img src="<%=basePath%>/js/appWeb/images/plus.png" onclick="plus(this);" class="dis_b plus_click" style="width:0.8rem;" /> 
			</div>

		</div>
		<input class="ipt3 f_24 clr_6 mt_20 w_100" onchange="changeAwardContent(this)" placeholder="请填写奖励内容" type="text" />

	</div>
	</script>
	
	
	<script id="award_init_tmpl" type="text/x-jquery-tmpl">
		<div class="pad_30 borderbot1 bg_f awards" value='{"title": "{{= title}}","content":"{{= content}}","sumCount":{{= sumCount}},"osType": "html"}'>
		<div class=" f_24 clr_3 dis_f ali_ct jus_bt">
			<p class="dis_f ali_ct">
			<img onclick="closeAward(this);" src="<%=basePath%>/js/appWeb/images/close3.png" class="dis_b" style="width:0.75rem;margin-right: 0.5rem;" />
			<input onchange="changeAwardTitle(this)"; class="ipt3 f_26 clr_6" placeholder="请输入奖励标题(限8字)" type="text" value="{{= title}}"/>
			</p>
			<div class="dis_f ali_ct">
				<img src="<%=basePath%>/js/appWeb/images/reduce.png" onclick="reduce(this);" class="dis_b reduce_click" style="width:0.8rem;" />
				<input class="ipt3 al_ct ipt_num f_22" value="{{= sumCount}}" type="text" style="width:1.5rem;" />
				<img src="<%=basePath%>/js/appWeb/images/plus.png" onclick="plus(this);" class="dis_b plus_click" style="width:0.8rem;" /> 
			</div>
		</div>
		<input class="ipt3 f_24 clr_6 mt_20 w_100" onchange="changeAwardContent(this)" placeholder="请填写奖励内容" value="{{= content}}" type="text" />
		</div>
	</script>

					<script>
						function reduce(reduce){
							if($(reduce).siblings('input').val()>1){
									$(reduce).siblings('input').val(parseInt($(reduce).siblings('input').val())-1);
									changeAwardSumCount($($(reduce).siblings('input'))[0]);
							}
						};

						function plus(reduce){
							$(reduce).siblings('input').val(parseInt($(reduce).siblings('input').val())+1);
							changeAwardSumCount($($(reduce).siblings('input'))[0]);
						};
					</script>
					
	<script id="images_update_tmpl" type="text/x-jquery-tmpl">
		<img onclick="headOnc();" src="<%=basePath%>/js/appWeb/images/xztp.png" class=" waitCheck" style="width:4.75rem;" />
	</script>
	
	<script id="images_update_init_tmpl" type="text/x-jquery-tmpl">
		<img onclick="headOnc();" src="{{= image}}" class=" waitCheck" style="width:4.75rem;" />
	</script>

	</div>
	
	
	
	   <script>
        $('.reduce_click').click(function () {
            if ($(this).siblings('input').val() > 1) {
                $(this).siblings('input').val(parseInt($(this).siblings('input').val()) - 1);
            }
        });
        $('.plus_click').click(function () {

            $(this).siblings('input').val(parseInt($(this).siblings('input').val()) + 1);

        });
        var week = [ "星期日","星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];
        var d = new Date();
        var p = document.getElementsByClassName("timeTop")[0].getElementsByTagName("p");
        p[0].innerHTML = week[d.getDay()];
        p[1].innerHTML = d.getFullYear();
        p[2].innerHTML = d.getMonth() + 1 + "月";
        p[3].innerHTML = d.getDate();
        var years = d.getFullYear();
        var tops = 1, bottom = 1;
        var check;
        for (var o = d.getFullYear() - 1; o <= d.getFullYear() + 1; o++) {
            for (var i = 0; i <= 11; i++) {
                var lis = document.createElement("li");
                var divs = document.createElement("div");
                divs.className = "week";
                divs.innerHTML = "<b>日</b><b>一</b><b>二</b><b>三</b><b>四</b><b>五</b><b>六</b>";
                var ps = document.createElement("p");
                ps.innerHTML = o + "年" + (i + 1) + "月";
                lis.appendChild(ps);
                lis.appendChild(divs);
                for (var dd = 0; dd <=getWeek(o, i); dd++) {
                    lis.appendChild(document.createElement("span"));
                }
                for (var q = 1; q < days(o, i); q++) {
                    var spans = document.createElement("span");
                    if (q == d.getDate() && o == d.getFullYear() && i == d.getMonth()) {
                        check=spans;
                        spans.style.borderRadius = "50%";
                        spans.style.background = "rgba(0,151,136,0.5)"
                    }
                    spans.innerHTML = q;
                    spans.onclick=function () {
                        $(check).css({
                            "border-radius": "0",
                            "background": "rgba(0, 151, 136, 0)"
                        });
                        check=this;
                        $(this).css({
                            "border-radius": "50%",
                        "background": "rgba(0, 151, 136, 0.5)"
                        });
                      var Ctime=$(this).parents("li").find("p").html();
                      var theDay=this.innerHTML;
                      $(".timeTop p:eq(0)").html(week[(getWeekDay(parseInt(Ctime),parseInt(Ctime.split("年")[1])-1,parseInt(theDay)))])
                        $(".timeTop p:eq(1)").html(parseInt(Ctime));
                      $(".timeTop p:eq(2)").html(parseInt(Ctime.split("年")[1])+"月");
                      $(".timeTop p:eq(3)").html(theDay)
                    };
                    lis.appendChild(spans);
                }
                document.getElementsByTagName("ul")[0].appendChild(lis);
            }
        }
        //    向上增加
        function timeTop() {
            tops++;
            for (var w = 11; w >= 0; w--) {
                var lis = document.createElement("li");
                var divs = document.createElement("div");
                divs.className = "week";
                divs.innerHTML = "<b>日</b><b>一</b><b>二</b><b>三</b><b>四</b><b>五</b><b>六</b>";
                var ps = document.createElement("p");
                ps.innerHTML = (years - tops) + "年" + (w + 1) + "月";
                lis.appendChild(ps);
                lis.appendChild(divs);
                for (var dd = 0; dd <getWeek(years - tops, w)+1; dd++) {
                    lis.appendChild(document.createElement("span"));
                }
                for (var d = 1; d < days((years - tops), w); d++) {
                    var spans = document.createElement("span");
                    spans.innerHTML = d;
                    spans.onclick=function () {
                        var Ctime=$(this).parents("li").find("p").html();
                        var theDay=this.innerHTML;
                        $(".timeTop p:eq(0)").html(week[(getWeekDay(parseInt(Ctime),parseInt(Ctime.split("年")[1])-1,parseInt(theDay)))])
                        $(".timeTop p:eq(1)").html(parseInt(Ctime));
                        $(".timeTop p:eq(2)").html(parseInt(Ctime.split("年")[1])+"月");
                        $(".timeTop p:eq(3)").html(theDay)
                    };
                    lis.appendChild(spans);
                }
                document.getElementsByTagName("ul")[0].appendChild(lis);
                var last = document.getElementsByTagName("ul")[0].lastChild;
                document.getElementsByTagName("ul")[0].insertBefore(last, document.getElementsByTagName("ul")[0].firstChild)
            }
        }
        //        向下增加
        function timeBottom() {
            bottom++;
            for (var w = 0; w <= 11; w++) {
                var lis = document.createElement("li");
                var divs = document.createElement("div");
                divs.className = "week";
                divs.innerHTML = "<b>日</b><b>一</b><b>二</b><b>三</b><b>四</b><b>五</b><b>六</b>";
                var ps = document.createElement("p");
                ps.innerHTML = (years + bottom) + "年" + (w + 1) + "月";
                lis.appendChild(ps);
                lis.appendChild(divs);
                for (var dd = 0; dd < getWeek(years + bottom, w)+1; dd++) {
                    lis.appendChild(document.createElement("span"));
                }
                for (var d = 1; d < days((years + bottom), w); d++) {
                    var spans = document.createElement("span");
                    spans.innerHTML = d;
                    spans.onclick=function () {
                        var Ctime=$(this).parents("li").find("p").html();
                        console.log(Ctime,this.innerHTML);
                        var theDay=this.innerHTML;
                        console.log(parseInt(Ctime),parseInt(Ctime.split("年")[1]),parseInt(theDay));
                        $(".timeTop p:eq(0)").html(week[(getWeekDay(parseInt(Ctime),parseInt(Ctime.split("年")[1])-1,parseInt(theDay)))])
                        $(".timeTop p:eq(1)").html(parseInt(Ctime));
                        $(".timeTop p:eq(2)").html(parseInt(Ctime.split("年")[1])+"月");
                        $(".timeTop p:eq(3)").html(theDay)
                    };
                    lis.appendChild(spans);
                }
                document.getElementsByTagName("ul")[0].appendChild(lis);
            }
        }
        function clickSpan(span) {
            span.onclick = function () {
            }
        }
        //获取月的天数
        function days(year, month) {
            var dayCount;
            var now = new Date(year, month, 0);
            dayCount = now.getDate();
            return dayCount;
        }
        //    获取周几
        function getWeek(year, month) {
            var get = new Date(year, month, 0);
            return get.getDay();
        }
//        获取今天周几
        function getWeekDay(year, month,day) {
            var get = new Date(year,month,day);
            return get.getDay();
        }
        getWeekDay(2017,8,19);
        //    初始化位置

        document.getElementsByClassName("timeBox")[0].onscroll = function () {
            var shut = this.getElementsByTagName("li")[0].getElementsByTagName("p")[0].innerHTML;
            if (parseInt(shut) >= 2100 || parseInt(shut) < 1900) {
                return
            }
            if (this.scrollTop > this.getElementsByTagName("ul")[0].offsetHeight - 500) {
                timeBottom()
            } else if (this.scrollTop < 500) {
                timeTop();
            }
        };
        
        document.getElementsByClassName("datas")[0].onclick=function () {
            document.getElementById("showDate").style.display="block";
            for (var index = 0; index < document.getElementsByTagName("li").length; index++) {
                var mode = document.getElementsByTagName("li")[index].getElementsByTagName("p")[0].innerHTML;
                if (parseInt(mode) == d.getFullYear() && parseInt(mode.split("年")[1]) == d.getMonth() + 1) {
                    document.getElementsByClassName("timeBox")[0].scrollTop = document.getElementsByTagName("li")[index].offsetTop;
                }
            }
        };
        
        document.getElementsByClassName("oks")[0].onclick=function () {
            document.getElementById("showDate").style.display="none";
            $(".datas p:eq(1) span").html($(".timeTop p:eq(1)").html()+"年"+$(".timeTop p:eq(2)").html()+$(".timeTop p:eq(3)").html()+"日");
            setCookie("activityEndTime",$(".timeTop p:eq(1)").html()+"-"+$(".timeTop p:eq(2)").html()+"-"+$(".timeTop p:eq(3)").html()+" 23:59:59");
            setCookie("activityShowEndTime",$(".timeTop p:eq(1)").html()+"年"+$(".timeTop p:eq(2)").html()+$(".timeTop p:eq(3)").html()+"日");
            $($(".datas p:eq(1) span").parent()).attr("class","dis_f ali_ct");
        };
        document.getElementsByClassName("nos")[0].onclick=function () {
            document.getElementById("showDate").style.display="none";
        };
    </script>
	
</body>

</html>