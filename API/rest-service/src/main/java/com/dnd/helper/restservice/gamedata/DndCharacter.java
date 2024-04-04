package com.dnd.helper.restservice.gamedata;

public class DndCharacter {
	private String characterName;
	private long realTime;
	private String version;
	private String type;
	private String level;
	private String race;
	private String appearance;
	private String size;
	private String speeds;
	private int maximumHealth;
	private String hitDice;
	private int currentHealth;
	private int armorClass;
	private int flatFootedAcOrStrSave;
	private int touchAcOrIntSave;
	private int fortSaveOrConSave;
	private int refSaveOrDexSave;
	private int willSaveOrWisSave;
	private int grappleOrChaSave;
	private int initiativeBonusOrDexScore;
	private String mainAttacksAndSpells;
	private String resistances;
	private String vulnerabilities;
	private String reactionsOrLegendaryActions;
	private String otherAttacksAndSpells;
	private String moreSpellsAndAbilities;
	private String additionalSpellsAndAbilities;
	private String featsAndExtraAbilities;
	private String skillsAndProficiencies;
	private String abilityScores;
	private String items;
	private String other;
	// constants:
	final String VERSION_3_5 = "3.5";
	public final String VERSION_5_e = "5e";
	final String DUPLICATE = "duplicate";
	final String VEHICLE = "vehicle";
	final String PLAYER = "player";
	final String NPC = "npc";
	final String ENVIRONMENT = "environment";
	final String ITEM = "item";
	
	public DndCharacter() {
		this.characterName = "No name";
		this.realTime = System.currentTimeMillis();
		this.version = VERSION_3_5;
		this.type = NPC;
		this.level = "1";
		this.race = "human";
		this.appearance = "normal";
		this.size = "M";
		this.speeds = "30 ft";
		this.maximumHealth = 1;
		this.hitDice = "1 HD";
		this.currentHealth = 1;
		this.armorClass =10;
		this.flatFootedAcOrStrSave = 10;
		this.touchAcOrIntSave = 10;
		this.fortSaveOrConSave = 1;
		this.refSaveOrDexSave = 1;
		this.willSaveOrWisSave = 1;
		this.grappleOrChaSave = 1;
		this.initiativeBonusOrDexScore = 1;
		this.mainAttacksAndSpells = "None";
		this.resistances = "None";
		this.vulnerabilities = "None";
		this.reactionsOrLegendaryActions = "None";
		this.otherAttacksAndSpells = "None";
		this.moreSpellsAndAbilities = "None";
		this.additionalSpellsAndAbilities = "None";
		this.featsAndExtraAbilities = "None";
		this.skillsAndProficiencies = "None";
		this.abilityScores = "Str 10 Dex 10 Con 10 Int 10 Wis 10 Cha 10";
		this.items = "None";
		this.other = "None";
	}
	
	public DndCharacter(DndCharacter character) {
		this.characterName = character.getName();
		this.realTime = System.currentTimeMillis();
		this.version = character.getVersion();
		this.type = character.getType();
		this.level = character.getLevel();
		this.race = character.getRace();
		this.appearance = character.getAppearance();
		this.size = character.getSize();
		this.speeds = character.getSpeeds();
		this.maximumHealth = character.getMaximumHealth();
		this.hitDice = character.getHitDice();
		this.currentHealth = character.getCurrentHealth();
		this.armorClass = character.getArmorClass();
		this.flatFootedAcOrStrSave = character.getFlatFootedAcOrStrSave();
		this.touchAcOrIntSave = character.getTouchAcOrIntSave();
		this.fortSaveOrConSave = character.getFortSaveOrConSave();
		this.refSaveOrDexSave = character.getRefSaveOrDexSave();
		this.willSaveOrWisSave = character.getWillSaveOrWisSave();
		this.grappleOrChaSave = character.getGrappleOrChaSave();
		this.initiativeBonusOrDexScore = character.getInitiativeBonusOrDexScore();
		this.mainAttacksAndSpells = character.getMainAttacksAndSpells();
		this.resistances = character.getResistances();
		this.vulnerabilities = character.getVulnerabilities();
		this.reactionsOrLegendaryActions = character.getReactionsOrLegendaryActions();
		this.otherAttacksAndSpells = character.getOtherAttacksAndSpells();
		this.moreSpellsAndAbilities = character.getMoreSpellsAndAbilities();
		this.additionalSpellsAndAbilities = character.getAdditionalSpellsAndAbilities();
		this.featsAndExtraAbilities = character.getFeatsAndExtraAbilities();
		this.skillsAndProficiencies = character.getSkillsAndProficiencies();
		this.abilityScores = character.getAbilityScores();
		this.items = character.getItems();
		this.other = character.getOther();
	}
	
