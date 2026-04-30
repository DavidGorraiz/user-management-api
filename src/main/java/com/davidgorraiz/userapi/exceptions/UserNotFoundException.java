package com.davidgorraiz.userapi.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id){
        super("User ID " + id + " not found");
    }
}
