package com.example.vegasBackend.service.api;

import com.example.vegasBackend.dto.response.GameResponse;
import com.example.vegasBackend.dto.response.gameResponseApi.GameResponseApi;
import com.example.vegasBackend.dto.response.gameScoreApi.GameScoreResponseApi;
import com.example.vegasBackend.exception.EntityNotFoundException;

import java.util.List;

public interface GameService{

    /**
     * Calls the odds api and returns a list of games
     * with the odds for each game
     * and saves them to the database
     * @param sportKey
     * @return List<GameResponse>
     */
    List<GameResponse> getOddsFromApi(String sportKey) throws EntityNotFoundException;

    /**
     * Returns a list of games on offer from the database
     * where isFinished attribute is false
     * @return List<GameResponse>
     */
    List<GameResponse> getGamesFromDatabase();

    /**
     * Calls the odds api and returns a list of games
     * with the scores for each game
     * updates the games in the database, marks them as finished
     * @param sportKey
     * @return List<GameResponse>
     */
    List<GameResponse> getSoresFromApi(String sportKey) throws EntityNotFoundException;

    /**
     * Deletes games that are finished and have no tickets
     * @return List<GameResponse>
     */
    List<GameResponse> deleteGamesFromDatabase();




}
