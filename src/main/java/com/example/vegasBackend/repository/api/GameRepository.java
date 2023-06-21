package com.example.vegasBackend.repository.api;

import com.example.vegasBackend.model.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GameRepository extends CrudRepository<Game, Long> {

    Optional<Game> findByGameApiId(String gameApiId);
}
