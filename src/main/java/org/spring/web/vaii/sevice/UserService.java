package org.spring.web.vaii.sevice;

import org.spring.web.vaii.Authority;
import org.spring.web.vaii.entity.user.User;
import org.spring.web.vaii.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public User createUser(String username, String password, String email) {
        this.userRepository.createUser(username, this.encoder.encode(password), email);
        User user = new User();
        user.setEmail(email);
        user.setAuthority(Authority.ROLE_USER);
        user.setUsername(username);
        return user;
    }

    public int numberOfUsers() {
        return this.userRepository.numberOfUsers();
    }
}
