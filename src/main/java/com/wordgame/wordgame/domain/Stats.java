package com.wordgame.wordgame.domain;

import lombok.Builder;

public class Stats {

    private String username;
    private int gamesPlayed; 
    private int gamesWon; 
    private int guessesMade; 

    @Builder
    public Stats(String username, int gamesPlayed, int gamesWon, int guessesMade){
        this.username = username; 
        this.gamesPlayed = gamesPlayed; 
        this.gamesWon = gamesWon;        
        this.guessesMade = guessesMade;  
    }

    public Stats(String username){
        this.username = username; 
        gamesPlayed = 0; 
        gamesWon = 0; 
        guessesMade = 0; 
    }

    public String getUsername(){
        return username; 
    }

    public int getGamesPlayed(){
        return gamesPlayed; 
    }

    public int getGamesWon(){
        return gamesWon;
    }

    public int getGussesMade(){
        return guessesMade; 
    }

    public void setGamesPlayed(int gamesPlayed){
        this.gamesPlayed = gamesPlayed; 
    }

    public void setGamesWon(int gamesWon){
        this.gamesWon = gamesWon; 
    }

    public void setGuessesMade(int guessesMade){
        this.guessesMade = guessesMade;  
    }
}
