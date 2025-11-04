package com.los_pitufs.los_pitufs.controller;

import com.los_pitufs.los_pitufs.dto.GameDTO;
import com.los_pitufs.los_pitufs.model.Game;
import com.los_pitufs.los_pitufs.services.GameService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<List<GameDTO>> listarJogos() {
        List<GameDTO> jogos = gameService.listarJogos();
        return ResponseEntity.ok(jogos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDTO> buscarJogoPorID(@PathVariable Long id) {
        return gameService.buscarJogoPorID(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GameDTO> cadastrarJogo(@Valid @RequestBody Game game) {
        GameDTO novoJogo = gameService.cadastrarJogo(game);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoJogo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameDTO> atualizarJogo(@PathVariable Long id, @Valid @RequestBody Game game) {
        try {
            GameDTO jogoAtualizado = gameService.atualizarJogo(id, game);
            return ResponseEntity.ok(jogoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarJogo(@PathVariable Long id) {
        boolean deletado = gameService.deletarJogo(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
