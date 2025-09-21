package org.spring.web.vaii.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.spring.web.vaii.sevice.JwtService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.time.LocalTime;

@Component
public class CustomFilter extends OncePerRequestFilter
{
    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;



    public CustomFilter(@Qualifier("customAuthManager") AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService =  jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uri = request.getRequestURI();

        Authentication auth = null;
        if (uri.startsWith("/jobs/countdown")) {
            auth = new GuardianAuthenticationToken(authentication.getName(),authentication.getAuthorities(), null);
            auth = this.authenticationManager.authenticate(auth);
            SecurityContextHolder.getContext().setAuthentication(auth);
            String token = this.jwtService.generateToken(auth.getName(), auth.getAuthorities(), (LocalTime) auth.getDetails());
            response.setHeader("Authorization", "Bearer " + token);
        }






        filterChain.doFilter(request, response);
    }

}
