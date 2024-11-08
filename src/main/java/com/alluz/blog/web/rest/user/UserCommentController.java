package com.alluz.blog.web.rest.user;

import com.alluz.blog.domain.comment.CommentService;
import com.alluz.blog.domain.post.BlogService;
import com.alluz.blog.web.dto.BlogDto;
import com.alluz.blog.web.dto.CommentDto;
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
public class UserCommentController {

    private final CommentService commentService;

    private final BlogService blogService;

    public UserCommentController(CommentService commentService, BlogService blogService) {
        this.commentService = commentService;
        this.blogService = blogService;
    }

    @RequestMapping(ApiUrls.URL_USER_BLOGS_BLOG_COMMENTS)
    public ResponseEntity<Page<CommentDto>> getBlogComments(@PathVariable("blogId") Long blogId, @PageableDefault(size = 10, page = 0) Pageable pageable){
        BlogDto blog = blogService.getBlogById(blogId);
        Page<CommentDto> comments = commentService.getComments(blog, pageable);
        return ResponseEntity.ok(comments);
    }

    @RequestMapping(ApiUrls.URL_USER_COMMENTS)
    public ResponseEntity<Page<CommentDto>> getComments(@PageableDefault(size = 10, page = 0) Pageable pageable){
        Page<CommentDto> comments = commentService.getComments(pageable);
        return ResponseEntity.ok(comments);
    }

    @RequestMapping(ApiUrls.URL_USER_COMMENTS_COMMENT)
    public ResponseEntity<CommentDto> getComment(@PathVariable("commentId") Long commentId) {
        CommentDto comment = commentService.getComment(commentId);
        return ResponseEntity.ok(comment);
    }
}
