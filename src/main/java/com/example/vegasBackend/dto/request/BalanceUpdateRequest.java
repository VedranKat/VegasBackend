package com.example.vegasBackend.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BalanceUpdateRequest {
    private String email;
    private BigDecimal balanceDelta;
}
