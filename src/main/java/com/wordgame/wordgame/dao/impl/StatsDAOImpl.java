package com.wordgame.wordgame.dao.impl;

import com.wordgame.wordgame.dao.StatsDAO;
import com.wordgame.wordgame.domain.Stats;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;

@Component
public class StatsDAOImpl implements StatsDAO {
    private final JdbcTemplate jdbcTemplate; 

    public StatsDAOImpl(final JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate; 
    }

    public void create(Stats stats) {
        jdbcTemplate.update("INSERT INTO stats (username, games_played, games_won, guesses_made) VALUES (?, ?, ?, ?)",
        stats.getUsername(), stats.getGamesPlayed(), stats.getGamesWon(), stats.getGussesMade());
    }

    @Override
    public Optional<Stats> findStats(String username) {
        List<Stats> results = jdbcTemplate.query("SELECT username, games_played, games_won, guesses_made FROM stats WHERE username = ? LIMIT 1", 
        new StatsRowMapper(), username); 
        return results.stream().findFirst();
    }
    
    public static class StatsRowMapper implements RowMapper<Stats> {
        @Override
        public Stats mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Stats.builder()
                .username(rs.getString("username"))
                .gamesPlayed(rs.getInt("games_played"))
                .gamesWon(rs.getInt("games_won"))
                .guessesMade(rs.getInt("guesses_made"))
                .build(); 
        }
    }

    @Override
    public List<Stats> findAllStats() {
        return jdbcTemplate.query("SELECT username, games_played, games_won, guesses_made FROM stats", new StatsRowMapper());
    }

    @Override
    public void updateStats(Stats stats) {
        jdbcTemplate.update(
            "UPDATE stats SET games_played = ?, games_won = ?, guesses_made = ? WHERE username = ?",
            stats.getGamesPlayed(), stats.getGamesWon(), stats.getGussesMade(), stats.getUsername()
        );
    }
}
