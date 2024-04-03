package com.wordgame.wordgame.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import com.wordgame.wordgame.dao.impl.StatsDAOImpl;

@ExtendWith(MockitoExtension.class)
public class StatsDAOImplTests {
    
    @Mock
    private JdbcTemplate jdbcTemplate; 

    @InjectMocks
    private StatsDAOImpl underTest; 

    @Test
    public void testCreateStats(){
        
    }
}
