package com.yc.springmvc.web;

 import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
/*
 * 判断是否登录的拦截器
 */
 @Component
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if(request.getSession().getAttribute("loginedUser")==null) {
			//根据当前的请求方式返回不同的结果
			//判断当前页面是跳转还是ajax的请求
			String accept=request.getHeader("Accept");
		    if(accept.startsWith("application/json")) {
		    	//ajax请求
		    	response.setContentType("application/json;charset=utf-8");
		    	response.getWriter().append("{code:0,msg:'请先登录系统'}");
 		    }else {
 		    	//页面跳转请求
 		    	response.setContentType("text/html;charset=utf-8");
 		        response.sendRedirect("/login.html");
 		    }
		    return false;
		}
        //正常业务执行返回true
		return true;
	}
	 
	 
}
