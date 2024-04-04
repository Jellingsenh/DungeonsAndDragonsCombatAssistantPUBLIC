package com.dnd.helper.restservice.gamedata;

public class DndCharacterMinimal {
	private String characterName;
	private int currentHealth;
	private int maximumHealth;
	private int armorClass;
	private String mainAttacksAndSpells;
	private String resistances;
	private String vulnerabilities;
	private String reactionsOrLegendaryActions;
	private int initiativeTotal;
	private boolean isNpc;
	
	public DndCharacterMinimal() {
		this.characterName = "No name";
		this.currentHealth = 10;
		this.maximumHealth = 10;
		this.armorClass = 10;
		this.mainAttacksAndSpells = "No attacks";
		this.resistances = "None";
		this.vulnerabilities = "None";
		this.reactionsOrLegendaryActions = "None";
		this.initiativeTotal = 0;
		this.isNpc = true;
	}
	
	public DndCharacterMinimal(String characterName, int currentHealth, int maximumHealth,  int armorClass, String mainAttacksAndSpells, String resistances, String vulnerabilities, String reactionsOrLegendaryActions, int initiativeTotal, boolean isNpc) {
		this.characterName = characterName;
		this.currentHealth = currentHealth;
		this.maximumHealth = maximumHealth;
		this.armorClass = armorClass;
		this.mainAttacksAndSpells = mainAttacksAndSpells;
		this.resistances = resistances;
		this.vulnerabilities = vulnerabilities;
		this.reactionsOrLegendaryActions = reactionsOrLegendaryActions;
		this.initiativeTotal = initiativeTotal;
		this.isNpc = isNpc;
	}
	
	public String getName() {
		return characterName;
	}
	public void setName(String characterName) {
		this.characterName = characterName;
	}
	
	public int getCurrentHealth() {
		return currentHealth;
	}
	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}
	
	public int getArmorClass() {
		return armorClass;
	}
	public void setArmorClass(int armorClass) {
		this.armorClass = armorClass;
	}
	
	public String getResistances() {
		return resistances;
	}
	public void setResistances(String resistances) {
		this.resistances = resistances;
	}
	
	public String getMainAttacksAndSpells() {
		return mainAttacksAndSpells;
	}
	public void setMainAttacksAndSpells(String mainAttacksAndSpells) {
		this.mainAttacksAndSpells = mainAttacksAndSpells;
	}
	
	public String getVulnerabilities() {
		return vulnerabilities;
	}
	public void setVulnerabilities(String vulnerabilities) {
		this.vulnerabilities = vulnerabilities;
	}
	
	public String getReactionsOrLegendaryActions() {
		return reactionsOrLegendaryActions;
	}
	public void setReactionsOrLegendaryActions(String reactionsOrLegendaryActions) {
		this.reactionsOrLegendaryActions = reactionsOrLegendaryActions;
	}
	
	public int getInitiativeTotal() {
		return initiativeTotal;
	}
	public void setInitiativeTotal(int initiativeTotal) {
		this.initiativeTotal = initiativeTotal;
	}
	
	public int getMaximumHealth() {
		return maximumHealth;
	}
	public void setMaximumHealth(int maximumHealth) {
		this.maximumHealth = maximumHealth;
	}
	public boolean isNpc() {
		return isNpc;
	}
	public void setNpc(boolean isNpc) {
		this.isNpc = isNpc;
	}
	
	public DndCharacterMinimal(String errorMessage) {
		this.characterName = errorMessage;
	}
}
