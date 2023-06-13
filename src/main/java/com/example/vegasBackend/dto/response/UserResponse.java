package com.example.vegasBackend.dto.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class UserResponse {

    private Long id;
    private String email;
    private BigDecimal balance;
}
