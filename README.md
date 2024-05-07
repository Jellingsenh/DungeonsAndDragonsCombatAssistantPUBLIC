Dungeons & Dragons Combat Assistant<br>
Created by Joshua Haynes<br>
26 December 2021<br>
Version: 2.4<br>
Updated: 7 May 2024<br>

--------------------

This is a game to help streamline D&D combat for DMs.<br>
It will help keep track of character health, damage, armor, abilities, skills, spells, and many other things.<br>
The purpose of the game is to free your brain of the details of combat so you can be more immersed in the role-playing.<br>

It is best used with real dice & having a DM to oversee everything & tweak characters as needed.<br>
I designed it to be highly customizable & less rigid in case a player does something unexpected.<br>

--------------------

How to setup & run the D&D Combat Assistant:<br>
-> There are 3 main parts: a PostgeSQL databasem a Java/Maven API server, and a React webapp.<br>

1) Setup database:<br>
&nbsp;&nbsp;a) download & install PostgreSQL: https://www.postgresql.org/download/<br>
&nbsp;&nbsp;&nbsp;&nbsp;i) Launch PGAdmin to set up a new SQL database (make sure to remember the databse port & password).<br>
&nbsp;&nbsp;b) download & install DBeaver: https://dbeaver.io/download/<br>
&nbsp;&nbsp;&nbsp;&nbsp;ii) connect to the postgres database using the port & password from before.<br>

--> The PostgreSQL database is where the data is saved. DBeaver is a tool that lets you easily view the data in the database.<br>

2) Setup server:<br>
&nbsp;&nbsp;a) download & install Eclipse: https://www.eclipse.org/downloads/<br>
&nbsp;&nbsp;b) download the DungeonsAndDragonsCombatAssistant codebase (above)<br>
&nbsp;&nbsp;c) Import the project in Eclipse:<br>
&nbsp;&nbsp;&nbsp;&nbsp;i) Select File -> Import -> Maven -> Existing Maven Projects.<br>
&nbsp;&nbsp;&nbsp;&nbsp;ii) Select the 'API/rest-service' directory.<br>
&nbsp;&nbsp;&nbsp;&nbsp;iii) Set up a run configuration (look next to the green arrow). Java Application, Project = rest-service, Main class = RestServiceApplication.java.<br>
&nbsp;&nbsp;&nbsp;&nbsp;iv) Set the database port & password in 'API/rest-service/properties.txt'.<br>

--> Now you can just click 'Run' (the green arrow) to launch the server!<br>

3) Setup website:<br>
Windows only:<br>
&nbsp;&nbsp;a) Get Windows subsystem for Linux: https://learn.microsoft.com/en-us/windows/wsl/install.<br>
&nbsp;&nbsp;b) Ensure Ubuntu is installed.<br>
All devices:<br>
&nbsp;&nbsp;c) install npm (open Ubuntu for Windows or open Terminal for Mac, and enter the commands below):<br>
&nbsp;&nbsp;&nbsp;&nbsp;i) `sudo apt update && sudo apt upgrade`<br>
&nbsp;&nbsp;&nbsp;&nbsp;ii) `sudo apt install npm`<br>
&nbsp;&nbsp;&nbsp;&nbsp;iii) `sudo apt install nodejs`<br>
&nbsp;&nbsp;&nbsp;&nbsp;iv) `curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.0/install.sh | bash`<br>
&nbsp;&nbsp;&nbsp;&nbsp;v) restart Ubuntu<br>
&nbsp;&nbsp;&nbsp;&nbsp;vi) `nvm install 20.10.0`<br>
&nbsp;&nbsp;d) navigate to the 'GUI/dnd-gui' directory ('cd /mnt/c/path_to_folder'), then enter the following commands:<br>
&nbsp;&nbsp;&nbsp;&nbsp;i) `sudo npm install`<br>
&nbsp;&nbsp;&nbsp;&nbsp;ii) `sudo npm install -g serve`<br>
&nbsp;&nbsp;&nbsp;&nbsp;iii) `sudo npm run build`<br>

--> To run the website, open Ubuntu, navigate to the 'GUI/dnd-gui' directory and enter: `serve -s build -p 3005`.<br>

4) Setup Tabletop Simulator UI:<br>
&nbsp;&nbsp;a) Port 9001 needs port forwarding enabled.<br>
&nbsp;&nbsp;b) Open the scripting menu in Tabletop Simulator (Modding -> Scripting).<br>
&nbsp;&nbsp;c) Copy the entire text from 'TableTopSim/Processor.lua' and paste it into the Game section.<br>
&nbsp;&nbsp;d) Copy the entire text from 'TableTopSim/UserInterface.xml' and paste it into the Game section.<br>
&nbsp;&nbsp;e) Click 'Apply & Save'.<br>

--> Make a game save in Tabletop Simulator (Games -> Save As), and when you want to use the UI, just load that game.<br>

5) Optional: dowload Postman: https://www.postman.com/downloads/<br>
&nbsp;&nbsp;a) You use the collection in 'API/Postman Collection' to test.<br>
&nbsp;&nbsp;b) You can change the months of the year with this collection.<br>

--------------------
