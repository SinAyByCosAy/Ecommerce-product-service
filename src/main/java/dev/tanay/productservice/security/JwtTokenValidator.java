package dev.tanay.productservice.security;

import dev.tanay.productservice.models.JwtKeyEntity;
import dev.tanay.productservice.repositories.JwtKeyRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.List;

public class JwtTokenValidator implements TokenValidator{
    private JwtKeyRepository jwtKeyRepository;
    public JwtTokenValidator (JwtKeyRepository jwtKeyRepository){
        this.jwtKeyRepository = jwtKeyRepository;
    }
    @Override
    public Authentication validate(String token){
        if(token == null || token.isBlank()) throw new RuntimeException("No token");
        String[] parts = token.split("\\.");
        if(parts.length != 3) throw new RuntimeException("Invalid token");

        Claims claims;
        claims = Jwts.parser()
                .keyLocator(header -> {
                    String kid = (String) header.get("kid"); // pick the correct secret that signed it
                    System.out.println(kid);
                    JwtKeyEntity keyEntity = jwtKeyRepository.findByKid(kid)
                            .orElseThrow(() -> new RuntimeException("Unknown key"));
                    if(keyEntity.getRetiredAt() != null && keyEntity.getRetiredAt().isBefore(Instant.now()))
                        throw new RuntimeException("Key retired");

                    return rebuildSecretKey(keyEntity);
                })
                .build()
                .parseSignedClaims(token)
                .getPayload();
        String userId = claims.getSubject();
        List<String> roles = claims.get("roles", List.class);
        List<SimpleGrantedAuthority> authorities =
                roles.stream()
                        .map(SimpleGrantedAuthority :: new)
                        .toList();

        return new UsernamePasswordAuthenticationToken(
                userId,
                null,
                authorities
        );
    }
    private SecretKey rebuildSecretKey(JwtKeyEntity keyEntity){
        byte[] keyBytes = Decoders.BASE64.decode(keyEntity.getSecretBase64());
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);
        return key;
    }
}
