document.addEventListener('DOMContentLoaded', () => {
  initializeGameBoard();
  bindKeyboardInput();
  initializeKeyboard();
  startGame(); 

  const resetButton = document.getElementById('reset');
  resetButton.addEventListener('click', () => {
    window.location.reload();
  });
  
});

let currentGuess = [];
let currentRow = 0;
let foundWords = [false, false];
let gameOver = false; 
const maxGuesses = 8;
const wordLength = 5;
const allowedKeys = 'abcdefghijklmnopqrstuvwxyz';

/**
 * Initalize game board elements 
 */
function initializeGameBoard() {
  const board = document.getElementById('gameBoard');
  for (let i = 0; i < maxGuesses; i++) {
    const row = document.createElement('div');
    row.className = 'game-row';
    for (let x = 0; x < wordLength; x++) {
      const cell = document.createElement('div');
      cell.className = 'game-cell';
      const topHalf = document.createElement('div');
      topHalf.className = 'half-cell top-half';
      const bottomHalf = document.createElement('div');
      bottomHalf.className = 'half-cell bottom-half';
      cell.appendChild(topHalf);
      cell.appendChild(bottomHalf);
      row.appendChild(cell);
    }
    board.appendChild(row);
  }
}

function bindKeyboardInput() {
  document.addEventListener('keydown', handlePhysicalKeyPress);
}

/**
 * Start the game by using the GameController 
 */
async function startGame() {
  const url = '/game/start';
  const params = new URLSearchParams();
  const username = localStorage.getItem("username");
  if (username) {
      params.append("username", username); //Add username to the request if available
  }

  try {
      const response = await fetch(`${url}?${params.toString()}`, { method: 'POST' });
      if (!response.ok) {
          throw new Error(`Failed to start game for ${username ? username : 'guest'}.`);
      }
      console.log(`Game started for ${username ? username : 'guest'}.`);
  } catch (error) {
      console.error(error);
  }
}

/**
 * Submits a guess and processes results 
 * @returns 
 */
async function submitGuess() {
  if(currentRow >= maxGuesses){
    console.log('Game is already over'); 
    return; 
  }

  console.log('Guess submitted:', currentGuess.join(''));
  try {
    const response = await fetch(`/game/guess?guess=${encodeURIComponent(currentGuess.join(''))}`, {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
      },
    });

    if (response.ok) {
      const result = await response.json();
      
      //Check that results is a 2D array
      if (Array.isArray(result) && result.every(Array.isArray)) {
        console.log(result);
        colorCells(result);
        colorKeyboard(result);
        checkResults(result); 
      } else {
        console.log('Guess not in word list. Try again.');
        const guessMessage = document.getElementById('guessMessage');
        guessMessage.textContent = 'Invalid guess. Try again.'; // Set the message
        setTimeout(() => guessMessage.textContent = '', 3000); // Clear message after 3 seconds
        return;
      }

      currentGuess = [];
      currentRow++;
      updateDisplay();

      const guessMessage = document.getElementById('guessMessage');
      if (currentRow >= maxGuesses) {
        guessMessage.textContent = 'You ran out of guests. Game over.'; // Set the message
        showResetButton();
      } else if (foundWords[0] && foundWords[1]){
        guessMessage.textContent = 'Congrats, you solved the duordle!'
        showResetButton(); 
      }
    } else {
      console.error('Failed to submit guess.');
    }
  } catch (error) {
    console.error('Error submitting guess:', error);
  }
}

function showResetButton() {
  document.getElementById('reset').style.display = 'block';
}

/**
 * Check the results of the guess 
 * @param {*} result 
 */
function checkResults(result){
  for(let x = 0; x < 2; x++){
    let sum = 0;
    for(let y = 0; y < 5; y++){
      sum += result[x][y];
    }
    if(sum == 10 && x == 0) foundWords[0] = true; 
    if(sum == 10 && x == 1) foundWords[1] = true;   
  }
}

/**
 * Colors the cells based on the results of a guess 
 */
