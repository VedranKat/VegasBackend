package com.example.vegasBackend.service.api;

import com.example.vegasBackend.dto.request.AuthenticationRequest;
import com.example.vegasBackend.dto.request.RegisterRequest;
import com.example.vegasBackend.dto.response.TokenResponse;
import com.example.vegasBackend.exception.PasswordMismatchException;
import com.example.vegasBackend.exception.UserAlreadyExistsException;

public interface AuthenticationService {

    TokenResponse register(RegisterRequest registerRequest) throws PasswordMismatchException, UserAlreadyExistsException;

    TokenResponse authenticate(AuthenticationRequest authenticationRequest);
}
