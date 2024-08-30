package com.sparta.springupgradeschedule.repository;

import com.sparta.springupgradeschedule.dto.comment.CommentResponseDTO;
import com.sparta.springupgradeschedule.entity.Comment;
import com.sparta.springupgradeschedule.entity.Schedule;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void createComment() {
        //테스트용 id : 3 를 가진 Schedule이 존재하는 상태.
        Schedule schedule = scheduleRepository.findById(1L).get();
        System.out.println("Schedule Comment 확인 : " + schedule.getComments().size());

        Comment comment = new Comment();
        comment.setComment("schedule_1 에 대한 코멘트");
        comment.setUsername("작성자명1");
        comment.setSchedule(schedule);

        //Comment 저장
        Comment result = commentRepository.save(comment);               //relation이 제대로 잡혀있으면, 여기 한줄로 가능.
        Schedule savedSchedule = scheduleRepository.findById(1L).get(); //scheduleRepository.save(schedule);

        //Comment 출력
        System.out.println("Comment 내용 : " + result.getComment());
        System.out.println("Comment 작성자 : " + result.getUsername());

        //Schedule 반환
        System.out.println();

        System.out.println("Schedule Comment 확인 : " + savedSchedule.getComments().size());
    }

    @Test
    @Transactional
    @Rollback
    public void getComment(){
        //createComment() 테스트메소드 @Rollback(value=false)를 설정
        //schedule_id = 2L 과 schedule_id = 2L을 외래키로 갖는 Comment 하나(comment_id=5L)가 DB에 들어간 상태
        //이 Comment 정보를 Get하는 테스트

        // scheduleId 값으로 Schedule 검색
        Schedule schedule = scheduleRepository.findById(3L).orElseThrow(() -> new RuntimeException("해당 id값을 가진 스케쥴 데이터가 존재하지 않습니다."));

        // 검색한 Schedule의 comments에 commentId값으로 Comment 검색
        List<Comment> commentsInSchedule = schedule.getComments();
        Comment comment = commentsInSchedule.stream().filter(c -> c.getComment_id().equals(5L))
                .findFirst().orElseThrow(() -> new RuntimeException("해당 id값을 가진 댓글 데이터가 존재하지 않습니다."));

        // @ToString 어노테이션 작성 필요.
        System.out.println("Get한 Comment : "+comment);
    }

    @Test
    @Transactional
    @Rollback
    public void getAllComments(){
        // scheduleId 값으로 Schedule 검색
        Schedule schedule = scheduleRepository.findById(2L).orElseThrow(() -> new RuntimeException("해당 id값을 가진 스케쥴 데이터가 존재하지 않습니다."));

        // 검색한 Schedule의 Comments 리스트
        List<Comment> commentsInSchedule = schedule.getComments();

        // 정상출력 확인
        System.out.println("getAllComments() 테스트 출력");
        for(Comment comment : commentsInSchedule){
            System.out.println(comment);
        }

        //Comments 리스트 -> DTO 변환
//        List<CommentResponseDTO> commentResponseDTOs = commentsInSchedule.stream()
//                .map(comment -> new CommentResponseDTO(comment)).collect(Collectors.toList());

    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void updateComment(){
        // scheduleId 값으로 Schedule 검색
        Schedule schedule = scheduleRepository.findById(2L).orElseThrow(() -> new RuntimeException("해당 id값을 가진 스케쥴 데이터가 존재하지 않습니다."));

        // 검색한 Schedule의 comments에 commentId값으로 Comment 검색
        List<Comment> commentsInSchedule = schedule.getComments();
        Comment comment = commentsInSchedule.stream().filter(c -> c.getComment_id().equals(5L))
                .findFirst().orElseThrow(() -> new RuntimeException("해당 id값을 가진 댓글 데이터가 존재하지 않습니다."));

        // 변경 내용 반영     -> 검색해온 comment와 비교해서 DirtyChecking이 발생하여 자동으로 save()시에 update문이 발생.
        comment.setUsername("변경된 유저명");
        comment.setComment("변경된 내용");

        // comment 내용 반영 및 반환
        // 여기서 update문이 출력되는지도 확인.
        System.out.println("Dirty Checking 확인지점, Update문 실행 확인");
        Comment updatedComment = commentRepository.save(comment);

        System.out.println(updatedComment);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void deleteComment(){
        // scheduleId 값으로 Schedule 검색
        Schedule schedule = scheduleRepository.findById(2L).orElseThrow(() -> new RuntimeException("해당 id값을 가진 스케쥴 데이터가 존재하지 않습니다."));

        // 검색한 Schedule의 comments에 commentId값으로 Comment 검색
        List<Comment> commentsInSchedule = schedule.getComments();
        Comment comment = commentsInSchedule.stream().filter(c -> c.getComment_id().equals(5L))
                .findFirst().orElseThrow(() -> new RuntimeException("해당 id값을 가진 댓글 데이터가 존재하지 않습니다."));

        // comment 삭제 리턴타입 void
        commentRepository.delete(comment);
    }
}