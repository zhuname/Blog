package com.cz.mts.system.web;
import java.util.List;
import java.util.UUID;
import java.util.Map;
import java.util.HashMap;
import java.util.Formatter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;  

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.Page;
import com.cz.mts.frame.util.ReturnDatas;
import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.entity.Attention;
import com.cz.mts.system.entity.Medal;
import com.cz.mts.system.entity.UserMedal;
import com.cz.mts.system.entity.WxBean;


@Controller
@RequestMapping(value="/system/wxShare")
class Sign {
    public static void main(String[] args) {
        String jsapi_ticket = "jsapi_ticket";

       
        String url = "http://example.com";
        Map<String, String> ret = sign(jsapi_ticket, url);
        for (Map.Entry entry : ret.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
    };

    @RequestMapping("/ceshi/json")
    public void ceshi(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	
    	 String jsapi_ticket = getTicket();

         
         String url = "http://app.mtianw.com";
         Map<String, String> ret = sign(jsapi_ticket, url);
         String ticket="{";
         for (Map.Entry entry : ret.entrySet()) {
        	 ticket+="\""+entry.getKey()+"\":\""+entry.getValue()+"\",";
         }
         
         ticket=ticket.substring(0, ticket.length()-1);
         
         ticket+="}";
         response.getWriter().write(ticket);  
    }
    
    public String getTicket() throws Exception{
		
		  CloseableHttpClient httpclient = HttpClients.createDefault();  
		  WxBean wxBean = null ;
	        try {
	            // 创建httpget.
	        	String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+getAccessToken()+"&type=jsapi" ;
	            HttpGet httpget = new HttpGet(url);
	            System.out.println("executing request " + httpget.getURI());  
	            // 执行get请求.    
	            CloseableHttpResponse httpResponse = httpclient.execute(httpget);  
	            HttpEntity entity = httpResponse.getEntity();  
	            try {  
	                // 获取响应实体    
	                System.out.println("--------------------------------------");  
	                // 打印响应状态    
	                System.out.println(httpResponse.getStatusLine());  
	                if (entity != null) { 
	                	String string=EntityUtils.toString(entity);
	                	new JSONObject();
						//解析出来获取Unionid的json
	                	JSONObject obj = JSONObject.fromObject(string);//将json字符串转换为json对象
	                	wxBean = (WxBean)JSONObject.toBean(obj,WxBean.class);//将建json对象转换为Person对象
	            		// 打印响应内容长度    
	            		//System.out.println("Response content length: " + entity.getContentLength());  
	            		// 打印响应内容
	            		//System.out.println("Response content: " + EntityUtils.toString(entity));
	            		httpResponse.close();  
	                }  
	                System.out.println("------------------------------------");  
	            }catch (Exception e){
	            	e.printStackTrace();
	            }
	        } catch (ClientProtocolException e) {  
	            e.printStackTrace();  
	        } catch (ParseException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {  
	            // 关闭连接,释放资源    
	            try {  
	                httpclient.close();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }
	        }  
		return wxBean.getTicket();
	}
    
    public String getAccessToken() throws Exception{
		
		  CloseableHttpClient httpclient = HttpClients.createDefault();  
		  WxBean wxBean = null ;
	        try {
	            // 创建httpget.
	        	String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx8653ea068146c48c&secret=14392f71468a99159688155f5aa98e38" ;
	            HttpGet httpget = new HttpGet(url);
	            System.out.println("executing request " + httpget.getURI());  
	            // 执行get请求.    
	            CloseableHttpResponse httpResponse = httpclient.execute(httpget);  
	            HttpEntity entity = httpResponse.getEntity();  
	            try {  
	                // 获取响应实体    
	                System.out.println("--------------------------------------");  
	                // 打印响应状态    
	                System.out.println(httpResponse.getStatusLine());  
	                if (entity != null) { 
	                	String string=EntityUtils.toString(entity);
	                	new JSONObject();
						//解析出来获取Unionid的json
	                	JSONObject obj = JSONObject.fromObject(string);//将json字符串转换为json对象
	                	wxBean = (WxBean)JSONObject.toBean(obj,WxBean.class);//将建json对象转换为Person对象
	            		// 打印响应内容长度    
	            		//System.out.println("Response content length: " + entity.getContentLength());  
	            		// 打印响应内容
	            		//System.out.println("Response content: " + EntityUtils.toString(entity));
	            		httpResponse.close();  
	                }  
	                System.out.println("------------------------------------");  
	            }catch (Exception e){
	            	e.printStackTrace();
	            }
	        } catch (ClientProtocolException e) {  
	            e.printStackTrace();  
	        } catch (ParseException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {  
	            // 关闭连接,释放资源    
	            try {  
	                httpclient.close();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }
	        }  
  		return wxBean.getAccess_token();
	}
    
    
    public static Map<String, String> sign(String jsapi_ticket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
        System.out.println(string1);

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
