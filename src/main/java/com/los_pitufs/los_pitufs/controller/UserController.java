package com.los_pitufs.los_pitufs.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.los_pitufs.los_pitufs.dto.UserDTO;
import com.los_pitufs.los_pitufs.model.User;
import com.los_pitufs.los_pitufs.services.UserService;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<UserDTO> listarUsuarios() {
        return userService.listarUsuarios();
    }

    @PostMapping("/")
    public UserDTO criarUsuario(@RequestBody User user) {
        return userService.criarUsuario(user);
    }
}
