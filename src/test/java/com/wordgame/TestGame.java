package com.wordgame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wordgame.wordgame.TestDataUtil;
import com.wordgame.wordgame.domain.Game;
import com.wordgame.wordgame.domain.Stats;
import com.wordgame.wordgame.domain.User;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;

public class TestGame {
    private Game game;
    private Game gameWithStats; 
    private User user; 
    private Stats stats; 

    @BeforeEach
    void setUp(){
        user = TestDataUtil.createTestUser1();
        stats = TestDataUtil.createTestStats1(); 

        game = new Game(); 
        gameWithStats = new Game(stats); 
    }

    @Test
    public void testGenerateValidWordList(){
        HashSet<String> list = game.generateValidWordList(); 
        String testWord = "which";
        assertFalse(list.isEmpty(), "Valid word list should not be empty"); 
        assertTrue(list.contains(testWord), "which should be in the list"); 
        assertFalse(list.contains("no"), "no should not be in the valid words list"); 
    }

    @Test 
    public void testGenerateAnswersList(){
        String[] answers = game.generateAnswerWords();
        assertFalse(answers[0] == null, "The first answer word should not be null"); 
        assertFalse(answers[1] == null, "The second answer word should not be null"); 
    }

    @Test 
    public void testCheckInvalidGuess(){
        assertTrue(game.checkGuess("no") == null);
    }

    @Test
    public void testCheckGuess(){
        String[] mockAnswerWords = new String[2];
        mockAnswerWords[0] = "apple"; 
        mockAnswerWords[1] = "berry";
        game.setAnswerWords(mockAnswerWords);
        int[][] results = game.checkGuess("apple"); 
        int[][] expected = {{2, 2, 2, 2, 2}, {0, 0, 0, 0, 1}}; 
        assertTrue(Arrays.deepEquals(results, expected), "Results do not match expected");
        assertTrue(game.getWordsGuessed()[0] == true, "First word should have been set to guessed");
        assertTrue(game.getWordsGuessed()[1] == false, "Second word should not have been guessed");
    }

    @Test
    public void testCheckGuessNoneCorrect(){
        String[] mockAnswerWords = new String[2];
        mockAnswerWords[0] = "apple"; 
        mockAnswerWords[1] = "berry";
        game.setAnswerWords(mockAnswerWords);
        int[][] results = game.checkGuess("which"); 
        int[][] expected = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}}; 
        assertTrue(Arrays.deepEquals(results, expected), "Results do not match expected");
        assertTrue(game.getWordsGuessed()[0] == false, "First word should not have been set to guessed");
        assertTrue(game.getWordsGuessed()[1] == false, "Second word should not have been guessed");
    }

    @Test 
    public void testCheckGuessWithStats(){
        String[] mockAnswerWords = new String[2];
        mockAnswerWords[0] = "apple"; 
        mockAnswerWords[1] = "berry";
        gameWithStats.setAnswerWords(mockAnswerWords);
        int[][] results = gameWithStats.checkGuess("apple"); 
        int[][] expected = {{2, 2, 2, 2, 2}, {0, 0, 0, 0, 1}}; 
        assertTrue(Arrays.deepEquals(results, expected), "Results do not match expected");
        assertTrue(gameWithStats.getWordsGuessed()[0] == true, "First word should have been set to guessed");
        assertTrue(gameWithStats.getWordsGuessed()[1] == false, "Second word should not have been guessed");
        assertTrue(stats.getGuessesMade() == 6, "Guesses made should of been incremented"); 
    }

    @Test
    public void testCheckWinWithStats(){
        String[] mockAnswerWords = new String[2];
        mockAnswerWords[0] = "apple"; 
        mockAnswerWords[1] = "berry";
        gameWithStats.setAnswerWords(mockAnswerWords);
        gameWithStats.checkGuess("apple"); 
        assertTrue(gameWithStats.getWordsGuessed()[0] == true, "First word should have been set to guessed");
        assertTrue(gameWithStats.getWordsGuessed()[1] == false, "Second word should not have been guessed");
        assertTrue(stats.getGuessesMade() == 6, "Guesses made should of been incremented"); 

        gameWithStats.checkGuess("berry"); 
        assertTrue(gameWithStats.getWordsGuessed()[0], "First word should still be set to true");
        assertTrue(gameWithStats.getWordsGuessed()[1], "Second word should have been set to true"); 
        assertTrue(stats.getGuessesMade() == 7, "Guesses should have been incremented"); 
        assertTrue(stats.getGamesWon() == 1, "Win should have been added"); 
        assertTrue(stats.getGamesPlayed() == 2, "Games played should have been incremented");
    }

    @Test
    public void testResetGame(){
        game.checkGuess("apple"); 
        game.checkGuess("apple"); 
        game.checkGuess("apple"); 
        assertTrue(game.getGuessesAttempted() == 3, "Game should have had 3 guesses attempted"); 
        game.resetGame();
        assertTrue(game.getGuessesAttempted() == 0 && !game.getWordsGuessed()[0] 
            && !game.getWordsGuessed()[1], "Game should have been reset"); 
    }
}
