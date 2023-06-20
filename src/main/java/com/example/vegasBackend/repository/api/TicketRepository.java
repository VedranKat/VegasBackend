package com.example.vegasBackend.repository.api;

import com.example.vegasBackend.model.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Long> {

    List<Ticket> findAllByUserId(Long id);
}
