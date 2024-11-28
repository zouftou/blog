package com.alluz.blog.domain.post;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class BlogServiceTest {

    @InjectMocks
    BlogService blogService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private BlogRepository blogRepository;

    @Test
    void getBlogs() {
    }

    @Test
    void getBlogById() {
    }

    @Test
    void createBlog() {
    }

    @Test
    void getPublishedBlogs() {
    }
}