export const getPxFromPercentOfWindowWidth = (parent, percentNumber) => {
   return (parent.state.width * percentNumber / 100) + "px";
}

export const getPxFromPercentOfWindowHeight= (parent, percentNumber) => {
    return (parent.state.height * percentNumber / 100) + "px";
 }