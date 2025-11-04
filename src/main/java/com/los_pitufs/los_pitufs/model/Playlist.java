package com.los_pitufs.los_pitufs.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "playlists")
@Data
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name; // Nome da playlist (ex: "Jogando Agora", "Quero Jogar")

    private String description; // Descrição opcional da playlist

    // Referência ao usuário dono da playlist
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Relacionamento Many-to-Many com Game
    @ManyToMany
    @JoinTable(
            name = "playlist_games",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    private List<Game> games = new ArrayList<>();

    // Construtores
    public Playlist() {}
}
