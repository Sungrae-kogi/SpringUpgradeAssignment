package com.sparta.springupgradeschedule.service;

import com.sparta.springupgradeschedule.dto.comment.CommentRequestDTO;
import com.sparta.springupgradeschedule.dto.comment.CommentResponseDTO;
import com.sparta.springupgradeschedule.entity.Comment;
import com.sparta.springupgradeschedule.entity.Schedule;
import com.sparta.springupgradeschedule.repository.CommentRepository;
import com.sparta.springupgradeschedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    public CommentService(CommentRepository commentRepository, ScheduleRepository scheduleRepository) {
        this.commentRepository = commentRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public CommentResponseDTO createComment(Long id, CommentRequestDTO commentRequestDTO) {
        //id 값으로 Schedule 검색
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 id값을 가진 스케쥴 데이터가 존재하지 않습니다."));

        // DTO -> Entity    comment에 schedule 정보가 들어가야함. (comment가 어떤 schedule에 종속되어있는지)
        Comment comment = new Comment(commentRequestDTO, schedule);

        // comment 저장, Schedule에도 comment 저장.
        Comment savedComment = commentRepository.save(comment);
        schedule.addComment(savedComment);

        // scheduleRepository 불러서 schedule 을 저장해야하는게 맞는건가??

        return new CommentResponseDTO(savedComment);
    }
}
