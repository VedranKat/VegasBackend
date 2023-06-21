package com.example.vegasBackend.dto.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class TicketResponse {

    private BigDecimal price;
    private BigDecimal winAmount;
    private boolean isWon;
    private boolean isFinished;
    private String email;
}
