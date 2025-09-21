package org.spring.web.vaii.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.spring.web.vaii.sevice.JwtService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class CustomTokenFilter extends OncePerRequestFilter
{
    private final JwtService jwtService;

    public CustomTokenFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            if (jwtService.isTokenValid(token)) {

                String username = jwtService.extractUsername(token);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    List<GrantedAuthority> roles = jwtService.extractRoles(token).stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());

                    LocalTime expirationTime = jwtService.extractExpirationAsLocalTime(token);

                    GuardianAuthenticationToken authToken = new GuardianAuthenticationToken(username, roles,expirationTime);
                    authToken.setAuthenticated(true);
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }

        filterChain.doFilter(request,response);

    }
}
