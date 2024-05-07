package com.dnd.helper.restservice.gamedata;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Vector;

import com.dnd.helper.restservice.sqlfunctions.DndDatabase;
import com.fasterxml.jackson.core.JsonProcessingException;

public class GameManager {
	private GameData gameData;
	private DndDatabase dndDatabase;
	
	public GameManager() throws ClassNotFoundException, SQLException, IOException{
		dndDatabase = new DndDatabase();
		gameData = new GameData();
		
		if (!getAllGameNames().contains("BLANK")) {
			gameData.setName("BLANK");
			gameData.setYearNotation("Anno Domini");
			dndDatabase.saveGame(gameData);
			gameData.setName("Default");
		}
	}
	
	// GAME functions:

	public Vector<String> getAllGameNames() throws SQLException {
		return dndDatabase.getAllGameNames();
	}
	
	public Vector<String> getAllGameNamesReverseAlphabetical() throws SQLException {
		Vector<String> reverseGameNames = dndDatabase.getAllGameNames();
		Collections.reverse(reverseGameNames);
		return reverseGameNames;
	}
	
	public Vector<String> getAllGameNamesChronological() throws SQLException {
		return dndDatabase.getAllGameNamesChronological();
	}
	
	public Vector<String> getAllGameNamesReverseChronological() throws SQLException {
		Vector<String> reverseChronologicalGameNames = dndDatabase.getAllGameNamesChronological();
		Collections.reverse(reverseChronologicalGameNames);
		return reverseChronologicalGameNames;
	}
	
	public GameData getCurrentGame() {
		return gameData;
	}
	
	public String loadGame(String gameName) throws SQLException, JsonProcessingException {
		if (!getAllGameNames().contains(gameName)) return "Game " + gameName + " does not exist.";
		this.gameData = dndDatabase.loadGame(gameName);
		return "Game " + gameName + " loaded.";
	}

	public String saveGame(String gameName) throws SQLException, JsonProcessingException {
		gameData.setName(gameName);
		if (getAllGameNames().contains(gameName)) {
			dndDatabase.updateGame(gameData);
			return "Game " + gameData.getName() + " was updated.";
		} else {
			dndDatabase.saveGame(gameData);
			return "Game " + gameData.getName() + " was saved.";
		}
	}

	public String deleteGame(String gameName) throws SQLException {
		if (!getAllGameNames().contains(gameName)) return "Game " + gameName + " does not exist.";
		dndDatabase.deleteGame(gameName);
		return "Game " + gameName + " was deleted.";
	}
	
	// CHARACTER functions:
	
	public Vector<String> getAllCharacterNames(boolean includeCR, String searchBy) throws SQLException {
		if (includeCR) {
			return dndDatabase.getAllCharacterSaveNames(searchBy);
		} else {	
			return dndDatabase.getAllCharacterSaveNamesWithoutCRorOrder();
		}
	}
	
	public Vector<String> getAllCharacterNamesReverse(String searchBy) throws SQLException {
		Vector<String> reverseCharacterSaveNames = dndDatabase.getAllCharacterSaveNames(searchBy);
		Collections.reverse(reverseCharacterSaveNames);
		return reverseCharacterSaveNames;
	}
	
	public Vector<String> getAllCharacterNamesChronological(String searchBy) throws SQLException {
		return dndDatabase.getAllCharacterNamesChronological(searchBy);
	}
	
	public Vector<String> getAllCharacterNamesReverseChronological(String searchBy) throws SQLException {
		Vector<String> reverseChronologicalCharacterSaveNames = dndDatabase.getAllCharacterNamesChronological(searchBy);
		Collections.reverse(reverseChronologicalCharacterSaveNames);
		return reverseChronologicalCharacterSaveNames;
	}
	
	public Vector<String> getAllCharacterNamesByLevel(String searchBy) throws SQLException {
		return dndDatabase.getAllCharacterNamesByLevel(searchBy);
	}

	public Vector<String> getAllCharacterNamesByReverseLevel(String searchBy) throws SQLException {
		Vector<String> reverseLevelCharacterSaveNames = dndDatabase.getAllCharacterNamesByLevel(searchBy);
		Collections.reverse(reverseLevelCharacterSaveNames);
		return reverseLevelCharacterSaveNames;
	}
	
	public Vector<String> getAllCharacterNamesByType(String searchBy) throws SQLException {
		return dndDatabase.getAllCharacterNamesByType(searchBy);
	}

