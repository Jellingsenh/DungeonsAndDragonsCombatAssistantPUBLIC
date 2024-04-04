import { Box, Button } from '@mui/material';
import { Colors } from '../../Design/Colors/Colors';
import { getPxFromPercentOfWindowHeight, getPxFromPercentOfWindowWidth } from '../../Design/Sizing/getPxFromPercentOfWindow';

export const EditGroupModal = (parent) => {
    const width = getPxFromPercentOfWindowWidth(parent, 25);
    const height = getPxFromPercentOfWindowHeight(parent, 18)

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
        <Box border="4px outset" borderRadius="10px" borderColor={Colors.groupColor} sx={modalStyle} p="5px">
            <Box display="flex" justifyContent="center">
                Select a group to edit:
            </Box>
            <br></br>
            <br></br>
            <Box display="flex" justifyContent="center">
                {parent.state.groupNames.length > 0 ? 
                    <Box display="flex" justifyContent="center">
                        {Object.entries(parent.state.groupNames).map( (groupName) => {
                            return (
                                <Box key={groupName} mx="5px">
                                    <Button 
                                        color={Colors.defenseColor}
                                        onClick={() => { 
                                            parent.setState({
                                                selectedGroupCharacters: [],
                                            });
                                            parent.getAllCharacters("","");
                                            parent.getGroupMembers(groupName[1]);
                                            parent.setState({
                                                groupToEdit: groupName[1],
                                                editGroupModalOpen: false,
                                                editingGroupModalOpen: true,
                                            });
                                            // console.log('groupMembers of group to edit: ', parent.state.groupMembers);
                                            // console.log('selectedGroupCharacters: ', parent.state.selectedGroupCharacters);
                                        }} 
                                        variant="contained" 
                                        size="small" 
                                    >
                                        {groupName[1]}
                                    </Button>
                                </Box>
                            );
                        })}
                    </Box>
                :
                    <>There are no groups.</>
                }
            </Box>
        </Box>
    );
};