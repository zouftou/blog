package com.alluz.blog.web.rest.site;

import com.alluz.blog.domain.post.BlogService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SiteBlogController {

    private final BlogService blogService;

    public SiteBlogController(BlogService blogService) {
        this.blogService = blogService;
    }
}
