package com.los_pitufs.los_pitufs.repository;

import com.los_pitufs.los_pitufs.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Buscar todas as avaliações de um jogo específico
    List<Review> findByGameId(Long gameId);

    // Buscar a avaliação de um usuário para um jogo específico (para garantir que ele só avalie uma vez)
    Optional<Review> findByGameIdAndUserId(Long gameId, Long userId);
}
