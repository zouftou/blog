package com.alluz.blog.domain.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendEmail(final EmailMessage message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(message.getFromEmail());
        simpleMailMessage.setReplyTo(message.getReplyTo());
        simpleMailMessage.setSubject(message.getSubject());
        simpleMailMessage.setText(message.getBody());
        try {
            LOGGER.info("Try to send email to {}, message is: {}", message.getToEmail(), message.getBody());
            emailSender.send(simpleMailMessage);
            LOGGER.info("Sent email successfully");
        } catch (Exception ex) {
            LOGGER.debug("Exception:", ex);
            LOGGER.error("Got exception when sending email through sendgrid: {}", ex.getMessage());
        }
    }
}
