package com.alluz.blog.domain.notification;

import com.alluz.blog.web.dto.BlogDto;
import com.alluz.blog.web.dto.CommentDto;
import com.alluz.blog.web.dto.ContactDto;
import com.alluz.blog.web.dto.UserAccountDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MessageSenderTest {

    @Mock
    private EmailProperties properties;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private MessageSender messageSender;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testNotifyAuthorForNewComment() {
        // Arrange
        UserAccountDto blogAuthor = new UserAccountDto();
        blogAuthor.setEmail("author@example.com");
        blogAuthor.setDisplayName("Author");

        UserAccountDto commentUser = new UserAccountDto();
        commentUser.setDisplayName("Commenter");

        CommentDto comment = new CommentDto();
        comment.setContent("Nice blog post!");

        BlogDto blog = new BlogDto();
        blog.setTitle("My Blog");

        ArgumentCaptor<EmailMessage> captor = ArgumentCaptor.forClass(EmailMessage.class);

        // Act
        messageSender.notifyAuthorForNewComment(blogAuthor, commentUser, comment, blog);

        // Assert
        verify(emailService, times(1)).sendEmail(captor.capture());
        EmailMessage capturedMessage = captor.getValue();

        assertEquals("You got comment on your blog post My Blog", capturedMessage.getSubject());
        assertEquals("Commenter posted a comment on your blog: Nice blog post!", capturedMessage.getBody());
        assertEquals("author@example.com", capturedMessage.getToEmail());
    }

    @Test
    void testNotifyAdminForContactMessage() {
        // Arrange
        ContactDto contact = new ContactDto();
        contact.setName("John Doe");
        contact.setMessage("I have a question.");
        contact.setEmail("johndoe@example.com");

        ArgumentCaptor<EmailMessage> captor = ArgumentCaptor.forClass(EmailMessage.class);

        // Act
        messageSender.notifyAdminForContactMessage(contact);

        // Assert
        verify(emailService, times(1)).sendEmail(captor.capture());
        EmailMessage capturedMessage = captor.getValue();

        assertEquals("Contact message from John Doe", capturedMessage.getSubject());
        assertEquals("John Doe tried to contact you on your website: I have a question.", capturedMessage.getBody());
        assertEquals("johndoe@example.com", capturedMessage.getFromEmail());
    }

    @Test
    void testSendNewUserRegistered() {
        // Arrange
        UserAccountDto user = new UserAccountDto();
        user.setDisplayName("New User");
        user.setEmail("newuser@example.com");

        ArgumentCaptor<EmailMessage> captor = ArgumentCaptor.forClass(EmailMessage.class);

        // Act
        messageSender.sendNewUserRegistered(user);

        // Assert
        verify(emailService, times(1)).sendEmail(captor.capture());
        EmailMessage capturedMessage = captor.getValue();

        assertEquals("New user registered", capturedMessage.getSubject());
        assertEquals("A new user registered: name is New User, email is newuser@example.com", capturedMessage.getBody());
    }

    @Test
    void testSendNewUserRegisteredWhenEmailIsNull() {
        // Arrange
        UserAccountDto user = new UserAccountDto();
        user.setDisplayName("New User");

        ArgumentCaptor<EmailMessage> captor = ArgumentCaptor.forClass(EmailMessage.class);

        // Act
        messageSender.sendNewUserRegistered(user);

        // Assert
        verify(emailService, times(1)).sendEmail(captor.capture());
        EmailMessage capturedMessage = captor.getValue();

        assertEquals("New user registered", capturedMessage.getSubject());
        assertEquals("A new user registered: name is New User, email is [not set]", capturedMessage.getBody());
    }

    @Test
    void testSendNewPostPublished() {
        // Arrange
        UserAccountDto author = new UserAccountDto();
        author.setDisplayName("Author Name");

        BlogDto blog = new BlogDto();
        blog.setTitle("Exciting Blog Post");

        ArgumentCaptor<EmailMessage> captor = ArgumentCaptor.forClass(EmailMessage.class);

        // Act
        messageSender.sendNewPostPublished(author, blog);

        // Assert
        verify(emailService, times(1)).sendEmail(captor.capture());
        EmailMessage capturedMessage = captor.getValue();

        assertEquals("New post published", capturedMessage.getSubject());
        assertEquals("A new post was published: title is Exciting Blog Post, author is Author Name", capturedMessage.getBody());
    }
}
