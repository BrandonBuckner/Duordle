package com.wordgame.wordgame.dao.impl;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.wordgame.wordgame.TestDataUtil;
import com.wordgame.wordgame.dao.UserDAO;
import com.wordgame.wordgame.domain.Stats;
import com.wordgame.wordgame.domain.User;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class StatsDAOImplIntegrationTests {

    private UserDAO userDAO; 
    private StatsDAOImpl underTest; 

    @Autowired
    public StatsDAOImplIntegrationTests(StatsDAOImpl underTest, UserDAO userDAO){
        this.underTest = underTest; 
        this.userDAO = userDAO; 
    }

    @Test
    public void testCreateAndFindStats(){
        User user = TestDataUtil.createTestUser(); 
        userDAO.create(user);
        //NOTE: If the test user's username is changed this test will fail due to username not being set again
        Stats stats = TestDataUtil.createTestStats(); 
        underTest.create(stats);
        Optional<Stats> results = underTest.findStats(stats.getUsername());
        assertThat(results).isPresent(); 

        //Check All values of stats to ensure they are equal 
        assertThat(results.get().getUsername()).isEqualTo(stats.getUsername());
        assertThat(results.get().getGamesPlayed()).isEqualTo(stats.getGamesPlayed());
        assertThat(results.get().getGamesWon()).isEqualTo(stats.getGamesWon());
        assertThat(results.get().getGussesMade()).isEqualTo(stats.getGussesMade());
    }
}
