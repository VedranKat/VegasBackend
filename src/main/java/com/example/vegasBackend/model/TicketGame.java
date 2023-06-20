package com.example.vegasBackend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tickets_games")
public class TicketGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Ticket ticket;
    @ManyToOne
    private Game game;
    private String chosenWinner;
}
