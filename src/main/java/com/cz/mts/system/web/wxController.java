package com.cz.mts.system.web;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cz.mts.frame.controller.BaseController;
import com.cz.mts.frame.util.ReturnDatas;
import com.cz.mts.frame.util.wx.MD5Util;
import com.cz.mts.system.service.IAppUserService;
import com.sun.tools.internal.ws.util.xml.XmlUtil;



@Controller
@RequestMapping(value="/system/wx")
public class wxController extends BaseController {
	
	private static final Log log = LogFactory.getLog("CONSOLE");

	@Autowired
	IAppUserService appUserService;
	@Autowired  
	HttpSession session;  
	
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
					case"A":
						appUserService.alipay(code, 5, Double.parseDouble(total_fee)/100, transaction_id, 2);
						break;
					case "D":
						appUserService.alipay(code, 6, Double.parseDouble(total_fee)/100, transaction_id, 2);
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
	
	
	
	@RequestMapping("/getDingdan/json")
	public @ResponseBody ReturnDatas getDingdan(HttpServletRequest request) throws IOException, DocumentException {
		log.info("------------->微信提交订单！");
		
		ReturnDatas returnDatas=ReturnDatas.getSuccessReturnDatas();
		
		String payType=request.getParameter("payType");
		
		String code=request.getParameter("code");
		if(code==null){
			code=session.getAttribute("appUserSessionId").toString();
		}
		
        String appid = wxAppId;
        String mch_id = wxMch_id;
        String nonce_str = random();//生成随机数，可直接用系统提供的方法  
        String body = "每天赏-商品订单";  
        String out_trade_no = payType+code+"_"+new Date().getTime();  
        String total_fee = request.getParameter("total_fee1");
        String spbill_create_ip = "47.92.129.76";//用户端ip,这里随意输入的  
        String notify_url = "http://app.mtianw.com/mts/system/appuser/pay/json";//支付回调地址  
        String trade_type = "JSAPI";
        String openid = request.getParameter("openid1");
        //String openid = "o_5VotxlVkUaP7_ktlBWarSmnUWg";
        
        
        HashMap<String, String> map = new HashMap<String, String>();  
        map.put("appid", appid);  
        map.put("mch_id", mch_id);  
        map.put("attach", "支付测试");
        map.put("device_info", "WEB");  
        map.put("nonce_str", nonce_str);  
        map.put("body", body);  
        map.put("out_trade_no", out_trade_no);  
        map.put("total_fee", total_fee);  
        map.put("spbill_create_ip", spbill_create_ip);  
        map.put("trade_type", trade_type);  
        map.put("notify_url", notify_url);  
        map.put("openid", openid);  
        String sign = sign(map, MchSecret);//参数加密  
        System.out.println("sign秘钥:-----------"+sign);  
        map.put("sign", sign);  
        //组装xml(wx就这么变态，非得加点xml在里面)  
        String content=MapToXml(map);  
        //System.out.println(content);  
        
        CloseableHttpClient httpclient = HttpClients.createDefault();
        
        try {
        	HttpPost httpost = new HttpPost("https://api.mch.weixin.qq.com/pay/unifiedorder"); // 设置响应头信息
        	httpost.addHeader("Connection", "keep-alive");
        	httpost.addHeader("Accept", "*/*");
        	httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        	httpost.addHeader("Host", "api.mch.weixin.qq.com");
        	httpost.addHeader("X-Requested-With", "XMLHttpRequest");
        	httpost.addHeader("Cache-Control", "max-age=0");
        	httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
    		httpost.setEntity(new StringEntity(content.toString(), "UTF-8"));
    		CloseableHttpResponse response = httpclient.execute(httpost);
            try {
                HttpEntity entity = response.getEntity();
                String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
                EntityUtils.consume(entity);
                
                JSONObject obj = new JSONObject();  
                try {  
                    InputStream is = new ByteArrayInputStream(jsonStr.getBytes("utf-8"));  
                    SAXBuilder sb = new SAXBuilder();  
                    org.jdom.Document doc = sb.build(is);  
                    org.jdom.Element root = doc.getRootElement();  
                    obj.put(root.getName(), iterateElement(root));  
                    
                    System.out.println("----------微信支付-------------");  
                    //1、通过网页授权接口，获取到的openid  
                    //处理价格单位为：分(请自行处理)
                    
                    JSONObject jsonObject=JSONObject.fromObject(obj.get("xml").toString());
                    String preid=jsonObject.get("prepay_id").toString().substring(2, jsonObject.get("prepay_id").toString().length()-2);
                    System.out.println("预支付标示：----------------"+preid);  
                    //APPID  
                    String appId=appid;  
                    request.setAttribute("appId", appId);  
                    //时间戳  
                    String timeStamp=(System.currentTimeMillis()/1000)+"";  
                    request.setAttribute("timeStamp", timeStamp);  
                    //随机字符串  
                    String nonceStr=random();  
                    request.setAttribute("nonceStr", nonceStr);  
                    //预支付标识  
                    request.setAttribute("prepay_id", "prepay_id="+preid);  
                    //加密方式  
                    request.setAttribute("signType", "MD5");  
                      
                    //组装map用于生成sign  
                    Map<String, String> mapTijiao=new HashMap<String, String>();  
                    mapTijiao.put("appId", appId);  
                    mapTijiao.put("timeStamp", timeStamp);  
                    mapTijiao.put("nonceStr", nonceStr);
                    mapTijiao.put("package", "prepay_id="+preid);  
                    mapTijiao.put("signType", "MD5");
                    
                    mapTijiao.put("paySign", sign(mapTijiao, MchSecret));
                    mapTijiao.put("package1", "prepay_id="+preid);  
                    mapTijiao.put("out_trade_no", out_trade_no);  
                    
                    returnDatas.setData(mapTijiao);
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
                
               
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
        
        
        
        return returnDatas;  
		
	}
	
	
	
	@RequestMapping("/htmlRetrun/json")
	public @ResponseBody ReturnDatas htmlRetrun(HttpServletRequest request) throws IOException, DocumentException {
		log.info("------------->微信订单回调！");
		ReturnDatas returnDatas=ReturnDatas.getSuccessReturnDatas();
		
        String appid = wxAppId;
        String mch_id = wxMch_id;
        String nonce_str = random();//生成随机数，可直接用系统提供的方法  
        String out_trade_no = request.getParameter("out_trade_no");  
        
        //String openid = "o_5VotxlVkUaP7_ktlBWarSmnUWg";
        HashMap<String, String> map = new HashMap<String, String>();  
        map.put("appid", appid);  
        map.put("mch_id", mch_id);  
        map.put("nonce_str", nonce_str);  
        map.put("out_trade_no", out_trade_no);  
        String sign = sign(map, MchSecret);//参数加密  
        System.out.println("sign秘钥:-----------"+sign);  
        map.put("sign", sign);
        //组装xml(wx就这么变态，非得加点xml在里面)  
        String content=MapToXml(map);  
        //System.out.println(content);  
        
        CloseableHttpClient httpclient = HttpClients.createDefault();
        
        try {
        	HttpPost httpost = new HttpPost("https://api.mch.weixin.qq.com/pay/orderquery"); // 设置响应头信息
        	httpost.addHeader("Connection", "keep-alive");
        	httpost.addHeader("Accept", "*/*");
        	httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        	httpost.addHeader("Host", "api.mch.weixin.qq.com");
        	httpost.addHeader("X-Requested-With", "XMLHttpRequest");
        	httpost.addHeader("Cache-Control", "max-age=0");
        	httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
    		httpost.setEntity(new StringEntity(content.toString(), "UTF-8"));
    		CloseableHttpResponse response = httpclient.execute(httpost);
            try {
                HttpEntity entity = response.getEntity();
                String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
                EntityUtils.consume(entity);
                
                
                
                JSONObject obj = new JSONObject();  
                try {
                    InputStream is = new ByteArrayInputStream(jsonStr.getBytes("utf-8"));  
                    SAXBuilder sb = new SAXBuilder();  
                    org.jdom.Document doc = sb.build(is);  
                    org.jdom.Element root = doc.getRootElement();  
                    obj.put(root.getName(), iterateElement(root)); 
                    
                    
                    
                    JSONObject jsonObject=JSONObject.fromObject(obj.get("xml").toString());
                    
                    String result_code=jsonObject.get("result_code").toString().substring(2, jsonObject.get("result_code").toString().length()-2);
                    if(result_code.equals("SUCCESS")){
                    	String total_fee=jsonObject.get("total_fee").toString().substring(2, jsonObject.get("total_fee").toString().length()-2);
                    	String transaction_id=jsonObject.get("transaction_id").toString().substring(2, jsonObject.get("transaction_id").toString().length()-2);
                    	out_trade_no = out_trade_no.substring(1);
                    	type = out_trade_no.substring(0, 1);

        				if ("SUCCESS".equals(result_code)) {
        					// c充值
        					// 此处为业务逻辑
        					int returnvalue = 0;
        					switch (type) {
        					case"P":
        						appUserService.alipay(out_trade_no, 1, Double.parseDouble(total_fee)/100, transaction_id, 2);
        						break;
        					case"V":
        						appUserService.alipay(out_trade_no, 2, Double.parseDouble(total_fee)/100, transaction_id, 2);
        						break;
        					case"C":
        						appUserService.alipay(out_trade_no, 3, Double.parseDouble(total_fee)/100, transaction_id, 2);
        						break;
        					case"R":
        						appUserService.alipay(out_trade_no, 4, Double.parseDouble(total_fee)/100, transaction_id, 2);
        						break;
        					case"A":
        						appUserService.alipay(out_trade_no, 5, Double.parseDouble(total_fee)/100, transaction_id, 2);
        						break;
        					case "D":
        						appUserService.alipay(out_trade_no, 6, Double.parseDouble(total_fee)/100, transaction_id, 2);
        						break;
        					}
                    	
                    	
        					}
                    }
                    
                    
                    returnDatas.setData(obj);
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
                
               
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
        
        return returnDatas;  
		
	}
	
	
	/** 
     * 生成签名sign 
     * 第一步：非空参数值的参数按照参数名ASCII码从小到大排序，按照键值对的形式。生成字符串StringA 
     * stringA="appid=wxd930ea5d5a258f4f&body=test&device_info=1000&mch_id=10000100&nonce_str=ibuaiVcKdpRxkhJA"; 
     * 第二部：拼接API密钥，这里的秘钥是微信商户平台的秘钥，是自己设置的，不是公众号的秘钥 
     * stringSignTemp="stringA&key=192006250b4c09247ec02edce69f6a2d" 
     * 第三部：MD5加密 
     * sign=MD5(stringSignTemp).toUpperCase()="9A0A8659F005D6984697E2CA0A9CF3B7" 
     *  
     * @param map 不包含空字符串的map 
     * @return 
     */  
    public static String sign(Map<String, String> map,String key) {  
        //排序  
        String sort=sortParameters(map);  
        //拼接API秘钥  
        sort=sort+"&key="+key;  
        //System.out.println(sort);  
        //MD5加密  
        String sign=MD5Util.MD5Encode(sort, "UTF-8").toUpperCase() ;
        return sign;  
    }  
      
    /** 
     * 对参数列表进行排序，并拼接key=value&key=value形式 
     * @param map 
     * @return 
     */  
    private static String sortParameters(Map<String, String> map) {  
        Set<String> keys = map.keySet();  
        List<String> paramsBuf = new ArrayList<String>();  
        for (String k : keys) {  
            paramsBuf.add((k + "=" + getParamString(map, k)));  
        }  
        // 对参数排序  
        Collections.sort(paramsBuf);  
        String result="";  
        int count=paramsBuf.size();  
        for(int i=0;i<count;i++){  
            if(i<(count-1)){  
                result+=paramsBuf.get(i)+"&";  
            }else {  
                result+=paramsBuf.get(i);  
            }  
        }  
        return result;  
    }  
    /** 
     * 返回key的值 
     * @param map 
     * @param key 
     * @return 
     */  
    private static String getParamString(Map map, String key) {  
        String buf = "";  
        if (map.get(key) instanceof String[]) {  
            buf = ((String[]) map.get(key))[0];  
        } else {  
            buf = (String) map.get(key);  
        }  
        return buf;  
    }  
    /** 
     * 字符串列表从大到小排序 
     * @param data 
     * @return 
     */  
    private static List<String> sort(List<String> data) {  
        Collections.sort(data, new Comparator<String>() {  
            public int compare(String obj1, String obj2) {  
                return obj1.compareTo(obj2);  
            }  
        });  
        return data;  
    }  
	
    /** 
     * Map转Xml 
     * @param arr 
     * @return 
     */  
    public static String MapToXml(Map<String, String> arr) {  
        String xml = "<xml>";  
        Iterator<Entry<String, String>> iter = arr.entrySet().iterator();  
        while (iter.hasNext()) {  
            Entry<String, String> entry = iter.next();  
            String key = entry.getKey();  
            String val = entry.getValue();  
            if (IsNumeric(val)) {  
                xml += "<" + key + ">" + val + "</" + key + ">";  
            } else  
                xml += "<" + key + "><![CDATA[" + val + "]]></" + key + ">";  
        }  
        xml += "</xml>";  
        return xml;  
    }  
    private static boolean IsNumeric(String str) {  
        if (str.matches("\\d *")) {  
            return true;  
        } else {  
            return false;  
        }  
    }  
    
    public static  String xml2JSON(String xml) {  
        JSONObject obj = new JSONObject();  
        try {  
            InputStream is = new ByteArrayInputStream(xml.getBytes("utf-8"));  
            org.jdom.input.SAXBuilder sb = new org.jdom.input.SAXBuilder();  
            org.jdom.Document doc = sb.build(is);  
            org.jdom.Element root = doc.getRootElement();  
            obj.put(root.getName(), iterateElement(root));  
            return obj.toString();  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
    
    /** 
     * 一个迭代方法 
     *  
     * @param element 
     *            : org.jdom.Element 
     * @return java.util.Map 实例 
     */  
    @SuppressWarnings("unchecked")  
    private static Map  iterateElement(org.jdom.Element element) {  
        List jiedian = element.getChildren();  
        org.jdom.Element et = null;  
        Map obj = new HashMap();  
        List list = null;  
        for (int i = 0; i < jiedian.size(); i++) {  
            list = new LinkedList();  
            et = (org.jdom.Element) jiedian.get(i);  
            if (et.getTextTrim().equals("")) {  
                if (et.getChildren().size() == 0)  
                    continue;  
                if (obj.containsKey(et.getName())) {  
                    list = (List) obj.get(et.getName());  
                }  
                list.add(iterateElement(et));  
                obj.put(et.getName(), list);  
            } else {
                if (obj.containsKey(et.getName())) {  
                    list = (List) obj.get(et.getName());  
                }
                list.add(et.getTextTrim());  
                obj.put(et.getName(), list);  
            }  
        }  
        return obj;  
    }
    
    public String random(){
    	String str="";
    	
    	RandomUtils randomUtils=new RandomUtils();
    	
    	for (int i = 0; i < 15; i++) {
    		str+=randomUtils.nextInt(0,9);
		}
    	
    	return str;
    }
    
    
    private static String wxAppId="wx8653ea068146c48c";
    private static String wxMch_id="1348183401";
    private static String MchSecret="meitian13812407081shangabcd12345";
}
