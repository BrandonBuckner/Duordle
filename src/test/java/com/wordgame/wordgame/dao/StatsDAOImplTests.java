package com.wordgame.wordgame.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import com.wordgame.wordgame.dao.impl.StatsDAOImpl;
import com.wordgame.wordgame.domain.Stats;
import com.wordgame.wordgame.domain.User;

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
        Stats stats = Stats.builder()
            .user(new User("kevin1", "secret", "Kevin", "Smith"))
            .gamesPlayed(1)
            .gamesWon(0)
            .guessesMade(5)
            .build();
        
        underTest.create(stats); 

        verify(jdbcTemplate).update(
            eq("INSERT INTO stats (username, games_played, games_won, guesses_made) VALUES (?, ?, ?, ?)"),
            eq("kevin1"), eq(1), eq(0), eq(5)
        );
    }
}
