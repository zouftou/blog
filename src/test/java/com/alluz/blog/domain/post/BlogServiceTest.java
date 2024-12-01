package com.alluz.blog.domain.post;


import com.alluz.blog.web.dto.BlogDto;
import com.alluz.blog.web.exp.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BlogServiceTest {

    @InjectMocks
    BlogService blogService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private BlogRepository blogRepository;

    @Test
    void testGetLatestBlog() {
        // Arrange
        Blog blog = new Blog(); // Assume a Blog entity exists
        blog.setId(1L);
        BlogDto blogDto = new BlogDto();
        when(blogRepository.findFirstByPublishedIsTrueOrderByPublishedTimeDesc()).thenReturn(blog);
        when(modelMapper.map(blog, BlogDto.class)).thenReturn(blogDto);

        // Act
        BlogDto result = blogService.getLatestBlog();

        // Assert
        assertNotNull(result);
        verify(blogRepository, times(1)).findFirstByPublishedIsTrueOrderByPublishedTimeDesc();
        verify(modelMapper, times(1)).map(blog, BlogDto.class);
    }

    @Test
    void testGetBlogs() {
        // Arrange
        Blog blog = new Blog();
        BlogDto blogDto = new BlogDto();
        List<Blog> blogList = List.of(blog);
        Page<Blog> blogPage = new PageImpl<>(blogList);
        Pageable pageable = PageRequest.of(0, 10);
        when(blogRepository.findByPublishedIsTrueOrderByPublishedTimeDesc(pageable)).thenReturn(blogPage);
        when(modelMapper.map(blog, BlogDto.class)).thenReturn(blogDto);

        // Act
        Page<BlogDto> result = blogService.getBlogs(pageable);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(blogRepository, times(1)).findByPublishedIsTrueOrderByPublishedTimeDesc(pageable);
        verify(modelMapper, times(1)).map(blog, BlogDto.class);
    }

    @Test
    void testGetBlogByIdWhenFound() {
        // Arrange
        Blog blog = new Blog();
        BlogDto blogDto = new BlogDto();
        when(blogRepository.findById(1L)).thenReturn(Optional.of(blog));
        when(modelMapper.map(blog, BlogDto.class)).thenReturn(blogDto);

        // Act
        BlogDto result = blogService.getBlogById(1L);

        // Assert
        assertNotNull(result);
        verify(blogRepository, times(1)).findById(1L);
        verify(modelMapper, times(1)).map(blog, BlogDto.class);
    }

    @Test
    void testGetBlogByIdWhenNotFound() {
        // Arrange
        when(blogRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> blogService.getBlogById(1L));
        verify(blogRepository, times(1)).findById(1L);
        verifyNoInteractions(modelMapper);
    }

    @Test
    void testCountBlogs() {
        // Arrange
        when(blogRepository.countByPublishedIsTrue()).thenReturn(5);

        // Act
        int count = blogService.countBlogs();

        // Assert
        assertEquals(5, count);
        verify(blogRepository, times(1)).countByPublishedIsTrue();
    }
}