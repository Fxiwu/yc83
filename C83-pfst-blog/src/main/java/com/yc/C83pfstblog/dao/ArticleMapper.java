package com.yc.C83pfstblog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.yc.C83pfstblog.bean.Article;

public interface ArticleMapper {

	@Select("select * from article order by createtime desc")
	public List<Article> selectBynew();
	
	@Select("select * from article where id=#{id}")
	public  Article  selectById(int id);

	@Insert("insert into article values (#{id},#{author},#{title},#{content},"
 		+ "#{keywords},#{description},#{categoryid},#{label},#{titleimgs},"
 		+ "#{status},now(),#{readcnt},#{agreecnt})")
 	@Options(useGeneratedKeys=true,keyColumn="id",keyProperty="id")
	public int insert(Article art);
}
