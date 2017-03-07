package com.cz.mts.system.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SecurityAspect {

	@Pointcut("execution(* com.cz.mts.system.web.*Controller.*json(..))")
	public void securityAop(){}
	
	@After("securityAop()")
	public void securityAfter(){
		System.out.println("*******这是一个切面**********");
	}
}
