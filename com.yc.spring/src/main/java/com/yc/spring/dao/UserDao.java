package com.yc.spring.dao;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

import com.yc.spring.bean.Person;
 
public interface UserDao {

	public int selectUserId(String string);
	 @Insert("insert into person values(#{name},#{age})")
	 public int insert(Person person);

}
