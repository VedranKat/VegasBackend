package com.example.vegasBackend.controller;

import com.example.vegasBackend.dto.request.TicketRequest;
import com.example.vegasBackend.exception.EntityNotFoundException;
import com.example.vegasBackend.service.api.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/ticket")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/create")
    public ResponseEntity<Object> createTicket(@RequestBody TicketRequest ticketRequest) throws EntityNotFoundException {

        return ResponseEntity.ok(ticketService.create(ticketRequest));
    }
}
