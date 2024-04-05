package com.wordgame.wordgame.dao.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import com.wordgame.wordgame.TestDataUtil;
import com.wordgame.wordgame.domain.Stats;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class StatsDAOImplTests {
    
    @Mock
    private JdbcTemplate jdbcTemplate; 

    @InjectMocks
    private StatsDAOImpl underTest; 

    @Test
    public void testCreateStatsSQL(){
        Stats stats = TestDataUtil.createTestStats1();
        
        underTest.create(stats); 

        verify(jdbcTemplate).update(
            eq("INSERT INTO stats (username, games_played, games_won, guesses_made) VALUES (?, ?, ?, ?)"),
            eq("yes"), eq(1), eq(0), eq(5)
        );
    }

    @Test
    public void testReadOneStatsSQL(){
        underTest.findStats("yes");
        verify(jdbcTemplate).query(
            eq("SELECT username, games_played, games_won, guesses_made FROM stats WHERE username = ? LIMIT 1"),
            ArgumentMatchers.<StatsDAOImpl.StatsRowMapper>any(),
            eq("yes")
        );
    }

    @Test
    public void testReadManyStatsSQL(){
        underTest.findAllStats();
        verify(jdbcTemplate).query(
            eq("SELECT username, games_played, games_won, guesses_made FROM stats"), 
            ArgumentMatchers.<StatsDAOImpl.StatsRowMapper>any()
        );
    }

    @Test
    public void testUpdateStatsSQL(){
        Stats stats2 = TestDataUtil.createTestStats2();
        underTest.updateStats(stats2);
        verify(jdbcTemplate).update(
            "UPDATE stats SET games_played = ?, games_won = ?, guesses_made = ? WHERE username = ?",
            3, 1, 12, "no"
        );
    }

    @Test
    public void testDeleteStatsSQL(){
        Stats stats1 = TestDataUtil.createTestStats1(); 
        underTest.deleteStats(stats1.getUsername());
        verify(jdbcTemplate).update(
          "DELETE FROM stats WHERE username = ?", 
            "yes"
        );
    }
}
