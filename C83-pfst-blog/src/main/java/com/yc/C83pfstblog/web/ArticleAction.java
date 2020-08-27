package com.yc.C83pfstblog.web;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.yc.C83pfstblog.bean.Article;
import com.yc.C83pfstblog.bean.User;
import com.yc.C83pfstblog.biz.ArticleBiz;
import com.yc.C83pfstblog.dao.ArticleMapper;
import com.yc.C83pfstblog.util.Utils;

@RestController
public class ArticleAction {

	@Resource
 	private ArticleBiz abiz;
	
	@Resource
 	private ArticleMapper amapper;
 
	@GetMapping("article")
 	public ModelAndView article(int id, ModelAndView mav) {
 		mav.addObject("article", amapper.selectById(id));
 		mav.setViewName("article");
 		return mav;
 	}
	
	
  	@GetMapping("toAddArticle")
 	public ModelAndView toAddArticle(ModelAndView mav) {
 		mav.setViewName("addArticle");
 		return mav;
 	}
 	@PostMapping("addArticle.do")
 	public ModelAndView addArticle(@Valid Article a, Errors errors, ModelAndView mav,
 		//ModelAndView   Model+View
 			@SessionAttribute("loginedUser") User user) { 
 		 
 		if (errors.hasErrors()) {
 			mav.addObject("errors", Utils.asMap(errors));
     		mav.addObject("article", a);
 			mav.setViewName("addArticle");
 		} else {
 			// 作者的名字写入文章实体 ==> loginUser
 			a.setAuthor(user.getName());
 			abiz.create(a);
 			// a.id ==> 有值 ==> 数据库的自增列 ==> MyBatis ==> @Options
 			mav.setViewName("redirect:article?id=" + a.getId());
 			// 未完待续 .. 		
 			}
 		return mav;
 	}
 }
