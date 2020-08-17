package com.yc.springmvc.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yc.damaibean.DmCart;
import com.yc.damaibean.DmUser;

@RestController
public class ProductAction {

  /*
   * path  ==value   互为同义词
   * method  定义响应请求的类型  例如  get Post ,DELETE。。。
   * params   定义参数的限定
   *           例如   a=100 （限定请求中必须有a=100的参数）
   *           a   （限定请求中必须有a 的参数）
   *headers   限定请求头域中的字段值，例如 ：Cookie
   *consumes  消费 ：限定请求中的类型
   *produces    产品：限定返回内容的类型           
   *        
   */
	
	
	@RequestMapping(path ="product.do" ,params="op=query")
	public String query(){
		return "query!!";
	}
	@RequestMapping(path ="product.do" ,params="op=add")
	public String add(){
		return "add!!";
	}
	@RequestMapping(path ="product.do" ,params="op=mod",method = RequestMethod.POST)
	public String mod(){
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
	//===========请求参数接收
	
	/*
	 * SpringMVC  对于简单请求参数  数字，字符串，布尔
	 * 
	 * 可以直接将请求参数注入到方法参数中
	 * 必须满足：
	 *   1.方法参数名==请求参数名
	 *   2.类型要兼容
	 * 
	 */
	@GetMapping("login.do")
	public String login(String user,String pwd){
		return user+":"+pwd;
	}
	/*
	 * 如果对象接受请求参数，对象的属性名与请求参数名称映射
	 *请求参数==》对象 装箱  
	 *
	 * 对于特殊类型对象（日期)，需要springmvc转换器进行值的转换
	 * 
	 */
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
