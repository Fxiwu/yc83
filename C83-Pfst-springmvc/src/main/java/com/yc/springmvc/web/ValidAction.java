package com.yc.springmvc.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.damaibean.DmUser;
import com.yc.damaibean.Result;
@RestController
@RequestMapping("demo")
public class ValidAction {

	/*
	 * 1、在被校验的对象前加@Valid注解
	 * 2.在被校验的对象后添加Errors对象
	 */
	@RequestMapping("reg.do")
	public Result reg(@Valid DmUser du,Errors errors) {
		 if(errors.hasErrors()) {
			 return  new Result(0,"验证错误",errors.getAllErrors());
		 }else {
			 return  new Result(1,"验证成功");
		 }
		
	}
}
