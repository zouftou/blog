package com.alluz.blog.domain.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Page<Comment> findByStatusOrderByCreatedTimeDesc(CommentStatus status, Pageable pageable);

    Page<Comment> findByBlogIdOrderByCreatedTimeDesc(Long blogId, Pageable pageable);

    Page<Comment> findByUserIdAndStatusOrderByCreatedTimeDesc(Long userId, CommentStatus status, Pageable pageable);

    int countByBlogIdAndStatus(Long blogId, CommentStatus status);

    int countByBlogId(Long blogId);
}
