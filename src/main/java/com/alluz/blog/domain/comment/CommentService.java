package com.alluz.blog.domain.comment;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final ModelMapper modelMapper;

    public CommentService(CommentRepository commentRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }
}
