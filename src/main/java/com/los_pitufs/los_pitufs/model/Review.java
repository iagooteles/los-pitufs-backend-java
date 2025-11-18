package com.los_pitufs.los_pitufs.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer rating;

    // O texto da avaliação (opcional)
    private String comment;

    // Referência ao jogo que está sendo avaliado
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    // Referência ao usuário que fez a avaliação
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Data e hora da avaliação
    private LocalDateTime reviewDate;

    // Construtores
    public Review() {
        this.reviewDate = LocalDateTime.now();
    }
}
