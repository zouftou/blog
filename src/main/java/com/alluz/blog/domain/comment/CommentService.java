package com.alluz.blog.domain.comment;

import com.alluz.blog.web.dto.BlogDto;
import com.alluz.blog.web.dto.CommentDto;
import com.alluz.blog.web.exc.ResourceNotFoundException;
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

    private final ModelMapper modelMapper;

    public CommentService(CommentRepository commentRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    public Page<CommentDto> getRecentComments(CommentStatus approved, Pageable pageable) {
        return null;
    }

    public Page<CommentDto> getComments(Long blogId, Pageable pageable) {
        return null;
    }

    public CommentDto getComment(Long blogId, Long commentId) {
        return null;
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
