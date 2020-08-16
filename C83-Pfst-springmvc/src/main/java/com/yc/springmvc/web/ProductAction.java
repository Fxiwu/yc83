package com.yc.springmvc.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yc.damaibean.DmCart;
import com.yc.damaibean.DmUser;

@RestController
public class ProductAction {

	@RequestMapping(path ="product.do" ,params="op=query")
	public String query(){
		return "query!!";
	}
	@RequestMapping(path ="product.do" ,params="op=add")
	public String add(){
		return "add!!";
	}
	//@GetMapping==@RequestMapping(method=RequestMethod.Get)
	@GetMapping(path ="product.do" ,params="op=select")
	public String select(){
		return "select!!";
	}
	@GetMapping(path ="product.do" ,params="op=mgr",headers = {"Host=localhost:8080","Cookie"})
	public String mgr(){
		return "mgr!!";
	}
	//请求参数接收
	@GetMapping("login.do")
	public String login(String user,String pwd){
		return user+":"+pwd;
	}
	
	@GetMapping("reg.do")
	public String login(DmUser user,DmCart dc){
		return user.toString()+"<br>"+dc;
	}
	
	@GetMapping("pay.do")
	public String pay(@RequestParam(
			name="userid",
			//defaultValue="1",
			required=true)
	         String uid,Double money){
		return uid+":"+money;
	}
	
	@GetMapping("findUser.do")
	public DmUser findUser(){
		DmUser du=new DmUser();
		du.setId(100);
		du.setCname("lisi");
		du.setPassword("123");
		return du;
	}
}
