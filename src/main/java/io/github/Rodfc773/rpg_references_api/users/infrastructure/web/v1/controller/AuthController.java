package io.github.Rodfc773.rpg_references_api.users.infrastructure.web.v1.controller;

import io.github.Rodfc773.rpg_references_api.users.application.services.AuthService;
import io.github.Rodfc773.rpg_references_api.users.infrastructure.web.v1.dto.LoginRequestDTO;
import io.github.Rodfc773.rpg_references_api.users.infrastructure.web.v1.dto.LoginResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService service){
        this.authService = service;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO credentials){
        String token = this.authService.login(credentials);

        LoginResponseDTO response = new LoginResponseDTO(token);

        return ResponseEntity.ok().body(response);
    }
}
