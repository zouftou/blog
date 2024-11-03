package com.alluz.blog.web.dto;

import com.alluz.blog.domain.comment.CommentStatus;

public class CommentDto {

    private Long id;

    private String content;

    private CommentStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
