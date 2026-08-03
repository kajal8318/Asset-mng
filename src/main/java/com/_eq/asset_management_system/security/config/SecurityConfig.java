package com._eq.asset_management_system.security.config;

import com._eq.asset_management_system.security.filter.FirebaseAuthenticationFilter;
import com._eq.asset_management_system.security.handler.AuthenticationEntryPointHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final FirebaseAuthenticationFilter firebaseAuthenticationFilter;
    private final AuthenticationEntryPointHandler authenticationEntryPointHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http

                .csrf(csrf -> csrf.disable())

                .cors(Customizer.withDefaults())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint(authenticationEntryPointHandler))

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                                "/public/**",
                                "/employees",
                                "/users"
                                        ).permitAll()


                        .anyRequest().authenticated())

                .addFilterBefore(
                        firebaseAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}