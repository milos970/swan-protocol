package org.spring.web.vaii.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class EmailValidator {

    private final Pattern pattern = Pattern.compile("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", Pattern.CASE_INSENSITIVE);

    public boolean validate(String email) {
        if (email == null) return false;
        return pattern.matcher(email).matches();
    }
}