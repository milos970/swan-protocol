package org.spring.web.vaii.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.spring.web.vaii.sevice.CustomSessionManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


public class CustomFilter extends OncePerRequestFilter {

    private final CustomSessionManagement customSessionManagement;

    @Autowired
    public CustomFilter(CustomSessionManagement customSessionManagement) {
        this.customSessionManagement = customSessionManagement;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();

        if (path.startsWith("/login") || path.startsWith("/logout")) {
            filterChain.doFilter(request, response);
            return;
        }

        HttpSession session = request.getSession(false);
        if (session != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
                String sessionId = session.getId();
                if (this.customSessionManagement.isEmpty()) {
                    this.customSessionManagement.setSessionId(sessionId);
                } else if (this.customSessionManagement.checkSessionId(sessionId)) {
                    filterChain.doFilter(request, response);
                } else {
                    response.sendRedirect("/login?alreadyLogged=true");
                    return;
                }
            }

        }
        filterChain.doFilter(request, response);


    }
}
