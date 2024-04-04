// Dungeons & Dragons Combat Asistant
// Created by Joshua Haynes December 2023

import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { ThemeProvider, createTheme } from '@mui/material/styles';

import "./global.css";
import MainBackground from './Design/Images/arena.jpg';

import Arena from './Pages/Arena.js';
import Combat from './Pages/Combat.js';

import { Colors } from './Design/Colors/Colors';
import { grey } from '@mui/material/colors';
import CharacterPage from './Pages/CharacterPage.js';

const darkTheme = createTheme({
  palette: {
    primary: { // display
      light: grey[900],
      main: Colors.displayColor2,
      dark: grey[200],
      contrastText: '#000',
    },
    secondary: { // speed / time
      light: grey[900],
      main: Colors.timeColor2,
      dark: grey[200],
      contrastText: '#000',
    },
    error: { // health / weaknesses
      light: grey[900],
      main: Colors.healthColor2,
      dark: grey[200],
      contrastText: '#000',
    },
    warning: { // attacks
      light: grey[900],
      main: Colors.attackColor2,
      dark: grey[200],
      contrastText: '#000',
    },
    info: { // defenses
      light: grey[900],
      main: Colors.defenseColor2,
      dark: grey[200],
      contrastText: '#000',
    },
    success: { // other / grey
      light: grey[900],
      main: Colors.otherColor2,
      dark: grey[200],
      contrastText: '#000',
    },
  },
});

const isPortForwardingEnabled = false; // set to true if port forwarding is enabled

window.apiURL = 'http://localhost:9001'; // server port here

if (isPortForwardingEnabled) {
  getExternalIp();
} else {
  checkServer();
}

async function getExternalIp() {
  await fetch('https://icanhazip.com', {
    method: 'GET',
  })
  .then(response => response.text())
  .then(response => {
    console.log('Found external ip: ', response);
    window.apiURL = 'http://'+response+':9001'
    checkServer();
  })
  .catch(err => {
    console.log(err);
  });
}

async function checkServer() {
  await fetch(window.apiURL + '/', {
    method: 'GET',
  })
  .then((response) => {
    if (response.status === 200){
      console.log('Server is running.');
      startApp();
    } else {
      console.log('Server is not running.');
      alert('Cannot connect to the server. Make sure it is running.');
    }
  })
  .catch(err => {
    console.log('Server error:', err);
    alert('Cannot connect to the server. Make sure it is running.');
  });
}

function startApp() {
  console.log('Starting D&DCombatAssistant.');
  const root = ReactDOM.createRoot(document.getElementById('root'));
  root.render(
    <ThemeProvider theme={darkTheme}>
      <BrowserRouter>
        <div align="center"
          style={{
            justifyContent: "center", // horizontal
            alignItems: "center", // vertical
            display: "flex",
            backgroundImage: `url(${MainBackground})`,
            backgroundPosition: 'center',
            backgroundSize: 'cover',
            backgroundRepeat: 'no-repeat',
            width: '100vw',
            height: '100vh'
          }}
        >
          <Routes>
            <Route exact path="/" element={<Arena />}></Route>
            <Route exact path="/Combat" element={<Combat />}></Route>
            <Route exact path="/CharacterPage" element={<CharacterPage />}></Route>
          </Routes>
        </div>
      </BrowserRouter>
    </ThemeProvider>
  );
}
