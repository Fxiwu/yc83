package com.yc.springmvc.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//@Controller 注解  表示该类所有的方法返回值是跳转到某个页面

//@RestController 注解  表示该类所有的方法返回值是json数据
@RestController
public class HelloAction {

	@RequestMapping("hello")
	public String hello(){
		return "hello world";
	}
	
}
