package com.yc.user.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
	public String user(HttpServletRequest req) {
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
