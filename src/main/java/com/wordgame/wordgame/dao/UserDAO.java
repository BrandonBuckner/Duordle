package com.wordgame.wordgame.dao;

import java.util.List;
import java.util.Optional;

import com.wordgame.wordgame.domain.User;

public interface UserDAO {
    void create(User user);
    
    Optional<User> findUser(String username);

    List<User> findManyUsers();

    void updateUser(User user); 

    void deleteUser(String username); 
}
