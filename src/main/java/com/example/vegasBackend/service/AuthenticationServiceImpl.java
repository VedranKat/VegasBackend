package com.example.vegasBackend.service;

import com.example.vegasBackend.dto.request.AuthenticationRequest;
import com.example.vegasBackend.dto.request.RegisterRequest;
import com.example.vegasBackend.dto.response.TokenResponse;
import com.example.vegasBackend.exception.EntityNotFoundException;
import com.example.vegasBackend.exception.PasswordMismatchException;
import com.example.vegasBackend.exception.UserAlreadyExistsException;
import com.example.vegasBackend.model.User;
import com.example.vegasBackend.repository.api.UserRepository;
import com.example.vegasBackend.service.api.AuthenticationService;
import com.example.vegasBackend.service.api.JwtService;
import com.example.vegasBackend.service.api.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final MyUserDetailsService myUserDetailsService;

    private final AuthenticationManager authenticationManager;
    @Override
    public TokenResponse register(RegisterRequest registerRequest) throws PasswordMismatchException, UserAlreadyExistsException {

        if(!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())){
            throw new PasswordMismatchException("Passwords do not match");
        }

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new UserAlreadyExistsException("User with email " + registerRequest.getEmail() + " already exists");
        }

        var user = User.builder()
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .balance(BigDecimal.valueOf(0.0))
                .build();

        userRepository.save(user);

        UserDetails userDetails = myUserDetailsService.loadUserByUsername(user.getEmail());

        var jwtToken = jwtService.generateToken(userDetails);

        return TokenResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public TokenResponse authenticate(AuthenticationRequest authenticationRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );

        UserDetails userDetails = myUserDetailsService
                .loadUserByUsername(authenticationRequest.getEmail());

        var jwtToken = jwtService.generateToken(userDetails);

        return TokenResponse.builder()
                .token(jwtToken)
                .build();
    }
}
