package com.hotel.utils.serializable;

import java.io.Serializable;

/**
 * @author wxy
 */
public class Person implements Serializable{
	
	private static final long serialVersionUID = 1L;
	String name ;
	private int age ;
	
	public Person() {
	}
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
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
	
}