	public Vector<String> getAllCharacterNamesByReverseType(String searchBy) throws SQLException {
		Vector<String> reverseLevelCharacterSaveNames = dndDatabase.getAllCharacterNamesByType(searchBy);
		Collections.reverse(reverseLevelCharacterSaveNames);
		return reverseLevelCharacterSaveNames;
	}
	
	public DndCharacter getCharacter(String characterName) throws Exception {
		if (!getAllCharacterNames(false, "").contains(characterName)) {
			throw new Exception("There is no character save for " + characterName);
		}
		return dndDatabase.loadCharacter(characterName);
	}
	
	public String enterCharacterIntoArena(String characterName) throws Exception {
		characterName = removeCrFromName(characterName);
		if (!getAllCharacterNames(false ,"").contains(characterName)) {
			throw new Exception("There is no character save for " + characterName);
		}
		if (isCharacterInArena(characterName)) {
			return characterName + " is already in the arena.";
		}
		
		gameData.addSidelineCharacter(characterName);
		return characterName + " has entered the arena.";
	}

	public String addCharacter(DndCharacter character) throws Exception {
		while (getAllCharacterNames(false ,"").contains(character.getName())) {
			character.setName(character.getName() + " Copy");
		}
		dndDatabase.saveCharacter(character);
		gameData.addSidelineCharacter(character.getName());
		return "Character " + character.getName() + " was saved.";
	}
	
	public String addCharacterToCombat(DndCharacter character) throws Exception {
		while (getAllCharacterNames(false ,"").contains(character.getName())) {
			character.setName(character.getName() + " Copy");
		}
		dndDatabase.saveCharacter(character);
		gameData.addCombatCharacter(character.getName());
		return "Character " + character.getName() + " was saved.";
	}

	public String deleteCharacter(String characterName) throws Exception {
		characterName = removeCrFromName(characterName);
		if (!getAllCharacterNames(false, "").contains(characterName)) {
			throw new Exception("There is no character save for " + characterName);
		}
		removeCharacterFromArena(characterName);
		dndDatabase.deleteCharacter(characterName, gameData.getName());
		return "Character " + characterName + " was deleted.";
	}

	public Vector<String> getCombatCharacters() {
		Vector<String> combatCharacters = new Vector<String>();
		combatCharacters.addAll(gameData.getCombatCharacters());
		return combatCharacters;
	}

	public Vector<String> getSidelineCharacters() {
		Vector<String> sidelineCharacters = new Vector<String>();
		sidelineCharacters.addAll(gameData.getSidelineCharacters());
		return sidelineCharacters;
	}

	public Vector<String> getAllArenaCharacterNames() {
		Vector<String> allCharacters = new Vector<String>();
		allCharacters.addAll(getCombatCharacters());
		allCharacters.addAll(getSidelineCharacters());
		return allCharacters;
	}

	public boolean isCharacterInArena(String characterName) {
		return getAllArenaCharacterNames().contains(characterName);
	}

	public String editCharacter(DndCharacter updatedCharacter, String oldCharacterName) throws Exception {
		if (!isCharacterInArena(oldCharacterName)) {
			throw new Exception("There is no character named " + oldCharacterName + " in the arena to update.");
		}

		String successString = oldCharacterName + " edited successfully.";
		if (updatedCharacter.getName().equals(oldCharacterName)) {
			dndDatabase.updateCharacter(updatedCharacter);
		} else {
			addCharacter(updatedCharacter);
			successString = updatedCharacter.getName() + " added.";
		}
		return successString;
	}
	
	public String removeCharacterFromArena(String characterName) {
		if (!isCharacterInArena(characterName)) {
			return "Character " + characterName + " is not in the arena.";
		}
		if (gameData.removeSidelineCharacter(characterName)) {
			return "Character " + characterName + " was removed from the arena.";
		}
		if (gameData.removeCombatCharacter(characterName)) {
			return "Character " + characterName + " was removed from the arena.";
		}
		return "Character " + characterName + " was not removed from the arena.";
	}

	public String moveCharacterToCombat(String characterName) {
		if (!isCharacterInArena(characterName)) {
			return "Character " + characterName + " is not in the arena.";
		}
		if (gameData.removeSidelineCharacter(characterName)) {
			gameData.addCombatCharacter(characterName);
			return "Character " + characterName + " was moved into combat.";
		}
		return "Character " + characterName + " was not moved into combat.";
	}

