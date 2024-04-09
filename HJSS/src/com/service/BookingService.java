package com.service;

import java.util.ArrayList;
import java.util.List;

import com.model.Coach;
import com.model.Learner;
import com.model.Lesson;

public class BookingService {
	
	LearnerService learnerService=new LearnerService();
	LessonService lessonService=new LessonService();
	CoachService coachService=new CoachService();
	
	
	public boolean isRepeatedBooking(int learnerId,int lessonId) {
		boolean flag=false;
		Learner learner=learnerService.getById(learnerId);
		for(int id: learner.getBookedLessons()) {
			if(id==lessonId) {
				flag=true;
			}
		}
		return flag;
	}
	
	public void printBookings(int learnerId) {
		
		
		System.out.printf("%-3s | %-13s | %-10s | %-6s | %-6s  | %-15s |%s%n", "ID","Date","Day","Timing","Level","Coach name","Available seats");
		System.out.println("-----------------------------------------------------------------------------------------------------");
	

		
		for(int lessonId:learnerService.getById(learnerId).getBookedLessons()) {
			Lesson lesson=lessonService.getById(lessonId);
			
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
	
	public void cancelBooking(int learnerId,int lessonId) {
		learnerService.cancelBooking(learnerId, lessonId);
	}
	
	public void attendBooking(int learnerId,int lessonId) {
		learnerService.attendBooking(learnerId, lessonId);
	}
	
	public boolean book(int learnerId,int lessonId) {
		
		boolean flag=false;
		
		
		Learner learner=learnerService.getById(learnerId);
		Lesson lesson=lessonService.getById(lessonId);
		if(learner!=null) {
			if(lessonService.isSeatAvailable(lessonId)) {
				int level=lesson.getLevel()-learner.getLevel();
				if(level==0 || level == 1) {
					if(!isRepeatedBooking(learnerId, lessonId)) {
						learnerService.addBooking(learnerId, lessonId);
						lessonService.addBooking(lessonId);
						System.out.println("Booking is successful");
						flag=true;
					}
					else {
						System.out.println("No repeated booking alowed");
					}
				}
				else {
					System.out.println("Not allowed for this grade level");
				}
			}
			else {
				System.out.println("No seat available , try another ");
			}
			
		}
		else {
			System.out.println("invalid learner id");
		}
		
		return flag;
		
	}
	
	public void printMonthlyLearnerReport(List<Integer> lessonList) {
		
		for(Learner learner:LearnerService.getList()) {
			System.out.println("Learner details :\n");
			System.out.println("Report for learner "+learner.getName());
			
			
			System.out.printf("%-3s | %-13s | %-6s | %-6s | %-15s  |%s%n", "ID","Name","Gender","Age","Phone","Level");
			System.out.println("-----------------------------------------------------------------------------------------------------");
			
			System.out.printf("%-3s | %-13s | %-6s | %-6s | %-15s  |%s%n", 
					learner.getId(),learner.getName(),learner.getGender(),
					learner.getAge(),learner.getPhone(),learner.getLevel());
			
		
			
			int totalBookedLessons=0;
			int totalAttendedLessons=0;
			int totalCancelledLessons=0;
			
		
			
			System.out.println();
			System.out.println("Booked Lessons :\n");
			System.out.printf("%-3s | %-13s | %-10s | %-6s | %-6s  | %-15s |%s%n", "ID","Date","Day","Timing","Level","Coach name","Available seats");
			System.out.println("-----------------------------------------------------------------------------------------------------");
			
			for(Integer lessonId:learner.getBookedLessons()) {
				for(Integer lessonId1: lessonList) {
					if(lessonId==lessonId1) {
						totalBookedLessons=totalBookedLessons+1;
						Lesson lesson=lessonService.getById(lessonId);
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
				
			}
			
			System.out.println("Cancelled Lessons :\n");
			System.out.printf("%-3s | %-13s | %-10s | %-6s | %-6s  | %-15s |%s%n", "ID","Date","Day","Timing","Level","Coach name","Available seats");
			System.out.println("-----------------------------------------------------------------------------------------------------");
			
			for(Integer lessonId:learner.getCancelledLessons()) {
				for(Integer lessonId1: lessonList) {
					if(lessonId==lessonId1) {
						totalCancelledLessons=totalCancelledLessons+1;
						Lesson lesson=lessonService.getById(lessonId);
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
				
			}
			
			System.out.println("Attended Lessons :\n");
			System.out.printf("%-3s | %-13s | %-10s | %-6s | %-6s  | %-15s |%s%n", "ID","Date","Day","Timing","Level","Coach name","Available seats");
			System.out.println("-----------------------------------------------------------------------------------------------------");
			
			for(Integer lessonId:learner.getAttendedLessons()) {
				for(Integer lessonId1: lessonList) {
					if(lessonId==lessonId1) {
						totalAttendedLessons=totalAttendedLessons+1;
						Lesson lesson=lessonService.getById(lessonId);
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
				
			}
			
			System.out.println("Total number of Booked Lessons : "+totalBookedLessons);
			System.out.println("Total number of Cancelled Lessons : "+totalCancelledLessons);
			System.out.println("Total number of Attended Lessons : "+totalAttendedLessons);
		
			
			
		}
		
	}
	
public void printMonthlyCoachReport(List<Integer> lessonList) {
	
	
		System.out.println("Monthly coach report\r\n");
		
		System.out.println("Coach Name       Avg rating");
		
		List<Coach> coachList=new ArrayList<Coach>();
		
		for(Integer lessonId:lessonList) {
			Coach coach=coachService.getByName(lessonService.getById(lessonId).getCoachName());
			
			if(!coachList.contains(coach)) {
				coachList.add(coach);
				System.out.println(coach.getName()+"        "+coach.getAvgRating());
			}
			
		}
		
		
		
	}

	
	

}
