package com.alluz.blog.web.rest.author;

import com.alluz.blog.domain.comment.CommentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AuthorCommentController {

    private final CommentService commentService;

    public AuthorCommentController(CommentService commentService) {
        this.commentService = commentService;
    }
}
