package com.wordgame.wordgame.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import com.wordgame.wordgame.dao.impl.UserDAOImpl;
import com.wordgame.wordgame.domain.User;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class UserDAOImplTests {
    
    @Mock
    private JdbcTemplate jdbcTemplate; 

    @InjectMocks
    private UserDAOImpl underTest; 

    @Test
    public void testCreateUserSQL(){
        User user = User.builder()
            .username("yes")
            .password("secure")
            .firstName("Orange")
            .lastName("Smith")
            .build();
        
        underTest.create(user); 

        verify(jdbcTemplate).update(
            eq("INSERT INTO users (username, password, first_Name, last_Name) VALUES (?, ?, ?, ?)"),
            eq("yes"), eq("secure"), eq("Orange"), eq("Smith")
        );
    }
}
