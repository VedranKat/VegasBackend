package com.example.vegasBackend.dto.request;

import com.example.vegasBackend.model.Game;
import com.example.vegasBackend.model.Ticket;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TicketGameRequest {
    private String chosenWinner;
    private Game game;
    private Ticket ticket;
}
