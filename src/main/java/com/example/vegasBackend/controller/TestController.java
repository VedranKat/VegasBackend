package com.example.vegasBackend.controller;

import com.example.vegasBackend.dto.response.GameResponse;
import com.example.vegasBackend.dto.response.TicketResponse;
import com.example.vegasBackend.exception.EntityNotFoundException;
import com.example.vegasBackend.service.api.GameService;
import com.example.vegasBackend.service.api.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/test")
@RequiredArgsConstructor
public class TestController {

    //TODO: Remove this controller later, it's just for testing purposes
    private final GameService gameService;
    private final TicketService ticketService;

    @GetMapping("/get-odds")
    public ResponseEntity<Object> getOdds() throws EntityNotFoundException {

        // baseball_mlb, americanfootball_cfl, soccer_usa_mls

        List<GameResponse> games = gameService.getOddsFromApi("baseball_mlb");

        List<GameResponse> additionalGames = gameService.getOddsFromApi("americanfootball_cfl");

        games.addAll(additionalGames);

        return ResponseEntity.ok(games);
    }

    @GetMapping("/get-scores")
    public ResponseEntity<Object> getScores() throws EntityNotFoundException {

        List<GameResponse> games = gameService.getScoresFromApi("baseball_mlb");

        List<GameResponse> additionalGames = gameService.getScoresFromApi("americanfootball_cfl");

        games.addAll(additionalGames);

        return ResponseEntity.ok(games);
    }

    @GetMapping("/validate-tickets")
    public ResponseEntity<Object> validateTickets() throws EntityNotFoundException {

        List<TicketResponse> games = ticketService.validateTickets();

        return ResponseEntity.ok(games);
    }

    @GetMapping("/delete-games")
    public ResponseEntity<Object> deleteGames() throws EntityNotFoundException {

        List<GameResponse> games = gameService.deleteGamesFromDatabase();

        return ResponseEntity.ok(games);
    }
}
