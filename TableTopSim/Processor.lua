-- Tabletop Simulator Connector for D&D Combat Assistant
-- Made by Benjamin Kim & Joshua Haynes, April 2023
-- Updated 3/31/2024 (Version 2.3)


-- Tabletop Object variables:

isTabletopObject = false -- set to true if using an object to run the assistant
objectGuid = "12345" -- also replace objectGuid with the spawned object's GUID

-- XML variable:

XML_STRING = [[
<Panel id = "test" onClick = "foo" onSubmit = "foo2" onEndEdit = "foo3">Test</Panel>
]]

-- Network constants:

IP_ADDRESS = nil
PORT = "9001"

GET_INITIATIVE_PATH = "/getInitiativeOrder"
ROLL_INITIATIVE_PATH = "/rollInitiative"
GET_CHARACTER_INITIATIVE_PATH = "/getCharacterInitiativeTotal"
GET_PCS_PATH = "/getCombatPCs"
NEXT_TURN_PATH = "/nextTurn"
GET_CURRENT_PLAYER_PATH = "/getCurrentCharacter"
GET_PLAYER_IS_NPC_PATH = "/isCharacterNpc"
GET_NEXT_PLAYER_PATH = "/getNextCharacter"
GET_TIMED_EFFECTS_PATH = "/getTimedEffects"
ADD_TIMED_EFFECT_PATH = "/addTimedEffect"

-- XML id constants:

changeIPAddressID = "change_ipaddress"
inputIPAddressID = "ipaddress_input"
pcListID = "pc_list"
textButtonID = "text_button"
requestInitiativeID = "requestInit"
blueInitiativeID = "blueInitiative"
purpleInitiativeID = "purpleInitiative"
greenInitiativeID = "greenInitiative"
orangeInitiativeID = "orangeInitiative"
tealInitiativeID = "tealInitiative"
brownInitiativeID = "brownInitiative"
pinkInitiativeID = "pinkInitiative"
redInitiativeID = "redInitiative"
yellowInitiativeID = "yellowInitiative"
whiteInitiativeID = "whiteInitiative"
gmInitiativeID = "gm_roll_initiative"
gmInitTextID = "text_button_2"
turnOrderID = "h_scrollView"
endTurnID = "end_turn_button"
endTurnGmID = "end_turn_gm"
endCombatID = "end_combat_gm"
refreshCombatID = "refresh_combat_gm"
addAPlayerID = "addAPlayer"
requestSkillID = "requestPartySkill"
whiteSkillID = "whiteSkill"
blueSkillID = "blueSkill"
greenSkillID = "greenSkill"
yellowSkillID = "yellowSkill"
purpleSkillID = "purpleSkill"
redSkillID = "redSkill"
orangeSkillID = "orangeSkill"
tealSkillID = "tealSkill"
pinkSkillID = "pinkSkill"
brownSkillID = "brownSkill"
skillTextID = "text_button_5"
openPartySkillViewerID = "openPartySkillViewer"
addTimedEffectID = "add_timed_effect"
addTimedEffectDurationID = "timed_effect_duration"
addTimedEffectDurationMinutesID = "timed_effect_duration_minutes"
addTimedEffectDurationHoursID = "timed_effect_duration_hours"
addTimedEffectDurationDaysID = "timed_effect_duration_days"
addTimedEffectTargetsID = "timed_effect_targets"
addTimedEffectNameID = "timed_effect_name"
timedEffectEffectID = "timed_effect_effect"
timedEffectTextID = "text_button_6"
cancelTimedEffectID = "cancel_timed_effect"
confirmTimedEffectID = "confirm_timed_effect"
displayTimedEffectsTextID = "text_button_7"
timedEffect1ID = "text_button_8"
timedEffect2ID = "text_button_9"
timedEffect3ID = "text_button_10"
timedEffect4ID = "text_button_15"
timedEffect5ID = "text_button_16"
timedEffect1TargetsID = "text_button_32"
timedEffect1TimeLeftID = "text_button_33"
timedEffect2TargetsID = "text_button_34"
timedEffect2TimeLeftID = "text_button_35"
timedEffect3TargetsID = "text_button_36"
timedEffect3TimeLeftID = "text_button_37"
timedEffect4TargetsID = "text_button_38"
timedEffect4TimeLeftID = "text_button_39"
timedEffect5TargetsID = "text_button_40"
timedEffect5TimeLeftID = "text_button_41"
timedEffectsDownID = "timed_effects_down_button"
timedEffectsUpID = "timed_effects_up_button"
initTextID = "text_button_3"
initSlot1ID = "text_button_4"
initSlot2ID = "text_button_11"
initSlot3ID = "text_button_12"
initSlot4ID = "text_button_13"
initSlot5ID = "text_button_14"
turnLeftButtonID = "turn_left_button"
turnRightButtonID = "turn_right_button"
ipSelectorPanelID = "ipSelectorPanel"
getPlayersButtonID = "get_players_button"
timedEffect1EffectLabelID = "text_button_17"
timedEffect1TargetsLabelID = "text_button_18"
timedEffect1TimeLeftLabelID = "text_button_19"
timedEffect2EffectLabelID = "text_button_20"
timedEffect2TargetsLabelID = "text_button_21"
timedEffect2TimeLeftLabelID = "text_button_22"
timedEffect3EffectLabelID = "text_button_23"
timedEffect3TargetsLabelID = "text_button_24"
timedEffect3TimeLeftLabelID = "text_button_25"
timedEffect4EffectLabelID = "text_button_26"
timedEffect4TargetsLabelID = "text_button_27"
timedEffect4TimeLeftLabelID = "text_button_28"
timedEffect5EffectLabelID = "text_button_29"
timedEffect5TargetsLabelID = "text_button_30"
timedEffect5TimeLeftLabelID = "text_button_31"
closeTimedEffectViewerID = "closeTimedEffectViewer"
showImageID = "showImage"
blackImageID = "blackImage"
closeBlackImageID = "closeBlackImage"
blueImageID = "blueImage"
closeBlueImageID = "closeBlueImage"
purpleImageID = "purpleImage"
closePurpleImageID = "closePurpleImage"
greenImageID = "greenImage"
closeGreenImageID = "closeGreenImage"
orangeImageID = "orangeImage"
closeOrangeImageID = "closeOrangeImage"
tealImageID = "tealImage"
closeTealImageID = "closeTealImage"
brownImageID = "brownImage"
closeBrownImageID = "closeBrownImage"
pinkImageID = "pinkImage"
closePinkImageID = "closePinkImage"
redImageID = "redImage"
closeRedImageID = "closeRedImage"
yellowImageID = "yellowImage"
closeYellowImageID = "closeYellowImage"
whiteImageID = "whiteImage"
closeWhiteImageID = "closeWhiteImage"
showTimeDetailsID = "showTimeDetails"
timeDetailsID = "timeDetails"
locationID = "locationButton"
environmentID = "environmentButton" 

-- Game constants:

SELECTED_GREY = "#787878"
PROMPT_BLUE = "#0474bf"
NPC_PURPLE = "#9B59B6"
NPC_PURPLEs = "#9B59B6|#9B59B6|#9B59B6|#9B59B6"
DEFAULT_RED_PINK = "#ff6666"
DEFAULT_BLUE_GREY = "#5c7091"
DEFAULT_BLUE_GREYs = "#5c7091|#5c7091|#5c7091|#5c7091"
BUTTON_TEXT_BLUE_GREY = "#83a2d4"
DEFAULTY_GREY = "#d1d1d1"
-- CURRENT_GOLD = "#ebb03b"
CURRENT_GOLDs = "#ebb03b|#ebb03b|#ebb03b|#ebb03b"
BLOODIED_RED = "#8a0315"
-- CABOOSE_RED = "#CD5C5C"
CABOOSE_REDs = "#CD5C5C|#CD5C5C|#CD5C5C|#CD5C5C"
BLACK = "#000000"
REFRESH_GREEN = "#339933"
BUTTON_COLOR_1 = "#99ccff"
BUTTON_COLOR_2 = "#3399ff"
BUTTON_COLOR_3 = "#ff99ff"
BUTTON_COLOR_4 = "#cc99ff"
BUTTON_COLOR_5 = "#ff0066"
BUTTON_COLOR_6 = "#cc0000"
BUTTON_COLOR_7 = "#ffcc99"
BUTTON_COLOR_8 = "#e48b07"
BUTTON_COLOR_9 = "#00ffcc"
BUTTON_COLOR_10 = "#009933"
BUTTON_COLOR_11 = "#ebebeb"
BUTTON_COLOR_12 = "#bdbdbd"
EFFECT_LABEL_COLORs = "#42BE76|#42BE76|#42BE76|#42BE76"
TARGETS_LABEL_COLORs = "#C0392B|#C0392B|#C0392B|#C0392B"
TIME_LEFT_LABEL_COLORs = "#0474bf|#0474bf|#0474bf|#0474bf" -- PROMPT_BLUEs

