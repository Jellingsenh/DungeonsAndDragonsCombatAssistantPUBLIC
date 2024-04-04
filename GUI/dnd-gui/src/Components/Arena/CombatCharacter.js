import { Box, IconButton, Tooltip, Typography } from '@mui/material';
import { East, GroupAddOutlined } from '@mui/icons-material';
import { Colors } from '../../Design/Colors/Colors';

export const CombatCharacter = (characterName, parent) => {
    return (
        <Box display="flex" justifyContent="space-between" alignItems="center">
            <Tooltip title={`Duplicate ${characterName}`} placement="right">
                <IconButton onClick={() => { parent.duplicateCharacter(characterName) }}>
                    <GroupAddOutlined sx={{ color: Colors.displayColor2 }}/>
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
            <Tooltip title={`Move ${characterName} to the sideline`} placement="left">
                <IconButton onClick={() => { parent.moveCharacterToSideline(characterName) }}>
                    <East sx={{ color: Colors.moveColor }}/>
                </IconButton>
            </Tooltip>
        </Box>
    );
}