import { Box, Button, CircularProgress, IconButton, TextField, Tooltip } from '@mui/material';
import { Colors } from '../../Design/Colors/Colors';
import { getPxFromPercentOfWindowHeight, getPxFromPercentOfWindowWidth } from '../../Design/Sizing/getPxFromPercentOfWindow';
import { HdrAuto, North, Save, Schedule, South } from '@mui/icons-material';

export const CreateGameSaveModal = (parent) => {
    const width = getPxFromPercentOfWindowWidth(parent, 50);
    const height = getPxFromPercentOfWindowHeight(parent, 30)

    const modalStyle = {
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        bgcolor: 'background.paper',
        overflow: 'auto',
        maxHeight:  height,
        minHeight: height, 
        minWidth: width, 
        maxWidth: width,
    };

    function setDefaultButtonDirections() {
        parent.setState({
            gotGames: false,
            reverseGameAlphabetical: true,
            reverseGameChronological: true,
        });
    }

    function updateResults(sortBy) {
        setDefaultButtonDirections();
        parent.getGames(sortBy);
    }

    // parent.setState({
    //     currentGameName: "Default",
    // });

    return(
        <Box border="5px outset" borderRadius="15px" borderColor={Colors.gameColor} sx={modalStyle} p="15px">
            {parent.state.gotGames ? 
                <>
                    <Box my="20px" display="flex" justifyContent="center" style={{ fontSize: 20 }}>
                        Enter a new game name:
                    </Box>
                    <Box display="flex" justifyContent="center" alignItems="center">
                        <TextField
                            color={Colors.displayColor}
                            label="New game name"
                            size="small"
                            // defaultValue={parent.state.currentGameName}
                            onChange={(e) => {
                                parent.setState({
                                    currentGameName: e.target.value,
                                });
                            }}
                            onKeyDown={(ev) => {
                                if(ev.key === 'Enter'){
                                    parent.saveGame(parent.state.currentGameName);
                                }
                            }}
                        />
                        <Tooltip title={`Save ${parent.state.currentGameName}`} placement="right">
                            <IconButton
                                onClick={() => { 
                                    parent.saveGame(parent.state.currentGameName);
                                }} 
                                sx={{ color: Colors.displayColor2}}>
                                    <Save/>
                                </IconButton>
                        </Tooltip>
                    </Box>
                    <Box mt="30px" display="flex" justifyContent="left">
                        <Box display="flex" justifyContent="center">
                            {parent.state.reverseGameAlphabetical ? (
                                <Tooltip title="Sort alphabetically">
                                    <IconButton 
                                    onClick={() => { 
                                        updateResults("a");
                                        parent.setState({
                                            reverseGameAlphabetical: false,
                                            sortGamesBy: "alphabetical",
                                        });
                                    }} 
                                    sx={{ color: Colors.sortColor}}>
                                        <HdrAuto/>{parent.state.sortGamesBy === "alphabetical" ? <South /> : <></>}
                                    </IconButton>
                                </Tooltip>
                            ) : (
                                <Tooltip title="Sort reverse alphabetically">
                                    <IconButton 
                                    onClick={() => { 
                                        updateResults("ra");
                                        parent.setState({
                                            sortGamesBy: "alphabetical",
                                        });
                                    }}
                                    sx={{ color: Colors.sortColor}}>
                                        <HdrAuto/>{parent.state.sortGamesBy === "alphabetical" ? <North /> : <></>}
                                    </IconButton>
                                </Tooltip>
                            )}
                            {parent.state.reverseGameChronological ? (
                                <Tooltip title="Sort chronologically">
                                    <IconButton 
                                    onClick={() => { 
                                        updateResults("c");
                                        parent.setState({
                                            reverseGameChronological: false,
                                            sortGamesBy: "chronological",
                                        });
                                    }} 
                                    sx={{ color: Colors.sortColor}}>
                                        <Schedule/>{parent.state.sortGamesBy === "chronological" ? <South /> : <></>}
                                    </IconButton>
                                </Tooltip>
                            ) : (
                                <Tooltip title="Sort reverse chronologically">
                                    <IconButton 
                                    onClick={() => { 
                                        updateResults("rc");
                                        parent.setState({
                                            sortGamesBy: "chronological",
                                        });
                                    }} 
                                    sx={{ color: Colors.sortColor}}>
                                        <Schedule/>{parent.state.sortGamesBy === "chronological" ? <North /> : <></>}
                                    </IconButton>
                                </Tooltip>
                            )}
                        </Box>
                        <Box mb="20px" ml="26%" display="flex" justifyContent="center" style={{ fontSize: 20 }}>
                        Or, overwrite an existing game save:
                    </Box>
                    </Box>

                    <Box display="flex" justifyContent="center">
                        {parent.state.gameNames.length > 0 ?
                            <Box display="flex" justifyContent="center">
                                {Object.entries(parent.state.gameNames).map( (gameName) => {
                                    return (
                                        <Box key={gameName} mx="5px">
                                            <Button
                                                color={Colors.displayColor}
                                                style={{textTransform: 'none'}}
                                                onClick={() => { 
                                                    if (window.confirm(`Are you sure you want to overwrite ${gameName[1]}?`)) {
                                                        parent.saveGame(gameName[1]);
                                                    }
                                                }} 
                                                variant="contained" 
                                                size="small" 
                                            >
                                                {gameName[1]}
                                            </Button>
                                        </Box>
                                    );
                                })}
                            </Box>
                        :
                            'No games found.'
                        }
                    </Box>
                </>
            :
                <CircularProgress />
            }
        </Box>
    );
};