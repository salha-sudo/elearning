package com.fst.elearning.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.fst.elearning.service.CustomUserDetailsService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // à activer en prod si nécessaire
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/formateur/**").hasRole("FORMATEUR")
                        .requestMatchers("/apprenant/**").hasRole("APPRENANT")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler((req, res, authentication) -> {
                            String role = authentication.getAuthorities().toString();
                            if (role.contains("ADMIN"))
                                res.sendRedirect("/cours");
                            else if (role.contains("FORMATEUR"))
                                res.sendRedirect("/formateur/cours");
                            else
                                res.sendRedirect("/apprenant/cours");
                        })
                        .permitAll()
                    )
                    .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}