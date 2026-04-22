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
            //  CSRF désactivé 
            .csrf(csrf -> csrf.disable())

            //  AUTORISATIONS
            .authorizeHttpRequests(auth -> auth

                // pages publiques
                .requestMatchers(
                        "/login",
                        "/css/**",
                        "/js/**",
                        "/images/**"
                ).permitAll()

                // rôles
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/formateur/**").hasRole("FORMATEUR")
                .requestMatchers("/apprenant/**").hasRole("APPRENANT")

                // tout le reste nécessite login
                .anyRequest().authenticated()
            )

            //  LOGIN THYMELEAF
            .formLogin(login -> login
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/web/cours", true)
                .failureUrl("/login?error")
                .permitAll()
            )

            // LOGOUT
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
            );

        return http.build();
    }

    // PASSWORD ENCODER
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}