package com.example.vegasBackend.service.api;

import com.example.vegasBackend.dto.request.TicketRequest;
import com.example.vegasBackend.dto.response.TicketResponse;
import com.example.vegasBackend.exception.EntityNotFoundException;

import java.util.List;

public interface TicketService{

    /**
     * Creates a new ticket
     * Frontend will calculate the winning amount and send it in the request
     * Add a validation for the winning amount on the backend later
     * @param ticketRequest
     * @return TicketResponse
     */
    TicketResponse create(TicketRequest ticketRequest) throws EntityNotFoundException;

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
