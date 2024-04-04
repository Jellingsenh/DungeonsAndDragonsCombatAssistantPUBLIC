import { Box, IconButton, Tooltip, TextField } from '@mui/material';
import { HourglassBottom } from '@mui/icons-material';
import { Colors } from '../../Design/Colors/Colors';
import { getPxFromPercentOfWindowWidth } from '../../Design/Sizing/getPxFromPercentOfWindow';

export const TimedEffect = (effect, parent) => {
    const effectPadding = "2px";
    const miniWidth = getPxFromPercentOfWindowWidth(parent, 6);
    const width = getPxFromPercentOfWindowWidth(parent, 13);

    return (
        <Box display="flex" py="4px">
            <Tooltip title={`Edit ${effect.name}`} placement="top-start">
                <Box 
                display="flex" 
                onClick={() => { 
                    parent.setState({ 
                        currentEditEffect: effect,
                        editTimedEffectModalOpen: true,
                     })
                }}>
                    <Box p={effectPadding} sx={{ minWidth: width }}>
                        <TextField
                            focused
                            InputProps={{
                                readOnly: true,
                                style: { 
                                    color: Colors.black,
                                    fontSize: 14
                                }
                            }}
                            fullWidth
                            size="small"
                            color={Colors.timeColor}
                            label="Name"
                            value={effect.name}
                        />
                    </Box>
                    <Box p={effectPadding} sx={{ minWidth: getPxFromPercentOfWindowWidth(parent, 30) }}>
                        <TextField
                            focused
                            InputProps={{
                                readOnly: true,
                                style: { 
                                    color: Colors.timeColor2,
                                    fontSize: 14
                                }
                            }}
                            fullWidth
                            size="small"
                            color={Colors.timeColor}
                            label="Effect"
                            value={effect.effect}
                        />
                    </Box>
                    <Box p={effectPadding} sx={{ minWidth: width }}>
                        <TextField
                            focused
                            InputProps={{
                                readOnly: true,
                                style: { 
                                    color: Colors.timeColor2,
                                    fontSize: 14
                                }
                            }}
                            fullWidth
                            size="small"
                            color={Colors.timeColor}
                            label="Target(s)"
                            value={effect.targets}
                        />
                    </Box>
                    <Box p={effectPadding} sx={{ maxWidth: miniWidth, minWidth: miniWidth }}>
                        <TextField
                            focused
                            InputProps={{
                                readOnly: true,
                                style: { 
                                    color: Colors.timeColor2,
                                    fontSize: 14
                                }
                            }}
                            InputLabelProps={{ 
                                style: {
                                    fontSize: 12
                                } 
                            }}
                            fullWidth
                            size="small"
                            color={Colors.timeColor}
                            label="Rounds left"
                            value={effect.timeRemaining}
                        />
                    </Box>
                </Box>
            </Tooltip>
            <Box py={effectPadding} pl="7px">
                <Tooltip title={`End ${effect.name} now`} placement="right">
                    <IconButton onClick={() => { parent.endTimedEffect(effect.name) }} size="small" sx={{ color: Colors.removeColor }}>
                        <HourglassBottom/>
                    </IconButton>
                </Tooltip>
            </Box>
        </Box>
    );
}