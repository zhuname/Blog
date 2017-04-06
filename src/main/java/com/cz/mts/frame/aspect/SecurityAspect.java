package com.cz.mts.frame.aspect;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.servlet.http.HttpServletRequest;

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
	private String privateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAN1u6AJ+BhiqjVd7+a3/Bb12RKY1ghHDharBtjux2xqj3yME5aot3UGVmsiVScnt5ixuBOuHWr644qpGhtCBuw7B2qzaANJapoFo2utjijF4Ic5TLjZj6bj5RsAo0Ye4meukYW6iTdMNpUKpgzUewl3wvpezYFz9aIuTR+bPUXBpAgMBAAECgYAYLaf/vasEvnq4dr0IlrQVv/g2/di5/9N0lbpEQmNeQU54EGzTbDaglqlUBZkNB4D+kPTQkvMBqEZ3uUO3OLMn8Vwbr9dDMKMEcL7Q/03L6jrNXkehSTtftGMHNykTzq2yoUKpmGTRn42ytKdCEMg2B/jV/HWbyQuD+pIra/o8QQJBAO7EqApWqbzPH9nrQJyqPfLsElnQhQXZMwxTGNJPWOPaFFWjQQJiLJIooEXCDDwQaP8/ngQgjWVShJjf0uCfEhUCQQDtafqnNLX/7TyBObqVsVRlWGDIZCXRO9kSxk5lhfC8Xn0NA+hBrTAgya7BRP9i1A5ocyvde+ccMHapeuU0wT4FAkEAvJQjqC60VsQ8AtBUMqgez2r2P2t6dJkMQXKsCwbSTPtjzhNnd+t0Cmf91bqiERpg4yAqvFW9d0YKJfe5ROE91QJBAM2i9v1u9ZlYJgkUNJ3F/ADQHPxVakAh43KUm4DqE8tE2HG2iayhUseDiT9UARTb8iLcZwkvcc2D2LQRg4g/PPUCQQCyF0F98JE+jLR/nUFr2QNCUEQABDj7a0VM/ptRktoJAB97oJ+La4OeIdNJeRMzr4VAPqvtSWuLY38o7G2GIIK0" ;
	

	@Pointcut("@annotation(com.cz.mts.frame.annotation.SecurityApi)")
	public void securityAop(){}
	
	
	
	@Before("securityAop() && args(joinpoint)")
	public void  securityBefore(Joinpoint joinpoint){
		
	}
	
	@AfterReturning(pointcut = "securityAop()",
			returning = "returnDatas")
	public void securityAfter(ReturnDatas returnDatas){
		
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
	    	return new ReturnDatas(ReturnDatas.ERROR, "非法请求") ;
	    }else {
	    	try {
	    		String[] sign = (String[])paramMap.get("signCode") ;
	    		//解密
        		String params=SecureRSA.decrypt(sign[0], privateKey, "UTF-8") ;   //公钥
        		JSONObject json = JSONObject.fromObject(params) ;
        		//验证时间戳，防止爬虫请求
        		if(!json.containsKey("T")){
	            	return new ReturnDatas(ReturnDatas.ERROR, "非法请求") ;
        		}else {
        			
        			Long T = json.getLong("T") ;
					Date legalTime = DateUtils.addMinutes(new Date(), -10) ;
					if(T < Double.valueOf(DateFormatUtils.format(legalTime, "yyyyMMddHHmmss"))) {  //说明请求时间差超过10分钟，不是合法的
						return new ReturnDatas(ReturnDatas.ERROR, "通讯超时") ; 
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
        			object = proceedingJoinPoint.proceed() ;
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
