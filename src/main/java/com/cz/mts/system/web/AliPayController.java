package com.cz.mts.system.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cz.mts.frame.annotation.SecurityApi;
import com.cz.mts.frame.controller.BaseController;
import com.cz.mts.system.service.IAppUserService;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Controller
@RequestMapping(value="/system/zfb")
public class AliPayController extends BaseController {
	
	@Autowired
	IAppUserService appUserService;
	
	public String type;

	private static final Log log = LogFactory.getLog("CONSOLE");

	
	public @ResponseBody static Notify xml2JavaObject(String xml) {
		XStream xs = new XStream(new DomDriver());
		xs.alias("notify", Notify.class);
		Notify notify = (Notify) xs.fromXML(xml);
		return notify;
	}

	@RequestMapping("/getzfb/json")
	public @ResponseBody String getzfb(HttpServletRequest request) throws Exception {
		log.info("************支付宝回调**************");

		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		String code = "";
		String money = "";
		String payNumber = "";
		String body = "";
		String tradeNo = "";
		String trade_status = "";
		String type = "";
		System.out.println(params);
		System.out.println(requestParams);
		// 安卓或者ios 分开来算
		if (params.get("notify_data") != null) {
			String data = params.get("notify_data");
			Notify notify =new Notify();
			try {
				notify = xml2JavaObject(data);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			// code0返回的订单 ，最后一位是判断分类的
			// 团购1 外卖2
			code = notify.getOut_trade_no().split("a")[0];
			log.info("****支付宝返回的订单号=" + code + "*****");
			type = code.substring(0, 1);
			code = code.substring(1);
			money = notify.getTotal_fee();
			payNumber = notify.getTrade_no();
			tradeNo = notify.getTrade_no();
			body = notify.getBody();
			trade_status = notify.getTrade_status();
			System.out.println(trade_status);
			log.info("****trade_status=" + trade_status + "*****");
			log.info("****code=" + code + "*****");
		} else {
			money = params.get("total_fee");
			tradeNo = params.get("trade_no");
			payNumber = params.get("trade_no");
			body = params.get("body");
			trade_status = params.get("trade_status");
			code = params.get("out_trade_no");
			type = code.substring(0, 1);
			code = code.substring(1);
			// code0返回的订单 ，最后一位是判断分类的

			System.out.println(trade_status);
			log.info("****trade_status=" + trade_status + "*****");
			log.info("****code=" + code + "*****");
			
		}

		if ("TRADE_SUCCESS".equalsIgnoreCase(trade_status)) {
			// 1充值
			// 此处为业务逻辑
			int returnvalue = 0;
			switch (type) {
			case"P":
				appUserService.alipay(code, 1, Double.parseDouble(money), tradeNo, 1);
				break;
			case"V":
				appUserService.alipay(code, 2, Double.parseDouble(money), tradeNo, 1);
				break;
			case"C":
				appUserService.alipay(code, 3, Double.parseDouble(money), tradeNo, 1);
				break;
			case"R":
				appUserService.alipay(code, 4, Double.parseDouble(money), tradeNo, 1);
				break;
			case"A":
				appUserService.alipay(code, 5, Double.parseDouble(money), tradeNo, 1);
				break;
			case "D":
				appUserService.alipay(code, 6, Double.parseDouble(money), tradeNo, 1);
				break;
			}
			
		} else {
		}
		log.info("************支付宝回调通知结束**************");
		return null;
	}

	
}
