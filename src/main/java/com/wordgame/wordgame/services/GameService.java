package com.wordgame.wordgame.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wordgame.wordgame.domain.Game;
import com.wordgame.wordgame.domain.Stats;

@Service
public class GameService {
    private final StatsService statsService; 
    private Game game;

    @Autowired
    public GameService(StatsService statsService) {
        this.statsService = statsService;
    }

    public void initializeGame() {
        this.game = new Game();
    }

    // Add user to an existing game for stats
    public void addUserToGame(String username) {
        if (username != null && !username.isEmpty() && this.game != null) {
            Stats userStats = statsService.findStatsByUser(username)
                .orElse(new Stats(username));
            this.game.setUserStats(userStats);
        }
    }

    // Example usage within makeGuess
    public int[][] makeGuess(String guess, String username) {
        return game.checkGuess(guess);
    }

    public void resetGame() {
        game.resetGame();
    }

    public int getGuessesAttempted() {
        return game.getGuessesAttempted();
    }

    public boolean isGameOver() {
        return game.getGameOver();
    }

    public boolean[] getWordsGuessed() {
        return game.getWordsGuessed();
    }

    public void setAnswerWords(String[] testAnswers) {
        game.setAnswerWords(testAnswers);
    }

    public String[] getAnswerWords() {
        return game.getAnswerWords();
    }

    public Stats getUserStats() {
        return game.getUserStats(); 
    }
}
