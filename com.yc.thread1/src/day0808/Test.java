package day0808;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

//只用注解的注解来限定该注解被标注的位置
@Target(value= {ElementType.METHOD,
			ElementType.LOCAL_VARIABLE})
public @interface Test {

	
}
@Target(value= {ElementType.METHOD})
@interface Select{
	//定义注解的属性：定义语法类似于接口方法
	//如果一个属性定义的数组类型，那么使用数组语法复制，
	//但是如果只赋值一个元素，可以使用单变量形式
	String [] value();
	//所有的注解属性都必须赋值，如果不赋值，那么要设置默认值
	int age() default 100;
	/*
	 * 如果某个属性的名称是value那么这个属性就是默认属性
	 * 默认属性可以不写属性名直接赋值，前提是只填写该属性，其他属性不填写
	 */
}





