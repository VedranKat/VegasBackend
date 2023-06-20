package com.example.vegasBackend.dto.response.gameResponseApi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameResponseApi {

    private String id;
    private String sport_key;
    private String commence_time;
    private String home_team;
    private String away_team;
    private List<BookmakerResponse> bookmakers;


}
