package com.wordgame.wordgame.controllers;

import com.wordgame.wordgame.services.GameService;
import com.wordgame.wordgame.services.StatsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;
    private final StatsService statsService; 

    @Autowired
    public GameController(GameService gameService, StatsService statsService) {
        this.gameService = gameService;
        this.statsService = statsService; 
    }

    @PostMapping("/start")
    public ResponseEntity<String> startGame(@RequestParam(value = "username", required = false) String username) {
        gameService.initializeGame();
        if (username != null) {
            gameService.addUserToGame(username);
        }
        return ResponseEntity.ok("Game started for " + (username != null ? username : "guest") + ".");
    }

    @PostMapping("/guess")
    public ResponseEntity<?> makeGuess(@RequestParam("guess") String guess, @RequestParam(value = "username", required = false) String username) {
        try {
            int[][] result = gameService.makeGuess(guess, username);
            if (result == null) {
                return ResponseEntity.ok().body("{}");
            }
            
            //Update the game stats after every guess 
            if(gameService.getUserStats() != null){
                statsService.updateStats(gameService.getUserStats());
            }

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\":\"Error processing guess: " + e.getMessage() + "\"}");
        }
    }


    @GetMapping("/status")
    public ResponseEntity<?> getGameStatus() {
        boolean gameOver = gameService.isGameOver();
        int attempts = gameService.getGuessesAttempted();
        boolean[] wordsGuessed = gameService.getWordsGuessed();

        // Simplified status response, adjust as needed for your frontend
        return ResponseEntity.ok(String.format("Game Over: %s, Attempts: %d, Words Guessed: %s",
                gameOver, attempts, wordsGuessed));
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetGame() {
        gameService.resetGame();
        return ResponseEntity.ok("Game has been reset.");
    }
}
