import { Box, IconButton, MenuItem, TextField, Tooltip } from '@mui/material';
import { Colors } from '../../Design/Colors/Colors';
import { getPxFromPercentOfWindowHeight, getPxFromPercentOfWindowWidth } from '../../Design/Sizing/getPxFromPercentOfWindow';
import { Edit, OpenInNew, Save } from '@mui/icons-material';

export const ViewEditCharacterModal = (parent) => {
    const width = getPxFromPercentOfWindowWidth(parent, 88);
    const height = getPxFromPercentOfWindowHeight(parent, 93)
    const gapHeight = "8px";
    const smallWidth = getPxFromPercentOfWindowWidth(parent, 8);
    const smallerWidth = getPxFromPercentOfWindowWidth(parent, 6);

    const modalStyle = {
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        bgcolor: 'background.paper',
        overflow: 'auto',
        maxHeight:  height,
        // minHeight: height, 
        minWidth: width, 
        maxWidth: width,
    };

    const createMode = parent.state.createMode;

    const viewEditCharacter = {
        name: parent.state.character.name ?? "",
        race: parent.state.character.race ?? "",
        appearance: parent.state.character.appearance ?? "",
        size: parent.state.character.size ?? "",
        speeds: parent.state.character.speeds ?? "",
        currentHealth: parent.state.character.currentHealth ?? 0,
        maximumHealth: parent.state.character.maximumHealth ?? 0,
        hitDice: parent.state.character.hitDice ?? "",
        armorClass: parent.state.character.armorClass ?? 0,
        flatFootedAcOrStrSave: parent.state.character.flatFootedAcOrStrSave ?? 0,
        touchAcOrIntSave: parent.state.character.touchAcOrIntSave ?? 0,
        fortSaveOrConSave: parent.state.character.fortSaveOrConSave ?? 0,
        refSaveOrDexSave: parent.state.character.refSaveOrDexSave ?? 0,
        willSaveOrWisSave: parent.state.character.willSaveOrWisSave ?? 0,
        grappleOrChaSave: parent.state.character.grappleOrChaSave ?? 0,
        initiativeBonusOrDexScore: parent.state.character.initiativeBonusOrDexScore ?? 0,
        mainAttacksAndSpells: parent.state.character.mainAttacksAndSpells ?? "",
        resistances: parent.state.character.resistances ?? "",
        vulnerabilities: parent.state.character.vulnerabilities ?? "",
        reactionsOrLegendaryActions: parent.state.character.reactionsOrLegendaryActions ?? "",
        otherAttacksAndSpells: parent.state.character.otherAttacksAndSpells ?? "",
        moreSpellsAndAbilities: parent.state.character.moreSpellsAndAbilities ?? "",
        additionalSpellsAndAbilities: parent.state.character.additionalSpellsAndAbilities ?? "",
        featsAndExtraAbilities: parent.state.character.featsAndExtraAbilities ?? "",
        skillsAndProficiencies: parent.state.character.skillsAndProficiencies ?? "",
        abilityScores: parent.state.character.abilityScores ?? "",
        items: parent.state.character.items ?? "",
        other: parent.state.character.other ?? "",
        type: parent.state.character.type ?? "",
        level: parent.state.character.level ?? "",
        version: parent.state.character.version ?? ""
    };

    const editMode = parent.state.editMode;
    const version5e = viewEditCharacter.version === "5e";
    const characterType = viewEditCharacter.type;
    const isCharacterNpc = characterType === "npc" || characterType === "duplicate";
    const isCharacterVehicle = characterType === "vehicle";
    const isItemOrEnvironment = characterType === "item" || characterType === "environment"

    let grappleBoxColor = Colors.attackColor2;
    if (version5e) {
        grappleBoxColor = Colors.defenseColor2;
    }

    const TouchAcBox = (
        <TextField
            focused
            InputProps={{
                inputProps: { style: { textAlign: 'center' } },
                readOnly: !editMode,
                style: { color: Colors.defenseColor2 }
            }}
            color={Colors.defenseColor}
            type="number"
            label={version5e ? isCharacterVehicle ? "Capacity (tons)" : "Intelligence Save" : "Touch AC"}
            value={viewEditCharacter.touchAcOrIntSave}
            onChange={(e) => {
                viewEditCharacter.touchAcOrIntSave = e.target.value;
                parent.setState({
                    character: viewEditCharacter,
                });
            }}
        />
    );

    const ReflexSaveBox = (
        <TextField
            focused
            InputProps={{
                inputProps: { style: { textAlign: 'center' } },
                readOnly: !editMode,
                style: { color: Colors.defenseColor2 }
            }}
            color={Colors.defenseColor}
            type="number"
            label={isCharacterVehicle ? "Wheels / Rooms" : version5e ? "Dexterity Save" : "Reflex Save"}
            value={viewEditCharacter.refSaveOrDexSave}
            onChange={(e) => {
                viewEditCharacter.refSaveOrDexSave = e.target.value;
                parent.setState({
                    character: viewEditCharacter,
                });
            }}
        />
    );

    return(
        <Box border="4px outset" borderRadius="10px" borderColor={editMode ? createMode ? Colors.displayColor2 : Colors.defenseColor2 : Colors.timeColor2} sx={modalStyle} p="10px">
            <Box mx="200px" display="flex" justifyContent="space-between" alignItems="center">
                {editMode ? 
                    <>
                        <Box style={{ fontSize: 20 }}>
                            {createMode ? 'Create a new character:' : <>{`Editing ${viewEditCharacter.name}:`}</> }
                        </Box>
                        <Tooltip title={`Save changes`} placement="top">
                            <IconButton 
                                onClick={() => { 
                                    if (createMode) {
                                        parent.createNewCharacter(viewEditCharacter);
                                    } else {
                                        if (parent.state.oldName === "") {
                                            parent.updateCharacter(viewEditCharacter.name, viewEditCharacter);
                                        }  else {
                                            parent.updateCharacter(parent.state.oldName, viewEditCharacter);
                                        }
                                    }
                                }} 
                                sx={{ color: Colors.displayColor2 }}
                            >
                                <Save/>
                            </IconButton>
                        </Tooltip>
                    </>
                :
                    <>
                        <Tooltip title={`Edit ${viewEditCharacter.name}`} placement="left">
                            <IconButton 
                                onClick={() => { 
                                    parent.setState({ 
                                        editMode: true,
                                        gotOldName: false,
                                    });
                                }} 
                                sx={{ color: Colors.sortColor }}>
                                <Edit/>
                            </IconButton>
                        </Tooltip>
                        <Tooltip title={`View ${viewEditCharacter.name} in a new tab`} placement="right">
                            <IconButton onClick={() => { window.open(`/CharacterPage?name=${viewEditCharacter.name}`, '_blank', 'noopener,noreferrer') }} sx={{ color: Colors.sortColor}}>
                                <OpenInNew/>
                            </IconButton>
                        </Tooltip>
                    </>
                }
            </Box>
            <Box display="block">
                <Box display="flex" gap={1} my={gapHeight}>
                    <Tooltip title={createMode ? "Do not use slashes ( \\ or / ) in your names" : (editMode ? "Changing the name will create a new character" : "")} placement="top-start">
                        <TextField
                            focused
                            required={editMode}
                            fullWidth
                            InputProps={{
                                readOnly: !editMode,
                                style: { color: Colors.displayColor2 }
                            }}
                            color={Colors.displayColor}
                            label="Name"
                            value={viewEditCharacter.name}
                            onChange={(e) => {
                                if (!parent.state.gotOldName) {
                                    parent.setState({
                                        oldName: viewEditCharacter.name,
                                        gotOldName: true,
                                    });
                                    
                                }
                                viewEditCharacter.name = e.target.value;
                                parent.setState({
                                    character: viewEditCharacter,
                                });
                            }}
                        />
                    </Tooltip>
                    <TextField
                        focused
                        fullWidth
                        InputProps={{
                            readOnly: !editMode,
                            style: { color: Colors.displayColor2 }
                        }}
                        color={Colors.displayColor}
                        label={isItemOrEnvironment || isCharacterVehicle ? "Origin" : "Race"}
                        value={viewEditCharacter.race}
                        onChange={(e) => {
                            viewEditCharacter.race = e.target.value;
                            parent.setState({
                                character: viewEditCharacter,
                            });
                        }}
                    />
                    <Box minWidth={smallWidth} maxWidth={smallWidth}>
                        <TextField
                            focused
                            select={editMode}
                            fullWidth
                            required={editMode}
                            InputProps={{
                                readOnly: !editMode,
                                style: { color: Colors.otherColor2 }
                            }}
                            color={Colors.otherColor}
                            label="Type"
                            InputLabelProps={{
                                component: 'span',
                            }}
                            value={editMode ? characterType : characterType.toUpperCase()}
                            onChange={(e) => {
                                viewEditCharacter.type = e.target.value;
                                parent.setState({
                                    character: viewEditCharacter,
                                });
                            }}
                        >
                            <MenuItem value="player">PLAYER</MenuItem>
                            <MenuItem value="npc">NPC</MenuItem>
                            <MenuItem value="duplicate">DUPLICATE</MenuItem>
                            <MenuItem value="vehicle">VEHICLE</MenuItem>
                            <MenuItem value="environment">ENVIRONMENT</MenuItem>
                            <MenuItem value="item">ITEM</MenuItem>
                        </TextField>
                    </Box>
                </Box>
                <Box display="flex" gap={1} my={gapHeight}>
                    <Box minWidth={smallerWidth} maxWidth={smallerWidth}>
                        <TextField
                            focused
                            select={editMode}
                            fullWidth
                            InputProps={{
                                readOnly: !editMode,
                                style: { color: Colors.displayColor2 }
                            }}
                            color={Colors.displayColor}
                            label="Size"
                            InputLabelProps={{
                                component: 'span',
                            }}
                            value={viewEditCharacter.size.toUpperCase()}
                            onChange={(e) => {
                                viewEditCharacter.size = e.target.value;
                                parent.setState({
                                    character: viewEditCharacter,
                                });
                            }}
                        >
                            <MenuItem value="-">{'-'}</MenuItem>
                            <MenuItem value="< F">{'< F'}</MenuItem>
                            <MenuItem value="F">F</MenuItem>
                            <MenuItem value="D">D</MenuItem>
                            <MenuItem value="T">T</MenuItem>
                            <MenuItem value="S">S</MenuItem>
                            <MenuItem value="M">M</MenuItem>
                            <MenuItem value="L">L</MenuItem>
                            <MenuItem value="H">H</MenuItem>
                            <MenuItem value="G">G</MenuItem>
                            <MenuItem value="C">C</MenuItem>
                            <MenuItem value="> C">{'> C'}</MenuItem>
                        </TextField>
                    </Box>
                    <TextField
                        focused
                        InputProps={{
                            inputProps: { style: { textAlign: 'center' } },
                            readOnly: !editMode,
                            style: { color: Colors.timeColor2 }
                        }}
                        color={Colors.timeColor}
                        label="Speed(s)"
                        value={viewEditCharacter.speeds}
                        onChange={(e) => {
                            viewEditCharacter.speeds = e.target.value;
                            parent.setState({
                                character: viewEditCharacter,
                            });
                        }}
                    />
                    <TextField
                        focused
                        InputProps={{
                            inputProps: { style: { textAlign: 'center' } },
                            readOnly: !editMode,
                            style: { color: Colors.timeColor2 }
                        }}
                        color={Colors.timeColor}
                        type="number"
                        label={isCharacterNpc ? "Initiative Bonus" : isItemOrEnvironment ? "Initiative Total" : isCharacterVehicle? "Driver's Initiative" : "Dexterity Score"}
                        value={viewEditCharacter.initiativeBonusOrDexScore}
                        onChange={(e) => {
                            viewEditCharacter.initiativeBonusOrDexScore = e.target.value;
                            parent.setState({
                                character: viewEditCharacter,
                            });
                        }}
                    />
                    <TextField
                        focused
                        fullWidth
                        InputProps={{
                            readOnly: !editMode,
                            style: { color: Colors.displayColor2 }
                        }}
                        color={Colors.displayColor}
                        label="Appearance"
                        value={viewEditCharacter.appearance}
                        onChange={(e) => {
                            viewEditCharacter.appearance = e.target.value;
                            parent.setState({
                                character: viewEditCharacter,
                            });
                        }}
                    />
                    <Box minWidth={smallWidth} maxWidth={smallWidth}>
                        <TextField
                            focused
                            InputProps={{
                                readOnly: !editMode,
                                style: { color: Colors.otherColor2 }
                            }}
                            color={Colors.otherColor}
                            label={isCharacterNpc ? "Challenge Rating" : isCharacterVehicle ? "Challenge Number" : "Level"}
                            value={viewEditCharacter.level}
                            onChange={(e) => {
                                viewEditCharacter.level = e.target.value;
                                parent.setState({
                                    character: viewEditCharacter,
                                });
                            }}
                        />
                    </Box>
                </Box>
                <Box display="flex" gap={.5} my={gapHeight}>
                    <TextField
                        focused
                        InputProps={{
                            inputProps: { style: { textAlign: 'center' } },
                            readOnly: !editMode,
                            style: { color: Colors.healthColor2 }
                        }}
                        color={Colors.healthColor}
                        type="number"
                        label="Current Health"
                        value={viewEditCharacter.currentHealth}
                        onChange={(e) => {
                            viewEditCharacter.currentHealth = e.target.value;
                            parent.setState({
                                character: viewEditCharacter,
                            });
                        }}
                    />
                    <Box display="flex" alignItems="center" style={{ fontWeight: "bold", fontSize: "30px" }}>/</Box>
                    <TextField
                        focused
                        InputProps={{
                            inputProps: { style: { textAlign: 'center' } },
                            readOnly: !editMode,
                            style: { color: Colors.healthColor2 }
                        }}
                        color={Colors.healthColor}
                        type="number"
                        label="Maximum Health"
                        value={viewEditCharacter.maximumHealth}
                        onChange={(e) => {
                            viewEditCharacter.maximumHealth = e.target.value;
                            parent.setState({
                                character: viewEditCharacter,
                            });
                        }}
                    />
                    <TextField
                        focused
                        InputProps={{
                            inputProps: { style: { textAlign: 'center' } },
                            readOnly: !editMode,
                            style: { color: Colors.defenseColor2 }
                        }}
                        color={Colors.defenseColor}
                        type="number"
                        label="Armor Class"
                        value={viewEditCharacter.armorClass}
                        onChange={(e) => {
                            viewEditCharacter.armorClass = e.target.value;
                            parent.setState({
                                character: viewEditCharacter,
                            });
                        }}
                    />
                    <TextField
                        focused
                        InputProps={{
                            inputProps: { style: { textAlign: 'center' } },
                            readOnly: !editMode,
                            style: { color: Colors.defenseColor2 }
                        }}
                        color={Colors.defenseColor}
                        type="number"
                        label={version5e ? isCharacterVehicle ? "Secondary AC" : "Strength Save" : "Flat-footed AC"}
                        value={viewEditCharacter.flatFootedAcOrStrSave}
                        onChange={(e) => {
                            viewEditCharacter.flatFootedAcOrStrSave = e.target.value;
                            parent.setState({
                                character: viewEditCharacter,
                            });
                        }}
                    />
                    {version5e ? ReflexSaveBox : TouchAcBox}
                    <TextField
                        focused
                        InputProps={{
                            inputProps: { style: { textAlign: 'center' } },
                            readOnly: !editMode,
                            style: { color: Colors.defenseColor2 }
                        }}
                        color={Colors.defenseColor}
                        type="number"
                        label={isCharacterVehicle ? "Driver's Steer" : version5e ? "Constitution Save" : "Fortitude Save"}
                        value={viewEditCharacter.fortSaveOrConSave}
                        onChange={(e) => {
                             viewEditCharacter.fortSaveOrConSave = e.target.value;
                            parent.setState({
                                character: viewEditCharacter,
                            });
                        }}
                    />
                    {version5e ? TouchAcBox : ReflexSaveBox}
                    <TextField
                        focused
                        InputProps={{
                            inputProps: { style: { textAlign: 'center' } },
                            readOnly: !editMode,
                            style: { color: Colors.defenseColor2 }
                        }}
                        color={Colors.defenseColor}
                        type="number"
                        label={isCharacterVehicle ? "Refueling Cost (g)" : version5e ? "Wisdom Save" : "Will Save"}
                        value={viewEditCharacter.willSaveOrWisSave}
                        onChange={(e) => {
                            viewEditCharacter.willSaveOrWisSave = e.target.value;
                            parent.setState({
                                character: viewEditCharacter,
                            });
                        }}
                    />
                    <TextField
                        focused
                        InputProps={{
                            inputProps: { style: { textAlign: 'center' } },
                            readOnly: !editMode,
                            style: { color: grappleBoxColor }
                        }}
                        color={version5e ? Colors.defenseColor : Colors.attackColor}
                        type="number"
                        label={isCharacterVehicle || isItemOrEnvironment ? "Cost (g)" : version5e ? "Charisma Save" : "Grapple"}
                        value={viewEditCharacter.grappleOrChaSave}
                        onChange={(e) => {
                            viewEditCharacter.grappleOrChaSave = e.target.value;
                            parent.setState({
                                character: viewEditCharacter,
                            });
                        }}
                    />
                    <TextField
                        focused
                        InputProps={{
                            inputProps: { style: { textAlign: 'center' } },
                            readOnly: !editMode,
                            style: { color: Colors.healthColor2 }
                        }}
                        color={Colors.healthColor}
                        label={isCharacterVehicle || isItemOrEnvironment ? "Weight" : "Hit Dice"}
                        value={viewEditCharacter.hitDice}
                        onChange={(e) => {
                            viewEditCharacter.hitDice = e.target.value;
                            parent.setState({
                                character: viewEditCharacter,
                            });
                        }}
                    />
                    <Box ml="3px" minWidth={smallWidth} maxWidth={smallWidth}>
                        <TextField
                            focused
                            select={editMode}
                            fullWidth
                            InputProps={{
                                readOnly: !editMode,
                                style: { color: Colors.otherColor2 }
                            }}
                            color={Colors.otherColor}
                            label="Version"
                            InputLabelProps={{
                                component: 'span',
                            }}
                            value={viewEditCharacter.version}
                            onChange={(e) => {
                                viewEditCharacter.version = e.target.value;
                                parent.setState({
                                    character: viewEditCharacter,
                                });
                            }}
                        >
                            <MenuItem value="3.5">3.5</MenuItem>
                            <MenuItem value="5e">5e</MenuItem>
                        </TextField>
                    </Box>
                </Box>
                <Box my={gapHeight}>
                    <TextField
                        focused
                        fullWidth
                        InputProps={{
                            readOnly: !editMode,
                            style: { color: Colors.attackColor2 }
                        }}
                        color={Colors.attackColor}
                        label="Attacks (weapons, spells, etc)"
                        value={viewEditCharacter.mainAttacksAndSpells}
                        onChange={(e) => {
                            viewEditCharacter.mainAttacksAndSpells = e.target.value;
                            parent.setState({
                                character: viewEditCharacter,
                            });
                        }}
                    />
                </Box>
                <Box my={gapHeight}>
                    <TextField
                        focused
                        fullWidth
                        InputProps={{
                            readOnly: !editMode,
                            style: { color: Colors.defenseColor2 }
                        }}
                        color={Colors.defenseColor}
                        label={isCharacterVehicle || isItemOrEnvironment ? "Defenses / Hardness" : "Resistances"}
                        value={viewEditCharacter.resistances}
                        onChange={(e) => {
                            viewEditCharacter.resistances = e.target.value;
                            parent.setState({
                                character: viewEditCharacter,
                            });
                        }}
                    />
                </Box>
                <Box my={gapHeight}>
                    <TextField
                        focused
                        fullWidth
                        InputProps={{
                            readOnly: !editMode,
                            style: { color: Colors.healthColor2 }
                        }}
                        color={Colors.healthColor}
                        label="Vulnerabilities"
                        value={viewEditCharacter.vulnerabilities}
                        onChange={(e) => {
                            viewEditCharacter.vulnerabilities = e.target.value;
                            parent.setState({
                                character: viewEditCharacter,
                            });
                        }}
                    />
                </Box>
                <Box my={gapHeight}>
                    <TextField
                        focused
                        fullWidth
                        InputProps={{
                            readOnly: !editMode,
                            style: { color: Colors.defenseColor2 }
                        }}
                        color={Colors.defenseColor}
                        label={isCharacterVehicle ? "Special Maneuvers" : version5e && isCharacterNpc ? "Legendary Actions" : "Reactions"}
                        value={viewEditCharacter.reactionsOrLegendaryActions}
                        onChange={(e) => {
                            viewEditCharacter.reactionsOrLegendaryActions = e.target.value;
                            parent.setState({
                                character: viewEditCharacter,
                            });
                        }}
                    />
                </Box>
                <Box my={gapHeight}>
                    <TextField
                        focused
                        fullWidth
                        size="small"
                        InputProps={{
                            readOnly: !editMode,
                            style: { color: Colors.attackColor2 }
                        }}
                        color={Colors.attackColor}
                        label="Other Attacks"
                        value={viewEditCharacter.otherAttacksAndSpells}
                        onChange={(e) => {
                            viewEditCharacter.otherAttacksAndSpells = e.target.value;
                            parent.setState({
                                character: viewEditCharacter,
                            });
                        }}
                    />
                </Box>
                <Box my={gapHeight}>
                    <TextField
                        focused
                        fullWidth
                        size="small"
                        InputProps={{
                            readOnly: !editMode,
                            style: { color: Colors.attackColor2 }
                        }}
                        color={Colors.attackColor}
                        label="More Attacks"
                        value={viewEditCharacter.moreSpellsAndAbilities}
                        onChange={(e) => {
                            viewEditCharacter.moreSpellsAndAbilities = e.target.value;
                            parent.setState({
                                character: viewEditCharacter,
                            });
                        }}
                    />
                </Box>
                <Box my={gapHeight}>
                    <TextField
                        focused
                        fullWidth
                        size="small"
                        InputProps={{
                            readOnly: !editMode,
                            style: { color: Colors.displayColor2 }
                        }}
                        color={Colors.displayColor}
                        label={isCharacterVehicle || isItemOrEnvironment ? "Addons" : "Abilities"}
                        value={viewEditCharacter.additionalSpellsAndAbilities}
                        onChange={(e) => {
                            viewEditCharacter.additionalSpellsAndAbilities = e.target.value;
                            parent.setState({
                                character: viewEditCharacter,
                            });
                        }}
                    />
                </Box>
                <Box my={gapHeight}>
                    <TextField
                        focused
                        fullWidth
                        size="small"
                        InputProps={{
                            readOnly: !editMode,
                            style: { color: Colors.displayColor2 }
                        }}
                        color={Colors.displayColor}
                        label={isCharacterVehicle || isItemOrEnvironment ? "More Addons" : "Feats"}
                        value={viewEditCharacter.featsAndExtraAbilities}
                        onChange={(e) => {
                            viewEditCharacter.featsAndExtraAbilities = e.target.value;
                            parent.setState({
                                character: viewEditCharacter,
                            });
                        }}
                    />
                </Box>
                <Box display="flex" gap={1} my={gapHeight}>
                    <TextField
                        focused
                        fullWidth
                        size="small"
                        InputProps={{
                            readOnly: !editMode,
                            style: { color: Colors.otherColor2 }
                        }}
                        color={Colors.otherColor}
                        label={isCharacterVehicle || isItemOrEnvironment ? "Repairs" : version5e ? "Skills (and passive perception)" : "Skills"}
                        value={viewEditCharacter.skillsAndProficiencies}
                        onChange={(e) => {
                            viewEditCharacter.skillsAndProficiencies = e.target.value;
                            parent.setState({
                                character: viewEditCharacter,
                            });
                        }}
                    />
                    <TextField
                        focused
                        fullWidth
                        size="small"
                        InputProps={{
                            readOnly: !editMode,
                            style: { color: Colors.otherColor2 }
                        }}
                        color={Colors.otherColor}
                        label={isCharacterVehicle ? "Terrain / Top Speed" : "Ability Scores"}
                        value={viewEditCharacter.abilityScores}
                        onChange={(e) => {
                            viewEditCharacter.abilityScores = e.target.value;
                            parent.setState({
                                character: viewEditCharacter,
                            });
                        }}
                    />
                </Box>
                <Box display="flex" gap={1} my={gapHeight}>
                    <TextField
                        focused
                        fullWidth
                        size="small"
                        InputProps={{
                            readOnly: !editMode,
                            style: { color: Colors.otherColor2 }
                        }}
                        color={Colors.otherColor}
                        label={isCharacterVehicle ? "Cargo / Resource Capacity (Fuel, O2, etc)" : isItemOrEnvironment ? "Related Items" : "Items"}
                        value={viewEditCharacter.items}
                        onChange={(e) => {
                            viewEditCharacter.items = e.target.value;
                            parent.setState({
                                character: viewEditCharacter,
                            });
                        }}
                    />
                    <TextField
                        focused
                        fullWidth
                        size="small"
                        InputProps={{
                            readOnly: !editMode,
                            style: { color: Colors.otherColor2 }
                        }}
                        color={Colors.otherColor}
                        label="Other"
                        value={viewEditCharacter.other}
                        onChange={(e) => {
                            viewEditCharacter.other = e.target.value;
                            parent.setState({
                                character: viewEditCharacter,
                            });
                        }}
                    />
                </Box>
            </Box>
        </Box>
    );
};
