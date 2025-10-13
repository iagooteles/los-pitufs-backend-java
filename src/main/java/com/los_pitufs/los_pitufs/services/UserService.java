package com.los_pitufs.los_pitufs.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.los_pitufs.los_pitufs.dto.UserDTO;
import com.los_pitufs.los_pitufs.model.User;
import com.los_pitufs.los_pitufs.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> listarUsuarios() {
        List<User> usuariosListados = userRepository.findAll();

        return usuariosListados.stream()
        .map(this::toDTO)
        .toList();
    }

    public UserDTO criarUsuario(User user) {
        User usuarioSalvo = userRepository.save(user);
        return toDTO(usuarioSalvo);
    }

    private UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        dto.setBio(user.getBio());
        dto.setProfilePictureUrl(user.getProfilePictureUrl());
        dto.setBirthDate(user.getBirthDate());
        dto.setCountry(user.getCountry());
        dto.setRole(user.getRole());

        return dto;
    }
}
