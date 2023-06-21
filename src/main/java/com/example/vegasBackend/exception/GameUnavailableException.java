package com.example.vegasBackend.exception;

public class GameUnavailableException extends RuntimeException{
    public GameUnavailableException(String message) {
        super(message);
    }
}