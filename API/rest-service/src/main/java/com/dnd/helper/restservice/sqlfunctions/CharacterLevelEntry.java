package com.dnd.helper.restservice.sqlfunctions;

public class CharacterLevelEntry {
	private String name;
	private double level;
	
	public CharacterLevelEntry(String name, double level) {
		this.name = name;
		this.level= level;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public double getLevel() {
		return level;
	}
	public void setLevel(double level) {
		this.level = level;
	}
}
