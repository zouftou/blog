package com.alluz.blog.domain.post;

import com.alluz.blog.web.dto.BlogDto;
import com.alluz.blog.web.exp.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    private final ModelMapper modelMapper;

    public BlogService(BlogRepository blogRepository, ModelMapper modelMapper) {
        this.blogRepository = blogRepository;
        this.modelMapper = modelMapper;
    }

    public BlogDto getLatestBlog() {
        Blog blog = blogRepository.findFirstByPublishedIsTrueOrderByPublishedTimeDesc();
        return modelMapper.map(blog, BlogDto.class);
    }

    public Page<BlogDto> getBlogs(Pageable pageable) {
        Page<Blog> blogs = blogRepository.findByPublishedIsTrueOrderByPublishedTimeDesc(pageable);
        List<BlogDto> blogsList = blogs.stream().map(blog -> modelMapper.map(blog, BlogDto.class)).toList();
        return new PageImpl<>(blogsList);
    }

    public BlogDto getBlogById(Long blogId) {
        Optional<Blog> blog = blogRepository.findById(blogId);
        if (blog.isPresent()){
            return modelMapper.map(blog.get(), BlogDto.class);
        }else{
            throw new ResourceNotFoundException("Blog","blogId", blogId);
        }
    }
}
