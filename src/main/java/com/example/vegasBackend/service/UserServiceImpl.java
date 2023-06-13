package com.example.vegasBackend.service;

import com.example.vegasBackend.dto.request.UserRequest;
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

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LogManager.getLogger(UserServiceImpl.class);

    private final ModelMapper mapper = new ModelMapper();

    private final UserRepository userRepository;

    @Override
    public UserResponse create(UserRequest userRequest) {
        LOG.info(" ---- UserService Implementation : create() ---- ");

        //TODO: create user

        return null;
    }

    @Override
    public Set<UserResponse> getAll() {
        LOG.info(" ---- UserService Implementation : getAll() ---- ");

        Set<User> users = new HashSet<>();

        userRepository.findAll().forEach(users::add);

        return users.stream()
                .map(user -> mapper.map(user, UserResponse.class))
                .collect(Collectors.toSet());
    }

    @Override
    public UserResponse getById(Long id) throws EntityNotFoundException {
        LOG.info(" ---- UserService Implementation : getById() ---- ");

        User user = userRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));

        return mapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse update(UserRequest userRequest, Long id) throws EntityNotFoundException {
        LOG.info(" ---- UserService Implementation : update() ---- ");

        //TODO: update user


        return null;
    }

    @Override
    public UserResponse deleteById(Long id) throws EntityNotFoundException {
        LOG.info(" ---- UserService Implementation : deleteById() ---- ");

        User user = userRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));

        userRepository.deleteById(id);

        return mapper.map(user, UserResponse.class);
    }
}
