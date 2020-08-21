package com.yc.C83pfstSpringBoot.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yc.C83pfstSpringBoot.dao.ProductMapper;
import com.yc.damai.bean.DmProduct;

@Controller
public class IndexAction {

	 @Resource 
	 ProductMapper pm;
	 
	@GetMapping("index.do")
	public String index(Model m) {
		//查询热销商品
		List<DmProduct> hlist=pm.selectByHot();
		
		//查询最新商品
				List<DmProduct> Llist=pm.selectByLeast();
		//推送给页面
		m.addAttribute("hlist",hlist);
		m.addAttribute("Llist",Llist);
		return "index";
	}
	@GetMapping("detail.do")
	public String detail(Model m,int id) {
		//查询商品详情
		DmProduct dp=pm.selectById(id);
		
		 
		//推送给页面
		m.addAttribute("dp",dp);
	 
		 return "detail";
	}
}
