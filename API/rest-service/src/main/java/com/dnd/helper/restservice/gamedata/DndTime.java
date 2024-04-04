package com.dnd.helper.restservice.gamedata;

import java.util.Vector;

public class DndTime {
	private long rounds;
	private long minutes;
	private long hours;
	private long days;
	private long years;
	private long totalTimeInRounds;
	private String timeAsString;
	private Vector<Month> months = new Vector<Month>();
	private long daysInAYear = 1;
	private String yearNotation;
	
	public DndTime() {
		this.rounds = 0;
		this.minutes = 0;
		this.hours = 0;
		this.days = 0;
		this.years = 0;
	}
	
	public DndTime(long totalRounds) {
		setupMonths();
		setTimeFromTotalRounds(totalRounds);
	}
	
	public long getTotalTimeInRounds() {
//		System.out.println("years: " + years);
//		System.out.println("daysInAYear: " + daysInAYear);
		this.totalTimeInRounds = rounds + minutes*10 + hours*600 + days*14400 + years*daysInAYear*14400;
		return totalTimeInRounds;
	}
	
	public String setTimeFromTotalRounds(long totalRounds) {
			this.rounds = 0;
			this.minutes = 0;
			this.hours = 0;
			this.days = 0;
			this.years = 0;
						
		if (totalRounds <= 0) {
			return getTotalTimeString();
		} else {
			long timeHolder = totalRounds;
			long roundsInAYear = daysInAYear*14400;
			if (timeHolder >= roundsInAYear) {
				this.years = timeHolder/roundsInAYear;
				timeHolder = timeHolder%roundsInAYear;
			}
			if (timeHolder >= 14400) {
				this.days = timeHolder/14400;
				timeHolder = timeHolder%14400;
			}
			if (timeHolder >= 600) {
				this.hours = timeHolder/600;
				timeHolder = timeHolder%600;
			}
			if (timeHolder >= 10) {
				this.minutes = timeHolder/10;
				timeHolder = timeHolder%10;
			}
			if (timeHolder > 0) {
				this.rounds = timeHolder;
			}
		}
		return getTotalTimeString();
	}

	public String getTotalTimeString() {
		getTotalTimeInRounds();
				
		String timeString = "";
		if (totalTimeInRounds <= 0) {
			timeString = "Round 0.";
		} else {
			if (rounds >= 0) {
				if (!timeString.equals("")) timeString += ", ";
				timeString += "Round " + Long.toString(rounds+1);
			}
			if (hours >= 0 || minutes >= 0) {
				timeString += " of " + makeHourString(hours, minutes);
			}
			if (years >= 0 || days >= 0) {
				if (!timeString.equals("")) timeString += " ";
				String dateString = makeDateString(years, days);
				if (!dateString.equals("")) timeString += dateString;
			}
			if (yearNotation != null && !yearNotation.isEmpty()) {
				timeString += " " + getYearNotation();
			}
			timeString += ".";
		};
		this.timeAsString = timeString;
//		System.out.println("getTotalTimeString: " + timeAsString);
		return timeAsString;
	}
	
	private String makeDateString(long years, long days) {
		String dateString = "";
		String monthAndDayString = "";
		if (days >= 0) {
			monthAndDayString = getCurrentMonthNameAndCurrentDay();
			dateString += monthAndDayString;
		}
		if (years >= 0) {
			if (monthAndDayString != null && !monthAndDayString.isEmpty()) {
				dateString += ", ";
			}
			dateString += Long.toString(years);
		}
		return dateString;
	}
	
	public String getCurrentMonthNameAndCurrentDay() {
		String monthAndDay = "";
		long dayToReach = days+1;
		for (Month month: months) {
			long remainingDays = dayToReach - month.getDays();
			if (remainingDays <= 0) {
				monthAndDay += month.getName() + " ";
				monthAndDay += Long.toString(dayToReach);
				return monthAndDay;
			}
			dayToReach = remainingDays;
		}
		return monthAndDay;
	}
	
	private String makeHourString(long hours, long minutes) {
		String hourAndMinuteString = "";
		String hourString = "";
		String minuteString = "";
		String ampm = ".";
		if (hours + minutes < 0) return hourAndMinuteString;
		if (hours >= 0) {
			ampm = " am.";
			if (hours >= 12) {
				ampm = " pm.";
				if (hours > 12) {
					hours -= 12;
				}
			}
			if (hours == 0) {
				hours = 12; // 12 am
			}
			hourString += Long.toString(hours);
		}
		if (minutes >= 0) {
			if (minutes < 10) {
				minuteString += "0";
			}
			minuteString += Long.toString(minutes);
		}
		hourAndMinuteString = String.format("%s%s%s", hourString, ":", minuteString);
		return hourAndMinuteString + ampm;
	}
	
	private void setupMonths() {
		if (months.size() < 1) {
			months.add(new Month("Icentime", 61));
			months.add(new Month("Errus", 61));
			months.add(new Month("Sorrus", 61));
			months.add(new Month("Darc", 61));
			months.add(new Month("Tymbol", 61));
			months.add(new Month("Bentimbol", 61));
		}

		daysInAYear = 0;
		for (Month month: months) {
			long monthDays = month.getDays();
			daysInAYear += monthDays;
		}
		
		if (yearNotation == null || yearNotation.isEmpty()) {
			this.yearNotation = "Anno Domini";
		}
	}
	
	public Month getCurrentMonth() {
		long dayToReach = days+1;
		for (Month month: months) {
			long remainingDays = dayToReach - month.getDays();
			if (remainingDays <= 0) {
				return month;
			}
			dayToReach = remainingDays;
		}
		return null;
	}
	
	public Vector<Month> getMonths() {
		return months;
	}
	public Vector<Month> setMonths(Vector<Month> newMonths) {
		this.months = newMonths;
		return months;
	}
	
	public long getRounds() {
		return rounds;
	}
	public void setRounds(long rounds) {
		this.rounds = rounds;
	}
	
	public long getMinutes() {
		return minutes;
	}
	public void setMinutes(long minutes) {
		this.minutes = minutes;
	}

	public long getHours() {
		return hours;
	}
	public void setHours(long hours) {
		this.hours = hours;
	}

	public long getDays() {
		return days;
	}
	public void setDays(long days) {
		this.days = days;
	}

	public long getYears() {
		return years;
	}
	public void setYears(long years) {
		this.years = years;
	}

	public long getTotalTimeInSeconds() {
		return totalTimeInRounds;
	}
	public void setTotalTimeInSeconds(long totalTimeInSeconds) {
		this.totalTimeInRounds = totalTimeInSeconds;
	}

	public String getTimeAsString() {
		return timeAsString;
	}
	public void setTimeAsString(String timeAsString) {
		this.timeAsString = timeAsString;
	}

	public String getYearNotation() {
		if (yearNotation == null) {
			return "Twintime";
		}
		return yearNotation;
	}
	public void setYearNotation(String yearNotation) {
		if (yearNotation.length() < 1 || yearNotation == "") {
			yearNotation = "Twintime";
		}
		this.yearNotation = yearNotation;
	}
	
	public long getDaysInAYear() {
		return daysInAYear;
	}
	public void setDaysInAYear(long yearDays) {
		if (yearDays < 1) {
			yearDays = 1;
		}
		this.daysInAYear = yearDays;
	}
}
