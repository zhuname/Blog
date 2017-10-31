package com.cz.mts.frame.util;


import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *支付宝工具类
 */
@Component
public class AlipayUtil {
	@Autowired
	CacheManager cacheManager ;

	private static CacheManager staticCacheManager ;

	@PostConstruct
	public void  init(){
		staticCacheManager = this.cacheManager ;
	}


	/**
	 * 对支付宝回调参数进行解析
	 * @param requestParams
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> getNotifyParam(Map requestParams ) throws Exception{
		Map<String,String> params = new HashMap<String,String>();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			if(!"id".equals(name)){
				params.put(name, valueStr);
			}
		}
		return params;
	}


	public static String zfbPay(String name,String code,String money,String detail) throws Exception{
		try {
			Cache cache = staticCacheManager.getCache(GlobalStatic.cacheKey) ;
			AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","2014112800018006","MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCD91+5mCotHpXBBlyUscfzQn31YNSj5aynj8G3i0TD3ltZkG1rOl3058cKoS8SxIGF8RQ23+91CIlWJYp79siEP4F5Lis+gwJxdbLeYl1we5CxV1uR6K+Lh2Aoyhn2wH2kslXAr3cyuclH+X5EgLMSp14ECCjXLcAV+iUIEjtKnIBluxPZmHjPRgm4NWV89EQV6Cmkp/Loo6aizB3lvl1YtOIo4EFvch3S3ZA+4OgVkrr+GAqY1ajTHDpWrpNjQTZPKXvWrMZi0EcyhMsM0PU8h61tVoolOZd4pJgCW22L5c+cAfJy1Ks56cidaaD/sm0B0CrFsdTBeTd5oZCgvDw1AgMBAAECggEAShdKFE59Z4WjeJMUyZsi8s3jIDA0UqyxigYpMd6sRfgCe7+d3zUO6WB0HOCwKC5RthzLR5/ktmWLjeVX4g+FNtLc42drehZd+cumGDtL0MpfS2ILlFj6rjDcaSpG94XNRPmnMZ6SItWyT4mhPd6wK4nyO/tEysLx9KTTw7tQtKtjTL/lOyRNiLPUtAu0nCu/GWIcY9uRi1ivpJ9APrNISq5rMDzcDGQQvhseBf+6XCSYyhipC3UsWswcksjFUoLyMi5Km0dBvR2oIOgKyLP01+jOI/EYKJeuaTPB7AFWECiLpYadHGgeRMntqC4PMpN2sKeyCXdzZeC8GmQvyjjJYQKBgQDoSOLV1d44pl1wEzmNPG/Ttw8g7TU82Wm91opToFpgQlK8Veq1jld/5AzDmV9A3xGNCPeD8BsLELOuUnpWR1VN+8CRyGeNYmoFop1ko7wtDpPwMFNhk9eRqhvRDOn0RLKQw85vFeKu81IC9Q9ImKZvutgPHqW6Ctj6P0vcm/hHuQKBgQCRcIOKzAIohnaOuNvAmPVLTNq0lk2RxRmJTfOt9+pfRX6R5ZO3KPP0DuP5KNnriUAEL+E2SuYoSixT1enj9glG6y02t1LqMt28J94ihXLWDbpBmxxGM7HcDkqVYao0VxDggDOM6mADWiYgL/vW5E3xvay/M1khpAcXbxyzDHaeXQKBgCuqh/nq2nuLysTm2ErUsbD7GLz32qo1tMsbKaXO3y55SVReUmEPASPpmvyqOF3ZkVMR/HWxDWRnH2AF4TmW71JZoTnGkrDhb0zf5PamURmnQ+hDLWmKgVvAvrnt04033NmZ9ZSsaJC67Q/hQZ5EFAWs+jDdcgonP+LLQ1eVencBAoGAUSdgLx2hAq3tD7x02qY/EXJkAypvRCygEC6QD33rqJelhZF9+FEAtylr6gbyemxObUeuOlZW0Rp6W5lcdlg+WvU1eHeY65fQhIIWgFrIdkqYqKz9fOqxjyHmWi5yA9ylTTAcE8BgLN6rsYA03XbtSzvUXagwTwYw8tGMSRhmQ/0CgYBdoArbUnf8PbRy96tNjLaUBUnE9jWmwNPl36uBDfN8D4Ox6qoSjWzTiElG9GFamsmLLRXlT1gYGOGHz1rvkGOeqXik1DQh1O/WPmQMJTWXEqaWWtekJ556iemy8ww/N4W6XijyTvJtaz7gfh0tPw1qw4eWOq4eKON0CkJ09nX/hg==","json","utf8","MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhISNPWjldIZC23YUSsonx8+aUVv54fYPd3PBQAGMypl9VrLJ2rkLEt/Y8IUtJI9VqTcjz7+gNkB2wfSoARlO0ufzfZ91GYGZxmnPsI2mXfDWp3wQ+01f4XvSj6Vj/i1LlzhBlIwqCW6jIBCkvNKnV1nMcaa+eqT+D48GyG+eU1s2XsBzYoKzJHtgpSisVXl0YJe2mmUxZe8iTZ3ROpFvhXOpEdSi3xN+xX9yJIS009FAEqdNDCu2zz2GSbUQE7dJzGm+MvQr5H2O//435A5BcQYHAlhOdOXYc5oyY9rBsATlG5hJtKlrj4K7Fi6MK4fifN+SCb6HrZyXaYcShXcURQIDAQAB","RSA2");
			AlipayTradeWapPayRequest alipay_request=new AlipayTradeWapPayRequest();
			// 封装请求支付信息
			AlipayTradeWapPayModel model=new AlipayTradeWapPayModel();
			model.setOutTradeNo(code);
			model.setSubject(name);
			model.setTotalAmount(money);
			model.setBody(detail);
			model.setTimeoutExpress("2m");
			model.setProductCode("QUICK_WAP_PAY");
			alipay_request.setBizModel(model);
			// 设置异步通知地址
			alipay_request.setNotifyUrl("http://w37919765677.imwork.net/mts/system/zfb/getzfb/json");
			// 设置同步地址
			alipay_request.setReturnUrl("http://app.mtianw.com/mts/appWeb/moneyDetail/appMoneyDetail.jsp");
			// 调用SDK生成表单
			String form = alipayClient.pageExecute(alipay_request).getBody();
			StringBuffer returnHtml = new StringBuffer("");
			returnHtml.append("<html>");
			String head = "<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" pageEncoding=\"utf-8\" />";
			returnHtml.append(head);
			returnHtml.append("<title>loading</title>");
			returnHtml.append("<style type=\"text/css\">");
			returnHtml.append("body{margin:200px auto;font-family: \"宋体\", Arial;font-size: 12px;color: #369;text-align: center;}");
			returnHtml.append("#1{height:auto; width:78px; margin:0 auto;}");
			returnHtml.append("#2{height:auto; width:153px; margin:0 auto;}");
			returnHtml.append("vertical-align: bottom;}");
			returnHtml.append("</style>");
			returnHtml.append("</head>");
			returnHtml.append("<body>");
			returnHtml.append("<div id=\"3\">跳转中...</div>");
			returnHtml.append(form);
			returnHtml.append("</body>");
			returnHtml.append("</html>");
			return returnHtml.toString();
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}


}
