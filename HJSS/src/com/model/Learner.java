package com.model;

import java.util.ArrayList;
import java.util.List;

public class Learner {
	
	
	private List<Integer> bookedLessons;
	private List<Integer> attendedLessons;
	private List<Integer> cancelledLessons;
	
	private int id;
	private String name;
	private String  gender;
	private int  age;
	private String phone;
	private int level;
	
	
	public Learner() {
		
	}
	public Learner(int id, String name, String gender, int age, String phone, int level) {
		
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.phone = phone;
		this.level = level;
	}
	
	public Learner(String name, String gender, int age, String phone, int level) {
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.phone = phone;
		this.level = level;
		bookedLessons=new ArrayList<Integer>();
		attendedLessons=new ArrayList<Integer>();
		cancelledLessons=new ArrayList<Integer>();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	
	
	
	
	public List<Integer> getBookedLessons() {
		return bookedLessons;
	}
	public void setBookedLessons(int lessonId) {
		this.bookedLessons.add(lessonId);
	}
	public List<Integer> getAttendedLessons() {
		return attendedLessons;
	}
	public void setAttendedLessons(int lessonId) {
		this.attendedLessons.add(lessonId);
	}
	public List<Integer> getCancelledLessons() {
		return cancelledLessons;
	}
	public void setCancelledLessons(int lessonId) {
		this.cancelledLessons.add(lessonId);
	}
	@Override
	public String toString() {
		return "Learner [id=" + id + ", name=" + name + ", gender=" + gender + ", age=" + age + ", phone=" + phone
				+ ", level=" + level + "]";
	}

	
	
	
	
}
