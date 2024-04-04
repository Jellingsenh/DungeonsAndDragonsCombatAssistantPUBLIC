import { Box, Tooltip, Typography } from '@mui/material';
import { Colors } from '../../Design/Colors/Colors';
import { getPxFromPercentOfWindowHeight, getPxFromPercentOfWindowWidth } from '../../Design/Sizing/getPxFromPercentOfWindow';

export const BringInSidelineCharacterModal = (parent) => {
    const height = getPxFromPercentOfWindowHeight(parent, 80);
    const width = getPxFromPercentOfWindowWidth(parent, 30);

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

    return(
        <Box border="4px outset" borderRadius="5px" borderColor={Colors.defenseColor2} sx={modalStyle} p="5px">
            <Box display="flex" justifyContent="center" style={{ fontSize: 20 }}>
                Bring in a sideline character:
            </Box>
            <Box display="flex" justifyContent="center" mt="10px">
                <Box display="block">
                    {Object.entries(parent.state.sidelineCharacters).map( (sidelineCharacter) => {
                        return <Box mt="5px" key={sidelineCharacter}>
                            <Tooltip title={`Bring ${sidelineCharacter[1]} into combat`}>
                                <Typography
                                    border="2px outset" 
                                    borderRadius="15px"
                                    borderColor={Colors.defenseColor2}
                                    px="10px"
                                    py="2px"
                                    onClick={() => {parent.moveCharacterToCombat(sidelineCharacter[1])}}
                                    sx={{ color: Colors.black }}
                                >
                                    {sidelineCharacter[1]}
                                </Typography>
                            </Tooltip>
                        </Box>
                    })}
                </Box>
            </Box>
        </Box>
    );
};