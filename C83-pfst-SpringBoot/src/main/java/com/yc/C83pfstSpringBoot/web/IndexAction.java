package com.yc.C83pfstSpringBoot.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.yc.C83pfstSpringBoot.dao.ProductMapper;

@Controller
public class IndexAction {

	 @Resource 
	 ProductMapper pm;
	 
	@GetMapping("index.do")
	public String index() {
		return "index";
	}
}
