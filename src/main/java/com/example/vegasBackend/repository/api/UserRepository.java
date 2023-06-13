package com.example.vegasBackend.repository.api;

import com.example.vegasBackend.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
