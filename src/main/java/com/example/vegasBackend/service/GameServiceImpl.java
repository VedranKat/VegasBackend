package com.example.vegasBackend.service;

import com.example.vegasBackend.dto.response.GameResponse;
import com.example.vegasBackend.dto.response.gameResponseApi.BookmakerResponse;
import com.example.vegasBackend.dto.response.gameResponseApi.GameResponseApi;
import com.example.vegasBackend.dto.response.gameResponseApi.OutcomeResponse;
import com.example.vegasBackend.dto.response.gameScoreApi.GameScoreResponseApi;
import com.example.vegasBackend.exception.EntityNotFoundException;
import com.example.vegasBackend.exception.OddsApiError;
import com.example.vegasBackend.model.Game;
import com.example.vegasBackend.model.Sport;
import com.example.vegasBackend.model.TicketGame;
import com.example.vegasBackend.repository.api.GameRepository;
import com.example.vegasBackend.repository.api.SportRepository;
import com.example.vegasBackend.repository.api.TicketGameRepository;
import com.example.vegasBackend.service.api.GameService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final SportRepository sportRepository;
    private final TicketGameRepository ticketGameRepository;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public List<GameResponse> getOddsFromApi(String sportKey) throws EntityNotFoundException {

        String apiUrl = "https://api.the-odds-api.com/v4/sports/"
                +sportKey
                +"/odds/?regions=eu&markets=h2h&apiKey="
                +System.getenv("ODDS_API_KEY");

        List<GameResponseApi> gameResponse = this.getGamesFromApi(apiUrl, GameResponseApi.class);

        return this.saveGames(gameResponse, sportKey);

    }

    @Override
    public List<GameResponse> getGamesFromDatabase() {

        Date currentTime = new Date(System.currentTimeMillis() + 360000); // 360 seconds, add a zero and check

        List<Game> gameResponses = gameRepository.findAllByIsFinishedFalse();

        return gameResponses.stream()
                .filter(game ->game.getCommenceTime().compareTo(currentTime) > 0)
                .map(game -> mapper.map(game, GameResponse.class))
                .toList();
    }

    //Create some sort of check to see if a game hasn't been updated
    //even though it's finished, in case the API fails to update the game
    @Override
    public List<GameResponse> getScoresFromApi(String sportKey) throws EntityNotFoundException {

        String apiUrl = "https://api.the-odds-api.com/v4/sports/"
                +sportKey
                +"/scores/?apiKey="
                +System.getenv("ODDS_API_KEY")
                +"&daysFrom=2&dateFormat=iso";

        //Get the games from the API
        List<GameScoreResponseApi> gameScoreResponse = this.getGamesFromApi(apiUrl, GameScoreResponseApi.class);

        //Update and return the games
        return this.updateGames(gameScoreResponse, sportKey);
    }

    @Override
    public List<GameResponse> deleteGamesFromDatabase() {

        //Get all the games that are finished
        List<Game> games = gameRepository.findAllByIsFinishedTrue();

        List<GameResponse> gameResponses = new ArrayList<>();

        //For each game, check if it's assigned to a ticket
        games.forEach(game -> {
            List<TicketGame> ticketGames = ticketGameRepository.findAllByGameId(game.getId());

            //If it's not assigned to a ticket, delete it
            if (ticketGames.isEmpty()) {
                gameRepository.delete(game);
                gameResponses.add(mapper.map(game, GameResponse.class));
            }

        });
        return gameResponses;
    }

    private <T> List<T> getGamesFromApi(String apiUrl, Class<T> responseType) {

        try {
            // Create an instance of ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            // Make the API call to retrieve the JSON response
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

            // Construct the JavaType for the generic response type
            TypeFactory typeFactory = objectMapper.getTypeFactory();
            JavaType javaType = typeFactory.constructCollectionType(List.class, responseType);

            // Deserialize the JSON response into a list of objects
            return objectMapper.readValue(jsonContent.toString(), javaType);
        } catch (IOException e) {
            e.printStackTrace();
            throw new OddsApiError("Error while getting games from API");
        }
    }

    private List<GameResponse> updateGames(List<GameScoreResponseApi> gameResponses, String sportKey) throws EntityNotFoundException {

        Sport sport = sportRepository.findByKey(sportKey)
                .orElseThrow(() -> new EntityNotFoundException
                        ("Sport with key " + sportKey + " not found"));

        List<GameResponse> returnGameResponses = new ArrayList<>();

        //Get a smaller subset of games from the database, for in-memory filtering
        List<Game> games = gameRepository.findAllBySportIdAndIsFinishedFalse(sport.getId());

        gameResponses.forEach(gameResponse ->{
            //Find the game in the small list of games we fetched from the database
            Game game = games.stream()
                    .filter(game1 -> game1.getGameApiId().equals(gameResponse.getId()))
                    .findFirst()
                    .orElse(null);

            if(game != null && gameResponse.getCompleted().equals("true")) {

                //Set the scores
                gameResponse.getScores().forEach(score -> {
                    if(score.getName().equals(game.getHomeTeam())) {
                        game.setHomeTeamScore(score.getScore());
                    } else if(score.getName().equals(game.getAwayTeam())) {
                        game.setAwayTeamScore(score.getScore());
                    }
                });

                //Set the winner
                if (game.getHomeTeamScore() > game.getAwayTeamScore()) {
                    game.setWinner(game.getHomeTeam());
                } else if (game.getHomeTeamScore() < game.getAwayTeamScore()) {
                    game.setWinner(game.getAwayTeam());
                } else {
                    game.setWinner("Draw");
                }

                game.setFinished(true);

                Game savedGame = gameRepository.save(game);

                returnGameResponses.add(mapper.map(savedGame, GameResponse.class));
            }
        });

        return returnGameResponses;
    }


    private List<GameResponse> saveGames(List<GameResponseApi> gameresponses, String sportKey) throws EntityNotFoundException {

        Sport sport = sportRepository.findByKey(sportKey)
                .orElseThrow(() -> new EntityNotFoundException
                        ("Sport with key " + sportKey + " not found"));

        List<GameResponse> returnGameResponses = new ArrayList<>();

        gameresponses.forEach(gameResponseApi -> {
            Game game = mapGameResponseApiToGame(gameResponseApi, sport);
            if(game != null) {
                Game savedGame = gameRepository.save(game);

                returnGameResponses.add(mapper.map(savedGame, GameResponse.class));
            }
        });

        return returnGameResponses;

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
