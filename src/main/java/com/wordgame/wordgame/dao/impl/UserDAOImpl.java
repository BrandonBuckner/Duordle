package com.wordgame.wordgame.dao.impl;

import com.wordgame.wordgame.dao.UserDAO;
import com.wordgame.wordgame.domain.User;

import org.springframework.jdbc.core.JdbcTemplate;

public class UserDAOImpl implements UserDAO {
    private final JdbcTemplate jdbcTemplate; 

    public UserDAOImpl(final JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate; 
    }

    @Override
    public void create(User user) {
        jdbcTemplate.update("INSERT INTO users (username, password, first_Name, last_Name) VALUES (?, ?, ?, ?)",
        user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName());
    }
}
