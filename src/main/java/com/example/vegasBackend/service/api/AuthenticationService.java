package com.example.vegasBackend.service.api;

import com.example.vegasBackend.dto.request.AuthenticationRequest;
import com.example.vegasBackend.dto.request.RegisterRequest;
import com.example.vegasBackend.dto.response.TokenResponse;

public interface AuthenticationService {

    TokenResponse register(RegisterRequest registerRequest);

    TokenResponse authenticate(AuthenticationRequest authenticationRequest);
}
