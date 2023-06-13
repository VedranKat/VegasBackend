package com.example.vegasBackend.service.api;

import com.example.vegasBackend.dto.request.UserRequest;
import com.example.vegasBackend.dto.response.UserResponse;

public interface UserService extends CrudService<UserRequest, Long, UserResponse>{
}
