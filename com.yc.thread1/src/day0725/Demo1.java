package day0725;

import java.util.Scanner;

public class Demo1 {
    public static void main(String[] args) {
       A a=new A("a方法的线程");
       B b=new B();
       Thread t=new Thread(b,"b线程的方法");
       a.start();//启动线程
       t.start();
       //获取当前线程：主线程
       System.out.println("main getName:"+Thread.currentThread().getName());
       System.out.println("main getPriority:"+Thread.currentThread().getPriority());
       System.out.println("main getId:"+Thread.currentThread().getId());
       System.out.println("main getState:"+Thread.currentThread().getState());
    }
    public static void a() {
    	Scanner sc=new Scanner(System.in);
    	System.out.println("请输入：");
    	String s=sc.nextLine();
    	System.out.println("您输入的是"+s);
    	sc.close();
    }
    public static void b() {
    	 
    	System.out.println("这是b()方法");
    	 
    }
    public static class A extends Thread{
    	//重写父类的构造方法
    	public A(String name) {
    		super(name);
    	}
    	public void run() {
    		a();
    		  System.out.println("a getName:"+Thread.currentThread().getName());
    	       System.out.println("a getPriority:"+Thread.currentThread().getPriority());
    	       System.out.println("a getId:"+Thread.currentThread().getId());
    	       System.out.println("a getState:"+Thread.currentThread().getState());

    	}
    }
    public static class B implements Runnable{
    	public void run() {
    		//让线程休眠10秒
    		System.out.println("b()休眠10秒");
    		try {
				Thread.sleep(10*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		b();
    		  System.out.println("b getName:"+Thread.currentThread().getName());
    	       System.out.println("b getPriority:"+Thread.currentThread().getPriority());
    	       System.out.println("b getId:"+Thread.currentThread().getId());
    	       System.out.println("b getState:"+Thread.currentThread().getState());

    	}
    }
}
