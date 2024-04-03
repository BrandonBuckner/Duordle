package com.wordgame.wordgame.dao.impl;

import com.wordgame.wordgame.dao.StatsDAO;
import org.springframework.jdbc.core.JdbcTemplate;

public class StatsDAOImpl implements StatsDAO {
    private final JdbcTemplate jdbcTemplate; 

    public StatsDAOImpl(final JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate; 
    }
}
