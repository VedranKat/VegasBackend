package com.example.vegasBackend.controller;

import com.example.vegasBackend.service.api.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping("/get-odds")
    public ResponseEntity<Object> getOdds(){

        return ResponseEntity.ok(gameService.getGamesFromDatabase());
    }
}
