package com.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import com.model.Coach;
import com.model.Learner;
import com.model.Lesson;
import com.service.BookingService;
import com.service.CoachService;
import com.service.DataGenerator;
import com.service.LearnerService;
import com.service.LessonService;
import com.service.LoginService;

public class HjssApp {

	static LessonService lessonService = new LessonService();
	BookingService bookingService = new BookingService();
	CoachService coachService = new CoachService();
	LoginService loginService=new LoginService();
	static LearnerService learnerService = new LearnerService();

	public static void main(String[] args) {

		HjssApp app = new HjssApp();

		DataGenerator.generateDummyLearners(15);
		DataGenerator.generateLessons(50);
		DataGenerator.generateCoaches(4);
		DataGenerator.setLessonsToLearner();
	
		
		app.login();

	}
	
	public void login() {
		
		System.out.println("================== Login ========================");
		String phone="";
		System.out.println("Enter your phone number to login and if you want to login as admin then enter admin password...");
		phone=getInputString();
		while (!(loginService.login(phone) || phone.equalsIgnoreCase("admin@123"))) {
			System.out.println("Invalid phone number / password , try again");
			phone=getInputString();
		}
		if(phone.equalsIgnoreCase("admin@123")) {
			System.out.println("Welcome Admin...");
			adminMenu();
		}
		else {
			Learner learner=LoginService.logedInLearner;
			System.out.println("Welcome "+learner.getName());
			System.out.println("Learner details :\n");
			
			
			System.out.printf("%-3s | %-13s | %-6s | %-6s | %-15s  |%s%n", "ID","Name","Gender","Age","Phone","Level");
			System.out.println("-------------------------------------------------------------------------------------");
			
			System.out.printf("%-3s | %-13s | %-6s | %-6s | %-15s  |%s%n", 
					learner.getId(),learner.getName(),learner.getGender(),
					learner.getAge(),learner.getPhone(),learner.getLevel());
			
			learnerMenu();
		}
		
		

	}

	
	
	public void adminMenu() {

		boolean flag = true;

		while (flag) {
			printAdminMenu();

			switch (getInputInt()) {
			case 1:
				monthlyLearnerReport();
				break;
			case 2:
				monthlyCoachReport();
				break;
			case 3:
				registerLearner();
				break;
			
			case 0:
				System.out.println("exit");
				flag = false;
				break;

			default:
				System.out.println("invalid input , try again");
				break;
			}
		}

	}
	
	public void learnerMenu() {

		boolean flag = true;

		while (flag) {
			printLearnerMenu();

			switch (getInputInt()) {
			case 1:
				one();
				break;
			case 2:
				initChangeCancel();
				break;
			case 3:
				attendAndReview();
				break;
			case 0:
				System.out.println("exit");
				flag = false;
				break;

			default:
				System.out.println("invalid input , try again");
				break;
			}
		}

	}

	public void one() {
		boolean flag = true;
		List<Lesson> list = new ArrayList<Lesson>();
		while (flag) {

			printLessonSearchMenu();
			switch (getInputInt()) {
			case 1:
				System.out.println("Enter Day ");

				list = lessonService.searcByDay(getInputString());
				if (list.size()>=1) {
					lessonService.printLessons(list);
					flag = !initBooking();
				}
				else {
					System.out.println("no data found , try with another one");
				}
				

				

				break;
			case 2:
				System.out.println("Enter grade level");
				list = lessonService.searcByGrade(getInputInt());
				if (list.size()>=1) {
					lessonService.printLessons(list);
					flag = !initBooking();
				}
				else {
					System.out.println("no data found , try with another one");
				}
				
				break;
			case 3:
				System.out.println("Enter coach name");
				list = lessonService.searcByCoach(getInputString());
				if (list.size()>=1) {
					lessonService.printLessons(list);
					flag = !initBooking();
				}
				else {
					System.out.println("no data found , try with another one");
				}
				
				break;
			case 4:
				System.out.println("back");
				flag = false;
				break;
			default:
				System.out.println("invalid input , try again");
				break;
			}

		}

	}

	public boolean initBooking() {

		boolean flag = false;

		System.out.println("Enter lesson Id from the above list for booking");
		int lessonId = getInputInt();
		if (lessonService.isExist(lessonId)) {
			flag = bookingService.book(LoginService.logedInLearner.getId(), lessonId);
		} else {
			System.out.println("Invalid lesson id ");
		}

		return flag;
	}

