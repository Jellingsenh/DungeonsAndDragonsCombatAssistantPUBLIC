import { isCharacterNpc, crString } from "./isCharacterNpc";

export function RemoveNameCR(nameIn){
    if(isCharacterNpc(nameIn)) {
		  return nameIn.substring(0, nameIn.indexOf(crString)-1);
    }
	return nameIn;
}