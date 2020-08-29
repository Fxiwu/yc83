package com.yc.user.web;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
 

@RestController
public class UserAction {

	@Resource
	private RestTemplate restTemplate;
	
	@GetMapping("user")
	public String user(HttpServletRequest req, HttpServletResponse resp,HttpSession session)  {

		System.out.println(req.getHeader("Cookie"));
		System.out.println(req.getHeader("Authorization"));
		if(session.getAttribute("loginedUser") == null) {
			session.setAttribute("loginedUser", "100");
			System.out.println("=========用户未登录=========");
		} else {
			System.out.println("=========用户已登录=========");
		}
		
		// 添加cookie ==> Set-Cookie
		Cookie cookie = new Cookie("test","test");
		resp.addCookie(cookie);
		return String.format("server:User;ip:%s;port:%s",req.getLocalAddr(),req.getLocalPort()) ;
		 
	}
	
	@GetMapping("order")
	@HystrixCommand(fallbackMethod = "fbOrder")
	public String order() {
		String url="http://order/order";
		String res=restTemplate.getForObject(url, String.class);
	return res;
	}
	public String fbOrder() {
		return "order服务接口降级回复信息";
	}
	
	//声明式调用
	@Resource
	IOrderAction ioa;
	@GetMapping("order1")
	public String order1() {
		 
	return ioa.order();
	}
}
