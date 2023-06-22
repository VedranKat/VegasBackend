package com.example.vegasBackend.dto.response.gameScoreApi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreResponse {

    private String name;
    private int score;
}
