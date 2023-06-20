package com.example.vegasBackend.dto.response.gameResponseApi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutcomeResponse {

    private String name;
    private double price;
}
