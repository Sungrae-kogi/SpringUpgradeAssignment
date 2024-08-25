package com.sparta.springupgradeschedule.repository;

import com.sparta.springupgradeschedule.entity.Comment;
import com.sparta.springupgradeschedule.entity.Schedule;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Test
    @Transactional
    @Rollback
    public void createComment() {
        //테스트용 id : 2 를 가진 Schedule이 존재하는 상태.
        Schedule schedule = scheduleRepository.findById(2L).get();

        Comment comment = new Comment();
        comment.setComment("댓글내용");
        comment.setUsername("작성자명");
        comment.setSchedule(schedule);

        //schedule에도 comment 저장.
        schedule.addComment(comment);

        //Comment 반환
        Comment result = commentRepository.save(comment);

        //Comment 출력
        System.out.println("Comment 내용 : " + result.getComment());
        System.out.println("Comment 작성자 : " + result.getUsername());

        //Schedule 반환
        System.out.println();
        System.out.println("Schedule id : " + schedule.getSchedule_id());
        System.out.println("Schedule 작성자 : " + schedule.getUsername());
        System.out.println("Schedule 내용 : " + schedule.getComments());
    }
}