package com.cz.mts.frame.aspect;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.aopalliance.intercept.Joinpoint;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cz.mts.frame.util.Des3;
import com.cz.mts.frame.util.GlobalStatic;
import com.cz.mts.frame.util.JsonUtils;
import com.cz.mts.frame.util.ReturnDatas;
import com.cz.mts.frame.util.SecureRSA;
import com.cz.mts.servlet.ParameterRequestWrapper;
import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.service.IAppUserService;

/**
 * 对app端返回数据进行加密
 * @author Michael
 *
 */
@Component
@Aspect
public class SecurityAspect {
	
	//私钥
//	private String privateKey = "MIICWgIBAAKBgFFYdVx1HDZKaQRHBm+FkwsA8jvZQA3eAWxXie7bT+puNfKtLp5e3l/qVXjPMX31sg87Mr8OsIjE+OQIhe+/tV/u+tAAKdzkGGqVruVNMhFuqvX9Hn+/j2cwNOrQEDBly7b56/QNlHS7i6bnB4LxyD3obo7VzCMwkP1YBReh9X9/AgMBAAECgYA6dM8h+iaj/SUqpb/CNMNOjQeGasDVNkzfhqjgtUngtgKeukVeGd7EHqn9fyeZ1Q54U5pMIkpKfwI9HMLjX2j/EC6ObRYwN7YmnhOA7g6/MNi+Btvu+sgFR2Kf3Ia2DGveLwb6wmdtIPJp6CiUvvviY0me3LZnS/duTeVoX6TGAQJBAJaHBxBCLG2EW2pcp2RAvPOOKRiE0kFP+8o4n1oTosX7fs87JdlYAf43ZrxcmxEjyRO3jGL8pnl4+BFJ12tdgd8CQQCKV9/r1fY5wDTOCHO8ls7O6OJ2ydP8JOSYmnTG4Ko4hkeWoy9MyyuulwdyiS2AGdERh44XBvP7w7urbI0cpfZhAkApVEWiNykPoMmguHPVWNkIXj32V3GLMTTG3ykRiFam2ViF+Y140Wsqq3dvvFVvLU4mNb076Hak34vs40NcXT4tAkBaq5dmEViMpy20r5NzUf//WmE667LVOjTc07afthW6cD+xOgjBMxPRHMlTxxacM89zxr1Y2ETGiJWSl9WwphFhAkAPzcT9Jn53JxpN7uPwgGqYvnmlnjTGB0U/lhnNiT6u93O0UO2k8zOagx1V6fWdU6Md9AcLuP9gzttQvUOI/mbK" ;
//	private String privateKey = "MIICxjBABgkqhkiG9w0BBQ0wMzAbBgkqhkiG9w0BBQwwDgQI+QSWa3knLiUCAggAMBQGCCqGSIb3DQMHBAgdrXruIMjDRwSCAoA51xauqrknwKORBu2BoaKiCc/S5Uzk7gpVKjmDA/ceQ9o++Q89m4FzPfCSWF5KbI4sDGKpLJWdDJDVibZkFMP4t+goUsKN/tsy1WbYKhjdS/okqVT3q02GqrWdb1EMuDQnJDTuxmG8hjXXzh7Bafr8nnkN5XtPESaNQEX1q1kdvJkqe6B4/lS4yM+zxKJEgYy0u1lXPVOQ1CksXyRuFFkiF0YXeklhb/IwFCsUZxsXdNhAiS9exutthsv5yDFZ5inJh0M6JtdJ5cZqbaNgU54uyGxXJ5VKjqtI9hTe9F+ZSV7J/NhBsam24KNW/nFVhzPOjwtOaYFdYIckpLVHPQF78rGhWGcpW/rahmI4mP5dxpKTV1OU9xdh454EwdCe14tP2DpoIBUMU1Qb3lp1xKzKc2DOIWARMDbyeW8O5OVGpKIA43mc1H9cJQzbQG//HyhwnfrPDtVP2ZAcaFkqaNKrxif6eI+lKsJwmi26M/Gwocy+2t9nBneDx2n1B0wG6ecz3FDUZJQ5O7Eizbd8WtFGYUyMH3Not8TqOAsrQMW06Z4ENgja4zqoUpug4YVM7U2KbusTa6WHSp3nmomLi+TsUe0hayR8L+orcZd8UYBr5IQizJeLGURQ3g+tGUU0EHdQv58h/jIzzmn6+890kCvuA9WkWaKF2ZentGAymkdtH6RJr9aRB5zlgH7h4tnbr6a9HV/V79FFqLXDH7LU4TZm7GtzEST5BFkU5k2n4CGrTYUyVkwUORalrVN4IKO7tmJ9+CKEW3p2vhVtmzyzLCaMcKQ7HuaVpXIPAfe7fHiV4hZ9pzc/feY0OuxqD2QAlMf7b5visf1Y+0HyOZ1YPrNC" ;
	private String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAK/LECwRJXm77b7ezATdBAnDceTNvapU2aUOSwlUWSqTY75f53m0eTh4IfHPV2zN4FwYh2zN+T5QDI1LeNm4xn6BhGY+CJFwqWuICSD+vmb/5lp7MhMN5mmrR/ljkQVduWN3z/vydQl4Uuj9GoXBmgSpMQwCBoVhvXObMkGznRGXAgMBAAECgYBWlsYzfoqgCMJVZvbFWnw3tu5tzi2jLcI6u+yX/tUg1JKn3iNtXkjrWLkXm5KrhEkD9A0YuegQGmvkWQ2A6LPUHsb5it2pXJ2TR38QypQWv9PQjgVrci+uW7NSAukyQuUaOr1M0NSoul7VCWSxHUQ8oRHa/kOwD1jk6Cn5ZSpA4QJBAPenRXkrzfAGZFHMbXq9WhV3oBFUFGb9D3N8XUHjCtCDs+jXS5QxBYpn/8R17mZS8fWA4Y3ckgjYB1S54jaT/2cCQQC1t8oRTzZ9k4Jgs5M31XyDMMwFLvnTVh71kkqhIHXF2HCJgtrv92lbw7lP6asos/MnChJkYIZWEsZQ7ml4vW5RAkBSLaRlD/8XPde+OPq/NuQUqYxpE6BtiF2/Ma50dZWaGYnyoraGpzmycs4xb/y64Z0+nRS7kUy4OI8+Myx4deXTAkBIvB/tfb9Z1ElDW515o8IK4OVIzdzBcp9BvCSTCeMaJeAlqnLnyigpytXJ3sXbrOuOnMFv3BLpMOqgSk19PpWBAkBIEKVWc8+umWOqSUqIYhzrGEpB+lpUq3/y7HfwMee0G+DN5LeAi1WpT74P2sT2rwZLgIAiAtVEXPNeI53DXdjH" ;
	

