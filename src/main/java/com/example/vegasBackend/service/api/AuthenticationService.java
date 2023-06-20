package com.example.vegasBackend.service.api;

import com.example.vegasBackend.dto.request.AuthenticationRequest;
import com.example.vegasBackend.dto.request.RegisterRequest;
import com.example.vegasBackend.dto.response.TokenResponse;
import com.example.vegasBackend.exception.PasswordMismatchException;
import com.example.vegasBackend.exception.UserAlreadyExistsException;

public interface AuthenticationService {

    /**
     * Creates a new user and returns a token
     * @param registerRequest
     * @return TokenResponse
     * @throws PasswordMismatchException
     * @throws UserAlreadyExistsException
     */
    TokenResponse register(RegisterRequest registerRequest) throws PasswordMismatchException, UserAlreadyExistsException;

    /**
     * Authenticates the user and returns a token
     * @param authenticationRequest
     * @return TokenResponse
     */
    TokenResponse authenticate(AuthenticationRequest authenticationRequest);
}