	public String moveCharacterToSideline(String characterName) {
		if (!isCharacterInArena(characterName)) {
			return "Character " + characterName + " is not in the arena.";
		}
		if (gameData.removeCombatCharacter(characterName)) {
			gameData.addSidelineCharacter(characterName);
			return "Character " + characterName + " was moved to the sideline.";
		}
		return "Character " + characterName + " was not moved to the sideline.";
	}

	public String duplicateCharacter(String characterName, int numberOfDuplicates) throws SQLException {
		if (!isCharacterInArena(characterName)) {
			return "Character " + characterName + " is not in the arena.";
		}
		
		DndCharacter loadedCharacter = dndDatabase.loadCharacter(characterName);
		String baseName = loadedCharacter.getName();
		int nameModifier = 2;
		
		for (int duplicatesAdded = 0; duplicatesAdded < numberOfDuplicates; duplicatesAdded++) {
			String newName = baseName + " " + Integer.toString(nameModifier);
			nameModifier += 1;

			DndCharacter duplicateCharacter = new DndCharacter(loadedCharacter);
			duplicateCharacter.setName(newName);
			duplicateCharacter.setAsDuplicate();
			if (getAllDuplicateNames().contains(newName)) {
				dndDatabase.updateCharacter(duplicateCharacter);
			} else {
				dndDatabase.saveCharacter(duplicateCharacter);
			}
			gameData.addCombatCharacter(newName); // duplicates are added to combat, unlike most character adds
		}
		return "Character " + characterName + " was duplicated. (x" + Integer.toString(numberOfDuplicates) + ")";
	}
	
	public String removeAllDuplicates() throws SQLException {
		for (String duplicateName: getAllDuplicateNames()) {
			removeCharacterFromArena(duplicateName);
		}
		dndDatabase.removeAllDuplicates(gameData);
		return "All duplicates have been removed.";
	}
	
	public Vector<String> getAllDuplicateNames() throws SQLException {
		return dndDatabase.getAllDuplicateNames();
	}
	
//	private Vector<String> getAllDuplicateNamesInArena() throws SQLException {
//		Vector<String> duplicatesInArena = new Vector<String>();
//		for (String duplicateName: getAllDuplicateNames()) {
//			if (isCharacterInArena(duplicateName)) {
//				duplicatesInArena.add(duplicateName);
//			}
//		}
//		return duplicatesInArena;
//	}
	
	public Vector<String> getCombatPCs() {
		Vector<String> combatPCs = new Vector<String>();
		for (String characterName: gameData.getCombatCharacters()) {
			try {
				DndCharacter combatCharacter = dndDatabase.loadCharacter(characterName);
				if (combatCharacter.getType().equals(combatCharacter.PLAYER)) {
					combatPCs.add(characterName);
				}
			} catch (SQLException e) {
				System.out.println("Failed to load " + characterName + " for combat PC list.");
				e.printStackTrace();
			}
		}
		return combatPCs;
	}
	
	public boolean isCharacterNpc(String characterName) throws Exception {
		if (!getAllCharacterNames(false, "").contains(characterName)) {
			throw new Exception("There is no character save for " + characterName);
		}
		DndCharacter character = dndDatabase.loadCharacter(characterName);
		return character.getType().equals(character.NPC);
	}
	
	// INITIATIVE functions:
	
	public Vector<DndCharacterMinimal> rollInitiative(Vector<InitiativeOrderEntry> unsortedInitiative) throws SQLException {
		for (String characterName: gameData.getCombatCharacters()) {
			try {
				DndCharacter combatCharacter = dndDatabase.loadCharacter(characterName);
				if (combatCharacter.getType().equals(combatCharacter.NPC) || combatCharacter.getType().equals(combatCharacter.DUPLICATE) || combatCharacter.getType().equals(combatCharacter.VEHICLE)) {
					int result = combatCharacter.getInitiativeBonusOrDexScore() + getRandomNumber(1,20); // roll a d20
//					System.out.println("rolling " + combatCharacter.getName() + "\'s initiative: " + result);
					unsortedInitiative.add(new InitiativeOrderEntry(combatCharacter.getName(), result));
				} else if (combatCharacter.getType().equals(combatCharacter.ITEM) || combatCharacter.getType().equals(combatCharacter.ENVIRONMENT)) {
					unsortedInitiative.add(new InitiativeOrderEntry(combatCharacter.getName(), combatCharacter.getInitiativeBonusOrDexScore()));
				}
			} catch (SQLException e) {
				System.out.println("Failed to load " + characterName + " for initiative roll.");
				e.printStackTrace();
			}
		}
		gameData.setInitiativeOrder(sortInitiative(unsortedInitiative));
		setInitialCharacters();
		return getInitiativeOrder();
	}
	
