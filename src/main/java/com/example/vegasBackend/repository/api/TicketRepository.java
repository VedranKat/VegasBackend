package com.example.vegasBackend.repository.api;

import com.example.vegasBackend.model.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
}
