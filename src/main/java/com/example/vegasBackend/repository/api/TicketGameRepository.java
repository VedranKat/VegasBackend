package com.example.vegasBackend.repository.api;

import com.example.vegasBackend.model.TicketGame;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TicketGameRepository extends CrudRepository<TicketGame, Long> {

    List<TicketGame> findAllByTicketId(Long id);
    List<TicketGame> findAllByGameId(Long id);
}
