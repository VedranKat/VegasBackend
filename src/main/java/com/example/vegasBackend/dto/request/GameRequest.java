package com.example.vegasBackend.dto.request;

import lombok.*;

@Data
@RequiredArgsConstructor
public class GameRequest {

    private String gameApiId;
    private String chosenWinner;
}
