package med.voll.api.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JWTService {

    @Value("jwt.secret")
    private String secret;

    public String gerarToken(UserDetails user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withIssuer("Voll.med API refactor")
                .withSubject(user.getUsername())
                .withExpiresAt(dataExpiracao())
                .sign(algorithm);
    }

    public String getSubject(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.require(algorithm)
                .withIssuer("Voll.med API refactor")
                .build()
                .verify(token)
                .getSubject();
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }
}
