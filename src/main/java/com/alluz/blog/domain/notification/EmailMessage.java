package com.alluz.blog.domain.notification;

public class EmailMessage {

    private  String fromEmail;

    private  String fromName;

    private  String toEmail;

    private  String toName;

    private  String subject;

    private  String body;

    private  String replyTo;

    public EmailMessage(){
    }

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

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }
}
