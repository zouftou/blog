package com.alluz.blog.web.rest.user;

import com.alluz.blog.domain.comment.CommentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserCommentController {

    private final CommentService commentService;

    public UserCommentController(CommentService commentService) {
        this.commentService = commentService;
    }
}
