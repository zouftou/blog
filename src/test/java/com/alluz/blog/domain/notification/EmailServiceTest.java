package com.alluz.blog.domain.notification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmailServiceTest {

    @Mock
    private JavaMailSender emailSender;

    @InjectMocks
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendEmailSuccessfully() {
        // Arrange
        EmailMessage message = new EmailMessage();
        message.setFromEmail("from@example.com");
        message.setReplyTo("replyto@example.com");
        message.setToEmail("to@example.com");
        message.setSubject("Test Subject");
        message.setBody("Test Body");

        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);

        // Act
        emailService.sendEmail(message);

        // Assert
        verify(emailSender, times(1)).send(captor.capture());
        SimpleMailMessage capturedMessage = captor.getValue();

        assertEquals("from@example.com", capturedMessage.getFrom());
        assertEquals("replyto@example.com", capturedMessage.getReplyTo());
        assertEquals("Test Subject", capturedMessage.getSubject());
        assertEquals("Test Body", capturedMessage.getText());
    }

    @Test
    void testSendEmailHandlesException() {
        // Arrange
        EmailMessage message = new EmailMessage();
        message.setFromEmail("from@example.com");
        message.setReplyTo("replyto@example.com");
        message.setToEmail("to@example.com");
        message.setSubject("Test Subject");
        message.setBody("Test Body");

        doThrow(new RuntimeException("Email service error")).when(emailSender).send(any(SimpleMailMessage.class));

        // Act
        emailService.sendEmail(message);

        // Assert
        verify(emailSender, times(1)).send(any(SimpleMailMessage.class));
        // No exception is thrown, service handles it gracefully
    }
}
