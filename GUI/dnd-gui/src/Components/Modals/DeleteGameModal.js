import { Box, Button, CircularProgress, IconButton, Tooltip } from '@mui/material';
import { Colors } from '../../Design/Colors/Colors';
import { getPxFromPercentOfWindowHeight, getPxFromPercentOfWindowWidth } from '../../Design/Sizing/getPxFromPercentOfWindow';
import { HdrAuto, North, Schedule, South } from '@mui/icons-material';

export const DeleteGameModal = (parent) => {
    const width = getPxFromPercentOfWindowWidth(parent, 50);
    const height = getPxFromPercentOfWindowHeight(parent, 18)

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

    return(
        <Box border="5px outset" borderRadius="15px" borderColor={Colors.gameColor} sx={modalStyle} p="15px">
            {parent.state.gotGames ? 
                <>
                    <Box display="flex" justifyContent="left">
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
                        <Box mb="40px" ml="30%" display="flex" justifyContent="center" style={{ fontSize: 20 }}>
                            Select a game to delete:
                        </Box>
                    </Box>
                    <Box display="flex" justifyContent="center">
                        {parent.state.gameNames.length > 0 ?
                            <Box display="flex" justifyContent="center">
                                {Object.entries(parent.state.gameNames).map( (gameName) => {
                                    return (
                                        <Box key={gameName} mx="5px">
                                            <Button
                                                color={Colors.healthColor}
                                                style={{textTransform: 'none'}}
                                                onClick={() => { 
                                                    parent.deleteGame(gameName[1]) 
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
                            'No games found'
                        }
                    </Box>
                </>
            :
                <CircularProgress />
            }
        </Box>
    );
};