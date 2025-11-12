package io.github.rodfc773.rpgreferencesapi.users.application.services;


import io.github.rodfc773.rpgreferencesapi.users.application.port.repository.UserRepositoryPort;
import io.github.rodfc773.rpgreferencesapi.users.domain.exceptions.InvalidDataException;
import io.github.rodfc773.rpgreferencesapi.users.domain.models.UserModel;
import io.github.rodfc773.rpgreferencesapi.users.infrastructure.security.TokenService;
import io.github.rodfc773.rpgreferencesapi.users.infrastructure.web.v1.dto.LoginRequestDTO;
import io.github.rodfc773.rpgreferencesapi.users.infrastructure.web.v1.dto.LoginResponseDTO;

import io.github.rodfc773.rpgreferencesapi.users.infrastructure.web.v1.dto.NewAccessTokenResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Stream;


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

        UserModel user = repository.findByEmail(requestDTO.email())
                .filter(userModel -> passwordEncoder.matches(requestDTO.password(), userModel.getPassword()))
                .orElseThrow(() -> new InvalidDataException("The password or user name is incorrect"));

        var accessToken = tokenService.generateToken(user);
        var refreshToken= tokenService.generateRefreshToken(user);

        user.setRefreshToken(refreshToken);
        user.setRefreshTokenExpiry(Instant.now().plus(expirationDays, ChronoUnit.DAYS));
        repository.save(user);

        return new LoginResponseDTO(accessToken, refreshToken, "Bearer", LocalDateTime.now());
    }

    public NewAccessTokenResponseDTO refreshToken(String refreshToken){
        UserModel user = this.repository.findByRefreshToken(refreshToken).orElseThrow(()-> new RuntimeException("Refresh token not found"));

        if(user.getRefreshTokenExpiry().isBefore(Instant.now())) throw new RuntimeException("Refresh token expired, please do the login again");

        String newAccessToken = tokenService.generateRefreshToken(user);

        return new NewAccessTokenResponseDTO(newAccessToken);
    }
}
