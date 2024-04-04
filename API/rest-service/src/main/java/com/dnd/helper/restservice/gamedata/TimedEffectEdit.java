package com.dnd.helper.restservice.gamedata;

public class TimedEffectEdit {
	private String name;
	private String effect;
	private String targets;
	private long timeRemaining;
	private int endingInitiative;
	
	public TimedEffectEdit() {
		this.name = "No name";
		this.effect = "No effect";
		this.targets = "No targets";
		this.timeRemaining = 1;
		this.endingInitiative = 0;
	}
	
	public TimedEffectEdit(String name, String effect, String targets, long timeIn, int endingInitiative) {
		this.name = name;
		this.effect = effect;
		this.targets = targets;
		this.timeRemaining = timeIn;
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
	public int getEndingInitiative() {
		return endingInitiative;
	}
	public void setEndingInitiative(int endingInitiative) {
		this.endingInitiative = endingInitiative;
	}

	public long getTimeRemaining() {
		return timeRemaining;
	}

	public void setTimeRemaining(long timeIn) {
		this.timeRemaining = timeIn;
	}
}

