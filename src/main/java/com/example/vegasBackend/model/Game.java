package com.example.vegasBackend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstCompetitor;
    private String secondCompetitor;
    private String winner;
    private String result;
    private Double firstOdd;
    private Double secondOdd;
    @ManyToOne
    private Ticket ticket;

}
