import { Box, IconButton, TextField, Tooltip } from '@mui/material';
import { Colors } from '../../Design/Colors/Colors';
import { getPxFromPercentOfWindowHeight, getPxFromPercentOfWindowWidth } from '../../Design/Sizing/getPxFromPercentOfWindow';
import { HeartBroken } from '@mui/icons-material';

export const RemoveHealthModal = (parent) => {
    const width = getPxFromPercentOfWindowWidth(parent, 35);
    const height = getPxFromPercentOfWindowHeight(parent, 25);

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

    var damage = '0';

    function setDamage(damageIn) {
        if (damageIn.length > 0) {
            damage = damageIn;
        }
    };

    return(
        <Box border="8px outset" borderRadius="15px" borderColor={Colors.removeColor} sx={modalStyle} p="5px">
            <Box display="flex" justifyContent="center">
                {`How much damage does ${parent.state.characterBeingViewed} take?`}
            </Box>
            <Box display="flex" justifyContent="center" my="20px">
                <TextField
                    type="number"
                    color={Colors.timeColor}
                    defaultValue={0}
                    label={"Damage"}
                    onChange={(damage) => {
                        setDamage(damage.target.value);
                    }}
                />
            </Box>
            <Box display="flex" justifyContent="center">
                <Tooltip title="Deal damage!">
                    <IconButton 
                        onClick={() => { 
                            parent.dealDamage(parent.state.characterBeingViewed, damage, parent.state.characterBeingViewedCurrentHealth) 
                        }}
                        sx={{ color: Colors.healthColor2 }}
                    >
                        <HeartBroken/>
                    </IconButton>
                </Tooltip>
            </Box>
        </Box>
    );
};