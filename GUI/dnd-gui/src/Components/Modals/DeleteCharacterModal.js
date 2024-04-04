import { Box, Button, CircularProgress, Grid, IconButton, TextField, Tooltip } from '@mui/material';
import { Colors } from '../../Design/Colors/Colors';
import { getPxFromPercentOfWindowHeight, getPxFromPercentOfWindowWidth } from '../../Design/Sizing/getPxFromPercentOfWindow';
import { Category, Delete, FormatListNumbered, HdrAuto, North, Schedule, South } from '@mui/icons-material';
import { isCharacterNpc } from '../Helpers/isCharacterNpc';

export const DeleteCharacterModal = (parent) => {
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
            reverseAlphabeticalDelete: true,
            reverseChronologicalDelete: true,
            reverseLevelOrderDelete: true,
            reverseTypeOrderDelete: false,
        });
    }

    function updateResults(sortBy) {
        setDefaultButtonDirections();
        parent.getAllCharacters(sortBy, parent.state.searchDeleteBy);
        
    }

    return(
        <Box border="8px outset" borderRadius="10px" borderColor={Colors.healthColor2} sx={modalStyle} p="15px">
            {parent.state.gotCharacters ?
                <Box>
                    <Box display="flex" justifyContent="space-between" alignItems="center">
                        <Box display="flex" justifyContent="center">
                            {parent.state.reverseAlphabeticalDelete ? (
                                <Tooltip title="Sort alphabetically">
                                    <IconButton 
                                    onClick={() => { 
                                        updateResults("a");
                                        parent.setState({
                                            reverseAlphabeticalDelete: false,
                                            sortDeleteBy: "alphabetical",
                                        });
                                    }} 
                                    sx={{ color: Colors.sortColor}}>
                                        <HdrAuto/>{parent.state.sortDeleteBy === "alphabetical" ? <South /> : <></>}
                                    </IconButton>
                                </Tooltip>
                            ) : (
                                <Tooltip title="Sort reverse alphabetically">
                                    <IconButton 
                                    onClick={() => { 
                                        updateResults("ra");
                                        parent.setState({
                                            sortDeleteBy: "alphabetical",
                                        });
                                    }} 
                                    sx={{ color: Colors.sortColor}}>
                                        <HdrAuto/>{parent.state.sortDeleteBy === "alphabetical" ? <North /> : <></>}
                                    </IconButton>
                                </Tooltip>
                            )}
                            {parent.state.reverseChronologicalDelete ? (
                                <Tooltip title="Sort chronologically">
                                    <IconButton 
                                    onClick={() => { 
                                        updateResults("c");
                                        parent.setState({
                                            reverseChronologicalDelete: false,
                                            sortDeleteBy: "chronological",
                                        });
                                    }} 
                                    sx={{ color: Colors.sortColor}}>
                                        <Schedule/>{parent.state.sortDeleteBy === "chronological" ? <South /> : <></>}
                                    </IconButton>
                                </Tooltip>
                            ) : (
                                <Tooltip title="Sort reverse chronologically">
                                    <IconButton 
                                    onClick={() => { 
                                        updateResults("rc");
                                        parent.setState({
                                            sortDeleteBy: "chronological",
                                        });
                                    }} 
                                    sx={{ color: Colors.sortColor}}>
                                        <Schedule/>{parent.state.sortDeleteBy === "chronological" ? <North /> : <></>}
                                    </IconButton>
                                </Tooltip>
                            )}
                            {parent.state.reverseLevelOrderDelete ? (
                                <Tooltip title="Sort by level">
                                    <IconButton 
                                    onClick={() => { 
                                        updateResults("l");
                                        parent.setState({
                                            reverseLevelOrderDelete: false,
                                            sortDeleteBy: "level",
                                        });
                                    }} 
                                    sx={{ color: Colors.sortColor}}>
                                        <FormatListNumbered/>{parent.state.sortDeleteBy === "level" ? <South /> : <></>}
                                    </IconButton>
                                </Tooltip>
                            ) : (
                                <Tooltip title="Sort by level reverse">
                                    <IconButton 
                                    onClick={() => { 
                                        updateResults("rl");
                                        parent.setState({
                                            sortDeleteBy: "level",
                                        });
                                    }} 
                                    sx={{ color: Colors.sortColor}}>
                                        <FormatListNumbered/>{parent.state.sortDeleteBy === "level" ? <North /> : <></>}
                                    </IconButton>
                                </Tooltip>
                            )}
                            {parent.state.reverseTypeOrderDelete ? (
                                <Tooltip title="Sort by type">
                                    <IconButton 
                                    onClick={() => { 
                                        updateResults("t");
                                        parent.setState({
                                            reverseTypeOrderDelete: false,
                                            sortDeleteBy: "type",
                                        });
                                    }} 
                                    sx={{ color: Colors.sortColor}}>
                                        <Category/>{parent.state.sortDeleteBy === "type" ? <South /> : <></>}
                                    </IconButton>
                                </Tooltip>
                            ) : (
                                <Tooltip title="Sort by type reverse">
                                    <IconButton 
                                    onClick={() => { 
                                        updateResults("rt");
                                        parent.setState({
                                            sortDeleteBy: "type",
                                        });
                                    }} 
                                    sx={{ color: Colors.sortColor}}>
                                        <Category/>{parent.state.sortDeleteBy === "type" ? <North /> : <></>}
                                    </IconButton>
                                </Tooltip>
                            )}
                        </Box>
                        <Box style={{ fontSize: "24px" }}>{`Select characters to permanently delete (${parent.state.selectedDeleteCharacters.length} selected):`}</Box>
                        <Tooltip title="Delete selected characters">
                            <IconButton 
                            onClick={() => { 
                                parent.deleteSelectedCharacters();
                            }} 
                            sx={{ color: parent.state.selectedDeleteCharacters.length > 0 ? Colors.deleteColor : Colors.otherColor2}}>
                                <Delete/>
                            </IconButton>
                        </Tooltip>
                        <Box mr="100px">
                            <TextField
                                color={Colors.timeColor}
                                label="Search"
                                size="small"
                                defaultValue={parent.state.searchDeleteBy === "noSearchHere" ? "" : parent.state.searchDeleteBy}
                                onChange={(e) => {
                                    parent.setState({
                                        searchDeleteBy: e.target.value,
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
                                                style={{textTransform: 'none'}}
                                                color={isCharacterNpc(characterName[1]) ? Colors.otherColor : Colors.healthColor}
                                                onClick={() => { 
                                                    if (parent.state.selectedDeleteCharacters.includes(characterName[1])) {
                                                        var updatedCharNames = parent.state.selectedDeleteCharacters.filter(function(c) { 
                                                            return c !== characterName[1];
                                                        });
                                                        parent.setState({
                                                            selectedDeleteCharacters: updatedCharNames
                                                        });
                                                    } else {
                                                        parent.setState({
                                                            selectedDeleteCharacters: [...parent.state.selectedDeleteCharacters, characterName[1]]
                                                        });
                                                    }
                                                }} 
                                                variant={parent.state.selectedDeleteCharacters.includes(characterName[1]) ? "outlined" : "contained"}
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
                </Box>
            :
                <Box display="flex" justifyContent="center" alignItems="center" mt="20%">
                    <CircularProgress />
                </Box>
            }
        </Box>
    );
};
