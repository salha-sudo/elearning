package com.fst.elearning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            // Désactiver CSRF (utile pour tests / API)
            .csrf(csrf -> csrf.disable())

            // Autorisations
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/formateur/**").hasRole("FORMATEUR")
                .requestMatchers("/apprenant/**").hasRole("APPRENANT")
                .anyRequest().permitAll()
            )

            // Login personnalisé
            .formLogin(login -> login
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )

            // Logout propre
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
            );

        return http.build();
    }

    // BCrypt encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}