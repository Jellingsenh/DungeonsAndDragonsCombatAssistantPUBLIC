import { Box, Modal } from '@mui/material';
import { DeleteGameModal } from '../Modals/DeleteGameModal';
import { BringCharacterModal } from '../Modals/BringCharacterModal';
import { BringGroupModal } from '../Modals/BringGroupModal';
import { CreateGroupModal } from '../Modals/CreateGroupModal';
import { LoadGameModal } from '../Modals/LoadGameModal';
import { RollInitiativeModal } from '../Modals/RollInitiativeModal';
import { DeleteCharacterModal } from '../Modals/DeleteCharacterModal';
import { DeleteGroupModal } from '../Modals/DeleteGroupModal';
import { InfoModal } from '../Modals/InfoModal';
import { ViewEditCharacterModal } from '../Modals/ViewEditCharacterModal';
import { CreateGameSaveModal } from '../Modals/CreateGameSaveModal';
import { EditGroupModal } from '../Modals/EditGroupModal';
import { EditingGroupModal } from '../Modals/EditingGroupModal';

export const ArenaModals = (parent) => {
    return (
        <Box>
            <Modal
                open={parent.state.infoModalOpen}
                onClose={ () => {
                    parent.setState({
                        infoModalOpen: false,
                    })
                }}
                aria-labelledby="InfoModal"
                aria-describedby="InfoModal"
            >
                {InfoModal(parent)}
            </Modal>
            <Modal
                open={parent.state.bringCharacterModalOpen}
                onClose={ () => {
                    parent.setState({
                        bringCharacterModalOpen: false,
                    })
                }}
                aria-labelledby="BringCharacterModal"
                aria-describedby="BringCharacterModal"
            >
                {BringCharacterModal(parent)}
            </Modal>
            <Modal
                open={parent.state.bringGroupModalOpen}
                onClose={ () => {
                    parent.setState({
                        bringGroupModalOpen: false,
                    })
                }}
                aria-labelledby="BringGroupModal"
                aria-describedby="BringGroupModal"
            >
                {BringGroupModal(parent)}
            </Modal>
            <Modal
                open={parent.state.createGroupModalOpen}
                onClose={ () => {
                    parent.setState({
                        createGroupModalOpen: false,
                    })
                }}
                aria-labelledby="CreateGroupModal"
                aria-describedby="CreateGroupModal"
            >
                {CreateGroupModal(parent)}
            </Modal>
            <Modal
                open={parent.state.loadGameModalOpen}
                onClose={ () => {
                    parent.setState({
                        loadGameModalOpen: false,
                    })
                }}
                aria-labelledby="LoadGameModal"
                aria-describedby="LoadGameModal"
            >
                {LoadGameModal(parent)}
            </Modal>
            <Modal
                open={parent.state.rollInitiativeModalOpen}
                onClose={ () => {
                    parent.setState({
                        rollInitiativeModalOpen: false,
                    })
                }}
                aria-labelledby="RollInitiativeModal"
                aria-describedby="RollInitiativeModal"
            >
                {RollInitiativeModal(parent)}
            </Modal>
            <Modal
                open={parent.state.deleteCharacterModalOpen}
                onClose={ () => {
                    parent.setState({
                        deleteCharacterModalOpen: false,
                    })
                }}
                aria-labelledby="DeleteCharacterModal"
                aria-describedby="DeleteCharacterModal"
            >
                {DeleteCharacterModal(parent)}
            </Modal>
            <Modal
                open={parent.state.deleteGroupModalOpen}
                onClose={ () => {
                    parent.setState({
                        deleteGroupModalOpen: false,
                    })
                }}
                aria-labelledby="DeleteGroupModal"
                aria-describedby="DeleteGroupModal"
            >
                {DeleteGroupModal(parent)}
            </Modal>
            <Modal
                open={parent.state.deleteGameModalOpen}
                onClose={ () => {
                    parent.setState({
                        deleteGameModalOpen: false,
                    })
                }}
                aria-labelledby="DeleteGameModal"
                aria-describedby="DeleteGameModal"
            >
                {DeleteGameModal(parent)}
            </Modal>
            <Modal
                open={parent.state.viewEditCharacterModalOpen}
                onClose={ () => {
                    if (parent.state.editMode) {
                        if (parent.state.createMode) {
                            if (window.confirm("Are you sure you want to close character creation?\nData may be lost.\n\nPress 'OK' to leave, or press 'Cancel' to stay here.")) {
                                parent.setState({
                                    createMode: false,
                                    viewEditCharacterModalOpen: false,
                                })
                            }
                        } else {
                            if (window.confirm("Are you sure you want to close character editing? Data may be lost.\n\nPress 'OK' to leave, or press 'Cancel' to stay here.")) {
                                parent.setState({
                                    editMode: false,
                                    viewEditCharacterModalOpen: false,
                                })
                            }
                        }
                    } else {
                        parent.setState({
                            viewEditCharacterModalOpen: false,
                        })
                        if (parent.state.gotOldName) { // if true, the name was updated
                            parent.setState({
                                gotOldName: false,
                            })
                            window.location.reload()
                        }
                    }
                }}
                aria-labelledby="ViewEditCharacterModal"
                aria-describedby="ViewEditCharacterModal"
            >
                {ViewEditCharacterModal(parent)}
            </Modal>
            <Modal
                open={parent.state.createGameSaveModalOpen}
                onClose={ () => {
                    parent.setState({
                        createGameSaveModalOpen: false,
                    })
                }}
                aria-labelledby="CreateGameSaveModal"
                aria-describedby="CreateGameSaveModal"
            >
                {CreateGameSaveModal(parent)}
            </Modal>
            <Modal
                open={parent.state.editGroupModalOpen}
                onClose={ () => {
                    parent.setState({
                        editGroupModalOpen: false,
                    })
                }}
                aria-labelledby="EditGroupModal"
                aria-describedby="EditGroupModal"
            >
                {EditGroupModal(parent)}
            </Modal>
            <Modal
                open={parent.state.editingGroupModalOpen}
                onClose={ () => {
                    parent.setState({
                        editingGroupModalOpen: false,
                    })
                }}
                aria-labelledby="EditingGroupModal"
                aria-describedby="EditingGroupModal"
            >
                {EditingGroupModal(parent)}
            </Modal>
        </Box>
    );
}