package com.wordgame.wordgame.dao.impl;

import com.wordgame.wordgame.dao.UserDAO;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDAOImpl implements UserDAO {
    private final JdbcTemplate jdbcTemplate; 

    public UserDAOImpl(final JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate; 
    }
}
