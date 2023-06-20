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
    private int firstCompetitorScore;
    private int secondCompetitorScore;
    private Double firstOdd;
    private Double secondOdd;
    private String winner;
    private boolean isFinished;
    @ManyToOne
    private Sport sport;

}
