package com.yc.C83pfstblog.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yc.C83pfstblog.dao.ArticleMapper;

@Controller
public class IndexAction {
	@Resource
	
	private ArticleMapper armapper;
	@GetMapping("/")
	public String index(Model m) {
		
		m.addAttribute("alist",armapper.selectBynew());
		
		return "index";
		
	}
	

}
