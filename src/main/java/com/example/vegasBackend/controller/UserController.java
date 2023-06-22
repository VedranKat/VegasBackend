package com.example.vegasBackend.controller;

import com.example.vegasBackend.dto.request.BalanceUpdateRequest;
import com.example.vegasBackend.exception.EntityNotFoundException;
import com.example.vegasBackend.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping("/update-balance")
    public ResponseEntity<Object> updateBalance(@RequestBody BalanceUpdateRequest balanceUpdateRequest) throws EntityNotFoundException {

        return ResponseEntity.ok(userService.updateBalance(balanceUpdateRequest));
    }

    @GetMapping("/get-user/{email}")
    public ResponseEntity<Object> getUserInfo(@PathVariable String email) throws EntityNotFoundException {

        return ResponseEntity.ok(userService.getByEmail(email));
    }
}
