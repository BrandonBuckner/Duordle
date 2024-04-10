package com.wordgame.wordgame.domain;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Game {
    private HashSet<String> validWords; 
    private String[] validWordsArray; 
    private String[] answerWords;
    private boolean[] wordsGuessed = {false, false}; 
    private Stats userStats; 
    private int guessesAttempted; 
    private final int WORDSIZE = 5; 
    private final int GUESSES_ALLOWED = 8; 
    private boolean gameOver = false; 

    public Game(Stats stats){
        userStats = stats; 
        validWords = generateValidWordList(); 
        answerWords = generateAnswerWords(); 
        guessesAttempted = 0; 
    }

    public Game() {
        //If user is a guest 
        userStats = null; 
        validWords = generateValidWordList(); 
        answerWords = generateAnswerWords();  
        guessesAttempted = 0;   
    }

    /**
     * Creates a valid word list to guess and answer on 
     * @return Returns the list 
     */
    public HashSet<String> generateValidWordList() {
        HashSet<String> list = new HashSet<String>();
        try (Scanner scan = new Scanner(new File("src\\main\\java\\com\\wordgame\\wordgame\\domain\\valid-words.txt"))){
            while (scan.hasNext()){
                list.add(scan.next());
            } 
            scan.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        validWordsArray = list.toArray(new String[0]); 
        return list;
    }

    /**
     * Generates Answer words to be guessed on 
     * @return Returns answers 
     */
    public String[] generateAnswerWords(){
        String[] answers = new String[2]; 
        Random random = new Random();
        int index1 = random.nextInt(validWords.size());
        int index2 = random.nextInt(validWords.size()); 
        while(index1 == index2){
            index2 = random.nextInt(validWords.size());
        }
        answers[0] = validWordsArray[index1]; 
        answers[1] = validWordsArray[index2]; 

        return answers; 
    }

    public int getGuessesAttempted(){
        return guessesAttempted; 
    } 

    public boolean[] getWordsGuessed(){
        return wordsGuessed; 
    }

    public String[] getAnswerWords(){
        return answerWords; 
    }

    public boolean getGameOver(){
        return gameOver; 
    }

    private boolean checkIfGuessIsValid(String guess){
        return validWords.contains(guess); 
    }

    public int[][] checkGuess(String guess) {
        //Key: 2 = correct position, 1 = correct letter wrong position, 0 = not in word
        //Format it to always be lower case 
        guess = guess.toLowerCase(); 
        if (!checkIfGuessIsValid(guess) || gameOver) {
            return null; 
        }
        int[][] results = new int[2][WORDSIZE];
        boolean[][] matched = new boolean[2][WORDSIZE]; //Track matched letters 

        for (int x = 0; x < WORDSIZE; x++) {
            char guessChar = guess.charAt(x);
            //Check correct positions first
            if (guessChar == answerWords[0].charAt(x)) {
                results[0][x] = 2;
                matched[0][x] = true; 
            }
            if (guessChar == answerWords[1].charAt(x)) {
                results[1][x] = 2;
                matched[1][x] = true;
            }
        }
    
        //Check for correct letters in wrong position of first word 
        for (int x = 0; x < WORDSIZE; x++) {
            char guessChar = guess.charAt(x);
            for (int y = 0; y < WORDSIZE; y++) {
                if (!matched[0][y] && guessChar == answerWords[0].charAt(y)) {
                    results[0][x] = 1;
                    matched[0][y] = true; //Mark this letter as matched
                    break; //Break after finding if the letter exists in the word, but isn't in the right spot 
                }
            }
        }
        //Check for correct letters in wrong position of word2 
        for(int x = 0; x < WORDSIZE; x++){
            char guessChar = guess.charAt(x);
            for(int y = 0; y < WORDSIZE; y++){
                if (!matched[1][y] && guessChar == answerWords[1].charAt(y)) {
                    results[1][x] = 1;
                    matched[1][y] = true; //Mark this letter as matched
                    break; //Break after finding if the letter exists in the word but isn't in the right spot 
                }
            }
        }

        checkResults(results); 
        return results;
    }    

    /**
     * Private helper method to check results and update stats if applicable 
     * @param results 2D int array to determine locations 
     */
    private void checkResults(int[][] results){
        int sum1 = 0;
        int sum2 = 0; 
        guessesAttempted++; 
        if(userStats != null) userStats.setGuessesMade(userStats.getGuessesMade()+1);
        for(int x = 0; x < WORDSIZE; x++){
            sum1+= results[0][x];
            sum2+= results[1][x];
        }
        if(sum1 == 10) wordsGuessed[0] = true; 
        if(sum2 == 10) wordsGuessed[1] = true; 

        //Counts a game as played as soon as a user makes their first guess 
        if(userStats != null && guessesAttempted == 1) userStats.setGamesPlayed(userStats.getGamesPlayed()+1);

        //Check if they won or game is over 
        if(wordsGuessed[0] && wordsGuessed[1]){
            gameOver = true; 
            if (userStats != null) userStats.setGamesWon(userStats.getGamesWon()+1);
        } else if (guessesAttempted >= GUESSES_ALLOWED){
            gameOver = true; 
        }
    }

    /**
     * Reset the game 
     */
    public void resetGame(){
        gameOver = false; 
        guessesAttempted = 0; 
        answerWords = generateAnswerWords(); 
        wordsGuessed[0] = false; 
        wordsGuessed[1] = false;  
    }   

    public void setUserStats(Stats userStats){
        this.userStats = userStats; 
    }

    public Stats getUserStats(){
        return userStats; 
    }
    /**
     * This method is for testing purposes only! 
     */
    public void setAnswerWords(String[] testAnswers){
        answerWords = testAnswers; 
    }
}
