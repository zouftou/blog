package com.alluz.blog.domain.comment;

import com.alluz.blog.web.dto.BlogDto;
import com.alluz.blog.web.dto.CommentDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public CommentDto getComment(Long commentId) {
        return null;
    }

    public Page<CommentDto> getComments(Pageable pageable) {
        return null;
    }

    public Page<CommentDto> getUserComments(String userId, CommentStatus commentStatus, Pageable pageable) {
        return null;
    }
}
