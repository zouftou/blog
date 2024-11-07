package com.alluz.blog.domain.post;

import com.alluz.blog.domain.comment.CommentStatus;
import com.alluz.blog.web.dto.BlogDto;
import com.alluz.blog.web.dto.CommentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Page<CommentDto> getUserComments(String userId, CommentStatus commentStatus, Pageable pageable) {
        return null;
    }

    public BlogDto getLatestBlog() {
        return null;
    }

    public Page<BlogDto> getRecentBlogs(Pageable pageable) {
        return null;
    }

    public Page<BlogDto> getBlogs(Pageable pageable) {
        return null;
    }
}
