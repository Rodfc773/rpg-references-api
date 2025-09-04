package io.github.Rodfc773.rpg_references_api.users.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class TokenService {

    @Value("jwt.secret")
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

}
