package com.yc.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
 
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {
   //
	//
	//
	 
	@Pointcut(("execution( * com.yc.spring.dao.*Dao.select*(..))"))
    public void aspect1() {/* 切点方法*/}
	@Before("aspect1()")
	//JoinPoint 连接点对象  ==》方法    ==》反射对象
	//接口注入 JoinPoint对象
	public void before(JoinPoint jp) {
        jp.getArgs();//方法参数
        jp.getSignature();//方法签名
        System.out.println("======前置增强=======");
    }
	
	@After("aspect1()")
	public void after(JoinPoint jp) {
         
        System.out.println("======后置增强=======");
    }
	
	@AfterReturning(value="aspect1()",returning = "ret")
	public void afterReturning(JoinPoint jp,Object ret) {
         
        System.out.println("======返回增强==="+ret+"====");
    }
	
	@AfterThrowing(value="aspect1()",throwing ="ret")
	public void afterThrowing(JoinPoint jp,Exception ret) {
         
        System.out.println("======异常增强==="+ret+"====");
    }
	
	/*
	 * 环绕增强，业务需要自己执行
	 */
	@Around("execution(* com.yc.spring.dao.Oracle*.*(..))")
	public Object  around(ProceedingJoinPoint pjp) {
		Object ret=null;
		try {
			//调用业务对象的方法
			long begin=System.currentTimeMillis();
			
			ret=pjp.proceed();
			
			long end=System.currentTimeMillis();

			System.out.println("一共消耗了"+((end-begin)/1000)+"秒");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	
	
	@After("execution(* com.yc.spring.Hello.*(..))")
	public void afterForHello(JoinPoint jp ) {
         
        System.out.println("======Hello===后置增强====");
    }
	
}
