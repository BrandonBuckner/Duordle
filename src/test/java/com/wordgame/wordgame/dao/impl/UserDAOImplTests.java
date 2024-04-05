package com.wordgame.wordgame.dao.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import com.wordgame.wordgame.domain.User;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import com.wordgame.wordgame.TestDataUtil;



@ExtendWith(MockitoExtension.class)
public class UserDAOImplTests {
    
    @Mock
    private JdbcTemplate jdbcTemplate; 

    @InjectMocks
    private UserDAOImpl underTest; 
    
    @Test
    public void testCreateUserSQL(){
        User user = TestDataUtil.createTestUser(); 
        
        underTest.create(user); 

        verify(jdbcTemplate).update(
            eq("INSERT INTO users (username, password, first_Name, last_Name) VALUES (?, ?, ?, ?)"),
            eq("yes"), eq("secure"), eq("Orange"), eq("Smith")
        );
    }


    @Test
    public void testReadOneUserSQL(){
        underTest.findUser("yes");
        verify(jdbcTemplate).query(
            eq("SELECT username, password, first_name, last_name WHERE username = ? LIMIT 1"),
            ArgumentMatchers.<UserDAOImpl.UserRowMapper>any(),
            eq("yes")
        );
    }
}
