package com.wordgame.wordgame.domain;

import lombok.Builder;

public class User {
    private String username; 

    private String password; 

    private String firstName; 

    private String lastName; 

    @Builder
    public User(String username, String password, String firstName, String lastName){
        this.username = username; 
        this.password = password; 
        this.firstName = firstName; 
        this.lastName = lastName; 
    }

    public String getUsername(){
        return username; 
    }

    public String getPassword(){
        return password; 
    }

    public String getFirstName(){
        return firstName; 
    }

    public String getLastName(){
        return lastName; 
    }

    public void setfirstName(String firstName){
        this.firstName = firstName; 
    }

    public void setLastName(String lastName){
        this.lastName = lastName; 
    }
}
