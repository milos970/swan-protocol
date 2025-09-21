package org.spring.web.vaii.email;

import org.spring.web.vaii.EmailType;

public class TemplateFactory
{
    public static EmailTemplate getTemplate(EmailType type) {
        switch(type) {
            case PASSWORD_RESET: return new PasswordResetTemplate();
            case LOGIN_VERIFICATION: return new LoginVerificationTemplate();
            default: throw new IllegalArgumentException("Unknown email type");
        }
    }
}
