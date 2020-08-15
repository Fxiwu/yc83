package com.yc.spring;

public class Hello {

	public Hello() {
		//无参数构造方法
		System.out.println("=========无参数构造方法==========");
	}
	public Hello( int a) {
		//有参数构造方法
		System.out.println("=========有参数构造方法==========");
	}
	/*
	 * 生命周期不能带参数
	 */
	public void init() {
		System.out.println("====hell被创建");
	}
	public void destroy() {
		System.out.println("====hell被销毁");
	}
	public void sayHello() {
		System.out.println("你好世界");
	}
}
