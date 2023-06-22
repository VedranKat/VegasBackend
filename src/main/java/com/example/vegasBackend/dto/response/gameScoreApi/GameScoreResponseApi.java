package com.example.vegasBackend.dto.response.gameScoreApi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameScoreResponseApi {

    private String id;
    private String sport_key;
    private String completed;
    private String home_team;
    private String away_team;
    private List<ScoreResponse> scores;
}
