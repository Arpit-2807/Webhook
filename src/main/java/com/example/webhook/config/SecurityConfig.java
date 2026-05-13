package com.example.webhook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                        // Allow these URLs without login
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/logs").permitAll()
                        .requestMatchers("/webhook/**").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/static/**").permitAll()
                        // Everything else needs login
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf
                        // Disable CSRF for webhook and H2 console
                        .ignoringRequestMatchers("/webhook/**")
                        .ignoringRequestMatchers("/h2-console/**")
                )
                .headers(headers -> headers
                        // Allow H2 console to show in browser
                        .frameOptions(frame -> frame.disable())
                );

        return http.build();
    }
}