import { Box, IconButton, Tooltip, Typography } from '@mui/material';
import { DisplayCombatCharacters } from './DisplayCombatCharacters';
import { DisplaySidelineCharacters } from './DisplaySidelineCharacters';
import { Colors } from '../../Design/Colors/Colors';
import { Add, CasinoOutlined, DeleteForeverOutlined, Edit, EmojiPeople, FileDownloadOutlined, Groups2, InfoOutlined, SaveOutlined, SportsKabaddi, SportsMartialArts, SupervisorAccountOutlined, VideogameAsset } from '@mui/icons-material';

export const ArenaBox = (parent) => {
    const buttonGap = parent.state.height/1000 * 26;
    const buttonGap2 = parent.state.height/1000 * 32;

    return  (
        <Box>
            <Box position="absolute" top="0px" left="44%">
                <Box display="flex" justifyContent="center" alignItems="center">
                    <Box pb="2px">
                        <Typography variant="h4" sx={{ color: Colors.gameColor}}>
                            The Arena
                        </Typography>
                    </Box>
                    <Box pl="5px">
                        <Tooltip title="DM tips" placement="right">
                            <IconButton onClick={() => { parent.setState({infoModalOpen: true}) }} sx={{ color: Colors.gameColor}}>
                                <InfoOutlined/>
                            </IconButton>
                        </Tooltip>
                    </Box>
                </Box>
            </Box>
            <Box display="flex" justifyContent="center" alignItems="center" mb="5px">
                <Box display="flex" flexDirection="column" gap={buttonGap} alignItems="start" mx="10px">
                    <Box display="flex" justifyContent="center" alignItems="center">
                        <SportsMartialArts sx={{ color: Colors.characterColor }}/>
                        <Box display="flex" flexDirection="column">
                            <Tooltip title={
                                <>
                                    Bring a character into the arena
                                    <Box >
                                        from the database
                                    </Box>
                                </>
                            }
                                placement="left"
                            >
                                <IconButton 
                                    onClick={() => { 
                                        parent.setState({
                                            gotCharacters: false,
                                            selectedBringCharacters: [],
                                            bringCharacterModalOpen: true,
                                        })
                                        parent.getAllCharacters("a", parent.state.searchBy) // alphabetical by default 
                                    }} 
                                    sx={{ color: Colors.defenseColor2 }}
                                >
                                    <FileDownloadOutlined/>
                                </IconButton>
                            </Tooltip>
                            <Tooltip title="Create a new character" placement="left">
                                <IconButton 
                                    onClick={() => { 
                                        parent.setState({ 
                                            character: {},
                                            editMode: true,
                                            createMode: true,
                                            viewEditCharacterModalOpen: true,
                                        }) 
                                    }} 
                                    sx={{ color: Colors.displayColor2 }}
                                >
                                    <Add />
                                </IconButton>
                            </Tooltip>
                        </Box>
                    </Box>
                    <Box display="flex" justifyContent="center" alignItems="center">
                        <Groups2 sx={{ color: Colors.groupColor }}/>
                        <Box display="flex" flexDirection="column">
                            <Tooltip title={
                                    <>
                                        Bring a group into the arena
                                        <Box >
                                            from the database
                                        </Box>
                                    </>
                                }
                                placement="left"
                            >
                                <IconButton 
                                    onClick={() => { 
                                        parent.getGroups();
                                        parent.setState({
                                            bringGroupModalOpen: true
                                        });
                                    }} 
                                    sx={{ color: Colors.defenseColor2 }}
                                >
                                    <FileDownloadOutlined/>
                                </IconButton>
                            </Tooltip>
                            <Tooltip title="Create a new group" placement="left">
                                <IconButton 
                                    onClick={() => { 
                                        parent.setState({
                                            selectedGroupCharacters: [],
                                            createGroupModalOpen: true,
                                            gotCharacters: false,
                                        });
                                        parent.getAllCharacters("","");
                                    }} 
                                    sx={{ color: Colors.displayColor2 }}
                                >
                                    <Add />
                                </IconButton>
                            </Tooltip>
                        </Box>
                    </Box>
                    <Box display="flex" justifyContent="center" alignItems="center">
                        <VideogameAsset sx={{ color: Colors.gameColor }}/>
                        <Box display="flex" flexDirection="column">
                            <Tooltip title="Load a game" placement="left">
                                <IconButton onClick={() => { 
                                    parent.setState({
                                        gotGames: false,
                                    }) 
                                    parent.getGames('a') // Alphabetial by default
                                    parent.setState({
                                        loadGameModalOpen: true
                                    }) 
                                }} 
                                    sx={{ color: Colors.defenseColor2 }}
                                >
                                    <FileDownloadOutlined/>
                                </IconButton>
                            </Tooltip>
                            <Tooltip title="Create a game save" placement="left">
                                <IconButton 
                                    onClick={() => { 
                                        parent.setState({ gotGames: false, }) 
                                        parent.getGames('a');
                                        parent.setState({ createGameSaveModalOpen: true, }) 
                                    }} 
                                    sx={{ color: Colors.displayColor2 }}>
                                    <SaveOutlined />
                                </IconButton>
                            </Tooltip>
                        </Box>
                    </Box>
                </Box>
                <Box mx="5px">
                    <Typography variant="button" sx={{ color: Colors.attackColor2 }}>
                        Combat
                    </Typography>
                    {DisplayCombatCharacters(parent)}
                    <Tooltip title="Roll initiative">
                        <IconButton 
                            onClick={() => { 
                                parent.getCombatPCs()
                                parent.setState({rollInitiativeModalOpen: true}) 
                            }} 
                            sx={{ color: Colors.timeColor2 }}
                        >
                            <CasinoOutlined />
                        </IconButton>
                    </Tooltip>
                </Box>
                <Box mx="5px">
                    <Typography variant="button" sx={{ color: Colors.defenseColor2 }}>
                        Sideline
                    </Typography>
                    {DisplaySidelineCharacters(parent)}
                    <Tooltip title="Enter combat">
                        <IconButton onClick={() => {parent.enterCombat() }} sx={{ color: Colors.combatColor }}>
                            <SportsKabaddi />
                        </IconButton>
                    </Tooltip>
                </Box>
                <Box display="flex" flexDirection="column" gap={buttonGap2} alignItems="start" mx="10px">
                    <Box display="flex" flexDirection="column">
                        <Tooltip title="Delete a character from the database" placement="right">
                            <IconButton
                                onClick={() => { 
                                    parent.setState({
                                        gotCharacters: false,
                                        selectedDeleteCharacters: [],
                                        deleteCharacterModalOpen: true,
                                    })
                                    parent.getAllCharacters("a", parent.state.searchDeleteBy) // alphabetical by default
                                }} 
                            >
                                <DeleteForeverOutlined sx={{ color: Colors.deleteColor }}/> <EmojiPeople sx={{ color: Colors.characterColor }}/>
                            </IconButton>
                        </Tooltip>
                        <Tooltip title={
                            <>
                                Remove all duplicates from the arena
                                <Box >
                                    and delete them
                                </Box>
                            </>
                        }
                            placement="right"
                        >
                            <IconButton onClick={() => { parent.deleteAllduplicates() }}>
                                <DeleteForeverOutlined sx={{ color: Colors.deleteColor }}/> <SupervisorAccountOutlined sx={{ color: Colors.displayColor2 }}/>
                            </IconButton>
                        </Tooltip>
                    </Box>
                    <Box display="flex" justifyContent="center" alignItems="center">
                        <Box display="flex" flexDirection="column">
                            <Tooltip title="Edit a group" placement="right">
                                <IconButton
                                    onClick={() => { 
                                        parent.getGroups();
                                        parent.setState({editGroupModalOpen: true}) 
                                    }} 
                                >
                                    <Edit sx={{ color: Colors.defenseColor2 }}/>
                                </IconButton>
                            </Tooltip>
                            <Tooltip title={
                                    <>
                                        Delete a group from the database
                                        <Box display="flex" justifyContent="center">
                                            (does not delete characters)
                                        </Box>
                                    </>
                                } 
                                placement="right"
                            >
                                <IconButton
                                    onClick={() => { 
                                        parent.getGroups();
                                        parent.setState({deleteGroupModalOpen: true}) 
                                    }} 
                                >
                                    <DeleteForeverOutlined sx={{ color: Colors.deleteColor }}/>
                                </IconButton>
                            </Tooltip>
                        </Box>
                        <Groups2 sx={{ color: Colors.groupColor }}/>
                    </Box>
                    <Tooltip title="Delete a game save" placement="right">
                        <IconButton 
                            onClick={() => { 
                                parent.setState({gotGames: false}) 
                                parent.getGames('a') // Alphabetial by default
                                parent.setState({deleteGameModalOpen: true}) 
                            }}  
                        >
                            <DeleteForeverOutlined sx={{ color: Colors.deleteColor }}/> <VideogameAsset sx={{ color: Colors.gameColor }}/>
                        </IconButton>
                    </Tooltip>
                </Box>
            </Box>
        </Box>
    );
}
