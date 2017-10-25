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
<script type="text/javascript"
	src="<%=basePath%>/js/appWeb/activity/app_activityUserList.js"></script>
<script src="<%=basePath%>/js/appWeb/weixinjs/swiper.min.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/appWeb/css/swiper.min.css" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0"
	name="viewport">
<title>首页</title>
</head>


	<style>
		.srk_box{
			height: 100%;
			width: 100%;
			background-color: rgba(0,0,0,0.5);
			position: fixed;
			top: 0rem;
			z-index: -1;

		}
		
		.body{
			height: 1px;
			width: 1px;
		}

		.bottom_srk{
			position: fixed;
			bottom: 0rem;
			width: 100%;
			background-color: #f2f2f2;
			box-sizing: border-box;
		}

		.wraper{
			position:absolute;
			z-index: -2;
			width: 100%;
		}


		.inpt2{
			border: 2px solid #e1e1e1;
			border-radius: 0.1rem;
			width: 11rem;
			float: left;
			height: 2.0rem;
			line-height: 2.0rem;
			padding-left: 0.5rem;
			display: block;
			z-index: 1;
		}

		.fs{
			background-color: #0a7cd4;
			float: right;
			height: 2.0rem;
			line-height: 2.0rem;
			width: 3rem;
			border-radius: 0.2rem;
			color: #ffffff;
			text-align: center;
		}
.prize-box{
	width:100%;
	max-height:6rem;
	overflow-y:auto;
	overflow-x:hidden;
}
.prize-list{
	width:100%;
	height:2rem;
	border-bottom:.05rem solid #ddd;
	display:flex;
	justify-content:center;
	align-items:center;
}
.prize-list img{
width:1rem;
height:auto;
}
.prize-list b,.prize-list em{
	font-style: normal
}
.prize-list b{
padding:0 .3rem;
font-size:0.6rem;
}
::-webkit-scrollbar {
    width: 0px;
    height: 1px;
}::-webkit-scrollbar-thumb {
    border-radius: 5px;
    -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
    background: rgba(0, 0, 0, 0.2);
} 
	</style>

<body class="body">

	<div class="wraper">
<div class="alert-box dis_f jus_ct ali_end" style="position:fixed;width:100%;height:100%;background:rgba(0,0,0,.6);left:0;top:0;z-index:1000;display:none;">
	<div  style="width:100%;background:#fff;" id="price">
		<div style="height:1.5rem;border-bottom:.02rem solid #ddd;position:relative;"class="dis_f jus_ct ali_ct">
			<span style="display:inline-block;margin:0 auto;vertical-align:middle;font-size:0.55rem;color:#999;width:5rem;background:#fff;text-align:center;position:relative;z-index:100;">请选择中奖等级</span>
			<div style="position:absolute;left:50%;top:50%;transform:translate(-50%,-50%);width:8rem;height:.05rem;background:#ddd;"></div>
		</div>
		<div class="prize-box">
			<div class="prize-list">
				<img src="<%=basePath%>/js/appWeb/images/onePrice.png">
				<b>特等奖</b>
				<span>剩余<em>1</em>名额</span>
			</div>
			<div class="prize-list">
				<img src="<%=basePath%>/js/appWeb/images/onePrice.png">
				<b>特等奖</b>
				<span>剩余<em>1</em>名额</span>
			</div>
			<div class="prize-list">
				<img src="<%=basePath%>/js/appWeb/images/onePrice.png">
				<b>特等奖</b>
				<span>剩余<em>1</em>名额</span>
			</div>	
			<div class="prize-list">
				<img src="<%=basePath%>/js/appWeb/images/onePrice.png">
				<b>特等奖</b>
				<span>剩余<em>1</em>名额</span>
			</div>	
			<div class="prize-list">
				<img src="<%=basePath%>/js/appWeb/images/onePrice.png">
				<b>特等奖</b>
				<span>剩余<em>1</em>名额</span>
			</div>	
			<div class="prize-list">
				<img src="<%=basePath%>/js/appWeb/images/onePrice.png">
				<b>特等奖</b>
				<span>剩余<em>1</em>名额</span>
			</div>	
		</div>
	</div>
