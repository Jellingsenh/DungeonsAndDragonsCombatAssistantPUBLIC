import { Box, IconButton, Tooltip, Typography } from '@mui/material';
import { TimedEffect } from './TimedEffect';
import { getPxFromPercentOfWindowHeight, getPxFromPercentOfWindowWidth } from '../../Design/Sizing/getPxFromPercentOfWindow';
import { Colors } from '../../Design/Colors/Colors';
import { MoreTime } from '@mui/icons-material';
import TimedEffectsBackground from '../../Design/Images/sand.jpg';

export const DisplayTimedEffects = (parent) => {
    const height = getPxFromPercentOfWindowHeight(parent, 25);
    const width =  getPxFromPercentOfWindowWidth(parent, 66);
    return (
        <Box sx={{ minWidth: width, maxWidth: width }}>
            <Box display="flex" justifyContent="center" alignItems="center">
                <Typography variant="button" sx={{ color: Colors.timedEffectsColor}}>
                    Current effects
                </Typography>
                <Box pl="5px" pb="4px">
                    <Tooltip title="Create a new timed effect" placement="right">
                        <IconButton onClick={() => { parent.setState({ addTimedEffectModalOpen: true }) }} size="small" sx={{ color: Colors.timedEffectsColor }}>
                            <MoreTime />
                        </IconButton>
                    </Tooltip>
                </Box>
            </Box>
            <Box 
                p="5px" 
                sx={{ 
                    // minHeight: height, 
                    maxHeight: height, 
                    overflow: 'auto'
                }} 
                style={{
                    backgroundImage: `url(${TimedEffectsBackground})`,
                    backgroundPosition: 'center',
                    backgroundSize: 'cover',
                    backgroundRepeat: 'no-repeat',
                }}
                border="4px outset"
                borderRadius="10px" 
                borderColor={Colors.timedEffectsColor}
            >
                {Object.entries(parent.state.timedEffects).map( (timedEffect) => {
                    return <Box key={timedEffect}>{TimedEffect(timedEffect[1], parent)}</Box>
                })}
            </Box>
        </Box>
    );
}