package org.spring.web.vaii.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class CustomFilter extends OncePerRequestFilter
{
    private final AuthenticationManager authenticationManager;


    public CustomFilter(@Qualifier("customAuthManager") AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String uri = request.getRequestURI();

        Authentication auth = null;

        if (uri.startsWith("/jobs/countdown")) {
            auth = new GuardianAuthenticationToken(authentication.getName(),auth.getAuthorities());
        }

        SecurityContextHolder.getContext().setAuthentication(this.authenticationManager.authenticate(auth));


        filterChain.doFilter(request, response);
    }

}
