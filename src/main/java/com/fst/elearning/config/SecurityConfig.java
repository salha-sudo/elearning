package com.fst.elearning.config;

import com.fst.elearning.service.UtilisateurDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UtilisateurDetailsService userDetailsService;

    public SecurityConfig(UtilisateurDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth

                // ── Pages publiques ──────────────────────
                .requestMatchers(
                    "/",
                    "/catalogue",
                    "/catalogue/**",
                    "/cours/detail/**",
                    "/register",
                    "/login",
                    "/dashboard",
                    "/css/**",
                    "/js/**",
                    "/images/**",
                    "/uploads/**",
                    "/webjars/**"
                ).permitAll()

                // ── Formateur ────────────────────────────
                .requestMatchers("/formateur/**").hasRole("FORMATEUR")

                // ── Apprenant ────────────────────────────
                .requestMatchers("/apprenant/**").hasRole("APPRENANT")

                // ── Admin ────────────────────────────────
                .requestMatchers("/admin/**").hasRole("ADMIN")

                // ── Tout le reste ────────────────────────
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/dashboard", true)
                .failureUrl("/login?error")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll()
            )
            .userDetailsService(userDetailsService);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}