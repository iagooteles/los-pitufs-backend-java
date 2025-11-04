package com.los_pitufs.los_pitufs.services;

import com.los_pitufs.los_pitufs.dto.GameDTO;
import com.los_pitufs.los_pitufs.model.Game;
import com.los_pitufs.los_pitufs.repository.GameRepository;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<GameDTO> listarJogos() {
        List<Game> jogosListados = gameRepository.findAll();

        return jogosListados.stream()
                .map(this::toDTO)
                .toList();
    }

    public Optional<GameDTO> buscarJogoPorID(Long id) {
        return gameRepository.findById(id).map(this::toDTO);
    }

    public GameDTO cadastrarJogo(Game game) {
        Game jogoSalvo = gameRepository.save(game);
        return toDTO(jogoSalvo);
    }

    public GameDTO atualizarJogo(Long id, Game updatedGame) {
        return gameRepository.findById(id)
                .map( game -> {
                    game.setTitle(updatedGame.getTitle());
                    game.setDeveloper(updatedGame.getDeveloper());
                    game.setPublisher(updatedGame.getPublisher());
                    game.setGenres(updatedGame.getGenres());
                    game.setReleaseDate(updatedGame.getReleaseDate());
                    game.setCoverImageUrl(updatedGame.getCoverImageUrl());
                    game.setDescription(updatedGame.getDescription());

                    // A média de avaliação e a contagem de avaliações não devem ser atualizadas diretamente por esta rota
                    // Elas devem ser atualizadas por um service de avaliação separado.

                    Game jogoSalvo = gameRepository.save(game);
                    return toDTO(jogoSalvo);
                }).orElseThrow(() -> new RuntimeException("Jogo não encontrado."));
    }

    public boolean deletarJogo(Long id) {
        if (gameRepository.existsById(id)) {
            gameRepository.deleteById(id);
            return true;
        }

        return false;
    }

    private GameDTO toDTO(Game game) {
        GameDTO dto = new GameDTO();
        dto.setId(game.getId());
        dto.setTitle(game.getTitle());
        dto.setDeveloper(game.getDeveloper());
        dto.setPublisher(game.getPublisher());
        dto.setGenres(game.getGenres());
        dto.setReleaseDate(game.getReleaseDate());
        dto.setCoverImageUrl(game.getCoverImageUrl());
        dto.setDescription(game.getDescription());

        return dto;
    }
}
