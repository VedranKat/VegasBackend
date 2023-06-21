package com.example.vegasBackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String gameApiId;
    private Date commenceTime;
    private String homeTeam;
    private String awayTeam;
    private int homeTeamScore;
    private int awayTeamScore;
    private Double homeTeamOdd;
    private Double awayTeamOdd;
    private String winner;
    private boolean isFinished;
    @ManyToOne
    private Sport sport;

}