</div>

			<div class="bg_f pad_2030  dis_f ali_ct jus_bt pos_rela">
				<a onclick="javascript:window.history.back();"><img src="<%=basePath%>/js/appWeb/images/back6.png" class="dis_b" style="height:1rem;" /></a>
				<div class="search_bg2 pos_rela">
					<input class="ipt2 f_22 clr_he" id="title"  type="text" placeholder="昵称/主题" />
					<img onclick="select();" class="btn2"/>
				</div>
				<a href="javascript:;"><img src="<%=basePath%>/js/appWeb/images/filter2.png" class="dis_b filter_toggle" style="height:0.9rem;" /></a>
				<img src="<%=basePath%>/js/appWeb/images/arr_up.png" style="width:0.55rem;top:1.7rem;" class="dis_b arr_up_down dis_n xx_pic"/>
				<ul class="more_ul pad_20 dis_n xx_daohang" style="top:1.9rem;" >
					<li onclick="selectSort(1);"><img src="<%=basePath%>/js/appWeb/images/f1.png" class="ver_mid" style="width:0.6rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid">最新发布</span></li>
					<li onclick="selectSort(2);"><img src="<%=basePath%>/js/appWeb/images/dzzd.png" class="ver_mid" style="width:0.7rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid">点赞最多</span></li>
					<li onclick="selectSort(3);"><img src="<%=basePath%>/js/appWeb/images/plzd.png" class="ver_mid" style="width:0.7rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid">评论最多</span></li>
				</ul>

				<script type="text/javascript">
					$('.filter_toggle').click(function(){
						$('.xx_pic').toggle();
						$('.xx_daohang').toggle();
					});
				</script>
			</div>


		<div id="userList">
		
		</div>
		


<div id="foot"></div>


