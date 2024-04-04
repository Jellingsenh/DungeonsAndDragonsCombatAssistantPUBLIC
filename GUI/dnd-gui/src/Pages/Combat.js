import React from 'react';
import { CombatBox } from '../Components/Combat/CombatBox';
import { CombatModals } from '../Components/Combat/CombatModals';
import { removeSlashes } from '../Components/Helpers/removeSlashes';

class Combat extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            time: "",
            location: "",
            environment: "",

            yearNotation: "",
            daysInYear: 0,
            monthsInYear: [],
            gotTimeDetails: false,

            timedEffects: {},
            initiativeOrder: {},
            currentEditEffect: {},

            currentCharacter: "",
            nextCharacter: "",

            viewEditCharacterModalOpen: false,
            characterBeingViewed: "",
            character: {},
            editMode: false,
            characterBeingViewedCurrentHealth: 0,
            oldName: "",
            gotOldName: false,

            sidelineCharacters: {},

            addTimeModalOpen: false,
            subtractTimeModalOpen: false,
            editInitiativeModalOpen: false, 
            editLocationModalOpen: false,
            editEnvironmentModalOpen: false,
            editTimeModalOpen: false,
            addTimedEffectModalOpen: false,
            editTimedEffectModalOpen: false,
            removeHealthModalOpen: false,
            bringInSidelineCharacterModalOpen: false,
            combatRemindersModalOpen: false,

            width: window.innerWidth,
            height: window.innerHeight,
        };
        
        this.getTime();
        this.getTimeDetails();
        this.getLocation();
        this.getEnvironment();
        this.getTimedEffects();
        this.getInitiativeOrder();
        this.getCurrentCharacter();
        this.getNextCharacter();
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

    async getTime(){
        await fetch(window.apiURL + '/getTime', {
            method: 'GET',
        })
        .then(response => response.text())
        .then(response => response.substring(1, response.length-1))
        .then(response => {
            this.setState({
                time: response,
            });
        })
        .catch(err => {
            console.log(err);
        });
    }

    async getTimeDetails(){
        await fetch(window.apiURL + '/getTimeDetails', {
            method: 'GET',
        })
        .then(response => response.json())
        .then(response => {
            // console.log('response:', response);
            this.setState({
                yearNotation: response.yearNotation,
                daysInYear: response.daysInAYear,
                monthsInYear: response.months,
                gotTimeDetails: true,
            // }, () => {
            //     console.log(this.state.monthsInYear)
            });
        })
        .catch(err => {
            console.log(err);
        });
    }

    async saveTimeDetails() {
        var timeDetails = {
            yearNotation: this.state.yearNotation,
            daysInAYear: this.state.daysInYear,
            months: this.state.monthsInYear,
        }
        
        if (timeDetails != null) {
            var jsonHeaders = new Headers();
            jsonHeaders.append("Accept", "application/json");
            jsonHeaders.append("Content-Type", "application/json");

            await fetch(window.apiURL + '/setTimeDetails', {
                method: 'POST',
                headers: jsonHeaders,
                body: JSON.stringify(timeDetails),
            })
            .then(window.location.reload())
            .catch(err => {
                console.log(err);
            });
        }
    }

    async getLocation() {
        await fetch(window.apiURL + '/getLocation', {
            method: 'GET',
        })
        .then(response => response.text())
        .then(response => {
            this.setState({
                location: response,
            });
        })
        .catch(err => {
            console.log(err);
        });
    }

    async changeLocation(newLocation) {
        if (newLocation != null) {
            var jsonHeaders = new Headers();
            jsonHeaders.append("Accept", "application/json");
            jsonHeaders.append("Content-Type", "application/json");

            await fetch(window.apiURL + '/changeLocation', {
                method: 'POST',
                headers: jsonHeaders,
                body: newLocation,
            })
            .then(window.location.reload())
            .catch(err => {
                console.log(err);
            });
        }
    }

    async getEnvironment() {
        await fetch(window.apiURL + '/getEnvironment', {
            method: 'GET',
        })
        .then(response => response.text())
        .then(response => {
            this.setState({
                environment: response,
            });
        })
        .catch(err => {
            console.log(err);
        });
    }

    async changeEnvironment(newEnvironment) {
        if (newEnvironment != null) {
            var jsonHeaders = new Headers();
            jsonHeaders.append("Accept", "application/json");
            jsonHeaders.append("Content-Type", "application/json");

            await fetch(window.apiURL + '/changeEnvironment', {
                method: 'POST',
                headers: jsonHeaders,
                body: newEnvironment,
            })
            .then(window.location.reload())
            .catch(err => {
                console.log(err);
            });
        }
    }

    async getTimedEffects() {
        await fetch(window.apiURL + '/getTimedEffects', {
            method: 'GET',
        })
        .then(response => response.json())
        .then(response => {
            this.setState({
                timedEffects: response,
            });
        })
        .catch(err => {
            console.log(err);
        });
    }

    async getInitiativeOrder() {
        await fetch(window.apiURL + '/getInitiativeOrder', {
            method: 'GET',
        })
        .then(response => response.json())
        .then(response => {
            this.setState({
                initiativeOrder: response,
            });
        })
        .catch(err => {
            console.log(err);
        });
    }

    async getCurrentCharacter() {
        await fetch(window.apiURL + '/getCurrentCharacter', {
            method: 'GET',
        })
        .then(response => response.text())
        .then(response => {
            this.setState({
                currentCharacter: response,
            });
        })
        .catch(err => {
            console.log(err);
        });
    }

    async getNextCharacter() {
        await fetch(window.apiURL + '/getNextCharacter', {
            method: 'GET',
        })
        .then(response => response.text())
        .then(response => {
            this.setState({
                nextCharacter: response,
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

    async resetTime() {
        if (window.confirm("Are you sure you want to reset time?")) {
            await fetch(window.apiURL + '/resetTime', {
                method: 'GET',
            })
            .then(window.location.reload())
            .catch(err => {
                console.log(err);
            });
        }
    }

    

    async addTimedEffect(newEffect) {
        var jsonHeaders = new Headers();
        jsonHeaders.append("Accept", "application/json");
        jsonHeaders.append("Content-Type", "application/json");

        await fetch(window.apiURL + '/addTimedEffect', {
            method: 'POST',
            headers: jsonHeaders,
            body: JSON.stringify(newEffect),
        })
        .then(window.location.reload())
        .catch(err => {
            console.log(err);
        });
    }

    async editTimedEffect(effect) {
        var jsonHeaders = new Headers();
        jsonHeaders.append("Accept", "application/json");
        jsonHeaders.append("Content-Type", "application/json");

        await fetch(window.apiURL + '/editTimedEffect', {
            method: 'POST',
            headers: jsonHeaders,
            body: JSON.stringify(effect),
        })
        .then(window.location.reload())
        .catch(err => {
            console.log(err);
        });
    }

    async endTimedEffect(effectName) {
        if (window.confirm("Are you sure you want to end " + effectName + "?")) {
            await fetch(window.apiURL + '/removeTimedEffect/' + effectName, {
                method: 'GET',
            })
            .then(window.location.reload())
            .catch(err => {
                console.log(err);
            });
        }
    }

    async nextTurn() {
        await fetch(window.apiURL + '/nextTurn', {
            method: 'GET',
        })
        .then(window.location.reload())
        .catch(err => {
            console.log(err);
        });
    }

    async dealDamage(characterName, damageDealt, currentHealth) {
        var jsonHeaders = new Headers();
        jsonHeaders.append("Accept", "application/json");
        jsonHeaders.append("Content-Type", "application/json");

        await fetch(window.apiURL + '/dealDamage/' + characterName, {
            method: 'POST',
            headers: jsonHeaders,
            body: JSON.stringify(damageDealt),
        })
        .then(() => {
            if ((parseInt(currentHealth, 10) - parseInt(damageDealt, 10) <= 0) && window.confirm(`${characterName} is dead, would you like to remove them from combat?`)) {
                this.moveCharacterToSideline(characterName);
            } else {
                window.location.reload();
            }
        })
        .catch(err => {
            console.log(err);
        });
    }

    async editInitiative(initiativeArray) {
        if (initiativeArray != null && initiativeArray.length > 0) {
            var jsonHeaders = new Headers();
            jsonHeaders.append("Accept", "application/json");
            jsonHeaders.append("Content-Type", "application/json");

            await fetch(window.apiURL + '/editInitiativeOrder', {
                method: 'POST',
                headers: jsonHeaders,
                body: JSON.stringify(initiativeArray),
            })
            .then(window.location.reload())
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

    async addTime (time) {
        if (time !== null) {
            var jsonHeaders = new Headers();
            jsonHeaders.append("Accept", "application/json");
            jsonHeaders.append("Content-Type", "application/json");

            await fetch(window.apiURL + '/addTime', {
                method: 'POST',
                headers: jsonHeaders,
                body: JSON.stringify(time),
            })
            .then(window.location.reload())
            .catch(err => {
                console.log(err);
            });
        }
    }

    async subtractTime (time) {
        if (time !== null) {
            var jsonHeaders = new Headers();
            jsonHeaders.append("Accept", "application/json");
            jsonHeaders.append("Content-Type", "application/json");

            await fetch(window.apiURL + '/subtractTime', {
                method: 'POST',
                headers: jsonHeaders,
                body: JSON.stringify(time),
            })
            .then(window.location.reload())
            .catch(err => {
                console.log(err);
            });
        }
    }

    backToArena() {
        window.open('/', '_self', 'noopener,noreferrer');
    }

    render() {
        // if (this.state.width <= 300 || this.state.height <= 300) {
        //     return(
        //         <div style={{border: '20px solid transparent'}}>
        //             <div style={{flex: 1, width: '80%', height: '100%', resizeMode: 'contain',}}>
        //                 <h1>Mobile Combat (not finished)</h1>
        //             </div>
        //         </div>
        //     );
        // } else {
            return (<>
                {CombatBox(this)}
                {CombatModals(this)}
            </>);
        // }
    }
}

export default Combat;