CR_STRING = [[%pCR%s]]
CR_STRING_2 = [[%p%d]]
CR_STRING_3 = [[%p]] -- any punctuation
XML_REPLACE = [[onClick%s=%s"]]
XML_REPLACE_2 = [[onEndEdit%s=%s"]]
XML_REPLACE_3 = [[onSubmit%s=%s"]]

-- Game variables:

pcList = {}
numberOfPCs = 0
playerColorMap = {White = "", Red = "", Brown = "", Orange = "", Yellow = "", Green = "", Teal = "", Blue = "", Purple = "", Pink = ""}
currentTurnName = ""
nextTurnName = ""
pcSelectorActive = false
timedEffectsList = {}
fiveViewed = {1,2,3,4,5} -- indexes of viewed characters
threeViewed = {1,2,3} -- indexes of viewed effects
endRoundAdded = false;
endEffectAdded= false;

-- Core functions:

function onLoad()
    broadcastToAll("Loading the D&D Combat Assistant...")
    -- replaceXmlGuid(XML_STRING, "12345") -- testing only
    if isTabletopObject then
        setupObjectXmlUI()
    end
end

function onUpdate()
    -- print('onUpdate loop!')
end

-- Network setup functions:

function storeIPAddress(player, ipAddress, id)
    IP_ADDRESS = ipAddress
    print("The current server IP Address is: " .. ipAddress)
    UI.setAttribute(changeIPAddressID, "active", "true")
    UI.setAttribute(changeIPAddressID, "visibility", "host")
    UI.setAttribute(inputIPAddressID, "active", "false")
    UI.setAttribute(changeIPAddressID, "text", IP_ADDRESS)
    UI.setAttribute(getPlayersButtonID, "active", "true") -- load players button that calls loadPlayerData()
    UI.setAttribute(showTimeDetailsID, "active", "true")
end

function changeIPAddress()
    UI.setAttribute(inputIPAddressID, "active", "true")
    UI.setAttribute(inputIPAddressID, "visibility", "host")
    UI.setAttribute(changeIPAddressID, "active", "false")
    UI.setAttribute(addAPlayerID, "active", "false")
    UI.setAttribute(showTimeDetailsID, "active", "false")
    UI.setAttribute(getPlayersButtonID, "active", "false")
    -- UI.setAttribute(ipSelectorPanelID, "height", "30")
end

-- Player selection functions:

function makePcList(pcListFromServer)
    pcList = mysplit(shaveString(pcListFromServer), ",")
    numberOfPCs = #(pcList)
    displayPcs()
    UI.setAttribute(addAPlayerID, "active", "true")
    UI.setAttribute(getPlayersButtonID, "color", SELECTED_GREY)
    UI.setAttribute(addAPlayerID, "text", "Close Player Selector")
    UI.setAttribute(addAPlayerID, "color", BUTTON_COLOR_6)
    
end

function addPlayerToggle()
    if UI.getAttribute(addAPlayerID, "text") == "Open Player Selector" then
        UI.setAttribute(addAPlayerID, "text", "Close Player Selector")
        UI.setAttribute(addAPlayerID, "color", BUTTON_COLOR_6)
        UI.setAttribute(getPlayersButtonID, "color", SELECTED_GREY)
        displayPcs()
    else
        UI.setAttribute(addAPlayerID, "text", "Open Player Selector")
        UI.setAttribute(addAPlayerID, "color", PROMPT_BLUE)
        UI.setAttribute(getPlayersButtonID, "color", REFRESH_GREEN)
        closePcSelector()

    end
end

function showTimeDetailsToggle() 
    if UI.getAttribute(showTimeDetailsID, "text") == "Show time details" then
        UI.setAttribute(showTimeDetailsID, "text", "Hide time details")
        UI.setAttribute(showTimeDetailsID, "color", BUTTON_COLOR_6)
        -- displayPcs()
        UI.setAttribute(timeDetailsID, "active", "true")
        UI.setAttribute(locationID, "active", "true")
        UI.setAttribute(environmentID, "active", "true")
        -- UI.setAttribute("details_list", "active", "true")
        apiGetTimeDetails()
    else
        UI.setAttribute(showTimeDetailsID, "text", "Show time details")
        UI.setAttribute(showTimeDetailsID, "color", PROMPT_BLUE)
        UI.setAttribute(timeDetailsID, "active", "false")
        UI.setAttribute(locationID, "active", "false")
        UI.setAttribute(environmentID, "active", "false")
        -- UI.setAttribute("details_list", "active", "false")
    end
end

function displayPcs()
    -- for each PC, activate a button (max 10 PCs)
    for i = 1, numberOfPCs, 1 do
        -- print(pcList[i])
        name = shaveString(pcList[i])
        buttonId = "buttonId" .. tostring(i)
        UI.setAttribute(buttonId, "active", "true")
        UI.setAttribute(buttonId, "text", name)
        if isTabletopObject then
            UI.setAttribute(buttonId, "onClick", objectGuid.."/playerSelected("..name..")")
        else
            UI.setAttribute(buttonId, "onClick", "playerSelected("..name..")")
        end
    end
    for c = numberOfPCs + 1, 12, 1 do
        buttonId = "buttonId" .. tostring(c)
        UI.setAttribute(buttonId, "active", "false")
    end

    UI.setAttribute(textButtonID, "active", "true")
    UI.setAttribute(pcListID, "active", "true")
    pcSelectorActive = true
end

function playerSelected(player, name, id)
    -- id is the buttonId
    playerCurrentName = findPlayerNameFromColor(player.color)
    if playerCurrentName == name then
        broadcastToColor("You've already selected "..name, player.color)
    else
        broadcastToAll(player.steam_name .. " (" .. player.color .. ") selected: " .. name)
        playerColorMap[tostring(player.color)] = name
        updatePlayerButtons()
        UI.setAttribute(requestInitiativeID, "active", "true")
        UI.setAttribute(requestInitiativeID, "color", SELECTED_GREY)
        UI.setAttribute(addTimedEffectID, "active", "true") -- create timed effects before all players chosen
        UI.setAttribute(refreshCombatID, "active", "true")
        UI.setAttribute(refreshCombatID, "color", SELECTED_GREY)
        UI.setAttribute(requestSkillID, "active", "true")
        UI.setAttribute(requestSkillID, "color", SELECTED_GREY)
        checkIfAllPCsSelected()
    end
end

function updatePlayerButtons()
    -- set any selected buttons to SELECTED_GREY, all others to default
    for m = 1, 12, 1 do
        buttonId = "buttonId" .. tostring(m)
        playerName02 = UI.getAttribute(buttonId, "text")
        -- print(playerName02)
        setColor = DEFAULT_RED_PINK

        if isPlayerInColorMap(playerName02) then
            setColor = SELECTED_GREY
        else
            if m == 1 then
                setColor = BUTTON_COLOR_1
            else 
                if m == 2 then
                    setColor = BUTTON_COLOR_2
                else
                    if m == 3 then
                        setColor = BUTTON_COLOR_3
                    else
                        if m == 4 then
                            setColor = BUTTON_COLOR_4
                        else
                            if m == 5 then
                                setColor = BUTTON_COLOR_5
                            else
                                if m == 6 then
                                    setColor = BUTTON_COLOR_6
                                else
                                    if m == 7 then
                                        setColor = BUTTON_COLOR_7
                                    else
                                        if m == 8 then
                                            setColor = BUTTON_COLOR_8
                                        else
                                            if m == 9 then
                                                setColor = BUTTON_COLOR_9
                                            else
                                                if m == 10 then
                                                    setColor = BUTTON_COLOR_10
                                                else
                                                    if m == 11 then
                                                        setColor = BUTTON_COLOR_11
                                                    else
                                                        if m == 12 then
                                                            setColor = BUTTON_COLOR_12
                                                        end
                                                    end
                                                end
                                            end
                                        end
                                    end
                                end
                            end
                        end
                    end
                end
            end
        end

        UI.setAttribute(buttonId, "color", setColor)
    end
end

function checkIfAllPCsSelected()
    -- for each PC, check if it is assigned to a color
    foundNames = 0
    for k = 1, numberOfPCs, 1 do
        name = shaveString(pcList[k])
        -- print(name)
        if isPlayerInColorMap(name) then
            foundNames = foundNames + 1 
        end
    end
    -- print(foundNames)
    if foundNames == numberOfPCs then
        addPlayerToggle()
        UI.setAttribute(requestInitiativeID, "color", PROMPT_BLUE)
        UI.setAttribute(refreshCombatID, "color", PROMPT_BLUE)
        UI.setAttribute(requestSkillID, "color", PROMPT_BLUE)
    end
end

function closePcSelector()
    UI.setAttribute(textButtonID, "active", "false")
    UI.setAttribute(pcListID, "active", "false")
    pcSelectorActive = false
end

-- Initiative funtions:

function requestInitiative()
    endCombat()
    if  pcSelectorActive then
        UI.setAttribute(addAPlayerID, "text", "Open Player Selector")
        UI.setAttribute(addAPlayerID, "color", PROMPT_BLUE) -- double added to cover an error
        UI.setAttribute(getPlayersButtonID, "color", REFRESH_GREEN)
        closePcSelector()
    end
    numberInitspopulated = 0
    initNameList = ""
    initListForDisplay = ""
    initNamesJsonString = ""
    broadcastToAll("It\'s time to roll initiative!")
    -- for each color, if a PC, activate that UI element to request init
    if isNotEmpty(playerColorMap.White) then
        UI.setAttribute(whiteInitiativeID, "active", "true")
    end
    if isNotEmpty(playerColorMap.Red) then
        UI.setAttribute(redInitiativeID, "active", "true")
    end
    if isNotEmpty(playerColorMap.Orange) then
        UI.setAttribute(orangeInitiativeID, "active", "true")
    end
    if isNotEmpty(playerColorMap.Brown) then
        UI.setAttribute(brownInitiativeID, "active", "true")
    end
    if isNotEmpty(playerColorMap.Pink) then
        UI.setAttribute(pinkInitiativeID, "active", "true")
    end
    if isNotEmpty(playerColorMap.Purple) then
        UI.setAttribute(purpleInitiativeID, "active", "true")
    end
    if isNotEmpty(playerColorMap.Yellow) then
        UI.setAttribute(yellowInitiativeID, "active", "true")
    end
    if isNotEmpty(playerColorMap.Blue) then
        UI.setAttribute(blueInitiativeID, "active", "true")
    end
    if isNotEmpty(playerColorMap.Green) then
        UI.setAttribute(greenInitiativeID, "active", "true")
    end
    if isNotEmpty(playerColorMap.Teal) then
        UI.setAttribute(tealInitiativeID, "active", "true")
    end
    UI.setAttribute(requestInitiativeID, "color", SELECTED_GREY)
    UI.setAttribute(refreshCombatID, "color", SELECTED_GREY)
end

function addPlayerInitiative(player, initTotal, id)
    UI.setAttribute(id, "active", "false")

    addNameToGMPopup(findPlayerNameFromColor(player.color), initTotal)
end

function addNameToGMPopup(pName, initiativeTotal)
        initNameList = initNameList..pName..":"..initiativeTotal..", "
        initListForDisplay = "[" .. string.sub(initNameList, 1, string.len(initNameList)-2) .. "]"

        UI.setAttribute(gmInitTextID, "active", "true")
        UI.setAttribute(gmInitTextID, "text", initListForDisplay)

        initNamesJsonString = initNamesJsonString.."{\"characterName\": \""..pName.."\",\"initiativeTotal\": "..initiativeTotal.."}, "
        initNamesJsonStringToSend = "[" .. string.sub(initNamesJsonString, 1, string.len(initNamesJsonString)-2) .. "]"
        -- print("initNamesJsonStringToSend: "..initNamesJsonStringToSend)

        UI.setAttribute(gmInitiativeID, "active", "true")
        UI.setAttribute(gmInitiativeID, "color", SELECTED_GREY)

        numberInitspopulated = numberInitspopulated + 1
        if numberInitspopulated == numberOfPCs then
            UI.setAttribute(gmInitiativeID, "color", PROMPT_BLUE)
        end
end

function rollGmInitiative()
    UI.setAttribute(gmInitTextID, "active", "false")
    UI.setAttribute(gmInitiativeID, "active", "false")
    -- broadcastToAll("Rolling npcs...")
    apiRollInit()
    UI.setAttribute(requestInitiativeID, "color", PROMPT_BLUE)
end

function endCombat()
    UI.setAttribute(endTurnID, "active", "false")
    UI.setAttribute(endTurnGmID, "active", "false")
    UI.setAttribute(endCombatID, "active", "false")
    UI.setAttribute(refreshCombatID, "color", SELECTED_GREY)
    UI.setAttribute(closeTimedEffectViewerID, "text", "Open Timed Effect Viewer")
    UI.setAttribute(closeTimedEffectViewerID, "color", PROMPT_BLUE)
    closeTimedEffects()
    closeTurnOrder()
end

-- Party skill check functions (also for party saves):

function requestPartySkillCheck()
    if  pcSelectorActive then
        UI.setAttribute(addAPlayerID, "text", "Open Player Selector")
        UI.setAttribute(addAPlayerID, "color", PROMPT_BLUE)
        UI.setAttribute(getPlayersButtonID, "color", REFRESH_GREEN)
        closePcSelector()
    end
    skillNameList = ""
    statsString = ""
    statsList = {}
    broadcastToAll("It\'s time for a party roll!")
    -- for each color, if a PC, activate that UI element to request roll
    if isNotEmpty(playerColorMap.White) then
        UI.setAttribute(whiteSkillID, "active", "true")
    end
    if isNotEmpty(playerColorMap.Red) then
        UI.setAttribute(redSkillID, "active", "true")
    end
    if isNotEmpty(playerColorMap.Orange) then
        UI.setAttribute(orangeSkillID, "active", "true")
    end
    if isNotEmpty(playerColorMap.Brown) then
        UI.setAttribute(brownSkillID, "active", "true")
    end
    if isNotEmpty(playerColorMap.Pink) then
        UI.setAttribute(pinkSkillID, "active", "true")
    end
    if isNotEmpty(playerColorMap.Purple) then
        UI.setAttribute(purpleSkillID, "active", "true")
    end
    if isNotEmpty(playerColorMap.Yellow) then
        UI.setAttribute(yellowSkillID, "active", "true")
    end
    if isNotEmpty(playerColorMap.Blue) then
        UI.setAttribute(blueSkillID, "active", "true")
    end
    if isNotEmpty(playerColorMap.Green) then
        UI.setAttribute(greenSkillID, "active", "true")
    end
    if isNotEmpty(playerColorMap.Teal) then
        UI.setAttribute(tealSkillID, "active", "true")
    end
    UI.setAttribute(requestSkillID, "color", SELECTED_GREY)
end

function addPlayerSkill(player, skillTotal, id)
    UI.setAttribute(id, "active", "false")
    name03 = findPlayerNameFromColor(player.color)
    -- print(name03)
    addNameToPartySkillPopup(name03, skillTotal)
end

function addNameToPartySkillPopup(skillName, skillTotal)
    skillNameList = skillNameList..skillName..": "..skillTotal.."\n"
    table.insert(statsList,tonumber(skillTotal))
    UI.setAttribute(skillTextID, "text", "Party Results:\n\n"..skillNameList.."\n\nStatistics:\n"..getSkillStatistics())
    UI.setAttribute(skillTextID, "active", "true")

    UI.setAttribute(openPartySkillViewerID, "active", "true")
    activateClosePartySkillButton()
end

function getSkillStatistics()
    
    -- get the total, mean, & median
    local sum = 0
    for _,number in pairs(statsList) do
        -- print("number: "..tostring(number))
        sum = sum + number
    end

    if #statsList < 2 then
        return "\nTotal: "..tostring(sum)
    end

    local mean = sum / #(statsList)

    table.sort(statsList)
    local mid = #statsList/2
    local median = 0
    print("mid: "..tostring(mid))
    if math.floor(mid)==mid then
        median = statsList[mid]
    else
        median = (statsList[math.floor(mid)]+statsList[math.ceil(mid)])/2
    end

    statsString = "\nTotal: "..tostring(sum).."\nMiddle: "..tostring(median).."\nAverage: "..tostring(mean)
    return statsString
end

function skillPopupToggle()
    if UI.getAttribute(openPartySkillViewerID, "text") == "Open Party Roll Viewer" then
        activateClosePartySkillButton()
    else
        UI.setAttribute(openPartySkillViewerID, "text", "Open Party Roll Viewer")
        UI.setAttribute(openPartySkillViewerID, "color", PROMPT_BLUE)
        UI.setAttribute(skillTextID, "active", "false")
        UI.setAttribute(requestSkillID, "color", PROMPT_BLUE)
    end
end

function activateClosePartySkillButton()
    UI.setAttribute(openPartySkillViewerID, "text", "Close Party Roll Viewer")
    UI.setAttribute(openPartySkillViewerID, "color", BUTTON_COLOR_6)
    UI.setAttribute(skillTextID, "active", "true")
    UI.setAttribute(requestSkillID, "color", SELECTED_GREY)
end

-- Turn & time functions:

function announceTurn(currPlayer, isCharacterNpc)
    if currPlayer ~= nil and currPlayer ~= "" then
        if isCharacterNpc == "true" then
            UI.setAttribute(endTurnGmID, "color", NPC_PURPLE)
        else
            UI.setAttribute(endTurnGmID, "color", DEFAULT_RED_PINK)
        end
        currentTurnName = currPlayer
    else
        currentTurnName = "Nobody"
    end
    
    broadcastToAll("It\'s your turn, "..currentTurnName.."!")

    noMatch = false
    if playerColorMap.White == currentTurnName then
        UI.setAttribute(endTurnID, "visibility", "white")
    else
        if playerColorMap.Yellow == currentTurnName then
            UI.setAttribute(endTurnID, "visibility", "yellow")
        else
            if playerColorMap.Orange == currentTurnName then
                UI.setAttribute(endTurnID, "visibility", "orange")
            else
                if playerColorMap.Teal == currentTurnName then
                    UI.setAttribute(endTurnID, "visibility", "teal")
                else
                    if playerColorMap.Brown == currentTurnName then
                        UI.setAttribute(endTurnID, "visibility", "brown")
                    else
                        if playerColorMap.Pink == currentTurnName then
                            UI.setAttribute(endTurnID, "visibility", "pink")
                        else
                            if playerColorMap.Purple == currentTurnName then
                                UI.setAttribute(endTurnID, "visibility", "purple")
                            else
                                if playerColorMap.Green == currentTurnName then
                                    UI.setAttribute(endTurnID, "visibility", "green")
                                else
                                    if playerColorMap.Blue == currentTurnName then
                                        UI.setAttribute(endTurnID, "visibility", "blue")
                                    else
                                        if playerColorMap.Red == currentTurnName then
                                            UI.setAttribute(endTurnID, "visibility", "red")
                                        else
                                            noMatch = true
                                        end
                                    end
                                end
                            end
                        end
                    end
                end
            end
        end
    end
    if noMatch then
        UI.setAttribute(endTurnID, "active", "false")
    else
        UI.setAttribute(endTurnID, "active", "true")
    end
end

function setNextTurn(nextPlayer)
    -- print("nextPlayer: "..nextPlayer)
    if nextPlayer ~= nil and nextPlayer ~= "" then
        nextTurnName = nextPlayer
    else
        nextTurnName = "Nobody"
    end
    
    displayTurnOrder() -- performs api call
    displayTimedEffects() -- perfmors api call
end

function announceTime(currRound)
    -- print("The current time is: "..currRound)
    setUpTurnOrder() -- refresh on end turn
end

function setUpTurnOrder() 
    doAPIrefresh() -- calls APIs in order

    UI.setAttribute(endTurnGmID, "active", "true")
    UI.setAttribute(endCombatID, "active", "true")
    UI.setAttribute(refreshCombatID, "color", REFRESH_GREEN)
    UI.setAttribute(addTimedEffectID, "active", "true")
end

function displayTurnOrder()
    apiGetInit() -- populates initList
end

function getInitOrderListFromJsonStr(jsonStr)
    local initList = {}
    local responseBody = JSON.decode(jsonStr)
    for i, v in pairs( responseBody ) do
        local tempInitList = {Name="",Bloodied=false,Armor=0,Resistances="",Reactions=""}
        local cName = responseBody[i]["name"]
        if responseBody[i]["npc"] then
            if tonumber(responseBody[i]["currentHealth"]) * 2 <= tonumber(responseBody[i]["maximumHealth"]) then
                tempInitList.Bloodied = true
            end
        end
        tempInitList.Name = cName
        -- print("tempInitList name: "..tempInitList.Name)
        table.insert(initList,tempInitList)
    end
    return initList
end

function checkNumberOfInits()
    -- print("checkNumberOfInits")
    if initiativeOrderList == nil then return end
    numberOfPeopleInInitiative = #(initiativeOrderList)
    -- print("displaying "..numberOfPeopleInInitiative.." characters in initiative order")
    if numberOfPeopleInInitiative == 0 then
        closeTurnOrder()
        return 
    end
    UI.setAttribute(initTextID, "active", "true")
    UI.setAttribute(initTextID, "text", "Initiatve Order ("..numberOfPeopleInInitiative.." characters):")
    if numberOfPeopleInInitiative > 5 and endRoundAdded == false then
        table.insert(initiativeOrderList, {Name="↻",Bloodied=false}) -- round end
        numberOfPeopleInInitiative = numberOfPeopleInInitiative + 1
        endRoundAdded= true
    end
    setBlankTurnsInactive()
    getToCurrChar()
    refresh5Inits()
end

function setBlankTurnsInactive()
    if 0 < numberOfPeopleInInitiative and numberOfPeopleInInitiative < 5 then
        UI.setAttribute(initSlot5ID, "active", "false")
        if 0 < numberOfPeopleInInitiative and numberOfPeopleInInitiative < 4 then
            UI.setAttribute(initSlot4ID, "active", "false")
            if 0 < numberOfPeopleInInitiative and numberOfPeopleInInitiative < 3 then
                UI.setAttribute(initSlot3ID, "active", "false")
                if numberOfPeopleInInitiative == 1 then
                    UI.setAttribute(initSlot2ID, "active", "false")
                end
            end
        end
    end
end

function getToCurrChar()
    if isCurrentCharacterInInitList() then
        if pcall(function () 
            while true do
                if isCurrChar(initiativeOrderList[fiveViewed[1]].Name) then return end
                turnOrderLeft()
            end
        end) then 
            -- print("no failure")
        else 
            -- print("failure caught")
            print("There was a problem getting the current character.")
        end
        
    else
        print("There is no current character. End a turn or refresh the server to find the current character.")
    end
    
end

function isCurrentCharacterInInitList()
    for _, v in pairs(initiativeOrderList) do
        -- print(currentTurnName)
        if isCurrChar(v.Name)then return true end
    end
    return false
end

function turnOrderRight() -- switched L/R names to make scrolling more natural
    local firstTurnNum = fiveViewed[1]
    local secondTurnNum = fiveViewed[2]
    local thirdTurnNum = fiveViewed[3]
    local fourthTurnNum = fiveViewed[4]
    local fifthTurnNum = fiveViewed[5]
    -- print("left. fiveViewed numbers: "..firstTurnNum..","..secondTurnNum..","..thirdTurnNum..","..fourthTurnNum..","..fifthTurnNum)

    if fifthTurnNum == numberOfPeopleInInitiative then
        fiveViewed = {secondTurnNum, thirdTurnNum, fourthTurnNum, fifthTurnNum, 1}
    else
        if fourthTurnNum == numberOfPeopleInInitiative then
            fiveViewed = {secondTurnNum, thirdTurnNum, fourthTurnNum, 1, 2}
        else
            if thirdTurnNum == numberOfPeopleInInitiative then
                fiveViewed = {secondTurnNum, thirdTurnNum, 1, 2, 3}
            else
                if secondTurnNum == numberOfPeopleInInitiative then
                    fiveViewed = {secondTurnNum, 1, 2, 3, 4}
                else
                    if firstTurnNum == numberOfPeopleInInitiative then
                        fiveViewed = {1,2,3,4,5} -- full reset
                    else
                        fiveViewed = {firstTurnNum + 1, secondTurnNum + 1, thirdTurnNum + 1, fourthTurnNum + 1, fifthTurnNum + 1} -- regular case
                    end
                end
            end
        end
    end

    refresh5Inits()
end

function turnOrderLeft() -- switched L/R names to make scrolling more natural
    local firstTurnNum = fiveViewed[1]
    local secondTurnNum = fiveViewed[2]
    local thirdTurnNum = fiveViewed[3]
    local fourthTurnNum = fiveViewed[4]
    local fifthTurnNum = fiveViewed[5]
    -- print("right. fiveViewed numbers: "..firstTurnNum..","..secondTurnNum..","..thirdTurnNum..","..fourthTurnNum..","..fifthTurnNum)

    if firstTurnNum == 1 then
        fiveViewed = {numberOfPeopleInInitiative, 1, 2, 3, 4}
    else
        if secondTurnNum == 1 then
            fiveViewed = {numberOfPeopleInInitiative - 1,numberOfPeopleInInitiative, 1, 2, 3}
        else
            if thirdTurnNum == 1 then
                fiveViewed = {numberOfPeopleInInitiative - 2, numberOfPeopleInInitiative - 1, numberOfPeopleInInitiative, 1, 2}
            else 
                if fourthTurnNum == 1 then
                    fiveViewed = {numberOfPeopleInInitiative - 3, numberOfPeopleInInitiative - 2, numberOfPeopleInInitiative - 1, numberOfPeopleInInitiative, 1}
                else
                    if fifthTurnNum == 1 then
                        fiveViewed = {numberOfPeopleInInitiative - 4, numberOfPeopleInInitiative - 3, numberOfPeopleInInitiative - 2, numberOfPeopleInInitiative - 1, numberOfPeopleInInitiative}
                    else
                        fiveViewed = {firstTurnNum - 1, secondTurnNum - 1, thirdTurnNum - 1, fourthTurnNum - 1, fifthTurnNum - 1} -- regular case 
                    end
                end
            end
        end
    end

    refresh5Inits()
end

function refresh5Inits()
    -- print("refresh5Inits")
    local first = fiveViewed[1]
    local second = fiveViewed[2]
    local third = fiveViewed[3]
    local fourth = fiveViewed[4]
    local fifth = fiveViewed[5]

    if numberOfPeopleInInitiative >= 1 then
        makeInitTextButton(initSlot1ID,initiativeOrderList[first])
    end
    if numberOfPeopleInInitiative >= 2 then
        makeInitTextButton(initSlot2ID, initiativeOrderList[second])
    end
    if numberOfPeopleInInitiative >= 3 then
        makeInitTextButton(initSlot3ID, initiativeOrderList[third])
    end
    if numberOfPeopleInInitiative >= 4 then
        makeInitTextButton(initSlot4ID, initiativeOrderList[fourth])
    end
    if numberOfPeopleInInitiative >= 5 then
        makeInitTextButton(initSlot5ID, initiativeOrderList[fifth])
    end
    if numberOfPeopleInInitiative > 5 then -- have to choose 5 based on the buttons
        UI.setAttribute(turnLeftButtonID, "active", "true")
        UI.setAttribute(turnRightButtonID, "active", "true")
    else
        UI.setAttribute(turnLeftButtonID, "active", "false")
        UI.setAttribute(turnRightButtonID, "active", "false")
    end
end

function makeInitTextButton(initSlotID, initChar)
    -- print("makeInitTextButton")
    UI.setAttribute(initSlotID, "active", "true")
    local charName
    if pcall(function () 
        charName = initChar.Name
    end) then 
        -- print("no failure")
    else 
        -- print("failure caught")
        charName = "Nobody"
        print("There was a problem getting a character name.")
    end
    -- charName = initChar.name
    -- if charName ~= nil then 
    --     print("name: "..charName) 
    -- end
    UI.setAttribute(initSlotID, "text", charName)
    UI.setAttribute(initSlotID, "resizeTextForBestFit", "false")
   
    if charName == "↻" then
        UI.setAttribute(initSlotID, "colors", CABOOSE_REDs) -- color end red
        UI.setAttribute(initSlotID, "resizeTextForBestFit", "true")
    else
        if isCurrChar(charName) then
            UI.setAttribute(initSlotID, "colors", CURRENT_GOLDs) -- color gold
        else
            if isNextChar(charName) then
                UI.setAttribute(initSlotID, "colors", NPC_PURPLEs) -- color purple
            else
                UI.setAttribute(initSlotID, "colors", DEFAULT_BLUE_GREYs) -- color default, text default
            end
        end
        if initChar ~= nil and initChar.Bloodied then
            UI.setAttribute(initSlotID, "textColor", BLOODIED_RED) -- red text
        else
            UI.setAttribute(initSlotID, "textColor", BLACK)
        end
    end
end

function isCurrChar(char01)
    return currentTurnName == char01
end

function isNextChar(char02)
    return nextTurnName == char02
end

function closeTurnOrder()
    initiativeOrderList = {}
    UI.setAttribute(turnRightButtonID, "active", "false")
    UI.setAttribute(turnLeftButtonID, "active", "false")
    UI.setAttribute(initSlot5ID, "active", "false")
    UI.setAttribute(initSlot2ID, "active", "false")
    UI.setAttribute(initSlot4ID, "active", "false")
    UI.setAttribute(initSlot3ID, "active", "false")
    UI.setAttribute(initSlot1ID, "active", "false")
    UI.setAttribute(initTextID, "active", "false")
end

-- Timed Effect functions:

function addTimedEffect(player)
    local playerName = findPlayerNameFromColor(player.color)
    broadcastToAll(playerName.." is adding a timed effect...")
    setTimedEffectColor(player.color)
    playerNameLabel = playerName.."\'s "
    effectName = ""
    effectTargets = ""
    timedEffectEffect = ""
    effectRounds = "0"
    effectMinutes = "0"
    effectHours = "0"
    effectDays = "0"
    UI.setAttribute(addTimedEffectDurationID, "active", "true")
    UI.setAttribute(addTimedEffectDurationMinutesID, "active", "true")
    UI.setAttribute(addTimedEffectDurationHoursID, "active", "true")
    UI.setAttribute(addTimedEffectDurationDaysID, "active", "true")
    UI.setAttribute(addTimedEffectTargetsID, "active", "true")
    UI.setAttribute(addTimedEffectNameID, "active", "true")
    UI.setAttribute(timedEffectEffectID, "active", "true")
    UI.setAttribute(timedEffectTextID, "active", "true")

    UI.setAttribute(addTimedEffectNameID, "text", "") -- blank out the entries
    UI.setAttribute(addTimedEffectDurationID, "text", "")
    UI.setAttribute(addTimedEffectDurationMinutesID, "text", "")
    UI.setAttribute(addTimedEffectDurationHoursID, "text", "")
    UI.setAttribute(addTimedEffectDurationDaysID, "text", "")
    UI.setAttribute(addTimedEffectTargetsID, "text", "")
    UI.setAttribute(timedEffectEffectID, "text", "")

    UI.setAttribute(cancelTimedEffectID, "active", "true")
    UI.setAttribute(confirmTimedEffectID, "active", "true")
    UI.setAttribute(addTimedEffectID, "visibility", "black") -- hide the button (GM has cancel override)
    UI.setAttribute(addTimedEffectID, "text", "Cancel Timed Effect")
    if isTabletopObject then
        UI.setAttribute(addTimedEffectID, "onClick", objectGuid.."/closeTimedEffectCreator")
    else
        UI.setAttribute(addTimedEffectID, "onClick", "closeTimedEffectCreator")
    end
    UI.setAttribute(addTimedEffectID, "color", DEFAULT_RED_PINK)
end

function setTimedEffectColor(pColor)
    UI.setAttribute(addTimedEffectDurationID, "visibility", pColor)
    UI.setAttribute(addTimedEffectDurationMinutesID, "visibility", pColor)
    UI.setAttribute(addTimedEffectDurationHoursID, "visibility", pColor)
    UI.setAttribute(addTimedEffectDurationDaysID, "visibility", pColor)
    UI.setAttribute(addTimedEffectTargetsID, "visibility", pColor)
    UI.setAttribute(addTimedEffectNameID,"visibility", pColor)
    UI.setAttribute(timedEffectEffectID, "visibility", pColor)
    UI.setAttribute(timedEffectTextID, "visibility", pColor)
    UI.setAttribute(cancelTimedEffectID, "visibility", pColor)
    UI.setAttribute(confirmTimedEffectID, "visibility", pColor)
end

function closeTimedEffectCreator()
    UI.setAttribute(addTimedEffectDurationID, "active", "false")
    UI.setAttribute(addTimedEffectDurationMinutesID, "active", "false")
    UI.setAttribute(addTimedEffectDurationHoursID, "active", "false")
    UI.setAttribute(addTimedEffectDurationDaysID, "active", "false")
    UI.setAttribute(addTimedEffectTargetsID, "active", "false")
    UI.setAttribute(addTimedEffectNameID, "active", "false")
    UI.setAttribute(timedEffectEffectID, "active", "false")
    UI.setAttribute(timedEffectTextID, "active", "false")
    UI.setAttribute(cancelTimedEffectID, "active", "false")
    UI.setAttribute(confirmTimedEffectID, "active", "false")
    UI.setAttribute(addTimedEffectID, "visibility", "") -- show the button to all players again
    UI.setAttribute(addTimedEffectID, "text", "Add Timed Effect")
    if isTabletopObject then
        UI.setAttribute(addTimedEffectID, "onClick", objectGuid.."/addTimedEffect")
    else
        UI.setAttribute(addTimedEffectID, "onClick", "addTimedEffect")
    end
    setTimedEffectColor("black") -- this may be redundant but set to GM only just in case
    UI.setAttribute(addTimedEffectID, "color", PROMPT_BLUE)
end

function addTimedEffectName(player, effName, id)
    effectName = effName
end

function addTimedEffectEffect(player, effectEffect, id)
    timedEffectEffect = effectEffect
end

function addTimedEffectTargets(player, targets, id)
    effectTargets = targets
end

function addTimedEffectDuration(player, duration, id)
    effectRounds = duration
end
function addTimedEffectDurationMinutes(player, duration, id)
    effectMinutes = duration
end
function addTimedEffectDurationHours(player, duration, id)
    effectHours = duration
end
function addTimedEffectDurationDays(player, duration, id)
    effectDays = duration
end

function confirmTimedEffect(player, id)
    if checkIfTimeBad() then -- returning true is an error! (duration not entered)
        broadcastToColor("You cannot create a timed effect without a duration!", player.color)
        return
    end
    timedEffectEndingInitiative = "0"
    if isNotEmpty(playerName) and playerName ~= "Somebody" then
        timedEffectEndingInitiative = apiGetPlayerInitiative(playerName, player)
    else
        addTimedEffectAfterGettingData(player)
    end
    
end

function recievePlayerInitiative(playerInitiative, player)
    timedEffectEndingInitiative = playerInitiative
    addTimedEffectAfterGettingData(player)
end

function addTimedEffectAfterGettingData(player)
    if isEmpty(effectRounds) then
        effectRounds = "0"
    end
    if isEmpty(effectMinutes) then
        effectMinutes = "0"
    end
    if isEmpty(effectHours) then
        effectHours = "0"
    end
    if isEmpty(effectDays) then
        effectDays = "0"
    end
    if isNotEmpty(effectName) then
        timedEffectListToSend = "{\"name\":\""..effectName.."\",\"effect\":\""..timedEffectEffect.."\",\"targets\":\""..effectTargets.."\",\"timeRemaining\": {\"rounds\": "..effectRounds..", \"minutes\": "..effectMinutes..", \"hours\": "..effectHours..", \"days\": "..effectDays..", \"years\": 0}, \"endingInitiative\": "..timedEffectEndingInitiative.."}"
        -- print("timedEffectListToSend: "..timedEffectListToSend)
        apiAddTimedEffect(timedEffectListToSend, player.color)
    else
        broadcastToColor("You cannot create a timed effect without a name!", player.color)
        return
    end
end

function receiveTimedEffects(timedEffectsStr)
    -- print("received: "..timedEffectsStr)
    timedEffectsList = {{Name="",Effect="",Targets="",Duration="",Number=0}}
    timedEffectsList = getTimedEffectListFromJson(timedEffectsStr)
    -- print(timedEffectsList[1].Name)
end

function getTimedEffectListFromJson(timedEffectJsonStr)
    local effectsList = {}
    local responseBody = JSON.decode(timedEffectJsonStr)
    for i, v in pairs( responseBody ) do
        local tempEffectList = {Name="",Effect="",Targets="",Duration="",Number=i}
        tempEffectList.Name = responseBody[i]["name"]
        tempEffectList.Effect = responseBody[i]["effect"]
        tempEffectList.Targets = responseBody[i]["targets"]
        local timeInRounds = responseBody[i]["timeRemaining"]
        tempEffectList.Duration = convertRoundsStrToTimeLeft(timeInRounds)
        table.insert(effectsList,tempEffectList)
    end
    return effectsList
end

function displayTimedEffects() -- appear only in combat, dissapear when combat ends
    -- print("displaying timed effects...")
    apiGetTimedEffects() --calls receiveTimedEffects & checkNumberOfEffects
end

function checkNumberOfEffects()
    if timedEffectsList == nil then return end
    numberOfEffects = #(timedEffectsList)
    -- print("displaying "..numberOfEffects.." timed events")
    if numberOfEffects == 0 then
        closeTimedEffects()
        return
    end
    UI.setAttribute(closeTimedEffectViewerID, "active", "true")
    UI.setAttribute(closeTimedEffectViewerID, "text", "Close Timed Effect Viewer")
    UI.setAttribute(closeTimedEffectViewerID, "color", BUTTON_COLOR_6)
    UI.setAttribute(displayTimedEffectsTextID, "active", "true")
    UI.setAttribute(displayTimedEffectsTextID, "text", "Current Effects ("..numberOfEffects.."):")
    if numberOfEffects > 5  and endEffectAdded == false then
        table.insert(timedEffectsList, {Name="Timed Effects End",Effect="",Targets="",Duration="",Number=0})
        numberOfEffects = numberOfEffects + 1
        endEffectAdded = true
    end
    setBlankTimedEffectsInactive()
    threeViewed = {1,2,3,4,5} -- threeViewed does 5 now (yes its confusing, i'll change the name later)
    refresh3TimedEffects() 
end

function setBlankTimedEffectsInactive()
    if 0 < numberOfEffects and numberOfEffects < 5 then
        UI.setAttribute(timedEffect5ID, "active", "false")
        UI.setAttribute(timedEffect5TargetsID, "active", "false")
        UI.setAttribute(timedEffect5TimeLeftID, "active", "false")
        UI.setAttribute(timedEffect5EffectLabelID, "active", "false")
        UI.setAttribute(timedEffect5TargetsLabelID, "active", "false")
        UI.setAttribute(timedEffect5TimeLeftLabelID, "active", "false")
        if 0 < numberOfEffects and numberOfEffects < 4 then
            UI.setAttribute(timedEffect4ID, "active", "false")
            UI.setAttribute(timedEffect4TargetsID, "active", "false")
            UI.setAttribute(timedEffect4TimeLeftID, "active", "false")
            UI.setAttribute(timedEffect4EffectLabelID, "active", "false")
            UI.setAttribute(timedEffect4TargetsLabelID, "active", "false")
            UI.setAttribute(timedEffect4TimeLeftLabelID, "active", "false")
            if 0 < numberOfEffects and numberOfEffects < 3 then
                UI.setAttribute(timedEffect3ID, "active", "false")
                UI.setAttribute(timedEffect3TargetsID, "active", "false")
                UI.setAttribute(timedEffect3TimeLeftID, "active", "false")
                UI.setAttribute(timedEffect3EffectLabelID, "active", "false")
                UI.setAttribute(timedEffect3TargetsLabelID, "active", "false")
                UI.setAttribute(timedEffect3TimeLeftLabelID, "active", "false")
                if numberOfEffects == 1 then
                    UI.setAttribute(timedEffect2ID, "active", "false")
                    UI.setAttribute(timedEffect2TargetsID, "active", "false")
                    UI.setAttribute(timedEffect2TimeLeftID, "active", "false")
                    UI.setAttribute(timedEffect2EffectLabelID, "active", "false")
                    UI.setAttribute(timedEffect2TargetsLabelID, "active", "false")
                    UI.setAttribute(timedEffect2TimeLeftLabelID, "active", "false")
                end
            end
        end
    end
end

function timedEffectsDown() -- also switched up/down for scroll clarity
    local firstEffectNum = threeViewed[1]
    local secondEffectNum = threeViewed[2]
    local thirdEffectNum = threeViewed[3]
    local fourthEffectNum = threeViewed[4]
    local fifthEffectNum = threeViewed[5]

    -- print("up. threeViewed numbers: "..firstEffectNum..","..secondEffectNum..","..thirdEffectNum..","..fourthEffectNum..","..fifthEffectNum)

    if fifthEffectNum == numberOfEffects then
        threeViewed = {secondEffectNum, thirdEffectNum, fourthEffectNum, fifthEffectNum, 1}
    else
        if fourthEffectNum == numberOfEffects then
            threeViewed = {secondEffectNum, thirdEffectNum, fourthEffectNum, 1, 2}
        else
            if thirdEffectNum == numberOfEffects then
                threeViewed = {secondEffectNum, thirdEffectNum, 1, 2, 3}
            else
                if secondEffectNum == numberOfEffects then
                    threeViewed = {secondEffectNum, 1, 2, 3, 4}
                else
                    if firstEffectNum == numberOfEffects then
                        threeViewed = {1,2,3,4,5} -- full reset
                    else
                        threeViewed = {firstEffectNum + 1, secondEffectNum + 1, thirdEffectNum + 1, fourthEffectNum + 1, fifthEffectNum + 1} -- regular case
                    end
                end
            end
        end
    end

    refresh3TimedEffects()
end

function timedEffectsUp() -- also switched up/down for scroll clarity
    local firstEffectNum = threeViewed[1]
    local secondEffectNum = threeViewed[2]
    local thirdEffectNum = threeViewed[3]
    local fourthEffectNum = threeViewed[4]
    local fifthEffectNum = threeViewed[5]

    -- print("down. threeViewed numbers: "..firstEffectNum..","..secondEffectNum..","..thirdEffectNum..","..fourthEffectNum..","..fifthEffectNum)

    if firstEffectNum == 1 then
        threeViewed = {numberOfEffects, 1, 2, 3, 4}
    else
        if secondEffectNum == 1 then
            threeViewed = {numberOfEffects - 1,numberOfEffects, 1, 2, 3}
        else
            if thirdEffectNum == 1 then
                threeViewed = {numberOfEffects - 2, numberOfEffects - 1, numberOfEffects, 1, 2}
            else 
                if fourthEffectNum == 1 then
                    threeViewed = {numberOfEffects - 3, numberOfEffects - 2, numberOfEffects - 1, numberOfEffects, 1}
                else
                    if fifthEffectNum == 1 then
                        threeViewed = {numberOfEffects - 4, numberOfEffects - 3, numberOfEffects - 2, numberOfEffects - 1, numberOfEffects}
                    else
                        threeViewed = {firstEffectNum - 1, secondEffectNum - 1, thirdEffectNum - 1, fourthEffectNum - 1, fifthEffectNum - 1} -- regular case 
                    end
                end
            end
        end
    end

    refresh3TimedEffects()
end

function refresh3TimedEffects()
    local first = threeViewed[1]
    local second = threeViewed[2]
    local third = threeViewed[3]
    local fourth = threeViewed[4]
    local fifth = threeViewed[5]

    -- printNestedTable(timedEffectsList)

    if numberOfEffects >= 1 then
        makeTimedEffectTextButton(1, timedEffectsList[first])
    end
    if numberOfEffects >= 2 then
        makeTimedEffectTextButton(2, timedEffectsList[second])
    end
    if numberOfEffects >= 3 then
        makeTimedEffectTextButton(3, timedEffectsList[third])
    end
    if numberOfEffects >= 4 then
        makeTimedEffectTextButton(4, timedEffectsList[fourth])
    end
    if numberOfEffects >= 5 then
        makeTimedEffectTextButton(5, timedEffectsList[fifth])
    end

    if numberOfEffects > 5 then -- have to choose 5 based on the buttons
        UI.setAttribute(timedEffectsUpID, "active", "true")
        UI.setAttribute(timedEffectsDownID, "active", "true")
    else
        UI.setAttribute(timedEffectsUpID, "active", "false")
        UI.setAttribute(timedEffectsDownID, "active", "false")
    end
end

function makeTimedEffectTextButton(timedEffectNumber, tempTimedEffectList)
    local timedEffectID = ""
    local timedEffectTargetsID = ""
    local timedEffectsTimeLeftID = ""
    local timedEffectLabelID = ""
    local timedEffectTargetsLabelID = ""
    local timedEffectsTimeLeftLabelID = ""

    if timedEffectNumber == 5 then
        timedEffectID = timedEffect5ID
        timedEffectTargetsID = timedEffect5TargetsID
        timedEffectsTimeLeftID = timedEffect5TimeLeftID
        timedEffectLabelID = timedEffect5EffectLabelID
        timedEffectTargetsLabelID = timedEffect5TimeLeftLabelID
        timedEffectsTimeLeftLabelID = timedEffect5TargetsLabelID
    else
        if timedEffectNumber == 4 then
            timedEffectID = timedEffect4ID
            timedEffectTargetsID = timedEffect4TargetsID
            timedEffectsTimeLeftID = timedEffect4TimeLeftID
            timedEffectLabelID = timedEffect4EffectLabelID
            timedEffectTargetsLabelID = timedEffect4TimeLeftLabelID
            timedEffectsTimeLeftLabelID = timedEffect4TargetsLabelID
        else
            if timedEffectNumber == 3 then
                timedEffectID = timedEffect3ID
                timedEffectTargetsID = timedEffect3TargetsID
                timedEffectsTimeLeftID = timedEffect3TimeLeftID
                timedEffectLabelID = timedEffect3EffectLabelID
                timedEffectTargetsLabelID = timedEffect3TimeLeftLabelID
                timedEffectsTimeLeftLabelID = timedEffect3TargetsLabelID
            else
                if timedEffectNumber == 2 then
                    timedEffectID = timedEffect2ID
                    timedEffectTargetsID = timedEffect2TargetsID
                    timedEffectsTimeLeftID = timedEffect2TimeLeftID
                    timedEffectLabelID = timedEffect2EffectLabelID
                    timedEffectTargetsLabelID = timedEffect2TimeLeftLabelID
                    timedEffectsTimeLeftLabelID = timedEffect2TargetsLabelID
                else
                    if timedEffectNumber == 1 then
                        timedEffectID = timedEffect1ID
                        timedEffectTargetsID = timedEffect1TargetsID
                        timedEffectsTimeLeftID = timedEffect1TimeLeftID
                        timedEffectLabelID = timedEffect1EffectLabelID
                        timedEffectTargetsLabelID = timedEffect1TimeLeftLabelID
                        timedEffectsTimeLeftLabelID = timedEffect1TargetsLabelID
                    end
                end
            end
        end
    end

    if tempTimedEffectList.Name == "Timed Effects End" then
        UI.setAttribute(timedEffectID, "text", "↻")
        UI.setAttribute(timedEffectID, "colors", CABOOSE_REDs) -- color end red
        UI.setAttribute(timedEffectID, "resizeTextForBestFit", "true")
        UI.setAttribute(timedEffectID, "minHeight", "110")
        UI.setAttribute(timedEffectID, "minWidth", "460")
        UI.setAttribute(timedEffectTargetsID, "active", "false")
        UI.setAttribute(timedEffectsTimeLeftID, "active", "false")
        UI.setAttribute(timedEffectLabelID, "active", "false")
        UI.setAttribute(timedEffectTargetsLabelID, "active", "false")
        UI.setAttribute(timedEffectsTimeLeftLabelID, "active", "false")
    else
        UI.setAttribute(timedEffectID, "active", "true")
        local effectText = tempTimedEffectList.Name..": "..tempTimedEffectList.Effect
        UI.setAttribute(timedEffectID, "text", effectText)
        UI.setAttribute(timedEffectID, "colors", DEFAULT_BLUE_GREYs)
        UI.setAttribute(timedEffectID, "minHeight", "50")
        UI.setAttribute(timedEffectID, "minWidth", "360")
        UI.setAttribute(timedEffectID, "resizeTextForBestFit", "false")
        UI.setAttribute(timedEffectTargetsID, "active", "true")
        local effectTargetsText = tempTimedEffectList.Targets
        UI.setAttribute(timedEffectTargetsID, "text", effectTargetsText)
        UI.setAttribute(timedEffectsTimeLeftID, "active", "true")
        local effectTimeLeftText = tempTimedEffectList.Duration
        UI.setAttribute(timedEffectsTimeLeftID, "text", effectTimeLeftText)
        UI.setAttribute(timedEffectLabelID, "active", "true")
        local effectLabeltext = "Effect "..tostring(tempTimedEffectList.Number)..":"
        UI.setAttribute(timedEffectLabelID, "text", effectLabeltext)
        UI.setAttribute(timedEffectTargetsLabelID, "active", "true")
        UI.setAttribute(timedEffectsTimeLeftLabelID, "active", "true")
    end
end

function timedEffectViewToggle()
    if UI.getAttribute(closeTimedEffectViewerID, "text") == "Open Timed Effect Viewer" then
        UI.setAttribute(closeTimedEffectViewerID, "text", "Close Timed Effect Viewer")
        UI.setAttribute(closeTimedEffectViewerID, "color", BUTTON_COLOR_6)
        checkNumberOfEffects() -- show timed effects
    else
        UI.setAttribute(closeTimedEffectViewerID, "text", "Open Timed Effect Viewer")
        UI.setAttribute(closeTimedEffectViewerID, "color", PROMPT_BLUE)
        closeTimedEffects()
    end    
end

function closeTimedEffects()
    UI.setAttribute(timedEffectsUpID, "active", "false")
    UI.setAttribute(timedEffectsDownID, "active", "false")
    UI.setAttribute(timedEffect1ID, "active", "false")
    UI.setAttribute(timedEffect2ID, "active", "false")
    UI.setAttribute(timedEffect3ID, "active", "false")
    UI.setAttribute(timedEffect4ID, "active", "false")
    UI.setAttribute(timedEffect5ID, "active", "false")
    UI.setAttribute(timedEffect1TargetsID, "active", "false")
    UI.setAttribute(timedEffect2TargetsID, "active", "false")
    UI.setAttribute(timedEffect3TargetsID, "active", "false")
    UI.setAttribute(timedEffect4TargetsID, "active", "false")
    UI.setAttribute(timedEffect5TargetsID, "active", "false")
    UI.setAttribute(timedEffect1TimeLeftID, "active", "false")
    UI.setAttribute(timedEffect2TimeLeftID, "active", "false")
    UI.setAttribute(timedEffect3TimeLeftID, "active", "false")
    UI.setAttribute(timedEffect4TimeLeftID, "active", "false")
    UI.setAttribute(timedEffect5TimeLeftID, "active", "false")
    UI.setAttribute(timedEffect1EffectLabelID, "active", "false")
    UI.setAttribute(timedEffect2EffectLabelID, "active", "false")
    UI.setAttribute(timedEffect3EffectLabelID, "active", "false")
    UI.setAttribute(timedEffect4EffectLabelID, "active", "false")
    UI.setAttribute(timedEffect5EffectLabelID, "active", "false")
    UI.setAttribute(timedEffect1TimeLeftLabelID, "active", "false")
    UI.setAttribute(timedEffect2TimeLeftLabelID, "active", "false")
    UI.setAttribute(timedEffect3TimeLeftLabelID, "active", "false")
    UI.setAttribute(timedEffect4TimeLeftLabelID, "active", "false")
    UI.setAttribute(timedEffect5TimeLeftLabelID, "active", "false")
    UI.setAttribute(timedEffect1TargetsLabelID, "active", "false")
    UI.setAttribute(timedEffect2TargetsLabelID, "active", "false")
    UI.setAttribute(timedEffect3TargetsLabelID, "active", "false")
    UI.setAttribute(timedEffect4TargetsLabelID, "active", "false")
    UI.setAttribute(timedEffect5TargetsLabelID, "active", "false")
    UI.setAttribute(displayTimedEffectsTextID, "active", "false")
end

-- Image functions:

function showImage()
    UI.setAttribute(blackImageID, "active", "true")
    UI.setAttribute(closeBlackImageID, "active", "true")
    UI.setAttribute(blueImageID, "active", "true")
    UI.setAttribute(closeBlueImageID, "active", "true")
    UI.setAttribute(purpleImageID, "active", "true")
    UI.setAttribute(closePurpleImageID, "active", "true")
    UI.setAttribute(greenImageID, "active", "true")
    UI.setAttribute(closeGreenImageID, "active", "true")
    UI.setAttribute(orangeImageID, "active", "true")
    UI.setAttribute(closeOrangeImageID, "active", "true")
    UI.setAttribute(tealImageID, "active", "true")
    UI.setAttribute(closeTealImageID, "active", "true")
    UI.setAttribute(brownImageID, "active", "true")
    UI.setAttribute(closeBrownImageID, "active", "true")
    UI.setAttribute(pinkImageID, "active", "true")
    UI.setAttribute(closePinkImageID, "active", "true")
    UI.setAttribute(redImageID, "active", "true")
    UI.setAttribute(closeRedImageID, "active", "true")
    UI.setAttribute(whiteImageID, "active", "true")
    UI.setAttribute(closeWhiteImageID, "active", "true")
    UI.setAttribute(yellowImageID, "active", "true")
    UI.setAttribute(closeYellowImageID, "active", "true")

    local imageUrl = getObjectFromGUID("c28d75").getCustomObject().image;
    if (imageUrl == nil or imageUrl == "") then 
        imageUrl = "https://i.imgur.com/H8fzOCL.jpeg"
    end

    UI.setAttribute(blackImageID, "image", imageUrl)
    UI.setAttribute(blueImageID, "image", imageUrl)
    UI.setAttribute(purpleImageID, "image", imageUrl)
    UI.setAttribute(greenImageID, "image", imageUrl)
    UI.setAttribute(orangeImageID, "image", imageUrl)
    UI.setAttribute(tealImageID, "image", imageUrl)
    UI.setAttribute(brownImageID, "image", imageUrl)
    UI.setAttribute(pinkImageID, "image", imageUrl)
    UI.setAttribute(redImageID, "image", imageUrl)
    UI.setAttribute(whiteImageID, "image", imageUrl)
    UI.setAttribute(yellowImageID, "image", imageUrl)

    UI.setAttribute(showImageID, "active", "false")
end

function closeImageBlack()
    UI.setAttribute(blackImageID, "active", "false")
    UI.setAttribute(closeBlackImageID, "active", "false")
    UI.setAttribute(showImageID, "active", "true")
end

function closeImageBlue()
    UI.setAttribute(blueImageID, "active", "false")
    UI.setAttribute(closeBlueImageID, "active", "false")
    UI.setAttribute(showImageID, "active", "true")
end

function closeImagePurple()
    UI.setAttribute(purpleImageID, "active", "false")
    UI.setAttribute(closePurpleImageID, "active", "false")
    UI.setAttribute(showImageID, "active", "true")
end

function closeImageOrange()
    UI.setAttribute(orangeImageID, "active", "false")
    UI.setAttribute(closeOrangeImageID, "active", "false")
    UI.setAttribute(showImageID, "active", "true")
end

function closeImageGreen()
    UI.setAttribute(greenImageID, "active", "false")
    UI.setAttribute(closeGreenImageID, "active", "false")
    UI.setAttribute(showImageID, "active", "true")
end

function closeImageTeal()
    UI.setAttribute(tealImageID, "active", "false")
    UI.setAttribute(closeTealImageID, "active", "false")
    UI.setAttribute(showImageID, "active", "true")
end

function closeImageBrown()
    UI.setAttribute(brownImageID, "active", "false")
    UI.setAttribute(closeBrownImageID, "active", "false")
    UI.setAttribute(showImageID, "active", "true")
end

function closeImagePink()
    UI.setAttribute(pinkImageID, "active", "false")
    UI.setAttribute(closePinkImageID, "active", "false")
    UI.setAttribute(showImageID, "active", "true")
end

function closeImageRed()
    UI.setAttribute(redImageID, "active", "false")
    UI.setAttribute(closeRedImageID, "active", "false")
    UI.setAttribute(showImageID, "active", "true")
end

function closeImageWhite()
    UI.setAttribute(whiteImageID, "active", "false")
    UI.setAttribute(closeWhiteImageID, "active", "false")
    UI.setAttribute(showImageID, "active", "true")
end

function closeImageYellow()
    UI.setAttribute(yellowImageID, "active", "false")
    UI.setAttribute(closeYellowImageID, "active", "false")
    UI.setAttribute(showImageID, "active", "true")
end

-- API functions:

function doAPIrefresh()
    getCurrentPlayer() -- announces turn & sets End Turn button
    -- goes to call getNextPlayer(), whiich calls the disaply functions
end

function loadPlayerData() -- getPcs
    print("getting PCs...")
    url = "http://" .. IP_ADDRESS .. ":" .. PORT .. GET_PCS_PATH
    -- print("url: " .. url)
    WebRequest.get(url, function(request)
        if request.response_code > 399 then
            print("error: " .. request.text)
        else
            makePcList(request.text);
        end
    end)
end

function apiRollInit()
    url = "http://" .. IP_ADDRESS .. ":" .. PORT .. ROLL_INITIATIVE_PATH
    -- print("url: " .. url)
    headers = {
        ["Content-Type"] = "application/json",
        Accept = "application/json",
    }
    WebRequest.custom(url, "POST", true, initNamesJsonStringToSend, headers, function(request)
        if request.response_code > 399 then
            print("Unable to roll initiative. error: "..request.text)
        else
            -- print("init returned: "..request.text)
            setUpTurnOrder()
        end
    end)
end

function apiGetInit()
    -- print("apiGetInit")
    url = "http://" .. IP_ADDRESS .. ":" .. PORT .. GET_INITIATIVE_PATH
    -- print("url: " .. url)
    WebRequest.get(url, function(request)
        if request.response_code > 399 then
            print("error: " .. request.text)
        else
            initiativeOrderList = getInitOrderListFromJsonStr(request.text)
            
            -- debugging below:
            -- for i, v in pairs(initiativeOrderList) do 
            --     print(initiativeOrderList[i].Name)
            -- end

            endEffectAdded = false -- avoid duplicate end of turn labels
            checkNumberOfInits() -- populates the textButtons
        end
    end)
end

function apiGetPlayerInitiative(playerName, player)
    url = "http://" .. IP_ADDRESS .. ":" .. PORT .. GET_CHARACTER_INITIATIVE_PATH .. "/" .. playerName
    -- print("url: " .. url)
    WebRequest.get(url, function(request)
        if request.response_code > 399 then
            print("Unable to get initiative. error: "..request.text)
        else
            recievePlayerInitiative(request.text, player)
        end
    end)
end

function apiGetTimedEffects()
    url = "http://" .. IP_ADDRESS .. ":" .. PORT .. GET_TIMED_EFFECTS_PATH
    -- print("url: " .. url)
    WebRequest.get(url, function(request)
        if request.response_code > 399 then
            print("error: " .. request.text)
        else
            receiveTimedEffects(request.text) -- convert to a list
            endEffectAdded = false -- avoid duplicate end of turn labels
            checkNumberOfEffects() -- will populate the textButtons
        end
    end)
end

function apiAddTimedEffect(timedEffectList01, pColor)
    url = "http://" .. IP_ADDRESS .. ":" .. PORT .. ADD_TIMED_EFFECT_PATH
    -- print("url: " .. url)
    headers = {
        ["Content-Type"] = "application/json",
        Accept = "application/json",
    }
    WebRequest.custom(url, "POST", true, timedEffectList01, headers, function(request)
        if request.response_code > 399 then
            broadcastToColor("Unable to add timed effect.", pColor)
        else
            broadcastToColor("Added timed effect:\n"..timedEffectList01, pColor)
            closeTimedEffectCreator()
            displayTimedEffects()
        end
    end)
end

function apiGetTimeDetails()
    url = "http://" .. IP_ADDRESS .. ":" .. PORT .. "/getTime"
    -- print("url: " .. url)
    WebRequest.get(url, function(request)
        if request.response_code > 399 then
            print("error: " .. request.text)
        else
            UI.setAttribute(timeDetailsID, "text", "Current time: \n"..shaveString(request.text))
        end
    end)
    url = "http://" .. IP_ADDRESS .. ":" .. PORT .. "/getLocation"
    WebRequest.get(url, function(request)
        if request.response_code > 399 then
            print("error: " .. request.text)
        else
            UI.setAttribute(locationID, "text", "Location: \n"..request.text)
        end
    end)
    url = "http://" .. IP_ADDRESS .. ":" .. PORT .. "/getEnvironment"
    WebRequest.get(url, function(request)
        if request.response_code > 399 then
            print("error: " .. request.text)
        else
            UI.setAttribute(environmentID, "text", "Environment: \n"..request.text)
        end
    end)
end

function getCurrentPlayer()
    url = "http://" .. IP_ADDRESS .. ":" .. PORT .. GET_CURRENT_PLAYER_PATH
    -- print("url: " .. url)
    WebRequest.get(url, function(request)
        if request.response_code > 399 then
            print("error: " .. request.text)
        else
            prepTurnAnnounce(request.text)
            
        end
    end)
end

function prepTurnAnnounce(currentPlayerAnnouncement)
    url = "http://" .. IP_ADDRESS .. ":" .. PORT .. GET_PLAYER_IS_NPC_PATH .. "/" .. currentPlayerAnnouncement
    -- print("url: " .. url)
    WebRequest.get(url, function(request)
        if request.response_code > 399 then
            print("error: " .. request.text)
        else
            announceTurn(currentPlayerAnnouncement, request.text);
            getNextPlayer()
        end
    end)
end

function getNextPlayer()
    url = "http://" .. IP_ADDRESS .. ":" .. PORT .. GET_NEXT_PLAYER_PATH
    -- print("url: " .. url)
    WebRequest.get(url, function(request)
        if request.response_code > 399 then
            print("error: " .. request.text)
        else
            setNextTurn(request.text);
        end
    end)
end

function apiEndTurn()
    url = "http://" .. IP_ADDRESS .. ":" .. PORT .. NEXT_TURN_PATH
    -- print("url: " .. url)
    WebRequest.get(url, function(request)
        if request.response_code > 399 then
            print("error: " .. request.text)
        else
            announceTime(request.text);
        end
    end)
end

-- Utility functions:

function findPlayerNameFromColor(playerColor)
    -- find player name
    playerName = ""
    -- print("player color: " .. playerColor)
    if playerColor == "Purple" then
        playerName = playerColorMap.Purple
    else
        if playerColor == "Red" then
            playerName = playerColorMap.Red
        else
            if playerColor == "Blue" then
                playerName = playerColorMap.Blue
            else
                if playerColor == "Orange" then
                    playerName = playerColorMap.Orange
                else
                    if playerColor == "Green" then
                        playerName = playerColorMap.Green
                    else
                        if playerColor == "Brown" then
                            playerName = playerColorMap.Brown
                        else
                            if playerColor == "Yellow" then
                                playerName = playerColorMap.Yellow
                            else
                                if playerColor == "Teal" then
                                    playerName = playerColorMap.Teal
                                else
                                    if playerColor == "White" then
                                        playerName = playerColorMap.White
                                    else
                                        if playerColor == "Pink" then
                                            playerName = playerColorMap.Pink
                                        end
                                    end
                                end
                            end
                        end
                    end
                end
            end
        end
    end
    if playerName == nil or playerName == "" then
        playerName = "Somebody"
    end
    -- print("player name: " .. playerName)
    return playerName
end

function isPlayerInColorMap(potentialPlayerName)
    return playerColorMap.White == potentialPlayerName or playerColorMap.Red == potentialPlayerName or playerColorMap.Brown == potentialPlayerName or playerColorMap.Orange == potentialPlayerName or playerColorMap.Yellow == potentialPlayerName or playerColorMap.Green == potentialPlayerName or playerColorMap.Teal == potentialPlayerName or playerColorMap.Blue == potentialPlayerName or playerColorMap.Purple == potentialPlayerName or playerColorMap.Pink == potentialPlayerName
end

function shaveString(inputstr)
    return string.sub(inputstr, 2, string.len(inputstr)-1)
end

function mysplit (inputstr, sep)
    if sep == nil then
            sep = "%s"
    end
    local t={}
    for str in string.gmatch(inputstr, "([^"..sep.."]+)") do
            table.insert(t, str)
    end
    return t
end

function isNotEmpty(s)
    return s ~= nil and s ~= ''
end

function isEmpty(s)
    return s == nil or s == ''
end

function printNestedTable(inputTable) -- for testing & debugging only
    print("inputTable: "..tostring(inputTable))
    if inputTable == nil or #(inputTable) == 0 then return end
     for tableNum,tempTable in ipairs(inputTable) do
        print("table "..tableNum..": "..tostring(tempTable))
        for itemNum,item in pairs(tempTable) do
            print("item "..itemNum..": "..item)
        end
     end
end

function checkIfTimeBad()
    local roundNum = tonumber(effectRounds)
    if roundNum == nil then roundNum = 0 end
    local minNum = tonumber(effectMinutes)
    if minNum == nil then minNum = 0 end
    local hourNum = tonumber(effectHours)
    if hourNum == nil then hourNum = 0 end
    local dayNum = tonumber(effectDays)
    if dayNum == nil then dayNum = 0 end
    -- print("rounds: "..tostring(roundNum)..", minutes: "..tostring(minNum)..", hours: "..tostring(hourNum)..", days: "..tostring(dayNum))
    local totalDuration = roundNum + minNum*10 + hourNum*600 + dayNum*14400
    if totalDuration < 1 then
        return true -- returning true is an error! (duration not entered)
    end
    return false
end

function convertRoundsStrToTimeLeft(durationStringRounds)
    local fullTimeNum = tonumber(durationStringRounds)
    local timeStr = ""
    local years = 0
    local days = 0
    local hours = 0
    local minutes = 0
    local rounds = 0
    if fullTimeNum ~= nil and fullTimeNum > 0 then
        if fullTimeNum >= 5256000 then
            years = math.floor(fullTimeNum / 5256000)
            -- print("fullTimeNum pre mod 5256000: "..fullTimeNum)
            fullTimeNum = fullTimeNum % 5256000
            -- print("fullTimeNum post mod 5256000: "..fullTimeNum)
        end
        if fullTimeNum >= 14400 then
            days = math.floor(fullTimeNum / 14400)
            fullTimeNum = fullTimeNum % 14400
        end
        if fullTimeNum >= 600 then
            hours = math.floor(fullTimeNum / 600)
            -- print("fullTimeNum pre mod 600: "..fullTimeNum)
            fullTimeNum = fullTimeNum % 600
            -- print("fullTimeNum post mod 600: "..fullTimeNum)
        end
        if fullTimeNum >= 10 then
            minutes = math.floor(fullTimeNum / 10)
            rounds = fullTimeNum % 10
        end
        if fullTimeNum < 10 then
            rounds = fullTimeNum
        end
        -- print("time conversion of "..fullTimeNum)
        -- print("years: "..years)
        -- print("days: "..days)
        -- print("hours: "..hours)
        -- print("minutes: "..minutes)
        -- print("rounds: "..rounds)
        local yearsStr = ""
        local daysStr = ""
        local hoursStr = ""
        local minutesStr = ""
        local roundsStr = ""
        if rounds > 0 then
            if rounds == 1 then
                roundsStr = tostring(rounds).." round  "
            else
                roundsStr = tostring(rounds).." rounds  "
            end
        end
        if minutes > 0 then
            if minutes == 1 then
                minutesStr = tostring(minutes).." minute, "
            else
                minutesStr = tostring(minutes).." minutes, "
            end            
        end
        if hours > 0 then
            if hours == 1 then
                hoursStr = tostring(hours).." hour, "
            else
                hoursStr = tostring(hours).." hours, "
            end
        end
        if days > 0 then
            if days == 1 then
                daysStr = tostring(days).." day, "
            else
                daysStr = tostring(days).." days, "
            end
        end
        if years > 0 then
            if years == 1 then
                yearsStr = tostring(years).." year, "
            else
                yearsStr = tostring(years).." years, "
            end
        end
        if rounds > 0 then
            if years + days + hours + minutes > 0 then
                timeStr = yearsStr..daysStr..hoursStr..string.sub(minutesStr, 1, string.len(minutesStr)-2).." & "..roundsStr
            else
                timeStr = roundsStr
            end
        else -- now we know there are no rounds, lastStr could be minutes
            if minutes > 0 then
                if years + days + hours > 0 then
                    timeStr = yearsStr..daysStr..string.sub(hoursStr, 1, string.len(hoursStr)-2).." & "..minutesStr
                else
                    timeStr = minutesStr
                end
            else -- no minutes or rounds, lastStr could be hours
                if hours > 0 then
                    if years + days > 0 then
                        timeStr = yearsStr..string.sub(daysStr, 1, string.len(daysStr)-2).." & "..hoursStr
                    else
                        timeStr = hoursStr
                    end
                else -- no hours either, lastStr could be days
                    if days > 0 then
                        if years > 0 then
                            timeStr = string.sub(yearsStr, 1, string.len(yearsStr)-2).." & "..daysStr
                        else
                            timeStr = daysStr
                        end
                    else -- no days either, lastStr cannot be years tho
                        timeStr = yearsStr
                    end
                end
            end
        end
        
        timeStr = string.sub(timeStr, 1, string.len(timeStr)-2)
    else
        timeStr = "0 rounds" -- this should never happen
    end
    return timeStr
end

-- Object XML Utility functions:

-- function onObjectSpawn(object)
--     -- print("GUID: "..object.getGUID()) -- this would reset the GUID every time a new object spawns, causing errors.
-- end

function setupObjectXmlUI()
    local newXML = replaceXmlGuid(XML_STRING, objectGuid)
    UI.setXml(newXML)
end

function replaceXmlGuid(xml, guid)
    -- print("Old XML: "..xml)
    xml = xml:gsub(XML_REPLACE, [[onClick = "]]..guid..[[/]]) -- replace onClick = "
    xml = xml:gsub(XML_REPLACE_2, [[onEndEdit = "]]..guid..[[/]]) -- replace onEndEdit = "
    xml = xml:gsub(XML_REPLACE_3, [[onSubmit = "]]..guid..[[/]]) -- replace onSubmit = "
    -- print("New XML: "..xml)
    return xml
end
