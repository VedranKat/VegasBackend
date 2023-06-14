package com.example.vegasBackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")//in plural because user is a reserved word in postgres, I'm naming all tables in plural to avoid confusion
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private BigDecimal balance;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + "'" +
                ", balance='" + balance + "'" +
                "}";
    }
}
