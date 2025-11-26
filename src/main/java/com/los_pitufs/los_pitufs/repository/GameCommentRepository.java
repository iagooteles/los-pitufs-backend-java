package com.los_pitufs.los_pitufs.repository;

import com.los_pitufs.los_pitufs.model.GameComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameCommentRepository extends JpaRepository<GameComment, Long> {
    List<GameComment> findByGameId(Long gameId);
    List<GameComment> findByUserId(Long userId);
}
