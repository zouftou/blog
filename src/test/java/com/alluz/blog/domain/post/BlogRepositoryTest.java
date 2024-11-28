package com.alluz.blog.domain.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BlogRepositoryTest {

    @Autowired
    BlogRepository blogRepository;

    @Test
    void testFindByPublishedIsTrueOrderByPublishedTimeDesc() {
        Page<Blog> blogPage = blogRepository.findByPublishedIsTrueOrderByPublishedTimeDesc(PageRequest.of(0,5));
        assertNotNull(blogPage.getContent());
        assertEquals(2,blogPage.getContent().size());
        for (int i = 1; i < blogPage.getContent().size(); i++) {
            Blog current = blogPage.getContent().get(i);
            Blog previous = blogPage.getContent().get(i - 1);
            assertTrue(current.getPublishedTime().compareTo(previous.getPublishedTime()) <= 0);
        }
    }

    @Test
    void testFindByAuthorIdOrderByCreatedTimeDesc() {
        Page<Blog> blogPage = blogRepository.findByAuthorIdOrderByCreatedTimeDesc(2L,PageRequest.of(0,5));
        assertNotNull(blogPage.getContent());
        assertEquals(2,blogPage.getContent().size());
        for (int i = 1; i < blogPage.getContent().size(); i++) {
            Blog current = blogPage.getContent().get(i);
            Blog previous = blogPage.getContent().get(i - 1);
            assertTrue(current.getCreatedTime().compareTo(previous.getCreatedTime()) <= 0);
        }
    }

    @Test
    void testFindAllByPublishedIsTrue() {
        Page<Blog> blogPage = blogRepository.findAllByPublishedIsTrue(PageRequest.of(0,5));
        assertNotNull(blogPage.getContent());
        assertEquals(2,blogPage.getContent().size());
    }

    @Test
    void testCountByPublishedIsTrue() {
        int count = blogRepository.countByPublishedIsTrue();
        assertEquals(2,count);
    }
}