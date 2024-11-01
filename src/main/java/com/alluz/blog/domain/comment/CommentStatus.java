package com.alluz.blog.domain.comment;

/**
 * Indicates the status of user posted comments:
 * post --> PENDING <--> APPROVED <--> SPAM --> Deleted
 */
public enum CommentStatus {
    PENDING,
    APPROVED,
    SPAM
}