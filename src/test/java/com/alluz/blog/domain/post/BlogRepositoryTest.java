package com.alluz.blog.domain.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BlogRepositoryTest {

    @Autowired
    private BlogRepository blogRepository;

    @BeforeEach
    void setUp() {
        // Add some initial test data
        Blog blog1 = new Blog();
        blog1.setTitle("First Blog");
        blog1.setContent("Content of the first blog");
        blog1.setPublished(true);
        blog1.setPublishedTime(new Date());

        Blog blog2 = new Blog();
        blog2.setTitle("Second Blog");
        blog2.setContent("Content of the second blog");
        blog2.setPublished(true);
        blog2.setPublishedTime(new Date());

        Blog blog3 = new Blog();
        blog3.setTitle("Third Blog");
        blog3.setContent("Content of the third blog");
        blog3.setPublished(false);

        blogRepository.save(blog1);
        blogRepository.save(blog2);
        blogRepository.save(blog3);
    }

    @Test
    void testFindByPublishedIsTrueOrderByPublishedTimeDesc() {
        PageRequest pageable = PageRequest.of(0, 10);
        Page<Blog> result = blogRepository.findByPublishedIsTrueOrderByPublishedTimeDesc(pageable);

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals("Second Blog", result.getContent().get(0).getTitle()); // Most recent blog
    }

    @Test
    void testFindFirstByPublishedIsTrueOrderByPublishedTimeDesc() {
        Blog result = blogRepository.findFirstByPublishedIsTrueOrderByPublishedTimeDesc();

        assertNotNull(result);
        assertEquals("Second Blog", result.getTitle()); // Most recent blog
    }

    @Test
    void testCountByPublishedIsTrue() {
        int count = blogRepository.countByPublishedIsTrue();

        assertEquals(2, count);
    }
}