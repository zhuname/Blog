package com.cz.mts.frame.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;



public class SMSUtil {
	
	public static final String serviceURL = "http://sdk.entinfo.cn:8061/mdsmssend.ashx";
	public static final String serviceURL2 = "";
	public static final String Sn = "SDK-BSY-010-00094";
	public static final String password = "649309";
	public static final String Pwd = SecUtils.encoderByMd5With32Bit(Sn+password).toUpperCase();
	
	//发送短信
	public static String SendSMS(String mobile, String content) throws Exception {
		String smsUrl = serviceURL+"?Sn="+Sn+"&Pwd="+Pwd+"&mobile="+mobile+"&content="+URLEncoder.encode(content,"UTF-8");
		URL url = new URL(smsUrl);
 		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
 		conn.setConnectTimeout(5000);		
 		conn.setRequestMethod("GET");
 		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded,charset=UTF-8");
 		conn.setRequestProperty("Accept-Charset", "UTF-8");
 		conn.setRequestProperty("contentType", "UTF-8");
 		String json = "";
 		if (conn.getResponseCode() == 200) {
 			InputStream inStream = conn.getInputStream();
 			byte[] data = readInputStream(inStream);
 	 		 json = new String(data);
 		}
		System.out.println(smsUrl);
 		return json;
	}
	
	  public static byte[] readInputStream(InputStream instream) throws Exception {
	 		ByteArrayOutputStream outStream = new ByteArrayOutputStream();//读到的数据放到内存中
	 		byte []buffer = new  byte[1024];
	 		int len = 0;
	 		while((len = instream.read(buffer)) !=-1){
	 			outStream.write(buffer, 0, len);//往内存中写入数据
	 		}
	 		instream.close();
	 	 return outStream.toByteArray();
	 	}

	
	public static void main(String[] args) throws Exception {
		SMSUtil smsUtil = new SMSUtil();
		String status =  smsUtil.SendSMS("18339956750", "【幸福城市】");
		System.out.println(status);
	}
}
