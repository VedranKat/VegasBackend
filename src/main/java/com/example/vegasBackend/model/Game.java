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
    @Column(name = "first_competitor")
    private String firstCompetitor;
    @Column(name = "second_competitor")
    private String secondCompetitor;
    private String winner;
    private String result;
    private Double firstOdd;
    private Double secondOdd;
    @ManyToOne
    private Ticket ticket;

}
