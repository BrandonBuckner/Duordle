package com.wordgame.wordgame.dao.impl;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.wordgame.wordgame.TestDataUtil;
import com.wordgame.wordgame.dao.UserDAO;
import com.wordgame.wordgame.domain.Stats;
import com.wordgame.wordgame.domain.User;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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
        User user = TestDataUtil.createTestUser1(); 
        userDAO.create(user);
        //NOTE: If the test user's username is changed this test will fail due to username not being set to match stats 
        Stats stats = TestDataUtil.createTestStats1(); 
        underTest.create(stats);
        Optional<Stats> results = underTest.findStats(stats.getUsername());
        assertThat(results).isPresent(); 

        //Check All values of stats to ensure they are equal 
        assertThat(results.get()).isEqualTo(stats);
    }

    @Test
    public void testCreateAndFindManyStats(){
        User user1 = TestDataUtil.createTestUser1(); 
        userDAO.create(user1);
        User user2 = TestDataUtil.createTestUser2(); 
        userDAO.create(user2);
        User user3 = TestDataUtil.createTestUser3(); 
        userDAO.create(user3);

        Stats stats1 = TestDataUtil.createTestStats1();
        underTest.create(stats1);
        Stats stats2 = TestDataUtil.createTestStats2();
        underTest.create(stats2);
        Stats stats3 = TestDataUtil.createTestStats3();
        underTest.create(stats3);

        List<Stats> results = underTest.findAllStats();
        assertThat(results).hasSize(3);
        assertThat(results).containsExactly(stats1, stats2, stats3);
    }

    @Test
    public void testUpdateStats(){
        User user2 = TestDataUtil.createTestUser2(); 
        userDAO.create(user2);
        Stats stats2 = TestDataUtil.createTestStats2();
        underTest.create(stats2);

        stats2.setGamesPlayed(10);
        underTest.updateStats(stats2); 

        Optional<Stats> results = underTest.findStats(stats2.getUsername());
        assertThat(results).isPresent();
        assertThat(results).get().isEqualTo(stats2); 
    }
}
