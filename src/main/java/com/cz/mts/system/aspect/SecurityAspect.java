package com.cz.mts.system.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;





import com.cz.mts.frame.util.Des3;
import com.cz.mts.frame.util.GlobalStatic;
import com.cz.mts.frame.util.JsonUtils;
import com.cz.mts.frame.util.ReturnDatas;

/**
 * 对app端返回数据进行加密
 * @author Michael
 *
 */
@Component
@Aspect
public class SecurityAspect {

	@Pointcut("")
	public void securityAop(){}
	
	@AfterReturning(pointcut = "execution(* com.cz.mts.system.web.*Controller.*json(..))",
			returning = "returnDatas")
	public void securityAfter(ReturnDatas returnDatas){
		
//		if(returnDatas != null && returnDatas.getData() != null){
//			//返回数据
//			String result = JsonUtils.writeValueAsString(returnDatas.getData()) ;
//			
//			String encodeData = null;
//			try {
//				encodeData = Des3.encode(result);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				returnDatas.setStatus(ReturnDatas.WARNING);
//				returnDatas.setMessage("加密失败！");
//			}
//			returnDatas.setData(encodeData);
//		}
	}
}
