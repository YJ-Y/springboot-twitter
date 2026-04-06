package com.yyj.springboot_twitter.repository.post;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Slice<Post> findAllBy(Pageable pageable);

    Slice<Post> findByIdLessThan(Long lastPostId, Pageable pageable);

}