package com.sparta.springupgradeschedule.service;

import com.sparta.springupgradeschedule.dto.comment.CommentRequestDTO;
import com.sparta.springupgradeschedule.dto.comment.CommentResponseDTO;
import com.sparta.springupgradeschedule.entity.Comment;
import com.sparta.springupgradeschedule.entity.Schedule;
import com.sparta.springupgradeschedule.repository.CommentRepository;
import com.sparta.springupgradeschedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    public CommentService(CommentRepository commentRepository, ScheduleRepository scheduleRepository) {
        this.commentRepository = commentRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public CommentResponseDTO createComment(Long scheduleId, CommentRequestDTO commentRequestDTO) {
        // scheduleId 값으로 Schedule 검색
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new RuntimeException("해당 id값을 가진 스케쥴 데이터가 존재하지 않습니다."));

        // DTO -> Entity
        Comment comment = new Comment(commentRequestDTO);

        // Schedule의 List<Comment>에 comment 저장 -> comment에 schedule 정보가 들어가야함. (comment가 어떤 schedule에 종속되어있는지)
        schedule.addComment(comment);

        // comment 저장, Schedule에도 comment 저장    -> 연관관계가 설정되어있으므로 Schedule의 CommentList도 자동으로 반영.
        Comment savedComment = commentRepository.save(comment);


        return new CommentResponseDTO(savedComment);
    }

    public CommentResponseDTO getComment(Long scheduleId, Long commentId) {
        // scheduleId 값으로 Schedule 검색
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new RuntimeException("해당 id값을 가진 스케쥴 데이터가 존재하지 않습니다."));

        // 검색한 Schedule의 comments에 commentId값으로 Comment 검색
        List<Comment> commentsInSchedule = schedule.getComments();
        Comment comment = commentsInSchedule.stream().filter(c -> c.getComment_id().equals(commentId))
                .findFirst().orElseThrow(() -> new RuntimeException("해당 id값을 가진 댓글 데이터가 존재하지 않습니다."));


        // 검색 성공 -> DTO 반환
        return new CommentResponseDTO(comment);
    }


    public List<CommentResponseDTO> getAllComments(Long scheduleId) {
        // scheduleId 값으로 Schedule 검색
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new RuntimeException("해당 id값을 가진 스케쥴 데이터가 존재하지 않습니다."));

        // 검색한 Schedule의 Comments 리스트
        List<Comment> commentsInSchedule = schedule.getComments();

        //Comments 리스트 -> DTO 변환
        List<CommentResponseDTO> commentResponseDTOs = commentsInSchedule.stream()
                .map(comment -> new CommentResponseDTO(comment)).collect(Collectors.toList());

        return commentResponseDTOs;
    }

    public CommentResponseDTO updateComment(Long scheduleId, Long commentId, CommentRequestDTO commentRequestDTO) {
        // scheduleId 값으로 Schedule 검색
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new RuntimeException("해당 id값을 가진 스케쥴 데이터가 존재하지 않습니다."));

        // 검색한 Schedule의 comments에 commentId값으로 Comment 검색
        List<Comment> commentsInSchedule = schedule.getComments();
        Comment comment = commentsInSchedule.stream().filter(c -> c.getComment_id().equals(commentId))
                .findFirst().orElseThrow(() -> new RuntimeException("해당 id값을 가진 댓글 데이터가 존재하지 않습니다."));

        // 변경 내용 반영     -> 검색해온 comment와 비교해서 DirtyChecking이 발생하여 자동으로 save()시에 update문이 발생.
        comment.setUsername(commentRequestDTO.getUsername());
        comment.setComment(commentRequestDTO.getComment());

        // comment 내용 반영 및 반환
        return new CommentResponseDTO(commentRepository.save(comment));
    }

    public void deleteComment(Long scheduleId, Long commentId) {
        // scheduleId 값으로 Schedule 검색
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new RuntimeException("해당 id값을 가진 스케쥴 데이터가 존재하지 않습니다."));

        // 검색한 Schedule의 comments에 commentId값으로 Comment 검색
        List<Comment> commentsInSchedule = schedule.getComments();
        Comment comment = commentsInSchedule.stream().filter(c -> c.getComment_id().equals(commentId))
                .findFirst().orElseThrow(() -> new RuntimeException("해당 id값을 가진 댓글 데이터가 존재하지 않습니다."));

        // comment 삭제 리턴타입 void
        commentRepository.delete(comment);
    }
}
