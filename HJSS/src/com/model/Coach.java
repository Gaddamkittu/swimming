package com.model;

import java.util.Objects;

public class Coach {
	
	private String name;
	private int noOfRatings;
	private int avgRating;
	private String review;

	public Coach() {
	}

	public Coach(String name) {
		this.name = name;
	}

	
	
	
	public Coach(String name, int noOfRatings, int avgRating, String review) {
		
		this.name = name;
		this.noOfRatings = noOfRatings;
		this.avgRating = avgRating;
		this.review = review;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getNoOfRatings() {
		return noOfRatings;
	}

	private void setNoOfRatings(int noOfRatings) {
		this.noOfRatings = this.noOfRatings+noOfRatings;
	}

	public int getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(int avgRating) {
		
		this.avgRating=this.avgRating*getNoOfRatings();		
		setNoOfRatings(1);
		
		this.avgRating = (this.avgRating+avgRating)/getNoOfRatings();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	@Override
	public String toString() {
		return "Coach [name=" + name + ", noOfRatings=" + noOfRatings + ", avgRating=" + avgRating + ", review="
				+ review + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coach other = (Coach) obj;
		return Objects.equals(name, other.name);
	}
	
	
	

}
