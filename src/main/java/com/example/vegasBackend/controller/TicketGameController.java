package com.example.vegasBackend.controller;

import com.example.vegasBackend.exception.EntityNotFoundException;
import com.example.vegasBackend.service.api.TicketGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/ticket-game")
@RequiredArgsConstructor
public class TicketGameController {

    private final TicketGameService ticketGameService;

    @GetMapping("/get-by-ticket-id/{id}")
    public ResponseEntity<Object> getAllByTicketId(@PathVariable Long id) throws EntityNotFoundException {

        return ResponseEntity.ok(ticketGameService.findAllByTicketId(id));
    }


}