	public String getName() {
		return characterName;
	}
	public void setName(String name) {
		this.characterName = name;
	}
	
	public long getRealTime() {
		return realTime;
	}
	public void setRealTime(long realTime) {
		this.realTime = realTime;
	}
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	
	public String getAppearance() {
		return appearance;
	}
	public void setAppearance(String appearance) {
		this.appearance = appearance;
	}
	
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	
	public String getSpeeds() {
		return speeds;
	}
	public void setSpeeds(String speeds) {
		this.speeds = speeds;
	}
	
	public int getMaximumHealth() {
		return maximumHealth;
	}
	public void setMaximumHealth(int maximumHealth) {
		this.maximumHealth = maximumHealth;
	}
	
	public String getHitDice() {
		return hitDice;
	}
	public void setHitDice(String hitDice) {
		this.hitDice = hitDice;
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
	
	public int getFlatFootedAcOrStrSave() {
		return flatFootedAcOrStrSave;
	}
	public void setFlatFootedAcOrStrSave(int flatFootedAcOrStrSave) {
		this.flatFootedAcOrStrSave = flatFootedAcOrStrSave;
	}
	
	public int getTouchAcOrIntSave() {
		return touchAcOrIntSave;
	}
	public void setTouchAcOrIntSave(int touchAcOrIntSave) {
		this.touchAcOrIntSave = touchAcOrIntSave;
	}
	
	public int getFortSaveOrConSave() {
		return fortSaveOrConSave;
	}
	public void setFortSaveOrConSave(int fortSaveOrConSave) {
		this.fortSaveOrConSave = fortSaveOrConSave;
	}
	
	public int getRefSaveOrDexSave() {
		return refSaveOrDexSave;
	}
	public void setRefSaveOrDexSave(int refSaveOrDexSave) {
		this.refSaveOrDexSave = refSaveOrDexSave;
	}
	
	public int getWillSaveOrWisSave() {
		return willSaveOrWisSave;
	}
	public void setWillSaveOrWisSave(int willSaveOrWisSave) {
		this.willSaveOrWisSave = willSaveOrWisSave;
	}
	
	public int getGrappleOrChaSave() {
		return grappleOrChaSave;
	}
	public void setGrappleOrChaSave(int grappleOrChaSave) {
		this.grappleOrChaSave = grappleOrChaSave;
	}
	
	public int getInitiativeBonusOrDexScore() {
		return initiativeBonusOrDexScore;
	}
	public void setInitiativeBonusOrDexScore(int initiativeBonusOrDexScore) {
		this.initiativeBonusOrDexScore = initiativeBonusOrDexScore;
	}
	
	public String getMainAttacksAndSpells() {
		return mainAttacksAndSpells;
	}
	public void setMainAttacksAndSpells(String mainAttacksAndSpells) {
		this.mainAttacksAndSpells = mainAttacksAndSpells;
	}
	
	public String getResistances() {
		return resistances;
	}
	public void setResistances(String resistances) {
		this.resistances = resistances;
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
	
	public String getOtherAttacksAndSpells() {
		return otherAttacksAndSpells;
	}
	public void setOtherAttacksAndSpells(String otherAttacksAndSpells) {
		this.otherAttacksAndSpells = otherAttacksAndSpells;
	}
	
	public String getMoreSpellsAndAbilities() {
		return moreSpellsAndAbilities;
	}
	public void setMoreSpellsAndAbilities(String moreSpellsAndAbilities) {
		this.moreSpellsAndAbilities = moreSpellsAndAbilities;
	}
	
	public String getAdditionalSpellsAndAbilities() {
		return additionalSpellsAndAbilities;
	}
	public void setAdditionalSpellsAndAbilities(String additionalSpellsAndAbilities) {
		this.additionalSpellsAndAbilities = additionalSpellsAndAbilities;
	}
	
	public String getFeatsAndExtraAbilities() {
		return featsAndExtraAbilities;
	}
	public void setFeatsAndExtraAbilities(String featsAndExtraAbilities) {
		this.featsAndExtraAbilities = featsAndExtraAbilities;
	}
	
	public String getAbilityScores() {
		return abilityScores;
	}
	public void setAbilityScores(String abilityScores) {
		this.abilityScores = abilityScores;
	}
	
	public String getSkillsAndProficiencies() {
		return skillsAndProficiencies;
	}
	public void setSkillsAndProficiencies(String skillsAndProficiencies) {
		this.skillsAndProficiencies = skillsAndProficiencies;
	}
	
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	
	public void setAsDuplicate() {
		this.type = DUPLICATE;
	}
	
	public DndCharacter(String errorMessage) {
		this.characterName = errorMessage;
		this.realTime = System.currentTimeMillis();
	}
}
