package org.spring.web.vaii.validator;

import org.springframework.stereotype.Component;

@Component
public class UsernameValidator {
    private final int length = 15;

    public boolean validate(String username)
    {
        if ( (username == null) || (username.length() > length) ) {
            return false;
        }

        return true;
    }
}
