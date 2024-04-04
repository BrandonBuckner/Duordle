package com.wordgame.wordgame.dao.impl;

import com.wordgame.wordgame.dao.StatsDAO;
import com.wordgame.wordgame.domain.Stats;

import org.springframework.jdbc.core.JdbcTemplate;

public class StatsDAOImpl implements StatsDAO {
    private final JdbcTemplate jdbcTemplate; 

    public StatsDAOImpl(final JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate; 
    }

    public void create(Stats stats) {
        jdbcTemplate.update("INSERT INTO stats (username, games_played, games_won, guesses_made) VALUES (?, ?, ?, ?)",
        stats.getUsername(), stats.getGamesPlayed(), stats.getGamesWon(), stats.getGussesMade());
    }
}
