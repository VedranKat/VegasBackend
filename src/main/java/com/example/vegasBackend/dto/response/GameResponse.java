package com.example.vegasBackend.dto.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class GameResponse {
    private String gameApiId;
    private String homeTeam;
    private String awayTeam;
    private double homeTeamOdd;
    private double awayTeamOdd;
    private boolean isFinished;
    private String winner;
    private double homeTeamScore;
    private double awayTeamScore;
    private Date commenceTime;
    private String sportKey;

}
