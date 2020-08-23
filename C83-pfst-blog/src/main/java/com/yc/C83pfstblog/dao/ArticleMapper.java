package com.yc.C83pfstblog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yc.C83pfstblog.bean.Article;

public interface ArticleMapper {

	@Select("select * from article order by createtime desc")
	public List<Article> selectBynew();
}
