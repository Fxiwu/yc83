package com.yc.C83pfstblog.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.C83pfstblog.bean.Result;
import com.yc.C83pfstblog.bean.User;
import com.yc.C83pfstblog.biz.BizException;
import com.yc.C83pfstblog.biz.UserBiz;

@Controller//默认控制器方法执行页面跳转
public class UserAction {

	@Resource
	private UserBiz ubiz;
	
	//注册  ：表单提交  ==》页面跳转
	@PostMapping("reg.do")
	public String register(@Valid User user,Errors errors,Model m) {
		if(errors.hasErrors()) {
			m.addAttribute("errors",asMap(errors));
			m.addAttribute("user",user);
			return "reg";
		}
		
		try {
			ubiz.regist(user);
		} catch (BizException e) {
			 
			e.printStackTrace();
			errors.rejectValue("account","account",e.getMessage());
			m.addAttribute("errors",asMap(errors));
			m.addAttribute("user", user);
			return "reg";
		}
		//使用响应重定向方式跳转
		return "redirect:/";
	}
	/*
	 * 所有的字段验证写入一个map
	 */
	private Map<String,String> asMap(Errors errors) {

		if(errors.hasErrors()) {
			Map<String,String> ret=new HashMap<String,String>();
			for(FieldError fe:errors.getFieldErrors()) {
				ret.put(fe.getField(),fe.getDefaultMessage());
			}
			return ret;
		}else {
			return null;
		}
		
	}

	@GetMapping("toreg")
	public String toreg() {
		return "reg";
	}
	/*
	 * 登录  ajax提交  ==》Vue
	 * 
	 */
	@PostMapping("login.do")
	@ResponseBody// 是在 Controller 使用 ==> 方法返回视图名 
 	// @ResponseBody 表示该方法的返回值是json数据
	public Result login(@Valid User user, Errors errors, HttpSession session) {		
		try {
			if (errors.hasFieldErrors("account") || errors.hasFieldErrors("pwd")) {
				// 将错误结果转换成 Map集合再返回
 				Result res = new Result(0, "验证错误!", asMap(errors)); 	
 				return res;
 				 
			}
			User dbuser=ubiz.login(user);
			session.setAttribute("loginUser", dbuser);
			return new Result(1, "登录成功!", dbuser);
		} catch (BizException e) {
 			e.printStackTrace();
 			return new Result(e.getMessage());
		}
		 
	}
	
}
