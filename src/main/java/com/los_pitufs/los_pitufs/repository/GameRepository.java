package com.los_pitufs.los_pitufs.repository;

import com.los_pitufs.los_pitufs.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> {
}
