package com.sparta.springupgradeschedule.controller;

import com.sparta.springupgradeschedule.dto.comment.CommentRequestDTO;
import com.sparta.springupgradeschedule.dto.comment.CommentResponseDTO;
import com.sparta.springupgradeschedule.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedules/{id}/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 저장
    @PostMapping
    public CommentResponseDTO createComment(@PathVariable Long id, @RequestBody CommentRequestDTO commentRequestDTO) {
        //연관된 Schedule id값, Comment 내용 commentRequestDTO
        return commentService.createComment(id, commentRequestDTO);
    }

    // 댓글 단건 조회

    // 댓글 전체 조회

    // 댓글 수정

    // 댓글 삭제
}
