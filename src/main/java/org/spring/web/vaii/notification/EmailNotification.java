package org.spring.web.vaii.notification;

import org.spring.web.vaii.email.EmailService;
import org.springframework.stereotype.Component;

@Component
public class EmailNotification
{
    private final EmailService emailService;


    public EmailNotification(EmailService emailService) {
        this.emailService = emailService;
    }


    public void resetPassword()
    {

    }
}
