import { Box } from '@mui/material';
import { Colors } from '../../Design/Colors/Colors';
import { getPxFromPercentOfWindowHeight, getPxFromPercentOfWindowWidth } from '../../Design/Sizing/getPxFromPercentOfWindow';

export const CombatRemindersModal = (parent) => {
    const width = getPxFromPercentOfWindowWidth(parent, 56);
    const height = getPxFromPercentOfWindowHeight(parent, 33)
    const width2 = getPxFromPercentOfWindowWidth(parent, 46);

    const modal1Style = {
        position: 'absolute',
        top: '28%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        bgcolor: 'background.paper',
        overflow: 'auto',
        maxHeight:  height,
        minHeight: height, 
        minWidth: width, 
        maxWidth: width,
    };

    const modal2Style = {
        position: 'absolute',
        top: '70%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        bgcolor: 'background.paper',
        overflow: 'auto',
        maxHeight:  height,
        minHeight: height,
        minWidth: width2,
        maxWidth: width2,
    };

    return(
        <Box display="flex" justifyContent="center" alignItems="center">
            <Box border="4px outset" borderRadius="10px" borderColor={Colors.combatColor} sx={modal1Style} p="15px">
            <Box style={{ fontWeight: "bold", fontSize: "30px", fontFamily: "trebuchet" }} display="flex" justifyContent="center">
                Combat reminders:
            </Box>
            <Box ml="110px" mt="15px" display="flex" justifyContent="center" alignItems="center" gap={5}>
                <Box style={{ fontSize: "20px" }}>
                    Announce current & on-deck players.<br></br>
                    <br></br>
                    When a player uses inspiration, ask for a description of their actions.<br></br>
                    <br></br>
                    Cover gives +4 AC & +2 Reflex saves (can be more or less).<br></br>
                    <br></br>
                    It's ok to estimate or hand-wave character movement and let them move further than they should sometimes.<br></br>
                    <br></br>
                    Don't forget about enemy & NPC reactions in combat (this includes enemies retreating from combat)!<br></br>
                    <br></br>
                </Box>
            </Box>
            </Box>
            <Box alignItems="center" border="4px outset" borderRadius="10px" borderColor={Colors.timeColor2} sx={modal2Style} p="15px">
                <Box style={{ fontWeight: "bold", fontSize: "30px" }} display="flex" justifyContent="center">
                Alternate combat goals (to help keep combat interesting):
                </Box><br></br>
                <Box mt="10px" display="flex" justifyContent="center" alignItems="center" gap={5}>
                    <Box style={{ fontSize: "20px" }}>
                        1) An enemy you must capture (or an enemy you cannot kill)<br></br>
                        <br></br>
                        2) Retrive or chase an object (without destroying it)<br></br>
                        <br></br>
                        3) Earn the crowd's favor (as an alternate win condition)<br></br>
                        <br></br>
                        4) King of the hill (stay in one place or force an enemy out)<br></br>
                        <br></br>
                        5) Countdown to something (combat on a timer)<br></br>
                    </Box>
                </Box>
            </Box>
        </Box>
    );
};
