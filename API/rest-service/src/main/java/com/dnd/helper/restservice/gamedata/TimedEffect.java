package com.dnd.helper.restservice.gamedata;

public class TimedEffect {
	private String name;
	private String effect;
	private String targets;
	private long timeRemaining;
	private long endingTime;
	private int endingInitiative;
	
	public TimedEffect() {
		this.name = "No name";
		this.effect = "No effect";
		this.targets = "No targets";
		this.timeRemaining = 1;
		this.endingTime = 1;
		this.endingInitiative = 0;
	}
	
	public TimedEffect(String name, String effect, String targets, long remainingTime, long endingTime, int endingInitiative) {
		this.name = name;
		this.effect = effect;
		this.targets = targets;
		this.timeRemaining = remainingTime;
		this.endingTime = endingTime;
		this.endingInitiative = endingInitiative;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEffect() {
		return effect;
	}
	public void setEffect(String effect) {
		this.effect = effect;
	}
	
	public String getTargets() {
		return targets;
	}
	public void setTargets(String targets) {
		this.targets = targets;
	}
	
	public long getTimeRemaining() {
		return timeRemaining;
	}
	public void setTimeRemaining(long remainingTime) {
		this.timeRemaining = remainingTime;
	}
	
	public long getEndingTime() {
		return endingTime;
	}
	public void setEndingTime(long endingTime) {
		this.endingTime = endingTime;
	}
	
	public int getEndingInitiative() {
		return endingInitiative;
	}
	public void setEndingInitiative(int endingInitiative) {
		this.endingInitiative = endingInitiative;
	}
	
	public TimedEffect(String errorMessage) {
		this.name = errorMessage;
	}
}
