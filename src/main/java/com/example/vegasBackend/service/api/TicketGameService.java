package com.example.vegasBackend.service.api;

import com.example.vegasBackend.dto.response.TicketGameResponse;
import com.example.vegasBackend.exception.EntityNotFoundException;

import java.util.List;

public interface TicketGameService {

    /**
     * Returns all TicketGameResponse objects from the database with the given ticket id.
     * @param id
     * @return List<TicketGameResponse>
     */
    List<TicketGameResponse> findAllByTicketId(Long id) throws EntityNotFoundException;
}
