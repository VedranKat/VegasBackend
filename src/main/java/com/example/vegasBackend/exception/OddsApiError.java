package com.example.vegasBackend.exception;

public class OddsApiError extends RuntimeException{
    public OddsApiError(String message) {
        super(message);
    }
}
