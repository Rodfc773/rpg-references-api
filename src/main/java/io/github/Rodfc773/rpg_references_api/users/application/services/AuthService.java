package io.github.Rodfc773.rpg_references_api.users.application.services;


import io.github.Rodfc773.rpg_references_api.users.application.port.repository.UserRepositoryPort;
import io.github.Rodfc773.rpg_references_api.users.domain.exceptions.InvalidDataException;
import io.github.Rodfc773.rpg_references_api.users.domain.models.UserModel;
import io.github.Rodfc773.rpg_references_api.users.infrastructure.security.TokenService;
import io.github.Rodfc773.rpg_references_api.users.infrastructure.web.v1.dto.LoginRequestDTO;
import io.github.Rodfc773.rpg_references_api.users.infrastructure.web.v1.dto.LoginResponseDTO;

import io.github.Rodfc773.rpg_references_api.users.infrastructure.web.v1.dto.NewAccessTokenResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@Service
public class AuthService {

    @Value("${jwt.refresh-token.expiration-days}")
    private long expirationDays;

    private final UserRepositoryPort repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthService(UserRepositoryPort repository, PasswordEncoder passwordEncoder, TokenService tokenService){
        this.passwordEncoder =  passwordEncoder;
        this.repository = repository;
        this.tokenService= tokenService;
    }

    public LoginResponseDTO authenticate(LoginRequestDTO requestDTO) throws AuthenticationException {

        UserModel user = this.repository.findByEmail(requestDTO.email()).orElseThrow(
                () -> {throw new UsernameNotFoundException("Usuário não encontrado ou não existe");
                }
        );

        boolean isPasswordCorrect = this.passwordEncoder.matches(requestDTO.password(), user.getPassword());

        if(!isPasswordCorrect) throw new InvalidDataException("The password or user name is incorrect");

        var accessToken = tokenService.generateToken(user);
        var refreshToken= tokenService.generateRefreshToken(user);

        user.setRefreshToken(refreshToken);
        user.setRefreshTokenExpiry(Instant.now().plus(expirationDays, ChronoUnit.DAYS));
        repository.save(user);

        return new LoginResponseDTO(accessToken, refreshToken, "Bearer", LocalDateTime.now());
    }

    public NewAccessTokenResponseDTO refreshToken(String refreshToken){
        UserModel user = this.repository.findByRefreshToken(refreshToken).orElseThrow(()-> new RuntimeException("Refresh token not found"));

        if(user.getRefreshTokenExpiry().isBefore(Instant.now())) throw new RuntimeException("Refresh token expirado, Por favor, faça o login novamente");

        String newAccessToken = tokenService.generateRefreshToken(user);

        return new NewAccessTokenResponseDTO(newAccessToken);
    }
}
