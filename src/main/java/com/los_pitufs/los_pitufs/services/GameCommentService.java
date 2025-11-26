package com.los_pitufs.los_pitufs.services;

import com.los_pitufs.los_pitufs.model.Game;
import com.los_pitufs.los_pitufs.model.GameComment;
import com.los_pitufs.los_pitufs.model.User;
import com.los_pitufs.los_pitufs.repository.GameCommentRepository;
import com.los_pitufs.los_pitufs.repository.GameRepository;
import com.los_pitufs.los_pitufs.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameCommentService {
    private final GameCommentRepository gameCommentRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    public GameCommentService(GameCommentRepository gameCommentRepository,
                              UserRepository userRepository,
                              GameRepository gameRepository) {
        this.gameCommentRepository = gameCommentRepository;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }

    public GameComment createComment(Long userId, Long gameId, String content) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Jogo não encontrado."));

        GameComment gameComment = new GameComment();
        gameComment.setContent(content);
        gameComment.setUser(user);
        gameComment.setGame(game);

        return gameCommentRepository.save(gameComment);
    }

    public List<GameComment> getCommentsByGame(Long gameId) {
        return gameCommentRepository.findByGameId(gameId);
    }

    public List<GameComment> getCommentsByUser(Long userId) {
        return gameCommentRepository.findByUserId(userId);
    }

    public boolean deleteComment(Long commentId) {
        if (gameCommentRepository.existsById(commentId)) {
            gameCommentRepository.deleteById(commentId);
            return true;
        }
        return false;
    }
}