	@Pointcut("@annotation(com.cz.mts.frame.annotation.SecurityApi)")
	public void securityAop(){}
	
	
	
	@Before("securityAop() && args(joinpoint)")
	public void  securityBefore(Joinpoint joinpoint){
		
	}
	
	@AfterReturning(pointcut = "securityAop()",
			returning = "returnDatas")
	public void securityAfter(ReturnDatas returnDatas){
		
		
    	
		Map<String,String[]> paramMap = new HashMap(request.getParameterMap()) ;
    	if(paramMap.containsKey("web")){
    		return;
    	}
		
		if(returnDatas != null && returnDatas.getData() != null){
			//返回数据
			String result = JsonUtils.writeValueAsString(returnDatas.getData()) ;
			
			String encodeData = null;
			try {
				encodeData = Des3.encode(result);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				returnDatas.setStatus(ReturnDatas.WARNING);
				returnDatas.setMessage("加密失败！");
			}
			returnDatas.setData(encodeData);
		}
	}
	
	@Autowired  
	HttpServletRequest request; 
	@Autowired
	IAppUserService appUserService ;
	@Autowired  
	HttpSession session;  
	
	@Around("securityAop()")
	public Object around(ProceedingJoinPoint proceedingJoinPoint){
		Object object = null ;
		Object[] args = proceedingJoinPoint.getArgs() ;
//		HttpServletRequest request =  (HttpServletRequest) args[0] ;
		
	    Map<String,String[]> paramMap = new HashMap(request.getParameterMap()) ;
//		ServletRequestAttributes attr = (ServletRequestAttributes)  
//	               RequestContextHolder.currentRequestAttributes();  
//	       HttpServletRequest request = attr.getRequest(); 
//	    Map<String,String[]> paramMap = HttpUtil.getRequestMap(request) ;
	    
	    if(!paramMap.containsKey("signCode")){  //说明是非法请求
	    	
	    	
	    	if(session.getAttribute("appUserSessionId")!=null||paramMap.containsKey("web")){
	    		try {
					object = proceedingJoinPoint.proceed() ;
					return object ;
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return object;
				}
	    	}
	    	
	    	return new ReturnDatas(ReturnDatas.ERROR, "非法请求");
	    }else {
	    	try {
	    		String[] sign = (String[])paramMap.get("signCode") ;
	    		//解密
        		String params=SecureRSA.decrypt(sign[0], privateKey, "UTF-8") ;   //公钥
        		JSONObject json = JSONObject.fromObject(params) ;
        		//验证时间戳，防止爬虫请求
        		if(!json.containsKey("T")){
	            	return new ReturnDatas(ReturnDatas.ERROR, "非法请求");
        		}else {
        			
        			Long T = json.getLong("T") ;
					Date legalTime = DateUtils.addMinutes(new Date(), -10) ;
					if(T < Double.valueOf(DateFormatUtils.format(legalTime, "yyyyMMddHHmmss"))) {  //说明请求时间差超过10分钟，不是合法的
						return new ReturnDatas(ReturnDatas.ERROR, "通讯超时"); 
					}
					
					if(json.containsKey("sessionId")){
						Integer userId = json.getInt("sessionId") ;
						AppUser user = appUserService.findAppUserById(userId) ;
						if(user != null){
							if(null != user.getIsBlack() && user.getIsBlack() == 1){  //黑名单
								return new ReturnDatas(ReturnDatas.Black, "黑名单成员！") ; 
							}
						}
					}
        			
        			
//        			Iterator<String> keys = json.keys() ;
//        			String key  = "" ;
//        			while(keys.hasNext()){
//        				key = keys.next() ;
//        				if(key.equals("T")){
//        					Long T = json.getLong(key) ;
//        					Date legalTime = DateUtils.addMinutes(new Date(), -10) ;
//        					if(T < Double.valueOf(DateFormatUtils.format(legalTime, "yyyyMMddHHmmss"))) {  //说明请求时间差超过10分钟，不是合法的
////        						return new ReturnDatas(ReturnDatas.ERROR, "通讯超时") ; 
//        					}
//        				}
//        				paramMap.put(key, new String[]{json.get(key).toString()}) ; ;
//        			}
//        			ac.setParameters(parameters);
//        			paramMap.put("sign",new String[]{"1"}) ;
//        			HttpServletRequest req = new ParameterRequestWrapper(request, paramMap) ;
//        			args[0] = req ;
        			try {
						object = proceedingJoinPoint.proceed() ;
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        			}
//	    		object = proceedingJoinPoint.proceed() ;
	    	}catch ( BadPaddingException e){
	    		return new ReturnDatas(ReturnDatas.ERROR, "非法请求") ;
	    	}catch (Throwable e) {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    	}
	    }
		
		return object ;
	}
}
