package com.alluz.blog.domain.comment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    void testFindByStatusOrderByCreatedTimeDesc() {
        Page<Comment> commentPage = commentRepository.findByStatusOrderByCreatedTimeDesc(CommentStatus.PENDING,PageRequest.of(0,5));
        assertNotNull(commentPage.getContent());
        assertEquals(3,commentPage.getContent().size());
        for (int i = 1; i < commentPage.getContent().size(); i++) {
            Comment current = commentPage.getContent().get(i);
            Comment previous = commentPage.getContent().get(i - 1);
            assertTrue(current.getCreatedTime().compareTo(previous.getCreatedTime()) <= 0);
        }
    }

    @Test
    void testFindByBlogIdOrderByCreatedTimeDesc() {
        Page<Comment> commentPage = commentRepository.findByBlogIdOrderByCreatedTimeDesc(3L,PageRequest.of(0,5));
        assertNotNull(commentPage.getContent());
        assertEquals(2,commentPage.getContent().size());
        for (int i = 1; i < commentPage.getContent().size(); i++) {
            Comment current = commentPage.getContent().get(i);
            Comment previous = commentPage.getContent().get(i - 1);
            assertTrue(current.getCreatedTime().compareTo(previous.getCreatedTime()) <= 0);
        }
    }

    @Test
    void testCountByBlogIdAndStatus() {
        int count = commentRepository.countByBlogIdAndStatus(2L,CommentStatus.APPROVED);
        assertEquals(1,count);
    }

    @Test
    void testCountByBlogId() {
        int count = commentRepository.countByBlogId(1L);
        assertEquals(1,count);
    }
}