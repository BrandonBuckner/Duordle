package com.wordgame.wordgame;

import com.wordgame.wordgame.domain.Stats;
import com.wordgame.wordgame.domain.User;

public final class TestDataUtil {
    private TestDataUtil(){

    }

    public static User createTestUser(){
        return User.builder()
            .username("yes")
            .password("secure")
            .firstName("Orange")
            .lastName("Smith")
            .build();
    }

    public static Stats createTestStats(){
        return Stats.builder()
        .username("yes")
        .gamesPlayed(1)
        .gamesWon(0)
        .guessesMade(5)
        .build();
    }
}
