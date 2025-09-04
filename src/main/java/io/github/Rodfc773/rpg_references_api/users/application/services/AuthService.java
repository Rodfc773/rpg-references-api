package io.github.Rodfc773.rpg_references_api.users.application.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.github.Rodfc773.rpg_references_api.users.application.port.repository.UserRepositoryPort;
import io.github.Rodfc773.rpg_references_api.users.domain.exceptions.InvalidDataException;
import io.github.Rodfc773.rpg_references_api.users.domain.models.UserModel;
import io.github.Rodfc773.rpg_references_api.users.infrastructure.security.TokenService;
import io.github.Rodfc773.rpg_references_api.users.infrastructure.web.v1.dto.LoginRequestDTO;
import io.github.Rodfc773.rpg_references_api.users.infrastructure.web.v1.dto.LoginResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthService {

    private final UserRepositoryPort repository;
    private final PasswordEncoder passwordEncoder;

    @Value("jwt.secret")
    private String secretKey;

    public AuthService(UserRepositoryPort repository, PasswordEncoder passwordEncoder){
        this.passwordEncoder =  passwordEncoder;
        this.repository = repository;
    }

    public LoginResponseDTO authenticate(LoginRequestDTO requestDTO) throws AuthenticationException {

        UserModel user = this.repository.findByEmail(requestDTO.email()).orElseThrow(
                () -> {throw new UsernameNotFoundException("Usuário não encontrado ou não existe");
                }
        );

        boolean isPasswordCorrect = this.passwordEncoder.matches(requestDTO.password(), user.getPassword());

        if(!isPasswordCorrect) throw new InvalidDataException("The password or user name is incorrect");

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expires = Instant.now().plus(Duration.ofDays(2));

        var token = JWT.create().withIssuer("referencias").withSubject(user.getId().toString())
                .withExpiresAt(expires)
                .withClaim("roles", Arrays.asList(user.getRole().name()))
                .sign(algorithm);

        LoginResponseDTO responseDTO = new LoginResponseDTO(token);

        return  responseDTO;
    }
}
