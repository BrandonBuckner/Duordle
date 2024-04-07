package com.wordgame.wordgame.services;

import com.wordgame.wordgame.dao.StatsDAO;
import com.wordgame.wordgame.dao.UserDAO;
import com.wordgame.wordgame.domain.Stats;
import com.wordgame.wordgame.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserDAO userDAO;
    private final StatsDAO statsDAO; 

    @Autowired
    public UserService(UserDAO userDAO, StatsDAO statsDAO) {
        this.userDAO = userDAO;
        this.statsDAO = statsDAO; 
    }
    
    public boolean login(String username, String password){
        Optional<User> userOptional = userDAO.findUser(username);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getPassword().equals(password);
        }
        return false; 
    }

    public void createUser(User user) {
        userDAO.create(user);
        statsDAO.create(new Stats(user.getUsername())); 
    }

    public Optional<User> findUserByUsername(String username) {
        return userDAO.findUser(username);
    }

    public List<User> findAllUsers() {
        return userDAO.findManyUsers();
    }

    public void updateUserDetails(User user) {
        userDAO.updateUser(user);
    }

    public void deleteUserByUsername(String username) {
        userDAO.deleteUser(username);
    }
}
