package com.dnd.helper.restservice.gamedata;

import java.util.Vector;

public class GameData {
	// Saved in the database:
	private String gameName;
	private long gameTime;
	private long realTime;
	private String environment;
	private String location;
	private Vector<String> combatCharacters;
	private Vector<String> sidelineCharacters;
	private Vector<TimedEffect> currentEffects;
	private String currentCharacter;
	private String nextCharacter;
	private Vector<InitiativeOrderEntry> initiativeOrder;
	private DndTime gameTimeDetails;
	// constants:
	final String NO_CHARACTER = "No character";
	
	public GameData() {
		this.gameName = "Default";
		this.gameTime = 0;
		this.realTime = System.currentTimeMillis();
		this.environment = "No environment";
		this.location = "Nowhere";
		this.combatCharacters = new Vector<String>();
		this.sidelineCharacters = new Vector<String>();
		this.currentEffects = new Vector<TimedEffect>();
		this.currentCharacter = NO_CHARACTER;
		this.nextCharacter = NO_CHARACTER;
		this.initiativeOrder = new Vector<InitiativeOrderEntry>();
		setupGameTimeDetails();
	}
	
	public void setupGameTimeDetails() {
		this.gameTimeDetails = new DndTime(gameTime);
	}
	public String updateGameTimeDetails() {
		updateTimedEffects();
		return gameTimeDetails.setTimeFromTotalRounds(gameTime); 
	}
	public String getTotalTimeString() {
		return gameTimeDetails.getTotalTimeString();
	}
	public String addTime (long timeToAdd) {
		this.gameTime = gameTime + timeToAdd;
		return updateGameTimeDetails();
	}
	public String subtractTime (long timeToSubtract) {
		return addTime(timeToSubtract*-1);
	}
	public String resetTime() {
		this.gameTime = 0;
		return updateGameTimeDetails();
	}
	
	public Month getCurrentMonth() {
		return gameTimeDetails.getCurrentMonth();
	}
	public Vector<Month> getMonths() {
		return gameTimeDetails.getMonths();
	}
	public Vector<Month> setMonths(Vector<Month> months) {
		return gameTimeDetails.setMonths(months);
	}
	
	public void updateTimedEffects() {
		Vector<TimedEffect> effectsToRemove = new Vector<TimedEffect>();
		for (TimedEffect effect: currentEffects) {
			if (!updateTimedEffect(effect)) {
				effectsToRemove.add(effect);
			}
		}
		removeTimedEffects(effectsToRemove);
	}
	private boolean updateTimedEffect(TimedEffect effect) { // return false if the timed effect has ended
		long remainingTime = effect.getEndingTime()-gameTime;
		if (remainingTime <= 0) return false;
		effect.setTimeRemaining(remainingTime);
		return true;
	}
	private void removeTimedEffects(Vector<TimedEffect> effectsToRemove) {
		for (TimedEffect effectToRemove: effectsToRemove) {
			if (!updateTimedEffect(effectToRemove)) {
				currentEffects.removeElement(effectToRemove);
			}
		}
	}
	
	public String getName() {
		return gameName;
	}
	public void setName(String name) {
		this.gameName = name.replaceAll("\\s+","_");
	}
	
	public long getTime() {
		return gameTime;
	}
	public void setTime(long time) {
		this.gameTime = time;
	}
	
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public Vector<String> getCombatCharacters() {
		return combatCharacters;
	}
	public void setCombatCharacters(Vector<String> combatCharacters) {
		this.combatCharacters = combatCharacters;
	}
	public void addCombatCharacter(String character) {
		this.combatCharacters.add(character);
		if (!getInitiativeOrderNames().contains(character)) {
			addInitiativeEntry(character, 0);
		}
	}
	public boolean removeCombatCharacter(String character) {
		return removeInitiativeEntry(character) && combatCharacters.removeElement(character);
	}

	public Vector<String> getSidelineCharacters() {
		return sidelineCharacters;
	}
	public void setSidelineCharacters(Vector<String> sidelineCharacters) {
		this.sidelineCharacters = sidelineCharacters;
	}
	public void addSidelineCharacter(String character) {
		this.sidelineCharacters.add(character);
	}
	public boolean removeSidelineCharacter(String characterName) {
		return sidelineCharacters.removeElement(characterName);
	}
	
