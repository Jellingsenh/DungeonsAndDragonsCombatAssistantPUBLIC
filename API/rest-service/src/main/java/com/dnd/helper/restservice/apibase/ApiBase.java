package com.dnd.helper.restservice.apibase;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.dnd.helper.restservice.gamedata.InitiativeOrderEntry;
import com.dnd.helper.restservice.gamedata.CharacterGroup;
import com.dnd.helper.restservice.gamedata.DndCharacter;
import com.dnd.helper.restservice.gamedata.DndCharacterMinimal;
import com.dnd.helper.restservice.gamedata.GameData;
import com.dnd.helper.restservice.gamedata.GameManager;
import com.dnd.helper.restservice.gamedata.Month;
import com.dnd.helper.restservice.gamedata.DndTime;
import com.dnd.helper.restservice.gamedata.DndTimeDetails;
import com.dnd.helper.restservice.gamedata.TimedEffect;
import com.dnd.helper.restservice.gamedata.TimedEffectEdit;
import com.dnd.helper.restservice.gamedata.TimedEffectIn;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin(origins = "*")
public class ApiBase {
	private GameManager gameManager;
	
	ApiBase(){
		try {
			gameManager = new GameManager();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	// API endpoints
	
	@GetMapping(value = "/")
	public String getStartPage() {
		return "Welcome to the D&D Combat Assistant!";
	}
	
	// GAME endpoints
	
	@GetMapping(value = "/getAllGameNames/{sortBy}")
	public ResponseEntity<Vector<String>> getAllGameNames(@PathVariable String sortBy) {
		try {
			switch(sortBy) {
			  case "a":
				  return new ResponseEntity<Vector<String>> (gameManager.getAllGameNames(), HttpStatus.OK); // 200
			  case "c":
				  return new ResponseEntity<Vector<String>> (gameManager.getAllGameNamesChronological(), HttpStatus.OK); // 200
			  case "ra":
				  return new ResponseEntity<Vector<String>> (gameManager.getAllGameNamesReverseAlphabetical(), HttpStatus.OK); // 200
			  case "rc":
				  return new ResponseEntity<Vector<String>> (gameManager.getAllGameNamesReverseChronological(), HttpStatus.OK); // 200
			  default:
				  break;
			}
			return new ResponseEntity<Vector<String>> (gameManager.getAllGameNames(), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<Vector<String>> (createErrorVector(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@GetMapping(value = "/getCurrentGame")
	public ResponseEntity<GameData> getCurrentGame() {
		try {
			return new ResponseEntity<GameData> (gameManager.getCurrentGame(), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<GameData> (createErrorData(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@GetMapping(value = "/loadGame/{gameName}")
	public ResponseEntity<String> loadGame(@PathVariable String gameName) {
		try {
			return new ResponseEntity<String> (gameManager.loadGame(gameName), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@PostMapping(value = "/saveGame")
	public ResponseEntity<String> saveGame(@RequestBody String gameName) {
		try {
			return new ResponseEntity<String> (gameManager.saveGame(gameName.toUpperCase()), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@GetMapping(value = "/deleteGame/{gameName}")
	public ResponseEntity<String> deleteGame(@PathVariable String gameName) {
		try {
			return new ResponseEntity<String> (gameManager.deleteGame(gameName), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	// CHARACTER endpoints
	
	@GetMapping(value = "/getAllCharacterNames/{sortBy}/{searchBy}")
	public ResponseEntity<Vector<String>> getAllCharacterNames(@PathVariable String sortBy, @PathVariable String searchBy) {
		if (searchBy.equals("noSearchHere")) {
			searchBy = "";
		}
		try {
			switch(sortBy) {
			  case "a":
				  return new ResponseEntity<Vector<String>> (gameManager.getAllCharacterNames(true, searchBy), HttpStatus.OK); // 200
			  case "c":
				  return new ResponseEntity<Vector<String>> (gameManager.getAllCharacterNamesChronological(searchBy), HttpStatus.OK); // 200
			  case "ra":
				  return new ResponseEntity<Vector<String>> (gameManager.getAllCharacterNamesReverse(searchBy), HttpStatus.OK); // 200
			  case "rc":
				  return new ResponseEntity<Vector<String>> (gameManager.getAllCharacterNamesReverseChronological(searchBy), HttpStatus.OK); // 200
			  case "l":
				  return new ResponseEntity<Vector<String>> (gameManager.getAllCharacterNamesByLevel(searchBy), HttpStatus.OK); // 200
			  case "rl":
				  return new ResponseEntity<Vector<String>> (gameManager.getAllCharacterNamesByReverseLevel(searchBy), HttpStatus.OK); // 200
			  case "t":
				  return new ResponseEntity<Vector<String>> (gameManager.getAllCharacterNamesByType(searchBy), HttpStatus.OK); // 200
			  case "rt":
				  return new ResponseEntity<Vector<String>> (gameManager.getAllCharacterNamesByReverseType(searchBy), HttpStatus.OK); // 200
			  default:
				  break;
			}
			return new ResponseEntity<Vector<String>> (gameManager.getAllCharacterNames(true, searchBy), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Vector<String>> (createErrorVector(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@GetMapping(value = "/getCharacter/{characterName}")
	public ResponseEntity<DndCharacter> getCharacter(@PathVariable String characterName) {
		try {
			return new ResponseEntity<DndCharacter> (gameManager.getCharacter(characterName), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<DndCharacter> (createErrorCharacter(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@GetMapping(value = "/enterCharacterIntoArena/{characterName}")
	public ResponseEntity<String> enterCharacterIntoArena(@PathVariable String characterName) {
		try {
			return new ResponseEntity<String> (gameManager.enterCharacterIntoArena(characterName), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@PostMapping(value = "/addCharacter")
	public ResponseEntity<String> addCharacter(@RequestBody DndCharacter character) {
		try {
			return new ResponseEntity<String> (gameManager.addCharacter(character), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@GetMapping(value = "/deleteCharacter/{characterName}")
	public ResponseEntity<String> deleteCharacter(@PathVariable String characterName) {
		try {
			return new ResponseEntity<String> (gameManager.deleteCharacter(characterName), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@GetMapping(value = "/getAllArenaCharacterNames")
	public ResponseEntity<Vector<String>> getAllArenaCharacterNames() {
		try {
			return new ResponseEntity<Vector<String>> (gameManager.getAllArenaCharacterNames(), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Vector<String>> (createErrorVector(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@GetMapping(value = "/getCombatCharacters")
	public ResponseEntity<Vector<String>> getCombatCharacters() {
		try {
			return new ResponseEntity<Vector<String>> (gameManager.getCombatCharacters(), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Vector<String>> (createErrorVector(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@GetMapping(value = "/getSidelineCharacters")
	public ResponseEntity<Vector<String>> getSidelineCharacters() {
		try {
			return new ResponseEntity<Vector<String>> (gameManager.getSidelineCharacters(), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Vector<String>> (createErrorVector(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@PutMapping(value = "/editCharacter/{oldCharacterName}")
	public ResponseEntity<String> editCharacter(@RequestBody DndCharacter character, @PathVariable String oldCharacterName) {
		try {
			return new ResponseEntity<String> (gameManager.editCharacter(character, oldCharacterName), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@GetMapping(value = "/removeCharacterFromArena/{characterName}")
	public ResponseEntity<String> removeCharacterFromArena(@PathVariable String characterName) {
		try {
			return new ResponseEntity<String> (gameManager.removeCharacterFromArena(characterName), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@GetMapping(value = "/moveCharacterToCombat/{characterName}")
	public ResponseEntity<String> moveCharacterToCombat(@PathVariable String characterName) {
		try {
			return new ResponseEntity<String> (gameManager.moveCharacterToCombat(characterName), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@GetMapping(value = "/moveCharacterToSideline/{characterName}")
	public ResponseEntity<String> moveCharacterToSideline(@PathVariable String characterName) {
		try {
			return new ResponseEntity<String> (gameManager.moveCharacterToSideline(characterName), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@PostMapping(value = "/duplicateCharacter/{characterName}")
	public ResponseEntity<String> duplicateCharacter(@PathVariable String characterName, @RequestBody int numberOfDuplicates) {
		try {
			return new ResponseEntity<String> (gameManager.duplicateCharacter(characterName, numberOfDuplicates), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@GetMapping(value = "/removeAllDuplicates")
	public ResponseEntity<String> removeAllDuplicates() {
		try {
			return new ResponseEntity<String> (gameManager.removeAllDuplicates(), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@GetMapping(value = "/getCombatPCs")
	public ResponseEntity<Vector<String>> getCombatPCs() {
		try {
			return new ResponseEntity<Vector<String>> (gameManager.getCombatPCs(), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Vector<String>> (createErrorVector(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@GetMapping(value = "/isCharacterNpc/{characterName}")
	public ResponseEntity<String> isCharacterNpc(@PathVariable String characterName) {
		try {
			return new ResponseEntity<String> (new ObjectMapper().writeValueAsString(gameManager.isCharacterNpc(characterName)), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	// INITIATIVE endpoints:
	
	@PostMapping(value = "/rollInitiative")
	public ResponseEntity<Vector<DndCharacterMinimal>>  rollInitiative(@RequestBody Vector<InitiativeOrderEntry> pcInitiative) {
		try {
			return new ResponseEntity<Vector<DndCharacterMinimal>> (gameManager.rollInitiative(pcInitiative), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Vector<DndCharacterMinimal>> (createErrorInitiativeVector(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@GetMapping(value = "/getInitiativeOrder")
	public ResponseEntity<Vector<DndCharacterMinimal>>  getInitiativeOrder() {
		try {
			return new ResponseEntity<Vector<DndCharacterMinimal>> (gameManager.getInitiativeOrder(), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Vector<DndCharacterMinimal>> (createErrorInitiativeVector(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@GetMapping(value = "/getCharacterInitiativeTotal/{characterName}")
	public ResponseEntity<String>  getCharacterInitiativeTotal(@PathVariable String characterName) {
		try {
			return new ResponseEntity<String> (gameManager.getCharacterInitiativeTotal(characterName), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
//	@GetMapping(value = "/getCurrentFirstInitiativeOrder")
//	public ResponseEntity<Vector<DndCharacterMinimal>>  getCurrentFirstInitiativeOrder() {
//		try {
//			return new ResponseEntity<Vector<DndCharacterMinimal>> (gameManager.getCurrentFirstInitiativeOrder(), HttpStatus.OK); // 200
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<Vector<DndCharacterMinimal>> (createErrorInitiativeVector(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR); // 500
//		}
//	}
	
	@PostMapping(value = "/editInitiativeOrder")
	public ResponseEntity<Vector<DndCharacterMinimal>>  editInitiativeOrder(@RequestBody Vector<InitiativeOrderEntry> initiative) {
		try {
			return new ResponseEntity<Vector<DndCharacterMinimal>> (gameManager.editInitiativeOrder(initiative), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Vector<DndCharacterMinimal>> (createErrorInitiativeVector(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@PostMapping(value = "/dealDamage/{characterName}")
	public ResponseEntity<String> dealDamage(@PathVariable String characterName, @RequestBody int damageDealt) {
		try {
			return new ResponseEntity<String> (gameManager.dealDamage(characterName, damageDealt), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@GetMapping(value = "/nextTurn")
	public ResponseEntity<String>  nextTurn() {
		try {
			return new ResponseEntity<String> (new ObjectMapper().writeValueAsString(gameManager.nextTurn()), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@GetMapping(value = "/getCurrentCharacter")
	public ResponseEntity<String> getCurrentCharacter() {
		try {
			return new ResponseEntity<String> (gameManager.getCurrentCharacter(), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@GetMapping(value = "/getNextCharacter")
	public ResponseEntity<String>  getNextCharacter() {
		try {
			return new ResponseEntity<String> (gameManager.getNextCharacter(), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	// Time endpoints:
	
	@GetMapping(value = "/getTime")
	public ResponseEntity<String>  getTime() {
		try {
			return new ResponseEntity<String> (new ObjectMapper().writeValueAsString(gameManager.getTime()), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
//	@PostMapping(value = "/setTime")
//	public ResponseEntity<String>  setTime(@RequestBody DndTime time) {
//		try {
//			gameManager.resetTime();
//			return new ResponseEntity<String> (new ObjectMapper().writeValueAsString(gameManager.addTime(time.getTotalTimeInRounds())), HttpStatus.OK); // 200
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
//		}
//	}
	
	@GetMapping(value = "/resetTime")
	public ResponseEntity<String>  resetTime() {
		try {
			return new ResponseEntity<String> (new ObjectMapper().writeValueAsString(gameManager.resetTime()), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@PostMapping(value = "/addTime")
	public ResponseEntity<String>  addTime(@RequestBody DndTime time) {
		try {
			time.setDaysInAYear(gameManager.getDaysInAYear());
			return new ResponseEntity<String> (new ObjectMapper().writeValueAsString(gameManager.addTime(time.getTotalTimeInRounds())), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@PostMapping(value = "/subtractTime")
	public ResponseEntity<String>  subtractTime(@RequestBody DndTime time) {
		try {
			time.setDaysInAYear(gameManager.getDaysInAYear());
			return new ResponseEntity<String> (new ObjectMapper().writeValueAsString(gameManager.subtractTime(time.getTotalTimeInRounds())), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@PostMapping(value = "/setTimeDetails")
	public ResponseEntity<String>  setTimeDetails(@RequestBody DndTimeDetails timeDetails) {
		try {
			return new ResponseEntity<String> (new ObjectMapper().writeValueAsString(gameManager.updateTimeDetails(timeDetails)), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@GetMapping(value = "/getTimeDetails")
	public ResponseEntity<DndTimeDetails>  getTimeDetails() {
		try {
			return new ResponseEntity<DndTimeDetails> (gameManager.getTimeDetails(), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<DndTimeDetails> (new DndTimeDetails(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@GetMapping(value = "/getEnvironment")
	public ResponseEntity<String>  getEnvironment() {
		try {
			return new ResponseEntity<String> (gameManager.getEnvironment(), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@PostMapping(value = "/changeEnvironment")
	public ResponseEntity<String>  changeEnvironment(@RequestBody String newEnvironment) {
		try {
			return new ResponseEntity<String> (gameManager.changeEnvironment(newEnvironment), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@GetMapping(value = "/getLocation")
	public ResponseEntity<String>  getLocation() {
		try {
			return new ResponseEntity<String> (gameManager.getLocation(), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@PostMapping(value = "/changeLocation")
	public ResponseEntity<String>  changeLocation(@RequestBody String newLocation) {
		try {
			return new ResponseEntity<String> (gameManager.changeLocation(newLocation), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@GetMapping(value = "/getCurrentMonth")
	public ResponseEntity<Month>  getCurrentMonth() {
		try {
			return new ResponseEntity<Month> (gameManager.getCurrentMonth(), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Month> (createErrorMonth(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
//	@GetMapping(value = "/getMonths")
//	public ResponseEntity<Vector<Month>> getMonths() {
//		try {
//			return new ResponseEntity<Vector<Month>> (gameManager.getMonths(), HttpStatus.OK); // 200
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<Vector<Month>> (createErrorMonthVector(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR); // 500
//		}	
//	}
	
//	@PostMapping(value = "/changeMonths")
//	public ResponseEntity<Vector<Month>> changeMonths(@RequestBody Vector<Month> months) {
//		try {
//			return new ResponseEntity<Vector<Month>> (gameManager.changeMonths(months), HttpStatus.OK); // 200
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<Vector<Month>> (createErrorMonthVector(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR); // 500
//		}
//	}
	
	// GROUP endpoints:
	
	@GetMapping(value = "/getAllGroupNames")
	public ResponseEntity<Vector<String>> getAllGroupNames() {
		try {
			return new ResponseEntity<Vector<String>> (gameManager.getAllGroupNames(), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Vector<String>> (createErrorVector(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@GetMapping(value = "/enterGroupIntoArena/{groupName}")
	public ResponseEntity<String> enterGroupIntoArena(@PathVariable String groupName) {
		try {
			return new ResponseEntity<String> (gameManager.enterGroupIntoArena(groupName), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@GetMapping(value = "/getGroupMembers/{groupName}")
	public ResponseEntity<Vector<String>> getGroupMembers(@PathVariable String groupName) {
		try {
			return new ResponseEntity<Vector<String>> (gameManager.getGroupMembers(groupName), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Vector<String>> (createErrorVector(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@PostMapping(value = "/saveGroup")
	public ResponseEntity<String> saveGroup(@RequestBody CharacterGroup group) {
		try {
			return new ResponseEntity<String> (gameManager.saveGroup(group), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@GetMapping(value = "/deleteGroup/{groupName}")
	public ResponseEntity<String> deleteGroup(@PathVariable String groupName) {
		try {
			return new ResponseEntity<String> (gameManager.deleteGroup(groupName), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	// TIMED EFFECT endpoints:
	
	@PostMapping(value = "/addTimedEffect")
	public ResponseEntity<String> addTimedEffect(@RequestBody TimedEffectIn effect) {
		try {
			return new ResponseEntity<String> (gameManager.addTimedEffect(effect), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@GetMapping(value = "/getTimedEffects")
	public ResponseEntity<Vector<TimedEffect>> getTimedEffects() {
		try {
			return new ResponseEntity<Vector<TimedEffect>> (gameManager.getCurrentEffects(), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Vector<TimedEffect>> (createErrorEffectVector(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	@PostMapping(value = "/editTimedEffect")
	public ResponseEntity<String> editTimedEffect(@RequestBody TimedEffectEdit effect) {
		try {
			return new ResponseEntity<String> (gameManager.editTimedEffect(effect), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}

	@GetMapping(value = "/removeTimedEffect/{effectName}")
	public ResponseEntity<String> removeTimedEffect(@PathVariable String effectName) {
		try {
			return new ResponseEntity<String> (gameManager.removeTimedEffect(effectName), HttpStatus.OK); // 200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	
	// helper functions:
	
	private GameData createErrorData(String errorMessage) {
		return new GameData(errorMessage);
	}
	private Vector<String> createErrorVector(String errorMessage) {
		Vector<String> errorVector = new Vector<String>();
		errorVector.add(errorMessage);
		return errorVector;
	}
	private DndCharacter createErrorCharacter(String errorMessage) {
		return new DndCharacter(errorMessage);
	}
	private Month createErrorMonth(String errorMessage) {
		return new Month(errorMessage);
	}
	private Vector<Month> createErrorMonthVector(String errorMessage) {
		Vector<Month> errorMonthVector = new Vector<Month>();
		errorMonthVector.add(createErrorMonth(errorMessage));
		return errorMonthVector;
	}
	private Vector<DndCharacterMinimal> createErrorInitiativeVector(String errorMessage) {
		Vector<DndCharacterMinimal> errorInitiativeVector = new Vector<DndCharacterMinimal>();
		errorInitiativeVector.add(new DndCharacterMinimal(errorMessage));
		return errorInitiativeVector;
	}
	private Vector<TimedEffect> createErrorEffectVector(String errorMessage) {
		Vector<TimedEffect> errorInitiativeVector = new Vector<TimedEffect>();
		errorInitiativeVector.add(new TimedEffect(errorMessage));
		return errorInitiativeVector;
	}
}
