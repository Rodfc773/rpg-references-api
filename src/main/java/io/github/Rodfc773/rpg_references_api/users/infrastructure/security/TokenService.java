package io.github.Rodfc773.rpg_references_api.users.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.github.Rodfc773.rpg_references_api.users.domain.models.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;


@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secretKey;

    public DecodedJWT validateToken(String token){
        String cleanToken = token.replace("Bearer ", "");

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        try {
            DecodedJWT tokenDecoded = JWT.require(algorithm).build().verify(cleanToken);

            return  tokenDecoded;
        } catch (Exception e) {
            return null;
        }
    }
    public String generateToken(UserModel user) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expires = Instant.now().plus(Duration.ofDays(2));

        return JWT.create().withIssuer("referencias").withSubject(user.getId().toString())
                .withExpiresAt(expires)
                .withClaim("roles", List.of(user.getRole().name()))
                .sign(algorithm);
    }

}
