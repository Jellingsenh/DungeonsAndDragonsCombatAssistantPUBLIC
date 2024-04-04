import { Box, IconButton, TextField, Tooltip } from '@mui/material';
import { Colors } from '../../Design/Colors/Colors';
import { getPxFromPercentOfWindowHeight, getPxFromPercentOfWindowWidth } from '../../Design/Sizing/getPxFromPercentOfWindow';
import { Edit } from '@mui/icons-material';

export const EditLocationModal = (parent) => {
    const width = getPxFromPercentOfWindowWidth(parent, 32);
    const height = getPxFromPercentOfWindowHeight(parent, 20)

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

    var currentLocation = parent.state.location;

    function addLocation(newLocation) {
        currentLocation = newLocation;
    };

    return(
        <Box border="4px outset" borderRadius="5px" borderColor={Colors.black} sx={modalStyle} p="5px">
            <Box ml="-40px" display="flex" justifyContent="center" style={{ fontSize: 20 }}>
                Edit location:
            </Box>
            <br></br>
            <br></br>
            <Box display="flex" justifyContent="center">
                <TextField
                    color={Colors.otherColor}
                    defaultValue={currentLocation}
                    label={"Location"}
                    onChange={(location) => {
                        addLocation(location.target.value);
                    }}
                />
                <Tooltip title="Submit">
                    <IconButton
                        onClick={() => { 
                            parent.changeLocation(currentLocation) 
                        }}
                        sx={{ color: Colors.otherColor }}
                    >
                        <Edit/>
                    </IconButton>
                </Tooltip>
            </Box>
        </Box>
    );
};