	public void initChangeCancel() {

		boolean flag = true;
		int lessonId = 0;
		while (flag) {

			printChangeCancelMenu();
			switch (getInputInt()) {
			case 1:
				System.out.println("cancel booking ");
				bookingService.printBookings(LoginService.logedInLearner.getId());
				System.out.println("Enter id to cancel");
				lessonId = 0;
				lessonId = getInputInt();
				while (!bookingService.isRepeatedBooking(LoginService.logedInLearner.getId(), lessonId)) {
					System.out.println("Please enter correct id");
					lessonId = getInputInt();

				}
				bookingService.cancelBooking(LoginService.logedInLearner.getId(), lessonId);
				lessonService.CancelBooking(lessonId);
				System.out.println("Booking canceled");
				flag = false;

				break;
			case 2:
				System.out.println("change booking ");
				bookingService.printBookings(LoginService.logedInLearner.getId());
				System.out.println("Enter id to change");
				lessonId = 0;
				lessonId = getInputInt();
				while (!bookingService.isRepeatedBooking(LoginService.logedInLearner.getId(), lessonId)) {
					System.out.println("Please enter correct id");
					lessonId = getInputInt();

				}
				bookingService.cancelBooking(LoginService.logedInLearner.getId(), lessonId);
				lessonService.CancelBooking(lessonId);
				one();

				flag = false;
				break;

			case 3:
				System.out.println("back");
				flag = false;
				break;
			default:
				System.out.println("invalid input , try again");
				break;
			}

		}

	}

	public void attendAndReview() {
		int lessonId = 0;
		int rating = 0;
		String review = "";
		bookingService.printBookings(LoginService.logedInLearner.getId());
		System.out.println("Enter id to Attend");

		lessonId = getInputInt();
		while (!bookingService.isRepeatedBooking(LoginService.logedInLearner.getId(), lessonId)) {
			System.out.println("Please enter correct id");
			lessonId = getInputInt();

		}

		System.out.println("Please enter rating from 1-5");
		rating = getInputInt();
		while (!(rating >= 1 && rating <= 5)) {
			System.out.println("Invalid input , Please enter rating from 1-5");
			rating = getInputInt();

		}
		
		System.out.println("Enter review ");

		review = getInputString();

		coachService.submitReview(rating, review, lessonId);
		bookingService.attendBooking(LoginService.logedInLearner.getId(), lessonId);

		System.out.println("Lesson attended and review submited");

	}

	public void monthlyLearnerReport() {
		List<Integer> listByMonth = new ArrayList<Integer>();
		boolean flag = true;
		int month = 0;
		String strMonth = "";

		while (flag) {

			System.out.println("Enter month number 01-12 (e.g. 03 for March)");
			month = getInputInt();
			switch (month) {
			case 1:
				strMonth = "JAN";
				flag = false;
				break;
			case 2:
				strMonth = "FEB";
				flag = false;
				break;
			case 3:
				strMonth = "MAR";
				flag = false;
				break;
			case 4:
				strMonth = "APR";
				flag = false;
				break;
			case 5:
				strMonth = "MAY";
				flag = false;
				break;
			case 6:
				strMonth = "JUN";
				flag = false;
				break;
			case 7:
				strMonth = "JUL";
				flag = false;
				break;
			case 8:
				strMonth = "AUG";
				flag = false;
				break;
			case 9:
				strMonth = "SEP";
				flag = false;
				break;
			case 10:
				strMonth = "OCT";
				flag = false;
				break;
			case 11:
				strMonth = "NOV";
				flag = false;
				break;
			case 12:
				strMonth = "DEC";
				flag = false;
				break;

			default:
				System.out.println("invalid input , try again");
				break;
			}

		}

		listByMonth = lessonService.searcByMonth(month);

		if (listByMonth.size() > 0) {
			bookingService.printMonthlyLearnerReport(listByMonth);
		} else {
			System.out.println("No report found for month " + strMonth);
		}

	}

