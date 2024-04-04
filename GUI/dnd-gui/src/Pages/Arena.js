import React from 'react';
import { ArenaBox } from '../Components/Arena/ArenaBox';
import { ArenaModals } from '../Components/Arena/ArenaModals';
import { RemoveNameCR } from '../Components/Helpers/RemoveNameCR';
import { removeSlashes } from '../Components/Helpers/removeSlashes';

class Arena extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            combatCharacters: {},
            sidelineCharacters: {},
            allCharacters: {},
            combatPCs: {},
            groupNames: {},
            gameNames: {},
            currentGameName: "default",
            
            viewEditCharacterModalOpen: false,
            characterBeingViewed: "",
            character: {},
            editMode: false,
            createMode: false,
            oldName: "",
            gotOldName: false,

            rollInitiativeModalOpen: false,

            bringCharacterModalOpen: false,
            gotCharacters: false,
            searchBy: "noSearchHere",
            sortBy: "alphabetical",
            reverseAlphabetical: false,
            reverseChronological: true,
            reverseLevelOrder: true,
            reverseTypeOrder: true,
            selectedBringCharacters: [],

            deleteCharacterModalOpen: false,
            searchDeleteBy: "noSearchHere",
            sortDeleteBy: "alphabetical",
            reverseAlphabeticalDelete: false,
            reverseChronologicalDelete: true,
            reverseLevelOrderDelete: true,
            reverseTypeOrderDelete: true,
            selectedDeleteCharacters: [],

            createGroupModalOpen: false,
            bringGroupModalOpen: false,
            deleteGroupModalOpen: false,
            selectedGroupCharacters: [],
            editGroupModalOpen: false,
            editingGroupModalOpen: false,
            groupToEdit: "",
            groupMembers: [],
            gotGroup: false,

            createGameSaveModalOpen: false,
            gotGames: false,
            loadGameModalOpen: false,
            deleteGameModalOpen: false,
            sortGamesBy: "alphabetical",
            reverseGameAlphabetical: false,
            reverseGameChronological: false,

            infoModalOpen: false,

            width: window.innerWidth,
            height: window.innerHeight,
        };

        this.getCombatCharacters();
        this.getSidelineCharacters();
    }

    componentDidMount() {
        window.addEventListener('resize', this.handleWindowSizeChange);
    }

    componentWillUnmount() {
        window.removeEventListener('resize', this.handleWindowSizeChange);
    }

    handleWindowSizeChange = () => {
        this.setState({ 
            width: window.innerWidth,
            height: window.innerHeight
        });
    }

    async getCombatCharacters() {
        await fetch(window.apiURL + '/getCombatCharacters', {
            method: 'GET',
        })
        .then(response => response.json())
        .then(response => {
            this.setState({
                combatCharacters: response,
            });
        })
        .catch(err => {
            console.log(err);
        });
    }

    async getSidelineCharacters() {
        await fetch(window.apiURL + '/getSidelineCharacters', {
            method: 'GET',
        })
        .then(response => response.json())
        .then(response => {
            this.setState({
                sidelineCharacters: response,
            });
        })
        .catch(err => {
            console.log(err);
        });
    }
    
    async getCombatPCs() {
        await fetch(window.apiURL + '/getCombatPCs', {
            method: 'GET',
        })
        .then(response => response.json())
        .then(response => {
            this.setState({
                combatPCs: response,
            });
        })
        .catch(err => {
            console.log(err);
        });
    }
    
    async getGroups() {
        await fetch(window.apiURL + '/getAllGroupNames', {
            method: 'GET',
        })
        .then(response => response.json())
        .then(response => {
            this.setState({
                groupNames: response,
            });
        })
        .catch(err => {
            console.log(err);
        });
    }

    async getGroupMembers(groupName) { 
        await fetch(window.apiURL + '/getGroupMembers/' + groupName, {
            method: 'GET',
        })
        .then(response => response.json())
        .then(response => {
            this.setState({
                groupMembers: response,
                selectedGroupCharacters: [...this.state.selectedGroupCharacters, ...response]
            }); 
        })
        .catch(err => {
            console.log(err);
        });
    }

    async getGames(sortBy) {
        await fetch(window.apiURL + '/getAllGameNames/' + sortBy, {
            method: 'GET',
        })
        .then(response => response.json())
        .then(response => {
            this.setState({
                gameNames: response,
                gotGames: true,
            });
        })
        .catch(err => {
            console.log(err);
        });
    }

    async getAllCharacters(sortBy, searchBy) {
        if (searchBy.length <= 0) {
            searchBy = "noSearchHere";
        }
        if (sortBy.length <= 0) {
            sortBy = "a";
        }
        await fetch(window.apiURL + '/getAllCharacterNames/' + sortBy + '/' + searchBy, {
            method: 'GET',
        })
        .then(response => response.json())
        .then(response => {
            this.setState({
                allCharacters: response,
                gotCharacters: true,
            });
        })
        .catch(err => {
            console.log(err);
        });
    }

    async moveCharacterToSideline(characterName) {
        await fetch(window.apiURL + '/moveCharacterToSideline/' + characterName, {
            method: 'GET',
        })
        .then(window.location.reload())
        .catch(err => {
            console.log(err);
        });
    }

    async moveCharacterToCombat(characterName) {
        await fetch(window.apiURL + '/moveCharacterToCombat/' + characterName, {
            method: 'GET',
        })
        .then(window.location.reload())
        .catch(err => {
            console.log(err);
        });
    }

    async removeCharacterFromArena(characterName) {
        await fetch(window.apiURL + '/removeCharacterFromArena/' + characterName, {
            method: 'GET',
        })
        .then(window.location.reload())
        .catch(err => {
            console.log(err);
        });
    }

    async duplicateCharacter(characterName) {
        let numberOfDuplicates = window.prompt(`How many copies of ${characterName} do you want to make? (enter a number):`);
        if (numberOfDuplicates != null) {
            if (!/[0-9]/.test(numberOfDuplicates)) {
                console.log(numberOfDuplicates, 'is not a number, setting to 1');
                numberOfDuplicates = 1;
            }
    
            var jsonHeaders = new Headers();
            jsonHeaders.append("Accept", "application/json");
            jsonHeaders.append("Content-Type", "application/json");
    
            await fetch(window.apiURL + '/duplicateCharacter/' + characterName, {
                method: 'POST',
                headers: jsonHeaders,
                body: JSON.stringify(numberOfDuplicates),
            })
            .then(window.location.reload())
            .catch(err => {
                console.log(err);
            });
        }
    }

    async deleteAllduplicates() {
        if (window.confirm("Are you sure you want to delete all duplicates?")) {
            await fetch(window.apiURL + '/removeAllDuplicates', {
                method: 'GET',
            })
            .then(window.location.reload())
            .catch(err => {
                console.log(err);
            });
        }
    }

    async saveGame(newGameName) {
        if (newGameName != null) {
            var jsonHeaders = new Headers();
            jsonHeaders.append("Accept", "application/json");
            jsonHeaders.append("Content-Type", "application/json");

            await fetch(window.apiURL + '/saveGame', {
                method: 'POST',
                headers: jsonHeaders,
                body: newGameName,
            })
            .then(window.location.reload())
            .catch(err => {
                console.log(err);
            });
        }
    }

    async loadGame(gameName) {
        await fetch(window.apiURL + '/loadGame/' + gameName, {
            method: 'GET',
        })
        .then(
            this.setState({
                currentGameName: gameName,
            }),
            window.location.reload()
            )
        .catch(err => {
            console.log(err);
        });
    }

    async deleteGame(gameName) {
        if (window.confirm(`Are you sure you want to delete ${gameName}?`)) {
            await fetch(window.apiURL + '/deleteGame/' + gameName, {
                method: 'GET',
            })
            .then(window.location.reload())
            .catch(err => {
                console.log(err);
            });
        }
    }

    async bringGroup(groupName) {
        await fetch(window.apiURL + '/enterGroupIntoArena/' + groupName, {
            method: 'GET',
        })
        .then(window.location.reload())
        .catch(err => {
            console.log(err);
        });
    } 
    
    async createGroup() {
        let groupName = window.prompt(`Enter a name for this group:`);
        if (groupName != null && groupName.length> 0 && this.state.selectedGroupCharacters != null && this.state.selectedGroupCharacters.length > 0) {
            var jsonHeaders = new Headers();
            jsonHeaders.append("Accept", "application/json");
            jsonHeaders.append("Content-Type", "application/json");

            var payload = `{"name": "${groupName}", "characterNames": [${this.state.selectedGroupCharacters.map((c) => {return `"${RemoveNameCR(c)}"`;})}]}`;

            await fetch(window.apiURL + '/saveGroup', {
                method: 'POST',
                headers: jsonHeaders,
                body: payload,
            })
            .then(window.location.reload())
            .catch(err => {
                console.log(err);
            });
        }
    }

    async editGroup(groupName) {
        if (groupName != null && groupName.length> 0 && this.state.selectedGroupCharacters != null && this.state.selectedGroupCharacters.length > 0) {
            var jsonHeaders = new Headers();
            jsonHeaders.append("Accept", "application/json");
            jsonHeaders.append("Content-Type", "application/json");

            var payload = `{"name": "${groupName}", "characterNames": [${this.state.selectedGroupCharacters.map((c) => {return `"${RemoveNameCR(c)}"`;})}]}`;

            await fetch(window.apiURL + '/saveGroup', {
                method: 'POST',
                headers: jsonHeaders,
                body: payload,
            })
            .then(window.location.reload())
            .catch(err => {
                console.log(err);
            });
        }
    }

    async deleteGroup(groupName) {
        if (window.confirm(`Are you sure you want to delete ${groupName}?`)) {
            await fetch(window.apiURL + '/deleteGroup/' + groupName, {
                method: 'GET',
            })
            .then(window.location.reload())
            .catch(err => {
                console.log(err);
            });
        }
    }

    async bringCharacter(characterName) {
        await fetch(window.apiURL + '/enterCharacterIntoArena/' + RemoveNameCR(characterName), {
            method: 'GET',
        })
        .then(window.location.reload())
        .catch(err => {
            console.log(err);
        });
    }

    async deleteCharacter(characterName) {
        characterName = RemoveNameCR(characterName);
        await fetch(window.apiURL + '/deleteCharacter/' + characterName, {
            method: 'GET',
        })
        .then(window.location.reload())
        .catch(err => {
            console.log(err);
        });
    }

    async rollInitiative(initiativeArray) {
        if (initiativeArray != null && initiativeArray.length > 0) {
            var jsonHeaders = new Headers();
            jsonHeaders.append("Accept", "application/json");
            jsonHeaders.append("Content-Type", "application/json");

            await fetch(window.apiURL + '/rollInitiative', {
                method: 'POST',
                headers: jsonHeaders,
                body: JSON.stringify(initiativeArray),
            })
            .then(this.enterCombat())
            .catch(err => {
                console.log(err);
            });
        }
    }

    async getCharacter(characterName) {
        if (characterName !== "") {
            await fetch(window.apiURL + '/getCharacter/' + characterName, {
                method: 'GET',
            })
            .then(response => response.json())
            .then(response => {
                this.setState({
                    character: response,
                    editMode: false,
                    viewEditCharacterModalOpen: true,
                });
            })
            .catch(err => {
                console.log(err);
            });
        }
    }

    async createNewCharacter(character) {
        if (character.name !== "") {
            character.name = removeSlashes(character.name);
            var jsonHeaders = new Headers();
            jsonHeaders.append("Accept", "application/json");
            jsonHeaders.append("Content-Type", "application/json");

            await fetch(window.apiURL + '/addCharacter', {
                method: 'POST',
                headers: jsonHeaders,
                body: JSON.stringify(character),
            })
            .then(this.setState({ 
                editMode: false,
                createMode: false,
                character: character,
            }))
            .catch(err => {
                console.log(err);
            });
        }
    }

    async updateCharacter(oldName, newCharacter) {
        if (oldName !== "" && newCharacter.name !== "" ) {
            newCharacter.name = removeSlashes(newCharacter.name);
            var jsonHeaders = new Headers();
            jsonHeaders.append("Accept", "application/json");
            jsonHeaders.append("Content-Type", "application/json");

            await fetch(window.apiURL + '/editCharacter/' + oldName, {
                method: 'PUT',
                headers: jsonHeaders,
                body: JSON.stringify(newCharacter),
            })
            .then(this.setState({ 
                editMode: false,
                createMode: false,
                character: newCharacter,
            }))
            .catch(err => {
                console.log(err);
            });
        }
    }

    bringSelectedCharacters() {
        for (var i = 0; i < this.state.selectedBringCharacters.length; i++) {
            this.bringCharacter(this.state.selectedBringCharacters[i]);
        }
    }

    deleteSelectedCharacters() {
        if (window.confirm(`Are you sure you want to delete ${this.state.selectedDeleteCharacters.length} characters?\nThey will be deleted from any game saves they are in as well.`)) {
            for (var i = 0; i < this.state.selectedDeleteCharacters.length; i++) {
                this.deleteCharacter(this.state.selectedDeleteCharacters[i]);
            }
        }
    }

    enterCombat() {
        window.open('/Combat', '_self', 'noopener,noreferrer');
    }

    render() {
        // if (this.state.width <= 300 || this.state.height <= 300) {
        //     return(
        //         <div style={{border: '20px solid transparent'}}>
        //             <div style={{flex: 1, width: '80%', height: '100%', resizeMode: 'contain',}}>
        //                 <h1>Mobile Arena (not finished)</h1>
        //             </div>
        //         </div>
        //     );
        // } else {
            return (<>
                {ArenaBox(this)}
                {ArenaModals(this)}
            </>);
            
        // }
    }
}

export default Arena;
