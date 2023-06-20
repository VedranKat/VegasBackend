package com.example.vegasBackend.service.api;

import com.example.vegasBackend.dto.request.GameRequest;
import com.example.vegasBackend.dto.response.GameResponse;
import com.example.vegasBackend.dto.response.gameResponseApi.GameResponseApi;

import java.util.List;

public interface GameService{


    /**
     * Calls the odds api and returns a list of games
     * With the odds for each game
     * @param sportKey
     * @return List<GameResponse>
     */
    List<GameResponseApi> getOdds(String sportKey);

}
