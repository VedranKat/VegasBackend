package com.example.vegasBackend.dto.response.gameResponseApi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketResponse {

    private String key;
    private String last_update;
    private List<OutcomeResponse> outcomes;
}
