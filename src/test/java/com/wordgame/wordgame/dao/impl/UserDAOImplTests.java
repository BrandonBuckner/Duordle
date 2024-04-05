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
        User user = TestDataUtil.createTestUser1(); 
        
        underTest.create(user); 

        verify(jdbcTemplate).update(
            eq("INSERT INTO users (username, password, first_Name, last_Name) VALUES (?, ?, ?, ?)"),
            eq("yes"), eq("secure"), eq("Orange"), eq("Smith")
        );
    }

    /**
     * Test to determine if the correct SQL is being used and read 
     */
    @Test
    public void testReadOneUserSQL(){
        underTest.findUser("yes");
        verify(jdbcTemplate).query(
            eq("SELECT username, password, first_name, last_name FROM users WHERE username = ? LIMIT 1"),
            ArgumentMatchers.<UserDAOImpl.UserRowMapper>any(),
            eq("yes")
        );
    }

    @Test
    public void testReadManyUserSQL(){
        underTest.findManyUsers();
        verify(jdbcTemplate).query(
            eq("SELECT username, password, first_name, last_name FROM users"),
            ArgumentMatchers.<UserDAOImpl.UserRowMapper>any()
        );
    }


    @Test 
    public void testUpdateUserSQL(){
        User user1 = TestDataUtil.createTestUser1();
        underTest.updateUser(user1);
        verify(jdbcTemplate).update(
            //"UPDATE users SET username = ?, password = ?, first_name = ?, last_name = ? WHERE username = ?",
            //"yes", "secure", "Orange", "Smith", "yes"
            "UPDATE users SET first_name = ?, last_name = ? WHERE username = ?", 
            "Orange", "Smith", "yes"
        );
    }

    @Test
    public void testDeleteUserSQL(){
        underTest.deleteUser("yes");
        verify(jdbcTemplate).update(
          "DELETE FROM users WHERE username = ?", 
          "yes"
        );
    }
}
