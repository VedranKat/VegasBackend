package com.example.vegasBackend.service;

import com.example.vegasBackend.dto.request.GameRequest;
import com.example.vegasBackend.dto.response.gameResponseApi.BookmakerResponse;
import com.example.vegasBackend.dto.response.gameResponseApi.GameResponseApi;
import com.example.vegasBackend.dto.response.gameResponseApi.OutcomeResponse;
import com.example.vegasBackend.exception.EntityNotFoundException;
import com.example.vegasBackend.model.Game;
import com.example.vegasBackend.model.Sport;
import com.example.vegasBackend.repository.api.GameRepository;
import com.example.vegasBackend.repository.api.SportRepository;
import com.example.vegasBackend.service.api.GameService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final SportRepository sportRepository;

    @SneakyThrows
    @Override
    public List<GameResponseApi> getOdds(String sportKey) {

        //TODO: Improve error handling
        try {
            // Create an instance of ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            // Make the API call to retrieve the JSON response
            String apiUrl = "https://api.the-odds-api.com/v4/sports/"
                    +sportKey
                    +"/odds/?regions=eu&markets=h2h&apiKey="
                    +System.getenv("ODDS_API_KEY");

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read the JSON response into a String
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
            reader.close();

            // Deserialize the JSON response into a list of GameRequest objects
            List<GameResponseApi> gameResponses = objectMapper.readValue(jsonContent.toString(), new TypeReference<List<GameResponseApi>>() {});

            this.saveGames(gameResponses, sportKey);

            // Return the deserialized list of GameRequest objects
            return gameResponses;
        } catch (IOException e) {
            e.printStackTrace();
            return null; // TODO: Improve error handling
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    private void saveGames(List<GameResponseApi> gameresponses, String sportKey) throws EntityNotFoundException {

        Sport sport = sportRepository.findByKey(sportKey)
                .orElseThrow(() -> new EntityNotFoundException
                        ("Sport with key " + sportKey + " not found"));

        gameresponses.forEach(gameResponseApi -> {
            Game game = mapGameResponseApiToGame(gameResponseApi, sport);
            if(game != null) {
                gameRepository.save(game);
            }
        });

    }

    private Game mapGameResponseApiToGame(GameResponseApi gameResponseApi, Sport sport) {

        String gameApiId = gameResponseApi.getId();

        // Check if the game already exists in the database
        Optional<Game> optionalGame = gameRepository.findByGameApiId(gameApiId);
        if (optionalGame.isPresent()) {
            return null; //skip this game
        }

        //Root object data
        Game game = new Game();
        game.setGameApiId(gameResponseApi.getId());
        game.setHomeTeam(gameResponseApi.getHome_team());
        game.setAwayTeam(gameResponseApi.getAway_team());

        //Convert commence time from String to Date
        String commenceTimeStr = gameResponseApi.getCommence_time();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        LocalDateTime localDateTime = LocalDateTime.parse(commenceTimeStr, formatter);
        Date commenceTime = java.sql.Timestamp.valueOf(localDateTime);

        game.setCommenceTime(commenceTime);

        //My data
        game.setFinished(false);
        game.setSport(sport);

        //Occasionally, the bookmakers list is empty, so we need to check for that
        List<BookmakerResponse> bookmakerResponse = gameResponseApi.getBookmakers();
        if(bookmakerResponse.isEmpty()) {
            return null; //skip this game
        }

        //Nested objects data
        //Only interested in the first bookmaker and the first market for now
        List<OutcomeResponse> outcomeResponses = bookmakerResponse
                .get(0).getMarkets().get(0).getOutcomes();

        outcomeResponses.forEach(outcomeResponse -> {
            if(outcomeResponse.getName().equals(game.getHomeTeam())) {
                game.setHomeTeamOdd(outcomeResponse.getPrice());
            } else if(outcomeResponse.getName().equals(game.getAwayTeam())) {
                game.setAwayTeamOdd(outcomeResponse.getPrice());
        }});

        //If the odds are null or too high, we don't want to save the game
        if (game.getHomeTeamOdd() == null || game.getAwayTeamOdd() == null ||
                game.getHomeTeamOdd() > 15 || game.getAwayTeamOdd() > 15) {
            return null; //skip this game
        }

        return game;
    }
}
