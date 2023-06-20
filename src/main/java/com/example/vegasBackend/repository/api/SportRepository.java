package com.example.vegasBackend.repository.api;

import com.example.vegasBackend.model.Sport;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SportRepository extends CrudRepository<Sport, Long> {

    Optional<Sport> findByKey(String key);
}
