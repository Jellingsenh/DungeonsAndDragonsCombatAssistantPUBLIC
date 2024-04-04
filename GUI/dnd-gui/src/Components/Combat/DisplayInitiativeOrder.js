import { Box, IconButton, Tooltip, Typography } from '@mui/material';
import { InitiativeCharacter } from './InitiativeCharacter';
import { getPxFromPercentOfWindowHeight, getPxFromPercentOfWindowWidth } from '../../Design/Sizing/getPxFromPercentOfWindow';
import { Colors } from '../../Design/Colors/Colors';
import { AirlineSeatReclineExtra, EditNote, Forward, TransitEnterexit} from '@mui/icons-material';
import InitiativeBackground from '../../Design/Images/marble.jpg';

export const DisplayInitiativeOrder = (parent) => {
    const width = getPxFromPercentOfWindowWidth(parent, 96);
    return (
        <>
            <Box display="flex" justifyContent="space-between" alignItems="center">
                <Box pl="12px" pb="4px">
                    <Tooltip title="Next turn" placement="right">
                        <IconButton onClick={() => {parent.nextTurn()}} size="small" sx={{ color: Colors.displayColor2 }}>
                            <Forward />
                        </IconButton>
                    </Tooltip>
                </Box>
                <Box pl="50px" display="flex" justifyContent="center" alignItems="center">
                    <Typography variant="button" sx={{ color: Colors.combatColor }}>
                        Initiative order
                    </Typography>
                    <Box pl="5px" pb="4px">
                        <Tooltip title="Edit initiative order" placement="left">
                            <IconButton onClick={() => { parent.setState({ editInitiativeModalOpen: true })}} size="small" sx={{ color: Colors.timeColor2 }}>
                                <EditNote />
                            </IconButton>
                        </Tooltip>
                    </Box>
                </Box>
                <Box pr="16px" pb="4px">
                    <Tooltip title="Bring in a character from the sideline" placement="left">
                        <IconButton 
                            onClick={() => {
                                parent.setState({
                                    bringInSidelineCharacterModalOpen: true,
                                });
                            }}
                            size="small" 
                            sx={{ color: Colors.defenseColor2 }}
                        >
                            <TransitEnterexit /> <AirlineSeatReclineExtra />
                        </IconButton>
                    </Tooltip>
                </Box>
            </Box>
            <Box
                p="10px" 
                pr="25px"
                sx={{ 
                    maxHeight:  getPxFromPercentOfWindowHeight(parent, 66), 
                    minWidth: width, 
                    maxWidth: width, 
                    overflow: 'auto'
                }}
                style={{
                    backgroundImage: `url(${InitiativeBackground})`,
                    backgroundPosition: 'center',
                    backgroundSize: 'cover',
                    backgroundRepeat: 'no-repeat',
                }}
                border="4px outset" 
                borderRadius="15px" 
                borderColor={Colors.combatColor}
            >   
                {Object.entries(parent.state.initiativeOrder).map( (initiativeCharacter) => {
                    return <Box key={initiativeCharacter}>{InitiativeCharacter(initiativeCharacter[1], parent)}</Box>;
                })}
            </Box>
        </>
    );
}