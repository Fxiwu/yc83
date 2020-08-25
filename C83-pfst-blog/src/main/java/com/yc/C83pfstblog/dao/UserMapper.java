package com.yc.C83pfstblog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.yc.C83pfstblog.bean.Article;
import com.yc.C83pfstblog.bean.User;

public interface UserMapper {

	@Insert("insert into user value(null,#{name},#{account},#{pwd},#{phone},#{email},#{head},now(),#{status},#{type})")
	public int insert(User user);
	
	@Select("select * from user where account=#{account} and pwd=#{pwd} ")
	public User selectByLogin(User user);
	
	@Select("select count(*) from user where account=#{account}")
	public  int countByAcount(String account );
}
