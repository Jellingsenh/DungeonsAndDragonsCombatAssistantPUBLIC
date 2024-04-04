package com.dnd.helper.restservice.gamedata;

public class InitiativeOrderEntry {
	private String characterName;
	private int initiativeTotal;
	
	public InitiativeOrderEntry() {
		this.characterName = "No name";
		this.initiativeTotal = 0;
	}
	
	public InitiativeOrderEntry(String characterName, int initiativeTotal) {
		this.characterName = characterName;
		this.initiativeTotal = initiativeTotal;
	}
	
	public String getCharacterName() {
		return characterName;
	}
	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}
	
	public int getInitiativeTotal() {
		return initiativeTotal;
	}
	public void setInitiativeTotal(int initiativeTotal) {
		this.initiativeTotal = initiativeTotal;
	}
}
