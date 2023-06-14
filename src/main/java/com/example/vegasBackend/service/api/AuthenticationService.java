package com.example.vegasBackend.service.api;

import com.example.vegasBackend.dto.request.AuthenticationRequest;
import com.example.vegasBackend.dto.request.RegisterRequest;
import com.example.vegasBackend.dto.response.TokenResponse;
import com.example.vegasBackend.exception.PasswordMismatchException;

public interface AuthenticationService {

    TokenResponse register(RegisterRequest registerRequest) throws PasswordMismatchException;

    TokenResponse authenticate(AuthenticationRequest authenticationRequest);
}
