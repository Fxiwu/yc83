package com.yc.C83pfstblog.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.yc.C83pfstblog.dao.ArticleMapper;
 

@Controller
public class IndexAction {
	@Resource
	
	private ArticleMapper armapper;
	@GetMapping("/")
	public String index(Model m, @RequestParam(defaultValue ="1") int page) {
		//查询前设置分页参数
		//必须是在查询方法执行前调用分页参数设置
		PageHelper.startPage(page,5);
		m.addAttribute("alist",armapper.selectBynew());
 		return "index";
		
	}
	
	 
	
	 
}
