package com.alluz.blog.web.rest;

import com.alluz.blog.web.dto.BlogDto;
import com.alluz.blog.web.dto.CommentDto;
import com.alluz.blog.web.dto.ContactDto;
import com.alluz.blog.web.dto.UserAccountDto;
import com.alluz.blog.web.mail.EmailMessage;
import com.alluz.blog.web.mail.EmailProperties;
import com.alluz.blog.web.mail.EmailService;
import org.springframework.stereotype.Service;

@Service
public class MessageSender {

    private final EmailProperties properties;

    private final EmailService emailService;

    public MessageSender(EmailProperties properties, EmailService emailService) {
        this.properties = properties;
        this.emailService = emailService;
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
        emailService.sendEmail(new EmailMessage(properties, blogAuthor.getEmail(), blogAuthor.getDisplayName(), subject, message, null));
    }

    /**
     * Send contact messages by Web contact form in Contact page to administrator.
     *
     * @param contact
     */
    public void notifyAdminForContactMessage(ContactDto contact) {
        String subject = "Contact message from " + contact.getName();
        String message = contact.getName() + " tried to contact you on your website: " + contact.getMessage();
        emailService.sendEmail(new EmailMessage(properties, subject, message, contact.getEmail()));
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
        emailService.sendEmail(new EmailMessage(properties, subject, message, null));
    }

    /**
     * Send message to admin when a new blog post was published.
     * @param author
     * @param blog
     */
    public void sendNewPostPublished(UserAccountDto author, BlogDto blog) {
        String subject = "New post published";
        String message = "A new post was published: title is " + blog.getTitle() + ", author is " + author.getDisplayName();
        emailService.sendEmail(new EmailMessage(properties, subject, message, null));
    }
}
