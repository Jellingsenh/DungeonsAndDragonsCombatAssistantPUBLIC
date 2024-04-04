package com.dnd.helper.restservice.gamedata;

import java.util.Vector;

public class DndTimeDetails {
	private Vector<Month> months = new Vector<Month>();
	private long daysInAYear;
	private String yearNotation;
	
	public DndTimeDetails() {
		this.yearNotation = "Anno Domini";
		this.daysInAYear = 1;
	}

	public Vector<Month> getMonths() {
		return months;
	}

	public void setMonths(Vector<Month> months) {
		this.months = months;
	}

	public long getDaysInAYear() {
		return daysInAYear;
	}

	public void setDaysInAYear(long daysInAYear) {
		this.daysInAYear = daysInAYear;
	}

	public String getYearNotation() {
		return yearNotation;
	}

	public void setYearNotation(String yearNotation) {
		this.yearNotation = yearNotation;
	}
	
	public DndTimeDetails(String errorMessage) {
		this.yearNotation = errorMessage;
	}

}
