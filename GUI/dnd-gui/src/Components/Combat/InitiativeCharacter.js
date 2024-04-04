import { Box, IconButton, Tooltip, TextField } from '@mui/material';
import { AirlineSeatReclineExtra } from '@mui/icons-material';
import { Colors } from '../../Design/Colors/Colors';
import { getPxFromPercentOfWindowWidth } from '../../Design/Sizing/getPxFromPercentOfWindow';

export const InitiativeCharacter = (character, parent) => {
    const initiativePadding = "2px";
    const smallestWidth = getPxFromPercentOfWindowWidth(parent, 4);
    const width = getPxFromPercentOfWindowWidth(parent, 12);
    const largestWidth = getPxFromPercentOfWindowWidth(parent, 32);

    var nameSize = "small";
    var nameColor = Colors.displayColor;
    var nameColor2 = Colors.displayColor2;
    var nameFontSize = 14;

    if (parent.state.currentCharacter === character.name)  {
        nameSize = "large";
        nameColor = Colors.attackColor;
        nameColor2 = Colors.attackColor2;
        nameFontSize = 18;
    } else if (parent.state.nextCharacter === character.name) {
        nameColor = Colors.timeColor;
        nameColor2 = Colors.timeColor2;
        nameFontSize = 16;
    }

    return (
        <Box display="flex" py="4px" alignItems="center">
            <Box p={initiativePadding} sx={{ minWidth: width, maxWidth: width }}>
                <Tooltip title={`View ${character.name}`} placement="top">
                    <TextField
                        onClick={() => { parent.getCharacter(character.name) }}
                        focused
                        InputProps={{ 
                            readOnly: true,
                            style: {
                                color: nameColor2,
                                fontSize: nameFontSize,
                            }
                        }}
                        InputLabelProps={{ 
                            style: {
                                fontSize: 14
                            } 
                        }}
                        fullWidth
                        size={nameSize}
                        color={nameColor}
                        label="Name"
                        value={character.name}
                    />
                </Tooltip>
            </Box>
            <Box p={initiativePadding} sx={{ minWidth: smallestWidth, maxWidth: smallestWidth }}>
                <Tooltip title={`Deal damage to ${character.name}`} placement="top">
                    <TextField
                        focused
                        onClick={() => {
                            parent.setState({
                                characterBeingViewed: character.name,
                                characterBeingViewedCurrentHealth: character.currentHealth,
                                removeHealthModalOpen: true,
                            });
                        }}
                        InputProps={{
                            inputProps: { style: { textAlign: 'center' } },
                            readOnly: true,
                            style: {
                                color: Colors.healthColor2,
                                fontSize: 14
                            }
                        }}
                        InputLabelProps={{
                            style: {
                                fontSize: 15
                            }
                        }}
                        fullWidth
                        size="small"
                        color={Colors.healthColor}
                        label="Health"
                        value={character.currentHealth}
                    />
                </Tooltip>
            </Box>
            <Box display="flex" onClick={() => { parent.getCharacter(character.name) }}>
                <Box p={initiativePadding} sx={{ minWidth: smallestWidth, maxWidth: smallestWidth }}>
                    <TextField
                        focused
                        InputProps={{
                            inputProps: { style: { textAlign: 'center' } },
                            readOnly: true,
                            style: { 
                                color: Colors.defenseColor2,
                                fontSize: 14
                            }
                        }}
                        InputLabelProps={{ 
                            style: {
                                fontSize: 15
                            } 
                        }}
                        fullWidth
                        size="small"
                        color={Colors.defenseColor}
                        label="Armor"
                        value={character.armorClass}
                    />
                </Box>
                <Box p={initiativePadding} sx={{ minWidth: largestWidth, maxWidth: largestWidth }}>
                    <TextField
                        focused
                        InputProps={{
                            readOnly: true,
                            style: { 
                                color: Colors.attackColor2,
                                fontSize: 14
                            }
                        }}
                        InputLabelProps={{ 
                            style: {
                                fontSize: 14
                            } 
                        }}
                        fullWidth
                        size="small"
                        color={Colors.attackColor}
                        label="Main Attacks"
                        value={character.mainAttacksAndSpells}
                    />
                </Box>
                <Box p={initiativePadding} sx={{ minWidth: width, maxWidth: width }}>
                    <TextField
                        focused
                        InputProps={{
                            readOnly: true,
                            style: { 
                                color: Colors.defenseColor2,
                                fontSize: 14
                            }
                        }}
                        InputLabelProps={{ 
                            style: {
                                fontSize: 14
                            } 
                        }}
                        fullWidth
                        size="small"
                        color={Colors.defenseColor}
                        label="Resistances"
                        value={character.resistances}
                    />
                </Box>
                <Box p={initiativePadding} sx={{ minWidth: width, maxWidth: width }}>
                    <TextField
                        focused
                        InputProps={{
                            readOnly: true,
                            style: { 
                                color: Colors.healthColor2,
                                fontSize: 14
                            }
                        }}
                        InputLabelProps={{ 
                            style: {
                                fontSize: 14
                            } 
                        }}
                        fullWidth
                        size="small"
                        color={Colors.healthColor}
                        label="Vulnerabilities"
                        value={character.vulnerabilities}
                    />
                </Box>
                <Box p={initiativePadding} sx={{ minWidth: width, maxWidth: width }}>
                    <TextField
                        focused
                        InputProps={{
                            readOnly: true,
                            style: { 
                                color: Colors.defenseColor2,
                                fontSize: 14
                            }
                        }}
                        InputLabelProps={{ 
                            style: {
                                fontSize: 14
                            } 
                        }}
                        fullWidth
                        size="small"
                        color={Colors.defenseColor}
                        label="Reactions"
                        value={character.reactionsOrLegendaryActions}
                    />
                </Box>
                <Box p={initiativePadding} sx={{ minWidth: smallestWidth, maxWidth: smallestWidth }}>
                    <TextField
                        focused
                        InputProps={{
                            inputProps: { style: { textAlign: 'center' } },
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
                        label="Initiative"
                        value={character.initiativeTotal}
                    />
                </Box>
            </Box>
            <Box py={initiativePadding} ml="10px">
                <Tooltip title={`Move ${character.name} to the sideline`} placement="left">
                    <IconButton 
                        onClick={() => {
                            parent.moveCharacterToSideline(character.name)
                        }}
                        size="small" 
                        sx={{ color: Colors.removeColor }}
                    >
                        <AirlineSeatReclineExtra />
                    </IconButton>
                </Tooltip>
            </Box>
        </Box>
    );
}
