package com.example.vegasBackend.controller;

import com.example.vegasBackend.dto.response.GameResponse;
import com.example.vegasBackend.dto.response.gameResponseApi.GameResponseApi;
import com.example.vegasBackend.service.api.GameService;
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

    private final GameService gameService;

    @GetMapping("/hello")
    public ResponseEntity<Object> sayHello(){

        List<GameResponseApi> games = gameService.getOdds("americanfootball_nfl");

        return ResponseEntity.ok(games);
    }
}
