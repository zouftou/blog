package com.alluz.blog.web.mail;

public class EmailMessage {

    private final String fromEmail;

    private final String fromName;

    private final String toEmail;

    private final String toName;

    private final String subject;

    private final String body;

    private final String replyTo;

    public EmailMessage(EmailProperties properties, String subject, String body, String replyTo) {
        this.fromEmail = properties.getSystemEmail();
        this.fromName = properties.getSystemName();
        this.toEmail = properties.getAdminEmail();
        this.toName = properties.getAdminName();
        this.subject = subject;
        this.body = body;
        this.replyTo = replyTo;
    }

    public EmailMessage(EmailProperties properties, String toEmail, String toName,
                        String subject, String message, String replyTo) {
        this.fromEmail = properties.getSystemEmail();
        this.fromName = properties.getSystemName();
        this.toEmail = toEmail;
        this.toName = toName;
        this.subject = subject;
        this.body = message;
        this.replyTo = replyTo;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public String getFromName() {
        return fromName;
    }

    public String getToEmail() {
        return toEmail;
    }

    public String getToName() {
        return toName;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public String getReplyTo() {
        return replyTo;
    }
}
