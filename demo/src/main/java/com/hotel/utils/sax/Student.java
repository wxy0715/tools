package com.hotel.utils.sax;

//把 等待排序的类 implements Comparable ，并实现接口的  comparator()方法，用comparator()指定排序规则
public class Student implements Comparable<Student>{
	private int id;
	private String name;
	private double score;

	public Student(int id, String name, double score) {
		this.id = id;
		this.name = name;
		this.score = score;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getScore() {
		return score;
	}

	//对象.compareTo(stu) 
	@Override
	public int compareTo(Student stu) {//>0 当前元素 >传入的元素     
		int result =   0  ;
//		return   this.getScore()> stu.getScore()   ? 1 : (  this.getScore()< stu.getScore()? -1:0      )     ;
		if(this.getScore()> stu.getScore()) {
			result = 1;
		}else if(this.getScore()< stu.getScore()){
			result = -1 ;
		}
		return result ;
	}
	
	
}
