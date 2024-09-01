package com.sparta.springupgradeschedule.controller;

import com.sparta.springupgradeschedule.dto.comment.request.CommentRequestDTO;
import com.sparta.springupgradeschedule.dto.comment.response.CommentResponseDTO;
import com.sparta.springupgradeschedule.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules/{scheduleId}/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 저장
    @PostMapping
    public CommentResponseDTO createComment(@PathVariable Long scheduleId, @RequestBody CommentRequestDTO commentRequestDTO) {
        // 연관된 Schedule id값, Comment 내용 commentRequestDTO
        return commentService.createComment(scheduleId, commentRequestDTO);
    }

    // 댓글 단건 조회
    @GetMapping("/{commentId}")
    public CommentResponseDTO getComment(@PathVariable Long scheduleId, @PathVariable Long commentId) {
        // 연관된 Schedule id, Comment id
        return commentService.getComment(scheduleId, commentId);
    }

    // 댓글 전체 조회
    @GetMapping
    public List<CommentResponseDTO> getAllComments(@PathVariable Long scheduleId) {
        return commentService.getAllComments(scheduleId);
    }

    // 댓글 수정
    @PutMapping("/{commentId}")
    public CommentResponseDTO updateComment(@PathVariable Long scheduleId, @PathVariable Long commentId, @RequestBody CommentRequestDTO commentRequestDTO) {
        // 연관된 Schedule id값, Comment 내용 commentRequestDTO
        return commentService.updateComment(scheduleId, commentId, commentRequestDTO);
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long scheduleId, @PathVariable Long commentId) {
        //연관된 Schedule id값, 삭제할 Comment id값.
        commentService.deleteComment(scheduleId, commentId);
    }
}
