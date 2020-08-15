package com.yc.spring.bean;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import com.yc.spring.Hello;
import com.yc.spring.bean.Person;
import com.yc.spring.dao.MySQLUserDao;
import com.yc.spring.dao.OracleUserDao;

/*
 * 注解配置IOC容器
 */
@Configuration
@ComponentScan("com.yc.spring")
public class BeanConfig {
      //xml中的每一个bean 对应java的一个方法，这个方法返回bean 对象
	//方法名不限定  
	@Bean(name="hello")
	public Hello getHello() {
		return new Hello();
	}
	
//	@Bean(name="odao")
//	public OracleUserDao  getOracleUserDao(){
//		return new OracleUserDao();
//	}
//	
//	@Bean(name="mdao")
//	public MySQLUserDao  getMySQLUserDao(){
//		return new MySQLUserDao();
//	}
	
	@Bean(name="p1")
	public Person getPerson1() {
		Person ret=new Person();
		ret.setName("wusong");
		ret.setAge(54);
		ret.setKilled(new ArrayList<String>());
		ret.getKilled().add("B");
		ret.getKilled().add("B");
		ret.getKilled().add("B");
		ret.getKilled().add("B");
		ret.getKilled().add("B");
		return ret;
	}
	
	@Bean(name="p4")
	public Person getPerson5() {
		Person p=Person.PersonFactory();
		p.setName("王英");
		return p;
		
	}
	/*
	 * 原型模式
	 */
	@Bean("hello1")
	@Scope(value="prototype")
	public Hello getHello1() {
		return  new Hello();
	}
	/*
	 * 懒加载
	 */
	@Bean("hello2")
	@Lazy
	public Hello getHello2() {
		return  new Hello();
	}
}
