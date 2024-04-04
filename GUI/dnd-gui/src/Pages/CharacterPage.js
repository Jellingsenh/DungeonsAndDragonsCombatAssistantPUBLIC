import React from 'react';
import { getPxFromPercentOfWindowHeight, getPxFromPercentOfWindowWidth } from '../Design/Sizing/getPxFromPercentOfWindow';
import { Colors } from '../Design/Colors/Colors';
import { Box, MenuItem, TextField, Tooltip, IconButton, Typography } from '@mui/material';
import { Save, Edit } from '@mui/icons-material';
import { removeSlashes } from '../Components/Helpers/removeSlashes';

class CharacterPage extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            character: {},
            editMode: false,
            characterBeingViewedCurrentHealth: 0,
            oldName: "",
            gotOldName: false,

            width: window.innerWidth,
            height: window.innerHeight,
        };
        
        this.getCharacter();
    }

    componentDidMount() {
        window.addEventListener('resize', this.handleWindowSizeChange);
    }

    componentWillUnmount() {
        window.removeEventListener('resize', this.handleWindowSizeChange);
    }

    handleWindowSizeChange = () => {
        this.setState({ 
            width: window.innerWidth,
            height: window.innerHeight
        });
    }

    async getCharacter(){
        const queryParams = new URLSearchParams(window.location.search);
        const characterName = queryParams.get('name');
        // console.log(characterName);
        if (characterName !== "") {
            await fetch(window.apiURL + '/getCharacter/' + characterName, {
                method: 'GET',
            })
            .then(response => response.json())
            .then(response => {
                this.setState({
                    character: response,
                    editMode: false,
                });
                // console.log(response);
            })
            .catch(err => {
                console.log(err);
            });
        }   
    }

    async updateCharacter(oldName, newCharacter) {
        if (oldName !== "" && newCharacter.name !== "" ) {
            newCharacter.name = removeSlashes(newCharacter.name);
            var jsonHeaders = new Headers();
            jsonHeaders.append("Accept", "application/json");
            jsonHeaders.append("Content-Type", "application/json");

            await fetch(window.apiURL + '/editCharacter/' + oldName, {
                method: 'PUT',
                headers: jsonHeaders,
                body: JSON.stringify(newCharacter),
            })
            .then(this.setState({ 
                editMode: false,
                character: newCharacter,
            }))
            .catch(err => {
                console.log(err);
            });
        }
    }

    render() {
        const width = getPxFromPercentOfWindowWidth(this, 88);
        const height = getPxFromPercentOfWindowHeight(this, 93)
        const gapHeight = "8px";
        const smallWidth = getPxFromPercentOfWindowWidth(this, 8);
        const smallerWidth = getPxFromPercentOfWindowWidth(this, 6);

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

        const viewEditCharacter = {
            name: this.state.character.name ?? "",
            race: this.state.character.race ?? "",
            appearance: this.state.character.appearance ?? "",
            size: this.state.character.size ?? "",
            speeds: this.state.character.speeds ?? "",
            currentHealth: this.state.character.currentHealth ?? 0,
            maximumHealth: this.state.character.maximumHealth ?? 0,
            hitDice: this.state.character.hitDice ?? "",
            armorClass: this.state.character.armorClass ?? 0,
            flatFootedAcOrStrSave: this.state.character.flatFootedAcOrStrSave ?? 0,
            touchAcOrIntSave: this.state.character.touchAcOrIntSave ?? 0,
            fortSaveOrConSave: this.state.character.fortSaveOrConSave ?? 0,
            refSaveOrDexSave: this.state.character.refSaveOrDexSave ?? 0,
            willSaveOrWisSave: this.state.character.willSaveOrWisSave ?? 0,
            grappleOrChaSave: this.state.character.grappleOrChaSave ?? 0,
            initiativeBonusOrDexScore: this.state.character.initiativeBonusOrDexScore ?? 0,
            mainAttacksAndSpells: this.state.character.mainAttacksAndSpells ?? "",
            resistances: this.state.character.resistances ?? "",
            vulnerabilities: this.state.character.vulnerabilities ?? "",
            reactionsOrLegendaryActions: this.state.character.reactionsOrLegendaryActions ?? "",
            otherAttacksAndSpells: this.state.character.otherAttacksAndSpells ?? "",
            moreSpellsAndAbilities: this.state.character.moreSpellsAndAbilities ?? "",
            additionalSpellsAndAbilities: this.state.character.additionalSpellsAndAbilities ?? "",
            featsAndExtraAbilities: this.state.character.featsAndExtraAbilities ?? "",
            skillsAndProficiencies: this.state.character.skillsAndProficiencies ?? "",
            abilityScores: this.state.character.abilityScores ?? "",
            items: this.state.character.items ?? "",
            other: this.state.character.other ?? "",
            type: this.state.character.type ?? "",
            level: this.state.character.level ?? "",
            version: this.state.character.version ?? ""
        };

        const editMode = this.state.editMode;
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
                    this.setState({
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
                    this.setState({
                        character: viewEditCharacter,
                    });
                }}
            />
        );

        // if (this.state.width <= 300 || this.state.height <= 300) {
        //     return(
        //         <div style={{border: '20px solid transthis'}}>
        //             <div style={{flex: 1, width: '80%', height: '100%', resizeMode: 'contain',}}>
        //                 <h1>Mobile (not finished)</h1>
        //             </div>
        //         </div>
        //     );
        // } else {
            return (
                <Box>
                    <Box position="absolute" top="0px" left="44%">
                        <Box display="flex" justifyContent="center" alignItems="center">
                            <Box mt="10px">
                                <Typography variant="h4" sx={{ color: Colors.timeColor2}}>
                                    {`${viewEditCharacter.name}`}
                                </Typography>
                            </Box>
                        </Box>
                    </Box>
                    <Box border="4px outset" borderRadius="10px" borderColor={editMode ? Colors.defenseColor2 : Colors.timeColor2} sx={modalStyle} p="10px">
                        <Box mx="200px" display="flex" justifyContent="space-between" alignItems="center">
                            {editMode ? 
                                <>
                                    <Box style={{ fontSize: 20 }}>
                                        {`Editing ${viewEditCharacter.name}:`}
                                    </Box>
                                    <Tooltip title={`Save changes (reload page to discard changes)`} placement="top">
                                        <IconButton 
                                            onClick={() => { 
                                                if (this.state.oldName === "") {
                                                    this.updateCharacter(viewEditCharacter.name, viewEditCharacter);
                                                }  else {
                                                    this.updateCharacter(this.state.oldName, viewEditCharacter);
                                                }
                                            }} 
                                            sx={{ color: Colors.displayColor2 }}
                                        >
                                            <Save/>
                                        </IconButton>
                                    </Tooltip>
                                </>
                            :
                                <Box ml="47%">
                                    <Tooltip title={`Edit ${viewEditCharacter.name}`} placement="left">
                                        <IconButton 
                                            onClick={() => { 
                                                this.setState({ 
                                                    editMode: true,
                                                    gotOldName: false,
                                                });
                                            }} 
                                            sx={{ color: Colors.sortColor }}>
                                            <Edit/>
                                        </IconButton>
                                    </Tooltip>
                                </Box>
                            }
                        </Box>
                        <Box display="block">
                            <Box display="flex" gap={1} my={gapHeight}>
                                <Tooltip title={editMode ? "Changing the name will create a new character" : ""} placement="top-start">
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
                                            if (!this.state.gotOldName) {
                                                this.setState({
                                                    oldName: viewEditCharacter.name,
                                                    gotOldName: true,
                                                });
                                                
                                            }
                                            viewEditCharacter.name = e.target.value;
                                            this.setState({
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
                                        this.setState({
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
                                            this.setState({
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
                                            this.setState({
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
                                        this.setState({
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
                                        this.setState({
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
                                        this.setState({
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
                                            this.setState({
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
                                        this.setState({
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
                                        this.setState({
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
                                        this.setState({
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
                                        this.setState({
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
                                        this.setState({
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
                                        this.setState({
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
                                        this.setState({
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
                                        this.setState({
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
                                            this.setState({
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
                                        this.setState({
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
                                        this.setState({
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
                                        this.setState({
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
                                        this.setState({
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
                                        this.setState({
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
                                        this.setState({
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
                                        this.setState({
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
                                        this.setState({
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
                                        this.setState({
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
                                        this.setState({
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
                                        this.setState({
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
                                        this.setState({
                                            character: viewEditCharacter,
                                        });
                                    }}
                                />
                            </Box>
                        </Box>
                    </Box>
                </Box>
            );
        // }
    }
}

export default CharacterPage;