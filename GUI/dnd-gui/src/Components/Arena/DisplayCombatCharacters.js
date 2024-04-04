import { Box } from '@mui/material';
import { CombatCharacter } from './CombatCharacter';
import { getPxFromPercentOfWindowHeight, getPxFromPercentOfWindowWidth } from '../../Design/Sizing/getPxFromPercentOfWindow';
import { Colors } from '../../Design/Colors/Colors';

export const DisplayCombatCharacters = (parent) => {
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
            borderColor={Colors.attackColor2}
        >
            {Object.entries(parent.state.combatCharacters).map( (combatCharacter) => {
                return <Box key={combatCharacter}>{CombatCharacter(combatCharacter[1], parent)}</Box>
            })}
        </Box>
    );
}