	public void setInitialCharacters() throws SQLException {
		if (gameData.getInitiativeOrder().size() < 1) return;
		
		gameData.setCurrentCharacter(gameData.getInitiativeOrder().get(0).getCharacterName());
		if (gameData.getInitiativeOrder().size() > 1) {
			gameData.setNextCharacter(gameData.getInitiativeOrder().get(1).getCharacterName());
		} else {
			gameData.setNextCharacter(gameData.getInitiativeOrder().get(0).getCharacterName());
		}
//		System.out.println("initial current & next characters: " + gameData.getCurrentCharacter() + " & " + gameData.getNextCharacter());
	}

	private void checkForEmptyInitiativeOrder() throws SQLException {
		if (gameData.getInitiativeOrder().isEmpty() && !gameData.getCombatCharacters().isEmpty()) {
			Vector<InitiativeOrderEntry> defaultInitiative = new Vector<InitiativeOrderEntry>();
			for (String characterName: gameData.getCombatCharacters()) {
				defaultInitiative.add(new InitiativeOrderEntry(characterName, 0));
			}
			gameData.setInitiativeOrder(sortInitiative(defaultInitiative));
		}
//		if (gameData.getCurrentCharacter().equals(gameData.NO_CHARACTER) || gameData.getNextCharacter().equals(gameData.NO_CHARACTER)) setInitialCharacters();  
	}

	public Vector<DndCharacterMinimal> getInitiativeOrder() throws SQLException {
		checkForEmptyInitiativeOrder();
//		if (sorted) {
//			return buildFullInitiativeFromInitiativeOrder(sortInitiative(gameData.getInitiativeOrder()));
//		}
		return buildFullInitiativeFromInitiativeOrder(gameData.getInitiativeOrder());
	}
	
	public String getCharacterInitiativeTotal(String characterName) throws SQLException {
		for (DndCharacterMinimal minimalCharacter: getInitiativeOrder()) {
			if (minimalCharacter.getName().equals(characterName)) {
				return Integer.toString(minimalCharacter.getInitiativeTotal());
			}
		}
		return "0";
	}
	
	private Vector<InitiativeOrderEntry> sortInitiative(Vector<InitiativeOrderEntry> unsortedInitiative) throws SQLException {
		Vector<InitiativeOrderEntry> sortedInitiative = new Vector<InitiativeOrderEntry>();
		
//		System.out.println("~~~~~\nUnsorted initiative:");
//		for (InitiativeOrderEntry unsortedEntry: unsortedInitiative) {
//			System.out.println(unsortedEntry.getCharacterName()+ ": " +  unsortedEntry.getInitiativeTotal());
//		}
		
		for (InitiativeOrderEntry unsortedEntry: unsortedInitiative) {
			if (sortedInitiative.isEmpty()) {
				sortedInitiative.add(unsortedEntry);
			} else {
				boolean placed = false;
				for (int i = 0; i < sortedInitiative.size(); i++) {
					if (unsortedEntry.getInitiativeTotal() > sortedInitiative.get(i).getInitiativeTotal() || unsortedHasEqualInitiativeButHigherDexterity(unsortedEntry, sortedInitiative.get(i))) {
						sortedInitiative.add(i, unsortedEntry);
						placed = true;
						break;
					}
				}
				if (!placed) {
					sortedInitiative.add(unsortedEntry);
				}
			}
		}

//		System.out.println("~~~~~\nSorted initiative:");
//		for (InitiativeOrderEntry sortedEntry: sortedInitiative) {
//			System.out.println(sortedEntry.getCharacterName()+ ": " +  sortedEntry.getInitiativeTotal());
//		}
		
		return sortedInitiative;
	}
	
