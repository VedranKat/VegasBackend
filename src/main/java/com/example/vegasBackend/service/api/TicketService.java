package com.example.vegasBackend.service.api;

import com.example.vegasBackend.dto.request.TicketRequest;
import com.example.vegasBackend.dto.response.TicketResponse;

import java.util.List;

public interface TicketService{

    /**
     * Creates a new ticket
     * @param ticketRequest
     * @return TicketResponse
     */
    TicketResponse create(TicketRequest ticketRequest);

    /**
     * Returns all tickets for a given user
     * @param email
     * @return List<TicketResponse>
     */
    List<TicketResponse> getAllByUserEmail(String email);

    /**
     * Checks if the ticket is a winning one and updates the ticket status and user balance accordingly
     * @param id
     */
    void checkTicket(Long id);


}
