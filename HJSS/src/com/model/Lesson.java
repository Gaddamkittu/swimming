package com.model;

public class Lesson {
	
	private int id;
	private String date;
	private int  month;
	private String year;
	private String day;
	private String time;
	private int level;
	private int seat;
	private String coachName;
	
	
	public Lesson(int id, String date, int month, String year, String day, String time, int level, int seat,
			String coachName) {
		super();
		this.id = id;
		this.date = date;
		this.month = month;
		this.year = year;
		this.day = day;
		this.time = time;
		this.level = level;
		this.seat = seat;
		this.coachName = coachName;
	}
	public Lesson(String date, int month, String year, String day, String time, int level, int seat,
			String coachName) {
		super();
		this.date = date;
		this.month = month;
		this.year = year;
		this.day = day;
		this.time = time;
		this.level = level;
		this.seat = seat;
		this.coachName = coachName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getSeat() {
		return seat;
	}
	public void setSeat(int seat) {
		
		if(seat>4) {
			this.seat =4;
		}
		else {
			this.seat = seat;
		}
		
		
	}
	public String getCoachName() {
		return coachName;
	}
	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}
	@Override
	public String toString() {
		return "Lesson [id=" + id + ", date=" + date + ", month=" + month + ", year=" + year + ", day=" + day
				+ ", time=" + time + ", level=" + level + ", seat=" + seat + ", coachName=" + coachName + "]";
	}
	
	
	
	

}





