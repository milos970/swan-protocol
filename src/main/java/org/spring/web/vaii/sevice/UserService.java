package org.spring.web.vaii.sevice;

import org.spring.web.vaii.entity.User;
import org.spring.web.vaii.repository.UserRepository;
import org.spring.web.vaii.validator.EmailValidator;
import org.spring.web.vaii.validator.PasswordValidator;
import org.spring.web.vaii.validator.UsernameValidator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final EmailValidator emailValidator;
    private final PasswordValidator passwordValidator;
    private final UsernameValidator usernameValidator;

    public UserService(UserRepository userRepository,
                       PasswordEncoder encoder, EmailValidator emailValidator, PasswordValidator passwordValidator, UsernameValidator usernameValidator) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.emailValidator = emailValidator;
        this.passwordValidator = passwordValidator;
        this.usernameValidator = usernameValidator;
    }

    public User createUser(String username, String password, String email) {
        if (!this.usernameValidator.validate(username)) {
            throw new IllegalArgumentException("Invalid username!");
        }

        if (!this.emailValidator.validate(email)) {
            throw new IllegalArgumentException("Invalid email!");
        }

        if (!this.passwordValidator.validate(password)) {
            throw new IllegalArgumentException("Invalid password!");
        }

        this.userRepository.createUser(username, this.encoder.encode(password), email);
        User user = new User();
        user.setEmail(email);

        user.setUsername(username);
        return user;
    }

    public void removeUser(long id)
    {
        this.userRepository.removeUser(id);
    }

    public int numberOfUsers() {
        return this.userRepository.numberOfUsers();
    }

    public boolean existsByUsername(String username) {
        return this.userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public List<User> findAllUsers() {
        return this.userRepository.findAllUsers();
    }
}
