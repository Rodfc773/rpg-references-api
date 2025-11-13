package io.github.rodfc773.rpgreferencesapi.users.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.github.rodfc773.rpgreferencesapi.users.domain.models.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.access-token.expiration-minutes}")
    private long accessTokenExpirationMinutes;

    @Value("${jwt.refresh-token.expiration-days}")
    private long refreshTokenExpirationDays;

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

        var expires = Instant.now().plus(accessTokenExpirationMinutes, ChronoUnit.MINUTES);


        return JWT.create().withIssuer("referencias").withSubject(user.getId().toString())
                .withExpiresAt(expires)
                .withClaim("roles", List.of(user.getRole().name()))
                .withIssuedAt(Date.from(Instant.now()))
                .sign(algorithm);
    }

    public String generateRefreshToken(UserModel user){
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expires = Instant.now().plus(refreshTokenExpirationDays, ChronoUnit.DAYS);

        return JWT.create()
                .withIssuer("referencias")
                .withSubject(user.getId().toString())
                .withExpiresAt(expires)
                .withIssuedAt(Date.from(Instant.now()))
                .sign(algorithm);
    }

}
