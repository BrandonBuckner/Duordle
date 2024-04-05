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
import com.wordgame.wordgame.domain.User;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserDAOImplIntegrationTests {
    
    private UserDAOImpl underTest; 

    @Autowired
    public UserDAOImplIntegrationTests(UserDAOImpl underTest){
        this.underTest = underTest;
    }

    @Test
    public void testCreateAndFindUser(){
        User user = TestDataUtil.createTestUser1(); 
        underTest.create(user);
        Optional<User> results = underTest.findUser(user.getUsername());
        assertThat(results).isPresent(); 

        //Check values of user to ensure they are equal 
        assertThat(results.get()).isEqualTo(user);
    }

    @Test
    public void testCreateAndFindMultipleUsers(){
        User user1 = TestDataUtil.createTestUser1(); 
        underTest.create(user1); 
        User user2 = TestDataUtil.createTestUser2();
        underTest.create(user2);
        User user3 = TestDataUtil.createTestUser3(); 
        underTest.create(user3);

        List<User> results = underTest.findManyUsers();
        assertThat(results).hasSize(3);
        assertThat(results).containsExactly(user1, user2, user3);
    }

    @Test
    public void testUpdateUser(){
        User user2 = TestDataUtil.createTestUser2();
        underTest.create(user2); 
        user2.setfirstName("Brandon");
        underTest.updateUser(user2); 
        Optional<User> results = underTest.findUser(user2.getUsername());
        assertThat(results).isPresent(); 
        assertThat(results).get().isEqualTo(user2);
    }
}
