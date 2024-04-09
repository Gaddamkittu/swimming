package com.service;

import java.util.ArrayList;
import java.util.List;

import com.model.Coach;
import com.model.Learner;
import com.model.Lesson;

public class CoachService {
	
	private static List<Coach> list=new ArrayList<Coach>();
	LessonService lessonService=new LessonService();
	
	private static int id=0;
	
	private int generateId() {
		id=id+1;
		return id;
	}
	
	public void add(Coach coach) {
		list.add(coach);
		
	}
	
	
	
	public static List<Coach> getList() {
		return list;
	}

	public void submitReview(int rating,String review,int lessonId) {
		
		String coachName=lessonService.getById(lessonId).getCoachName();
		
		for(Coach coach:list) {
			
			if(coachName.equalsIgnoreCase(coach.getName())) {
				coach.setReview(review);
				coach.setAvgRating(rating);
			}
		}
		
		
	}
	
	public Coach getByName(String name) {
		
		for (Coach coach : list) {

			if (name.equalsIgnoreCase(coach.getName())) {
					return coach;
			}

		}

		return null;
	}

}
