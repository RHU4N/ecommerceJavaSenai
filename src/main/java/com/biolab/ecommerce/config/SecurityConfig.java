package com.biolab.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desabilita o CSRF para liberar o POST
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated()) // Exige autenticação para tudo
                .httpBasic(Customizer.withDefaults()); // Mantém o Basic Auth ativo

        return http.build();
    }
}

