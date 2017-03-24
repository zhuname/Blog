package com.cz.mts.frame.util;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;


public class JPushUtil {
	public static final String APPKEY = "27f7b699b5a1d3edd28e02f3";
	public static final String MASTER = "092abab91d65bfe7f7a84da5";
	
	
	/**
	 * 单个推送通知
	 * @Description  单个推送
	 * @author wxy
	 * @param content  推送内容
	 * @param type	         自定义的type类型，与客户端对应
	 * @param id
	 * @param userId   userId
	 * @param appType  1:用户版 2：商家版 3：快递版
	 * void
	 */
	public static void sendJPushNotification(String content,String type,Integer id,Integer userId,String url){
		String message = content;
		String[] tags = {"userId"+userId};
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("platform", "all");
		
		Map<String,Object> audienceMap = new HashMap<String,Object>();
		audienceMap.put("tag", tags);
		map.put("audience", audienceMap);
		
		/*通知*/
		Map<String,Object> notificationMap = new HashMap<String,Object>();
		notificationMap.put("alert", message);
		notificationMap.put("title", "美天赏");
		Map<String,Object> androidiosextra = new HashMap<String,Object>();
		androidiosextra.put("type", type);
		androidiosextra.put("id", id);
		androidiosextra.put("url", url);
		
//		androidiosextra.put("sound", sound);
		notificationMap.put("category", "identifier");
		Map<String,Object> android = new HashMap<String,Object>();
		android.put("extras", androidiosextra);
		notificationMap.put("android", android);
		Map<String,Object> ios = new HashMap<String,Object>();
		ios.put("extras", androidiosextra);
		ios.put("alert", message);
		notificationMap.put("ios", ios);
		map.put("notification", notificationMap);		
				
		Map<String,Object> optionsMap = new HashMap<String,Object>();
		optionsMap.put("sendno", "1");
		optionsMap.put("apns_production", false);//开发环境
//		optionsMap.put("apns_production", true);//生产环境
		map.put("options", optionsMap);
		String pushpayload = new Gson().toJson(map);
		System.out.println(pushpayload);
		JPushClient jpush = null ;
		jpush = new JPushClient(MASTER, APPKEY);
		PushResult pushresult = null;
		try {
			if(jpush != null)
				pushresult = jpush.sendPush(pushpayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		try {
			if(pushresult != null && pushresult.isResultOK()){
				System.out.println("发送成功， sendNo="+pushresult.sendno+",msg_id="+pushresult.msg_id);
			}else{
				System.out.println("发送失败， 错误代码=" + pushresult.ERROR_CODE_NONE + ", 错误消息=" + pushresult.ERROR_MESSAGE_NONE);			
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("------该处报异常");
		}
		
	}
	
	/**
	 * 全局推送通知
	 * @Description 全局推送
	 * @author wxy
	 * @param content  具体内容
	 * @param type		
	 * @param orther
	 * void
	 */
	public static void sendAllPushNotification(String content,String type,Map<String, String> other,String sound){
		String message = content;	
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("platform", "all");
		
		Map<String,Object> audienceMap = new HashMap<String,Object>();	
		map.put("audience", "all");
		
		/*通知*/
		Map<String,Object> notificationMap = new HashMap<String,Object>();
		notificationMap.put("alert", message);
		notificationMap.put("title", "美天赏");
		Map<String,Object> androidiosextra = new HashMap<String,Object>();
		androidiosextra.put("type", type);
		for (Object key:other.keySet()) {			
			androidiosextra.put(key.toString(), other.get(key));
		}
		notificationMap.put("category", "identifier");
		Map<String,Object> android = new HashMap<String,Object>();
		android.put("extras", androidiosextra);
		notificationMap.put("android", android);
		Map<String,Object> ios = new HashMap<String,Object>();
		ios.put("extras", androidiosextra);
		ios.put("sound", sound);
		notificationMap.put("ios", ios);
		map.put("notification", notificationMap);		
		
		Map<String,Object> optionsMap = new HashMap<String,Object>();
		optionsMap.put("sendno", "1");
		optionsMap.put("apns_production", false);//开发环境
//		optionsMap.put("apns_production", true);//生产环境
		map.put("options", optionsMap);
		
		String pushpayload = new Gson().toJson(map);
		JPushClient jpush = null ;
		jpush = new JPushClient(MASTER, APPKEY);
		PushResult pushresult = null;
		try {
			if(jpush != null)
				pushresult = jpush.sendPush(pushpayload);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		try {
			if(pushresult.isResultOK()){
				System.out.println("发送成功， sendNo="+pushresult.sendno+",msg_id="+pushresult.msg_id);
			}else{
				System.out.println("发送失败， 错误代码=" + pushresult.ERROR_CODE_NONE + ", 错误消息=" + pushresult.ERROR_MESSAGE_NONE);			
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("------该处报异常");
		}
	}
	
	
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "47");
		map.put("url", "www.baidu.com");
		sendAllPushNotification("测试一下", "1", map, null);
	}
	 
}
