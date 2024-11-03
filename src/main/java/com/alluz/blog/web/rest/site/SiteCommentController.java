package com.alluz.blog.web.rest.site;

import com.alluz.blog.domain.comment.CommentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SiteCommentController {

    private final CommentService commentService;

    public SiteCommentController(CommentService commentService) {
        this.commentService = commentService;
    }
}
