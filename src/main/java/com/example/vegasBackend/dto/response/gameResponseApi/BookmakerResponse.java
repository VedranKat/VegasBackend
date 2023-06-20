package com.example.vegasBackend.dto.response.gameResponseApi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookmakerResponse {

    private String key;
    private String title;
    private String last_update;
    private List<MarketResponse> markets;

}
