package dev.tanay.productservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, TokenValidator tokenValidator) throws Exception {
        TokenAuthenticationFilter filter = new TokenAuthenticationFilter(tokenValidator);

        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for non-browser clients (Postman/React)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Allow EVERYTHING
                )
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
