package com.sparta.springupgradeschedule.service;

import com.sparta.springupgradeschedule.dto.comment.request.CommentRequestDTO;
import com.sparta.springupgradeschedule.dto.comment.response.CommentResponseDTO;
import com.sparta.springupgradeschedule.entity.Comment;
import com.sparta.springupgradeschedule.entity.Schedule;
import com.sparta.springupgradeschedule.exception.CommentNotFoundByIdException;
import com.sparta.springupgradeschedule.exception.ScheduleNotFoundByIdException;
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
        Schedule schedule = findById(scheduleId);

        // DTO -> Entity, comment에 연관된 schedule 설정
        Comment comment = new Comment(commentRequestDTO);
        comment.setSchedule(schedule);

        // comment 저장, Schedule에도 comment 저장    -> 연관관계가 설정되어있으므로 Schedule의 CommentList도 자동으로 반영.
        Comment savedComment = commentRepository.save(comment);

        return new CommentResponseDTO(savedComment);
    }

    public CommentResponseDTO getComment(Long scheduleId, Long commentId) {
        // Schedule_Id, Comment_ID 로 Comment객체 검색.
        Comment comment = findByScheduleIdAndCommentId(scheduleId, commentId);

        // 검색 성공 -> DTO 반환
        return new CommentResponseDTO(comment);
    }


    public List<CommentResponseDTO> getAllComments(Long scheduleId) {
        // scheduleId 값으로 Schedule 검색
        Schedule schedule = findById(scheduleId);

        // 검색한 Schedule의 Comments 리스트
        List<Comment> commentsInSchedule = schedule.getComments();

        //Comments 리스트 -> DTO 변환
        List<CommentResponseDTO> commentResponseDTOs = commentsInSchedule.stream()
                .map(comment -> new CommentResponseDTO(comment)).collect(Collectors.toList());

        return commentResponseDTOs;
    }

    public CommentResponseDTO updateComment(Long scheduleId, Long commentId, CommentRequestDTO commentRequestDTO) {
        // Schedule_Id, Comment_ID 로 Comment객체 검색.
        Comment comment = findByScheduleIdAndCommentId(scheduleId, commentId);

        // 변경 내용 반영     -> 검색해온 comment와 비교해서 DirtyChecking이 발생하여 자동으로 save()시에 update문이 발생.
        comment.setUsername(commentRequestDTO.getUsername());
        comment.setComment(commentRequestDTO.getComment());

        // comment 내용 반영 및 반환
        return new CommentResponseDTO(commentRepository.save(comment));
    }

    public void deleteComment(Long scheduleId, Long commentId) {
        // Schedule_Id, Comment_ID 로 Comment객체 검색.
        Comment comment = findByScheduleIdAndCommentId(scheduleId, commentId);
        // comment 삭제 리턴타입 void
        commentRepository.delete(comment);
    }

    // Schedule_id 값 -> Schedule 객체 반환.
    public Schedule findById(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).orElseThrow(() -> new ScheduleNotFoundByIdException("해당 id값을 가진 스케쥴 데이터가 존재하지 않습니다."));
    }

    // Schedule_id 값, Comment_id 값 -> Comment 객체 반환.
    public Comment findByScheduleIdAndCommentId(Long scheduleId, Long commentId) {
        // scheduleId 값으로 Schedule 검색
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new ScheduleNotFoundByIdException("해당 id값을 가진 스케쥴 데이터가 존재하지 않습니다."));

        // 검색한 Schedule의 comments에 commentId값으로 Comment 검색
        List<Comment> commentsInSchedule = schedule.getComments();
        return commentsInSchedule.stream().filter(c -> c.getComment_id().equals(commentId))
                .findFirst().orElseThrow(() -> new CommentNotFoundByIdException("해당 id값을 가진 댓글 데이터가 존재하지 않습니다."));
    }
}
