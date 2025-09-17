package org.spring.web.vaii.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class PasswordValidator {
    private final Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{12,}$");

    public boolean validate(String password) {
        if (password == null) return false;
        return pattern.matcher(password).matches();
    }
}
