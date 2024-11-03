package com.alluz.blog.domain.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Page<Comment> findByBlogId(Long blogId, Pageable pageable);

    Page<Comment> findByStatusOrderByCreatedTimeDesc(CommentStatus status, Pageable pageable);

    int countByBlogIdAndStatus(Long blogId, CommentStatus status);

    int countByBlogId(Long blogId);

    Page<Comment> findByBlogIdOrderByCreatedTimeDesc(Long blogId, Pageable pageable);

    Page<Comment> findByBlogIdAndStatusOrderByCreatedTimeAsc(Long blogId, CommentStatus status, Pageable pageable);
}
