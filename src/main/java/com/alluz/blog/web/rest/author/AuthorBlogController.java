package com.alluz.blog.web.rest.author;

import com.alluz.blog.domain.post.BlogService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AuthorBlogController {

    private final BlogService blogService;

    public AuthorBlogController(BlogService blogService) {
        this.blogService = blogService;
    }
}
