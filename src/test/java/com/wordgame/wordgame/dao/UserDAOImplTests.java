package com.wordgame.wordgame.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import com.wordgame.wordgame.dao.impl.UserDAOImpl;
import com.wordgame.wordgame.domain.User;

@ExtendWith(MockitoExtension.class)
public class UserDAOImplTests {
    
    @Mock
    private JdbcTemplate jdbcTemplate; 

    @InjectMocks
    private UserDAOImpl underTest; 

    @Test
    public void testCreateUser(){
        User user = User.builder()
            .username("yes")
            .password("secure")
            .firstName("Orange")
            .lastName("Smith")
            .build();
            
    }
}
