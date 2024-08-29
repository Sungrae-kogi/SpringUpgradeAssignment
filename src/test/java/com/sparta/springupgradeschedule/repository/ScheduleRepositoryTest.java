package com.sparta.springupgradeschedule.repository;

import com.sparta.springupgradeschedule.dto.schedule.ScheduleResponseDTO;
import com.sparta.springupgradeschedule.dto.user.UserSmallResponseDTO;
import com.sparta.springupgradeschedule.entity.Schedule;
import com.sparta.springupgradeschedule.entity.User;
import com.sparta.springupgradeschedule.entity.UserSchedule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class ScheduleRepositoryTest {
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void createSchedule(){
        // userId User 검색
        User user = userRepository.findById(1L).orElseThrow(() -> new RuntimeException("해당 id값을 가진 유저 데이터가 존재하지 않습니다."));

        // RequestDto -> Entity
        Schedule schedule = new Schedule();
        schedule.setTitle("새벽제목");
        schedule.setContents("과제5단계왜이럼");

        // User가 Schedule을 생성.
        schedule.addUser(user);

        // DB 저장
        Schedule savedSchedule = scheduleRepository.save(schedule);

        List<UserSchedule> userSchedules = savedSchedule.getUserSchedules();
        for(UserSchedule userSchedule : userSchedules){
            System.out.println("userSchedule : " + userSchedule);
        }
    }

    @Test
    @Transactional
    @Rollback
    public void getSchedule(){
        // id key값으로 검색 - 현재 id = 4인 test DB가 들어가있는상태.
        // .get()의 경우 결과값이 null일 경우 NoSuchElementException 발생   https://velog.io/@aidenshin/Optional-%EA%B4%80%EB%A0%A8..
        // .orElseThrow()로 값이 null일 경우 지정한 예외를 발생시켜줄 수 있음.
        Schedule schedule = scheduleRepository.findById(1L).orElseThrow(() -> new RuntimeException("해당 id값을 가진 데이터가 존재하지 않습니다."));

        List<UserSmallResponseDTO> users = schedule.getUserSchedules().stream().map(userSchedule -> new UserSmallResponseDTO(userSchedule.getUser())).collect(Collectors.toList());



        System.out.println(schedule);
        System.out.println("------일정 조회시 담당 유저들의 고유 식별자, 유저명, 이메일 즉시로딩 테스트 ------");
        for(UserSmallResponseDTO user : users){
            System.out.println("user_id : " + user.getUser_id());
            System.out.println("username : " + user.getUsername());
            System.out.println("email : "+ user.getEmail());

        }
    }


    @Test
    @Transactional
    @Rollback
    public void updateSchedule(){
        //param : id, body : username, title, contents
        Schedule schedule = scheduleRepository.findById(4L).orElseThrow(() -> new RuntimeException("해당 id값을 가진 데이터가 존재하지 않습니다."));

        schedule.setTitle("수정된 제목");
        schedule.setContents("수정된 내용");

        //업데이트된 내용으로 save()
        Schedule updatedSchedule = scheduleRepository.save(schedule);

        //수정되고 반환받은 값 출력 및 확인
        System.out.println(updatedSchedule.getSchedule_id());
        System.out.println(updatedSchedule.getTitle());
        System.out.println(updatedSchedule.getContents());
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void addUserToSchedule(){
        // Schedule 검색  3번스케쥴에다가 유저를 배치
        Schedule schedule = scheduleRepository.findById(3L).orElseThrow(() -> new RuntimeException("해당 id값을 가진 데이터가 존재하지 않습니다."));


        // User 검색
        User user = userRepository.findById(1L).orElseThrow(() -> new RuntimeException("해당 id값을 가진 데이터가 존재하지 않습니다."));

        // Schedule에 User 배치
        schedule.addUser(user);
    
        // 출력 확인
        List<UserSchedule> userSchedules = schedule.getUserSchedules();
        for(UserSchedule userSchedule : userSchedules){
            System.out.println("userSchedule : " + userSchedule);
        }
    }



    @Test
    @Transactional
    @Rollback(value = false)
    public void deleteSchedule(){
        //schedule 2를 지우면, 연관 comment 가 삭제
        Schedule schedule = scheduleRepository.findById(2L).orElseThrow(() -> new RuntimeException("해당 id값을 가진 데이터가 존재하지 않습니다."));

        scheduleRepository.delete(schedule);
    }
}