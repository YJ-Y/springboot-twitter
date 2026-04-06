package com.yyj.springboot_twitter.repository.comment;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public record CommentResponse (
        Long id,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
){

    public static CommentResponse from(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }
}
