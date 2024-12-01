package com.alluz.blog.domain.comment;

import com.alluz.blog.domain.post.Blog;
import com.alluz.blog.domain.post.BlogRepository;
import com.alluz.blog.web.dto.CommentDto;
import com.alluz.blog.web.exp.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private BlogRepository blogRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CommentService commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRecentComments() {
        // Arrange
        Comment comment = new Comment();
        CommentDto commentDto = new CommentDto();
        Page<Comment> commentPage = new PageImpl<>(List.of(comment));
        PageRequest pageable = PageRequest.of(0, 10);
        when(commentRepository.findByStatusOrderByCreatedTimeDesc(CommentStatus.APPROVED, pageable)).thenReturn(commentPage);
        when(modelMapper.map(comment, CommentDto.class)).thenReturn(commentDto);

        // Act
        Page<CommentDto> result = commentService.getRecentComments(CommentStatus.APPROVED, pageable);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(commentRepository, times(1)).findByStatusOrderByCreatedTimeDesc(CommentStatus.APPROVED, pageable);
        verify(modelMapper, times(1)).map(comment, CommentDto.class);
    }

    @Test
    void testGetComments() {
        // Arrange
        Long blogId = 1L;
        Blog blog = new Blog();
        Comment comment = new Comment();
        CommentDto commentDto = new CommentDto();
        Page<Comment> commentPage = new PageImpl<>(List.of(comment));
        PageRequest pageable = PageRequest.of(0, 10);
        when(blogRepository.findById(blogId)).thenReturn(Optional.of(blog));
        when(commentRepository.findByBlogIdOrderByCreatedTimeDesc(blogId, pageable)).thenReturn(commentPage);
        when(modelMapper.map(comment, CommentDto.class)).thenReturn(commentDto);

        // Act
        Page<CommentDto> result = commentService.getComments(blogId, pageable);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(blogRepository, times(1)).findById(blogId);
        verify(commentRepository, times(1)).findByBlogIdOrderByCreatedTimeDesc(blogId, pageable);
        verify(modelMapper, times(1)).map(comment, CommentDto.class);
    }

    @Test
    void testGetCommentsThrowsWhenBlogNotFound() {
        // Arrange
        Long blogId = 1L;
        PageRequest pageable = PageRequest.of(0, 10);
        when(blogRepository.findById(blogId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> commentService.getComments(blogId, pageable));
        verify(blogRepository, times(1)).findById(blogId);
        verifyNoInteractions(commentRepository, modelMapper);
    }

    @Test
    void testGetComment() {
        // Arrange
        Long blogId = 1L;
        Long commentId = 2L;
        Blog blog = new Blog();
        Comment comment = new Comment();
        CommentDto commentDto = new CommentDto();
        when(blogRepository.findById(blogId)).thenReturn(Optional.of(blog));
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));
        when(modelMapper.map(comment, CommentDto.class)).thenReturn(commentDto);

        // Act
        CommentDto result = commentService.getComment(blogId, commentId);

        // Assert
        assertNotNull(result);
        verify(blogRepository, times(1)).findById(blogId);
        verify(commentRepository, times(1)).findById(commentId);
        verify(modelMapper, times(1)).map(comment, CommentDto.class);
    }

    @Test
    void testGetCommentThrowsWhenBlogNotFound() {
        // Arrange
        Long blogId = 1L;
        Long commentId = 2L;
        when(blogRepository.findById(blogId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> commentService.getComment(blogId, commentId));
        verify(blogRepository, times(1)).findById(blogId);
        verifyNoInteractions(commentRepository, modelMapper);
    }

    @Test
    void testGetCommentThrowsWhenCommentNotFound() {
        // Arrange
        Long blogId = 1L;
        Long commentId = 2L;
        Blog blog = new Blog();
        when(blogRepository.findById(blogId)).thenReturn(Optional.of(blog));
        when(commentRepository.findById(commentId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> commentService.getComment(blogId, commentId));
        verify(blogRepository, times(1)).findById(blogId);
        verify(commentRepository, times(1)).findById(commentId);
    }

    @Test
    void testCountCommentsByBlogAndStatus() {
        // Arrange
        Long blogId = 1L;
        when(commentRepository.countByBlogIdAndStatus(blogId, CommentStatus.APPROVED)).thenReturn(5);

        // Act
        int result = commentService.countComments(blogId, CommentStatus.APPROVED);

        // Assert
        assertEquals(5, result);
        verify(commentRepository, times(1)).countByBlogIdAndStatus(blogId, CommentStatus.APPROVED);
    }

    @Test
    void testCountCommentsByBlog() {
        // Arrange
        Long blogId = 1L;
        when(commentRepository.countByBlogId(blogId)).thenReturn(10);

        // Act
        int result = commentService.countComments(blogId);

        // Assert
        assertEquals(10, result);
        verify(commentRepository, times(1)).countByBlogId(blogId);
    }
}