import { Box } from '@mui/material';
import { Colors } from '../../Design/Colors/Colors';
import { getPxFromPercentOfWindowHeight, getPxFromPercentOfWindowWidth } from '../../Design/Sizing/getPxFromPercentOfWindow';

export const InfoModal = (parent) => {
    const width = getPxFromPercentOfWindowWidth(parent, 80);
    const width2 = getPxFromPercentOfWindowWidth(parent, 55);
    const height = getPxFromPercentOfWindowHeight(parent, 34)
    const height2 = getPxFromPercentOfWindowHeight(parent, 30)

    const modal1Style = {
        position: 'absolute',
        top: '30%',
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
        maxHeight:  height2,
        minHeight: height2, 
        minWidth: width2, 
        maxWidth: width2,
    };

    return(
        <Box display="flex" justifyContent="center" alignItems="center">
            <Box border="4px outset" borderRadius="10px" borderColor={Colors.attackColor2} sx={modal1Style} p="15px">
            <Box style={{ fontWeight: "bold", fontSize: "30px", fontFamily: "trebuchet" }} display="flex" justifyContent="center">
                DM roles:
            </Box>
            <Box ml="110px" mt="15px" display="flex" justifyContent="center" alignItems="center" gap={5}>
                <Box style={{ fontSize: "18px" }}>
                    <Box display="inline-block" style={{ fontWeight: "bold", fontSize: "20px" }}>1) Narrator</Box><br></br>
                    &nbsp;&nbsp;&nbsp;&nbsp;a) Describe senses (sight, smell, sound, etc).<br></br>
                    &nbsp;&nbsp;&nbsp;&nbsp;b) Don't ask "what are you doing?", just describe the setting & the NPCs' actions.<br></br>
                    <br></br>
                    &nbsp;&nbsp;&nbsp;&nbsp;c) Let the party talk to each other, don't always respond to questions.<br></br>
                    &nbsp;&nbsp;&nbsp;&nbsp;d) Call for skill checks when needed, don't make them ask you.<br></br>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;i) an auto-pass roll determines how long the task takes.<br></br>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ii) an auto-fail roll determines if a new possibility is discovered.<br></br>
                    <br></br>
                    &nbsp;&nbsp;&nbsp;&nbsp;e) Have players describe what they do or say from their characters' perspectives.<br></br>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;i) for asked metagame questions, do memory checks (unless it's clarification).<br></br>
                </Box>
                <Box style={{ fontSize: "18px" }}>
                    <Box display="inline-block" style={{ fontWeight: "bold", fontSize: "20px" }}>2) NPC (friends & enemies)</Box><br></br>
                    &nbsp;&nbsp;&nbsp;&nbsp;a) Get in character, stay in character, only break to answer setting questions & request rolls.<br></br>
                    &nbsp;&nbsp;&nbsp;&nbsp;b) Show their reactions (both in & out of combat, and lair actions too).<br></br>
                    <br></br>
                    <Box display="inline-block" style={{ fontWeight: "bold", fontSize: "20px" }}>3) Party Unifier</Box><br></br>
                    &nbsp;&nbsp;&nbsp;&nbsp;a) Call back to anyone who gets cut off while talking (this is essential in online sessions).<br></br>
                    &nbsp;&nbsp;&nbsp;&nbsp;b) Play party tavern games vs each other or NPCs.<br></br>
                    &nbsp;&nbsp;&nbsp;&nbsp;c) Do overlapping night watches for 1-on-1 convos.<br></br>
                    &nbsp;&nbsp;&nbsp;&nbsp;d) Have NPCs inquire about quiet party members.<br></br>
                    <br></br>
                    <Box display="inline-block" style={{ fontWeight: "bold", fontSize: "20px" }}>Don’t forget to give inspiration points!</Box><br></br>
                </Box>
            </Box>
            </Box>
            <Box alignItems="center" border="4px outset" borderRadius="10px" borderColor={Colors.defenseColor2} sx={modal2Style} p="15px">
                <Box style={{ fontWeight: "bold", fontSize: "30px" }} display="flex" justifyContent="center">
                    Helpful reminders:
                </Box><br></br>
                <Box mt="10px" display="flex" justifyContent="center" alignItems="center" gap={5}>
                    <Box display="flex" justifyContent="center" alignItems="center" style={{ fontSize: "18px" }}>
                        Firearms exist but are counteracted by MMTs & Ignis.<br></br>
                        <br></br>
                        The Internet exists (the IUCP) but is controlled by the U.C.C.<br></br>
                        <br></br>
                        <br></br>
                        A 30 ft walking speed is 3 miles per hour.<br></br>
                        A 30 ft hustling speed is 6 miles per hour.<br></br>
                        A character with a 30 ft walking speed can walk 24 miles per day.<br></br>
                        A horse-drawn wagon can travel 16 miles per horse per day.<br></br>
                    </Box>
                    <Box display="flex" justifyContent="center" alignItems="center" style={{ fontSize: "18px" }}>
                        A rowboat can travel 24 miles per day.<br></br>
                        A heavy ship can travel 100 miles per day.<br></br>
                        A light ship can travel 200 miles per day.<br></br>
                        A steamboat can travel 400 miles per day.<br></br>
                        A speedboat can travel 1,000 miles per day.<br></br>
                        A car can travel 1,000 miles per day.<br></br>
                        An airship can travel 500 miles per day.<br></br>
                        A non-Erlian spaceship can travel 1 AG per day.<br></br>
                        An Erlian/Auroran speed spaceship can travel 24 AG per day.<br></br>
                    </Box>
                </Box>
            </Box>
        </Box>
    );
};
