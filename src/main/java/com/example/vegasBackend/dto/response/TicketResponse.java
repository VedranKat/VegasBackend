package com.example.vegasBackend.dto.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@RequiredArgsConstructor
public class TicketResponse {

    private Long id;
    private BigDecimal price;
    private BigDecimal winAmount;
    private boolean isWon;
    private boolean isFinished;
    private Date dateCreated;
}
