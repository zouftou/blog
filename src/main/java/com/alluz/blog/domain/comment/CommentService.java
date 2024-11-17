package com.alluz.blog.domain.comment;

import com.alluz.blog.domain.post.Blog;
import com.alluz.blog.domain.post.BlogRepository;
import com.alluz.blog.web.dto.CommentDto;
import com.alluz.blog.web.exp.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final BlogRepository blogRepository;

    private final ModelMapper modelMapper;

    public CommentService(CommentRepository commentRepository, BlogRepository blogRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.blogRepository = blogRepository;
        this.modelMapper = modelMapper;
    }

    public Page<CommentDto> getRecentComments(CommentStatus approved, Pageable pageable) {
        Page<Comment> comments = commentRepository.findByStatusOrderByCreatedTimeDesc(approved,pageable);
        List<CommentDto> commentsList = comments.stream().map(comment -> modelMapper.map(comment, CommentDto.class)).toList();
        return new PageImpl<>(commentsList);
    }

    public Page<CommentDto> getComments(Long blogId, Pageable pageable) {
        Optional<Blog> blog = blogRepository.findById(blogId);
        if (blog.isPresent()){
            Page<Comment> comments = commentRepository.findByBlogIdOrderByCreatedTimeDesc(blogId,pageable);
            List<CommentDto> commentsList = comments.stream().map(comment -> modelMapper.map(comment, CommentDto.class)).toList();
            return new PageImpl<>(commentsList);
        }else{
            throw new ResourceNotFoundException("Blog","blogId", blogId);
        }
    }

    public CommentDto getComment(Long blogId, Long commentId) {
        Optional<Blog> blog = blogRepository.findById(blogId);
        if (blog.isPresent()){
            Optional<Comment> comment = commentRepository.findById(commentId);
            if (comment.isPresent()){
                return modelMapper.map(comment.get(), CommentDto.class);
            }else{
                throw new ResourceNotFoundException("Comment","commentId",commentId);
            }
        }else{
            throw new ResourceNotFoundException("Blog","blogId", blogId);
        }
    }

    public CommentDto getCommentById(Long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()){
            return modelMapper.map(comment.get(), CommentDto.class);
        }else{
            throw new ResourceNotFoundException("Comment","commentId",commentId);
        }
    }

    public Page<CommentDto> getComments(Pageable pageable) {
        Page<Comment> comments = commentRepository.findAll(pageable);
        List<CommentDto> commentsList = comments.stream().map(comment -> modelMapper.map(comment, CommentDto.class)).toList();
        return new PageImpl<>(commentsList);
    }

    public Page<CommentDto> getUserComments(String userId, CommentStatus commentStatus, Pageable pageable) {
        return null;
    }
}
