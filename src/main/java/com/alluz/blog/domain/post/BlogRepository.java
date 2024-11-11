package com.alluz.blog.domain.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    Page<Blog> findByPublishedIsTrueOrderByPublishedTimeDesc(Pageable pageable);

    Blog findFirstByPublishedIsTrueOrderByPublishedTimeDesc();

    int countByPublishedIsTrue();
}
