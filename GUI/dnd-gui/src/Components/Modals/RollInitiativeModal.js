import { Box, Grid, IconButton, TextField, Tooltip } from '@mui/material';
import { Colors } from '../../Design/Colors/Colors';
import { getPxFromPercentOfWindowHeight, getPxFromPercentOfWindowWidth } from '../../Design/Sizing/getPxFromPercentOfWindow';
import { Casino } from '@mui/icons-material';

export const RollInitiativeModal = (parent) => {
    const width = getPxFromPercentOfWindowWidth(parent, 85);
    const height = getPxFromPercentOfWindowHeight(parent, 25);

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

    var initiativeArray = [];

    function addInititaiveTotal(characterName, initiativeTotal) {
        if (initiativeTotal.length > 0) {
            // eslint-disable-next-line
            initiativeArray.map( (existingCharacter) => {
                if (existingCharacter.characterName === characterName) {
                    // console.log('Updating ' + characterName + '\'s initiative')
                    initiativeArray = initiativeArray.filter(function(existingPerson) { 
                        return existingPerson.characterName !== characterName
                    })
                }
            })
            initiativeArray.push({characterName: characterName, initiativeTotal: initiativeTotal});
        }
    };

    function initiativeArrayContainsCharacter(characterName) {
        // eslint-disable-next-line
        initiativeArray.map( (existingCharacter) => {
            if (existingCharacter.characterName === characterName) {
                return true;
            }
        });
        return false;
    }

    return(
        <Box border="4px outset" borderRadius="15px" borderColor={Colors.attackColor2} sx={modalStyle} p="15px">
            <Box my="20px" display="flex" justifyContent="center" style={{ fontSize: 20 }}>
                {parent.state.combatPCs.length > 0 ? 'Enter initiative totals:' : 'There are no PCs. Roll NPC initiaive below:'}
            </Box>
            <Box display="block">
                <Box display="block">
                    {parent.state.combatPCs.length > 0 ? 
                        <Grid container justifyContent="center" alignItems="center">
                            {Object.entries(parent.state.combatPCs).map( (characterName) => {
                                if (!initiativeArrayContainsCharacter(characterName[1])) {
                                    addInititaiveTotal(characterName[1], '0');
                                }
                                return (
                                    <Box key={characterName} m="5px" sx={{ maxWidth: getPxFromPercentOfWindowWidth(parent, 12)}}>
                                        <TextField
                                            type="number"
                                            color={Colors.timeColor}
                                            defaultValue={0}
                                            label={characterName[1]}
                                            onChange={(initiativeTotal) => {
                                                addInititaiveTotal(characterName[1], initiativeTotal.target.value);
                                            }}
                                        />
                                    </Box>
                                );
                            })}
                        </Grid>
                    :
                        <></>
                    }
                    <Box mt="20px" display="flex" justifyContent="center" alignItems="center">
                        <Tooltip title="Roll initiaitve & enter combat!">
                            <IconButton 
                                onClick={() => { 
                                    parent.rollInitiative(initiativeArray) 
                                }}
                                sx={{ color: Colors.combatColor }}
                            >
                                <Casino/>
                            </IconButton>
                        </Tooltip>
                    </Box>
                </Box>
            </Box>
        </Box>
    );
};