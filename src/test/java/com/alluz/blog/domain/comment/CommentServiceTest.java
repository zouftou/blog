package com.alluz.blog.domain.comment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @InjectMocks
    CommentService commentService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private CommentRepository commentRepository;

    @Test
    void postComment() {
    }

    @Test
    void getBlogCommentsOrderByCreatedTimeDesc() {
    }

    @Test
    void getComments() {
    }

    @Test
    void getCommentById() {
    }

    @Test
    void deleteComment() {
    }

    @Test
    void getBlogComments() {
    }

    @Test
    void getApprovedBlogComments() {
    }

    @Test
    void getCommentsByStatusOrderByCreatedTimeDesc() {
    }

    @Test
    void getCommentsByUserIdAndStatusOrderByCreatedTimeDesc() {
    }
}