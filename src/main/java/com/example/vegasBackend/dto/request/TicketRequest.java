package com.example.vegasBackend.dto.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@RequiredArgsConstructor
public class TicketRequest {

    private String email;
    private BigDecimal price;
    private BigDecimal winAmount;
    private List<GameRequest> games;
}
