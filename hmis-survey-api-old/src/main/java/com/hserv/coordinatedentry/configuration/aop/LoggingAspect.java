package com.hserv.coordinatedentry.configuration.aop;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


@Aspect
public class LoggingAspect {

	@Around(value = "loggingPointCut()")
	public Object loggableAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
		System.out.println("Method called : " + proceedingJoinPoint.getSignature() +" \nParameters :");
		
		Object[] params = proceedingJoinPoint.getArgs();
		if(params!=null && params.length>0)
		for (int i = 0; i < params.length; i++) {
			System.out.print(params[i].toString()+"\t");
		}
		long startTime= new Date().getTime();
		Object result = null;
		
		try {
			result = proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		long endTime= new Date().getTime();
		System.out.println("Time taken to complete the operation : " + (endTime-startTime) +" ms");
		return result;
		
	}
	
	//@Pointcut(value="execution(* app.service.*.*(..)) || execution(* app.controller.*.*(..))")
	@Pointcut(value="execution(* com.hserv.coordinatedentry.service.*.*(..))")
	public void loggingPointCut(){
		
	}
}
