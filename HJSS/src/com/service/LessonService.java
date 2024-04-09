package com.service;

import java.util.ArrayList;
import java.util.List;

import com.model.Learner;
import com.model.Lesson;

public class LessonService {

	private static List<Lesson> list = new ArrayList<Lesson>();
	
	private static int id=0;
	
	private int generateId() {
		id=id+1;
		return id;
	}
	
	public void addLesson(Lesson lesson) {
		
		lesson.setId(generateId());
		list.add(lesson);
		
		
	}
	
	

	public static List<Lesson> getList() {
		return list;
	}

	public List<Lesson> searcByCoach(String coachName) {

		List<Lesson> listByCoach = new ArrayList<Lesson>();

		for (Lesson lesson : list) {

			if (coachName.equalsIgnoreCase(lesson.getCoachName())) {
				listByCoach.add(lesson);
			}

		}

		return listByCoach;

	}

	public List<Lesson> searcByGrade(int level) {

		List<Lesson> listByCoach = new ArrayList<Lesson>();

		for (Lesson lesson : list) {

			if (level == lesson.getLevel()) {
				listByCoach.add(lesson);
			}

		}

		return listByCoach;

	}

	public List<Lesson> searcByDay(String day) {

		List<Lesson> listByCoach = new ArrayList<Lesson>();

		for (Lesson lesson : list) {

			if (day.equalsIgnoreCase(lesson.getDay())) {
				listByCoach.add(lesson);
			}

		}

		return listByCoach;

	}

	public void printLessons(List<Lesson> listArg) {
		
		System.out.printf("%-3s | %-13s | %-10s | %-6s | %-6s  | %-15s |%s%n", "ID","Date","Day","Timing","Level","Coach name","Available seats");
		System.out.println("-----------------------------------------------------------------------------------------------------");
	

		for (Lesson lesson : listArg) {
			System.out.printf("%-3s | %-13s | %-10s | %-6s | %-7s | %-15s |%s%n",
					lesson.getId(),
					lesson.getDate()+"-"+lesson.getMonth()+"-"+lesson.getYear(),
					lesson.getDay(),
					lesson.getTime(),
					lesson.getLevel(),					
					lesson.getCoachName(),
					lesson.getSeat()
					);

		}
	}
	
	
	
	
	public Lesson getById(int id) {
		
		for (Lesson lesson : list) {

			if (id == lesson.getId()) {
					return lesson;
			}

		}

		return null;

	}
	
	public List<Integer> searcByMonth(int month) {

		List<Integer> listByMonth = new ArrayList<Integer>();

		for (Lesson lesson : list) {

			if (month==lesson.getMonth()) {
				listByMonth.add(lesson.getId());
			}

		}

		return listByMonth;

	}

	public boolean isExist(int id) {

		boolean flag = false;

		for (Lesson lesson : list) {

			if (id == lesson.getId()) {
				flag = true;
			}

		}

		return flag;

	}
	
	public boolean isSeatAvailable(int id) {
		
		boolean flag = false;

		if(getById(id).getSeat()>0) {
			flag=true;
		}

		return flag;
		
		
	}
	
	
public void addBooking(int lessonId) {
		
		for (Lesson lesson : list) {

			if (lessonId == lesson.getId()) {
				int seat=lesson.getSeat();
					lesson.setSeat(seat-1);
			}

		}

	}

public void CancelBooking(int lessonId) {
	
	for (Lesson lesson : list) {

		if (lessonId == lesson.getId()) {
			int seat=lesson.getSeat();
				lesson.setSeat(seat+1);
		}

	}

}


}
