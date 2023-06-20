package com.example.vegasBackend.service;

import com.example.vegasBackend.dto.request.BalanceUpdateRequest;
import com.example.vegasBackend.dto.response.UserResponse;
import com.example.vegasBackend.exception.EntityNotFoundException;
import com.example.vegasBackend.model.User;
import com.example.vegasBackend.repository.api.UserRepository;
import com.example.vegasBackend.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ModelMapper mapper = new ModelMapper();

    private final UserRepository userRepository;

    @Override
    public UserResponse getByEmail(String email) throws EntityNotFoundException {

        User user = userRepository.findByEmail(email).
                orElseThrow(() -> new EntityNotFoundException("User with email " + email + " not found"));

        return mapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse deleteById(Long id) throws EntityNotFoundException {

        User user = userRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));

        //TODO: cascade delete user
        userRepository.deleteById(id);

        return mapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse updateBalance(BalanceUpdateRequest balanceUpdateRequest) throws EntityNotFoundException {

        User user = userRepository.findByEmail(balanceUpdateRequest.getEmail()).
                        orElseThrow(() -> new EntityNotFoundException("User with email " + balanceUpdateRequest.getEmail() + " not found"));

        user.setBalance(balanceUpdateRequest.getBalanceDelta().add(user.getBalance()));

        User updatedUser = userRepository.save(user);

        return mapper.map(updatedUser, UserResponse.class);
    }
}
