package com.alluz.blog.domain.comment;

import com.alluz.blog.domain.AuditableEntity;
import com.alluz.blog.domain.account.UserAccount;
import com.alluz.blog.domain.post.Blog;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "comments")
public class Comment extends AuditableEntity {

    private String content;

    private CommentStatus status;

    @ManyToOne
    private Blog blog;

    @ManyToOne
    private UserAccount user;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CommentStatus getStatus() {
        return status;
    }

    public void setStatus(CommentStatus status) {
        this.status = status;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public UserAccount getUser() {
        return user;
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }

    public Comment approve() {
        assert status == CommentStatus.PENDING;
        this.status = CommentStatus.APPROVED;
        return this;
    }

    public Comment disapprove() {
        assert status == CommentStatus.APPROVED;
        this.status = CommentStatus.PENDING;
        return this;
    }

    public Comment markSpam() {
        assert status == CommentStatus.PENDING;
        this.status = CommentStatus.SPAM;
        return this;
    }

    public Comment unmarkSpam() {
        assert status == CommentStatus.SPAM;
        this.status = CommentStatus.PENDING;
        return this;
    }
}
