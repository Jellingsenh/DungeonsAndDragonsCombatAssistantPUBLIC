import { Box, IconButton, Tooltip, TextField, Grid } from '@mui/material';
import { Colors } from '../../Design/Colors/Colors';
import { getPxFromPercentOfWindowHeight, getPxFromPercentOfWindowWidth } from '../../Design/Sizing/getPxFromPercentOfWindow';
import { Schedule } from '@mui/icons-material';

export const AddTimedEffectModal = (parent) => {
    const width = getPxFromPercentOfWindowWidth(parent, 55);
    const height = getPxFromPercentOfWindowHeight(parent, 30);

    const smallWidth = getPxFromPercentOfWindowWidth(parent, 8);
    const buttonPadding = "5px";
    const smallButtonPadding = "3px";

    const modalStyle = {
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        bgcolor: 'background.paper',
        overflow: 'auto',
        maxHeight:  height,
        // minHeight: height, 
        minWidth: width, 
        maxWidth: width,
    };

    var newEffect = {
        name: "",
        effect: "",
        targets: "",
        timeRemaining: {
            rounds: 0,
            minutes: 0,
            hours: 0,
            days: 0,
            years: 0,
        },
        endingInitiative: 0,
    };

    return(
        <Box border="4px outset" borderRadius="15px" borderColor={Colors.timedEffectsColor} sx={modalStyle} p="15px">
            <Box display="flex" justifyContent="center" style={{ fontSize: 20 }}>
                New timed effect:
            </Box>
            <Box display="block" justifyContent="center" alignItems="center">
                <Box m={buttonPadding}>
                    <TextField
                        fullWidth
                        size="small"
                        color={Colors.timeColor}
                        defaultValue=""
                        label="Name"
                        onChange={(name) => {
                            newEffect.name = name.target.value;
                        }}
                    />
                </Box>
                <Box m={buttonPadding}>
                    <TextField
                        fullWidth
                        size="small"
                        color={Colors.timeColor}
                        defaultValue=""
                        label="Effect"
                        onChange={(effect) => {
                            newEffect.effect = effect.target.value;
                        }}
                    />
                </Box>
                <Box m={buttonPadding}>
                    <TextField
                        fullWidth
                        size="small"
                        color={Colors.timeColor}
                        defaultValue=""
                        label="Target(s)"
                        onChange={(targets) => {
                            newEffect.targets = targets.target.value;
                        }}
                    />
                </Box>
                <Grid container justifyContent="center" alignItems="center">
                    <Box m={smallButtonPadding} sx={{ maxWidth: smallWidth}}>
                        <TextField
                            type="number"
                            size="small"
                            color={Colors.timeColor}
                            defaultValue={0}
                            label="Rounds"
                            onChange={(rounds) => {
                                const round = rounds.target.value;
                                if (round.length > 0) {
                                    newEffect.timeRemaining.rounds = round;
                                }
                            }}
                        />
                    </Box>
                    <Box m={smallButtonPadding} sx={{ maxWidth: smallWidth}}>
                        <TextField
                            type="number"
                            size="small"
                            color={Colors.timeColor}
                            defaultValue={0}
                            label="Minutes"
                            onChange={(minutes) => {
                                const minute = minutes.target.value;
                                if (minute.length > 0) {
                                    newEffect.timeRemaining.minutes = minute;
                                }
                            }}
                        />
                    </Box>
                    <Box m={smallButtonPadding} sx={{ maxWidth: smallWidth}}>
                        <TextField
                            type="number"
                            size="small"
                            color={Colors.timeColor}
                            defaultValue={0}
                            label="Hours"
                            onChange={(hours) => {
                                const hour = hours.target.value;
                                if (hour.length > 0) {
                                    newEffect.timeRemaining.hours = hour;
                                }
                            }}
                        />
                    </Box>
                    <Box m={smallButtonPadding} sx={{ maxWidth: smallWidth}}>
                        <TextField
                            type="number"
                            size="small"
                            color={Colors.timeColor}
                            defaultValue={0}
                            label="Days"
                            onChange={(days) => {
                                const day = days.target.value;
                                if (day.length > 0) {
                                    newEffect.timeRemaining.days = day;
                                }
                            }}
                        />
                    </Box>
                    <Box m={smallButtonPadding} sx={{ maxWidth: smallWidth}}>
                        <TextField
                            type="number"
                            size="small"
                            color={Colors.timeColor}
                            defaultValue={0}
                            label="Years"
                            onChange={(years) => {
                                const year = years.target.value;
                                if (year.length > 0) {
                                    newEffect.timeRemaining.years = year;
                                }
                            }}
                        />
                    </Box>
                    <Tooltip title="(reference initaitve totals below)">
                        <Box m={smallButtonPadding} sx={{ maxWidth: smallWidth }}>
                            <TextField
                                type="number"
                                size="small"
                                color={Colors.timeColor}
                                defaultValue={0}
                                label="Initiative"
                                onChange={(initiative) => {
                                    const init = initiative.target.value;
                                    if (init.length > 0) {
                                        newEffect.endingInitiative = init;
                                    }
                                }}
                            />
                        </Box>
                    </Tooltip>
                </Grid>
                <Box display="flex" justifyContent="center" alignItems="center">
                    <Tooltip title={`Start ${newEffect.name}`}>
                        <IconButton 
                            onClick={() => { 
                                parent.addTimedEffect(newEffect);
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