function colorCells(result) {
  const rowCells = document.querySelectorAll(`.game-row:nth-child(${currentRow + 1}) .game-cell`);
  rowCells.forEach((cell, index) => {
    const topHalf = cell.querySelector('.top-half');
    const bottomHalf = cell.querySelector('.bottom-half');
    
    colorCell(topHalf, result[0][index]);
    colorCell(bottomHalf, result[1][index]);
  });
}

/*
* Helper method For color cells 
*/
function colorCell(cell, result) {
  let color;
  switch(result) {
    case 2:
      color = 'LightGreen';
      break;
    case 1:
      color = 'yellow';
      break;
    case 0:
      color = 'LightGray';
      break;
  }
  cell.style.backgroundColor = color;
}
/**
 * Method to input physical key presses 
 */
function handlePhysicalKeyPress(event) {
  const letter = event.key;
  if (allowedKeys.includes(letter) && currentGuess.length < wordLength && (!foundWords[0] || !foundWords[1])) {
    currentGuess.push(letter.toUpperCase());
    updateDisplay();
  } else if (event.key === 'Enter' && currentGuess.length === wordLength) {
    submitGuess();
  } else if (event.key === 'Backspace' && currentGuess.length > 0) {
    currentGuess.pop();
    updateDisplay();
  }
}

/**
 * Updates display to show current information
 */
function updateDisplay() {
  const rowTiles = document.querySelectorAll(`.game-row:nth-child(${currentRow + 1}) .game-cell .top-half`);
  const bottomRowTiles = document.querySelectorAll(`.game-row:nth-child(${currentRow + 1}) .game-cell .bottom-half`);
  rowTiles.forEach((tile, index) => {
    tile.textContent = currentGuess[index] || '';
  });
  bottomRowTiles.forEach((tile, index) => {
    tile.textContent = currentGuess[index] || '';
  });
}

/**
 * Creates Display Keyboard used to help keep track of what letters have been used 
 */
function initializeKeyboard() {
  const keyboardContainer = document.getElementById('keyboardContainer');
  if (!keyboardContainer) return;

  const keyboardLayout = 'abcdefghijklmnopqrstuvwxyz';
  keyboardLayout.split('').forEach(letter => {
    const key = document.createElement('div');
    key.className = 'keyboard-key';
    key.id = `key-${letter}`;

    //Top half
    const topHalf = document.createElement('div');
    topHalf.className = 'key-half top-half';
    const topHalfText = document.createElement('span'); 
    topHalfText.textContent = letter.toUpperCase();
    topHalf.appendChild(topHalfText);

    //Bottom half
    const bottomHalf = document.createElement('div');
    bottomHalf.className = 'key-half bottom-half';
    const bottomHalfText = document.createElement('span'); 
    bottomHalfText.textContent = letter.toUpperCase();
    bottomHalf.appendChild(bottomHalfText);

    //Combine Keys
    key.appendChild(topHalf);
    key.appendChild(bottomHalf);
  
    keyboardContainer.appendChild(key);
  });
}

/**
 * Helper method for initalizeKeyboard and is used to color it 
 */
function colorKeyboard(result) {
  result[0].forEach((colorCode, index) => {
    const letter = currentGuess[index].toLowerCase();
    const keyElement = document.getElementById(`key-${letter}`);
    if (keyElement) {
      const topHalf = keyElement.querySelector('.top-half');
      topHalf.style.backgroundColor = getColorFromCode(colorCode);
    }
  });

  result[1].forEach((colorCode, index) => {
    const letter = currentGuess[index].toLowerCase();
    const keyElement = document.getElementById(`key-${letter}`);
    if (keyElement) {
      const bottomHalf = keyElement.querySelector('.bottom-half');
      bottomHalf.style.backgroundColor = getColorFromCode(colorCode);
    }
  });
}

/*
* Helper method for colors 
*/
function getColorFromCode(code) {
  switch (code) {
    case 2: return 'LightGreen';
    case 1: return 'yellow';
    case 0: return 'LightGray';
    default: return ''; // Use a default or clear color if necessary
  }
}
