import { Box, Tooltip, Typography, IconButton } from '@mui/material';
import { InfoOutlined, KeyboardBackspace } from '@mui/icons-material';
import { DisplayTimedEffects } from './DisplayTimedEffects';
import { DisplayInitiativeOrder } from './DisplayInitiativeOrder';
import { DisplayGameSetting } from './DisplayGameSetting';
import { Colors } from '../../Design/Colors/Colors';

export const CombatBox = (parent) => {
    return  (
        <Box>
            <Box position="absolute" top="0px" left="3%">
                <Tooltip title="Back to the arena" placement="right">
                    <IconButton onClick={() => { parent.backToArena() }} size="small" sx={{ color: Colors.gameColor }}>
                        <KeyboardBackspace />
                    </IconButton>
                </Tooltip>
            </Box>
            <Box position="absolute" top="0px" left="46%">
                <Box display="flex" justifyContent="center" alignItems="center">
                    <Box pb="2px">
                        <Typography variant="h4" sx={{ color: Colors.combatColor }}>
                            Combat
                        </Typography>
                    </Box>
                    <Box pl="5px">
                        <Tooltip title="DM tips" placement="right">
                            <IconButton onClick={() => { parent.setState({combatRemindersModalOpen: true}) }} sx={{ color: Colors.combatColor }}>
                                <InfoOutlined/>
                            </IconButton>
                        </Tooltip>
                    </Box>
                </Box>
            </Box>     
            <Box display="flex" justifyContent="space-between" alignItems="end" mb="5px">
                {DisplayTimedEffects(parent)}
                {DisplayGameSetting(parent)}
            </Box>
            {DisplayInitiativeOrder(parent)}
        </Box>
    );
}
