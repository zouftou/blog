package com.alluz.blog.web.mail;

import com.alluz.blog.web.dto.BlogDto;
import com.alluz.blog.web.dto.CommentDto;
import com.alluz.blog.web.dto.ContactForm;
import com.alluz.blog.web.dto.UserAccountDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender emailSender;

    private final EmailProperties properties;

    public EmailService(JavaMailSender emailSender, EmailProperties properties) {
        this.emailSender = emailSender;
        this.properties = properties;
    }

    private void sendEmail(final EmailMessage message) {
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

    /**
     * Send message to blog author when a new comment was posted to the blog.
     *
     * @param commentUser
     * @param comment
     * @param blog
     */
    public void notifyAuthorForNewComment(UserAccountDto blogAuthor, UserAccountDto commentUser, CommentDto comment, BlogDto blog) {
        String subject = "You got comment on your blog post " + blog.getTitle();
        String message = commentUser.getDisplayName() + " posted a comment on your blog: " + comment.getContent();
        sendEmail(new EmailMessage(properties, blogAuthor.getEmail(), blogAuthor.getDisplayName(), subject, message, null));
    }

    /**
     * Send contact messages by Web contact form in Contact page to administrator.
     *
     * @param contact
     */
    public void notifyAdminForContactMessage(ContactForm contact) {
        String subject = "Contact message from " + contact.getName();
        String message = contact.getName() + " tried to contact you on your website: " + contact.getMessage();
        sendEmail(new EmailMessage(properties, subject, message, contact.getEmail()));
    }

    /**
     * Send message to admin when a new user registered by auto signup.
     * @param user
     */
    public void sendNewUserRegistered(UserAccountDto user) {
        String userEmail = user.getEmail();
        if (userEmail == null){
            userEmail = "[not set]";
        }
        String subject = "New user registered";
        String message = "A new user registered: name is " + user.getDisplayName() + ", email is " + userEmail;
        sendEmail(new EmailMessage(properties, subject, message, null));
    }

    /**
     * Send message to admin when a new blog post was published.
     * @param author
     * @param blog
     */
    public void sendNewPostPublished(UserAccountDto author, BlogDto blog) {
        String subject = "New post published";
        String message = "A new post was published: title is " + blog.getTitle() + ", author is " + author.getDisplayName();
        sendEmail(new EmailMessage(properties, subject, message, null));
    }
}
