import { Box, Modal } from '@mui/material';
import { RemoveHealthModal } from '../Modals/RemoveHealthModal';
import { AddTimedEffectModal } from '../Modals/AddTimedEffectModal';
import { EditInitiativeModal } from '../Modals/EditInitiativeModal';
import { AddTimeModal } from '../Modals/AddTimeModal';
import { SubtractTimeModal } from '../Modals/SubtractTimeModal';
import { EditEnvironmentModal } from '../Modals/EditEnvironmentModal';
import { EditLocationModal } from '../Modals/EditLocationModal';
import { ViewEditCharacterModal } from '../Modals/ViewEditCharacterModal';
import { CombatRemindersModal } from '../Modals/CombatRemindersModal';
import { BringInSidelineCharacterModal } from '../Modals/BringInSidelineCharacterModal';
import { EditTimedEffectModal } from '../Modals/EditTimedEffectModal';
import { EditTimeModal } from '../Modals/EditTimeModal';

export const CombatModals = (parent) => {
    return (
        <Box>
            <Modal
                open={parent.state.combatRemindersModalOpen}
                onClose={ () => {
                    parent.setState({
                        combatRemindersModalOpen: false,
                    })
                }}
                aria-labelledby="CombatRemindersModal"
                aria-describedby="CombatRemindersModal"
            >
                {CombatRemindersModal(parent)}
            </Modal>
            <Modal
                open={parent.state.removeHealthModalOpen}
                onClose={ () => {
                    parent.setState({
                        removeHealthModalOpen: false,
                    })
                }}
                aria-labelledby="RemoveHealthModal"
                aria-describedby="RemoveHealthModal"
            >
                {RemoveHealthModal(parent)}
            </Modal>
            <Modal
                open={parent.state.addTimedEffectModalOpen}
                onClose={ () => {
                    parent.setState({
                        addTimedEffectModalOpen: false,
                    })
                }}
                aria-labelledby="AddTimedEffectModal"
                aria-describedby="AddTimedEffectModal"
            >
                {AddTimedEffectModal(parent)}
            </Modal>
            <Modal
                open={parent.state.editInitiativeModalOpen}
                onClose={ () => {
                    parent.setState({
                        editInitiativeModalOpen: false,
                    })
                }}
                aria-labelledby="EditInitiativeModal"
                aria-describedby="EditInitiativeModal"
            >
                {EditInitiativeModal(parent)}
            </Modal>
            <Modal
                open={parent.state.addTimeModalOpen}
                onClose={ () => {
                    parent.setState({
                        addTimeModalOpen: false,
                    })
                }}
                aria-labelledby="AddTimeModal"
                aria-describedby="AddTimeModal"
            >
                {AddTimeModal(parent)}
            </Modal>
            <Modal
                open={parent.state.subtractTimeModalOpen}
                onClose={ () => {
                    parent.setState({
                        subtractTimeModalOpen: false,
                    })
                }}
                aria-labelledby="SubtractTimeModal"
                aria-describedby="SubtractTimeModal"
            >
                {SubtractTimeModal(parent)}
            </Modal>
            <Modal
                open={parent.state.editLocationModalOpen}
                onClose={ () => {
                    parent.setState({
                        editLocationModalOpen: false,
                    })
                }}
                aria-labelledby="EditLocationModal"
                aria-describedby="EditLocationModal"
            >
                {EditLocationModal(parent)}
            </Modal>
            <Modal
                open={parent.state.editEnvironmentModalOpen}
                onClose={ () => {
                    parent.setState({
                        editEnvironmentModalOpen: false,
                    })
                }}
                aria-labelledby="EditEnvironmentModal"
                aria-describedby="EditEnvironmentModal"
            >
                {EditEnvironmentModal(parent)}
            </Modal>
            <Modal
                open={parent.state.viewEditCharacterModalOpen}
                onClose={ () => {
                    if (parent.state.editMode) {
                        if (window.confirm("Are you sure you want to close character editing? Data may be lost.\n\nPress 'OK' to leave, or press 'Cancel' to stay here.")) {
                            parent.setState({
                                viewEditCharacterModalOpen: false,
                            })
                        }
                    } else {
                        parent.setState({
                            viewEditCharacterModalOpen: false,
                        })
                    }
                }}
                aria-labelledby="ViewEditCharacterModal"
                aria-describedby="ViewEditCharacterModal"
            >
                {ViewEditCharacterModal(parent)}
            </Modal>
            <Modal
                open={parent.state.editTimeModalOpen}
                onClose={ () => {
                    parent.setState({
                        editTimeModalOpen: false,
                    })
                }}
                aria-labelledby="EditTimeModal"
                aria-describedby="EditTimeModal"
            >
                {EditTimeModal(parent)}
            </Modal>
            <Modal
                open={parent.state.editTimedEffectModalOpen}
                onClose={ () => {
                    parent.setState({
                        editTimedEffectModalOpen: false,
                    })
                }}
                aria-labelledby="EditTimedEffectModal"
                aria-describedby="EditTimedEffectModal"
            >
                {EditTimedEffectModal(parent)}
            </Modal>
            <Modal
                open={parent.state.bringInSidelineCharacterModalOpen}
                onClose={ () => {
                    parent.setState({
                        bringInSidelineCharacterModalOpen: false,
                    })
                }}
                aria-labelledby="BringInSidelineCharacterModal"
                aria-describedby="BringInSidelineCharacterModal"
            >
                {BringInSidelineCharacterModal(parent)}
            </Modal>
        </Box>
    );
}