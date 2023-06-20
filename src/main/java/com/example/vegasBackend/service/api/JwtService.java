package com.example.vegasBackend.service.api;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JwtService {

    /**
     * Extracts email from the token
     * @param token
     * @return email
     */
    String extractEmail(String token);

    /**
     * Extracts the claim from the token
     * @param token
     * @param claimsResolver
     * @return claim
     */
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    /**
     * Generates the token
     * @param extraClaims
     * @param userDetails
     * @return token
     */
    String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);

    /**
     * Generates the token
     * @param userDetails
     * @return token
     */
    String generateToken(UserDetails userDetails);

    /**
     * Checks if the token is valid
     * @param token
     * @param userDetails
     * @return true if the token is valid, false otherwise
     */
    Boolean isTokenValid(String token, UserDetails userDetails);

}
