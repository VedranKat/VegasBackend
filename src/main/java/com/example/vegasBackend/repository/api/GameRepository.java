package com.example.vegasBackend.repository.api;

import com.example.vegasBackend.model.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long> {
}
