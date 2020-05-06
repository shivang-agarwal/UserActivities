package com.zenon.exception;

public class UserNotFoundException extends RuntimeException{
    private String username;

    public UserNotFoundException(){
        super("Either username or password is incorrect.");
    }

    public UserNotFoundException(String username){
        super("Either username or password is incorrect.");
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
