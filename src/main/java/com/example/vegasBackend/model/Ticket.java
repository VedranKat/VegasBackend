package com.example.vegasBackend.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Builder
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal price;
    private BigDecimal winAmount;
    private boolean isWon;
    private boolean isFinished;
    @ManyToOne
    private User user;
}
