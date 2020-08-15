package com.yc.spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.ibatis.annotations.Insert;

import com.yc.spring.bean.Person;
import com.yc.spring.dao.UserDao;

public class SqlSession {
    public static void main(String[] args) {
		SqlSession session=new SqlSession();
    	UserDao udao=session.getMapper(UserDao.class);
	    udao.insert(new Person());
    }
    /*
     * 泛型
     */
    public <M> M getMapper(Class<M> cls) {
    	@SuppressWarnings("unchecked")
    	M ret=(M) Proxy.newProxyInstance(cls.getClassLoader(), 
    			new Class[] {cls},
    			new InvocationHandler() {
					public Object invoke(Object proxy,Method method,Object[]args) {
						Insert insert=method.getAnnotation(Insert.class);
					  if(insert!=null) {
						  //使用DBHelper执行该语句
						  System.out.println("执行："+insert.value()[0]);
						  //根据方法的返回结果，将dbhelper的执行结果返回
						  if(method.getReturnType()!=null) {
							  if(method.getReturnType().equals(int.class)) {
								  //如果返回值是Integer类型，这返回integer值
							      return 0;
							  }
						  }
					  }
					  return null;
					}
							 
				 
				});
    	return ret;
    }
}
