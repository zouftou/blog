package com.alluz.blog.web.rest.site;

import com.alluz.blog.domain.post.BlogService;
import com.alluz.blog.web.dto.BlogDto;
import com.alluz.blog.web.rest.ApiUrls;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiUrls.API_ROOT + ApiUrls.API_VERSION_1)
public class SiteBlogController {

    private final BlogService blogService;

    public SiteBlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @RequestMapping(ApiUrls.URL_SITE_BLOGS)
    public ResponseEntity<Page<BlogDto>> getRecentComments(@PageableDefault(size = 10, page = 0) Pageable pageable){
        Page<BlogDto> blogs = blogService.getBlogs(pageable);
        return ResponseEntity.ok(blogs);
    }

    @RequestMapping(ApiUrls.URL_SITE_BLOGS_BLOG)
    public ResponseEntity<BlogDto> getBlog(@PathVariable("blogId") Long blogId) {
        BlogDto blog = blogService.getBlogById(blogId);
        return ResponseEntity.ok(blog);
    }
}
