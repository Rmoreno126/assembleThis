package edu.redwoods.cis18.assemble.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/**", "/index.html", "/api/**", "/static/**", "/css/**", "/js/**", "/script.js", "/error", "/favicon.ico").permitAll() // Allow all API and public access
                        .anyRequest().authenticated() // Secure other endpoints
                )
                .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity
                .formLogin(form -> form.disable()) // Disable default login page
                .httpBasic(httpBasic -> httpBasic.disable()); // Disable basic auth

        return http.build();
    }
}