	public void monthlyCoachReport() {
		List<Integer> listByMonth = new ArrayList<Integer>();
		boolean flag = true;
		int month = 0;
		String strMonth = "";

		while (flag) {

			System.out.println("Enter month number 01-12 (e.g. 03 for March)");
			month = getInputInt();
			switch (month) {
			case 1:
				strMonth = "JAN";
				flag = false;
				break;
			case 2:
				strMonth = "FEB";
				flag = false;
				break;
			case 3:
				strMonth = "MAR";
				flag = false;
				break;
			case 4:
				strMonth = "APR";
				flag = false;
				break;
			case 5:
				strMonth = "MAY";
				flag = false;
				break;
			case 6:
				strMonth = "JUN";
				flag = false;
				break;
			case 7:
				strMonth = "JUL";
				flag = false;
				break;
			case 8:
				strMonth = "AUG";
				flag = false;
				break;
			case 9:
				strMonth = "SEP";
				flag = false;
				break;
			case 10:
				strMonth = "OCT";
				flag = false;
				break;
			case 11:
				strMonth = "NOV";
				flag = false;
				break;
			case 12:
				strMonth = "DEC";
				flag = false;
				break;

			default:
				System.out.println("invalid input , try again");
				break;
			}

		}

		listByMonth = lessonService.searcByMonth(month);

		if (listByMonth.size() > 0) {
			bookingService.printMonthlyCoachReport(listByMonth);
		} else {
			System.out.println("No report found for month " + strMonth);
		}

	}

	public void registerLearner() {

		int id;
		System.out.println("Enter name :- ");
		String name = getInputString();

		System.out.println("Enter gender :- ");
		String gender = getInputString();

		System.out.println("Enter age [should be 4-11] :- ");
		int age = getInputInt();

		while (age < 4 || age > 11) {
			System.out.println("Age should be between 4-11, enter again");
			age = getInputInt();
		}

		System.out.println("Enter phone number :- ");
		String phone = getInputString();

		System.out.println("Enter grade level [0-5] :- ");
		int level = getInputInt();

		while (level < 0 || level > 5) {
			System.out.println("Grade level should be between [0-5], enter again :-  ");
			level = getInputInt();
		}

		learnerService.add(new Learner(name, gender, age, phone, level));

		System.out.println("A new learner has been Registered successfully\r\n");

	}

	public String getInputString() {
		Scanner scanner = new Scanner(System.in);
		boolean flag = true;
		String input = "";
		while (flag) {

			try {
				scanner = new Scanner(System.in);
				input = scanner.nextLine();
				flag = false;

			} catch (Exception e) {
				System.out.println("invalid input , try again");
			}

		}

		return input;
	}

	public int getInputInt() {
		Scanner scanner = new Scanner(System.in);
		boolean flag = true;
		int opt = 0;
		while (flag) {

			try {
				scanner = new Scanner(System.in);
				opt = scanner.nextInt();
				flag = false;

			} catch (Exception e) {
				System.out.println("invalid input , try again");
			}

		}

		return opt;
	}

	public void printAdminMenu() {
		System.out.println("\n");
		System.out.println("Press.....\r");
		System.out.println(" 1. Monthly learner report\r\n" + " 2. Monthly coach report\r\n"
				+ " 3. Register a new learner\r\n" + " 0. Exit\r\n" + "");
		System.out.println("Enter your choise..\n");
	}
	
	public void printLearnerMenu() {
		System.out.println("\n");
		System.out.println("Press.....\r");
		System.out.println(" 1. Book a swimming lesson\r\n" + " 2. Change/Cancel a booking\r\n"
				+ " 3. Attend a swimming lesson\r\n" + " 0. Exit\r\n" + "");
		System.out.println("Enter your choise..\n");
	}

	public void printLessonSearchMenu() {
		System.out.println("\n");
		System.out.println("Press.....\r");
		System.out.println(" 1. Search by specifying the day\r\n" + " 2. By specifying the grade level\r\n"
				+ " 3. by specifying the coach’s name\r\n" + " 4. Go back\r\n" + "");
		System.out.println("Enter your choise..\n");
	}

	public void printChangeCancelMenu() {
		System.out.println("\n");
		System.out.println("Press.....\r");
		System.out.println(" 1. Cancel booking\r\n" + " 2. Change booking\r\n" + " 3. Go back\r\n" + "");
		System.out.println("Enter your choise..\n");
	}

}
