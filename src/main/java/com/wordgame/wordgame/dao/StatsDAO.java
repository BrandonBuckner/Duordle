package com.wordgame.wordgame.dao;

import java.util.Optional;

import com.wordgame.wordgame.domain.Stats;

public interface StatsDAO {
    void create(Stats stats);
    
    Optional<Stats> findStats(String username);
} 
