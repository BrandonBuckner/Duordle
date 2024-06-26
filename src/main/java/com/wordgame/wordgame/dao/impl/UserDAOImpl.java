package com.wordgame.wordgame.dao.impl;

import com.wordgame.wordgame.dao.UserDAO;
import com.wordgame.wordgame.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
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

    @Override
    public Optional<User> findUser(String username) {
        List<User> results = jdbcTemplate.query("SELECT username, password, first_name, last_name FROM users WHERE username = ? LIMIT 1", 
        new UserRowMapper(), username); 

        return results.stream().findFirst();
    }

    public static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return User.builder()
                .username(rs.getString("username"))
                .password(rs.getString("password"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .build(); 
        }
    }

    public List<User> findManyUsers() {
        return jdbcTemplate.query(
            "SELECT username, password, first_name, last_name FROM users", 
            new UserRowMapper()
        );
    }

    @Override
    public void updateUser(User user) {
        jdbcTemplate.update(
            //"UPDATE users SET username = ?, password = ?, first_name = ?, last_name = ? WHERE username = ?",
            //user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getUsername()
            "UPDATE users SET first_name = ?, last_name = ? WHERE username = ?",
            user.getFirstName(), user.getLastName(), user.getUsername()
        );
    }

    @Override
    public void deleteUser(String username) {
        jdbcTemplate.update(
            "DELETE FROM users WHERE username = ?",
            username
        );
    }
}
