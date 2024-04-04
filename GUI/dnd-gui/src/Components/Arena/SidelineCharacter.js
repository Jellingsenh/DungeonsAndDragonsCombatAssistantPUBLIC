import { Box, IconButton, Tooltip, Typography } from '@mui/material';
import { PersonRemove, West } from '@mui/icons-material';
import { Colors } from '../../Design/Colors/Colors';

export const SidelineCharacter = (characterName, parent) => {
    return (
        <Box display="flex" justifyContent="space-between" alignItems="center">
            <Tooltip title={`Move ${characterName} into combat`} placement="right">
                <IconButton onClick={() => { parent.moveCharacterToCombat(characterName) }}>
                    <West sx={{ color: Colors.moveColor }}/>
                </IconButton>
            </Tooltip>
            <Tooltip title={`View ${characterName}`}>
                <Typography 
                    onClick={() => { parent.getCharacter(characterName) }} 
                    // variant="button" 
                    sx={{ color: Colors.black }}
                >
                    {characterName}
                </Typography>
            </Tooltip>
            <Tooltip title={`Remove ${characterName} from the arena`} placement="left">
                <IconButton onClick={() => { parent.removeCharacterFromArena(characterName) }}>
                    <PersonRemove sx={{ color: Colors.removeColor }}/>
                </IconButton>
            </Tooltip>
        </Box>
    );
}