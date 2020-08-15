package day0808.junit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

	public static void main(String[] args) {
 
		DemoTest dt=new DemoTest();
		Class<?> cls=dt.getClass();
		Method beforeMethod=getMethodByAnnotation(cls,Before.class);
		Method afterMethod=getMethodByAnnotation(cls,After.class);
		int flag=1;
		int count=0;
		//获取所有的方法，并进行遍历
		for(Method m:cls.getMethods()) {
			if(m.getAnnotation(Test.class)!=null) {
				try {
					//动态执行方法，junit的测试方法不能定义参数
					if(beforeMethod!=null) {
						beforeMethod.invoke(dt);
					}
					m.invoke(dt);
					if(afterMethod!=null) {
						afterMethod.invoke(dt);
					}
				}catch(IllegalAccessException|IllegalArgumentException e) {
					 flag=-1;
					e.printStackTrace();
				}catch(InvocationTargetException e) {
					flag=-1;
					//如果是dt.m方法出现业务异常，将会封装 该异常中
					System.out.println("测试异常，失败！");
					// Throwable t=e.getCause();//异常原因，空指针
					//System.out.println(t);
					e.printStackTrace();
				}finally {
					if(flag==1) {
						count++;
					}
				}
			}
		}
		System.out.println("运行成功:"+count+"个");
	}
/*
 * 根据输入得注释类得类名，返回对应的方法
 * testCls   要查找的被测试类对象
 * annoCls   要查找方法标注的注解类对象
 */
	@SuppressWarnings("unckecked")
	private static Method getMethodByAnnotation(Class<?> testCls,
			@SuppressWarnings("rawtypes") Class annoCls) {
		 for(Method m: testCls.getMethods()) {
			 if(m.getAnnotation(annoCls)!=null) {
				 return m;
			 }
		 }
		return null;
	}

	 

}
