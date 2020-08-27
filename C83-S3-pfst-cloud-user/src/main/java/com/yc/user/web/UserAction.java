package com.yc.user.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
 

@RestController
public class UserAction {

	@Resource
	private RestTemplate restTemplate;
	
	@GetMapping("user")
	public String user(HttpServletRequest req) {
		return String.format("server:User;ip:%s;port:%s",req.getLocalAddr(),req.getLocalPort()) ;
		 
	}
	
	@GetMapping("order")
	public String order() {
		String url="http://order/order";
		String res=restTemplate.getForObject(url, String.class);
	return res;
	}
	
}
