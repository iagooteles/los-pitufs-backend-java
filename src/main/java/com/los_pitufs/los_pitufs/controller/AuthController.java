package com.los_pitufs.los_pitufs.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.los_pitufs.los_pitufs.dto.AuthResponse;
import com.los_pitufs.los_pitufs.dto.LoginDTO;
import com.los_pitufs.los_pitufs.dto.UserDTO;
import com.los_pitufs.los_pitufs.services.UserService;
import com.los_pitufs.los_pitufs.utils.JwtUtil;


@RestController
@RequestMapping
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        Optional<UserDTO> userOpt = userService.login(loginDTO.getEmail(), loginDTO.getPassword());
        if(userOpt.isPresent()) {
            String token = JwtUtil.generateToken(userOpt.get());
            return ResponseEntity.ok(new AuthResponse(userOpt.get(), token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }

}