	public Vector<TimedEffect> getCurrentEffects() {
		return sortTimedEffetcs(currentEffects);
	}
	private Vector<TimedEffect> sortTimedEffetcs(Vector<TimedEffect> unsortedEffects) {
		Vector<TimedEffect> sortedEffects = new Vector<TimedEffect>();
		for (TimedEffect unsortedEffect : unsortedEffects) {
			if (sortedEffects.isEmpty()) {
				sortedEffects.add(unsortedEffect);
			} else {
				long newRemainingTime = unsortedEffect.getTimeRemaining();
				int indexBeforeCurrentEntry = 0;
				boolean placed = false;
				for (TimedEffect existingEffect : sortedEffects) {
					long existingRemainingTime = existingEffect.getTimeRemaining();
					if (newRemainingTime < existingRemainingTime) {
						sortedEffects.add(indexBeforeCurrentEntry, unsortedEffect); // add unsorted before the current entry
						placed = true;
						break;
					}
					indexBeforeCurrentEntry++;
				} // break exits here
				if (!placed) sortedEffects.add(unsortedEffect); // add unsorted to the end
			}
		}
		return sortedEffects;
	}
	public Vector<String> getCurrentEffectNames() {
		Vector<String> currentEffectNames = new Vector<String>();
		for (TimedEffect effect: currentEffects) {
			currentEffectNames.add(effect.getName());
		}
		return currentEffectNames;
	}
	public void setCurrentEffects(Vector<TimedEffect> currentEffects) {
		this.currentEffects = currentEffects;
	}
	public void addCurrentEffect(TimedEffect effect) {
		this.currentEffects.add(effect);
	}
	public void editTimedEffect(TimedEffectEdit effect) {
		for (TimedEffect te: currentEffects) {
			if (te.getName().equals(effect.getName())) {
				te.setEffect(effect.getEffect());
				te.setTargets(effect.getTargets());
				te.setEndingInitiative(effect.getEndingInitiative());
				te.setTimeRemaining(effect.getTimeRemaining());
				te.setEndingTime(getTime()+te.getTimeRemaining());
			}
		}
	}
	public boolean removeEffect(TimedEffect effect) {
		return currentEffects.removeElement(effect);
	}
	
	public String getCurrentCharacter() {
//		System.out.println("currentCharacter: " + currentCharacter);
		return currentCharacter;
	}
	public void setCurrentCharacter(String currentTurn) {
		this.currentCharacter = currentTurn;
	}
	public String getNextCharacter() {
		return nextCharacter;
	}
	public void setNextCharacter(String nextTurn) {
		this.nextCharacter = nextTurn;
	}
	public String findNextNextCharacter() {
		for (int i = 0; i < initiativeOrder.size(); i++) {
//			System.out.println("findNextNextCharacter: " + initiativeOrder.get(i).getCharacterName());
			if (initiativeOrder.get(i).getCharacterName().equals(nextCharacter)) {
				if (i+1 == initiativeOrder.size()) {
					return initiativeOrder.get(0).getCharacterName();
				} else {
					return initiativeOrder.get(i+1).getCharacterName();
				}
			}
		}
		return NO_CHARACTER;
	}
	public void doNextTurn() {
		
//		try {
//			System.out.println("doNextTurn initiative: " + new ObjectMapper().writeValueAsString(initiativeOrder));
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
		
		this.currentCharacter = nextCharacter;
		this.nextCharacter = findNextNextCharacter();
		if (currentCharacter.equals(initiativeOrder.get(0).getCharacterName())) addTime(1);
	}
	
	public Vector<InitiativeOrderEntry> getInitiativeOrder() {
		return initiativeOrder;
	}
	public Vector<String> getInitiativeOrderNames() {
		Vector<String> initiativeNames = new Vector<String>();
		for (InitiativeOrderEntry entry: getInitiativeOrder()) {
			initiativeNames.add(entry.getCharacterName());
		}
		return initiativeNames;
	}
	public void setInitiativeOrder(Vector<InitiativeOrderEntry> initiativeOrder) {
		this.initiativeOrder = initiativeOrder;
	}
	public void addInitiativeEntry(String characterName, int initiativeTotal) {
		this.initiativeOrder.add(new InitiativeOrderEntry(characterName, initiativeTotal));
	}
	private boolean removeInitiativeEntry(String character) {
		for (int i = 0; i < initiativeOrder.size(); i++) {
			if (initiativeOrder.get(i).getCharacterName().equals(character) ) {
				if (getNextCharacter().equals(character)) {
					if (i+1 == initiativeOrder.size()) {
						setNextCharacter(initiativeOrder.get(0).getCharacterName());
					} else {
						setNextCharacter(initiativeOrder.get(i+1).getCharacterName());
					}
				}
				initiativeOrder.removeElementAt(i); // removeElement(initiativeOrder.get(i));
				return true;
			}
		}
		return false;
	}

