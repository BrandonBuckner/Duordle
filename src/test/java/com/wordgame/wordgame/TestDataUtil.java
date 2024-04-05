package com.wordgame.wordgame;

import com.wordgame.wordgame.domain.Stats;
import com.wordgame.wordgame.domain.User;

public final class TestDataUtil {
    private TestDataUtil(){

    }

    public static User createTestUser1(){
        return User.builder()
            .username("yes")
            .password("secure")
            .firstName("Orange")
            .lastName("Smith")
            .build();
    }

    public static User createTestUser2(){
        return User.builder()
            .username("no")
            .password("notsecure")
            .firstName("Blue")
            .lastName("John")
            .build();
    }

    public static User createTestUser3(){
        return User.builder()
            .username("lol12")
            .password("secret")
            .firstName("James")
            .lastName("Rock")
            .build();
    }

    public static Stats createTestStats1(){
        return Stats.builder()
        .username("yes")
        .gamesPlayed(1)
        .gamesWon(0)
        .guessesMade(5)
        .build();
    }

    public static Stats createTestStats2(){
        return Stats.builder()
        .username("no")
        .gamesPlayed(3)
        .gamesWon(1)
        .guessesMade(12)
        .build();
    }

    public static Stats createTestStats3(){
        return Stats.builder()
        .username("lol12")
        .gamesPlayed(15)
        .gamesWon(4)
        .guessesMade(46)
        .build();
    }
}
