package org.spring.web.vaii.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalTime;
import java.util.Collection;

public class GuardianAuthenticationToken implements Authentication {

    private final String username;
    private final Collection<? extends GrantedAuthority> authorities;
    private final LocalTime expirationTime;
    private boolean isAuthenticated;


    public GuardianAuthenticationToken(String username, Collection<? extends GrantedAuthority> authorities, LocalTime expirationTime)
    {
        this.username = username;
        this.authorities = authorities;
        this.expirationTime = expirationTime;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return  this.expirationTime;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return this.isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return this.username;
    }

}
