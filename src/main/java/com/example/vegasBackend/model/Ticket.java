package com.example.vegasBackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal price;
    @Column(name = "win_amount")
    private BigDecimal winAmount;
    private Date endDate;
    private boolean isWon;
    private boolean isFinished;
    @ManyToOne
    private User user;
    @OneToMany
    @JoinColumn(name = "ticket_id")
    private Set<Game> games;
}
