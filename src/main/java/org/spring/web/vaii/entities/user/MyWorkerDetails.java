package org.spring.web.vaii.entities.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class MyWorkerDetails implements UserDetails {

    private final Worker worker;

    public MyWorkerDetails(Worker worker) {
        this.worker = worker;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(this.worker.getRole());
        return Arrays.asList(authority);
    }

    public Worker getWorker() {
        return this.worker;
    }

    @Override
    public String getPassword() {
        return this.worker.getPassword();
    }

    @Override
    public String getUsername() {
        return this.worker.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
