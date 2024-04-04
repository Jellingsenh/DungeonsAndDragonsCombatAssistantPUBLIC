package com.dnd.helper.restservice.gamedata;

public class TimedEffectIn {
	private String name;
	private String effect;
	private String targets;
	private DndTime timeRemaining;
	private int endingInitiative;
	
	public TimedEffectIn() {
		this.name = "No name";
		this.effect = "No effect";
		this.targets = "No targets";
		this.setTimeRemaining(new DndTime());
		this.endingInitiative = 0;
	}
	
	public TimedEffectIn(String name, String effect, String targets, DndTime timeIn, int endingInitiative) {
		this.name = name;
		this.effect = effect;
		this.targets = targets;
		this.setTimeRemaining(timeIn);
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

	public DndTime getTimeRemaining() {
		return timeRemaining;
	}

	public void setTimeRemaining(DndTime timeIn) {
		this.timeRemaining = timeIn;
	}
}
