export const crString = "(CR";

export function isCharacterNpc(nameIn){
    return (nameIn.includes(crString) && nameIn.includes(")"));
}