<script type="text/javascript">
$('.more_ul_toggle').click(function(){
	$(this).siblings('.more_ul').toggle();
	$(this).siblings('.arr_up_down').toggle();
});
$(".alert-box").click(function(){
	$(this).hide()
});
$("#price").click(function(){
	event.stopPropagation();
})
</script>





	<script id="user_list_tmpl" type="text/x-jquery-tmpl">

		<div class="pad_30 bg_f borderbot1 mt_20">
			<div class="dis_f ali_top jus_bt pos_rela" >
				<div class="dis_f ali_ct"  onclick="window.location.href='/mts/appWeb/appuser/otherUser.jsp?id={{if appUser}}{{= appUser.id}}{{/if}}'">
					<img src="{{if appUser}}{{if appUser.header}}{{= appUser.header}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}" class="dis_b" style="width:1.75rem;border-radius: 0.9rem;margin-right:0.5rem;" />
					<div>
						<span class="ver_mid f_28 clr_3">{{if appUser}}{{= appUser.name}}{{/if}}</span>
						{{if appUser.sex}}
						{{if appUser.sex == '男'}}
						<img src="<%=basePath%>/js/appWeb/images/male.png" class="ver_mid" style="width:0.4rem;" />
						{{else appUser.sex == '女'}}
						<img src="<%=basePath%>/js/appWeb/images/female2.png" class="ver_mid" style="width:0.4rem;" />
						{{/if}}
						{{/if}}
				{{if appUser.userMedals}}
						{{each appUser.userMedals}}
							<img src="{{= $value.medal.image}}" class="ver_mid" style="width:0.5rem;" />
   					 	{{/each}}
				{{/if}}
						<div class="f_22 clr_9">{{= createTime}}</div>

					</div>
				</div>

				<a href="javascript:;" class="more_ul_toggle"><img src="<%=basePath%>/js/appWeb/images/more.png" class="dis_b" style="width:1rem;" /></a>
				<img src="<%=basePath%>/js/appWeb/images/arr_up.png" style="width:0.55rem;top:1rem;right:0.3rem;" class="dis_b arr_up_down dis_n" />
				<ul class="more_ul pad_20 dis_n" style="top:1.25rem;right:0;">
					<li><img src="<%=basePath%>/js/appWeb/images/a2.png" class="ver_mid" style="width:0.75rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid">已关注</span></li>
					<li><img src="<%=basePath%>/js/appWeb/images/f3.png" class="ver_mid" style="height:0.75rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid">屏蔽</span></li>
					<li><img src="<%=basePath%>/js/appWeb/images/a4.png" class="ver_mid" style="width:0.75rem;margin-right:0.3rem;" /> <span class="f_26 clr_f ver_mid">举报</span></li>
				</ul>
			</div>
			<div class="f_28 clr_3 mt_20">


					{{= content}}
					</br>
						{{if type==2}}

						<video src="{{= mediaUrl}}" style="width:100%;height:30%;" controls="controls">
						您的浏览器不支持 video 标签。
						</video>
						{{else}}
						{{if images}}
							{{each images}}
								<img src="{{= $value}}" class="ver_mid" style="width:4.6rem;height:4.6rem;" />
   					 		{{/each}}
						{{/if}}
						{{/if}}
						</div>
		</div>

		<div class="bg_f padl_30">
			<div class="dis_f ali_ct jus_bt padt_20">
				
				<span itemId="{{if appUser}}{{= appUser.id}}{{/if}}" class="bj" onclick="alertPrice()">
				<img src="<%=basePath%>/js/appWeb/images/bj.png" class="ver_mid"  style="width:3.5rem;" />
				</span>

				<div class="dis_f ali_ct jus_bt">
					<div style="margin-right:1rem;">
						<img onclick="showInput({{= id}});" src="<%=basePath%>/js/appWeb/images/xx.png" class="ver_mid" style="height:0.7rem;" />
						<span class="f_20 clr_6 ver_mid">{{= commentCount}}</span>
					</div>
					<div>
						{{if isOper}}
							{{if isOper==1}}						
								<img src="<%=basePath%>/js/appWeb/images/zan3.png" class="ver_mid" style="height:0.75rem;" />
							{{else}}
								<img onclick="oper({{= id}});" src="<%=basePath%>/js/appWeb/images/zan2.png" class="ver_mid" style="height:0.75rem;" />
							{{/if}}
						{{else}}
							<img onclick="oper({{= id}});" src="<%=basePath%>/js/appWeb/images/zan2.png" class="ver_mid" style="height:0.75rem;" />
						{{/if}}
						<span class="f_20 clr_6 ver_mid">{{= topCount}}</span>
					</div>
				</div>
			</div>

			<div class="f_22 clr_3 padt_20 pinglun ">
				{{if opers}}
						{{each opers}}
						<div onclick="toUser({{= itemId}},{{if $value.userId}}{{= $value.userId}}{{/if}},'{{if $value.nickName}}{{= $value.nickName}}{{/if}}');">
							<span class="clr_b">{{if $value.nickName}}{{= $value.nickName}}{{/if}}</span>  {{if $value.toUserName}}回复  <font color="#003D79">{{= $value.toUserName}}</font>:{{else}}:{{/if}}  {{if $value.content}}{{= $value.content}}{{/if}}
							<p class="f_18 clr_9">{{if $value.createTime}}{{= $value.createTime}}{{/if}}</p>
						</div>
   					 	{{/each}}
				{{/if}}
				<a href="javascript:all({{= id}});" id="{{= id}}" style="display:none;" all="0" class="clr_b">展开</a>
			</div>
		</div>

	</script>

	<script id="canyu_tmpl" type="text/x-jquery-tmpl">
			{{if isPart}}
				{{if isPart == 1}}
				<a class="dis_b f_28 a_btn2 al_ct mt_20" style=" background: #e3e3e6;color: #a7a7a6;">已参与</a>
				{{else}}
				<a  onclick="canyu();"  class="dis_b f_28 a_btn2 al_ct mt_20" style=" background: #FF7575;color: #FFFFFF;">立即参与</a>
				{{/if}}
			{{else}}
				<a onclick="canyu();" class="dis_b f_28 a_btn2 al_ct mt_20" style=" background: #FF7575;color: #FFFFFF;">立即参与</a>
			{{/if}}
	</script>
	
	</div>
	
	<script id="image_tmpl" type="text/x-jquery-tmpl">
			<img src="{{= image}}" class="ver_mid" style="width:4.75rem;height:4.75rem;" />
	</script>
	
	</div>
	<div id="srk_box" style="display:none;" onclick="hideInput();"  class="srk_box">
	</div>
	<div  id="inputBtn"  style="display:none;">
			<div class="pad_20 bottom_srk">
				<input id="content" class="inpt2 f_28 clr_9" type="text" placeholder="回复：说点儿什么吧...">
				<div class="fs" onclick="fasong();">发送</div>
				<div style="clear: both"></div>
			</div>
		</div>
	
	
</body>
	
</html>