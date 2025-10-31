package com.los_pitufs.los_pitufs.repository;


import com.los_pitufs.los_pitufs.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

    // Buscar todas as playlists de um usuário específico
    List<Playlist> findByUserId(Long userId);
}
