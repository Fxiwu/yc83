package com.yc.spring;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.yc.spring.bean.BeanConfig;
import com.yc.spring.bean.Person;
import com.yc.spring.dao.UserDao;

public class HelloTestForAnno {
	// 创建Spring容器对象
		private	AnnotationConfigApplicationContext ctx;
	@Before
		public void before() {
		 ctx=new AnnotationConfigApplicationContext(BeanConfig.class);

		}
	@After
	public void after() {
		ctx.close();
	}
	
	@Test
	public void test() {
 		Hello h=(Hello) ctx.getBean("hello");
		Hello h1=(Hello) ctx.getBean("hello");
		Hello h2=(Hello) ctx.getBean("hello");

		//h1和h2是同一个对象
		System.out.println(h1==h2);
		//执行sayHello方法
		h.sayHello();
		
 	}
	@Test
	public void test1() {
 		UserDao udao1= (UserDao) ctx.getBean("mdao");
		UserDao udao2= (UserDao) ctx.getBean("odao");
		udao1.selectUserId("zhangda");
		udao2.selectUserId("zhangda");

 	}
	@Test
	public void test2() {
 		 Person p1=(Person) ctx.getBean("p1");
		 Assert.assertEquals("wusong",p1.getName());
		 Assert.assertEquals(54,p1.getAge());
		 Assert.assertEquals(3,p1.getKilled().size());
		 Assert.assertEquals("B",p1.getKilled( ).get(1));
 
		 System.out.println(p1.getAlisa());
		 System.out.println(p1.getHeight());
 	}
	
	@Test
	public void test3() {
 		 Person p1=ctx.getBean(Person.class);
		 Assert.assertEquals("李逵",p1.getName());
		 Assert.assertEquals(33,p1.getAge());
		 Assert.assertEquals(null,p1.getKilled());
   
 	}
	
	/*
	 * org.springframework.beans.factory.xml.XmlBeanDefinitionStoreException: Line 78 in XML document from class path resource [beans.xml] is invalid; nested exception is org.xml.sax.SAXParseException; lineNumber: 78; columnNumber: 8; 元素类型 "bean" 必须后跟属性规范 ">" 或 "/>"。
	      78行 bean后没有配对>
	 */
	/*
	 * org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'p5' defined in class path resource [beans.xml]: No matching factory method found: factory bean 'personFactory'; factory method 'PersonFactory()'. Check that a method with the specified name exists and that it is non-static.
	 
	 */
	@Test
	public void test4() {
 		 Person p1=(Person) ctx.getBean("p2");
		 Assert.assertEquals("吴用",p1.getName());
		 Assert.assertEquals(38,p1.getAge());
 		 Assert.assertEquals("华荣",p1.getFriend().getName());
 
	}
	@Test
	public void test5() {
 		 Person p1=(Person) ctx.getBean("p4");
		 Assert.assertEquals("王英",p1.getName());
		 Assert.assertEquals(40,p1.getAge());
  
	}
	@Test
	public void test6() {
 		 Person p1=(Person) ctx.getBean("p5");
		 Assert.assertEquals("扈三娘",p1.getName());
		 Assert.assertEquals(20,p1.getAge());
  
	}
	@Test
	public void test7() {
 		System.out.println("=====7======");	
 		Hello h0=(Hello) ctx.getBean("hello");
 		Hello h0_1=(Hello) ctx.getBean("hello");
 		Hello h0_2=(Hello) ctx.getBean("hello");

 		Hello h1=(Hello) ctx.getBean("hello1");
 		Hello h1_1=(Hello) ctx.getBean("hello1");
 		Hello h1_2=(Hello) ctx.getBean("hello1");
 		System.out.println(h0==h1);
 		System.out.println(h0_1==h0_2);
 		System.out.println(h1_1==h1_1);
 		System.out.println(h1==h1_2);

 		System.out.println(h0==h1);
	}
	@Test
	public void test8() {
 		System.out.println("=====8======");	
 		Hello h0=(Hello) ctx.getBean("hello2");
     h0.sayHello();
	}
	@Test
	public void test9() {
  Hello h0=(Hello) ctx.getBean("hello3");
      h0.sayHello();
	}
	/*
	 * 自动装载
	 */
	@Test
	public void test10() {
 	 Person p7=(Person) ctx.getBean("p7");
 	 System.out.println(p7.getFriend().getName());
	}
}
