package com.demo3;


import java.io.Serializable;

//Object对象,,,读写需要实现可序列化
public class Info implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//重写toString方法,方便输出
	@Override
	public String toString() {
		return "Info [name=" + name + ", age=" + age + ", sex=" + sex + "]";
	}
	//三个属性,姓名,年龄,性别
	private String name;
	private int age;
	private String sex;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Info(String name, int age, String sex) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
	}
	public Info() {
		super();
	}
	
}
