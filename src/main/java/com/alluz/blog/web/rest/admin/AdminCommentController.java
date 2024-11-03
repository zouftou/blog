package com.alluz.blog.web.rest.admin;

import com.alluz.blog.domain.comment.CommentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AdminCommentController {

    private final CommentService commentService;

    public AdminCommentController(CommentService commentService) {
        this.commentService = commentService;
    }
}