	public long getRealTime() {
		return realTime;
	}
	public void setRealTime(long realTime) {
		this.realTime = realTime;
	}

	public String getYearNotation() {
		return gameTimeDetails.getYearNotation();
	}
	public void setYearNotation(String yearNotation) {
		gameTimeDetails.setYearNotation(yearNotation);
	}
	
	public long getDaysInAYear() {
		return gameTimeDetails.getDaysInAYear();
	}
	public void setDaysInAYear(long days) {
		gameTimeDetails.setDaysInAYear(days);
	}
	
	public DndTimeDetails getTimeDetails() {
		DndTimeDetails details = new DndTimeDetails();
		details.setYearNotation(getYearNotation());
		details.setDaysInAYear(getDaysInAYear());
		details.setMonths(getMonths());
		return checkTimeDetails(details);
	}
	public DndTimeDetails checkTimeDetails(DndTimeDetails timeDetails) {
//		System.out.println("Checking time details...");
//		System.out.println("Months:");
//		for (Month m: timeDetails.getMonths()) {
//			System.out.println(m.getName());
//		}
//		System.out.println(timeDetails.getYearNotation());
//		System.out.println("days in a year: " + timeDetails.getDaysInAYear());
		
		long totalDays = 0;
		for (Month m: timeDetails.getMonths() ) {
			totalDays += m.getDays();
		}
		
		if (totalDays == timeDetails.getDaysInAYear()) {
			return timeDetails;
		} else if (totalDays > timeDetails.getDaysInAYear()) { // add to daysInAYear
			System.out.println("Time details are unbalanced (More total days in all months than days in the year). Adding days to the year...");
			setDaysInAYear(totalDays);
			timeDetails.setDaysInAYear(totalDays);
			return timeDetails;
		} 
		// add extra days to months
		System.out.println("Time details are unbalanced (More days in the year than total days in all months). Adding days to months...");
		timeDetails.setMonths(fillMonths(timeDetails.getMonths()));
		setMonths(timeDetails.getMonths());
		return timeDetails;
	}
	public String updateTimeDetails(DndTimeDetails timeDetails) {
		String yearNotation = timeDetails.getYearNotation();
		if (yearNotation == "" || yearNotation.length() < 1) {
			yearNotation = "Anno Domini";
		}
		setYearNotation(yearNotation);
		
		long yearDays = timeDetails.getDaysInAYear();
		if (yearDays < 1) {
			yearDays = 1;
		}
		setDaysInAYear(yearDays);
		
		Vector<Month> monthDetails = timeDetails.getMonths();
		if (monthDetails.size() < 1) {
			monthDetails.add(new Month("", yearDays));
		} else {
			// auto-fill months here if they have 0 days
			monthDetails = fillMonths(monthDetails);
		}
		setMonths(monthDetails);
		return "Updated the time details";
	}
	private Vector<Month> fillMonths(Vector<Month> months) {
		Vector<Month> emptyMonths = new Vector<Month>();
		long daysUsedCount = 0;
		for (Month m: months) {
			if (m.getDays() == 0) {
				emptyMonths.add(m);
			} else {
				daysUsedCount += m.getDays();
			}
		}
		long daysLeft = getDaysInAYear() - daysUsedCount;
//		System.out.println("Adding " + daysLeft + " days to the year, across varoius months.");
		long daysPerMonth;
		long leftoverDays;
		
		if (!emptyMonths.isEmpty()) {
			daysPerMonth = daysLeft / emptyMonths.size();
			leftoverDays = daysLeft % emptyMonths.size();
			
			for (Month m: emptyMonths) {
				m.setDays(daysPerMonth);
			}
			while (leftoverDays > 0) {
				for (Month m: emptyMonths) {
					if ((int) ((Math.random() * (2)) + 1) > 1) { // 50% chance
						m.setDays(m.getDays()+1);
						leftoverDays--;
						if (leftoverDays == 0) {
							break;
						}
					}
				}
			}
		} else {
			// no empty months, but still extra days:
			daysPerMonth = daysLeft / months.size();
			leftoverDays = daysLeft % months.size();
			
			for (Month m: months) {
				m.setDays(daysPerMonth + m.getDays());
			}
			while (leftoverDays > 0) {
				for (Month m: months) {
					if ((int) ((Math.random() * (2)) + 1) > 1) { // 50% chance
						m.setDays(m.getDays()+1);
						leftoverDays--;
						if (leftoverDays == 0) {
							break;
						}
					}
				}
			}
		}
		return months;
	}
	
	public GameData(String errorMessage) {
		this.gameName = errorMessage;
		this.realTime = System.currentTimeMillis();
	}
}
