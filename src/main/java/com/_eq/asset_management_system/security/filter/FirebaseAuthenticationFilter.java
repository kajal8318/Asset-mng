package com._eq.asset_management_system.security.filter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FirebaseAuthenticationFilter extends OncePerRequestFilter {

        @Override
        protected void doFilterInternal(HttpServletRequest request,
                                        HttpServletResponse response,
                                        FilterChain filterChain)
                throws ServletException, IOException {

            System.out.println("URI: " + request.getRequestURI());

            String authorizationHeader = request.getHeader("Authorization");
            if (authorizationHeader == null ||
                    !authorizationHeader.startsWith("Bearer ")) {

                filterChain.doFilter(request, response);
                return;
            }
            String token = authorizationHeader.substring(7);

            FirebaseToken decodedToken =
                    null;
            try {
                decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            } catch (FirebaseAuthException e) {
                throw new RuntimeException(e);
            }

            String firebaseUid =
                    decodedToken.getUid();

            System.out.println(firebaseUid);

            filterChain.doFilter(request, response);
        }
    }

