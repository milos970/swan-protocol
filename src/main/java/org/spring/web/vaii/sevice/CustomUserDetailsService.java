package org.spring.web.vaii.sevice;

import org.spring.web.vaii.entity.User;
import org.spring.web.vaii.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User entity = this.userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Employee not found"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(entity.getUsername())
                .password(entity.getPassword())
                .roles(entity.getRole().name())
                .build();
    }

}
