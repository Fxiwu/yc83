package com.yc.order.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

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
	@HystrixCommand(fallbackMethod ="fbUser" )
	public String user() {
		String url="http://user/user";// 系统内部的远程调用地址
		String res=restTemplate.getForObject(url,String.class);
		return res;
	}
	
	public String fbUser() {
		return "user服务接口降级回复信息";
	}
	
	//声明式服务调用
	@Resource
	IUserAction iua;
	
	@GetMapping("user1")
	public String user1() {
		return iua.user();
		
	}
}
