package com.dnd.helper.restservice.gamedata;

public class Month {
	private String name;
	private long days;
//	private String season;
	
	public Month() {
		this.name = "Amonth";
		this.days = 0;
	}
	
	public Month(String name, long days) {
		this.name = name;
		this.days = days;
//		this.season = season;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getDays() {
		return days;
	}
	public void setDays(long days) {
		this.days = days;
	}
//	public String getSeason() {
//		return season;
//	}
//	public void setSeason(String season) {
//		this.season = season;
//	}
	
	public Month (String errorMessage) {
		this.name = errorMessage;
	}
}
