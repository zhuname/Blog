package com.cz.mts.frame.util;

import java.rmi.ServerException;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsRequest;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsResponse;

import net.sf.json.JSONObject;



public class SMSUtil {
	
	public static final String serviceURL = "https://sms.aliyuncs.com/?Action=SingleSendSms";
	public static final String SignName = "美天赏";
	public static final String TemplateCode1 = "SMS_57370135";
	public static final String TemplateCode2 = "SMS_57355139";
	public static final String TemplateCode3 = "SMS_57285091";
	public static final String TemplateCode4 = "SMS_57385113";
	public static final String TemplateCode5 = "SMS_57430047";
	

	
	/**
	 * 发送短信
	 * @param mobile：手机号
	 * @param content：验证码
	 * @return
	 * @throws Exception
	 */
	public static String SendSMS(String mobile, String content,String type) throws Exception {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("code", content);
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI3ugrkiVbqkuF", "AGQJXuWCxS0oOoavMkqcybdF5wTzTX");
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Sms",  "sms.aliyuncs.com");
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendSmsRequest request = new SingleSendSmsRequest();
        try {
        	if("1".equals(type)){
				request.setTemplateCode(TemplateCode1); 
			}
			if("2".equals(type)){
				request.setTemplateCode(TemplateCode2); 
			}
			if("3".equals(type)){
				request.setTemplateCode(TemplateCode3); 
			}
			if("4".equals(type)){
				request.setTemplateCode(TemplateCode4); 
			}
			if("5".equals(type)){
				request.setTemplateCode(TemplateCode5); 
			}
			
			request.setSignName(SignName);   
            request.setParamString(jsonObj.toString());
            request.setRecNum(mobile);
            System.out.println("-------->"+request.getSignName());
            SingleSendSmsResponse httpResponse = client.getAcsResponse(request);
//            System.out.println(httpResponse.getModel());
//            System.out.println(httpResponse.getRequestId());
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
	}

	
	public static void main(String[] args) throws Exception {
		SMSUtil smsUtil = new SMSUtil();
		String status =  smsUtil.SendSMS("18538036976", "123456","1");
//		System.out.println(status);
//		JSONObject jsonObj = new JSONObject();
//		jsonObj.put("code", "123456");
//		jsonObj.put("product", "ECCare");
//		System.out.println(jsonObj.toString());
	}
}
