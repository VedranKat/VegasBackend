package com.example.vegasBackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Data
@Table(name = "users")//in plural because user is a reserved word in postgres, I'm naming all tables in plural to avoid confusion
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private BigDecimal balance;
    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<Ticket> tickets;
}
