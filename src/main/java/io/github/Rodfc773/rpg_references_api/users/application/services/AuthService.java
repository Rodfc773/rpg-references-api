package io.github.Rodfc773.rpg_references_api.users.application.services;

import io.github.Rodfc773.rpg_references_api.users.application.port.repository.UserRepositoryPort;
import io.github.Rodfc773.rpg_references_api.users.domain.models.UserModel;
import io.github.Rodfc773.rpg_references_api.users.infrastructure.security.TokenService;
import io.github.Rodfc773.rpg_references_api.users.infrastructure.web.v1.dto.LoginRequestDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;


    public AuthService(AuthenticationManager authenticationManager, TokenService tokenService)
    {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public String login(LoginRequestDTO loginRequest){
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());

        Authentication auth = this.authenticationManager.authenticate(usernamePassword);

        var user = (UserModel) auth.getPrincipal();

        return tokenService.generateToken(user);

    }
}
