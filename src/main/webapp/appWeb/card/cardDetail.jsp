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
	src="<%=basePath%>/js/appWeb/card/app_cardDetail.js"></script>
	
	
	
	
<script src="<%=basePath%>/js/appWeb/weixinjs/swiper.min.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/appWeb/css/swiper.min.css" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0"
	name="viewport">
<title>卡券详情</title>
<style>
html,body{background:#f95d47;}
</style>

	<script>
				$('.close_kq_mask').click(function(){
					$('.kq_mask').hide();
				});
				$('.reduce_click').click(function(){
					if($(this).siblings('input').val()>1){
							$(this).siblings('input').val(parseInt($(this).siblings('input').val())-1);
					}
				});
				$('.plus_click').click(function(){
			
					$(this).siblings('input').val(parseInt($(this).siblings('input').val())+1);
		
				});
				
			</script>



</head>

<body>

	<div class="wraper" style="background: #f95d47;height:100%;">
		<div class="dis_f ali_ct jus_bt pad_20 bg_f bg_r">
			<a onclick="javascript:window.history.back();" ><img src="<%=basePath%>/js/appWeb/images/back5.png" class="dis_b" style="height:1rem;" /></a>
			
				<div class="whte"></div>
			<a><img src="<%=basePath%>/js/appWeb/images/more3.png" class="dis_b more_ul_toggle" style="width:1rem;" /></a>
			
			<img src="<%=basePath%>/js/appWeb/images/images/arr_up.png" style="width:0.55rem" class="dis_b arr_up_down dis_n" />
			<ul class="more_ul pad_20 dis_n">
				<li onclick="attr();"><img src="<%=basePath%>/js/appWeb/images/a2.png" class="ver_mid" style="width:0.75rem;" /> <span class="f_26 clr_f ver_mid"  id="attr">关注</span></li>
				<li onclick="collect();"><img src="<%=basePath%>/js/appWeb/images/a3.png" class="ver_mid" style="width:0.75rem;" /> <span class="f_26 clr_f ver_mid" id="collect">收藏</span></li>
				<li onclick="report();"><img src="<%=basePath%>/js/appWeb/images/a4.png" class="ver_mid" style="width:0.75rem;" /> <span class="f_26 clr_f ver_mid" id="report">举报</span></li>
			</ul>

			<script type="text/javascript">
				$('.more_ul_toggle').click(function(){
					$('.more_ul').toggle();
					$('.arr_up_down').toggle();
					$(".whte").show();
				});
				$(".whte").click(function(){
				$(this).hide();
					$('.more_ul').toggle();
					$('.arr_up_down').toggle();
				})
			</script>
			
			
		</div>
	

	<div class="pad_20" id="detail">
		
		
		<script id="detail_tmpl" type="text/x-jquery-tmpl">
		<div class="bg_f pad_20" style="padding-bottom:0;">
			<div class="dis_f ali_ct flex_col">
				<img onclick="window.location.href='/mts/appWeb/appuser/otherUser.jsp?id={{if appUser}}{{= appUser.id}}{{/if}}'" src="{{if appUser}}{{if appUser.header}}{{= appUser.header}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}" class="dis_b" style="width:2rem;border: 2px solid #FFF;border-radius: 1.1rem;margin-top:-1.5rem;" />
				<div class="dis_f ali_ct f_28 clr_3 mt_20">{{if appUser}}{{= appUser.name}}{{/if}}
						{{if appUser.sex}}
							{{if appUser.sex == '男'}}
							<img src="<%=basePath%>/js/appWeb/images/male.png" class="ver_mid" style="width:0.4rem;" />
							{{else appUser.sex == '女'}}
							<img src="<%=basePath%>/js/appWeb/images/female2.png" class="ver_mid" style="width:0.4rem;" />
							{{/if}}
						{{/if}}</div>

				<div class="f_42 clr_3 al_ct mt_30">{{= title}}</div>

				
				<img src="{{= images}}" class="dis_b mt_30" style="width:14rem;height:4rem;" />
			</div>
			<div class="dis_f ali_ct jus_rt padt_20" style="float:left;">
				<div  class="f_20 clr_he"  style="margin:0.5 0rem;float:left;color:red;">￥{{= convertMoney}}</div>
			</div>
			<div class="dis_f ali_ct jus_rt padt_20" style="float:right;">
				
				<img src="<%=basePath%>/js/appWeb/images/kq2.png" class="dis_b" style="width:0.65rem;" />
				<div class="f_20 clr_he" style="margin:0 0.5rem;">总数:{{= convertNum}}</div>
				<div class="f_20 clr_he">剩余:{{= num}}</div>
			</div>
			</br></br></br></br>
			<div class="f_20 clr_he dis_f ali_ct padt_20 bordertop1">
				<img src="<%=basePath%>/js/appWeb/images/ld.png" class="dis_b" style="width:0.5rem;margin-right	: 0.3rem;" />
				{{= endTime}}止(过期作废)
			</div>

			<div class="f_26 clr_3">{{= descr}}</div>


			<div class="f_26 clr_3 dis_f ali_ct padt_20 bordertop1 mt_30">
				<img src="<%=basePath%>/js/appWeb/images/wz.png" class="dis_b" style="width:0.65rem;margin-right: 0.3rem;" />
				<div>
					{{= address}}
				</div>
			</div>

			<div class="f_28 clr_he dis_f ali_ct jus_bt padt_20 bordertop1 mt_30" onclick="phone({{= phone}});">
				<div class="dis_f ali_ct"><img src="<%=basePath%>/js/appWeb/images/tel_x.png" class="dis_b" style="width:0.8rem;margin-right: 0.3rem;" />{{= phone}}</div>
				 <a href="tel://{{= phone}}" id="tell"><img src="<%=basePath%>/js/appWeb/images/tel_blue.png" class="dis_b" style="width:1rem;margin-left: 0.3rem;" />
			</a>
			</div>


		</div>
		<div class="dis_f jus_ct al_ct">
			<img src="<%=basePath%>/js/appWeb/images/kq_line.png" class="dis_b" style="width:16rem;height:0.75rem;" />
		</div>

		<div class="pad_30 bg_f" id="cardCode" style="text-align:center;">
			{{if status}}
			{{if status == 1}}
			<input type="button" class="f_26 clr_9 dis_b waiting_check_a" style="background: #e3e3e6;border:0;" value="待审核" />
			{{else status==2 }}
			<input type="button" onclick="javascript:$('.kq_mask').show();"  class="f_26 clr_f dis_b waiting_check_a" style="background: #f95d47;border:0;" value="立即领取" />
			{{else status==3 }}
			<input type="button" class="f_26 clr_f dis_b waiting_check_a" style="background: #e3e3e6;border:0;" value="审核失败" />
			{{else status==4 }}
			<input type="button" class="f_26 clr_f dis_b waiting_check_a" style="background: #e3e3e6;border:0;" value="已过期" />
			{{else status==5 }}
			<input type="button" class="f_26 clr_f dis_b waiting_check_a" style="background: #e3e3e6;border:0;" value="已领完" />
			{{else status==6 }}
			<input type="button" onclick="javascript:window.location.href='/mts/appWeb/appuser/appuserLogin.jsp';"  class="f_26 clr_f dis_b waiting_check_a" style="background: #f95d47;border:0;" value="立即领取" />
			{{/if}}
			{{/if}}
		</div>
				<div class="bg_f dis_f ali_ct padl_30 jus_bt" style="padding-bottom:0.75rem;margin-top: 1%;text-align:right;" id="lingquan">
				</div>
		</script>
		
		<script id="lingqu_tmpl" type="text/x-jquery-tmpl">
	
	<div class="kq_mask" style="display: none;">
		<div class="kq_bg pos_rela" style="padding-top:5.8rem;">
			<a onclick="javascript:$('.kq_mask').hide();"  class="dis_b close_kq_mask"></a>
			<div class="pad_30">
				<div  style="border:1px solid #e0e0e0;border-radius:0.1rem;"  class="pad_20 dis_f ali_ct jus_bt" >
					<div class="dis_f ali_ct jus_bt">
						<div class="f_22 clr_3 dis_f ali_ct">
							<img src="<%=basePath%>/js/appWeb/images/lqsl.png" class="dis_b" style="width:0.7rem;margin-right:0.2rem;" />
							领取数量
						</div>
					</div>
					
					<div class="dis_f ali_ct">
						<img src="<%=basePath%>/js/appWeb/images/reduce.png"  onclick="javascript:change(1);"  class="dis_b reduce_click" style="width:0.8rem;" />
						<input class="ipt3 al_ct ipt_num f_22" value="1" id="num" type="number" style="width:1.5rem;" />
						<img src="<%=basePath%>/js/appWeb/images/plus.png" onclick="javascript:change(2);" class="dis_b plus_click" style="width:0.8rem;" />
					</div>
					
				</div>
			</div>

			<div class="padl_30">
				<div class="padl_20 dis_f ali_ct jus_bt" >
					<div class="dis_f ali_ct jus_bt">
						<div class="f_22 clr_3 dis_f ali_ct">
							<img src="<%=basePath%>/js/appWeb/images/sxje.png" class="dis_b" style="width:0.7rem;margin-right:0.2rem;" />
							所需金额
						</div>
					</div>
					<div class="f_32 clr_r"><span class="f_22">￥</span><span id="convertMoney">{{= convertMoney}}</span></div>
				</div>
			</div>

			<div class="f_20 clr_3 pad_30 al_ct">使用账户余额付款 ¥<span id="balance">{{= convertMoney}}</span>  <a href="#" class="clr_b">更换</a></div>
				<div class="">
			<input type="button"  onclick="pay();"  class="f_26 clr_f dis_b waiting_check_a" style="background: #f95d47;border:0;width:9.5rem;height:1.7rem;line-height: 1.7rem;" value="立即领取" />
		</div>
		</div>
	</div>
			
	</div>

		</div>
		
		</script>
		
		<script id="lingquan_tmpl" type="text/x-jquery-tmpl">
			<img onclick="window.location.href='/mts/appWeb/appuser/otherUser.jsp?id={{= id}}'" src="{{if header}}{{= header}}{{else}}<%=basePath%>/js/appWeb/images/default_header.png{{/if}}" class="ver_mid" style="width:1.5rem;border-radius: 0.8rem;" />
		</script>
		
		<script id="lingquanFoot_tmpl" type="text/x-jquery-tmpl">
			<div class="clr_3 f_30">…</div>
			<img src="<%=basePath%>/js/appWeb/images/right.png" class="dis_b" style="width:0.4rem;margin:0 0.5rem;padding-right: 5px;" />
		</script>
		
		
		
		
</body>

</html>