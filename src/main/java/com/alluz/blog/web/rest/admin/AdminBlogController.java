package com.alluz.blog.web.rest.admin;

import com.alluz.blog.domain.post.BlogService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AdminBlogController {

    private final BlogService blogService;

    public AdminBlogController(BlogService blogService) {
        this.blogService = blogService;
    }
}
