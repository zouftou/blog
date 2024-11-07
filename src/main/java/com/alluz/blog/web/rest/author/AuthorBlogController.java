package com.alluz.blog.web.rest.author;

import com.alluz.blog.domain.post.BlogService;
import com.alluz.blog.web.rest.ApiUrls;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiUrls.API_ROOT + ApiUrls.API_VERSION_1)
public class AuthorBlogController {

    private final BlogService blogService;

    public AuthorBlogController(BlogService blogService) {
        this.blogService = blogService;
    }
}
