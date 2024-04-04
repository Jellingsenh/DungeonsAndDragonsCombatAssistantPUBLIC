import { Box } from '@mui/material';
import { SidelineCharacter } from './SidelineCharacter';
import { getPxFromPercentOfWindowHeight, getPxFromPercentOfWindowWidth } from '../../Design/Sizing/getPxFromPercentOfWindow';
import { Colors } from '../../Design/Colors/Colors';

export const DisplaySidelineCharacters = (parent) => {
    const height = getPxFromPercentOfWindowHeight(parent, 80);
    const width = getPxFromPercentOfWindowWidth(parent, 30);
    return (
        <Box 
            p="5px" 
            sx={{ 
                minHeight: height, 
                maxHeight: height, 
                minWidth: width, 
                maxWidth: width,
                overflow: 'auto'
            }}
            border="2px outset" 
            borderRadius="15px" 
            borderColor={Colors.defenseColor2}
        >
            {Object.entries(parent.state.sidelineCharacters).map( (sidelineCharacter) => {
                return <Box key={sidelineCharacter}>{SidelineCharacter(sidelineCharacter[1], parent)}</Box>
            })}
        </Box>
    );
}