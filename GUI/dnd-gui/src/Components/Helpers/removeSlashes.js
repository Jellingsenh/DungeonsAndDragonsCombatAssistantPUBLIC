export function removeSlashes(nameIn){
    // eslint-disable-next-line
    if (nameIn.includes('\\') || nameIn.includes('\/')) {
        nameIn = nameIn.replace(/\\/g, '|');
        nameIn = nameIn.replace(/\//g, '|');
    }
    return nameIn;
}
