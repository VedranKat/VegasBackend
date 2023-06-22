package com.example.vegasBackend.service.api;

import com.example.vegasBackend.dto.request.BalanceUpdateRequest;
import com.example.vegasBackend.dto.response.UserResponse;
import com.example.vegasBackend.exception.EntityNotFoundException;

public interface UserService{

    /**
     * Fetches the user by email
     * @param email
     * @return UserResponse
     * @throws EntityNotFoundException
     */
    UserResponse getByEmail(String email) throws EntityNotFoundException;

    /**
     * Updates the user balance
     * @param balanceUpdateRequest
     * @return UserResponse
     * @throws EntityNotFoundException
     */
    UserResponse updateBalance(BalanceUpdateRequest balanceUpdateRequest) throws EntityNotFoundException;
}
