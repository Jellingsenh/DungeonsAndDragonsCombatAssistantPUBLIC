import { Box, CircularProgress, IconButton, TextField, Tooltip } from '@mui/material';
import { Colors } from '../../Design/Colors/Colors';
import { getPxFromPercentOfWindowHeight, getPxFromPercentOfWindowWidth } from '../../Design/Sizing/getPxFromPercentOfWindow';
import { Add, CalendarMonth, Remove } from '@mui/icons-material';

export const EditTimeModal = (parent) => {
    const width = getPxFromPercentOfWindowWidth(parent, 80);
    const height = getPxFromPercentOfWindowHeight(parent, 40);
    // const smallButtonPadding = "5px";
    const smallWidth = getPxFromPercentOfWindowWidth(parent, 12);
    // const smallHeight = getPxFromPercentOfWindowHeight(parent, 20);


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

    return(
        <Box border="5px outset" borderRadius="10px" borderColor={Colors.timeColor2} sx={modalStyle} p="15px">
            <Box display="flex" justifyContent="center" style={{ fontSize: 20 }}>
                Edit time details:
            </Box>
            <br></br>
            {/* year names, days in a year, number of months, month names & # of days (auto-set will divide evenly) */}
            {parent.state.gotTimeDetails ? 
                <Box display="block" justifyContent="center" alignItems="center">
                <Box display="flex" justifyContent="center" gap={2}>
                    <Tooltip title={`(example: C.E., or Common Era)`} placement="top-start">
                        <TextField
                            fullWidth
                            focused
                            InputProps={{
                                style: { color: Colors.attackColor2 }
                            }}
                            color={Colors.attackColor}
                            label="Year notation"
                            defaultValue={parent.state.yearNotation}
                            onChange={(e) => {
                                parent.setState({
                                    yearNotation: e.target.value,
                                });
                            }}
                        />
                    </Tooltip>
                    <TextField
                        type="number"
                        focused
                        color={Colors.timeColor}
                        defaultValue={parent.state.daysInYear}
                        label="Days in a year"
                        onChange={(e) => {
                            const days = e.target.value;
                            if (days > 0) {
                                parent.setState({
                                    daysInYear: days,
                                });
                            }
                        }}
                    />
                </Box>
                <Box display="flex" sx={{ overflow: 'auto'}}>
                    {/* {console.log('-----------------------months:')} */}
                    {Object.entries(parent.state.monthsInYear).map( (month) => {
                        // console.log('month: ', month[1]);
                        return <Box key={month} mx="5px" my="25px" p="15px" border="2px outset" borderRadius="10px" borderColor={Colors.black} alignItems="center" minWidth={smallWidth} maxWidth={smallWidth}>
                            <TextField
                                fullWidth
                                focused
                                InputProps={{
                                    style: { color: Colors.defenseColor2 }
                                }}
                                color={Colors.defenseColor}
                                label="Month name"
                                value={month[1].name}
                                onChange={(e) => {
                                    // month[1].name = e.target.value;
                                    var newMonths = parent.state.monthsInYear;
                                    for (var i = 0; i < newMonths.length; i++) {
                                        if (newMonths[i].name === month[1].name) {
                                            newMonths[i].name = e.target.value;
                                        }
                                    }
                                    parent.setState({
                                        monthsInYear: newMonths,
                                    })
                                }}
                            />
                            <Box mt="15px" display="flex">
                                <Tooltip title="Leaving this value as 0 will let the program auto-set the days in the month, using up the unused days of the year.">
                                    <TextField
                                        type="number"
                                        fullWidth
                                        focused
                                        color={Colors.timeColor}
                                        value={month[1].days}
                                        label="Days in the month"
                                        onChange={(e) => {
                                            var newMonths = parent.state.monthsInYear;
                                            for (var i = 0; i < newMonths.length; i++) {
                                                if (newMonths[i].name === month[1].name) {
                                                    newMonths[i].days = e.target.value;
                                                }
                                            }
                                            parent.setState({
                                                monthsInYear: newMonths,
                                            })
                                        }}
                                    />
                                </Tooltip>
                                <Tooltip title={`Remove ${month[1].name}`}>
                                    <IconButton 
                                        onClick={() => { 
                                            var newMonths = parent.state.monthsInYear.filter(function(m) { 
                                                return m.name !== month[1].name;
                                            });
                                            parent.setState({
                                                monthsInYear: newMonths,
                                            });
                                        }}
                                        sx={{ color: Colors.timeColor2 }}
                                    >
                                        <Remove/>
                                    </IconButton>
                                </Tooltip>
                            </Box>
                        </Box>
                    })}
                    <Tooltip title={`Add a month`}>
                        <IconButton 
                            onClick={() => {                 
                                parent.setState({
                                    monthsInYear: [...parent.state.monthsInYear, {name: `Month ${parent.state.monthsInYear.length + 1}`, days: 0}]
                                });
                            }}
                            sx={{ color: Colors.timeColor2 }}
                        >
                            <Add/>
                        </IconButton>
                    </Tooltip>
                </Box>
                <Box display="flex" justifyContent="center" alignItems="center" mt="10px">
                    <Tooltip title={`Save time details`}>
                        <IconButton 
                            onClick={() => { 
                                parent.saveTimeDetails();
                            }}
                            sx={{ color: Colors.timeColor2 }}
                        >
                            <CalendarMonth/>
                        </IconButton>
                    </Tooltip>
                </Box>
            </Box> 
        : 
            <Box display="flex" justifyContent="center" alignItems="center" mt="20%">
                <CircularProgress />
            </Box>
        }
        </Box>
    );
};