package org.spring.web.vaii.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String redirection = "/home";

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority auth : authorities) {
            if ("ROLE_ADMIN".equals(auth.getAuthority())) {
                redirection = "/users";
                break;
            }

            if ("ROLE_USER".equals(auth.getAuthority())) {
                redirection = "/home";
                break;
            }
        }

        response.sendRedirect(redirection);
    }
}
