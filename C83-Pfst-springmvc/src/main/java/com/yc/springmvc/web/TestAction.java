package com.yc.springmvc.web;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.yc.damaibean.DmUser;

@RestController
//@SessionAttributes(name="loginUser" ,types="Date.class")
/*
 * @SessionAttributes
 * names 用于监控数据模型中出现指定名字的对象
 * types 用于监控数据模型中出现指定类型的对象
 */
@RequestMapping("user")
//@RequestMapping在类上定义用于定义该类所有的共同父路径
public class TestAction {
	@RequestMapping("add")
	public String add() {
		return "add";
	}
	@RequestMapping("?/del")
	//1个字符
	//user/a/del  user/b/del
	public String del() {
		return "del";
	}
	@RequestMapping("*/mod")
	//1-N个字符
	//user/a/mod  user/abc/mod
	public String mod() {
		return "mod";
	}
	@RequestMapping("**/ins")
	//0-N级目录
	//user/a/ins  user/a/bc/ins
	public String ins() {
		return "ins";
	}
	
	
	@RequestMapping("**/head")
 	public String  head(@RequestHeader()String accept,
 			@RequestHeader(value = "Connection",required = true)String conn) {
		
		
		return accept+"<br>"+conn;
	}
	
	@RequestMapping("**/cookie")
 	public String  cookie(@CookieValue String user,@CookieValue int age,
 			@RequestHeader String cookie) {
		return user+"<br>"+age+"<br>"+cookie;

	}
	/*
	 * 请求对象 响应 会话
	 * 添加servlet依赖 
	 * 依赖上下文不能注入
	 */
	@RequestMapping("**/servlet")

	public String servlet(HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session,
			InputStream in,
			OutputStream out) {
		return  "request"+request+"<br>"
			+"response"+response+"<br>"
			+"session"+session+"<br>"
			+"in"+in+"<br>"
			+"out"+out+"<br>";

	}
	/*
	 * 地址参数   
	 */
	@RequestMapping("{user}/{pwd}/login")
	public String login(@PathVariable("user")String user,
			@PathVariable
			String pwd) {
		return user+"<br>"+pwd;
	}
	/*
	 * 
	 * 会话对象的注入
	 *  Springmvc 数据模型  Model请求过程中临时保存的对象
	 * @SessioAttributes  将指定的对象设置到会话中，夹在类上
      * @SessioAttribute 从会话中获取一个指定的对象，加在方法参数上
	 *
	 */
	@RequestMapping("{user}/{pwd}/login.do")
	public String logindo(@PathVariable("user")String user,
			@PathVariable
			String pwd,
			//Model model
			HttpSession session) {
		 
		DmUser du=new DmUser();
		du.setEname(user);
        du.setPassword(pwd);
        //将用户添加到数据模型中
        session.setAttribute("loginUser",du);
        session.setAttribute("now",new Date());
    //    session.setAttribute("age",100);
		return du.toString();
		
	}
	//验证会话中的值
	@RequestMapping("testlogin")
	public String testlogin(
		@SessionAttribute("loginUser") DmUser du,
		@SessionAttribute("now") Date date,
		@SessionAttribute(value="age",required = false) Integer age) {
		
		return du +"<br>"+date +"<br>"+age;
	}
}
