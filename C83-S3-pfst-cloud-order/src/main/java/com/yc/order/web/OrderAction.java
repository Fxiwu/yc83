package com.yc.order.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderAction {

	@Resource
	private RestTemplate restTemplate;
	
	@GetMapping("order")
	public String order(HttpServletRequest req) {
		return String.format("server:Order;ip:%s;port:%s",req.getLocalAddr(),req.getLocalPort()) ;
		 
	}
	//RetsTemplate调用
	@GetMapping("user")
	public String user() {
		String url="http://user/user";
		String res=restTemplate.getForObject(url,String.class);
		return res;
	}
	
	//声明式服务调用
	@Resource
	IUserAction iua;
	
	@GetMapping("user1")
	public String user1() {
		return iua.user();
		
	}
}
