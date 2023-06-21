package com.example.vegasBackend.controller;

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

    @GetMapping("/get-odds")
    public ResponseEntity<Object> getOdds(){

        List<GameResponseApi> games = gameService.getOdds("baseball_mlb");

        return ResponseEntity.ok(games);
    }

    @GetMapping("/test")
    public ResponseEntity<Object> test(){

        return ResponseEntity.ok("test");
    }
}
