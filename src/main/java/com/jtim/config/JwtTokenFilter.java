package com.jtim.config;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.debug("TokenFilter called for URI: {}", request.getRequestURI());
        
        String token = jwtUtil.getJwtTokenFromHeader(request);
        
        // Only process if token exists
        if (token != null && !token.isEmpty()) {
            try {
                String username = jwtUtil.getUserNameFromToken(token);
                if ("admin".equals(username) && jwtUtil.validateJwtToken(token)) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        username, null, List.of());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (Exception e) {
                log.error("Cannot set user authentication: {}", e.getMessage());
                // Don't set authentication, will result in 403
            }
        }
        
        filterChain.doFilter(request, response);
    }
}