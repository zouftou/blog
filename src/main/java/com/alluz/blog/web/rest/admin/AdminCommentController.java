package com.alluz.blog.web.rest.admin;

import com.alluz.blog.domain.comment.CommentService;
import com.alluz.blog.domain.post.Blog;
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
public class AdminCommentController {

    private final CommentService commentService;

    public AdminCommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(ApiUrls.URL_ADMIN_BLOGS_BLOG_COMMENTS)
    public ResponseEntity<Page<CommentDto>> getBlogComments(@PathVariable("blogId") Long blogId, @PageableDefault(size = 10, page = 0) Pageable pageable){
        Page<CommentDto> comments = commentService.getComments(blogId, pageable);
        return ResponseEntity.ok(comments);
    }

    @RequestMapping(ApiUrls.URL_ADMIN_COMMENTS)
    public ResponseEntity<Page<CommentDto>> getComments(@PageableDefault(size = 10, page = 0) Pageable pageable){
        Page<CommentDto> comments = commentService.getComments(pageable);
        return ResponseEntity.ok(comments);
    }

    @RequestMapping(ApiUrls.URL_ADMIN_COMMENTS_COMMENT)
    public ResponseEntity<CommentDto> getComment(@PathVariable("commentId") Long commentId) {
        CommentDto comment = commentService.getComment(commentId);
        return ResponseEntity.ok(comment);
    }
}
