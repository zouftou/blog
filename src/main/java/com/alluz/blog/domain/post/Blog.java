package com.alluz.blog.domain.post;

import com.alluz.blog.domain.AuditableEntity;
import com.alluz.blog.domain.account.UserAccount;
import com.alluz.blog.domain.comment.Comment;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "blogs")
public class Blog extends AuditableEntity {

    private String title;

    private String content;

    private boolean published;

    @Column(name = "published_time")
    private Date publishedTime;

    private String tags;

    @OneToMany(mappedBy="blog")
    private Set<Comment> comments;

    @ManyToOne
    private UserAccount author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public Date getPublishedTime() {
        return publishedTime;
    }

    public void setPublishedTime(Date publishedTime) {
        this.publishedTime = publishedTime;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public UserAccount getAuthor() {
        return author;
    }

    public void setAuthor(UserAccount author) {
        this.author = author;
    }
}
