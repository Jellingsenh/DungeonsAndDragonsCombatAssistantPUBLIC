import { Box, IconButton, TextField, Tooltip } from '@mui/material';
import { Colors } from '../../Design/Colors/Colors';
import { getPxFromPercentOfWindowHeight, getPxFromPercentOfWindowWidth } from '../../Design/Sizing/getPxFromPercentOfWindow';
import { Dehaze } from '@mui/icons-material';

export const EditInitiativeModal = (parent) => {
    const width = getPxFromPercentOfWindowWidth(parent, 88);
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
    // eslint-disable-next-line
    Object.entries(parent.state.initiativeOrder).map((entry) => {
        initiativeArray.push({characterName: entry[1].name, initiativeTotal: entry[1].initiativeTotal});
    });
    
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

    return(
        <Box border="4px outset" borderRadius="15px" borderColor={Colors.combatColor} sx={modalStyle} p="15px">
            <Box mt="25px" display="flex" justifyContent="center" style={{ fontSize: 20 }}>
                Edit initiative totals:
            </Box>
            <br></br>
            <Box display="flex" justifyContent="center" alignItems="center">
                {initiativeArray.map( (initiativeCharacter) => {
                    return (
                        <Box key={initiativeCharacter} m="5px" sx={{ maxWidth: getPxFromPercentOfWindowWidth(parent, 12)}}>
                            <TextField
                                type="number"
                                color={Colors.timeColor}
                                defaultValue={initiativeCharacter.initiativeTotal}
                                label={initiativeCharacter.characterName}
                                onChange={(initiativeTotal) => {
                                    addInititaiveTotal(initiativeCharacter.characterName, initiativeTotal.target.value);
                                }}
                            />
                        </Box>
                    );
                })}
            </Box>
            <Box display="flex" justifyContent="center" alignItems="center">
                <Tooltip title="Submit initiative order">
                    <IconButton 
                        onClick={() => { 
                            parent.editInitiative(initiativeArray) 
                        }}
                        sx={{ color: Colors.timeColor2 }}
                    >
                        <Dehaze/>
                    </IconButton>
                </Tooltip>
            </Box>
        </Box>
    );
};