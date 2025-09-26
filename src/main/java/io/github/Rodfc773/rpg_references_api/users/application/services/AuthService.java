package io.github.Rodfc773.rpg_references_api.users.application.services;


import io.github.Rodfc773.rpg_references_api.users.application.port.repository.UserRepositoryPort;
import io.github.Rodfc773.rpg_references_api.users.domain.exceptions.InvalidDataException;
import io.github.Rodfc773.rpg_references_api.users.domain.models.UserModel;
import io.github.Rodfc773.rpg_references_api.users.infrastructure.security.TokenService;
import io.github.Rodfc773.rpg_references_api.users.infrastructure.web.v1.dto.LoginRequestDTO;
import io.github.Rodfc773.rpg_references_api.users.infrastructure.web.v1.dto.LoginResponseDTO;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

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

        var token = tokenService.generateToken(user);

        return new LoginResponseDTO(token);
    }
}
