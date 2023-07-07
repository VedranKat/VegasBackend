package com.example.vegasBackend.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketGameResponse {

    private Long id;
    private GameResponse gameResponse;
    private String chosenWinner;
}