	private boolean unsortedHasEqualInitiativeButHigherDexterity(InitiativeOrderEntry unsortedCharEntry, InitiativeOrderEntry sortedCharEntry) throws SQLException {
		if (unsortedCharEntry.getInitiativeTotal() == sortedCharEntry.getInitiativeTotal()) {
			DndCharacter unsortedCharacter = dndDatabase.loadCharacter(unsortedCharEntry.getCharacterName());
			DndCharacter existingCharacter = dndDatabase.loadCharacter(sortedCharEntry.getCharacterName());
			if (unsortedCharacter.getInitiativeBonusOrDexScore() > existingCharacter.getInitiativeBonusOrDexScore() 
					|| (unsortedCharacter.getInitiativeBonusOrDexScore() == existingCharacter.getInitiativeBonusOrDexScore() 
					&& unsortedCharacter.getType().equals(unsortedCharacter.PLAYER)
					&& !existingCharacter.getType().equals(existingCharacter.PLAYER))
				) {
				return true;
			}
		}
		return false;
	}
	
	private Vector<DndCharacterMinimal> buildFullInitiativeFromInitiativeOrder(Vector<InitiativeOrderEntry> initiativeOrder) throws SQLException {
		Vector<DndCharacterMinimal> fullInitiative = new Vector<DndCharacterMinimal>();
		for (InitiativeOrderEntry entry: initiativeOrder) {
			DndCharacterMinimal characterMinimal = dndDatabase.loadCharacterMinimal(entry.getCharacterName());
			characterMinimal.setInitiativeTotal(entry.getInitiativeTotal());
			fullInitiative.add(characterMinimal); // already sorted
//			System.out.println("(buildFullInitiativeFromInitiativeOrder) initiative entry: " + entry.getCharacterName() + ", " + entry.getInitiativeTotal());
		}
		
		return fullInitiative;
	}

	public Vector<DndCharacterMinimal> editInitiativeOrder(Vector<InitiativeOrderEntry> initiative) throws SQLException {
		gameData.setInitiativeOrder(sortInitiative(initiative));
//		if (gameData.getCurrentCharacter().equals(gameData.NO_CHARACTER) || gameData.getNextCharacter().equals(gameData.NO_CHARACTER)) setInitialCharacters();
		return getInitiativeOrder();
	}
	
	public String dealDamage(String characterName, int damageDealt) throws Exception {
		DndCharacter damagedCharacter = getCharacter(characterName);
		damagedCharacter.setCurrentHealth(damagedCharacter.getCurrentHealth()-damageDealt);
		dndDatabase.updateCharacter(damagedCharacter);
		return characterName + " was dealt " + damageDealt + " damage.";
	}
	
	public String nextTurn() throws SQLException {
//		checkForEmptyInitiativeOrder();
		if (gameData.getInitiativeOrder().size() < 2) return "There are not enough characters in combat to take turns.";
		if (gameData.getCurrentCharacter().equals(gameData.NO_CHARACTER) || gameData.getNextCharacter().equals(gameData.NO_CHARACTER)) setInitialCharacters();
		gameData.doNextTurn();
		return gameData.updateGameTimeDetails();
	}

	public String getCurrentCharacter() throws SQLException {
//		checkForEmptyInitiativeOrder();
		if (gameData.getInitiativeOrder().size() < 2) return "There are not enough characters in combat to need a current character.";
		if (gameData.getCurrentCharacter().equals(gameData.NO_CHARACTER)) setInitialCharacters();
		return gameData.getCurrentCharacter();
	}

	public String getNextCharacter() throws SQLException {
//		checkForEmptyInitiativeOrder();
		if (gameData.getInitiativeOrder().size() < 2) return "There are not enough characters in combat to need a next character.";
		if (gameData.getNextCharacter().equals(gameData.NO_CHARACTER)) setInitialCharacters();
		return gameData.getNextCharacter();
	}
	
	// DM controls functions:
	
	public String getTime() {
		return gameData.getTotalTimeString();
	}
	
	public String resetTime() {
		return gameData.resetTime();
	}
	
	public String addTime(long time) {
		return gameData.addTime(time);
	}
	
	public String subtractTime(long time) {
		return gameData.subtractTime(time);
	}
	
	public String getEnvironment() {
		return gameData.getEnvironment();
	}
	
	public String changeEnvironment(String newEnvironment) {
		gameData.setEnvironment(newEnvironment);
		return "Environment changed to " + newEnvironment;
	}
	
	public String getLocation() {
		return gameData.getLocation();
	}
	
	public String changeLocation(String newLocation) {
		gameData.setLocation(newLocation);
		return "Location changed to " + newLocation;
	}
	
