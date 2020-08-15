package com.yc.spring.aop;

import java.lang.reflect.Method;

 import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
 
public class CGLIBProxy implements MethodInterceptor{

	public static void main(String[] args) {
		RealSubject rs=new RealSubject();
		Houseowner ho=new Houseowner();
		CGLIBProxy cp=new CGLIBProxy();
		RealSubject proxyRs=(RealSubject) cp.proxy(rs);
		proxyRs.say();
		
		Houseowner proxyHo=(Houseowner) cp.proxy(ho);
		proxyHo.sale();
	}
	private Object realSubject;
	public Object intercept(Object obj,Method method,Object[] args,MethodProxy methodProxy ) throws Throwable {
		System.out.println("代理类"+obj.getClass());
		System.out.println("方法名称"+method.getName());
        if(args!=null&&args.length>0) {
        	for(int i=0;i<args.length;i++) {
        		System.out.println("方法参数"+args[i]);
        	}
        }
        Object  returnvalue=null;
        returnvalue =methodProxy.invoke(this.realSubject, args);
        System.out.println("方法的返回值"+returnvalue);
		return returnvalue;
		
	}
	/*
	 * 代理方法
	 */
	public Object proxy(Object targetObject) {
		this.realSubject=targetObject;
		Enhancer enhancer=new Enhancer();
		enhancer.setSuperclass(targetObject.getClass());
		enhancer.setCallback(this);
		return enhancer.create();
		
	}
	 
	 
	 
}
