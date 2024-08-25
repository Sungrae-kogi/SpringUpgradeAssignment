package com.sparta.springupgradeschedule.dto.comment;

import com.sparta.springupgradeschedule.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDTO {
    private Long comment_id;
    private String comment;
    private String username;
    private LocalDateTime created_at;
    private LocalDateTime modified_at;

    public CommentResponseDTO(Comment comment) {
        this.comment_id = comment.getComment_id();
        this.comment = comment.getComment();
        this.username = comment.getUsername();
        this.created_at = comment.getCreatedAt();
        this.modified_at = comment.getModifiedAt();
    }
}
