package dev.tanay.productservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;

import java.time.Instant;
import java.util.List;

@Configuration
@Profile("local")
public class LocalJwtConfig {

    @Bean
    public JwtDecoder jwtDecoder(){
        return token -> Jwt.withTokenValue(token)
                .header("alg", "none")
                .claim("sub", "local-user")
                .claim("roles", List.of("ROLE_ADMIN"))
                .claim("email", "local@test.com")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(3600))
                .build();
    }
}
