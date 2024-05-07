package com.dnd.helper.restservice.sqlfunctions;

//import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.Scanner;
import java.util.Vector;

import com.dnd.helper.restservice.gamedata.CharacterGroup;
import com.dnd.helper.restservice.gamedata.DndCharacter;
import com.dnd.helper.restservice.gamedata.DndCharacterMinimal;
import com.dnd.helper.restservice.gamedata.DndTimeDetails;
import com.dnd.helper.restservice.gamedata.GameData;
import com.dnd.helper.restservice.gamedata.InitiativeOrderEntry;
import com.dnd.helper.restservice.gamedata.Month;
import com.dnd.helper.restservice.gamedata.TimedEffect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class DndDatabase {
	private String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
	final String USER = "postgres";
	private String PASS = "password";
	private Connection dbConnection;
	
	public DndDatabase() throws ClassNotFoundException, SQLException, IOException {
		getPasswordAndPort();
		initializeDatabase();
//		System.out.println("initialized database");
	}

	private void getPasswordAndPort() throws IOException {
		String newDatabasePort = "5432";
		String newDatabasePassword = "password";
		
//		Scanner myReader = new Scanner(new File("properties.txt")); // commented this area for maven build (properties.txt was out of scope, not in src folder)
//		while (myReader.hasNextLine()) {
//			String data = myReader.nextLine();
//			if (data.contains("databasePort")) {
//				newDatabasePort = data.substring(data.indexOf("=")+1);
//			} else if (data.contains("databasePassword")) {
//				newDatabasePassword = data.substring(data.indexOf("=")+1);
//			}
//		}
//	    myReader.close();
	    		
		if (newDatabasePort != null) this.DB_URL = "jdbc:postgresql://localhost:"+newDatabasePort+"/postgres";
		if (newDatabasePassword != null) this.PASS = newDatabasePassword;
	}

	void initializeDatabase() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
//		System.out.println("initializing connection");
//		System.out.println(DB_URL);
//		System.out.println(USER);
//		System.out.println(PASS);
		dbConnection = DriverManager.getConnection(DB_URL, USER, PASS); // josh here
//		System.out.println("initialized connection");

				
		try {
			dbConnection.prepareStatement("CREATE SCHEMA savedata;").executeUpdate();
			System.out.println("SaveData schema created successfully.");
		} catch (SQLException e) {
//			System.out.println("SaveData schema already created, proceeding...");
		}
		
		try {
			dbConnection.prepareStatement("CREATE TABLE savedata.gamesaves (\r\n"
					+ "    name varchar,\r\n"
					+ "    realtime varchar,\r\n"
					+ "    time varchar,\r\n"
					+ "    environment varchar,\r\n"
					+ "    location varchar,\r\n"
					+ "    currentturn varchar,\r\n"
					+ "    nextturn varchar,\r\n"
					+ "    yearnotation varchar\r\n"
					+ ");").executeUpdate();
			dbConnection.prepareStatement("CREATE UNIQUE INDEX index_gameName ON savedata.gamesaves (name);").executeUpdate();
			System.out.println("GameSaves table created successfully.");
		} catch (SQLException e) {
//			System.out.println("GameSaves table already created, proceeding...");
		}
		
		try {
			dbConnection.prepareStatement("CREATE TABLE savedata.charactersaves (\r\n"
					+ "    name varchar,\r\n"
					+ "    race varchar,\r\n"
					+ "    appearance varchar,\r\n"
					+ "    size varchar,\r\n"
					+ "    speeds varchar,\r\n"
					+ "    maxhealth varchar,\r\n"
					+ "    hitdice varchar,\r\n"
					+ "    currenthealth varchar,\r\n"
					+ "    armorclass varchar,\r\n"
					+ "    flatfootedacorstrsave varchar,\r\n"
					+ "    touchacorintsave varchar,\r\n"
					+ "    fortsaveorconsave varchar,\r\n"
					+ "    refsaveordexsave varchar,\r\n"
					+ "    willsaveorwissave varchar,\r\n"
					+ "    grappleorchasave varchar,\r\n"
					+ "    initiativebonusordexscore varchar,\r\n"
					+ "    mainattacksandspells varchar,\r\n"
					+ "    resistances varchar,\r\n"
					+ "    vulnerabilities varchar,\r\n"
					+ "    reactionsorlegendaryactions varchar,\r\n"
					+ "    otherattacksandspells varchar,\r\n"
					+ "    morespellsandabilities varchar,\r\n"
					+ "    additionalspellsandabilities varchar,\r\n"
					+ "    featsandextraabilities varchar,\r\n"
					+ "    skillsandproficiencies varchar,\r\n"
					+ "    abilityscores varchar,\r\n"
					+ "    items varchar,\r\n"
					+ "    other varchar,\r\n"
					+ "    realtime varchar,\r\n"
					+ "    version varchar,\r\n"
					+ "    type varchar,\r\n"
					+ "    level varchar\r\n"
					+ ");").executeUpdate();
			dbConnection.prepareStatement("CREATE UNIQUE INDEX index_characterName ON savedata.charactersaves (name);").executeUpdate();
			System.out.println("CharacterSaves table created successfully.");
		} catch (SQLException e) {
//			System.out.println("CharacterSaves table already created, proceeding...");
		}
		
		try {
			dbConnection.prepareStatement("CREATE TABLE savedata.groupsaves (\r\n"
					+ "    groupname varchar,\r\n"
					+ "    realtime varchar\r\n"
					+ ");").executeUpdate();
			dbConnection.prepareStatement("CREATE UNIQUE INDEX index_groupName ON savedata.groupsaves (groupname);").executeUpdate();
			System.out.println("GroupSaves table created successfully.");
		} catch (SQLException e) {
//			System.out.println("GroupSaves table already created, proceeding...");
		}
				
		try {
			dbConnection.prepareStatement("CREATE SCHEMA months;").executeUpdate();
			System.out.println("Months schema created successfully.");
		} catch (SQLException e) {
//			System.out.println("Months schema already created, proceeding...");
		}
		
		try {
			dbConnection.prepareStatement("CREATE SCHEMA initiativeorder;").executeUpdate();
			System.out.println("InitiativeOrder schema created successfully.");
		} catch (SQLException e) {
//			System.out.println("InitiativeOrder schema already created, proceeding...");
		}
		
		try {
			dbConnection.prepareStatement("CREATE SCHEMA timedeffects;").executeUpdate();
			System.out.println("TimedEffects schema created successfully.");
		} catch (SQLException e) {
//			System.out.println("TimedEffects schema already created, proceeding...");
		}
		
		try {
			dbConnection.prepareStatement("CREATE SCHEMA sidelinecharacters;").executeUpdate();
			System.out.println("SidelineCharacters schema created successfully.");
		} catch (SQLException e) {
//			System.out.println("SidelineCharacters schema already created, proceeding...");
		}
		
		try {
			dbConnection.prepareStatement("CREATE SCHEMA combatcharacters;").executeUpdate();
			System.out.println("CombatCharacters schema created successfully.");
		} catch (SQLException e) {
//			System.out.println("CombatCharacters schema already created, proceeding...");
		}
		
		try {
			dbConnection.prepareStatement("CREATE SCHEMA charactergroups;").executeUpdate();
			System.out.println("CharacterGroups schema created successfully.");
		} catch (SQLException e) {
//			System.out.println("CharacterGroups schema already created, proceeding...");
		}
		
		createCrTemplates();
	}
	
	private void createCrTemplates() {
		DndCharacter crTemplate = new DndCharacter();
		
		// 3.5 CR 1/2: Badger
		crTemplate.setName("CR .5 template 3.5 (Badger)");
		crTemplate.setLevel("1/2");
		crTemplate.setRace("Badger");
		crTemplate.setSize("S");
		crTemplate.setAppearance("A 3 ft badger");
		crTemplate.setSpeeds("30 ft, Burrow 10 ft");
		crTemplate.setMaximumHealth(6);
		crTemplate.setHitDice("1d8");
		crTemplate.setCurrentHealth(6);
		crTemplate.setArmorClass(15);
		crTemplate.setFlatFootedAcOrStrSave(12);
		crTemplate.setTouchAcOrIntSave(14);
		crTemplate.setFortSaveOrConSave(4);
		crTemplate.setRefSaveOrDexSave(5);
		crTemplate.setWillSaveOrWisSave(1);
		crTemplate.setGrappleOrChaSave(-5);
		crTemplate.setInitiativeBonusOrDexScore(3);
		crTemplate.setMainAttacksAndSpells("2 Claws +4 (1 dmg) and Bite -1 (1d3-1). Rage 1/day");
		crTemplate.setResistances("None");
		crTemplate.setVulnerabilities("None");
		crTemplate.setReactionsOrLegendaryActions("None");
		crTemplate.setOtherAttacksAndSpells("Rage (+4 Str, +4 Con, & -2 AC for 10 rounds) 1/day");
		crTemplate.setMoreSpellsAndAbilities("None");
		crTemplate.setAdditionalSpellsAndAbilities("Low-light vision, Scent");
		crTemplate.setFeatsAndExtraAbilities("Agile, Track, Weapon Finesse");
		crTemplate.setSkillsAndProficiencies("Balance +5, Escape Artist +9, Listen +3, Spot +3");
		crTemplate.setAbilityScores("Str 8, Dex 17, Con 15, Int 2, Wis 12, Cha 6");
		crTemplate.setItems("Badger pelt & badger meat (requires a survival check)");
		crTemplate.setOther("Fierce and fearless");
		try {
			saveCharacter(crTemplate);
		} catch (SQLException e) {
			// System.out.println(crTemplate.getName() + " already created, proceeding...");
		}
		
		// 3.5 CR 1: Wolf
		crTemplate.setName("CR 1 template 3.5 (Wolf)");
		crTemplate.setLevel("1");
		crTemplate.setRace("Wolf");
		crTemplate.setSize("M");
		crTemplate.setAppearance("A 5 ft wolf");
		crTemplate.setSpeeds("50 ft");
		crTemplate.setMaximumHealth(13);
		crTemplate.setHitDice("2d8");
		crTemplate.setCurrentHealth(13);
		crTemplate.setArmorClass(14);
		crTemplate.setTouchAcOrIntSave(12);
		crTemplate.setFortSaveOrConSave(5);
		crTemplate.setGrappleOrChaSave(2);
		crTemplate.setInitiativeBonusOrDexScore(2);
		crTemplate.setMainAttacksAndSpells("Bite +3 (1d6+1) & Trip");
		crTemplate.setOtherAttacksAndSpells("Trip (free trip check w/ +1 after biting)");
		crTemplate.setFeatsAndExtraAbilities("Track, Weapon Focus (bite)");
		crTemplate.setSkillsAndProficiencies("Hide +2, Listen +3, Move Silently +3, Spot +3, Survival +1 (+5 scent)");
		crTemplate.setAbilityScores("Str 13, Dex 15, Con 15, Int 2, Wis 12, Cha 6");
		crTemplate.setItems("Wolf pelt & wolf meat (requires a survival check)");
		crTemplate.setOther("Roam in packs");
		try {
			saveCharacter(crTemplate);
		} catch (SQLException e) {
			// System.out.println(crTemplate.getName() + " already created, proceeding...");
		}
		
		// 3.5 CR 2: Ape
		crTemplate.setName("CR 2 template 3.5 (Ape)");
		crTemplate.setLevel("2");
		crTemplate.setRace("Ape");
		crTemplate.setSize("L");
		crTemplate.setAppearance("An 8 ft ape");
		crTemplate.setSpeeds("30 ft, Climb 30 ft");
		crTemplate.setMaximumHealth(29);
		crTemplate.setHitDice("4d8");
		crTemplate.setCurrentHealth(29);
		crTemplate.setTouchAcOrIntSave(11);
		crTemplate.setFortSaveOrConSave(6);
		crTemplate.setRefSaveOrDexSave(6);
		crTemplate.setWillSaveOrWisSave(2);
		crTemplate.setGrappleOrChaSave(12);
		crTemplate.setMainAttacksAndSpells("10 ft reach, 2 Claws +7 (1d6+5) and Bite +2 (1d6+2)");
		crTemplate.setOtherAttacksAndSpells("None");
		crTemplate.setFeatsAndExtraAbilities("Alertness, Toughness");
		crTemplate.setSkillsAndProficiencies("Climb +14, Listen +6, Spot +6");
		crTemplate.setAbilityScores("Str 21, Dex 15, Con 14, Int 2, Wis 12, Cha 7");
		crTemplate.setItems("Ape pelt & ape meat (requires a survival check)");
		crTemplate.setOther("If it doesn't have a tail, it's an ape");
		try {
			saveCharacter(crTemplate);
		} catch (SQLException e) {
			// System.out.println(crTemplate.getName() + " already created, proceeding...");
		}
		
		// 3.5 CR 3: Lion
		crTemplate.setName("CR 3 template 3.5 (Lion)");
		crTemplate.setLevel("3");
		crTemplate.setRace("Lion");
		crTemplate.setSize("L");
		crTemplate.setAppearance("A 10 ft lion");
		crTemplate.setSpeeds("40 ft");
		crTemplate.setMaximumHealth(32);
		crTemplate.setHitDice("5d8");
		crTemplate.setCurrentHealth(32);
		crTemplate.setArmorClass(15);
		crTemplate.setTouchAcOrIntSave(12);
		crTemplate.setRefSaveOrDexSave(7);
		crTemplate.setInitiativeBonusOrDexScore(3);
		crTemplate.setMainAttacksAndSpells("2 Claws +7 (1d4+5) and Bite +2 (1d8+2). with Pounce: 2 Rakes +7 (1d4+2)");
		crTemplate.setOtherAttacksAndSpells("Pounce: full attack on a charge, Improved grab (bite)");
		crTemplate.setFeatsAndExtraAbilities("Alertness, Run");
		crTemplate.setSkillsAndProficiencies("Balance +7, Hide +3 (+11 grass), Listen +5, Move Silently +11, Spot +5");
		crTemplate.setAbilityScores("Str 21, Dex 17, Con 15, Int 2, Wis 12, Cha 6");
		crTemplate.setItems("Lion pelt & lion meat (requires a survival check)");
		crTemplate.setOther("King of the jungle");
		try {
			saveCharacter(crTemplate);
		} catch (SQLException e) {
			// System.out.println(crTemplate.getName() + " already created, proceeding...");
		}
		
		// 3.5 CR 4: Polar Bear
		crTemplate.setName("CR 4 template 3.5 (Polar Bear)");
		crTemplate.setLevel("4");
		crTemplate.setRace("Polar Bear");
		crTemplate.setSize("L");
		crTemplate.setAppearance("A 12 ft bear");
		crTemplate.setSpeeds("40 ft, swim 30 ft");
		crTemplate.setMaximumHealth(68);
		crTemplate.setHitDice("8d8");
		crTemplate.setCurrentHealth(68);
		crTemplate.setFlatFootedAcOrStrSave(14);
		crTemplate.setTouchAcOrIntSave(10);
		crTemplate.setFortSaveOrConSave(10);
		crTemplate.setWillSaveOrWisSave(3);
		crTemplate.setGrappleOrChaSave(18);
		crTemplate.setInitiativeBonusOrDexScore(1);
		crTemplate.setMainAttacksAndSpells("2 Claws +13 (1d8+8) & Bite +8 (2d6+4)");
		crTemplate.setOtherAttacksAndSpells("Improved grab (claw or bite)");
		crTemplate.setFeatsAndExtraAbilities("Endurance, Run, Track");
		crTemplate.setSkillsAndProficiencies("Hide -2 (+10 snow), Listen +5, Spot +7, Swim +16");
		crTemplate.setAbilityScores("Str 27, Dex 13, Con 19, Int 2, Wis 12, Cha 6");
		crTemplate.setItems("Polar bear pelt & bear meat (requires a survival check)");
		crTemplate.setOther("Icy fresh");
		try {
			saveCharacter(crTemplate);
		} catch (SQLException e) {
			// System.out.println(crTemplate.getName() + " already created, proceeding...");
		}
		
		// 3.5 CR 5: Mountain Troll
		crTemplate.setName("CR 5 template 3.5 (Mountain Troll)");
		crTemplate.setLevel("5");
		crTemplate.setRace("Mountain Troll (giant)");
		crTemplate.setSize("L");
		crTemplate.setAppearance("A 14 ft troll");
		crTemplate.setSpeeds("30 ft");
		crTemplate.setMaximumHealth(63);
		crTemplate.setHitDice("6d8");
		crTemplate.setCurrentHealth(63);
		crTemplate.setArmorClass(16);
		crTemplate.setTouchAcOrIntSave(11);
		crTemplate.setFortSaveOrConSave(11);
		crTemplate.setRefSaveOrDexSave(4);
		crTemplate.setGrappleOrChaSave(14);
		crTemplate.setInitiativeBonusOrDexScore(2);
		crTemplate.setMainAttacksAndSpells("10 ft reach, 2 Claws +9 (1d6+6) and Bite +4 (1d6+3), also Rend 2d6+9 (automatic dmg if both claws hit)");
		crTemplate.setVulnerabilities("Sunlight will turn a troll to stone in its natural form");
		crTemplate.setReactionsOrLegendaryActions("Regeneration 5");
		crTemplate.setOtherAttacksAndSpells("Rock Throwing: small rocks, 15 ft range, +6 ranged, 2d6+7 dmg");
		crTemplate.setMoreSpellsAndAbilities("Shifting form: mountain troll tails allow mountain trolls to shift into natural, unmoving objects only (stone, tree, etc), as a move action, an unlimited number of times");
		crTemplate.setAdditionalSpellsAndAbilities("Darkvision 90 ft, Low-light vision, Scent");
		crTemplate.setFeatsAndExtraAbilities("Alertness, Iron Will, Track");
		crTemplate.setSkillsAndProficiencies("Listen +5, Spot +6");
		crTemplate.setAbilityScores("Str 23, Dex 14, Con 23, Int 6, Wis 9, Cha 6");
		crTemplate.setItems("Mountain troll tail (preserve w/ survival DC 20): UMD DC 20 (no check if you can cast level 1 arcane spells) to disguise as a natural, unmoving object (stone, tree, etc) for up to 8 hours, 1/day. Must be preserved every week.");
		crTemplate.setOther("Mountain trolls speak giant. Mountain troll tails are bunny/moss tails. Tails are always visible, even when magically disguised or invisible.");
		try {
			saveCharacter(crTemplate);
		} catch (SQLException e) {
			// System.out.println(crTemplate.getName() + " already created, proceeding...");
		}
		
		// 3.5 CR 6: Megaraptor
		crTemplate.setName("CR 6 template 3.5 (Megaraptor)");
		crTemplate.setLevel("6");
		crTemplate.setRace("Megaraptor (beast)");
		crTemplate.setSize("L");
		crTemplate.setAppearance("A 14 ft raptor");
		crTemplate.setSpeeds("60 ft");
		crTemplate.setMaximumHealth(79);
		crTemplate.setHitDice("8d8");
		crTemplate.setCurrentHealth(79);
		crTemplate.setArmorClass(17);
		crTemplate.setFlatFootedAcOrStrSave(15);
		crTemplate.setFortSaveOrConSave(10);
		crTemplate.setRefSaveOrDexSave(8);
		crTemplate.setWillSaveOrWisSave(4);
		crTemplate.setGrappleOrChaSave(15);
		crTemplate.setMainAttacksAndSpells("Talons +10 (2d6+5) & 2 Foreclaws +5 (1d4+2) & bite +5 (1d8+2)");
		crTemplate.setVulnerabilities("None");
		crTemplate.setReactionsOrLegendaryActions("None");
		crTemplate.setOtherAttacksAndSpells("Pounce: full attack on a charge, Improved grab (bite)");
		crTemplate.setMoreSpellsAndAbilities("None");
		crTemplate.setAdditionalSpellsAndAbilities("Low-light vision, Scent");
		crTemplate.setFeatsAndExtraAbilities("Run, Toughness, Track");
		crTemplate.setSkillsAndProficiencies("Hide +9, Jump +27, Listen +12, Spot +12, Survival +12");
		crTemplate.setAbilityScores("Str 21, Dex 15, Con 21, Int 2, Wis 15, Cha 10");
		crTemplate.setItems("Raptor pelt & raptor meat (requires survival check)");
		crTemplate.setOther("Clever girl");
		try {
			saveCharacter(crTemplate);
		} catch (SQLException e) {
			// System.out.println(crTemplate.getName() + " already created, proceeding...");
		}
		
		// 3.5 CR 7: Chimera
		crTemplate.setName("CR 7 template 3.5 (Chimera)");
		crTemplate.setLevel("7");
		crTemplate.setRace("Chimera (magical beast)");
		crTemplate.setSize("L");
		crTemplate.setAppearance("A 12 ft beast with a Lion, Dragon, & Ram head, and a snake's head for a tail.");
		crTemplate.setSpeeds("30 ft, fly 50 ft");
		crTemplate.setMaximumHealth(76);
		crTemplate.setHitDice("9d10");
		crTemplate.setCurrentHealth(76);
		crTemplate.setArmorClass(19);
		crTemplate.setFlatFootedAcOrStrSave(18);
		crTemplate.setTouchAcOrIntSave(10);
		crTemplate.setFortSaveOrConSave(9);
		crTemplate.setRefSaveOrDexSave(7);
		crTemplate.setWillSaveOrWisSave(6);
		crTemplate.setGrappleOrChaSave(17);
		crTemplate.setInitiativeBonusOrDexScore(1);
		crTemplate.setMainAttacksAndSpells("Bite +12 (2d6+4) (or breath weapon) & Bite +12 (1d8+4)& Gore +12 (1d8+4) & 2 Claws +10 (1d6+2) & bite +10 (1d6+2 + Poison)");
		crTemplate.setReactionsOrLegendaryActions("4 reactions, one for each head");
		crTemplate.setOtherAttacksAndSpells("Breath weapon (1d4 refresh, 40 ft line of acid, 3d8, Ref 17 for half)");
		crTemplate.setAdditionalSpellsAndAbilities("Darkvision 60 ft, Low-light vision, Scent");
		crTemplate.setFeatsAndExtraAbilities("Alertness, Hover, Iron Will, Multiattack, Chimera Combat Reflexes (extra reactions)");
		crTemplate.setSkillsAndProficiencies("Hide +1 (+5 brush), Listen +9, Spot +9");
		crTemplate.setAbilityScores("Str 19, Dex 13, Con 17, Int 4, Wis 13, Cha 10");
		crTemplate.setItems("Chimera pelt & 4 heads (requires survival check)");
		crTemplate.setOther("Speak draconic, prefer to ambush in groups");
		try {
			saveCharacter(crTemplate);
		} catch (SQLException e) {
			// System.out.println(crTemplate.getName() + " already created, proceeding...");
		}
		
		// 3.5 CR 8: Stone Giant
		crTemplate.setName("CR 8 template 3.5 (Stone Giant)");
		crTemplate.setLevel("8");
		crTemplate.setRace("Stone Giant");
		crTemplate.setSize("L");
		crTemplate.setAppearance("A 16 ft giant");
		crTemplate.setSpeeds("30 ft");
		crTemplate.setMaximumHealth(119);
		crTemplate.setHitDice("14d8");
		crTemplate.setCurrentHealth(119);
		crTemplate.setArmorClass(25);
		crTemplate.setFlatFootedAcOrStrSave(23);
		crTemplate.setTouchAcOrIntSave(11);
		crTemplate.setFortSaveOrConSave(13);
		crTemplate.setRefSaveOrDexSave(6);
		crTemplate.setWillSaveOrWisSave(7);
		crTemplate.setGrappleOrChaSave(22);
		crTemplate.setInitiativeBonusOrDexScore(2);
		crTemplate.setMainAttacksAndSpells("10 ft: Greatclub +17/+12 (2d8+12) or Rock 180 ft, +11 (2d8+12). Power Attack -8/+16");
		crTemplate.setReactionsOrLegendaryActions("3 reactions, Rock Catching: catch Small, Medium, or Large rocks (or projectiles of similar shape). Ref to catch (DC: 11 Small, 16 Medium, and 21 Large)");
		crTemplate.setOtherAttacksAndSpells("2 Slams +17 melee (1d4+8), Point blank shot");
		crTemplate.setAdditionalSpellsAndAbilities("Darkvision 60 ft, Low-light vision, Rock throwing, Rock catching");
		crTemplate.setFeatsAndExtraAbilities("Combat Reflexes, Iron Will, Point Blank Shot, Power Attack, Precise Shot");
		crTemplate.setSkillsAndProficiencies("Climb +11, Hide +6 (+14 rocky), Jump +11, Spot +12");
		crTemplate.setAbilityScores("Str 27, Dex 15, Con 19, Int 10, Wis 12, Cha 11");
		crTemplate.setItems("Giant's toe (requires a survival check) & Hide armor");
		crTemplate.setOther("Speaks giant & common");
		try {
			saveCharacter(crTemplate);
		} catch (SQLException e) {
			// System.out.println(crTemplate.getName() + " already created, proceeding...");
		}
		
		// 3.5 CR 9: Roc
		crTemplate.setName("CR 9 template 3.5 (Roc)");
		crTemplate.setLevel("9");
		crTemplate.setRace("Roc (beast)");
		crTemplate.setSize("G");
		crTemplate.setAppearance("A 40 ft bird");
		crTemplate.setSpeeds("20 ft, Fly 80 ft");
		crTemplate.setMaximumHealth(207);
		crTemplate.setHitDice("18d8");
		crTemplate.setCurrentHealth(207);
		crTemplate.setArmorClass(17);
		crTemplate.setFlatFootedAcOrStrSave(15);
		crTemplate.setTouchAcOrIntSave(8);
		crTemplate.setFortSaveOrConSave(18);
		crTemplate.setRefSaveOrDexSave(13);
		crTemplate.setWillSaveOrWisSave(9);
		crTemplate.setGrappleOrChaSave(37);
		crTemplate.setMainAttacksAndSpells("15 ft: 2 talons +21 (2d6+12) and bite +19 (2d8+6). Power attack -9/+9");
		crTemplate.setReactionsOrLegendaryActions("None");
		crTemplate.setOtherAttacksAndSpells("Snatch (talon or bite)");
		crTemplate.setAdditionalSpellsAndAbilities("Low-light vision");
		crTemplate.setFeatsAndExtraAbilities("Alertness, Flyby Attack (move before & after attacking), Iron Will, Multiattack, Power Attack, Snatch, Wingover (180º turn for 10 ft)");
		crTemplate.setSkillsAndProficiencies("Hide -3, Listen +10, Spot +14");
		crTemplate.setAbilityScores("Str 34, Dex 15, Con 24, Int 2, Wis 13, Cha 11");
		crTemplate.setItems("Roc beak & roc feathers (requires a survival check)");
		crTemplate.setOther("Very protective of their nests");
		try {
			saveCharacter(crTemplate);
		} catch (SQLException e) {
			// System.out.println(crTemplate.getName() + " already created, proceeding...");
		}
		
		// 3.5 CR 10: Fire Giant
		crTemplate.setName("CR 10 template 3.5 (Fire Giant)");
		crTemplate.setLevel("10");
		crTemplate.setRace("Fire giant");
		crTemplate.setSize("L");
		crTemplate.setAppearance("A 16 ft giant");
		crTemplate.setSpeeds("30 ft");
		crTemplate.setMaximumHealth(142);
		crTemplate.setHitDice("15d8");
		crTemplate.setCurrentHealth(142);
		crTemplate.setArmorClass(23);
		crTemplate.setFlatFootedAcOrStrSave(23);
		crTemplate.setFortSaveOrConSave(14);
		crTemplate.setRefSaveOrDexSave(4);
		crTemplate.setGrappleOrChaSave(25);
		crTemplate.setInitiativeBonusOrDexScore(-1);
		crTemplate.setMainAttacksAndSpells("10 ft reach, Greatsword +20/+15/+10 (3d6+15) or Rock 120 ft, +10 (2d6+10 plus 2d6 fire). Power Attack -10/+20 & Great Cleave.");
		crTemplate.setResistances("Immune to fire");
		crTemplate.setVulnerabilities("Vulnerable to cold (150% dmg)");
		crTemplate.setReactionsOrLegendaryActions("Rock Catching: catch Small, Medium, or Large rocks (or projectiles of similar shape). Ref to catch (DC: 11 Small, 16 Medium, and 21 Large)");
		crTemplate.setOtherAttacksAndSpells("2 slams +20 melee (1d4+10), Improved Overrun (they cannot be avoided), Improved Sunder (double sunder damage)");
		crTemplate.setAdditionalSpellsAndAbilities("Low-light vision, Rock throwing, Rock catching");
		crTemplate.setFeatsAndExtraAbilities("Cleave, Great Cleave, Improved Overrun, Improved Sunder, Iron Will, Power Attack");
		crTemplate.setSkillsAndProficiencies("Climb +9, Craft (any one) +6, Intimidate +6, Jump +9, Spot +14");
		crTemplate.setAbilityScores("Str 31, Dex 9, Con 21, Int 10, Wis 14, Cha 11");
		crTemplate.setItems("Giant's toe (requires a survival check) & Half-plate armor");
		crTemplate.setOther("Speaks giant & common");
		try {
			saveCharacter(crTemplate);
		} catch (SQLException e) {
			// System.out.println(crTemplate.getName() + " already created, proceeding...");
		}
		
		// 3.5 CR 11: Could Giant
		crTemplate.setName("CR 11 template 3.5 (Cloud Giant)");
		crTemplate.setLevel("11");
		crTemplate.setRace("Cloud giant");
		crTemplate.setSize("H");
		crTemplate.setAppearance("A 22 ft giant");
		crTemplate.setSpeeds("50 ft");
		crTemplate.setMaximumHealth(178);
		crTemplate.setHitDice("17d8");
		crTemplate.setCurrentHealth(178);
		crTemplate.setArmorClass(25);
		crTemplate.setFlatFootedAcOrStrSave(24);
		crTemplate.setTouchAcOrIntSave(9);
		crTemplate.setFortSaveOrConSave(16);
		crTemplate.setRefSaveOrDexSave(6);
		crTemplate.setWillSaveOrWisSave(10);
		crTemplate.setGrappleOrChaSave(32);
		crTemplate.setInitiativeBonusOrDexScore(1);
		crTemplate.setMainAttacksAndSpells("15 ft reach, Gargantuan morningstar +22/+17/+12 (4d6+18) or Rock 140 ft +12 (2d8+12). Power Attack -11/+22 & Cleave");
		crTemplate.setResistances("None");
		crTemplate.setVulnerabilities("None");
		crTemplate.setReactionsOrLegendaryActions("Rock Catching: catch Small, Medium, or Large rocks (or projectiles of similar shape). Ref to catch (DC: 11 Small, 16 Medium, and 21 Large)");
		crTemplate.setOtherAttacksAndSpells("2 Slams +22 melee (1d6+12), Awesome Blow (-4 attack, 10 ft knockback), Improved Overrun (they cannot be avoided), Improved Bull Rush (no AoOs)");
		crTemplate.setAdditionalSpellsAndAbilities("Low-light vision, Oversized Weapon, Rock throwing, Rock catching, Scent");
		crTemplate.setFeatsAndExtraAbilities("Awesome Blow, Cleave, Improved Bull Rush, Improved Overrun, Iron Will, Power Attack");
		crTemplate.setSkillsAndProficiencies("Climb +19, Craft clothing +11, Diplomacy +3, Intimidate +11, Listen +15, Perform (strings) +2, Sense Motive +9, Spot +15");
		crTemplate.setAbilityScores("Str 35, Dex 13, Con 23, Int 12, Wis 16, Cha 13");
		crTemplate.setItems("Giant's toe (requires a survival check) & Chain shirt & very fine clothing");
		crTemplate.setOther("Speaks giant & common");
		try {
			saveCharacter(crTemplate);
		} catch (SQLException e) {
			// System.out.println(crTemplate.getName() + " already created, proceeding...");
		}
		
		// 3.5 CR 12: Frost Worm
		crTemplate.setName("CR 12 template 3.5 (Frost Worm)");
		crTemplate.setLevel("12");
		crTemplate.setRace("Frost worm (magical beast)");
		crTemplate.setSize("H");
		crTemplate.setAppearance("A 40 ft worm");
		crTemplate.setSpeeds("30 ft, Burrow 10 ft");
		crTemplate.setMaximumHealth(147);
		crTemplate.setHitDice("14d10");
		crTemplate.setCurrentHealth(147);
		crTemplate.setArmorClass(18);
		crTemplate.setFlatFootedAcOrStrSave(18);
		crTemplate.setTouchAcOrIntSave(8);
		crTemplate.setFortSaveOrConSave(14);
		crTemplate.setRefSaveOrDexSave(9);
		crTemplate.setWillSaveOrWisSave(6);
		crTemplate.setGrappleOrChaSave(30);
		crTemplate.setInitiativeBonusOrDexScore(4);
		crTemplate.setMainAttacksAndSpells("10 ft reach, Bite +21 (2d8+12 plus 1d8 cold) or Breath weapon. Begin fights with trill");
		crTemplate.setResistances("Immune to cold");
		crTemplate.setVulnerabilities("Vulnerable to fire (150% dmg)");
		crTemplate.setReactionsOrLegendaryActions("Cold touch 1d8 & Death throes (12d6 cold & 8d6 piercing, 50 ft radius, Ref half DC 22)");
		crTemplate.setOtherAttacksAndSpells("Breath weapon: 30 ft cone, 1/hr, 15d6 cold, Ref DC 22 half");
		crTemplate.setMoreSpellsAndAbilities("Trill: 100 ft radius, Will DC 17 or stunned for as long as the worm trills + 1d4 rounds, even if they are attacked (if attacked or violently shaken (a full-round action), they get another saving throw). Once a creature has resisted or broken the effect, it cannot be affected again by that same frost worm’s trill for 24 hours.");
		crTemplate.setAdditionalSpellsAndAbilities("Darkvison 60 ft, Low-light vision, Scent");
		crTemplate.setFeatsAndExtraAbilities("Alertness, Improved Initiative, Improved Natural Attack (bite), Iron Will, Weapon Focus (bite)");
		crTemplate.setSkillsAndProficiencies("Hide +3 (+13 ice), Listen +5, Spot +5");
		crTemplate.setAbilityScores("Str 26, Dex 10, Con 20, Int 2, Wis 11, Cha 11");
		crTemplate.setItems("Frost worm teeth (requires a survival check)");
		crTemplate.setOther("Cannot burrow through stone, only ice & frozen dirt");
		try {
			saveCharacter(crTemplate);
		} catch (SQLException e) {
			// System.out.println(crTemplate.getName() + " already created, proceeding...");
		}
		
		// 3.5 CR 13: Iron Golem
		crTemplate.setName("CR 13 template 3.5 (Iron Golem)");
		crTemplate.setLevel("13");
		crTemplate.setRace("Iron golem (magical beast)");
		crTemplate.setSize("L");
		crTemplate.setAppearance("A 12 ft golem");
		crTemplate.setSpeeds("20 ft");
		crTemplate.setMaximumHealth(129);
		crTemplate.setHitDice("18d10");
		crTemplate.setCurrentHealth(129);
		crTemplate.setArmorClass(30);
		crTemplate.setFlatFootedAcOrStrSave(30);
		crTemplate.setFortSaveOrConSave(6);
		crTemplate.setRefSaveOrDexSave(5);
		crTemplate.setGrappleOrChaSave(28);
		crTemplate.setInitiativeBonusOrDexScore(-1);
		crTemplate.setMainAttacksAndSpells("10 ft reach, 2 slams +23 melee (2d10+11) & Breath (free)");
		crTemplate.setResistances("DR 15/adamantium & fire heals 1/3 dmg (& breaks Slow). Immunities: magic (SR ∞), critical hits, mind effects, ability damage/drain, most Con effects, poison, paralysis, stunning, disease, death, etc.");
		crTemplate.setVulnerabilities("Slowed by lightning for 3 rounds");
		crTemplate.setReactionsOrLegendaryActions("None");
		crTemplate.setOtherAttacksAndSpells("Breath weapon: free action, 1d4+1 refresh, 10 ft cube, Fort DC 19 poison");
		crTemplate.setMoreSpellsAndAbilities("None");
		crTemplate.setAdditionalSpellsAndAbilities("Darkvision 60 ft, Low-light vision");
		crTemplate.setFeatsAndExtraAbilities("None");
		crTemplate.setSkillsAndProficiencies("Craft Construct +20");
		crTemplate.setAbilityScores("Str 33, Dex 9, Con Ø, Int Ø, Wis 11, Cha 1");
		crTemplate.setItems("Iron pieces (requires a Craft Construct check)");
		crTemplate.setOther("Weighs 5,000 lbs, does not breathe, & cannot speak");
		try {
			saveCharacter(crTemplate);
		} catch (SQLException e) {
			// System.out.println(crTemplate.getName() + " already created, proceeding...");
		}
		
		// 3.5 CR 14: Sand Giant
		crTemplate.setName("CR 14 template 3.5 (Sand Giant)");
		crTemplate.setLevel("14");
		crTemplate.setRace("Sand giant");
		crTemplate.setSize("H");
		crTemplate.setAppearance("A 25 ft sand giant");
		crTemplate.setSpeeds("40 ft");
		crTemplate.setMaximumHealth(218);
		crTemplate.setHitDice("19d8");
		crTemplate.setCurrentHealth(218);
		crTemplate.setArmorClass(23);
		crTemplate.setFlatFootedAcOrStrSave(22);
		crTemplate.setTouchAcOrIntSave(9);
		crTemplate.setFortSaveOrConSave(16);
		crTemplate.setRefSaveOrDexSave(6);
		crTemplate.setWillSaveOrWisSave(8);
		crTemplate.setGrappleOrChaSave(33);
		crTemplate.setInitiativeBonusOrDexScore(1);
		crTemplate.setMainAttacksAndSpells("15 ft reach, Greatsword +24/+19/+14 melee (4d6+16, 19-20/x2) (Power attack -14/+28 & Cleave) or Sand Arm");
		crTemplate.setResistances("None");
		crTemplate.setVulnerabilities("None");
		crTemplate.setReactionsOrLegendaryActions("Rock Catching: catch Small, Medium, or Large rocks (or projectiles of similar shape). Ref to catch (DC: 11 Small, 16 Medium, and 21 Large)");
		crTemplate.setOtherAttacksAndSpells("2 Slams +23 melee (2d6+11), Awesome Blow (-4 attack, 10 ft knockback), Improved Bull Rush (no AoOs)");
		crTemplate.setMoreSpellsAndAbilities("Sand Arm (2/day, within 40 ft, 20 ft sand arm, 20 ft reach, AC 20, hp 65, punch +21, 1d8+10, Fort DC 24 or stunned 1 round, 17 rounds)");
		crTemplate.setAdditionalSpellsAndAbilities("Low-light vision, Oversized Weapon, Rock Throwing , Rock Catching, Scent");
		crTemplate.setFeatsAndExtraAbilities("Awesome Blow, Cleave, Improved Bull Rush, Iron Will, Power Attack, Weapon Focus (greatsword)");
		crTemplate.setSkillsAndProficiencies("Jump +28, Listen +16, Spot +16, Survival  +16");
		crTemplate.setAbilityScores("Str 37, Dex 13, Con 23, Int 12, Wis 12, Cha  14");
		crTemplate.setItems("Giant's toe (requires a survival check) & Leather armor & ~3,000 gold");
		crTemplate.setOther("Speaks giant and common");
		try {
			saveCharacter(crTemplate);
		} catch (SQLException e) {
			// System.out.println(crTemplate.getName() + " already created, proceeding...");
		}

		// 3.5 CR 15: Aberration
		crTemplate.setName("CR 15 template 3.5 (Herald of Erythnuul)");
		crTemplate.setLevel("15");
		crTemplate.setRace("Herald of Erythnuul (Aberration)");
		crTemplate.setSize("H");
		crTemplate.setAppearance("A 20 ft shadowy aberration");
		crTemplate.setSpeeds("30 ft, Swim 30 ft");
		crTemplate.setMaximumHealth(172);
		crTemplate.setHitDice("18d12");
		crTemplate.setCurrentHealth(172);
		crTemplate.setFlatFootedAcOrStrSave(20);
		crTemplate.setTouchAcOrIntSave(11);
		crTemplate.setFortSaveOrConSave(10);
		crTemplate.setRefSaveOrDexSave(8);
		crTemplate.setWillSaveOrWisSave(13);
		crTemplate.setGrappleOrChaSave(27);
		crTemplate.setInitiativeBonusOrDexScore(3);
		crTemplate.setMainAttacksAndSpells("10 ft reach, 10 tentacles +18 (1d8+7) & bite +15 (2d6+3) & Bellow (free). Power attack -15/+15");
		crTemplate.setResistances("DR 10/good & ER (sonic) 15");
		crTemplate.setReactionsOrLegendaryActions("Regeneration 10 & Touch paralysis: Fort DC 23 or paralized until save passed");
		crTemplate.setOtherAttacksAndSpells("Bellow (frog croak): free action, 1d4 refresh, 2 types. 1st type: single target 100 ft range, ref half DC 20, 5d6 sonic dmg.");
		crTemplate.setMoreSpellsAndAbilities("Bellow 2nd type: 10 ft radius, Fort DC 23 or stunned 1d4 rounds, 30 ft radius deafened 1d6+4 rounds.");
		crTemplate.setAdditionalSpellsAndAbilities("Darkvison 60 ft");
		crTemplate.setFeatsAndExtraAbilities("Alertness, Blind-Fight, Cleave, Multiattack, Power Attack, Weapon Focus (tentacle)");
		crTemplate.setSkillsAndProficiencies("Listen +13, Spot +13, Swim +18");
		crTemplate.setAbilityScores("Str 24, Dex 16, Con 20, Int 7, Wis 16, Cha 12");
		crTemplate.setItems("Rare or better item & 1d10 random potions");
		crTemplate.setOther("You hear ominous croaks from every direction in the dark swamp forest");
		try {
			saveCharacter(crTemplate);
		} catch (SQLException e) {
			// System.out.println(crTemplate.getName() + " already created, proceeding...");
		}
		
		// 5e CR 1/2: Gnoll
		crTemplate.setName("CR .5 template 5e (Gnoll)");
		crTemplate.setLevel("1/2");
		crTemplate.setVersion(crTemplate.VERSION_5_e);
		crTemplate.setRace("Gnoll (humanoid)");
		crTemplate.setSize("M");
		crTemplate.setAppearance("A 5 ft gnoll");
		crTemplate.setSpeeds("30 ft");
		crTemplate.setMaximumHealth(22);
		crTemplate.setHitDice("5d8");
		crTemplate.setCurrentHealth(22);
		crTemplate.setArmorClass(15);
		crTemplate.setFlatFootedAcOrStrSave(2);
		crTemplate.setTouchAcOrIntSave(-2);
		crTemplate.setFortSaveOrConSave(0);
		crTemplate.setRefSaveOrDexSave(1);
		crTemplate.setWillSaveOrWisSave(0);
		crTemplate.setGrappleOrChaSave(-2);
		crTemplate.setInitiativeBonusOrDexScore(1);
		crTemplate.setMainAttacksAndSpells("Spear: +4 (1d8+2) or throw Spear 20/60 ft: +4 (1d6+2) or Longbow 150/600 ft: +3 (1d8+1)");
		crTemplate.setResistances("None");
		crTemplate.setReactionsOrLegendaryActions("Rampage: When the gnoll reduces a creature to 0 hit points with a melee attack on its turn, the gnoll can take a bonus action to move up to half its speed and make a bite attack.");
		crTemplate.setOtherAttacksAndSpells("Bite: +4 (1d4+2)");
		crTemplate.setMoreSpellsAndAbilities("None");
		crTemplate.setAdditionalSpellsAndAbilities("Darkvision 60 ft");
		crTemplate.setFeatsAndExtraAbilities("None");
		crTemplate.setSkillsAndProficiencies("Passive Perception 10");
		crTemplate.setAbilityScores("Str 14, Dex 12, Con 11, Int 6, Wis 10, Cha 7");
		crTemplate.setItems("Gnoll ears (requires a nature check)");
		crTemplate.setOther("Speaks gnoll");
		try {
			saveCharacter(crTemplate);
		} catch (SQLException e) {
			// System.out.println(crTemplate.getName() + " already created, proceeding...");
		}
		
		// 5e CR 1: Dire Wolf
		crTemplate.setName("CR 1 template 5e (Dire Wolf)");
		crTemplate.setLevel("1");
		crTemplate.setRace("Dire wolf");
		crTemplate.setSize("L");
		crTemplate.setAppearance("A 10 ft wolf");
		crTemplate.setSpeeds("50 ft");
		crTemplate.setMaximumHealth(37);
		crTemplate.setHitDice("5d10");
		crTemplate.setCurrentHealth(37);
		crTemplate.setArmorClass(14);
		crTemplate.setFlatFootedAcOrStrSave(3);
		crTemplate.setTouchAcOrIntSave(-4);
		crTemplate.setFortSaveOrConSave(2);
		crTemplate.setRefSaveOrDexSave(2);
		crTemplate.setWillSaveOrWisSave(1);
		crTemplate.setInitiativeBonusOrDexScore(2);
		crTemplate.setMainAttacksAndSpells("Bite +5: 2d6+3 & DC 13 Str save or knocked prone");
		crTemplate.setReactionsOrLegendaryActions("None");
		crTemplate.setOtherAttacksAndSpells("Pack Tactics: advantage on an attack roll against a creature if at least one of the wolf's un-incapacitated allies is within 5 ft of the creature.");
		crTemplate.setAdditionalSpellsAndAbilities("Keen Hearing and Smell: The wolf has advantage on Perception checks that rely on hearing or smell");
		crTemplate.setSkillsAndProficiencies("Perception +3, Stealth +4, Passive Perception 10");
		crTemplate.setAbilityScores("Str 17, Dex 15, Con 15, Int 3, Wis 12, Cha 7");
		crTemplate.setItems("Wolf pelt & wolf teeth (requires a nature check)");
		crTemplate.setOther("Wander in packs");
		try {
			saveCharacter(crTemplate);
		} catch (SQLException e) {
			// System.out.println(crTemplate.getName() + " already created, proceeding...");
		}
		
		// 5e CR 2: Giant boar
		crTemplate.setName("CR 2 template 5e (Giant Boar)");
		crTemplate.setLevel("2");
		crTemplate.setRace("Boar");
		crTemplate.setAppearance("An 8 ft boar");
		crTemplate.setSpeeds("40 ft");
		crTemplate.setMaximumHealth(42);
		crTemplate.setHitDice("5d10");
		crTemplate.setCurrentHealth(42);
		crTemplate.setArmorClass(12);
		crTemplate.setFortSaveOrConSave(3);
		crTemplate.setRefSaveOrDexSave(0);
		crTemplate.setWillSaveOrWisSave(-2);
		crTemplate.setGrappleOrChaSave(-3);
		crTemplate.setInitiativeBonusOrDexScore(0);
		crTemplate.setMainAttacksAndSpells("Tusk +5: 2d6+3");
		crTemplate.setOtherAttacksAndSpells("Charge: moves at least 20 feet straight toward a target, +2d6 damage on the first hit & a DC 13 Str save or they are knocked prone");
		crTemplate.setAdditionalSpellsAndAbilities("Relentless (Recharge on a Short Rest): If the boar takes 10 damage or less that would reduce it to 0 hit points, it is reduced to 1 hit point instead");
		crTemplate.setSkillsAndProficiencies("Passive Perception 8");
		crTemplate.setAbilityScores("Str 17, Dex 10, Con 16, Int 2, Wis 12, Cha 5");
		crTemplate.setItems("Boar pelt & boar tusks (requires a nature check)");
		crTemplate.setOther("Very aggressive");
		try {
			saveCharacter(crTemplate);
		} catch (SQLException e) {
			// System.out.println(crTemplate.getName() + " already created, proceeding...");
		}
		
		// 5e CR 3: Minotaur
		crTemplate.setName("CR 3 template 5e (Minotaur)");
		crTemplate.setLevel("3");
		crTemplate.setRace("Minotaur (monstrosity)");
		crTemplate.setAppearance("A 10 ft minotaur");
		crTemplate.setMaximumHealth(76);
		crTemplate.setHitDice("9d10");
		crTemplate.setCurrentHealth(76);
		crTemplate.setArmorClass(14);
		crTemplate.setFlatFootedAcOrStrSave(4);
		crTemplate.setTouchAcOrIntSave(-2);
		crTemplate.setWillSaveOrWisSave(3);
		crTemplate.setGrappleOrChaSave(-1);
		crTemplate.setMainAttacksAndSpells("Greataxe +6 (2d12+4) or Gore +6 (2d8+4) & Reckless: Gain advantage on attacks & attacks against you have advanatge for 1 turn");
		crTemplate.setOtherAttacksAndSpells("Charge: moves at least 10 feet straight toward a target, +2d8 damage on the first Gore hit & a DC 14 Str save or they are pushed back 10 ft & knocked prone");
		crTemplate.setAdditionalSpellsAndAbilities("Darkvision 60 ft");
		crTemplate.setFeatsAndExtraAbilities("Labyrinthine Recall: The minotaur can perfectly recall any path it has traveled");
		crTemplate.setSkillsAndProficiencies("Perception +7, Passive Perception 17");
		crTemplate.setAbilityScores("Str 18, Dex 11, Con 16, Int 6, Wis 16, Cha 9");
		crTemplate.setItems("Minotaur head (requires a nature check) & 600 gold");
		crTemplate.setOther("Speaks abyssal");
		try {
			saveCharacter(crTemplate);
		} catch (SQLException e) {
			// System.out.println(crTemplate.getName() + " already created, proceeding...");
		}
		
		// 5e CR 4: Elephant
		crTemplate.setName("CR 4 template 5e (Elephant)");
		crTemplate.setLevel("4");
		crTemplate.setRace("Elephant");
		crTemplate.setSize("H");
		crTemplate.setAppearance("A 16 ft elephant");
		crTemplate.setHitDice("8d12");
		crTemplate.setArmorClass(12);
		crTemplate.setFlatFootedAcOrStrSave(6);
		crTemplate.setTouchAcOrIntSave(-4);
		crTemplate.setRefSaveOrDexSave(-1);
		crTemplate.setWillSaveOrWisSave(0);
		crTemplate.setGrappleOrChaSave(-2);
		crTemplate.setInitiativeBonusOrDexScore(-1);
		crTemplate.setMainAttacksAndSpells("Gore +8 (3d8+6) or Stomp (prone target only) +8 (3d10+6)");
		crTemplate.setOtherAttacksAndSpells("Trampling Charge: moves at least 20 feet straight toward a target, the first Gore hit has a DC 12 Str save or they are knocked prone & the elephant gets 1 Stomp againt them");
		crTemplate.setAdditionalSpellsAndAbilities("None");
		crTemplate.setFeatsAndExtraAbilities("None");
		crTemplate.setSkillsAndProficiencies("Passive Perception 10");
		crTemplate.setAbilityScores("Str 22, Dex 9, Con 17, Int 3, Wis 11, Cha 6");
		crTemplate.setItems("Elephant tusks (requires a nature check)");
		crTemplate.setOther("Large and in charge");
		try {
			saveCharacter(crTemplate);
		} catch (SQLException e) {
			// System.out.println(crTemplate.getName() + " already created, proceeding...");
		}
		
		// 5e CR 5: Flesh Golem
		crTemplate.setName("CR 5 template 5e (Flesh Golem)");
		crTemplate.setLevel("5");
		crTemplate.setRace("Flesh golem (construct)");
		crTemplate.setSize("M");
		crTemplate.setAppearance("A 6 ft construct");
		crTemplate.setSpeeds("30 ft");
		crTemplate.setMaximumHealth(93);
		crTemplate.setHitDice("11d8");
		crTemplate.setCurrentHealth(93);
		crTemplate.setArmorClass(9);
		crTemplate.setFlatFootedAcOrStrSave(4);
		crTemplate.setTouchAcOrIntSave(-2);
		crTemplate.setFortSaveOrConSave(4);
		crTemplate.setGrappleOrChaSave(-3);
		crTemplate.setMainAttacksAndSpells("2x Slam (magical) +7: 2d8+4");
		crTemplate.setResistances("Lightning damage heals the golem. Immunities: charmed, exhaustion, frightened, paralyzed, petrified, poisoned, lightning, poison, any spell or effect that would alter its form, bludgeoning, piercing, & slashing from nonmagical attacks, except adamantine");
		crTemplate.setVulnerabilities("Fire damage gives disadvantage on attack rolls and ability checks until the end of its next turn");
		crTemplate.setReactionsOrLegendaryActions("Berserk: Whenever the golem starts its turn with 40 hit points or fewer, roll a d6. On a 6, the golem goes berserk");
		crTemplate.setOtherAttacksAndSpells("Berserk: attack the nearest creatue you can see, or an object if no creatures. Continue berserking until it is destroyed or regains all its hit points");
		crTemplate.setMoreSpellsAndAbilities("The golem's creator can try to calm it by speaking (action, 60 ft range). DC 15 Persuasion check. If the check succeeds, the golem ceases being berserk.");
		crTemplate.setAdditionalSpellsAndAbilities("Darkvision 60 ft");
		crTemplate.setFeatsAndExtraAbilities("Magic Resistance: The golem has advantage on saving throws against spells and other magical effects");
		crTemplate.setSkillsAndProficiencies("Passive Perception 10");
		crTemplate.setAbilityScores("Str 19, Dex 9, Con 18, Int 6, Wis 10, Cha 5");
		crTemplate.setItems("None");
		crTemplate.setOther("Understands the language of its creator, but cannot speak");
		try {
			saveCharacter(crTemplate);
		} catch (SQLException e) {
			// System.out.println(crTemplate.getName() + " already created, proceeding...");
		}
	}

	public Vector<String> getAllGameNames() throws SQLException {
		Vector<String> allGameNames = new Vector<String>();
		ResultSet dataLoaded = dbConnection.prepareStatement("SELECT name\r\nFROM savedata.gamesaves\r\nORDER BY name ASC;").executeQuery();
		while (dataLoaded.next()) {
			allGameNames.add(dataLoaded.getString("name"));
		}
		return allGameNames;
	}

	public Vector<String> getAllGameNamesChronological() throws SQLException { 
		Vector<String> allGameNamesChronological = new Vector<String>();
		ResultSet dataLoaded = dbConnection.prepareStatement("SELECT name\r\nFROM savedata.gamesaves\r\nORDER BY realtime ASC;").executeQuery();
		while (dataLoaded.next()) {
			allGameNamesChronological.add(dataLoaded.getString("name"));
		}
		return allGameNamesChronological;
	}

	public GameData loadGame(String gameName) throws SQLException, JsonProcessingException {
		GameData foundGame = new GameData();

		PreparedStatement loadGameStatement = dbConnection.prepareStatement("SELECT name, realtime, time, environment, location, currentturn, nextturn, yearnotation\r\n"
				+ "FROM savedata.gamesaves\r\n"
				+ "WHERE name=?;");
		loadGameStatement.setString(1, gameName);
		ResultSet dataLoaded = loadGameStatement.executeQuery();
		while (dataLoaded.next()) {
			foundGame.setName(dataLoaded.getString("name"));
			foundGame.setRealTime(dataLoaded.getLong("realtime"));
			foundGame.setTime(dataLoaded.getLong("time"));
			foundGame.setEnvironment(dataLoaded.getString("environment"));
			foundGame.setLocation(dataLoaded.getString("location"));
			foundGame.setCombatCharacters(getCombatCharacterListFromTable(foundGame.getName()));
			foundGame.setSidelineCharacters(getSidelineCharacterListFromTable(foundGame.getName()));
			foundGame.setCurrentEffects(getTimedEffectsFromTable(foundGame.getName()));
			foundGame.setCurrentCharacter(dataLoaded.getString("currentturn"));
			foundGame.setNextCharacter(dataLoaded.getString("nextturn"));
			foundGame.setInitiativeOrder(getInitiativeOrderFromTable(foundGame.getName()));
			foundGame.setupGameTimeDetails();
			foundGame.updateTimeDetails(createTimeDetails(dataLoaded.getString("yearnotation"), getMonthsFromTable(foundGame.getName())));
		}
//		System.out.println("Year notation: " + foundGame.getYearNotation());
		System.out.println("Loaded game " + foundGame.getName() + ".");
		return foundGame;
	}
	
	private DndTimeDetails createTimeDetails(String yearNotation, Vector<Month> months) {
		DndTimeDetails details = new DndTimeDetails();
		details.setYearNotation(yearNotation);
		long daysInYear = 0;
		for (Month m: months) {
			daysInYear += m.getDays();
		}
		details.setDaysInAYear(daysInYear);
		details.setMonths(months);
		return details;
	}
	
	private Vector<Month> getMonthsFromTable(String gameName) throws JsonMappingException, JsonProcessingException, SQLException {
		Vector<Month> gameMonths = new Vector<Month>();
//		System.out.println("Getting months for " + gameName);
		ResultSet dataLoaded = dbConnection.prepareStatement("SELECT name, days\r\nFROM months."+gameName+"months\r\n;").executeQuery();
		while (dataLoaded.next()) {
			gameMonths.add(new Month(dataLoaded.getString("name"), dataLoaded.getLong("days")));
		}
//		for (Month m: gameMonths) {System.out.println(m.getName());}
		return gameMonths;
	}

	private Vector<InitiativeOrderEntry> getInitiativeOrderFromTable(String gameName) throws JsonMappingException, JsonProcessingException, SQLException {
		Vector<InitiativeOrderEntry> gameInitiativeOrder = new Vector<InitiativeOrderEntry>();
		ResultSet dataLoaded = dbConnection.prepareStatement("SELECT name, initiativetotal\r\nFROM initiativeorder."+gameName+"initiative\r\n;").executeQuery();
		while (dataLoaded.next()) {
			InitiativeOrderEntry entry = new InitiativeOrderEntry();
			entry.setCharacterName(dataLoaded.getString("name"));
			entry.setInitiativeTotal(dataLoaded.getInt("initiativetotal"));
			gameInitiativeOrder.add(entry);
		}
		return gameInitiativeOrder;
	}

	private Vector<TimedEffect> getTimedEffectsFromTable(String gameName) throws JsonMappingException, JsonProcessingException, SQLException {
		Vector<TimedEffect> gameTimedEffects = new Vector<TimedEffect>();
		ResultSet dataLoaded = dbConnection.prepareStatement("SELECT name, effect, targets, remainingtime, endingtime, endinginitiative\r\nFROM timedeffects."+gameName+"timedeffects\r\n;").executeQuery();
		while (dataLoaded.next()) {
			TimedEffect entry = new TimedEffect();
			entry.setName(dataLoaded.getString("name"));
			entry.setEffect(dataLoaded.getString("effect"));
			entry.setTimeRemaining(dataLoaded.getLong("remainingtime"));
			entry.setEndingTime(dataLoaded.getLong("endingtime"));
			entry.setEndingInitiative(dataLoaded.getInt("endinginitiative"));
			gameTimedEffects.add(entry);
		}
		return gameTimedEffects;
	}
	
	public Vector<String> getSidelineCharacterListFromTable(String gameName) throws JsonMappingException, JsonProcessingException, SQLException {
		Vector<String> gameSidelineCharacters = new Vector<String>();
		ResultSet dataLoaded = dbConnection.prepareStatement("SELECT name\r\nFROM sidelinecharacters."+gameName+"sideline\r\n;").executeQuery();
		while (dataLoaded.next()) {
			gameSidelineCharacters.add(dataLoaded.getString("name"));
		}
		return gameSidelineCharacters;
	}

	public Vector<String> getCombatCharacterListFromTable(String gameName) throws JsonMappingException, JsonProcessingException, SQLException {
		Vector<String> gameCombatCharacters = new Vector<String>();
		ResultSet dataLoaded = dbConnection.prepareStatement("SELECT name\r\nFROM combatcharacters."+gameName+"combat\r\n;").executeQuery();
		while (dataLoaded.next()) {
			gameCombatCharacters.add(dataLoaded.getString("name"));
		}
		return gameCombatCharacters;	
	}

	public void saveGame(GameData game) throws SQLException, JsonProcessingException {
		PreparedStatement saveGameStatement = dbConnection.prepareStatement("INSERT INTO savedata.gamesaves\r\n"
				+ "(name, realtime, time, environment, location, currentturn, nextturn, yearnotation)\r\n"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?);");
		saveGameStatement.setString(1, game.getName());
		saveGameStatement.setLong(2, System.currentTimeMillis());
		saveGameStatement.setLong(3, game.getTime());
		saveGameStatement.setString(4, game.getEnvironment());
		saveGameStatement.setString(5, game.getLocation());
		createAndPopulateCombatCharactersTable(game.getName(), game.getCombatCharacters());
		createAndPopulateSidelineCharactersTable(game.getName(), game.getSidelineCharacters());
		createAndPopulateTimedEffectsTable(game.getName(), game.getCurrentEffects());
		saveGameStatement.setString(6, game.getCurrentCharacter());
		saveGameStatement.setString(7, game.getNextCharacter());
		createAndPopulateInitiativeOrderTable(game.getName(), game.getInitiativeOrder());
		saveGameStatement.setString(8, game.getYearNotation());
		createAndPopulateMonthsTable(game.getName(), game.getMonths());
		saveGameStatement.executeUpdate();
	}
	
	public void createAndPopulateMonthsTable(String gameName, Vector<Month> months) throws SQLException {
		try {
			dropMonthsTable(gameName); // reset before creating
		} catch (SQLException e) {}
	
		dbConnection.prepareStatement("CREATE TABLE months."+gameName+"months (\r\n"
				+ "    name varchar,\r\n"
				+ "    days varchar\r\n"
				+ ");").executeUpdate();
		
		PopulateMonthsTable(gameName, months);
	}
	
	private void PopulateMonthsTable(String gameName, Vector<Month> months) throws SQLException {
		for (Month monthEntry: months) {
			PreparedStatement addMonthsStatement = dbConnection.prepareStatement("INSERT INTO months."+gameName+"months\r\n"
					+ "(name, days)\r\n"
					+ "VALUES(?, ?);");
			addMonthsStatement.setString(1, monthEntry.getName());
			addMonthsStatement.setLong(2, monthEntry.getDays());
			addMonthsStatement.executeUpdate();
		}
	}

	public void createAndPopulateInitiativeOrderTable(String gameName, Vector<InitiativeOrderEntry> initiativeOrder) throws SQLException {
		try {
			dropInitiativeOrderTable(gameName); // reset before creating
		} catch (SQLException e) {}
	
		dbConnection.prepareStatement("CREATE TABLE initiativeorder."+gameName+"initiative (\r\n"
				+ "    name varchar,\r\n"
				+ "    initiativetotal varchar\r\n"
				+ ");").executeUpdate();
		
		PopulateInitiativeOrderTable(gameName, initiativeOrder);
	}
	
	private void PopulateInitiativeOrderTable(String gameName, Vector<InitiativeOrderEntry> initiativeOrder) throws SQLException {
		for (InitiativeOrderEntry entry: initiativeOrder) {
			PreparedStatement addInitiativeStatement = dbConnection.prepareStatement("INSERT INTO initiativeorder."+gameName+"initiative\r\n"
					+ "(name, initiativetotal)\r\n"
					+ "VALUES(?, ?);");
			addInitiativeStatement.setString(1, entry.getCharacterName());
			addInitiativeStatement.setInt(2, entry.getInitiativeTotal());
			addInitiativeStatement.executeUpdate();
		}
	}

	public void createAndPopulateTimedEffectsTable(String gameName, Vector<TimedEffect> currentEffects) throws SQLException {
		try {
			dropTimedEffectsTable(gameName);
		} catch (SQLException e) {}
	
		dbConnection.prepareStatement("CREATE TABLE timedeffects."+gameName+"timedeffects (\r\n"
				+ "    name varchar,\r\n"
				+ "    effect varchar,\r\n"
				+ "    targets varchar,\r\n"
				+ "    remainingtime varchar,\r\n"
				+ "    endingtime varchar,\r\n"
				+ "    endinginitiative varchar\r\n"
				+ ");").executeUpdate();

		PopulateTimedEffectsTable(gameName, currentEffects);
	}

	private void PopulateTimedEffectsTable(String gameName, Vector<TimedEffect> currentEffects) throws SQLException {
		for (TimedEffect effect: currentEffects) {
			PreparedStatement addTimedEffectsStatement = dbConnection.prepareStatement("INSERT INTO timedeffects."+gameName+"timedeffects\r\n"
					+ "(name, effect, targets, remainingtime, endingtime, endinginitiative)\r\n"
					+ "VALUES(?, ?, ?, ?, ?, ?);");
			addTimedEffectsStatement.setString(1, effect.getName());
			addTimedEffectsStatement.setString(2, effect.getEffect());
			addTimedEffectsStatement.setString(3, effect.getTargets());
			addTimedEffectsStatement.setLong(4, effect.getTimeRemaining());
			addTimedEffectsStatement.setLong(5, effect.getEndingTime());
			addTimedEffectsStatement.setInt(6, effect.getEndingInitiative());
			addTimedEffectsStatement.executeUpdate();
		}
	}

	public void createAndPopulateSidelineCharactersTable(String gameName, Vector<String> sidelineCharacters) throws SQLException {
		try {
			dropSidelineCharactersTable(gameName);
		} catch (SQLException e) {}
	
		dbConnection.prepareStatement("CREATE TABLE sidelinecharacters."+gameName+"sideline (\r\n"
				+ "    name varchar\r\n"
				+ ");").executeUpdate();

		PopulateSidelineCharactersTable(gameName, sidelineCharacters);
	}

	private void PopulateSidelineCharactersTable(String gameName, Vector<String> sidelineCharacters) throws SQLException {
		for (String character: sidelineCharacters) {
			PreparedStatement addSidelineCharactersStatement = dbConnection.prepareStatement("INSERT INTO sidelinecharacters."+gameName+"sideline\r\n"
					+ "(name)\r\n"
					+ "VALUES(?);");
			addSidelineCharactersStatement.setString(1, character);
			addSidelineCharactersStatement.executeUpdate();
		}
	}

	public void createAndPopulateCombatCharactersTable(String gameName, Vector<String> combatCharacters) throws SQLException {
		try {
			dropCombatCharactersTable(gameName);
		} catch (SQLException e) {}
	
		dbConnection.prepareStatement("CREATE TABLE combatcharacters."+gameName+"combat (\r\n"
				+ "    name varchar\r\n"
				+ ");").executeUpdate();

		PopulateCombatCharactersTable(gameName, combatCharacters);
	}
	
	private void PopulateCombatCharactersTable(String gameName, Vector<String> combatCharacters) throws SQLException {
		for (String character: combatCharacters) {
			PreparedStatement addCombatCharactersStatement = dbConnection.prepareStatement("INSERT INTO combatcharacters."+gameName+"combat\r\n"
					+ "(name)\r\n"
					+ "VALUES(?);");
			addCombatCharactersStatement.setString(1, character);
			addCombatCharactersStatement.executeUpdate();
		}
	}
	
	public void updateGame(GameData gameData) throws SQLException {
		PreparedStatement updateGameStatement = dbConnection.prepareStatement("UPDATE savedata.gamesaves\r\n"
				+ "SET realtime = ?, time = ?, environment = ?, location = ?, currentturn = ?, nextturn = ?, yearnotation = ?\r\n"
				+ "WHERE name = ?;");
		updateGameStatement.setLong(1, System.currentTimeMillis());
		updateGameStatement.setLong(2, gameData.getTime());
		updateGameStatement.setString(3, gameData.getEnvironment());
		updateGameStatement.setString(4, gameData.getLocation());
		createAndPopulateCombatCharactersTable(gameData.getName(), gameData.getCombatCharacters());
		createAndPopulateSidelineCharactersTable(gameData.getName(), gameData.getSidelineCharacters());
		createAndPopulateTimedEffectsTable(gameData.getName(), gameData.getCurrentEffects());
		updateGameStatement.setString(5, gameData.getCurrentCharacter());
		updateGameStatement.setString(6, gameData.getNextCharacter());
		createAndPopulateInitiativeOrderTable(gameData.getName(), gameData.getInitiativeOrder());
		updateGameStatement.setString(7, gameData.getYearNotation());
		updateGameStatement.setString(8, gameData.getName());
		updateGameStatement.executeUpdate();
	}

	public void deleteGame(String gameName) throws SQLException {
		dropInitiativeOrderTable(gameName);
		dropTimedEffectsTable(gameName);
		dropSidelineCharactersTable(gameName);
		dropCombatCharactersTable(gameName);
		dropMonthsTable(gameName);

		PreparedStatement deleteGameStatement = dbConnection.prepareStatement("DELETE\r\n"
				+ "FROM savedata.gamesaves\r\n"
				+ "WHERE name=?;");
		deleteGameStatement.setString(1, gameName);
		deleteGameStatement.executeUpdate();
	}
	
	public void dropMonthsTable(String gameName) throws SQLException {
		dbConnection.prepareStatement("DROP TABLE months."+gameName+"months;").executeUpdate();
	}
	
	public void dropInitiativeOrderTable(String gameName) throws SQLException {
		dbConnection.prepareStatement("DROP TABLE initiativeorder."+gameName+"initiative;").executeUpdate();
	}
	
	public void dropTimedEffectsTable(String gameName) throws SQLException {
		dbConnection.prepareStatement("DROP TABLE timedeffects."+gameName+"timedeffects;").executeUpdate();
	}
	
	public void dropSidelineCharactersTable(String gameName) throws SQLException {
		dbConnection.prepareStatement("DROP TABLE sidelinecharacters."+gameName+"sideline;").executeUpdate();
	}
	
	public void dropCombatCharactersTable(String gameName) throws SQLException {
		dbConnection.prepareStatement("DROP TABLE combatcharacters."+gameName+"combat;").executeUpdate();
	}

	public Vector<String> getAllCharacterSaveNames(String searchBy) throws SQLException {
		Vector<String> allCharacterSaveNames = new Vector<String>();
		String statement = "SELECT name, version, type, level\r\nFROM savedata.charactersaves\r\nORDER BY name ASC;";
		if (searchBy.length() > 0) {
			statement = "SELECT name, version, type, level\r\nFROM savedata.charactersaves\r\nWHERE LOWER(name) LIKE LOWER('%" + searchBy + "%')\r\nORDER BY name ASC;";
		}
		ResultSet dataLoaded = dbConnection.prepareStatement(statement).executeQuery();
		while (dataLoaded.next()) {
			String type = dataLoaded.getString("type");
			if (!type.equals("duplicate")) {
				String nameString = dataLoaded.getString("name");
				if (type.equals("npc")) {
					nameString += " (CR " + dataLoaded.getString("level") + ")";
				}
				allCharacterSaveNames.add(nameString);
			}
		}
		return allCharacterSaveNames;
	}
	
	public Vector<String> getAllCharacterSaveNamesWithoutCRorOrder() throws SQLException {
		Vector<String> allCharacterSaveNamesWithoutCRorOrder = new Vector<String>();
		ResultSet dataLoaded = dbConnection.prepareStatement("SELECT name\r\nFROM savedata.charactersaves\r\n;").executeQuery();
		while (dataLoaded.next()) {
			allCharacterSaveNamesWithoutCRorOrder.add(dataLoaded.getString("name"));
		}
		return allCharacterSaveNamesWithoutCRorOrder;
	}
	
	public Vector<String> getAllCharacterNamesChronological(String searchBy) throws SQLException {
		Vector<String> allCharacterSaveNamesChronological = new Vector<String>();
		String statement = "SELECT name, version, type, level\r\nFROM savedata.charactersaves\r\nORDER BY realtime ASC;";
		if (searchBy.length() > 0) {
			statement = "SELECT name, version, type, level\r\nFROM savedata.charactersaves\r\nWHERE LOWER(name) LIKE LOWER('%" + searchBy + "%')\r\nORDER BY realtime ASC;";
		}
		ResultSet dataLoaded = dbConnection.prepareStatement(statement).executeQuery();
		while (dataLoaded.next()) {
			String type = dataLoaded.getString("type");
			if (!type.equals("duplicate")) {
				String nameString = dataLoaded.getString("name");
				if (type.equals("npc")) {
					nameString += " (CR " + dataLoaded.getString("level") + ")";
				}
				allCharacterSaveNamesChronological.add(nameString);
			}
		}
		return allCharacterSaveNamesChronological;
	}
	
	public Vector<String> getAllCharacterNamesByLevel(String searchBy) throws SQLException {
		Vector<CharacterLevelEntry> charactersToSort = new Vector<CharacterLevelEntry>();
		Vector<CharacterLevelEntry> sortedCharacters = new Vector<CharacterLevelEntry>();
		Vector<String> allCharacterSaveNamesByLevel = new Vector<String>();
		String statement = "SELECT name, version, type, level\r\nFROM savedata.charactersaves\r\nORDER BY name ASC;";
		if (searchBy.length() > 0) {
			statement = "SELECT name, version, type, level\r\nFROM savedata.charactersaves\r\nWHERE LOWER(name) LIKE LOWER('%" + searchBy + "%')\r\nORDER BY name ASC;";
		}
		ResultSet dataLoaded = dbConnection.prepareStatement(statement).executeQuery();
		
		while (dataLoaded.next()) {
			String type = dataLoaded.getString("type");
			if (!type.equals("duplicate")) {
				String nameString = dataLoaded.getString("name");
				if (type.equals("npc")) {
					nameString += " (CR " + dataLoaded.getString("level") + ")";
				}
				String levelString = dataLoaded.getString("level");
				charactersToSort.add(new CharacterLevelEntry(nameString, getLevelAsDouble(levelString)));	
			}
		}
		
		for (CharacterLevelEntry unsortedEntry : charactersToSort) {
			double unsortedLevel = unsortedEntry.getLevel();
			if(sortedCharacters.isEmpty()) {
				sortedCharacters.add(new CharacterLevelEntry(unsortedEntry.getName(), unsortedLevel));
			} else {
				int indexBeforeCurrentEntry = 0;
				boolean placed = false;
				for (CharacterLevelEntry sortedEntry : sortedCharacters) {
					double sortedLevel = sortedEntry.getLevel();
					if (unsortedLevel < sortedLevel) {
						sortedCharacters.add(indexBeforeCurrentEntry, new CharacterLevelEntry(unsortedEntry.getName(), unsortedLevel));
						placed = true;
						break;
					}
					indexBeforeCurrentEntry++;
				}
				if (!placed) sortedCharacters.add(unsortedEntry); // add unsorted to the end
			}
		}
		
		for (CharacterLevelEntry entry : sortedCharacters) {
			allCharacterSaveNamesByLevel.add(entry.getName());
		}
		return allCharacterSaveNamesByLevel;
	}
	
	public Vector<String> getAllCharacterNamesByType(String searchBy) throws SQLException {
		Vector<String> allCharacterSaveNamesByType = new Vector<String>();
		String statement = "SELECT name, version, type, level\r\nFROM savedata.charactersaves\r\nORDER BY type ASC;";
		if (searchBy.length() > 0) {
			statement = "SELECT name, version, type, level\r\nFROM savedata.charactersaves\r\nWHERE LOWER(name) LIKE LOWER('%" + searchBy + "%')\r\nORDER BY type ASC;";
		}
		ResultSet dataLoaded = dbConnection.prepareStatement(statement).executeQuery();
		while (dataLoaded.next()) {
			String type = dataLoaded.getString("type");
			if (!type.equals("duplicate")) {
				String nameString = dataLoaded.getString("name");
				if (type.equals("npc")) {
					nameString += " (CR " + dataLoaded.getString("level") + ")";
				}
				allCharacterSaveNamesByType.add(nameString);
			}
		}
		return allCharacterSaveNamesByType;
	}
	
	private double getLevelAsDouble(String levelIn) {
		double levelOut = 0;
		if (levelIn.contains(" ")) {
	        String[] splitlevelIn = levelIn.split(" ");
	        for (String number: splitlevelIn) {
	        	levelOut += parseDoubleString(number);
	        }
	        return levelOut;
	    } else {
	    	return parseDoubleString(levelIn);
	    }
	}
	
	private double parseDoubleString(String ratio) {
	    if (ratio.contains("/")) {
	        String[] splitRatio = ratio.split("/");
	        return Double.parseDouble(splitRatio[0]) / Double.parseDouble(splitRatio[1]);
	    } else {
	        return Double.parseDouble(ratio);
	    }
	}

	public DndCharacter loadCharacter(String characterName) throws SQLException {
		DndCharacter loadedCharacter = new DndCharacter();
		
		PreparedStatement loadCharacterStatement = dbConnection.prepareStatement("SELECT name, realtime, version, type, level, race, "
				+ "appearance, size, speeds, maxhealth, hitdice, currenthealth, armorclass, flatfootedacorstrsave, touchacorintsave, "
				+ "fortsaveorconsave, refsaveordexsave, willsaveorwissave, grappleorchasave, initiativebonusordexscore, mainattacksandspells, "
				+ "resistances, vulnerabilities, reactionsorlegendaryactions, otherattacksandspells, morespellsandabilities, "
				+ "additionalspellsandabilities, featsandextraabilities, skillsandproficiencies, abilityscores, items, other\r\n"
				+ "FROM savedata.charactersaves\r\n"
				+ "WHERE name=?;");
		loadCharacterStatement.setString(1, characterName);
		ResultSet dataLoaded = loadCharacterStatement.executeQuery();
		while (dataLoaded.next()) {
			loadedCharacter.setName(dataLoaded.getString("name"));
			loadedCharacter.setRealTime(dataLoaded.getLong("realtime"));
			loadedCharacter.setVersion(dataLoaded.getString("version"));
			loadedCharacter.setType(dataLoaded.getString("type"));
			loadedCharacter.setLevel(dataLoaded.getString("level"));
			loadedCharacter.setRace(dataLoaded.getString("race"));
			loadedCharacter.setAppearance(dataLoaded.getString("appearance"));
			loadedCharacter.setSize(dataLoaded.getString("size"));
			loadedCharacter.setSpeeds(dataLoaded.getString("speeds"));
			loadedCharacter.setMaximumHealth(dataLoaded.getInt("maxhealth"));
			loadedCharacter.setHitDice(dataLoaded.getString("hitdice"));
			loadedCharacter.setCurrentHealth(dataLoaded.getInt("currenthealth"));
			loadedCharacter.setArmorClass(dataLoaded.getInt("armorclass"));
			loadedCharacter.setFlatFootedAcOrStrSave(dataLoaded.getInt("flatfootedacorstrsave"));
			loadedCharacter.setTouchAcOrIntSave(dataLoaded.getInt("touchacorintsave"));
			loadedCharacter.setFortSaveOrConSave(dataLoaded.getInt("fortsaveorconsave"));
			loadedCharacter.setRefSaveOrDexSave(dataLoaded.getInt("refsaveordexsave"));
			loadedCharacter.setWillSaveOrWisSave(dataLoaded.getInt("willsaveorwissave"));
			loadedCharacter.setGrappleOrChaSave(dataLoaded.getInt("grappleorchasave"));
			loadedCharacter.setInitiativeBonusOrDexScore(dataLoaded.getInt("initiativebonusordexscore"));
			loadedCharacter.setMainAttacksAndSpells(dataLoaded.getString("mainattacksandspells"));
			loadedCharacter.setResistances(dataLoaded.getString("resistances"));
			loadedCharacter.setVulnerabilities(dataLoaded.getString("vulnerabilities"));
			loadedCharacter.setReactionsOrLegendaryActions(dataLoaded.getString("reactionsorlegendaryactions"));
			loadedCharacter.setOtherAttacksAndSpells(dataLoaded.getString("otherattacksandspells"));
			loadedCharacter.setMoreSpellsAndAbilities(dataLoaded.getString("morespellsandabilities"));
			loadedCharacter.setAdditionalSpellsAndAbilities(dataLoaded.getString("additionalspellsandabilities"));
			loadedCharacter.setFeatsAndExtraAbilities(dataLoaded.getString("featsandextraabilities"));
			loadedCharacter.setSkillsAndProficiencies(dataLoaded.getString("skillsandproficiencies"));
			loadedCharacter.setAbilityScores(dataLoaded.getString("abilityscores"));
			loadedCharacter.setItems(dataLoaded.getString("items"));
			loadedCharacter.setOther(dataLoaded.getString("other"));
		}
		return loadedCharacter;
	}
	
	public DndCharacterMinimal loadCharacterMinimal(String characterName) throws SQLException {
		DndCharacterMinimal loadedCharacterMinimal = new DndCharacterMinimal();
		
		PreparedStatement loadGameStatement = dbConnection.prepareStatement("SELECT name, currenthealth, maxhealth, armorclass, mainattacksandspells, "
				+ "resistances, vulnerabilities, reactionsorlegendaryactions, type\r\n"
				+ "FROM savedata.charactersaves\r\n"
				+ "WHERE name=?;");
		loadGameStatement.setString(1, characterName);
		ResultSet dataLoaded = loadGameStatement.executeQuery();
		while (dataLoaded.next()) {
			loadedCharacterMinimal.setName(dataLoaded.getString("name"));
			loadedCharacterMinimal.setCurrentHealth(dataLoaded.getInt("currenthealth"));
			loadedCharacterMinimal.setMaximumHealth(dataLoaded.getInt("maxhealth"));
			loadedCharacterMinimal.setArmorClass(dataLoaded.getInt("armorclass"));
			loadedCharacterMinimal.setMainAttacksAndSpells(dataLoaded.getString("mainattacksandspells"));
			loadedCharacterMinimal.setResistances(dataLoaded.getString("resistances"));
			loadedCharacterMinimal.setVulnerabilities(dataLoaded.getString("vulnerabilities"));
			loadedCharacterMinimal.setReactionsOrLegendaryActions(dataLoaded.getString("reactionsorlegendaryactions"));
			loadedCharacterMinimal.setNpc(dataLoaded.getString("type") != "player");
		}
		return loadedCharacterMinimal;
	}

	public void saveCharacter(DndCharacter character) throws SQLException {
		PreparedStatement saveCharacterStatement = dbConnection.prepareStatement("INSERT INTO savedata.charactersaves\r\n"
				+ "(name, realtime, version, type, level, race, appearance, size, speeds, maxhealth, hitdice, currenthealth, armorclass, "
				+ "flatfootedacorstrsave, touchacorintsave, fortsaveorconsave, refsaveordexsave, willsaveorwissave, grappleorchasave, "
				+ "initiativebonusordexscore, mainattacksandspells, resistances, vulnerabilities, reactionsorlegendaryactions, "
				+ "otherattacksandspells, morespellsandabilities, additionalspellsandabilities, featsandextraabilities, "
				+ "skillsandproficiencies, abilityscores, items, other)\r\n"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
		saveCharacterStatement.setString(1, character.getName());
		saveCharacterStatement.setLong(2, System.currentTimeMillis());
		saveCharacterStatement.setString(3, character.getVersion());
		saveCharacterStatement.setString(4, character.getType());
		saveCharacterStatement.setString(5, character.getLevel());
		saveCharacterStatement.setString(6, character.getRace());
		saveCharacterStatement.setString(7, character.getAppearance());
		saveCharacterStatement.setString(8, character.getSize());
		saveCharacterStatement.setString(9, character.getSpeeds());
		saveCharacterStatement.setInt(10, character.getMaximumHealth());
		saveCharacterStatement.setString(11, character.getHitDice());
		saveCharacterStatement.setInt(12, character.getCurrentHealth());
		saveCharacterStatement.setInt(13, character.getArmorClass());
		saveCharacterStatement.setInt(14, character.getFlatFootedAcOrStrSave());
		saveCharacterStatement.setInt(15, character.getTouchAcOrIntSave());
		saveCharacterStatement.setInt(16, character.getFortSaveOrConSave());
		saveCharacterStatement.setInt(17, character.getRefSaveOrDexSave());
		saveCharacterStatement.setInt(18, character.getWillSaveOrWisSave());
		saveCharacterStatement.setInt(19, character.getGrappleOrChaSave());
		saveCharacterStatement.setInt(20, character.getInitiativeBonusOrDexScore());
		saveCharacterStatement.setString(21, character.getMainAttacksAndSpells());
		saveCharacterStatement.setString(22, character.getResistances());
		saveCharacterStatement.setString(23, character.getVulnerabilities());
		saveCharacterStatement.setString(24, character.getReactionsOrLegendaryActions());
		saveCharacterStatement.setString(25, character.getOtherAttacksAndSpells());
		saveCharacterStatement.setString(26, character.getMoreSpellsAndAbilities());
		saveCharacterStatement.setString(27, character.getAdditionalSpellsAndAbilities());
		saveCharacterStatement.setString(28, character.getFeatsAndExtraAbilities());
		saveCharacterStatement.setString(29, character.getSkillsAndProficiencies());
		saveCharacterStatement.setString(30, character.getAbilityScores());
		saveCharacterStatement.setString(31, character.getItems());
		saveCharacterStatement.setString(32, character.getOther());
		saveCharacterStatement.executeUpdate();
	}
	
	public void updateCharacter(DndCharacter updatedCharacter) throws SQLException {
		PreparedStatement editCharacterStatement = dbConnection.prepareStatement("UPDATE savedata.charactersaves SET realtime = ?, "
				+ "version = ?, type = ?, level = ?, race = ?, appearance = ?, size = ?, speeds = ?, maxhealth = ?, hitdice = ?, "
				+ "currenthealth = ?, armorclass = ?, flatfootedacorstrsave = ?, touchacorintsave = ?, fortsaveorconsave = ?, "
				+ "refsaveordexsave = ?, willsaveorwissave = ?, grappleorchasave = ?, initiativebonusordexscore = ?, mainattacksandspells = ?, "
				+ "resistances = ?, vulnerabilities = ?, reactionsorlegendaryactions = ?, otherattacksandspells = ?, morespellsandabilities = ?, "
				+ "additionalspellsandabilities = ?, featsandextraabilities = ?, skillsandproficiencies = ?, abilityscores = ?, items = ?, other =? "
				+ "WHERE name = ?;");
		editCharacterStatement.setLong(1, System.currentTimeMillis());
		editCharacterStatement.setString(2, updatedCharacter.getVersion());
		editCharacterStatement.setString(3, updatedCharacter.getType());
		editCharacterStatement.setString(4, updatedCharacter.getLevel());
		editCharacterStatement.setString(5, updatedCharacter.getRace());
		editCharacterStatement.setString(6, updatedCharacter.getAppearance());
		editCharacterStatement.setString(7, updatedCharacter.getSize());
		editCharacterStatement.setString(8, updatedCharacter.getSpeeds());
		editCharacterStatement.setInt(9, updatedCharacter.getMaximumHealth());
		editCharacterStatement.setString(10, updatedCharacter.getHitDice());
		editCharacterStatement.setInt(11, updatedCharacter.getCurrentHealth());
		editCharacterStatement.setInt(12, updatedCharacter.getArmorClass());
		editCharacterStatement.setInt(13, updatedCharacter.getFlatFootedAcOrStrSave());
		editCharacterStatement.setInt(14, updatedCharacter.getTouchAcOrIntSave());
		editCharacterStatement.setInt(15, updatedCharacter.getFortSaveOrConSave());
		editCharacterStatement.setInt(16, updatedCharacter.getRefSaveOrDexSave());
		editCharacterStatement.setInt(17, updatedCharacter.getWillSaveOrWisSave());
		editCharacterStatement.setInt(18, updatedCharacter.getGrappleOrChaSave());
		editCharacterStatement.setInt(19, updatedCharacter.getInitiativeBonusOrDexScore());
		editCharacterStatement.setString(20, updatedCharacter.getMainAttacksAndSpells());
		editCharacterStatement.setString(21, updatedCharacter.getResistances());
		editCharacterStatement.setString(22, updatedCharacter.getVulnerabilities());
		editCharacterStatement.setString(23, updatedCharacter.getReactionsOrLegendaryActions());
		editCharacterStatement.setString(24, updatedCharacter.getOtherAttacksAndSpells());
		editCharacterStatement.setString(25, updatedCharacter.getMoreSpellsAndAbilities());
		editCharacterStatement.setString(26, updatedCharacter.getAdditionalSpellsAndAbilities());
		editCharacterStatement.setString(27, updatedCharacter.getFeatsAndExtraAbilities());
		editCharacterStatement.setString(28, updatedCharacter.getSkillsAndProficiencies());
		editCharacterStatement.setString(29, updatedCharacter.getAbilityScores());
		editCharacterStatement.setString(30, updatedCharacter.getItems());
		editCharacterStatement.setString(31, updatedCharacter.getOther());
		editCharacterStatement.setString(32, updatedCharacter.getName());
		editCharacterStatement.executeUpdate();
	}

	public void deleteCharacter(String characterName, String gameName) throws SQLException {
		removeFromDbSidelineAndCombatTable(characterName, gameName);
		PreparedStatement deleteGameStatement = dbConnection.prepareStatement("DELETE\r\n"
				+ "FROM savedata.charactersaves\r\n"
				+ "WHERE name=?;");
		deleteGameStatement.setString(1, characterName);
		deleteGameStatement.executeUpdate();
	}
	
	private void removeFromDbSidelineAndCombatTable(String characterName, String gameName) {
		PreparedStatement deleteFromCombatStatement;
		try {
			deleteFromCombatStatement = dbConnection.prepareStatement("DELETE\r\n"
					+ "FROM combatcharacters."+gameName+"combat\r\n"
					+ "WHERE name=?;");
			deleteFromCombatStatement.setString(1, characterName);
			deleteFromCombatStatement.executeUpdate();
			
			PreparedStatement deleteFromSidelineStatement = dbConnection.prepareStatement("DELETE\r\n"
					+ "FROM sidelinecharacters."+gameName+"sideline\r\n"
					+ "WHERE name=?;");
			deleteFromSidelineStatement.setString(1, characterName);
			deleteFromSidelineStatement.executeUpdate();
		} catch (SQLException e) {
			// e.printStackTrace(); // usually an expected error about the default table not existing
		}
	}

	public Vector<String> getAllGroupNames() throws SQLException {
		Vector<String> allGroupNames = new Vector<String>();
		ResultSet dataLoaded = dbConnection.prepareStatement("SELECT groupname\r\nFROM savedata.groupsaves\r\n;").executeQuery();
		while (dataLoaded.next()) {
			allGroupNames.add(dataLoaded.getString("groupname"));
		}
		return allGroupNames;
	}

	public Vector<String> loadGroup(String groupName) throws SQLException {
	Vector<String> foundGroup = new Vector<String>();
		ResultSet dataLoaded = dbConnection.prepareStatement("SELECT name\r\nFROM charactergroups."+groupName+";").executeQuery();
		while (dataLoaded.next()) {
			foundGroup.add(dataLoaded.getString("name"));
		}
		return foundGroup;
	}

	public void saveGroup(CharacterGroup group) throws SQLException {
		PreparedStatement saveGroupStatement = dbConnection.prepareStatement("INSERT INTO savedata.groupsaves\r\n"
				+ "(groupname)\r\n"
				+ "VALUES(?);");
		saveGroupStatement.setString(1, createAndPopulateGroupTable(group.getName(), group.getCharacterNames()));
//		saveGroupStatement.setLong(2, System.currentTimeMillis());
		saveGroupStatement.executeUpdate();
	}
	
	private String createAndPopulateGroupTable(String groupName, Vector<String> characters) throws SQLException {
		try {
			dropGroupTable(groupName);
		} catch (SQLException e) {}
	
		dbConnection.prepareStatement("CREATE TABLE charactergroups."+groupName+" (\r\n"
				+ "    name varchar\r\n"
				+ ");").executeUpdate();

		PopulateGroupTable(groupName, characters);
		
		return groupName;
	}

	private void PopulateGroupTable(String groupName, Vector<String> characters) throws SQLException {
		for (String characterName: characters) {
			PreparedStatement addGroupStatement = dbConnection.prepareStatement("INSERT INTO charactergroups."+groupName+"\r\n"
					+ "(name)\r\n"
					+ "VALUES(?);");
			addGroupStatement.setString(1, characterName);
			addGroupStatement.executeUpdate();
		}
	}

	public void deleteGroup(String groupName) throws SQLException {
		dropGroupTable(groupName);

		PreparedStatement deleteGroupStatement = dbConnection.prepareStatement("DELETE\r\n"
				+ "FROM savedata.groupsaves\r\n"
				+ "WHERE groupname=?;");
		deleteGroupStatement.setString(1, groupName);
		deleteGroupStatement.executeUpdate();
	}
	
	private void dropGroupTable(String groupName) throws SQLException {
		dbConnection.prepareStatement("DROP TABLE charactergroups."+groupName+";").executeUpdate();
	}

	public void removeAllDuplicates(GameData gameData) throws SQLException {
		createAndPopulateCombatCharactersTable(gameData.getName(), gameData.getCombatCharacters());
		createAndPopulateSidelineCharactersTable(gameData.getName(), gameData.getSidelineCharacters());
		createAndPopulateInitiativeOrderTable(gameData.getName(), gameData.getInitiativeOrder());
		dbConnection.prepareStatement("DELETE\r\nFROM savedata.charactersaves\r\nWHERE type = 'duplicate';").executeUpdate();
	}

	public Vector<String> getAllDuplicateNames() throws SQLException {
		Vector<String> allCharacterDuplicateNames = new Vector<String>();
		ResultSet dataLoaded = dbConnection.prepareStatement("SELECT name, type\r\nFROM savedata.charactersaves\r\n;").executeQuery();
		while (dataLoaded.next()) {
			if (dataLoaded.getString("type").equals("duplicate")) {
				allCharacterDuplicateNames.add(dataLoaded.getString("name"));
			}
		}
		return allCharacterDuplicateNames;
	}
}