	public Month getCurrentMonth() {
		return gameData.getCurrentMonth();
	}
	
	public Vector<Month> getMonths() {
		return gameData.getMonths();
	}
	
	public Vector<Month> changeMonths( Vector<Month> months) {
		return gameData.setMonths(months);
	}
	
	// GROUP functions:
	
	public Vector<String> getAllGroupNames() throws SQLException {
		return dndDatabase.getAllGroupNames();
	}

	public String enterGroupIntoArena(String groupName) throws SQLException {
		Vector<String> groupData = dndDatabase.loadGroup(groupName);
		for (String characterName: groupData) {
			if (!isCharacterInArena(characterName)) {
				gameData.addSidelineCharacter(characterName);
			}
		}
		return "Group " + groupName + " has entered the arena.";
	}
	
	public Vector<String> getGroupMembers(String groupName) throws SQLException {
		return dndDatabase.loadGroup(groupName);
	}

	public String saveGroup(CharacterGroup group) throws SQLException {
		String returnStr = "created.";
		if (getAllGroupNames().contains(group.getName())) {
			deleteGroup(group.getName());
			returnStr = "edited.";
		}
		dndDatabase.saveGroup(group);
		return "Group " + group.getName() + " was " + returnStr;
	}

	public String deleteGroup(String groupName) throws SQLException {
		dndDatabase.deleteGroup(groupName);
		return "Group " + groupName + " was deleted.";
	}
	
	// TIMED EFFECT functions:

	public String addTimedEffect(TimedEffectIn effect) {
		if (gameData.getCurrentEffectNames().contains(effect.getName())) {
			return effect.getName() + " is already in effect.";
		}
		TimedEffect newTimedEffect = new TimedEffect();
		newTimedEffect.setName(effect.getName());
		newTimedEffect.setEffect(effect.getEffect());
		newTimedEffect.setTargets(effect.getTargets());
		newTimedEffect.setEndingInitiative(effect.getEndingInitiative());
		DndTime timeRemainingForEffect = effect.getTimeRemaining();
		timeRemainingForEffect.setDaysInAYear(getDaysInAYear());
		effect.setTimeRemaining(timeRemainingForEffect);
		newTimedEffect.setTimeRemaining(effect.getTimeRemaining().getTotalTimeInRounds());
		newTimedEffect.setEndingTime(newTimedEffect.getTimeRemaining()+gameData.getTime());
		gameData.addCurrentEffect(newTimedEffect);
		return "Timed Effect " + effect.getName() + " has begun.";
	}
	
	public Vector<TimedEffect> getCurrentEffects() {
		gameData.updateTimedEffects();
		return gameData.getCurrentEffects();
	}
	
	public String editTimedEffect(TimedEffectEdit effect) {
		if (!gameData.getCurrentEffectNames().contains(effect.getName())) {
			return effect.getName() + " does not exist.";
		}
		gameData.editTimedEffect(effect);
		return "Timed Effect " + effect.getName() + " edited.";
	}

	public String removeTimedEffect(String effectName) {
//		int count = 0;
		for (TimedEffect effect: gameData.getCurrentEffects()) {
			if (effect.getName().equals(effectName)) {
				if(gameData.removeEffect(effect)) {
					return "Timed Effect " + effectName + " has ended.";
				} else {
					return "Timed Effect " + effectName + " was not ended.";
				}
			}
//			count += 1;
		}
		return "Timed Effect " + effectName + " does not exist.";
	}
	
	// helper functions:
	
	public int getRandomNumber(int min, int max) {
//		for (int i=0;i<10000;i++) {
//			int temp = (int) ((Math.random() * (max+1 - min)) + min);
//			if (temp > 19 || temp < 2 ) System.out.print( Integer.toString(temp) + " ");
//		}
	    return (int) ((Math.random() * (max+1 - min)) + min);
	}
	
	private String removeCrFromName(String characterName) {
		String crString = "(CR";
		if (characterName.contains(crString) && characterName.contains(")")) {
			return characterName.substring(0, characterName.indexOf(crString)-1);
		} 
		return characterName;
	}

	public String updateTimeDetails(DndTimeDetails timeDetails) {
		return gameData.updateTimeDetails(timeDetails);
	}
	public DndTimeDetails getTimeDetails() {
		return gameData.getTimeDetails();
	}
	
	public long getDaysInAYear() {
		return gameData.getDaysInAYear();
	}
}
