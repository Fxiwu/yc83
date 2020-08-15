package com.yc.spring.bean;

import java.util.List;

public class Person {

	private String name;
	private String id;
	private int age;
	private List<String> killed;
	
	private Person friend;
	 
	//别名
	private String alisa;
	//身高
	private int Height;
	
	public Person( ) {
 		 
	}
	public Person(String name, int age, String alisa) {
		super();
		System.out.println("===========2====");
		this.name = name;
		this.age = age;
		this.alisa = alisa;
	}
	public Person( int age,String name, String alisa) {
		
		super();
		System.out.println("===========1====");
 		this.name = name;
		this.age = age;
		this.alisa = alisa;
	}
	/*
	 * 静态的工厂模式
	 */
	public static Person PersonFactory() {
		
		Person p= new Person();
		p.setAge(40); 
		return p;
	}
	/*
	 * 实例的工厂模式
	 */
	public  Person PersonFactory1() {
		
		Person p= new Person();
		p.setAge(20); 
		return p;
	}
	public Person getFriend() {
		return friend;
	}
	public void setFriend(Person friend) {
		this.friend = friend;
	}
	public String getAlisa() {
		return alisa;
	}
	public void setAlisa(String alisa) {
		this.alisa = alisa;
	}
	public int getHeight() {
		return Height;
	}
	public void setHeight(int height) {
		Height = height;
	}
	 
	public List<String> getKilled() {
		return killed;
	}
	public void setKilled(List<String> killed) {
		this.killed = killed;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
