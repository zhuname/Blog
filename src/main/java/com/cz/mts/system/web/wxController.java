package com.cz.mts.system.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cz.mts.frame.controller.BaseController;
import com.cz.mts.system.service.IAppUserService;



@Controller
@RequestMapping(value="/system/wx")
public class wxController extends BaseController {
	
	private static final Log log = LogFactory.getLog("CONSOLE");

	@Autowired
	IAppUserService appUserService;
	
	public String type;

	@RequestMapping("/getwx/json")
	public @ResponseBody void getwx(HttpServletRequest request) throws IOException, DocumentException {
		log.info("------------->微信支付！");
		try {
			Map<String, String> map = new HashMap<String, String>();
			InputStream inputStream = request.getInputStream();
			SAXReader reader = new SAXReader();
			Document document = reader.read(inputStream);
			Element root = document.getRootElement();
			List<Element> elementList = root.elements();
			String info="" ;
			for (Element e : elementList) {
				map.put(e.getName(), e.getText());
				info = e.getName()+":"+e.getText()+"-----" ;
			}
			log.info("微信参数:"+info);
			String code = "";

			String result_code = map.get("result_code");
			System.out.println(result_code);
			if (result_code.equals("SUCCESS")) {
				// 商户订单号
				String weixinCode = map.get("out_trade_no");
				// 财付通订单号
				String transaction_id = map.get("transaction_id");
				// 金额,以分为单位
				String total_fee = map.get("total_fee");
				// 如果有使用折扣券，discount有值，total_fee+discount=原请求的total_fee
				String discount = map.get("discount");
				// 支付结果
				String trade_state = map.get("trade_state");

				String out_refund_no=map.get("out_refund_no");
				//公众号账号id
				String appid = map.get("appid") ;
				
				String orderNumber = "";
				// 餐厅1精品2云单3
				String resultCode = "";
				//if(weixinCode.indexOf("_")>3){
				//	resultCode = weixinCode.substring(0, weixinCode.indexOf("_"));
			//	}else {
					resultCode = weixinCode ;
				//}
				code = resultCode;
				type = code.substring(0, 1);
				code = code.substring(1);

				if ("SUCCESS".equals(result_code)) {
					// c充值
					// 此处为业务逻辑
					int returnvalue = 0;
					switch (type) {
					case"P":
						appUserService.alipay(code, 1, Double.parseDouble(total_fee)/100, transaction_id, 2);
						break;
					case"V":
						appUserService.alipay(code, 2, Double.parseDouble(total_fee)/100, transaction_id, 2);
						break;
					case"C":
						appUserService.alipay(code, 3, Double.parseDouble(total_fee)/100, transaction_id, 2);
						break;
					case"R":
						appUserService.alipay(code, 4, Double.parseDouble(total_fee)/100, transaction_id, 2);
						break;
					}
				} 
				if (weixinCode.indexOf("_") != -1) {
					orderNumber = weixinCode.substring(0, weixinCode.indexOf("_"));
				} else {
					orderNumber = weixinCode;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		log.info("==========================》微信结束");
	}
}
