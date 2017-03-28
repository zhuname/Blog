package com.cz.mts.frame.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

import com.cz.mts.frame.util.JsonUtils;
import com.cz.mts.frame.util.ReturnDatas;
import com.cz.mts.frame.util.SecureRSA;
import com.cz.mts.servlet.ParameterRequestWrapper;


public class SecurityFilter implements Filter{
	//私钥
	private String privateKey ;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		this.privateKey = null ;
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
//		HttpServletRequest httpRequest = new ParameterRequestWrapper((HttpServletRequest)request);
		
		Map<String, Object> paramMap = new HashMap(httpRequest.getParameterMap());
		  if(paramMap.containsKey("sign")){  //说明是非法请求
		    	try {
		    		String[] sign = (String[])paramMap.get("sign") ;
		    		//解密
	        		String params=SecureRSA.decrypt(sign[0], privateKey, "UTF-8") ;   //物业公钥
	        		JSONObject json = JSONObject.fromObject(params) ;
	        		//验证时间戳，防止爬虫请求
	        		if(!json.containsKey("T")){
//	        			PrintWriter out=response.getWriter(); 
	        			
//	        			out.print(JsonUtils.writeValueAsString(new ReturnDatas(ReturnDatas.ERROR, "非法请求")));
	        		}else {
	        			Iterator<String> keys = json.keys() ;
	        			String key  = "" ;
	        			while(keys.hasNext()){
	        				key = keys.next() ;
	        				if(key.equals("T")){
	        					Long T = json.getLong(key) ;
	        					Date legalTime = DateUtils.addMinutes(new Date(), -10) ;
	        					if(T < Double.valueOf(DateFormatUtils.format(legalTime, "yyyyMMddHHmmss"))) {  //说明请求时间差超过10分钟，不是合法的
//	        						return new ReturnDatas(ReturnDatas.ERROR, "通讯超时") ; 
	        					}
	        				}
	        				paramMap.put(key, json.get(key).toString()) ; ;
	        			}
//	        			ac.setParameters(parameters);
	        			HttpServletRequest req =  new ParameterRequestWrapper(httpRequest, paramMap) ;
	        			chain.doFilter(req, response);
	        		}
		    	} catch (Throwable e) {
		    		// TODO Auto-generated catch block
		    		e.printStackTrace();
		    	}
		    }
		  chain.doFilter(httpRequest, response);
		  
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("*******************************sfsfuagfuagfg");
	    privateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAN1u6AJ+BhiqjVd7+a3/Bb12RKY1ghHDharBtjux2xqj3yME5aot3UGVmsiVScnt5ixuBOuHWr644qpGhtCBuw7B2qzaANJapoFo2utjijF4Ic5TLjZj6bj5RsAo0Ye4meukYW6iTdMNpUKpgzUewl3wvpezYFz9aIuTR+bPUXBpAgMBAAECgYAYLaf/vasEvnq4dr0IlrQVv/g2/di5/9N0lbpEQmNeQU54EGzTbDaglqlUBZkNB4D+kPTQkvMBqEZ3uUO3OLMn8Vwbr9dDMKMEcL7Q/03L6jrNXkehSTtftGMHNykTzq2yoUKpmGTRn42ytKdCEMg2B/jV/HWbyQuD+pIra/o8QQJBAO7EqApWqbzPH9nrQJyqPfLsElnQhQXZMwxTGNJPWOPaFFWjQQJiLJIooEXCDDwQaP8/ngQgjWVShJjf0uCfEhUCQQDtafqnNLX/7TyBObqVsVRlWGDIZCXRO9kSxk5lhfC8Xn0NA+hBrTAgya7BRP9i1A5ocyvde+ccMHapeuU0wT4FAkEAvJQjqC60VsQ8AtBUMqgez2r2P2t6dJkMQXKsCwbSTPtjzhNnd+t0Cmf91bqiERpg4yAqvFW9d0YKJfe5ROE91QJBAM2i9v1u9ZlYJgkUNJ3F/ADQHPxVakAh43KUm4DqE8tE2HG2iayhUseDiT9UARTb8iLcZwkvcc2D2LQRg4g/PPUCQQCyF0F98JE+jLR/nUFr2QNCUEQABDj7a0VM/ptRktoJAB97oJ+La4OeIdNJeRMzr4VAPqvtSWuLY38o7G2GIIK0" ;
		
	}

}
