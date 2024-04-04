import { Box, IconButton, Tooltip, TextField, Grid } from '@mui/material';
import { Colors } from '../../Design/Colors/Colors';
import { getPxFromPercentOfWindowHeight, getPxFromPercentOfWindowWidth } from '../../Design/Sizing/getPxFromPercentOfWindow';
import { Schedule } from '@mui/icons-material';

export const EditTimedEffectModal = (parent) => {
    const width = getPxFromPercentOfWindowWidth(parent, 55);
    const height = getPxFromPercentOfWindowHeight(parent, 35);

    // const smallWidth = getPxFromPercentOfWindowWidth(parent, 8);
    const buttonPadding = "10px";
    const smallButtonPadding = "3px";

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

    var currentEffect = parent.state.currentEditEffect;

    return(
        <Box border="5px outset" borderRadius="5px" borderColor={Colors.timedEffectsColor} sx={modalStyle} p="5px">
            <Box display="flex" justifyContent="center" style={{ fontSize: 20 }}>
                Edit timed effect:
            </Box>
            <br></br>
            <Box display="block" justifyContent="center" alignItems="center">
                <Box m={buttonPadding}>
                    <Tooltip title="You cannot change the name of a timed effect" placement="top-start">
                        <TextField
                            fullWidth
                            size="small"
                            color={Colors.timeColor}
                            value={currentEffect.name}
                            label="Name"
                            InputProps={{
                                readOnly: true,
                                style: { color: Colors.timeColor2 }
                            }}
                            // onChange={(name) => {
                            //     currentEffect.name = name.target.value;
                            //     parent.setState({
                            //         currentEditEffect: currentEffect,
                            //     });
                            // }}
                        />
                    </Tooltip>
                </Box>
                <Box m={buttonPadding}>
                    <TextField
                        fullWidth
                        size="small"
                        color={Colors.timeColor}
                        value={currentEffect.effect}
                        label="Effect"
                        onChange={(effect) => {
                            currentEffect.effect = effect.target.value;
                            parent.setState({
                                currentEditEffect: currentEffect,
                            });
                        }}
                    />
                </Box>
                <Box m={buttonPadding}>
                    <TextField
                        fullWidth
                        size="small"
                        color={Colors.timeColor}
                        value={currentEffect.targets}
                        label="Target(s)"
                        onChange={(targets) => {
                            currentEffect.targets = targets.target.value;
                            parent.setState({
                                currentEditEffect: currentEffect,
                            });
                        }}
                    />
                </Box>
                <Grid container justifyContent="center" alignItems="center">
                    <Box m={smallButtonPadding}>
                        <TextField
                            fullWidth
                            type="number"
                            size="small"
                            color={Colors.timeColor}
                            value={currentEffect.timeRemaining}
                            label="Rounds remaining"
                            InputProps={{
                                style: { color: Colors.timeColor2 }
                            }}
                            onChange={(rounds) => {
                                const round = rounds.target.value;
                                if (round.length > 0) {
                                    currentEffect.timeRemaining = round;
                                    parent.setState({
                                        currentEditEffect: currentEffect,
                                    });
                                }
                            }}
                        />
                    </Box>
                    <Tooltip title="(reference initaitve totals below)">
                        <Box m={smallButtonPadding}>
                            <TextField
                                fullWidth
                                type="number"
                                size="small"
                                color={Colors.timeColor}
                                InputProps={{
                                    style: { color: Colors.timeColor2 }
                                }}
                                value={currentEffect.endingInitiative}
                                label="Initiative"
                                onChange={(initiative) => {
                                    const init = initiative.target.value;
                                    if (init.length > 0) {
                                        currentEffect.endingInitiative = init;
                                        parent.setState({
                                            currentEditEffect: currentEffect,
                                        });
                                    }
                                }}
                            />
                        </Box>
                    </Tooltip>
                </Grid>
                <Box display="flex" justifyContent="center" alignItems="center">
                    <Tooltip title={`Edit ${currentEffect.name}`}>
                        <IconButton 
                            onClick={() => { 
                                parent.editTimedEffect(currentEffect);
                            }}
                            sx={{ color: Colors.timeColor2 }}
                        >
                            <Schedule/>
                        </IconButton>
                    </Tooltip>
                </Box>
            </Box>
        </Box>
    );
};