package com.wordgame.wordgame.domain;

import lombok.Builder;

public class Stats {
    private User user;

    private int gamesPlayed; 
    private int gamesWon; 
    private int guessesMade; 

    @Builder
    public Stats(User user, int gamesPlayed, int gamesWon, int guessesMade){
        this.user = user; 
        this.gamesPlayed = gamesPlayed; 
        this.gamesWon = gamesWon;        
        this.guessesMade = guessesMade;  
    }

    public Stats(User user){
        this.user = user; 
        gamesPlayed = 0; 
        gamesWon = 0; 
        guessesMade = 0; 
    }

    public User getUser(){
        return user; 
    }

    public String getUsername(){
        return user.getUsername(); 
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
