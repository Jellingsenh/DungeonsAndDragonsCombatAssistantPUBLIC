import { Box, Button, CircularProgress, Grid, IconButton, TextField, Tooltip } from '@mui/material';
import { Colors } from '../../Design/Colors/Colors';
import { getPxFromPercentOfWindowHeight, getPxFromPercentOfWindowWidth } from '../../Design/Sizing/getPxFromPercentOfWindow';
import { Category, FormatListNumbered, HdrAuto, NextPlan, North, Schedule, South } from '@mui/icons-material';
import { isCharacterNpc } from '../Helpers/isCharacterNpc';

export const BringCharacterModal = (parent) => {
    const width = getPxFromPercentOfWindowWidth(parent, 80);
    const height = getPxFromPercentOfWindowHeight(parent, 84);
    const width2 = getPxFromPercentOfWindowWidth(parent, 20);
    const height2 = getPxFromPercentOfWindowHeight(parent, 76);

    const modalStyle = {
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        bgcolor: 'background.paper',
        overflow: 'auto',
        maxHeight: height,
        minHeight: height, 
        minWidth: width, 
        maxWidth: width,
    };

    function setDefaultButtonDirections() {
        parent.setState({
            gotCharacters: false,
            reverseAlphabetical: true,
            reverseChronological: true,
            reverseLevelOrder: true,
            reverseTypeOrder: true,
        });
    }

    function updateResults(sortBy) {
        setDefaultButtonDirections();
        parent.getAllCharacters(sortBy, parent.state.searchBy);
        
    }

    return(
        <Box border="8px outset" borderRadius="10px" borderColor={Colors.defenseColor2} sx={modalStyle} p="15px">
            {parent.state.gotCharacters ?
                <>
                    <Box display="flex" justifyContent="space-between" alignItems="center">
                        <Box display="flex" justifyContent="center">
                            {parent.state.reverseAlphabetical ? (
                                <Tooltip title="Sort alphabetically">
                                    <IconButton 
                                    onClick={() => { 
                                        updateResults("a");
                                        parent.setState({
                                            reverseAlphabetical: false,
                                            sortBy: "alphabetical",
                                        });
                                    }} 
                                    sx={{ color: Colors.sortColor}}>
                                        <HdrAuto/>{parent.state.sortBy === "alphabetical" ? <South /> : <></>}
                                    </IconButton>
                                </Tooltip>
                            ) : (
                                <Tooltip title="Sort reverse alphabetically">
                                    <IconButton 
                                    onClick={() => { 
                                        updateResults("ra");
                                        parent.setState({
                                            sortBy: "alphabetical",
                                        });
                                    }}
                                    sx={{ color: Colors.sortColor}}>
                                        <HdrAuto/>{parent.state.sortBy === "alphabetical" ? <North /> : <></>}
                                    </IconButton>
                                </Tooltip>
                            )}
                            {parent.state.reverseChronological ? (
                                <Tooltip title="Sort chronologically">
                                    <IconButton 
                                    onClick={() => { 
                                        updateResults("c");
                                        parent.setState({
                                            reverseChronological: false,
                                            sortBy: "chronological",
                                        });
                                    }} 
                                    sx={{ color: Colors.sortColor}}>
                                        <Schedule/>{parent.state.sortBy === "chronological" ? <South /> : <></>}
                                    </IconButton>
                                </Tooltip>
                            ) : (
                                <Tooltip title="Sort reverse chronologically">
                                    <IconButton 
                                    onClick={() => { 
                                        updateResults("rc");
                                        parent.setState({
                                            sortBy: "chronological",
                                        });
                                    }} 
                                    sx={{ color: Colors.sortColor}}>
                                        <Schedule/>{parent.state.sortBy === "chronological" ? <North /> : <></>}
                                    </IconButton>
                                </Tooltip>
                            )}
                            {parent.state.reverseLevelOrder ? (
                                <Tooltip title="Sort by level">
                                    <IconButton 
                                    onClick={() => { 
                                        updateResults("l");
                                        parent.setState({
                                            reverseLevelOrder: false,
                                            sortBy: "level",
                                        });
                                    }} 
                                    sx={{ color: Colors.sortColor}}>
                                        <FormatListNumbered/>{parent.state.sortBy === "level" ? <South /> : <></>}
                                    </IconButton>
                                </Tooltip>
                            ) : (
                                <Tooltip title="Sort by level reverse">
                                    <IconButton 
                                    onClick={() => { 
                                        updateResults("rl");
                                        parent.setState({
                                            sortBy: "level",
                                        });
                                    }} 
                                    sx={{ color: Colors.sortColor}}>
                                        <FormatListNumbered/>{parent.state.sortBy === "level" ? <North /> : <></>}
                                    </IconButton>
                                </Tooltip>
                            )}
                            {parent.state.reverseTypeOrder ? (
                                <Tooltip title="Sort by type">
                                    <IconButton 
                                    onClick={() => { 
                                        updateResults("t");
                                        parent.setState({
                                            reverseTypeOrder: false,
                                            sortBy: "type",
                                        });
                                    }} 
                                    sx={{ color: Colors.sortColor}}>
                                        <Category/>{parent.state.sortBy === "type" ? <South /> : <></>}
                                    </IconButton>
                                </Tooltip>
                            ) : (
                                <Tooltip title="Sort by type reverse">
                                    <IconButton 
                                    onClick={() => { 
                                        updateResults("rt");
                                        parent.setState({
                                            sortBy: "type",
                                        });
                                    }} 
                                    sx={{ color: Colors.sortColor}}>
                                        <Category/>{parent.state.sortBy === "type" ? <North /> : <></>}
                                    </IconButton>
                                </Tooltip>
                            )}
                        </Box>
                        <Box style={{ fontSize: "24px" }}>{`Select characters to bring into the arena (${parent.state.selectedBringCharacters.length} selected):`}</Box>
                        <Tooltip title="Bring in selected characters">
                            <IconButton 
                            onClick={() => { 
                                parent.bringSelectedCharacters();
                            }} 
                            sx={{ color: parent.state.selectedBringCharacters.length > 0 ? Colors.defenseColor2 : Colors.otherColor2}}>
                                <NextPlan/>
                            </IconButton>
                        </Tooltip>
                        <Box mr="100px">
                            <TextField
                                color={Colors.timeColor}
                                label="Search"
                                size="small"
                                defaultValue={parent.state.searchBy === "noSearchHere" ? "" : parent.state.searchBy}
                                onChange={(e) => {
                                    parent.setState({
                                        searchBy: e.target.value,
                                    });
                                }}
                                onKeyDown={(ev) => {
                                    if(ev.key === 'Enter'){
                                        updateResults("a");
                                    }
                                }}
                            />
                        </Box>
                    </Box>
                    <br></br>
                    <Box display="flex" justifyContent="center">
                        {parent.state.allCharacters.length > 0 ? 
                            <Grid container direction="column" gap={1} maxHeight={height2}>
                                {Object.entries(parent.state.allCharacters).map( (characterName) => {
                                    return (
                                        <Box key={characterName} minWidth={width2}>
                                            <Button
                                                focused
                                                style={{textTransform: 'none'}}
                                                color={isCharacterNpc(characterName[1]) ? Colors.otherColor : Colors.defenseColor}
                                                onClick={() => { 
                                                    if (parent.state.selectedBringCharacters.includes(characterName[1])) {
                                                        var updatedCharNames = parent.state.selectedBringCharacters.filter(function(c) { 
                                                            return c !== characterName[1];
                                                        });
                                                        parent.setState({
                                                            selectedBringCharacters: updatedCharNames
                                                        });
                                                    } else {
                                                        parent.setState({
                                                            selectedBringCharacters: [...parent.state.selectedBringCharacters, characterName[1]]
                                                        });
                                                    }
                                                }} 
                                                variant={parent.state.selectedBringCharacters.includes(characterName[1]) ? "outlined" : "contained"}
                                                size="small"
                                            >
                                                {characterName[1]}
                                            </Button>
                                        </Box>
                                    );
                                })}
                            </Grid>
                        :
                            'No characters found.'
                        }
                    </Box>
                </>
            :  
                <Box display="flex" justifyContent="center" alignItems="center" mt="20%">
                    <CircularProgress />
                </Box>
            }
            
        </Box>
    );
};