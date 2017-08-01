package com.cz.mts.system.aliyun;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.cz.mts.frame.util.HttpUtils;


public class mts {

	
	
	public static void main(String[] args) {
	    String host = "http://jisutqybmf.market.alicloudapi.com";
	    String path = "/weather/query";
	    String method = "GET";
	    String appcode = "3cfec02bee26451fa2743d25adc4a5f2";
	    Map<String, String> headers = new HashMap<String, String>();
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE " + appcode);
	    Map<String, String> querys = new HashMap<String, String>();
	    querys.put("city", "郑州");
	    /* querys.put("citycode", "citycode");
	    querys.put("cityid", "cityid");
	    querys.put("ip", "ip");
	    querys.put("location", "location");*/


	    try {
	    	/**
	    	* 重要提示如下:
	    	* HttpUtils请从
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
	    	* 下载
	    	*
	    	* 相应的依赖请参照
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
	    	*/
	    	HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
	    	System.out.println(response.toString());
	    	//获取response的body
	    	//System.out.println(EntityUtils.toString(response.getEntity()));
	    	
	    	
	    	JSONArray jsonArray = JSONArray.fromObject("["+EntityUtils.toString(response.getEntity())+"]");
	    	for (int i = 0; i < jsonArray.size(); i++) {
				
				JSONArray jsonCity = JSONArray.fromObject("["+jsonArray.getJSONObject(i).getString("result")+"]");
				System.out.println(jsonCity.toString());
				for (int k = 0; k < jsonCity.size(); k++) {
					
					System.out.print(jsonCity.getJSONObject(k).getString("weather"));
					System.out.print(jsonCity.getJSONObject(k).getString("date"));
					System.out.print(jsonCity.getJSONObject(k).getString("temphigh"));
					System.out.print(jsonCity.getJSONObject(k).getString("templow"));
				}
	        }
	    	
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	
	
	
}
