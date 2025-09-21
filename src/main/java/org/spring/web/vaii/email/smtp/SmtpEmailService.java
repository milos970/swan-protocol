package org.spring.web.vaii.email.smtp;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.spring.web.vaii.email.EmailMessage;
import org.spring.web.vaii.email.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class SmtpEmailService implements EmailService
{
    private final JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String sourceEmail;


    public SmtpEmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendEmail(EmailMessage emailMessage) {
        try {
            MimeMessage message = this.emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(sourceEmail);
            helper.setTo(emailMessage.to());
            helper.setSubject(emailMessage.subject());
            helper.setText(emailMessage.content(), true);
            this.emailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
