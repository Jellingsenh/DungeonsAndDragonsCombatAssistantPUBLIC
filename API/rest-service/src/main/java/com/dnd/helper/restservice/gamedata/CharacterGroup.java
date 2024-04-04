package com.dnd.helper.restservice.gamedata;

import java.util.Vector;

public class CharacterGroup {
	private String name;
	private Vector<String> characterNames;
	
	public CharacterGroup() {
		this.name = "Default";
		this.characterNames = new Vector<String>();
	}
	
	public CharacterGroup(String groupName, Vector<String> characterNames) {
		this.name = groupName.replaceAll("\\s+","_");
		this.characterNames = characterNames;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name.replaceAll("\\s+","_");
	}
	
	public Vector<String> getCharacterNames() {
		return characterNames;
	}
	public void setCharacterNames(Vector<String> characterNames) {
		this.characterNames = characterNames;
	}
}
