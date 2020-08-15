package com.yc.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.yc.spring.bean.Person;
import com.yc.spring.dao.UserDao;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=AOPConfig.class)

public class AOPTest {

	   @Autowired
	   @Qualifier("mdao")//指定注入的组件的id
	   private UserDao mdao;
	   
	   @Autowired
	   @Qualifier("odao")//指定注入的组件的id
	   private UserDao odao;
	   
//	   @Autowired
//	   @Qualifier("person")//指定注入的组件的id
//	   private Person person;
	   
	   @Autowired
	   private Hello hello;
	   
	   @Test
	   public void test1() {
		   System.out.println("====1========");
		   mdao.selectUserId("");
		   System.out.println("====2========");
		   odao.selectUserId("");
		   System.out.println("=====3========");
	   }
	   
	   @Test
	   public void test2() {
		   System.out.println(mdao);
		   System.out.println(odao);
           System.out.println(hello);
	   }
	   
}
