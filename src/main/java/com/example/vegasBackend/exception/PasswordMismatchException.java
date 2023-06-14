package com.example.vegasBackend.exception;

public class PasswordMismatchException extends Exception{
    public PasswordMismatchException(String message){
        super(message);
    }
}
