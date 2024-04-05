package com.wordgame.wordgame.dao.impl;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.wordgame.wordgame.TestDataUtil;
import com.wordgame.wordgame.domain.User;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserDAOImplIntegrationTests {
    
    private UserDAOImpl underTest; 

    @Autowired
    public UserDAOImplIntegrationTests(UserDAOImpl underTest){
        this.underTest = underTest;
    }

    @Test
    public void testCreateAndFindUser(){
        User user = TestDataUtil.createTestUser(); 
        underTest.create(user);
        Optional<User> results = underTest.findUser(user.getUsername());
        assertThat(results).isPresent(); 

        //Check All values of user to ensure they are equal 
        assertThat(results.get().getUsername()).isEqualTo(user.getUsername());
        assertThat(results.get().getPassword()).isEqualTo(user.getPassword());
        assertThat(results.get().getFirstName()).isEqualTo(user.getFirstName());
        assertThat(results.get().getLastName()).isEqualTo(user.getLastName());
    }
}
