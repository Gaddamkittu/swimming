package com.service;

import java.util.ArrayList;
import java.util.List;

import com.model.Learner;

public class LearnerService {
	
	private static List<Learner> list=new ArrayList<Learner>();
	
	private static int id=0;
	
	private int generateId() {
		id=id+1;
		return id;
	}
	
	public void add(Learner learner) {
		learner.setId(generateId());
		list.add(learner);
		
	}
	
	public Learner getById(int id) {
		
		for(Learner learner:list) {
			if(id==learner.getId()) {
				return learner;
			}
		}
		
		return null;
		
	}
	
public Learner getByPhone(String phone) {
		
		for(Learner learner:list) {
			if(phone.equalsIgnoreCase(learner.getPhone())) {
				return learner;
			}
		}
		
		return null;
		
	}
	
	
	
	
	
public static List<Learner> getList() {
		return list;
	}


public boolean isExist(int id) {
		boolean flag=false;
		for(Learner learner:list) {
			if(id==learner.getId()) {
				flag=true;
			}
		}
		
		return flag;
		
	}

public void addBooking(int leanerId,int lessonId) {
	for(Learner learner:list) {
		if(leanerId==learner.getId()) {
			learner.setBookedLessons(lessonId);
		}
	}
}

public void cancelBooking(int leanerId,int lessonId) {
	for(Learner learner:list) {
		if(leanerId==learner.getId()) {
			learner.setCancelledLessons(lessonId);
		}
	}
}

public void attendBooking(int leanerId,int lessonId) {
	for(Learner learner:list) {
		if(leanerId==learner.getId()) {
			learner.setAttendedLessons(lessonId);
		}
	}
}
	

}
