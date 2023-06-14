package com.example.vegasBackend.exception;

public class UserAlreadyExistsException extends Exception{

    public UserAlreadyExistsException(String message){
        super(message);
    }
}
