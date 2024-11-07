package com.alluz.blog.web.rest.site;

import com.alluz.blog.domain.post.BlogService;
import com.alluz.blog.web.rest.ApiUrls;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiUrls.API_ROOT + ApiUrls.API_VERSION_1)
public class SiteBlogController {

    private final BlogService blogService;

    public SiteBlogController(BlogService blogService) {
        this.blogService = blogService;
    }
}
