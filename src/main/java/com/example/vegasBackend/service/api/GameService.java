package com.example.vegasBackend.service.api;

import com.example.vegasBackend.dto.response.GameResponse;
import com.example.vegasBackend.dto.response.gameResponseApi.GameResponseApi;

import java.util.List;

public interface GameService{


    /**
     * Calls the odds api and returns a list of games
     * with the odds for each game
     * and saves them to the database
     * @param sportKey
     * @return List<GameResponse>
     */
    List<GameResponseApi> getOddsFromApi(String sportKey);

    /**
     * Returns a list of games on offer from the database
     * where isFinished attribute is false
     * @return List<GameResponse>
     */
    List<GameResponse> getGamesFromDatabase();


}
