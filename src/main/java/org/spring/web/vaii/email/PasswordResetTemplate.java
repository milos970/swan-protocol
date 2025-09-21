package org.spring.web.vaii.email;

import java.util.Map;

public class PasswordResetTemplate implements EmailTemplate{


    @Override
    public String getSubject() {
        return "";
    }

    @Override
    public String renderBody(Map<String, Object> variables) {
        return "";
    }
}
