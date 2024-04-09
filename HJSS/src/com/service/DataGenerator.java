package com.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import com.model.Coach;
import com.model.Learner;
import com.model.Lesson;

public class DataGenerator {

	public static List<Learner> generateDummyLearners(int numberOfLearners) {
		LearnerService learnerService = new LearnerService();
		List<Learner> learners = new ArrayList<>();
		Random ran1dom = new Random();
		String[] learnerNames = { "Alice", "Bob", "Charlie", "David", "Emma", "Frank", "Grace", "Henry", "Ivy", "Jack",
				"Kelly", "Liam", "Mia", "Noah", "Olivia" };
		String[] learnerGenders = { "Male", "Female" };
		String[] phone = { "(555) 555-5555", "(123) 456-7890", "(987) 654-3210", "(555) 123-4567",
				"(888) 888-8888","(555) 987-6543", "(777) 777-7777", "(999) 999-9999", "(123) 555-6789",
				"(456) 789-0123", "(111) 222-3333", "(999) 876-5432",
				"(321) 987-6543", "(555) 444-3333", "(777) 555-4444" };
		int co1unt=1;
		for (int i = 0; i < 15; i++) {
			String name = learnerNames[i];
			String gender = learnerGenders[ran1dom.nextInt(learnerGenders.length)];
			int age = ran1dom.nextInt(8) + 4; // Random age between 4 and 11
			String emergencyContactPhoneNumber = phone[i];
			co1unt=co1unt+1;
			int gradeLevel = ran1dom.nextInt(6); // Random grade level between 0 and 5

			Learner learner = new Learner(name, gender, age, emergencyContactPhoneNumber, gradeLevel);

			learnerService.add(learner);
		}

		return learners;
	}

	public static List<Lesson> generateLessons(int numberOfLessons) {
		LessonService lessonService = new LessonService();
		List<Lesson> lessons = new ArrayList<>();
		Random random = new Random();
		String[] days = { "Monday", "Wednesday", "Friday", "Saturday" };
		String[] timesMondayWednesdayFriday = { "4-5pm", "5-6pm", "6-7pm" };
		String[] timesSaturday = { "2-3pm", "3-4pm" };
		String[] coachNames = { "Harper Patel", "Noah Johnson", "Evelyn Lopez", "Sophia Wright" };

		Calendar calendar = Calendar.getInstance();
		for (int i = 0; i < numberOfLessons; i++) {
			String day = days[random.nextInt(days.length)];
			String time;
			if (day.equals("Saturday")) {
				time = timesSaturday[random.nextInt(timesSaturday.length)];
			} else {
				time = timesMondayWednesdayFriday[random.nextInt(timesMondayWednesdayFriday.length)];
			}
			String coachName = coachNames[random.nextInt(coachNames.length)];

			int dayOfWeek = convertDayOfWeek(day);
			calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);

			String date = String.valueOf(calendar.get(Calendar.DATE));
			int month = calendar.get(Calendar.MONTH) + 1;
			String year = String.valueOf(calendar.get(Calendar.YEAR));

			int level = random.nextInt(6); // Random grade level between 0 and 5
			int seat = random.nextInt(3) + 2; // Random number of available seats between 1 and 4

			Lesson lesson = new Lesson(date, month, year, day, time, level, seat, coachName);

			lessonService.addLesson(lesson);
			// Increment the week for Saturday lessons
			if (day.equals("Saturday")) {
				calendar.add(Calendar.WEEK_OF_MONTH, 1);
			}
		}

		return lessons;
	}

	private static int convertDayOfWeek(String day) {
		switch (day) {
		case "Monday":
			return Calendar.MONDAY;
		case "Wednesday":
			return Calendar.WEDNESDAY;
		case "Friday":
			return Calendar.FRIDAY;
		case "Saturday":
			return Calendar.SATURDAY;
		default:
			return Calendar.SUNDAY;
		}
	}

	public static List<Coach> generateCoaches(int numberOfCoaches) {
		CoachService coachService = new CoachService();
		List<Coach> coaches = new ArrayList<>();
		Random random = new Random();
		String[] coachNames = { "Harper Patel", "Noah Johnson", "Evelyn Lopez", "Sophia Wright" };

		for (int i = 0; i < numberOfCoaches; i++) {
			String name = coachNames[i];
			int noOfRatings = random.nextInt(50) + 1; // Random number of ratings between 1 and 50
			int avgRating = random.nextInt(3) + 3; // Random average rating between 3 and 5

			String review = "Some review for " + name;

			Coach coach = new Coach(name, noOfRatings, avgRating, review);

			coachService.add(coach);
		}

		return coaches;
	}

	public static void setLessonsToLearner() {
		Random random = new Random();
		int lessonId = 0;
		for (Learner learner : LearnerService.getList()) {
			for (int i = 0; i < 20; i++) {
				lessonId = random.nextInt(50) + 1;
				
				if (!learner.getBookedLessons().contains(lessonId)) {
					learner.setBookedLessons(lessonId);
				}

			}
			
			for (int i = 0; i < 10; i++) {
				lessonId = random.nextInt(50) + 1;
				if (!learner.getCancelledLessons().contains(lessonId)
						&& learner.getBookedLessons().contains(lessonId)) {
					learner.setCancelledLessons(lessonId);
				}

			}
			
			for (int i = 0; i < 10; i++) {
				lessonId = random.nextInt(50) + 1;
				if (!learner.getAttendedLessons().contains(lessonId)
						&& learner.getBookedLessons().contains(lessonId)
						&&!learner.getCancelledLessons().contains(lessonId)) {
					learner.setAttendedLessons(lessonId);
				}

			}
		}

	}
}
