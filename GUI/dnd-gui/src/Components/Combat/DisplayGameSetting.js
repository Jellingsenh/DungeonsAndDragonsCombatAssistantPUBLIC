import { Box, Tooltip, IconButton, TextField } from '@mui/material';
import { Restore, Add, Remove } from '@mui/icons-material';
import { Colors } from '../../Design/Colors/Colors';
import { getPxFromPercentOfWindowWidth } from '../../Design/Sizing/getPxFromPercentOfWindow';

export const DisplayGameSetting = (parent) => {
    const settingPadding = "8px";
    const widthNumber = 27;
    const width = getPxFromPercentOfWindowWidth(parent, widthNumber);
    const smallerWidth = getPxFromPercentOfWindowWidth(parent, widthNumber-4);
    return (
        <Box sx={{minWidth: width, maxWidth: width}}>
            <Box display="flex" justifyContent="center" alignItems="center">
                <Box pb="10px">
                    <Tooltip title="Reset time" placement="left">
                        <IconButton onClick={() => { parent.resetTime() }} sx={{ color: Colors.deleteColor }}>
                            <Restore />
                        </IconButton>
                    </Tooltip>
                </Box>
                <Box pb={settingPadding} sx={{minWidth: smallerWidth, maxWidth: smallerWidth}}>
                    <Tooltip title="Edit time details" placement="top-start">
                        <TextField
                            onClick={() => {
                                parent.getTimeDetails();
                                parent.setState({
                                    gotTimeDetails: false,
                                    editTimeModalOpen: true,
                                });
                            }}
                            focused
                            InputProps={{
                                readOnly: true,
                                style: { color: Colors.black, fontWeight: "bold" }
                            }}
                            fullWidth
                            color={Colors.timeColor}
                            label="Current Time"
                            value={parent.state.time}
                        />
                    </Tooltip>
                </Box>
                <Box pb="8px">
                    <Tooltip title="Add time" placement="right">
                        <IconButton 
                            onClick={() => {
                                parent.setState({
                                    addTimeModalOpen: true
                                })
                            }}
                            size="small" 
                            sx={{ color: Colors.timeColor2 }}>
                            <Add />
                        </IconButton>
                    </Tooltip>
                    <Tooltip title="Subtract time" placement="right">
                        <IconButton 
                            onClick={() => {
                                parent.setState({
                                    subtractTimeModalOpen: true
                                })
                            }} 
                            size="small"
                            sx={{ color: Colors.removeColor }}>
                            <Remove />
                        </IconButton>
                    </Tooltip>
                </Box>
            </Box>
            <Box pb={settingPadding}>
                <Tooltip title="Edit location" placement="bottom-start">
                    <TextField
                        onClick={() => {
                            parent.setState({
                                editLocationModalOpen: true,
                            });
                        }}
                        focused
                        InputProps={{
                            readOnly: true,
                            style: { color: Colors.black, fontWeight: "bold" }
                        }}
                        fullWidth
                        size="small"
                        color={Colors.otherColor}
                        label="Location"
                        value={parent.state.location}
                    />
                </Tooltip>
            </Box>
            <Tooltip title="Edit environment" placement="bottom-start">
                <TextField
                    onClick={() => {
                        parent.setState({
                            editEnvironmentModalOpen: true,
                        });
                    }}
                    focused
                    InputProps={{
                        readOnly: true,
                        style: { color: Colors.black, fontWeight: "bold" }
                    }}
                    fullWidth
                    size="small"
                    color={Colors.otherColor}
                    label="Environment"
                    value={parent.state.environment}
                />
            </Tooltip>
        </Box>
    );
}